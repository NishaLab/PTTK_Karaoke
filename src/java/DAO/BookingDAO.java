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
        String boooking         = "INSERT INTO booking(paymentType, paymentDate, Customer_id, Staff_id) VALUES(?,?,?,?)";
        String bookedRoom       = "INSERT INTO bookedroom(receivedDate,returnDate,penAmount,totalprice,tblCar_id,tblContract_id)VALUES(?,?,?,?,?,?)";
        String bookedService    = "INSERT INTO bookedservice(price,Service_id,BookedRoom_id) VALUES(?,?,?)";
        String bookedItem       = "INSERT INTO bookeditem(quantity,price,Item_id,BookedRoom_id) VALUES(?,?,?,?)";
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(boooking, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date sqldate = new Date(c.getPaymentDate().getTime());
            ps.setDate(1, sqldate);
            ps.setBoolean(2, true);
            ps.setInt(3, c.getStaff().getId());
            ps.setInt(4, c.getClient().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                c.setId(generatedKeys.getInt(1));
                for (BookedCar bc : c.getCar()) {
                    try {
                        String select = "select * from `tblbookedcar`  where receivedDate = ? and returnDate =?  "
                                + "and penAmount = ? and totalprice = ? and tblCar_id = ?";
                        java.sql.Timestamp sqlreceived = new java.sql.Timestamp(bc.getReceivedDate().getTime());
                        java.sql.Timestamp sqlreturn = new java.sql.Timestamp(bc.getReturnDate().getTime());
                        PreparedStatement sps = conn.prepareStatement(select);
                        sps.setTimestamp(1, sqlreceived);
                        sps.setTimestamp(2, sqlreturn);
                        sps.setFloat(3, bc.getPenAmount());
                        sps.setFloat(4, bc.getTotalPrice());
                        sps.setInt(5, bc.getCar().getId());
                        ResultSet crs = sps.executeQuery();
                        if (crs.next()) {
                            JOptionPane.showMessageDialog(null, "Car " + bc.getCar().getId() + " " + bc.getCar().getName() + " have been booked");
                            conn.rollback();
                            return false;
                        }
                        ps = conn.prepareStatement(bookedRoom, Statement.RETURN_GENERATED_KEYS);
                        ps.setTimestamp(1, sqlreceived);
                        ps.setTimestamp(2, sqlreturn);
                        ps.setFloat(3, bc.getPenAmount());
                        ps.setFloat(4, bc.getTotalPrice());
                        ps.setInt(5, bc.getCar().getId());
                        ps.setInt(6, c.getId());
                        ps.executeUpdate();
                        generatedKeys = ps.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            bc.setId(generatedKeys.getInt(1));
                        }
                        ps = conn.prepareStatement(updateCar);
                        ps.setInt(1, bc.getCar().getId());
                        ps.executeUpdate();
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
                for (ContractWarrant cw : c.getConWarrant()) {
                    try {
                        ps = conn.prepareStatement(conWarrant);
                        java.sql.Date sqlcheckin = new Date(cw.getCheckIn().getTime());
                        java.sql.Date sqlcheckout = new Date(cw.getCheckOut().getTime());
                        ps.setDate(1, sqlcheckin);
                        ps.setDate(2, sqlcheckout);
                        ps.setInt(3, cw.getWarrant().getId());
                        ps.setInt(4, c.getId());
                        ps.executeUpdate();
                    } catch (Exception f) {
                        f.printStackTrace();
                        try {
                            conn.rollback();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            conn.commit();
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
}
