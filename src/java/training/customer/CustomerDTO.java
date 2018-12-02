/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.customer;

import java.io.Serializable;
import training.role.RoleDTO;

/**
 *
 * @author nhatc
 */
public class CustomerDTO implements Serializable{

    private int id;
    private String username;
    private RoleDTO role;
    private String fullname;

    public CustomerDTO(int id, String username, String fullname) {
        this.id = id;
        this.username = username;
        this.role = new RoleDTO();
        this.fullname = fullname;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public CustomerDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}
