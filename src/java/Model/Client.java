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
public class Client extends User implements Serializable{
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Client() {
    }

    public Client(String type) {
        this.type = type;
    }

    public Client(String type, int id, String username, String password, String email, String address, String phone) {
        super(id, username, password, email, address, phone);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Client{" + "type=" + type + '}';
    }


}
