/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package library.forms;

import java.util.Arrays;
import javax.swing.JOptionPane;
import library.controller.userController;
import library.model.ModelUserData;

/**
 *
 * @author USER
 */
public class AccountInformation extends javax.swing.JPanel {

   
    public AccountInformation() {
        initComponents();
        setOpaque(false);
       hidePasswordField();
       userId.setVisible(false);
    }

    public void hidePasswordField(){
        p1lb.setVisible(false);
        p2lb.setVisible(false);
        p3lb.setVisible(false);
        passwordField1.setVisible(false);
        passwordField2.setVisible(false);
        passwordField3.setVisible(false);
        changePasswordBtn.setVisible(false);
    }
    
     public void showPasswordField(){
        p1lb.setVisible(true);
        p2lb.setVisible(true);
        p3lb.setVisible(true);
        passwordField1.setVisible(true);
        passwordField2.setVisible(true);
        passwordField3.setVisible(true);
         changePasswordBtn.setVisible(true);
    }
     private void changePassword(){
         try {
              String UID = userId.getText();
        char[] currentPassword = passwordField1.getPassword();
        char[] newPassword = passwordField3.getPassword();

        boolean conditionsMet = true; // Assume conditions are initially met

        if (!Arrays.equals(passwordField2.getPassword(), passwordField3.getPassword())) {
            JOptionPane.showMessageDialog(this, "Incorrect Password");
            conditionsMet = false;
        } else if (passwordField1.getPassword().length == 0 || passwordField2.getPassword().length == 0 || passwordField3.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Fill out remaining Fields");
            showPasswordField();
            conditionsMet = false;
        }

        if (conditionsMet) {
            userController controller = new userController();
            ModelUserData changePass = new ModelUserData();
            changePass.setUserId(UID);
            changePass.setPassWord(currentPassword);
            changePass.setNewPassword(newPassword);
            controller.changePasswordUser(changePass);

            hidePasswordField();
            changePasswordBtn.setVisible(false);
            button1.setVisible(true);
        }
         } catch (Exception e) {
             e.printStackTrace();
         }
       
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new library.components.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        imagePicture = new library.components.ImageAvatar();
        jLabel2 = new javax.swing.JLabel();
        libraryName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        birthDate = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        userId = new javax.swing.JLabel();
        button1 = new library.button.Button();
        passwordField1 = new library.textfield.PasswordField();
        passwordField2 = new library.textfield.PasswordField();
        passwordField3 = new library.textfield.PasswordField();
        p1lb = new javax.swing.JLabel();
        p2lb = new javax.swing.JLabel();
        p3lb = new javax.swing.JLabel();
        changePasswordBtn = new library.button.Button();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setRoundBottomLeft(30);
        roundPanel1.setRoundBottomRight(30);
        roundPanel1.setRoundTopLeft(30);
        roundPanel1.setRoundTopRight(30);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Profile");

        imagePicture.setImage(new javax.swing.ImageIcon(getClass().getResource("/library/image/jw.jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Library Name*");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        libraryName.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        libraryName.setForeground(new java.awt.Color(102, 102, 102));
        libraryName.setText("Library Sa Gwapo");
        libraryName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("BirthDate*");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        birthDate.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        birthDate.setForeground(new java.awt.Color(102, 102, 102));
        birthDate.setText("Library Sa Gwapo");
        birthDate.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Email*");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        email.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        email.setForeground(new java.awt.Color(102, 102, 102));
        email.setText("Library Sa Gwapo");
        email.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Password*");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("************");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        userId.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        userId.setForeground(new java.awt.Color(102, 102, 102));
        userId.setText("userId");

        button1.setForeground(new java.awt.Color(102, 102, 102));
        button1.setText("Change Password");
        button1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        passwordField1.setForeground(new java.awt.Color(51, 51, 51));
        passwordField1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        passwordField1.setShadowColor(new java.awt.Color(0, 0, 0));

        passwordField2.setForeground(new java.awt.Color(51, 51, 51));
        passwordField2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        passwordField2.setShadowColor(new java.awt.Color(0, 0, 0));

        passwordField3.setForeground(new java.awt.Color(51, 51, 51));
        passwordField3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        passwordField3.setShadowColor(new java.awt.Color(0, 0, 0));

        p1lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        p1lb.setForeground(new java.awt.Color(51, 51, 51));
        p1lb.setText(" Current Password");

        p2lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        p2lb.setForeground(new java.awt.Color(51, 51, 51));
        p2lb.setText("New Password");

        p3lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        p3lb.setForeground(new java.awt.Color(51, 51, 51));
        p3lb.setText("Confirm New Password");

        changePasswordBtn.setForeground(new java.awt.Color(102, 102, 102));
        changePasswordBtn.setText("Change Password");
        changePasswordBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        changePasswordBtn.setShadowColor(new java.awt.Color(0, 0, 0));
        changePasswordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(userId, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(254, 254, 254)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(birthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(libraryName, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(30, 30, 30)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(p1lb, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changePasswordBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(passwordField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(p2lb, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(p3lb, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imagePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(libraryName, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p1lb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(passwordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(p2lb, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(p3lb, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordField3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(changePasswordBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(166, 166, 166))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(birthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(60, 60, 60))
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(imagePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(userId, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void changePasswordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordBtnActionPerformed
        changePassword();
    }//GEN-LAST:event_changePasswordBtnActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        showPasswordField();
        button1.setVisible(false);
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel birthDate;
    public library.button.Button button1;
    public library.button.Button changePasswordBtn;
    public javax.swing.JLabel email;
    public library.components.ImageAvatar imagePicture;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JLabel libraryName;
    private javax.swing.JLabel p1lb;
    private javax.swing.JLabel p2lb;
    private javax.swing.JLabel p3lb;
    private library.textfield.PasswordField passwordField1;
    private library.textfield.PasswordField passwordField2;
    private library.textfield.PasswordField passwordField3;
    private library.components.RoundPanel roundPanel1;
    public javax.swing.JLabel userId;
    // End of variables declaration//GEN-END:variables
}
