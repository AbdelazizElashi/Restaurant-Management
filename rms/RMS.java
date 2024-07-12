/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rms; // Declares that the RMS class is part of the "rms" package

/**
 * Main class of the RMS (Restaurant Management System) application.
 * This class contains the main method to start the application.
 * @author abdul
 */
public class RMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // Create an instance of RestaurantDBConnection class
        RestaurantDBConnection con = new RestaurantDBConnection();
           // Call the connectRestaurantDB method to establish a database connection
        con.connectRestaurantDB();
         // Open the login GUI
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }
    
}
