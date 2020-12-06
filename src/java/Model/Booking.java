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
    private Date createDate;
    private User client;
    private User staff;
    public Booking() {
        rooms = new ArrayList<>();
        client = new User();
        staff = new User();
    }

    public Booking(int id, ArrayList<BookedRoom> rooms, String paymentType, Date paymentDate, Client client, Staff staff) {
        this.id = id;
        this.rooms = rooms;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.client = client;
        this.staff = staff;
    }

    public Booking(int id, ArrayList<BookedRoom> rooms, String paymentType, Date paymentDate, Date createDate, User client, User staff) {
        this.id = id;
        this.rooms = rooms;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.createDate = createDate;
        this.client = client;
        this.staff = staff;
    }

    public Booking(int id, ArrayList<BookedRoom> rooms, String paymentType, Date paymentDate, User client, User staff) {
        this.id = id;
        this.rooms = rooms;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.client = client;
        this.staff = staff;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getStaff() {
        return staff;
    }

    public void setStaff(User staff) {
        this.staff = staff;
    }



    public void setClient(Client client) {
        this.client = client;
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
