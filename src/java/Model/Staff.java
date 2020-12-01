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
public class Staff extends User implements Serializable {

    private String position;

    public Staff() {
    }

    public Staff(String position) {
        this.position = position;
    }

    public Staff(String position, int id, String username, String password, String email, String address, String phone) {
        super(id, username, password, email, address, phone);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Staff{" + "position=" + position + '}';
    }

    
}
