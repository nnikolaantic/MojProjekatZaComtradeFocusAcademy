/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseConnection;

/**
 *
 * @author Anta
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
public class DatabaseConnection {
  
    private static Connection con = null;
  
    static{ 
        
        try {
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabazeapp", "root","");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return con;
    }
}