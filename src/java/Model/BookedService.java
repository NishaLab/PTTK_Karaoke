/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author LEGION
 */
public class BookedService implements Serializable{
    private Service service;
    private float price;

    public BookedService() {
    }

    public BookedService(Service service, float price) {
        this.service = service;
        this.price = price;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookedService{" + "service=" + service + ", price=" + price + '}';
    }
    
}
