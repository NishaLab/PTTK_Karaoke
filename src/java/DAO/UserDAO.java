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
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends DAO {

    public UserDAO() {
        super();
    }

    public boolean checkLogin(User s) {
        boolean result = false;
        String sql = "SELECT user.id, user.name, user.address, user.email, user.phone , staff.position, customer.type "
                + "FROM user LEFT JOIN staff "
                + "ON user.id = staff.user_id"
                + "LEFT JOIN customer"
                + "ON user.id = customer.type"
                + "WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getUsername());
            ps.setString(2, s.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                String pos = rs.getString("position");
                String type = rs.getString("type");
                if (!pos.isEmpty() && pos.equalsIgnoreCase("receptionist")) {
                    Receptionist tmp = new Receptionist();
                    tmp.setId(rs.getInt("id"));
                    tmp.setName(rs.getString("name"));
                    tmp.setUsername(s.getUsername());
                    tmp.setPassword(s.getPassword());
                    tmp.setAddress(rs.getString("address"));
                    tmp.setEmail(rs.getString("email"));
                    tmp.setPhone(rs.getString("email"));
                    tmp.setPosition(rs.getString("position"));
                    s = tmp;
                }
                if (!type.isEmpty()) {
                    Client tmp = new Client();
                    tmp.setId(rs.getInt("id"));
                    tmp.setName(rs.getString("name"));
                    tmp.setUsername(s.getUsername());
                    tmp.setPassword(s.getPassword());
                    tmp.setAddress(rs.getString("address"));
                    tmp.setEmail(rs.getString("email"));
                    tmp.setPhone(rs.getString("email"));
                    tmp.setType(rs.getString("type"));
                    s = tmp;
                }
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
