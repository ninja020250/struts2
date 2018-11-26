/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearn.role;

/**
 *
 * @author nhatc
 */
public class RoleDTO {
    private String roleName;
    private int roleID;

    public RoleDTO(String roleName, int roleID) {
        this.roleName = roleName;
        this.roleID = roleID;
    }

    public RoleDTO() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    
}
