/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package library.components;

import java.text.SimpleDateFormat;
import library.model.ModelNotification;

/**
 *
 * @author USER
 */
public class NotificationItem extends javax.swing.JPanel {

  
    public ModelNotification getData() {
        return data;
    }

   
    public void setData(ModelNotification data) {
        this.data = data;
        name.setText(data.getName());
        lastname.setText(data.getLastname());
        title.setText(data.getBooktitle());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  // Adjust the format as needed
        String formattedDate = dateFormat.format(data.getReturnDate());
        returnDate.setText(formattedDate);
        
    }

   
    public NotificationItem() {
        initComponents();
    }

    private ModelNotification data;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        name = new javax.swing.JLabel();
        lastname = new javax.swing.JLabel();
        returnDate = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(251, 247, 247));

        name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        name.setForeground(new java.awt.Color(51, 51, 51));
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("Name");
        name.setToolTipText("");
        name.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lastname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lastname.setForeground(new java.awt.Color(51, 51, 51));
        lastname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname.setText("LastName");
        lastname.setToolTipText("");
        lastname.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        returnDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        returnDate.setForeground(new java.awt.Color(51, 51, 51));
        returnDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        returnDate.setText("ReturnDate");
        returnDate.setToolTipText("");
        returnDate.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        title.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        title.setForeground(new java.awt.Color(51, 51, 51));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("BorrowedBook");
        title.setToolTipText("");
        title.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Info");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/image/icons8_info_35px_1.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(102, 202, 55));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(returnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(returnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lastname;
    private javax.swing.JLabel name;
    private javax.swing.JLabel returnDate;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
