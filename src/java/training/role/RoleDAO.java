/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import training.customer.CustomerDTO;
import utils.DBUtil;

/**
 *
 * @author nhatc
 */
public class RoleDAO {

   
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    int Result = 0;

    private void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("some things happen prevent close connection");
        } finally {
        }
    }

  

    public List<RoleDTO> getRoles() {
        try {
            List<RoleDTO> roles = new ArrayList<RoleDTO>();
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "Select id, name from roletbl";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int id = resultSet.getInt("id");
                    int totalProduct = countingProductFollowRole(id);
                    RoleDTO dto = new RoleDTO(id, name, totalProduct);
                    roles.add(dto);
                }
                return roles;
            }
            return null;
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return null;
    }

    private int countingProductFollowRole(int roleId) {
        ResultSet rs = null;
        PreparedStatement stm = null;
        Connection conn = null;
        try {
            conn = DBUtil.makeDBConnection();

            if (conn != null) {
                String sql = "select COUNT(id) as numberOf from customertbl where role_id=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, roleId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int number = rs.getInt("numberOf");
                    return number;
                }
                return 0;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
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
        return 0;
    }

    public Map<String, String> loadRoleAsMap() {
        try {
            Map<String, String> listRole = new HashMap<String, String>();
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "Select id, name from roletbl";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int id = resultSet.getInt("id");
                    listRole.put(String.valueOf(id), name);
                }
                return listRole;
            }
            return null;
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return null;
    }
}
