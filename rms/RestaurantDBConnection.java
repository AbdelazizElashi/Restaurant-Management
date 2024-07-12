/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rms;
import java.sql.*; /* this package provides classes and interfaces for accessing
and processing data stored in a database. It allows us to connect to databases, execute SQL queries, and manipulate
 data within java programs */
/**
 *
 * @author abdul
 */
public class RestaurantDBConnection {
    public static Connection connectRestaurantDB(){
        try{
            Class.forName("org.sqlite.JDBC"); /*Method to load the sql driver. Inside 
           the parameter inside it is a reference to the class  which contains implementation
            of the SQLite JDBC driver. Which we need to establish connections with our SQLite databases.*/
            Connection con = DriverManager.getConnection("jdbc:sqlite:RDB.db");
            /* this line establishes a connection with our 'RDB' database, using driver manager.  */
            //get connection to our Restaurant Database using the driver manager. 
            System.out.println("Connected");
            return con;
        }
        catch(Exception e){
            System.out.println("Failed");
            return null;
        }
    }
}
