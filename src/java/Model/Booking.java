/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author LEGION
 */
public class Booking implements Serializable{
    private int id;
    private ArrayList<BookedRoom> rooms;
    private String paymentType;
    private Date paymentDate;
    private Client client;
    private Staff staff;
    public Booking() {
    }

    public Booking(int id, ArrayList<BookedRoom> rooms, String paymentType, Date paymentDate, Client client, Staff staff) {
        this.id = id;
        this.rooms = rooms;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.client = client;
        this.staff = staff;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<BookedRoom> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<BookedRoom> rooms) {
        this.rooms = rooms;
    }


    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Booking{" + "id=" + id + ", rooms=" + rooms + ", paymentType=" + paymentType + ", paymentDate=" + paymentDate + '}';
    }

    
}
