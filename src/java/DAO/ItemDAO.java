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

public class ItemDAO extends DAO {

    public ItemDAO() {
        super();
    }

    public ArrayList<Item> searchItem(String name) {
        ArrayList<Item> res = new ArrayList<Item>();
        String sql = "SELECT * FROM Item WHERE name LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setType(rs.getString("type"));
                item.setDesc(rs.getString("desc"));
                item.setPrice(rs.getFloat("price"));
                res.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public Item getItemById(int id) {
        Item item = new Item();
        String sql = "SELECT * FROM Item WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setType(rs.getString("type"));
                item.setDesc(rs.getString("desc"));
                item.setPrice(rs.getFloat("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

}
