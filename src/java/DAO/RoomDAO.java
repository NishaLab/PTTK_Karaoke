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

public class RoomDAO extends DAO {

    public RoomDAO() {
        super();
    }

    public ArrayList<Room> searchFreeRoom(Date receivedDate, Date returnDate, String name, String type) {
        ArrayList<Room> res = new ArrayList<Room>();
        String sql = "Select * from room where name LIKE ? AND type = ? AND id NOT IN(\n"
                + "Select Room_id from bookedroom\n"
                + "WHERE (receiveDate <= ? AND returnDate >= ?) \n"
                + "OR(receiveDate < ? AND returnDate >= ?)\n"
                + "OR(receiveDate >= ? AND returnDate <= ?))\n";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            java.sql.Timestamp sqlcheckin = new java.sql.Timestamp(receivedDate.getTime());
            java.sql.Timestamp sqlcheckout = new java.sql.Timestamp(returnDate.getTime());
            ps.setString(1, "%" + name + "%");
            ps.setString(2, type);
            ps.setTimestamp(3, sqlcheckin);
            ps.setTimestamp(4, sqlcheckin);
            ps.setTimestamp(5, sqlcheckout);
            ps.setTimestamp(6, sqlcheckout);
            ps.setTimestamp(7, sqlcheckin);
            ps.setTimestamp(8, sqlcheckout);
//            ps.setTimestamp(12, sqlcheckin);
//            ps.setTimestamp(13, sqlcheckout);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setName(rs.getString("name"));
                room.setDesc(rs.getString("desc"));
                room.setType(rs.getString("type"));
                room.setPrice(rs.getFloat("price"));
                res.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public Room getRoomById(int id) {
        Room room = new Room();
        String sql = "SELECT * FROM room WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                room.setId(rs.getInt("id"));
                room.setName(rs.getString("name"));
                room.setDesc(rs.getString("desc"));
                room.setType(rs.getString("type"));
                room.setPrice(rs.getFloat("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return room;
    }

}
