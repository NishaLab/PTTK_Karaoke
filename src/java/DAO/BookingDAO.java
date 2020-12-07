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
        String boooking = "INSERT INTO booking(createdDate, Customer_id) VALUES(?,?)";
        String bookedRoom = "INSERT INTO bookedroom(receiveDate,returnDate,price,Room_id, Booking_id)VALUES(?,?,?,?,?)";
        String bookedService = "INSERT INTO bookedservice(price,Service_id,BookedRoom_id) VALUES(?,?,?)";
        String bookedItem = "INSERT INTO bookeditem(quantity,price,Item_id,BookedRoom_id) VALUES(?,?,?,?)";
        String check = "Select * from room where id = ? AND id IN(\n"
                + "Select Room_id from bookedroom\n"
                + "WHERE (receiveDate <= ? AND returnDate >= ?) \n"
                + "OR(receiveDate < ? AND returnDate >= ?)\n"
                + "OR(receiveDate >= ? AND returnDate <= ?))\n";
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(boooking, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date sqldate = new Date(c.getCreateDate().getTime());
            ps.setDate(1, sqldate);
            ps.setInt(2, c.getClient().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                c.setId(generatedKeys.getInt(1));
                for (BookedRoom br : c.getRooms()) {
                    ps = conn.prepareStatement(check);
                    java.sql.Timestamp sqlcheckin = new java.sql.Timestamp(br.getReceiveDate().getTime());
                    java.sql.Timestamp sqlcheckout = new java.sql.Timestamp(br.getReturnDate().getTime());
                    ps.setInt(1, br.getRoom().getId());
                    ps.setTimestamp(2, sqlcheckin);
                    ps.setTimestamp(3, sqlcheckin);
                    ps.setTimestamp(4, sqlcheckout);
                    ps.setTimestamp(5, sqlcheckout);
                    ps.setTimestamp(6, sqlcheckin);
                    ps.setTimestamp(7, sqlcheckout);
                    System.out.println(ps);
                    ResultSet checkrs = ps.executeQuery();
                    if (checkrs.next()) {
                        conn.rollback();
                        return false;
                    }
                    try {
                        ps = conn.prepareStatement(bookedRoom, Statement.RETURN_GENERATED_KEYS);
                        java.sql.Date receiveDate = new Date(br.getReceiveDate().getTime());
                        java.sql.Date returnDate = new Date(br.getReturnDate().getTime());
                        ps.setDate(1, receiveDate);
                        ps.setDate(2, returnDate);
                        ps.setFloat(3, br.getPrice());
                        ps.setInt(4, br.getRoom().getId());
                        ps.setInt(5, c.getId());
                        ps.executeUpdate();
                        ResultSet RoomKeys = ps.getGeneratedKeys();
                        if (RoomKeys.next()) {
                            br.setId(generatedKeys.getInt(1));
                            for (BookedItem item : br.getItems()) {
                                ps = conn.prepareStatement(bookedItem);
                                ps.setInt(1, item.getQuantity());
                                ps.setFloat(2, item.getPrice());
                                ps.setInt(3, br.getId());
                                ps.setInt(4, c.getId());
                                ps.executeUpdate();
                            }
                            for (BookedService service : br.getServices()) {
                                ps = conn.prepareStatement(bookedService);
                                ps.setFloat(1, service.getPrice());
                                ps.setInt(2, br.getId());
                                ps.setInt(3, c.getId());
                                ps.executeUpdate();
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
        String clientSql = "SELECT * FROM user WHERE id = ?";
        String staffSql = "SELECT * FROM user WHERE id = ?";
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
                res.setCreateDate(rs.getTimestamp("createdDate"));
                int bookingId = rs.getInt("id");
                ps = conn.prepareStatement(clientSql);
                ps.setInt(1, rs.getInt("Customer_id"));
                ResultSet clientRs = ps.executeQuery();
                if (clientRs.next()) {
                    User client = new User();
                    client.setId(clientRs.getInt("id"));
                    client.setName(clientRs.getString("name"));
                    client.setUsername(clientRs.getString("username"));
                    client.setPassword(clientRs.getString("password"));
                    client.setAddress(clientRs.getString("address"));
                    client.setEmail(clientRs.getString("email"));
                    client.setPhone(clientRs.getString("phone"));
                    client.setRole(clientRs.getString("role"));
                    res.setClient(client);
                }
                ps = conn.prepareStatement(staffSql);
                ps.setInt(1, rs.getInt("Staff_id"));
                ResultSet staffRS = ps.executeQuery();
                if (staffRS.next()) {
                    User staff = new User();
                    staff.setId(clientRs.getInt("id"));
                    staff.setName(clientRs.getString("name"));
                    staff.setUsername(clientRs.getString("username"));
                    staff.setPassword(clientRs.getString("password"));
                    staff.setAddress(clientRs.getString("address"));
                    staff.setEmail(clientRs.getString("email"));
                    staff.setPhone(clientRs.getString("phone"));
                    staff.setRole(clientRs.getString("role"));
                    res.setStaff(staff);
                }
                ps = conn.prepareStatement(bookedroom);
                ps.setInt(1, bookingId);
                ResultSet roomSet = ps.executeQuery();
                while (roomSet.next()) {
                    int roomId = roomSet.getInt("Room_id");
                    System.out.println(roomId);
                    BookedRoom br = new BookedRoom();
                    System.out.println(roomSet.getDate("receiveDate"));
                    br.setId(roomSet.getInt("id"));
                    br.setReceiveDate(roomSet.getTimestamp("receiveDate"));
                    br.setReturnDate(roomSet.getTimestamp("returnDate"));
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

    public ArrayList<Booking> getBookingByCustomerName(String name) {
        ArrayList<Booking> res = new ArrayList<>();
        String booking = "SELECT * FROM booking WHERE booking.Customer_id IN( "
                + "SELECT id FROM user where name LIKE ?) AND booking.paymentDate IS null";
        String bookedroom = "SELECT * FROM bookedroom WHERE Booking_id = ?";
        String bookeditem = "SELECT * FROM bookeditem WHERE BookedRoom_id = ?";
        String bookedservice = "SELECT * FROM bookedservice WHERE BookedRoom_id = ?";
        String clientSql = "SELECT * FROM user WHERE id = ?";
        String staffSql = "SELECT * FROM user WHERE id = ?";
        RoomDAO rd = new RoomDAO();
        ItemDAO itemDAO = new ItemDAO();
        ServiceDAO sd = new ServiceDAO();
        try {
            PreparedStatement ps = conn.prepareStatement(booking);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking book = new Booking();
                ArrayList<BookedRoom> brList = new ArrayList<>();
                book.setId(rs.getInt("id"));
                book.setCreateDate(rs.getTimestamp("createdDate"));
                int bookingId = rs.getInt("id");
                ps = conn.prepareStatement(clientSql);
                ps.setInt(1, rs.getInt("Customer_id"));
                ResultSet clientRs = ps.executeQuery();
                if (clientRs.next()) {
                    User client = new User();
                    client.setId(clientRs.getInt("id"));
                    client.setName(clientRs.getString("name"));
                    client.setUsername(clientRs.getString("username"));
                    client.setPassword(clientRs.getString("password"));
                    client.setAddress(clientRs.getString("address"));
                    client.setEmail(clientRs.getString("email"));
                    client.setPhone(clientRs.getString("phone"));
                    client.setRole(clientRs.getString("role"));
                    book.setClient(client);
                }
                ps = conn.prepareStatement(staffSql);
                ps.setInt(1, rs.getInt("Staff_id"));
                ResultSet staffRS = ps.executeQuery();
                if (staffRS.next()) {
                    User staff = new User();
                    staff.setId(clientRs.getInt("id"));
                    staff.setName(clientRs.getString("name"));
                    staff.setUsername(clientRs.getString("username"));
                    staff.setPassword(clientRs.getString("password"));
                    staff.setAddress(clientRs.getString("address"));
                    staff.setEmail(clientRs.getString("email"));
                    staff.setPhone(clientRs.getString("phone"));
                    staff.setRole(clientRs.getString("role"));
                    book.setStaff(staff);
                }
                ps = conn.prepareStatement(bookedroom);
                ps.setInt(1, bookingId);
                ResultSet roomSet = ps.executeQuery();
                while (roomSet.next()) {
                    int roomId = roomSet.getInt("Room_id");
                    System.out.println(roomId);
                    BookedRoom br = new BookedRoom();
                    System.out.println(roomSet.getDate("receiveDate"));
                    br.setId(roomSet.getInt("id"));
                    br.setReceiveDate(roomSet.getTimestamp("receiveDate"));
                    br.setReturnDate(roomSet.getTimestamp("returnDate"));
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
                book.setRooms(brList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean updateBooking(Booking booking) {
        String bookingSQL = "UPDATE `booking` SET paymentType = ?, paymentDate = ? WHERE (`id` = ?)";
        String bookedService = "INSERT INTO bookedservice(price,Service_id,BookedRoom_id) VALUES(?,?,?)";
        String bookedItem = "INSERT INTO bookeditem(quantity,price,Item_id,BookedRoom_id) VALUES(?,?,?,?)";
        ItemDAO itemDAO = new ItemDAO();
        ServiceDAO sd = new ServiceDAO();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(bookingSQL);
            java.sql.Date paymentDate = new Date(booking.getPaymentDate().getTime());
            ps.setString(1, booking.getPaymentType());
            ps.setDate(2, paymentDate);
            ps.setInt(3, booking.getId());
            System.out.println(ps);
            ps.executeUpdate();
            for (BookedRoom room : booking.getRooms()) {
                int roomId = room.getRoom().getId();
                System.out.println(roomId);
                for (BookedItem item : room.getItems()) {
                    ps = conn.prepareStatement(bookedItem);
                    ps.setInt(1, item.getQuantity());
                    ps.setFloat(2, item.getPrice());
                    ps.setInt(3, item.getItem().getId());
                    ps.setInt(4, room.getId());
                    ps.executeUpdate();
                }
                for (BookedService service : room.getServices()) {
                    ps = conn.prepareStatement(bookedService);
                    ps.setFloat(1, service.getPrice());
                    ps.setInt(2, service.getService().getId());
                    ps.setInt(3, room.getId());
                    ps.executeUpdate();
                }
            }
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception f) {
                f.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
