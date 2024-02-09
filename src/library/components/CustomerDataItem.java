/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package library.components;

import library.controller.AddCustomerController;


public class CustomerDataItem extends javax.swing.JPanel {

    
   

    private AddCustomerController data;
    public CustomerDataItem() {
        initComponents();
    }

     public AddCustomerController getData() {
        return data;
    }

    public void setData(AddCustomerController data) {
        this.data = data;
        lName.setText(data.getLastName());
        fName.setText(data.getFirstName());
        bDate.setText(data.getBirthDate());
        age.setText(data.getAge());
        contactNo.setText(data.getContactNumber());
        country.setText(data.getCity());
        province.setText(data.getProvince());
        city.setText(data.getCity());
        postalCode.setText(data.getPostCode());
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lName = new javax.swing.JLabel();
        fName = new javax.swing.JLabel();
        age = new javax.swing.JLabel();
        bDate = new javax.swing.JLabel();
        contactNo = new javax.swing.JLabel();
        country = new javax.swing.JLabel();
        province = new javax.swing.JLabel();
        city = new javax.swing.JLabel();
        barangay = new javax.swing.JLabel();
        postalCode = new javax.swing.JLabel();

        lName.setText("jLabel1");

        fName.setText("jLabel1");

        age.setText("jLabel1");

        bDate.setText("jLabel1");

        contactNo.setText("jLabel1");

        country.setText("jLabel1");

        province.setText("jLabel1");

        city.setText("jLabel1");

        barangay.setText("jLabel1");

        postalCode.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lName, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bDate, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(155, 155, 155)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(province, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barangay, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(postalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(276, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(province, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(barangay, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(postalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lName, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(bDate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(contactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel age;
    private javax.swing.JLabel bDate;
    private javax.swing.JLabel barangay;
    private javax.swing.JLabel city;
    private javax.swing.JLabel contactNo;
    private javax.swing.JLabel country;
    private javax.swing.JLabel fName;
    private javax.swing.JLabel lName;
    private javax.swing.JLabel postalCode;
    private javax.swing.JLabel province;
    // End of variables declaration//GEN-END:variables
}
