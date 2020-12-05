/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author LEGION
 */
import Model.*;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends DAO {

    public UserDAO() {
        super();
    }

    public boolean checkLogin(User s) {
        boolean result = false;
//        String sql = "SELECT pttk.user.id, pttk.user.name, pttk.user.address, pttk.user.email, pttk.user.phone , pttk.staff.position, pttk.customer.type "
//                + "FROM pttk.user LEFT JOIN pttk.staff "
//                + "ON pttk.user.id = pttk.staff.user_id "
//                + "LEFT JOIN pttk.customer "
//                + "ON pttk.user.id = pttk.customer.user_id "
//                + "WHERE username = ? AND password = ?";
        String sql = "SELECT * From pttk.user "
                + "WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            System.out.println(ps);
            ps.setString(1, s.getUsername());
            ps.setString(2, s.getPassword());
            System.out.println(ps);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAddress(rs.getString("address"));
                s.setPhone(rs.getString("phone"));
                s.setEmail(rs.getString("email"));
                s.setRole(rs.getString("role"));
//                String pos = "";
//                String type = "";
//                try {
//                    pos = rs.getString("position");
//                    type = rs.getString("type");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                if (!(pos == null)&& !pos.isEmpty() && pos.equalsIgnoreCase("receptionist")) {
//                    Receptionist tmp = new Receptionist();
//                    tmp.setId(rs.getInt("id"));
//                    tmp.setName(rs.getString("name"));
//                    tmp.setUsername(s.getUsername());
//                    tmp.setPassword(s.getPassword());
//                    tmp.setAddress(rs.getString("address"));
//                    tmp.setEmail(rs.getString("email"));
//                    tmp.setPhone(rs.getString("email"));
//                    tmp.setPosition(rs.getString("position"));
//                    s = tmp;
//                }
//                if (!type.isEmpty()) {
//                    Client tmp = new Client();
//                    tmp.setId(rs.getInt("id"));
//                    tmp.setName(rs.getString("name"));
//                    tmp.setUsername(s.getUsername());
//                    tmp.setPassword(s.getPassword());
//                    tmp.setAddress(rs.getString("address"));
//                    tmp.setEmail(rs.getString("email"));
//                    tmp.setPhone(rs.getString("email"));
//                    tmp.setType(rs.getString("type"));
//                    s = tmp;
//                }
            }
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean saveClient(Client s) {
        String user = "INSERT INTO user(username, password, email, address, phone, name) VALUES(?,?,?,?,?,?)";
        String client = "INSERT INTO customer(type, user_id) VALUES(?,?)";
        System.out.println("Here");

        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(user, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, s.getUsername());
            ps.setString(2, s.getPassword());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getAddress());
            ps.setString(5, s.getPhone());
            ps.setString(6, s.getName());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                s.setId(generatedKeys.getInt(1));
                ps = conn.prepareStatement(client);
                ps.setString(1, s.getType());
                ps.setInt(2, s.getId());
                ps.executeUpdate();
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception f) {
                f.printStackTrace();
            }
            return false;
        }
        return true;
    }
}
