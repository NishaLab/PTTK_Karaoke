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

    public Booking getBooking(int id) {
        Booking res = new Booking();
        String booking = "SELECT * FROM booking WHERE id = ?";
        String bookedroom = "SELECT * FROM bookedroom WHERE Booking_id = ?";
        String bookeditem = "SELECT * FROM bookeditem WHERE BookedRoom_id = ?";
        String bookedservice = "SELECT * FROM bookedservice WHERE BookedRoom_id = ?";
        RoomDAO rd = new RoomDAO();
        ItemDAO itemDAO = new ItemDAO();
        ServiceDAO sd = new ServiceDAO();
        ArrayList<BookedRoom> brList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(booking);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res.setId(id);
                res.setPaymentDate(rs.getDate("paymentDate"));
                res.setPaymentType(rs.getString("paymentType"));
                int bookingId = rs.getInt("id");
                ps = conn.prepareStatement(bookedroom);
                ps.setInt(1, bookingId);
                ResultSet roomSet = ps.executeQuery();
                while (roomSet.next()) {
                    int roomId = roomSet.getInt("Room_id");
                    System.out.println(roomId);
                    BookedRoom br = new BookedRoom();
                    System.out.println(roomSet.getDate("receiveDate"));
                    br.setId(roomSet.getInt("id"));
                    br.setReceiveDate(roomSet.getDate("receiveDate"));
                    br.setReturnDate(roomSet.getDate("returnDate"));
                    br.setPrice(roomSet.getFloat("price"));
                    br.setRoom(rd.getRoomById(roomId));
                    ps = conn.prepareStatement(bookeditem);
                    ps.setInt(1, roomId);
                    ResultSet itemSet = ps.executeQuery();
                    while (itemSet.next()) {
                        BookedItem bi = new BookedItem();
                        int itemId = itemSet.getInt("Item_id");
                        bi.setItem(itemDAO.getItemById(itemId));
                        bi.setPrice(itemSet.getFloat("price"));
                        bi.setQuantity(itemSet.getInt("quantity"));
                        System.out.println(bi);
                        br.getItems().add(bi);
                    }
                    ps = conn.prepareStatement(bookedservice);
                    ps.setInt(1, roomId);
                    ResultSet serviceSet = ps.executeQuery();
                    while (serviceSet.next()) {
                        BookedService bs = new BookedService();
                        int serviceID = serviceSet.getInt("Service_id");
                        bs.setService(sd.getServiceById(serviceID));
                        bs.setPrice(serviceSet.getFloat("price"));
                        System.out.println(bs);
                        br.getServices().add(bs);
                    }
                    
                    brList.add(br);
                }
            }
            res.setRooms(brList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
