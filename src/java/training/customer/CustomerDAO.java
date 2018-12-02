/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

/**
 *
 * @author nhatc
 */
public class CustomerDAO {

    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    int result = 0;
    private List<CustomerDTO> customers = null;
    private final String AUTHOR = "admin";

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

    public CustomerDTO login(String username, String password) {
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "Select c.id, c.username, c.role_id, c.fullname, r.name as roleName from customertbl as c, roletbl as r where username=? and password=? and r.id=c.role_id";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String roleName = resultSet.getString("roleName");
                    String fullname = resultSet.getString("fullname");
                    int id = resultSet.getInt("id");
                    int roleId = resultSet.getInt("role_id");
                    CustomerDTO dto = new CustomerDTO(id, username, fullname);
                    dto.getRole().setId(roleId);
                    dto.getRole().setName(roleName);
                    return dto;
                }
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return null;
    }

    public List<CustomerDTO> getCustomers() {
        try {
            customers = new ArrayList<CustomerDTO>();
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "Select c.id, c.username, c.role_id, c.fullname, r.name as roleName from customertbl as c, roletbl as r where r.id=c.role_id ";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int roleId = resultSet.getInt("role_id");
                    String username = resultSet.getString("username");
                    String fullname = resultSet.getString("fullname");
                    String roleName = resultSet.getString("roleName");
                    CustomerDTO dto = new CustomerDTO(id, username, fullname);
                    dto.getRole().setId(roleId);
                    dto.getRole().setName(roleName);
                    customers.add(dto);
                }
                return customers;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return null;
    }

    public List<CustomerDTO> searchByfullName(String searchValue, int currentFilter) {
        List<CustomerDTO> result = new ArrayList<>();
        List<CustomerDTO> customers = filter(currentFilter);
        for (CustomerDTO customer : customers) {
            if (customer.getFullname().toUpperCase().contains(searchValue.toUpperCase())) {
                result.add(customer);
            }
        }
        return result;
    }

    public List<CustomerDTO> filter(int roleId) {
        try {
            customers = new ArrayList<CustomerDTO>();
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                if (roleId > 0) {
                    String sql = "Select c.id, c.username, c.role_id, c.fullname, r.name as roleName from customertbl as c, roletbl as r where r.id=c.role_id and role_id=?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, roleId);
                } else {
                    String sql = "Select c.id, c.username, c.role_id, c.fullname, r.name as roleName from customertbl as c, roletbl as r where r.id=c.role_id";
                    preparedStatement = connection.prepareStatement(sql);
                }
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String fullname = resultSet.getString("fullname");
                    String roleName = resultSet.getString("roleName");
                    roleId = resultSet.getInt("role_id");
                    CustomerDTO dto = new CustomerDTO(id, username, fullname);
                    dto.getRole().setId(roleId);
                    dto.getRole().setName(roleName);
                    customers.add(dto);
                }
                return customers;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean removeCustomer(int customerId) {
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "DELETE customertbl where id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, customerId);
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean updateCustomer(int customerId, String fullname, int roleId) {
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "UPDATE customertbl set fullname=?, role_id=? where id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, fullname);
                preparedStatement.setInt(2, roleId);
                preparedStatement.setInt(3, customerId);
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return false;
    }

}
