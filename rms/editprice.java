/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
// Importing necessary Java SQL and Swing components: 
package rms;  // Declares that the editprice class is part of the "rms" package
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *JFrame class for editing the price of a menu item.
 * @author abdul
 */
public class editprice extends javax.swing.JFrame {
Connection con=null; // Database connection object
PreparedStatement pst= null;  // Prepared statement for executing SQL queries
ResultSet rs= null;  // Result set for storing the results of SQL queries
Statement st= null;  // Statement object for executing SQL queries
    /**
     * Creates new form editprice
     */
    public editprice() {
        
        initComponents();  // Initializing the GUI components
         con = RestaurantDBConnection.connectRestaurantDB();  // Establishing a database connection
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        priceID = new javax.swing.JLabel();
        priceEdit = new javax.swing.JLabel();
        itemIDprice = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        canceleditmenu = new javax.swing.JButton();
        SaveeditPrice = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        priceID.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        priceID.setForeground(new java.awt.Color(246, 234, 234));
        priceID.setText("Item ID");
        priceID.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null));
        getContentPane().add(priceID, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 209, 74));

        priceEdit.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        priceEdit.setForeground(new java.awt.Color(246, 234, 234));
        priceEdit.setText("Price");
        priceEdit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null));
        getContentPane().add(priceEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, 209, 74));

        itemIDprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIDpriceActionPerformed(evt);
            }
        });
        getContentPane().add(itemIDprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 390, 50));
        getContentPane().add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 390, 50));

        canceleditmenu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        canceleditmenu.setText("Cancel");
        canceleditmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canceleditmenuActionPerformed(evt);
            }
        });
        getContentPane().add(canceleditmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, 160, 50));

        SaveeditPrice.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SaveeditPrice.setText("Save");
        SaveeditPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveeditPriceActionPerformed(evt);
            }
        });
        getContentPane().add(SaveeditPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 360, 160, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backGround.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemIDpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIDpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemIDpriceActionPerformed

    private void canceleditmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_canceleditmenuActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_canceleditmenuActionPerformed

    private void SaveeditPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveeditPriceActionPerformed
         // Getting the item ID and new price entered by the user
        String itemID = itemIDprice.getText();
    String newPrice = price.getText();
    
    // Validate the required fields
    if (itemID.isEmpty() || newPrice.isEmpty()) {
        // Display a message if any of the required fields are empty
        JOptionPane.showMessageDialog(null, "Please fill in both the item ID and the new price.");
        return;  // Exit the method if validation fails
    }
        try{
            // SQL query to check if the item ID exists in the database
            String checkSql = "SELECT COUNT(*) FROM menu WHERE itemID = ?";
        pst = con.prepareStatement(checkSql);
        pst.setString(1, itemID);
        
        // Execute the query
        ResultSet rs = pst.executeQuery();
        rs.next();
        
        // Check if the item ID exists
        int count = rs.getInt(1);
        if (count == 0) {
            // Display a message if the meal does not exist
            JOptionPane.showMessageDialog(null, "The meal whose price you are trying to edit doesn't exist.");
            // Close the resources
            rs.close();
            pst.close();
            return;  // Exit the method
        }
        
        // Close the ResultSet and PreparedStatement
        rs.close();
        pst.close();
        // SQL query to update the price of the menu item: 
            String sql = "UPDATE menu SET price=? WHERE itemID= ? ;";
          
            pst= con.prepareStatement(sql);
         
            pst.setString(1, price.getText());
             pst.setString(2, itemIDprice.getText());
            pst.executeUpdate();
             // Closing the database connection and displaying success message: 
            con.close();
            JOptionPane.showMessageDialog(null, "Price Updated");
           // Opening the menu window: 
          java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        }); 
        }
        catch(Exception e)
                {
                    // Catching and handling any exceptions
               e.printStackTrace();
               // Displaying an error message
               JOptionPane.showMessageDialog(null, "Update Failed!");
                
        }
    
        
    }//GEN-LAST:event_SaveeditPriceActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(editprice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editprice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editprice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editprice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editprice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SaveeditPrice;
    private javax.swing.JButton canceleditmenu;
    private javax.swing.JTextField itemIDprice;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField price;
    private javax.swing.JLabel priceEdit;
    private javax.swing.JLabel priceID;
    // End of variables declaration//GEN-END:variables
}
