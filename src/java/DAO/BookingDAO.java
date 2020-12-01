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
import com.mysql.jdbc.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BookingDAO extends DAO {

    public BookingDAO() {
        super();
    }

    public boolean addBooking(Booking c) {
        String boooking = "INSERT INTO booking(paymentType, paymentDate, Customer_id, Staff_id) VALUES(?,?,?,?)";
        String bookedRoom = "INSERT INTO bookedroom(receivedDate,returnDate,Room_id, Booking_id)VALUES(?,?,?,?,?,?)";
        String bookedService = "INSERT INTO bookedservice(price,Service_id,BookedRoom_id) VALUES(?,?,?)";
        String bookedItem = "INSERT INTO bookeditem(quantity,price,Item_id,BookedRoom_id) VALUES(?,?,?,?)";
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(boooking, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date sqldate = new Date(c.getPaymentDate().getTime());
            ps.setString(1, c.getPaymentType());
            ps.setDate(2, sqldate);
            ps.setInt(3, c.getClient().getId());
            ps.setInt(4, c.getStaff().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                c.setId(generatedKeys.getInt(1));
                for (BookedRoom br : c.getRooms()) {
                    try {
                        ps = conn.prepareStatement(bookedRoom, Statement.RETURN_GENERATED_KEYS);
                        java.sql.Date receiveDate = new Date(br.getReceiveDate().getTime());
                        java.sql.Date returnDate = new Date(br.getReturnDate().getTime());
                        ps.setDate(1, receiveDate);
                        ps.setDate(2, returnDate);
                        ps.setInt(3, br.getRoom().getId());
                        ps.setInt(4, c.getId());
                        ResultSet RoomKeys = ps.getGeneratedKeys();
                        if (RoomKeys.next()) {
                            br.setId(generatedKeys.getInt(1));
                            for (BookedItem item : br.getItems()) {
                                ps = conn.prepareStatement(bookedItem);
                                ps.setInt(1, item.getQuantity());
                                ps.setFloat(1, item.getPrice());
                                ps.setInt(3, br.getId());
                                ps.setInt(4, c.getId());
                            }
                            for (BookedService service : br.getServices()) {
                                ps = conn.prepareStatement(bookedItem);
                                ps.setFloat(1, service.getPrice());
                                ps.setInt(3, br.getId());
                                ps.setInt(4, c.getId());
                            }
                        }
                    } catch (Exception f) {
                        f.printStackTrace();
                        try {
                            conn.rollback();
                        } catch (Exception e) {
                            e.printStackTrace();
                            return false;
                        }
                    }

                }
            }
            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception f) {
                f.printStackTrace();
                return false;
            }
            return false;
        }
        return true;
    }

    public ArrayList<Booking> searchBooking(int id) {
        ArrayList<Booking> res = new ArrayList<>();
        String boooking = "SELECT * FROM booking WHERE id = ?";
        String bookedRoom = "INSERT INTO bookedroom(receivedDate,returnDate,Room_id, Booking_id)VALUES(?,?,?,?,?,?)";
        String bookedService = "INSERT INTO bookedservice(price,Service_id,BookedRoom_id) VALUES(?,?,?)";
        String bookedItem = "INSERT INTO bookeditem(quantity,price,Item_id,BookedRoom_id) VALUES(?,?,?,?)";
        return res;
    }
}
