
package library.forms;

import com.raven.datechooser.DateChooser;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import library.controller.RentBooksController;
import library.controller.UpdateBooksController;
import library.formsPopUp.RenterReceipt;
import static library.main.Main.generateCTR;
import library.model.ModelRentData;
import raven.glasspanepopup.GlassPanePopup;

public class RentBooks extends javax.swing.JPanel {

private DateChooser dateChooser = new DateChooser();
private DateChooser dateChooserReturn = new DateChooser();
private RentBooksController rentBooksControl;
private UpdateBooksController updateBookQuantity;
private RenterReceipt renterReceipt;
    public RentBooks() {
        initComponents();
        setOpaque(false);
          dateChooser.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
          dateChooserReturn.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
          dateChooser.setTextField(date);
          renterReceipt = new RenterReceipt();
          dateChooserReturn.setTextField(returnDate);
          updateBookQuantity = new UpdateBooksController();
          userId.setVisible(false);
          updatedQuantity.setVisible(false);
        
    }
    public void showBookData(ModelRentData data){
        String newQuantity = Integer.toString(data.getQuantity());
        String newPrice = Integer.toString(data.getPrice());
        bTitle.setText(data.getBookTitle());
        bookSQuantity.setText(newQuantity);
        price.setText(newPrice);
    }
      public static String generateCTR() {
        
        UUID uuid = UUID.randomUUID();

        String userId = uuid.toString().replace("-", "").substring(0, 6);

        return userId;
    }
  public void CalculateTotal(){
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String inputString1 = date.getText(); 
    String inputString2 = returnDate.getText(); 
    
    try {
        LocalDateTime date1 = LocalDate.parse(inputString1, dtf).atStartOfDay();
        LocalDateTime date2 = LocalDate.parse(inputString2, dtf).atStartOfDay();
        long daysBetween = Duration.between(date1, date2).toDays();
        long newTQuantity = 0;
        if (!quantity.getText().isEmpty()) {
            newTQuantity = Long.parseLong(quantity.getText());
        }
        double totalPrice = daysBetween * Double.parseDouble(price.getText()) * newTQuantity; 
        long totalDataAmount = Math.round(totalPrice); 
        totalAmount.setText(String.valueOf(totalDataAmount));
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}

  public void addRentedBooksData() {
    try {
        int toIntBookQuantity = Integer.parseInt(bookSQuantity.getText());
        int toIntQuantity = Integer.parseInt(quantity.getText());
        double cashChange = Double.parseDouble(change.getText());

        if (bookSQuantity.getText().isEmpty() || quantity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Book Quantity and Quantity.");
            return;
        }

        int totalDataQuantity = toIntBookQuantity - toIntQuantity;
        String toStringTotalData = Integer.toString(totalDataQuantity);

        if (totalDataQuantity < 0) {
            JOptionPane.showMessageDialog(this, "Inefficient Book Supply!");
        } else if (cashChange < 0) {
            JOptionPane.showMessageDialog(this, "Insufficient Amount");
        } else {
            updateBookQuantity(totalDataQuantity);
            processRentData();
            showSuccessMessage();
            resetCounter();
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter valid numeric values.");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void updateBookQuantity(int totalDataQuantity) {
    updatedQuantity.removeAll();
    updatedQuantity.repaint();
    updatedQuantity.revalidate();
    updatedQuantity.setText(Integer.toString(totalDataQuantity));
    updateBookQuantity.UpdateBooksQuantity(userId.getText(), bTitle.getText(), updatedQuantity.getText());
}

private void processRentData() throws ParseException, SQLException, IOException, ClassNotFoundException {
    String returnDateData = returnDate.getText();
    String dateData = date.getText();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date convertDate = dateFormat.parse(dateData);
    Date convertReturnDate = dateFormat.parse(returnDateData);
    
    String totalData = totalAmount.getText();
    String totalQuantity = quantity.getText();

    if (totalData.isEmpty() || totalQuantity.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Total Amount and Quantity.");
        return;
    }

    double convertTotalAmount = Double.parseDouble(totalData);
    int convertTotalQuantity = Integer.parseInt(totalQuantity);
    int bPrice = Integer.parseInt(price.getText());

    rentBooksControl = new RentBooksController(userId.getText(), ctr.getText(), bTitle.getText(),
            fName.getText(), lName.getText(), convertDate, convertReturnDate, convertTotalAmount,
            bPrice, convertTotalQuantity);

    rentBooksControl.rentBooksToDatabase();
    rentBooksControl.rentBooksToDatabaseV2();
    receiptData();
    GlassPanePopup.showPopup(renterReceipt);
    textRemover();
}

private void showSuccessMessage() {
    JOptionPane.showMessageDialog(this, "Rent Success");
}

private void resetCounter() {
    String generateCtr = generateCTR();
    ctr.setText(generateCtr);
}
   private void textRemover(){
       lName.setText("");
       fName.setText("");
       quantity.setText("");
       totalAmount.setText("");
       
   }
   public void receiptData(){
        renterReceipt.textReceipt.setFont(new Font("Courier New", Font.BOLD, 18));
        renterReceipt.textReceipt.setText("--------------------------------------------------------\n");
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "Lending Activities", ""));
        renterReceipt.textReceipt.append("--------------------------------------------------------\n");
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "CTR", ctr.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "NAME", fName.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "LASTNAME", lName.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "BOOKTITLE", bTitle.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "QUANTITY", quantity.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "PRICE", price.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "TOTALPAID", totalAmount.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "DATERENTED", date.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "RETURNDATE", returnDate.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "CASH", cash.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "CHANGE", change.getText()));
        renterReceipt.textReceipt.append("--------------------------------------------------------\n");
    }
   private void calculateChange(){
       String cashAmount = cash.getText();
       String totalAmountMoney = totalAmount.getText();
       if (!cashAmount.isEmpty() && !totalAmountMoney.isEmpty()) {
           try {
               double newCashAmount = Double.parseDouble(cashAmount);
               double newTotalAmount = Double.parseDouble(totalAmountMoney);
               
               double money_Change =  newCashAmount - newTotalAmount ;
               
               change.setText(String.valueOf(money_Change));
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
      
       
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
        price = new library.textfield.TextField();
        ctr = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cash = new library.textfield.TextField();
        change = new library.textfield.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setRoundBottomLeft(40);
        roundPanel1.setRoundBottomRight(40);
        roundPanel1.setRoundTopLeft(40);
        roundPanel1.setRoundTopRight(40);

        userId.setText("132");

        lName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lName.setShadowColor(new java.awt.Color(0, 0, 0));
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
        fName.setShadowColor(new java.awt.Color(0, 0, 0));
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
        date.setShadowColor(new java.awt.Color(0, 0, 0));

        jLabel5.setBackground(new java.awt.Color(102, 102, 102));
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Return Date*");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        returnDate.setEditable(false);
        returnDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        returnDate.setShadowColor(new java.awt.Color(0, 0, 0));
        returnDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                returnDateFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                returnDateFocusLost(evt);
            }
        });
        returnDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnDateActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(102, 102, 102));
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Book Title*");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        bTitle.setEditable(false);
        bTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bTitle.setShadowColor(new java.awt.Color(0, 0, 0));

        jLabel10.setBackground(new java.awt.Color(102, 102, 102));
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Book Supply Quantity*");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        bookSQuantity.setEditable(false);
        bookSQuantity.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bookSQuantity.setShadowColor(new java.awt.Color(0, 0, 0));

        jLabel11.setBackground(new java.awt.Color(102, 102, 102));
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Quantity*");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        quantity.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        quantity.setShadowColor(new java.awt.Color(0, 0, 0));
        quantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                quantityFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                quantityFocusLost(evt);
            }
        });
        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });
        quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quantityKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantityKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quantityKeyTyped(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(102, 102, 102));
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Total Amount*");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        totalAmount.setText("0");
        totalAmount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalAmount.setShadowColor(new java.awt.Color(0, 0, 0));
        totalAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalAmountKeyTyped(evt);
            }
        });

        button1.setText("RENT");
        button1.setShadowColor(new java.awt.Color(0, 0, 0));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        updatedQuantity.setText("0");

        price.setShadowColor(new java.awt.Color(0, 0, 0));

        ctr.setText("jLabel1");

        jLabel13.setBackground(new java.awt.Color(102, 102, 102));
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Price*");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cash.setMinimumSize(new java.awt.Dimension(25, 50));
        cash.setShadowColor(new java.awt.Color(0, 0, 0));
        cash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cashKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cashKeyTyped(evt);
            }
        });

        change.setPreferredSize(new java.awt.Dimension(25, 50));
        change.setShadowColor(new java.awt.Color(0, 0, 0));
        change.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                changeKeyTyped(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText(" Cash*");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText(" Change*");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
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
                                                .addGap(9, 9, 9))
                                            .addComponent(cash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(42, 42, 42)))
                        .addGap(8, 8, 8)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(190, 190, 190))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(returnDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bookSQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(totalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGap(4, 4, 4))
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(8, 8, 8))))
                                    .addComponent(change, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(182, 182, 182))))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ctr, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(updatedQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(userId, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(393, 393, 393)
                .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cash, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(change, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed

        addRentedBooksData();
 
    }//GEN-LAST:event_button1ActionPerformed

    private void lNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lNameActionPerformed
    
    }//GEN-LAST:event_lNameActionPerformed

    private void fNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fNameActionPerformed
       
    }//GEN-LAST:event_fNameActionPerformed

    private void quantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyPressed
  
    }//GEN-LAST:event_quantityKeyPressed

    private void quantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyTyped
          char c = evt.getKeyChar();

    if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
        evt.consume();
        return;
    }

    if (quantity.getText().length() < 5) {
        if (quantity.getText().equals("0") && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
            return;
        }
    } else {
        evt.consume(); 
    }
        
    }//GEN-LAST:event_quantityKeyTyped

    private void totalAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalAmountKeyTyped
       char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_totalAmountKeyTyped

    private void returnDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_returnDateFocusGained
        CalculateTotal();
    }//GEN-LAST:event_returnDateFocusGained

    private void returnDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_returnDateFocusLost
        CalculateTotal();
    
    }//GEN-LAST:event_returnDateFocusLost

    private void returnDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnDateActionPerformed
      
    }//GEN-LAST:event_returnDateActionPerformed

    private void quantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quantityFocusLost
//        quantityCalculator();       
    }//GEN-LAST:event_quantityFocusLost

    private void quantityFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quantityFocusGained
    
    }//GEN-LAST:event_quantityFocusGained

    private void quantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyReleased
       CalculateTotal();
    }//GEN-LAST:event_quantityKeyReleased

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
      
    }//GEN-LAST:event_quantityActionPerformed

    private void cashKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cashKeyTyped
         char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_cashKeyTyped

    private void changeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_changeKeyTyped
         char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_changeKeyTyped

    private void cashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cashKeyReleased
      calculateChange();
    }//GEN-LAST:event_cashKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private library.textfield.TextField bTitle;
    private library.textfield.TextField bookSQuantity;
    private library.button.Button button1;
    private library.textfield.TextField cash;
    private library.textfield.TextField change;
    public javax.swing.JLabel ctr;
    private library.textfield.TextField date;
    private library.textfield.TextField fName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private library.textfield.TextField lName;
    private library.textfield.TextField price;
    private library.textfield.TextField quantity;
    private library.textfield.TextField returnDate;
    private library.components.RoundPanel roundPanel1;
    private library.textfield.TextField totalAmount;
    private javax.swing.JLabel updatedQuantity;
    public javax.swing.JLabel userId;
    // End of variables declaration//GEN-END:variables
}
