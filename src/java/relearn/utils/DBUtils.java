/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearn.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author nhatc
 */
public class DBUtils implements Serializable{
    public static Connection MakeConnection() throws NamingException, SQLException{
        Context context =   new InitialContext();
        Context tomcatContext =  (Context)context.lookup("java:comp/env");
        DataSource dataSource =  (DataSource)tomcatContext.lookup("hoclai");
        Connection conn =  dataSource.getConnection();
        return conn;
    }
}
