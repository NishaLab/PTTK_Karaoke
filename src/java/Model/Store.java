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
public class Store implements Serializable {
    private int id;
    private String name, address, desc;

    public Store() {
    }

    public Store(int id, String name, String address, String desc) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Store{" + "id=" + id + ", name=" + name + ", address=" + address + ", desc=" + desc + '}';
    }
    
}
