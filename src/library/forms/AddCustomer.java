
package library.forms;

import com.raven.datechooser.DateChooser;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import library.components.CustomerDataItem;
import library.controller.AddCustomerController;
import library.database.DatabaseConnection;
import java.sql.ResultSet;
import java.util.UUID;
import java.util.Vector;
import javax.swing.table.TableModel;
import library.controller.Update_Delete_CustomerData;
import static library.forms.RentBooks.generateCTR;

public class AddCustomer extends javax.swing.JPanel {

private AddCustomerController addCostomerControll;
private DefaultTableCellRenderer centerRendererV2;
private DateChooser dateChooser = new DateChooser();
private Update_Delete_CustomerData ud_customerData;
    public AddCustomer() {
        initComponents();
        setOpaque(false);
        ud_customerData = new Update_Delete_CustomerData();
        addCostomerControll = new AddCustomerController();
        dateChooser.setTextField(bDate);
        dateChooser.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        bDate.setEditable(false);
        centerRendererV2 = new DefaultTableCellRenderer();
        tableTextCenter();
        populateCustomerData(userId.getText());
        lbID.setVisible(false);
        CtID.setVisible(false);
        userId.setVisible(false);
 
    }
    
      private void tableTextCenter(){
             centerRendererV2.setHorizontalAlignment(SwingConstants.CENTER);
              for (int i = 0; i < customerTable.getColumnCount(); i++) {
            customerTable.getColumnModel().getColumn(i).setCellRenderer(centerRendererV2);
        }
      }
   private void setTextToNone(){
       lName.setText("");
       fName.setText("");
       age.setText("");
       contactNo.setText("");
       street.setText("");
       province.setText("");
       city.setText("");
       barangay.setText("");
       postalCode.setText("");
       lbID.setText("");
   
   }
   private void JtableValues(){
       int i = customerTable.getSelectedRow();
       TableModel model = customerTable.getModel();
       lbID.setText(model.getValueAt(i, 0).toString());
       CtID.setText(model.getValueAt(i, 2).toString());
       lName.setText(model.getValueAt(i, 3).toString());
       fName.setText(model.getValueAt(i, 4).toString());
       bDate.setText(model.getValueAt(i, 5).toString());
       age.setText(model.getValueAt(i, 6).toString());
       contactNo.setText(model.getValueAt(i, 7).toString());
       province.setText(model.getValueAt(i, 8).toString());
       city.setText(model.getValueAt(i, 9).toString());
       barangay.setText(model.getValueAt(i, 10).toString());
       street.setText(model.getValueAt(i, 11).toString());
       postalCode.setText(model.getValueAt(i, 12).toString());
       
   }
  private void deleteCustomerData() {
    if (lbID.getText().equals("")) {
        JOptionPane.showMessageDialog(this, "Invalid Data to Delete");
    } else {
        int idData = Integer.parseInt(lbID.getText());
        if (addCostomerControll != null) {
            addCostomerControll.setIdNumber(idData);
            ud_customerData.DeleteCustomerData(addCostomerControll);

            populateCustomerData(userId.getText());
            String generateCtr = generateCTR();
            CtID.setText(generateCtr);
            JOptionPane.showMessageDialog(this, "Successfully Deleted");
            setTextToNone();
        } else {
            JOptionPane.showMessageDialog(this, "Error: addCostomerControll is null");
        }
    }
}
  private void updateCustomerData(){
      if (lbID.getText().equals("")) {
        JOptionPane.showMessageDialog(this, "Invalid Data to Update");
    }else {
        int idData = Integer.parseInt(lbID.getText());
        if (addCostomerControll != null) {
            addCostomerControll.setIdNumber(idData);
            addCostomerControll.setLastName(lName.getText());
            addCostomerControll.setFirstName(fName.getText());
            addCostomerControll.setBirthDate(bDate.getText());
            addCostomerControll.setAge(age.getText());
            addCostomerControll.setProvince(province.getText());
            addCostomerControll.setCity(city.getText());
            addCostomerControll.setBarangay(barangay.getText());
            addCostomerControll.setStreet(street.getText());
            addCostomerControll.setContactNumber(contactNo.getText());
            addCostomerControll.setPostCode(postalCode.getText());
            
            ud_customerData.UpdateCustomerData(addCostomerControll);

            populateCustomerData(userId.getText());
            String generateCtr = generateCTR();
            CtID.setText(generateCtr);
            JOptionPane.showMessageDialog(this, "Successfully Updated");
            setTextToNone();
        } else {
            JOptionPane.showMessageDialog(this, "Error: addCostomerControll is null");
        }
    }
  }
   public void populateCustomerData(String UserId){
       try {
           DefaultTableModel model = (DefaultTableModel)customerTable.getModel();
           model.setRowCount(0);
           String sql = "SELECT * FROM custumer_data WHERE cUID = ?";
           PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
           p.setString(1, UserId);
           ResultSet rs = p.executeQuery();
           while (rs.next()) {               
               Vector v = new Vector();
               for (int i = 0; i < 35; i++) {
                   v.add(rs.getInt("id"));
                   v.add(rs.getString("cUID"));
                   v.add(rs.getString("customerUserId"));
                   v.add(rs.getString("lastName"));
                   v.add(rs.getString("firstName"));
                   v.add(rs.getString("birthDate"));
                   v.add(rs.getString("age"));
                   v.add(rs.getString("contactNumber"));
                   v.add(rs.getString("province"));
                   v.add(rs.getString("city"));
                   v.add(rs.getString("barangay"));
                   v.add(rs.getString("country"));
                   v.add(rs.getString("postalCode"));
  
               }
               model.addRow(v);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   public void searchCustomerData(String userID, String searchField){
       try {
           DefaultTableModel model =(DefaultTableModel)customerTable.getModel();
           model.setRowCount(0);
           String sql = "SELECT * FROM custumer_data WHERE cUID = ? AND (customerUserId LIKE ? OR lastName LIKE ?) ORDER BY cUID";
           PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
           p.setString(1, userID);
            p.setString(2, "%"+searchField.trim()+"%");
             p.setString(3, "%"+searchField.trim()+"%");
             ResultSet rs = p.executeQuery();
           while (rs.next()) {               
               Vector v = new Vector();
               for (int i = 0; i < 35; i++) {
                   v.add(rs.getInt("id"));
                   v.add(rs.getString("cUID"));
                   v.add(rs.getString("customerUserId"));
                   v.add(rs.getString("lastName"));
                   v.add(rs.getString("firstName"));
                   v.add(rs.getString("birthDate"));
                   v.add(rs.getString("age"));
                   v.add(rs.getString("contactNumber"));
                   v.add(rs.getString("province"));
                   v.add(rs.getString("city"));
                   v.add(rs.getString("barangay"));
                   v.add(rs.getString("country"));
                   v.add(rs.getString("postalCode"));
  
               }
               model.addRow(v);
           }
  
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
     public static String generateCTR() {
        
        UUID uuid = UUID.randomUUID();

        String userId = uuid.toString().replace("-", "").substring(0, 6);

        return userId;
    }
    public void addCustomerData() throws SQLException, IOException{
        try {
           addCostomerControll = new AddCustomerController(userId.getText(),CtID.getText(),lName.getText(), fName.getText(), bDate.getText(), age.getText(), 
                                                contactNo.getText(), street.getText(),
                                            province.getText(), city.getText(), barangay.getText(), postalCode.getText());
            addCostomerControll.AddCustomerToDatabase();
            
            JOptionPane.showMessageDialog(this, "Customer Added!");
             String generateCtr = generateCTR();
            CtID.setText(generateCtr);
            populateCustomerData(userId.getText());
            setTextToNone();
            
        } catch (ClassNotFoundException ex) {
        try {
            DatabaseConnection.getInstance().getConnection().rollback();
        } catch (SQLException rollbackException) {
            rollbackException.printStackTrace();
        }
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } 
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new library.components.RoundPanel();
        lName = new library.textfield.TextField();
        lbLastName = new javax.swing.JLabel();
        lbfName = new javax.swing.JLabel();
        fName = new library.textfield.TextField();
        bDate = new library.textfield.TextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        age = new library.textfield.TextField();
        jLabel5 = new javax.swing.JLabel();
        contactNo = new library.textfield.TextField();
        jLabel7 = new javax.swing.JLabel();
        province = new library.textfield.TextField();
        city = new library.textfield.TextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        barangay = new library.textfield.TextField();
        jLabel10 = new javax.swing.JLabel();
        postalCode = new library.textfield.TextField();
        button2 = new library.button.Button();
        jLabel6 = new javax.swing.JLabel();
        street = new library.textfield.TextField();
        userId = new javax.swing.JLabel();
        CtID = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        lbID = new javax.swing.JLabel();
        button3 = new library.button.Button();
        button4 = new library.button.Button();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setRoundBottomLeft(60);
        roundPanel1.setRoundBottomRight(60);
        roundPanel1.setRoundTopLeft(60);
        roundPanel1.setRoundTopRight(60);

        lName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lName.setShadowColor(new java.awt.Color(0, 0, 0));

        lbLastName.setBackground(new java.awt.Color(204, 204, 204));
        lbLastName.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        lbLastName.setForeground(new java.awt.Color(102, 102, 102));
        lbLastName.setText("Last Name*");
        lbLastName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lbfName.setBackground(new java.awt.Color(204, 204, 204));
        lbfName.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        lbfName.setForeground(new java.awt.Color(102, 102, 102));
        lbfName.setText("First Name*");
        lbfName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        fName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fName.setShadowColor(new java.awt.Color(0, 0, 0));

        bDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bDate.setShadowColor(new java.awt.Color(0, 0, 0));

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Birth Date*");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Age*");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        age.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        age.setShadowColor(new java.awt.Color(0, 0, 0));
        age.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ageKeyTyped(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Contact No*");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        contactNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        contactNo.setShadowColor(new java.awt.Color(0, 0, 0));
        contactNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contactNoKeyTyped(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Province*");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        province.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        province.setShadowColor(new java.awt.Color(0, 0, 0));

        city.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        city.setShadowColor(new java.awt.Color(0, 0, 0));
        city.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("City*");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel9.setBackground(new java.awt.Color(204, 204, 204));
        jLabel9.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Barangay*");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        barangay.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        barangay.setShadowColor(new java.awt.Color(0, 0, 0));

        jLabel10.setBackground(new java.awt.Color(204, 204, 204));
        jLabel10.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Postal Code*");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        postalCode.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        postalCode.setShadowColor(new java.awt.Color(0, 0, 0));
        postalCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                postalCodeKeyTyped(evt);
            }
        });

        button2.setText("ADD");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Street*");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        street.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        street.setShadowColor(new java.awt.Color(0, 0, 0));

        userId.setText("userId");

        CtID.setText("CustomerID");

        customerTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        customerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "UserId", "CustomerID", "LastName", "Name", "BirthDate", "Age", "Contact No.", "Province", "City", "Barangay", "Street", "Postal Code"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customerTable.setRowHeight(50);
        customerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(customerTable);
        if (customerTable.getColumnModel().getColumnCount() > 0) {
            customerTable.getColumnModel().getColumn(0).setMinWidth(0);
            customerTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            customerTable.getColumnModel().getColumn(0).setMaxWidth(0);
            customerTable.getColumnModel().getColumn(1).setMinWidth(0);
            customerTable.getColumnModel().getColumn(1).setPreferredWidth(0);
            customerTable.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        button3.setText("UPDATE");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setText("DELETE");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbfName, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                    .addComponent(lbLastName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)))
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                        .addGap(1, 1, 1))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(11, 11, 11))
                            .addComponent(bDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(age, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(province, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(city, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(barangay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(street, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)))))
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(postalCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(contactNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(userId, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CtID, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(province, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(street, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(postalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(bDate, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(lbLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(lName, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbfName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(contactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(barangay, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(59, 59, 59)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userId, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CtID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
    try {
         addCostomerControll.setUserId(userId.getText());
         addCostomerControll.setLastName(lName.getText());
         addCostomerControll.setFirstName(fName.getText());
        if (ud_customerData.alreadyExistingCustomer(addCostomerControll)) {
            JOptionPane.showMessageDialog(this, "Customer Already Exist");
        }else{
             addCustomerData();
        }
       
    } catch (SQLException ex) {
        Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
    }     
    }//GEN-LAST:event_button2ActionPerformed

    private void cityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityActionPerformed
      
    }//GEN-LAST:event_cityActionPerformed

    private void ageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ageKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_ageKeyTyped

    private void contactNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contactNoKeyTyped
       char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_contactNoKeyTyped

    private void postalCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_postalCodeKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_postalCodeKeyTyped

    private void customerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerTableMouseClicked
       JtableValues();
    }//GEN-LAST:event_customerTableMouseClicked

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
      updateCustomerData();
    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        deleteCustomerData();
    }//GEN-LAST:event_button4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel CtID;
    private library.textfield.TextField age;
    private library.textfield.TextField bDate;
    private library.textfield.TextField barangay;
    private library.button.Button button2;
    private library.button.Button button3;
    private library.button.Button button4;
    private library.textfield.TextField city;
    private library.textfield.TextField contactNo;
    public javax.swing.JTable customerTable;
    private library.textfield.TextField fName;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private library.textfield.TextField lName;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbLastName;
    private javax.swing.JLabel lbfName;
    private library.textfield.TextField postalCode;
    private library.textfield.TextField province;
    private library.components.RoundPanel roundPanel1;
    private library.textfield.TextField street;
    public javax.swing.JLabel userId;
    // End of variables declaration//GEN-END:variables
}
