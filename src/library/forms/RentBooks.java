
package library.forms;

import com.raven.datechooser.DateChooser;
import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import library.controller.RentBooksController;
import library.controller.UpdateBooksController;
import library.model.ModelRentData;


public class RentBooks extends javax.swing.JPanel {
    

private DateChooser dateChooser = new DateChooser();
private DateChooser dateChooserReturn = new DateChooser();
private RentBooksController rentBooksControl;
private UpdateBooksController updateBookQuantity;
    public RentBooks() {
        initComponents();
        setOpaque(false);
          dateChooser.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
          dateChooserReturn.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        dateChooser.setTextField(date);
        dateChooserReturn.setTextField(returnDate);
        updateBookQuantity = new UpdateBooksController();
       
    }
    public void showBookData(ModelRentData data){
        String newQuantity = Integer.toString(data.getQuantity());
        bTitle.setText(data.getBookTitle());
        bookSQuantity.setText(newQuantity);
    }
   public void addRentedBooksData() throws ParseException, SQLException, IOException, ClassNotFoundException{
       
       int toIntBookQuantity = Integer.parseInt(bookSQuantity.getText());
      int toIntQuantity = Integer.parseInt(quantity.getText());
      
      int totalDataQuantity = toIntBookQuantity - toIntQuantity;
      String toString_totalData = Integer.toString(totalDataQuantity);
      updatedQuantity.removeAll();
      updatedQuantity.repaint();
      updatedQuantity.revalidate();
      updatedQuantity.setText(toString_totalData);
      updateBookQuantity.UpdateBooksQuantity(userId.getText(), bTitle.getText(), updatedQuantity.getText());
      
       String returnDateData = returnDate.getText();    
       String dateData = date.getText();
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dddd");
       Date convertDate = dateFormat.parse(dateData);
       Date ConvertReturnDate = dateFormat.parse(returnDateData);
       
       String totalData = totalAmount.getText();
       String totalQuantity = quantity.getText();
       double convertTotalAmount = Double.parseDouble(totalData);
       int convertTotalQuantity = Integer.parseInt(totalQuantity);
       rentBooksControl = new RentBooksController(userId.getText(), bTitle.getText(), fName.getText(), lName.getText(), convertDate, ConvertReturnDate, convertTotalAmount, convertTotalQuantity);
       
       rentBooksControl.rentBooksToDatabase();
       rentBooksControl.rentBooksToDatabaseV2();
       JOptionPane.showMessageDialog(this, "Rent Success");
   }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new library.components.RoundPanel();
        userId = new javax.swing.JLabel();
        lName = new library.textfield.TextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fName = new library.textfield.TextField();
        jLabel4 = new javax.swing.JLabel();
        date = new library.textfield.TextField();
        jLabel5 = new javax.swing.JLabel();
        returnDate = new library.textfield.TextField();
        jLabel9 = new javax.swing.JLabel();
        bTitle = new library.textfield.TextField();
        jLabel10 = new javax.swing.JLabel();
        bookSQuantity = new library.textfield.TextField();
        jLabel11 = new javax.swing.JLabel();
        quantity = new library.textfield.TextField();
        jLabel12 = new javax.swing.JLabel();
        totalAmount = new library.textfield.TextField();
        button1 = new library.button.Button();
        updatedQuantity = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setRoundBottomLeft(40);
        roundPanel1.setRoundBottomRight(40);
        roundPanel1.setRoundTopLeft(40);
        roundPanel1.setRoundTopRight(40);

        userId.setText("132");

        lName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lNameActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Last Name*");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("First Name*");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        fName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fNameActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(102, 102, 102));
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Date*");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        date.setEditable(false);
        date.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel5.setBackground(new java.awt.Color(102, 102, 102));
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Return Date*");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        returnDate.setEditable(false);
        returnDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel9.setBackground(new java.awt.Color(102, 102, 102));
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Book Title*");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        bTitle.setEditable(false);
        bTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel10.setBackground(new java.awt.Color(102, 102, 102));
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Book Supply Quantity*");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        bookSQuantity.setEditable(false);
        bookSQuantity.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel11.setBackground(new java.awt.Color(102, 102, 102));
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Quantity*");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        quantity.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel12.setBackground(new java.awt.Color(102, 102, 102));
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Total Amount*");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        totalAmount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        button1.setText("RENT");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        updatedQuantity.setText("0");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updatedQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(userId, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(101, 101, 101))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(50, 50, 50))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(164, 164, 164)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(quantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(9, 9, 9)))))
                                .addGap(42, 42, 42)))
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(returnDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bookSQuantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(totalAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGap(4, 4, 4))
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(8, 8, 8)))))
                                .addGap(182, 182, 182))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(190, 190, 190)))))
                .addContainerGap())
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(382, 382, 382)
                .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addGap(404, 404, 404))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lName, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(returnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bookSQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
    try {
        addRentedBooksData();
    } catch (ParseException ex) {
        Logger.getLogger(RentBooks.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(RentBooks.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(RentBooks.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(RentBooks.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_button1ActionPerformed

    private void lNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lNameActionPerformed
    
    }//GEN-LAST:event_lNameActionPerformed

    private void fNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fNameActionPerformed
       
    }//GEN-LAST:event_fNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private library.textfield.TextField bTitle;
    private library.textfield.TextField bookSQuantity;
    private library.button.Button button1;
    private library.textfield.TextField date;
    private library.textfield.TextField fName;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private library.textfield.TextField lName;
    private library.textfield.TextField quantity;
    private library.textfield.TextField returnDate;
    private library.components.RoundPanel roundPanel1;
    private library.textfield.TextField totalAmount;
    private javax.swing.JLabel updatedQuantity;
    public javax.swing.JLabel userId;
    // End of variables declaration//GEN-END:variables
}
