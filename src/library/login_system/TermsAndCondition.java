
package library.login_system;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import library.formsPopUp.Terms_Service;

/**
 *
 * @author USER
 */
public class TermsAndCondition extends javax.swing.JFrame {

 
    public TermsAndCondition() {
        initComponents();
        
        setBackground(new Color(0,0,0,0));
        terms();
        TermsAndService.setBorder(null);
        TermsAndService.setBorder(BorderFactory.createEmptyBorder());
         jScrollPane1.setBorder(null);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        initMoving(this);
    }

    private void terms(){
       String termsText = "Terms of Service\n" +
"\n" +
"Welcome to the Bibleothica Harmony platform, which is owned and operated by Name? These terms of service (which includes our Code of Conduct and our Privacy Policy) (\"Terms of Service\") are a contract between you and Portfolio. By using Portfolio (the \"Site\" and application), creating your account, and using the Site or our mobile applications to borrow and/or rent books, reading, or to access and view Portfolio content or other user content (together with the Site, the \"Services\"), you’re agreeing to these Terms of Service. If you don’t agree to any of these terms, you can’t use the Portfolio Services.\n" +
"\n" +
"You Need to be 18\n" +
"Portfolio Services are only for people 18 years old and over. If we learn someone under 18 is using Portfolio Services, we’ll terminate their account.\n" +
"\n" +
"You Need an Account\n" +
"You can’t browse very much on the Site or use the Services without registering for an account. To get the most out of the Portfolio platform, you’ll need to register, choose an account name, and set a password.\n" +
"\n" +
"You’re responsible for all the activity on your account, and for keeping your password confidential. If you share your account information with anyone, that other person may be able to take control of the account, and we may not be able to determine who is the proper account holder. We will not have any liability to you (or anyone you share your account information with) because of your or their actions under those circumstances. If you find out that someone’s used your account without your permission, you should report it immediately.\n" +
"\n" +
"Your Book is Yours\n" +
"All the books you’ve borrow, or rent will be under your supervision. You must make sure that you have understood the legal policies and consequences when paying for the amount declared for each book. Remember that every book has different prices depending upon the class and type of it. Renting a book will only last for ilang buwan. After this span of period, you will get no access to the book you add to your library.\n" +
"\n" +
"\n" +
"\n" +
"Things You Should and Shouldn’t Do\n" +
"We expect all the users to behave responsibly and help keep this a nice place. Please review the Terms of Service—which is designed to ensure that everyone understands what the Portfolio platform is all about and the values we treasure, to create a safe, fun, and caring environment for bookworms and wizards. Without restricting what’s in the Code of Conduct, don’t do any of these things on the Services:";
   TermsAndService.setText(termsText);
   
   }
    private int x;
private int y;

    public void initMoving(JFrame frame){
        roundPanel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
                x = e.getX();
                y = e.getY();
            }
            
        });
        roundPanel1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
             frame.setLocation(e.getXOnScreen()-x, e.getYOnScreen()-y);
                
                
            }
        });
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new library.components.RoundPanel();
        roundPanel2 = new library.components.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TermsAndService = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setRoundBottomLeft(30);
        roundPanel1.setRoundBottomRight(30);
        roundPanel1.setRoundTopLeft(30);
        roundPanel1.setRoundTopRight(30);

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        TermsAndService.setEditable(false);
        TermsAndService.setBackground(new java.awt.Color(255, 255, 255));
        TermsAndService.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TermsAndService.setForeground(new java.awt.Color(102, 102, 102));
        jScrollPane1.setViewportView(TermsAndService);

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        Sign_up signup;
        try {
            signup = new Sign_up();
             signup.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(TermsAndCondition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TermsAndCondition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TermsAndCondition.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        setVisible(false);
    }//GEN-LAST:event_jLabel2MouseClicked

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
            java.util.logging.Logger.getLogger(TermsAndCondition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TermsAndCondition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TermsAndCondition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TermsAndCondition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TermsAndCondition().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane TermsAndService;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private library.components.RoundPanel roundPanel1;
    private library.components.RoundPanel roundPanel2;
    // End of variables declaration//GEN-END:variables
}
