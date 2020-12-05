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
public class Receptionist extends Staff implements Serializable{

    public Receptionist() {
    }

    public Receptionist(int id, String username, String password, String email, String address, String phone, String name, String role) {
        super("receptionist", id, username, password, email, address, phone, name, role);
    }




    
}
