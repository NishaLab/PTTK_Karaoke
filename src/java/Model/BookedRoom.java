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
public class BookedRoom implements Serializable{
    private int id;
    private Date returnDate, receiveDate;
    private Room room;
    private ArrayList<BookedItem> items;
    private ArrayList<BookedService> services;
    
    public BookedRoom() {
    }

    public BookedRoom(int id, Date returnDate, Date receiveDate, Room room, ArrayList<BookedItem> items, ArrayList<BookedService> services) {
        this.id = id;
        this.returnDate = returnDate;
        this.receiveDate = receiveDate;
        this.room = room;
        this.items = items;
        this.services = services;
    }

    public ArrayList<BookedItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<BookedItem> items) {
        this.items = items;
    }

    public ArrayList<BookedService> getServices() {
        return services;
    }

    public void setServices(ArrayList<BookedService> services) {
        this.services = services;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "BookedRoom{" + "id=" + id + ", returnDate=" + returnDate + ", receiveDate=" + receiveDate + ", room=" + room + '}';
    }
    
}
