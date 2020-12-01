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
    private ArrayList<BookedItem> items;
    private ArrayList<BookedService> services;
    private String paymentType;
    private Date paymentDate;

    public Booking() {
        items = new ArrayList<>();
        services = new ArrayList<>();
    }

    public Booking(int id, ArrayList<BookedItem> items, ArrayList<BookedService> services, String paymentType, Date paymentDate) {
        this.id = id;
        this.items = items;
        this.services = services;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Booking{" + "id=" + id + ", items=" + items + ", services=" + services + ", paymentType=" + paymentType + ", paymentDate=" + paymentDate + '}';
    }
    
}
