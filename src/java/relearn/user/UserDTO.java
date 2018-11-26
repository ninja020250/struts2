/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearn.user;

import relearn.role.RoleDTO;

/**
 *
 * @author nhatc
 */
public class UserDTO {
    private String username;
    private String password;
    private RoleDTO role;

    public UserDTO(String username, String password, RoleDTO role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
    
}
