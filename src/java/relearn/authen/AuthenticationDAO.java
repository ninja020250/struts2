/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearn.authen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import relearn.role.RoleDTO;
import relearn.user.UserDTO;
import relearn.utils.DBUtils;

/**
 *
 * @author nhatc
 */
public class AuthenticationDAO {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement stm = null;
    int result = 0;

    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
        }
    }

    public UserDTO checkLogin(String username, String password) {
        try {
            conn = DBUtils.MakeConnection();
            if (conn != null) {
                String sql = "select * from Admin where username=? and password=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    RoleDTO role = getRole(rs.getInt("role_id"));
                    UserDTO user = new UserDTO(username, password, role);
                    return user;
                }
            }
        } catch (Exception ex) {
            System.out.println("login-failed " + ex.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public RoleDTO getRole(int roleId) {
        try {
            if (conn == null) {
                conn = DBUtils.MakeConnection();
            }
            if (conn != null) {
                String sql = "select * from Role where id=? ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, roleId);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    return new RoleDTO(rs.getString("Role_name"), rs.getInt("id"));
                } else {
                    return null;
                }
            }
        } catch (Exception ex) {
            System.out.println("get role failed " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
        }
        return null;
    }
}
