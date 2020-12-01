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
import java.util.*;

public class ServiceDAO extends DAO {

    public ServiceDAO() {
        super();
    }

    public ArrayList<Service> searchService(String name) {
        ArrayList<Service> res = new ArrayList<Service>();
        String sql = "SELECT * FROM Service WHERE name LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getInt("id"));
                service.setName(rs.getString("name"));
                service.setDesc(rs.getString("desc"));
                res.add(service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
