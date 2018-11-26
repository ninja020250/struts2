/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearn.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import relearn.utils.DBUtils;

/**
 *
 * @author nhatc
 */
public class TypeDAO {

    private List<TypeDTO> listType = null;
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

    public List<TypeDTO> getListType() {
        if (listType == null) {
            listType = new ArrayList<>();
        }
        try {
            conn = DBUtils.MakeConnection();
            if (conn != null) {
                String sql = "select * from Type";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    listType.add(new TypeDTO(id, name));
                }
                for (TypeDTO dto : listType) {
                    dto.setNumber(counting(dto.getId()));
                }
                return listType;
            }
        } catch (Exception ex) {
            System.out.println("login-failed " + ex.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public int counting(int typeId) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            if (conn == null) {
                conn = DBUtils.MakeConnection();
            }
            if (conn != null) {
                String sql = "select COUNT(id) as number from Product where type_id=?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, typeId);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int number = resultSet.getInt("number");
                    return number;
                }
            }
        } catch (Exception ex) {
            System.out.println("counting-failed " + ex.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }
}
