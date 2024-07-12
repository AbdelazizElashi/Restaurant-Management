/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package rms;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author abdul
 */
public class register extends javax.swing.JFrame {
Connection con=null; 
PreparedStatement pst= null; 
ResultSet rs= null; 
Statement st= null; 
    /**
     * Creates new form register
     */
    public register() {
        
        initComponents();
        con = RestaurantDBConnection.connectRestaurantDB();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        exit2 = new javax.swing.JButton();
        register2 = new javax.swing.JButton();
        passwordr = new javax.swing.JTextField();
        emailr = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(246, 234, 234));
        jLabel2.setText("Email");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 209, 74));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(246, 234, 234));
        jLabel1.setText("Password");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 209, 74));

        exit2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        exit2.setText("Exit");
        exit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit2ActionPerformed(evt);
            }
        });
        getContentPane().add(exit2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, 153, 73));

        register2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        register2.setText("Register");
        register2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                register2ActionPerformed(evt);
            }
        });
        getContentPane().add(register2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 420, 153, 73));
        getContentPane().add(passwordr, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 273, 64));
        getContentPane().add(emailr, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 273, 64));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backGround.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_exit2ActionPerformed

    private void register2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_register2ActionPerformed
        // TODO add your handling code here:
         // Check if any of the required fields are empty
    if (emailr.getText().isEmpty() || passwordr.getText().isEmpty()) {
        // Display a message indicating that all fields must be filled
        JOptionPane.showMessageDialog(null, "Please fill in all fields.");
        return; // Exit the method without proceeding further
    }
         try {
        String countSql = "SELECT COUNT(*) FROM login WHERE id BETWEEN 1111 AND 1112";
        pst = con.prepareStatement(countSql);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            int managerCount = rs.getInt(1);

            // Check if the number of managers is less than two
            if (managerCount < 2) {
                // Determine the ID for the new manager
                int newManagerID = 1111 + managerCount;

                // Prepare the SQL statement to register the new manager
                String sql = "INSERT INTO login (id, email, password) VALUES (?, ?, ?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, newManagerID);
                pst.setString(2, emailr.getText());
                pst.setString(3, passwordr.getText());
                pst.executeUpdate();

                // Display success message and open the login window
                JOptionPane.showMessageDialog(null, "Registration Successful!!");
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new login().setVisible(true);
                    }
                });
            } else {
                // Display a message if the maximum number of managers has been reached
                JOptionPane.showMessageDialog(null, "Maximum number of manager registrations reached. Cannot register more managers.");
            }
        }

        // Close the ResultSet and PreparedStatement
        rs.close();
        pst.close();
        
    } catch (Exception e) {
         // Catch any exceptions that occur during the execution of the try block
        e.printStackTrace();
        // Display an error message dialog box indicating that registration failed
        JOptionPane.showMessageDialog(null, "Registration FAILED!!");
    }

    }//GEN-LAST:event_register2ActionPerformed

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
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailr;
    private javax.swing.JButton exit2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField passwordr;
    private javax.swing.JButton register2;
    // End of variables declaration//GEN-END:variables
}
