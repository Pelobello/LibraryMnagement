/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package library.forms;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import library.components.BookItem;
import library.components.RenterItem;
import library.controller.BookReturnedController;
import library.controller.PopulateRenterController;
import library.event.EventRenter;
import library.formsPopUp.RenterReceipt;
import library.main.Main;
import library.model.ModelRenter;
import raven.glasspanepopup.GlassPanePopup;


public class RenterData extends javax.swing.JPanel {

   
    public EventRenter getEvent() {
        return event;
    }

    public void setEvent(EventRenter event) {
        this.event = event;
    }
    
    public void addRenter(ModelRenter data){
        RenterItem renter = new RenterItem();
        renter.setData(data);
        renter.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
         if (SwingUtilities.isLeftMouseButton(e)) {
                    event.itemClick(renter, data);
                }               
                super.mousePressed(e);

            }
  
        });
        panelItem1.add(renter);
        repaint();
        revalidate();
         
        String inputString1 = renter.returnDate.getText(); 
        String inputString2 = renter.dateToday.getText();

        try {
            LocalDateTime date1 = LocalDate.parse(inputString1, dtf).atStartOfDay();
            LocalDateTime date2 = LocalDate.parse(inputString2, dtf).atStartOfDay();
            long daysBetween = Duration.between(date1, date2).toDays();
            String db = Integer.toString((int) daysBetween);

            double totalPrice = daysBetween * Double.parseDouble(renter.price.getText()) * Double.parseDouble(renter.quantity.getText()); // Assuming price is an object with the getText() method
            int totalDataAmount = (int) totalPrice; // Assuming totalamount is an object with the settext() method
            renter.lapses.setText(db);
            renter.penalties.setText(String.valueOf(totalDataAmount));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
      public void setSelected(Component item){
        for (Component com : panelItem1.getComponents()) {
            RenterItem i =(RenterItem)com;
            if (i.isSelected()) {
                i.setSelected(false);
            }
            ((RenterItem) item).setSelected(true);
        }
        
    }
      public void showItemData(ModelRenter data){
          String q = Integer.toString(data.getQuantity());
          String bp = Integer.toString(data.getBookPrice());
          String tp = Integer.toString(data.getTotalPaidl());
//          SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
          String dt = df.format(data.getDateRented());
          String rd = df.format(data.getReturnDate());
          lbCTR.setText(data.getCtr());
          lbFname.setText(data.getFirstName());
          lbLname.setText(data.getLastName());
          rentedBook.setText(data.getBookTitle());
          quantity.setText(q);
          price.setText(bp);
          lbTotalP.setText(tp);
          lbDateRented.setText(dt);
          lbReturnDate.setText(rd);
          
      }
      private  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      private  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public void dateCalculator(){
      String d1 =  lbReturnDate.getText();
      String d2 =  DT.getText();
        
        try {
           LocalDateTime date1 = LocalDate.parse(d1, dtf).atStartOfDay();
            LocalDateTime date2 = LocalDate.parse(d2, dtf).atStartOfDay();
            long daysBetween = Duration.between(date1, date2).toDays();
            String db = Integer.toString((int) daysBetween); 
            double totalPenalties = daysBetween * Double.parseDouble(price.getText()) * Double.parseDouble(quantity.getText());
            int overall = (int)totalPenalties;
            
            DL.setText(db);
            TP.setText(String.valueOf(overall));
 
        } catch (Exception e) {
        }
        
    }
    public void textVisibleTrue(){
        lbCTR.setVisible(true);
        lbFname.setVisible(true);
        lbLname.setVisible(true);
        rentedBook.setVisible(true);
        quantity.setVisible(true);
        price.setVisible(true);
        lbTotalP.setVisible(true);
        lbDateRented.setVisible(true);
        lbReturnDate.setVisible(true);
        DT.setVisible(true);
        DL.setVisible(true);
        TP.setVisible(true);
    }
    public void textVisibleFalse(){
        lbCTR.setVisible(false);
        lbFname.setVisible(false);
        lbLname.setVisible(false);
        rentedBook.setVisible(false);
        quantity.setVisible(false);
        price.setVisible(false);
        lbTotalP.setVisible(false);
        lbDateRented.setVisible(false);
        lbReturnDate.setVisible(false);
        DT.setVisible(false);
        DL.setVisible(false);
        TP.setVisible(false);
    }
    private void textRemover(){
         lbCTR.setText("");
        lbFname.setText("");
        lbLname.setText("");
        rentedBook.setText("");
        quantity.setText("");
        price.setText("");
        lbTotalP.setText("");
        lbDateRented.setText("");
        lbReturnDate.setText("");
        DT.setText("");
        DL.setText("");
        TP.setText("");
        cash.setText("");
        change.setText("");
    }
    public void refreshRenter(){
        panelItem1.removeAll();
        panelItem1.repaint();
        panelItem1.revalidate();
        populateData.populateData(ID.getText());
        
    }
    
    public void returnRentedBooks(){
        double changeToString = Double.parseDouble(change.getText());
        if (lbCTR.getText().equals("") || rentedBook.getText().equals("") ||quantity.getText().equals("")||cash.getText().equals("")||change.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "invalid Data!");
        }else if (changeToString < 0){
            JOptionPane.showMessageDialog(this, "Insufficient Amount!");
        }
        else{
        int priceData = Integer.parseInt(quantity.getText());
        returnBook.returnBook(rentedBook.getText(), priceData);
        returnBook.deleteBookRented(lbCTR.getText());
        JOptionPane.showMessageDialog(this, "The Book Returned Succesfully...");
         receiptData();
        GlassPanePopup.showPopup(renterReceipt);
        textRemover();
        refreshRenter();
        }
       
    }
    public void receiptData(){
        renterReceipt.textReceipt.setFont(new Font("Courier New", Font.BOLD, 18));
        renterReceipt.textReceipt.setText("--------------------------------------------------------\n");
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "Lending Activities", ""));
        renterReceipt.textReceipt.append("--------------------------------------------------------\n");
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "CTR", lbCTR.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "NAME", lbFname.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "LASTNAME", lbLname.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "BOOKTITLE", rentedBook.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "QUANTITY", quantity.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "PRICE", price.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "TOTALPAID", lbTotalP.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "DATERENTED", lbDateRented.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "RETURNDATE", lbReturnDate.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "DATETODAY", DT.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "DAYLAPSES", DL.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "TOTALPENALTIES", TP.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "CASH", cash.getText()));
        renterReceipt.textReceipt.append(String.format("%-20s | %s\n", "CHANGE", change.getText()));
        renterReceipt.textReceipt.append("--------------------------------------------------------\n");
    }
    
    private void calculateChange() {
    String tpText = TP.getText();
    String cashText = cash.getText();

    if (!tpText.isEmpty() && !cashText.isEmpty()) {
        try {
            double newTp = Double.parseDouble(tpText);
            double newCash = Double.parseDouble(cashText);
            double totalChange = newCash - newTp;

            change.setText(String.valueOf(totalChange));
        } catch (NumberFormatException e) {
           
            e.printStackTrace(); 
        }
    } else {
       
    }
}
     

    private PopulateRenterController populateData;
    private BookReturnedController returnBook;
    private RenterReceipt renterReceipt;
    
    public RenterData() {
        initComponents();
        setOpaque(false);
        jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane2.setBorder(null);
        jScrollPane3.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane3.setBorder(null);
        returnBook = new BookReturnedController();
        populateData = new PopulateRenterController(this);
        renterReceipt = new RenterReceipt();
        Date Tdate = new Date();
       textVisibleFalse();
        String fd = df.format(Tdate);
        DT.setText(fd);
   
    }

  private EventRenter event;
  
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        panelItem1 = new library.swing.PanelItem();
        jScrollPane3 = new javax.swing.JScrollPane();
        roundPanel2 = new library.components.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        lbCTR = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbFname = new javax.swing.JLabel();
        lbLname = new javax.swing.JLabel();
        rentedBook = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        quantity = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbTotalP = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbDateRented = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbReturnDate = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        TP = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        DT = new javax.swing.JLabel();
        DL = new javax.swing.JLabel();
        cash = new library.textfield.TextField();
        change = new library.textfield.TextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        button1 = new library.button.Button();
        ID = new javax.swing.JLabel();

        jScrollPane2.setViewportView(panelItem1);

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("CTR :");

        lbCTR.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("First Name :");

        lbFname.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lbLname.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        rentedBook.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Book Title :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Quantity :");

        quantity.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Price :");

        price.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Total Paid :");

        lbTotalP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Date Rented :");

        lbDateRented.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Return Date :");

        lbReturnDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setText("Date Today :");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setText("Day Lapses :");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("Total Penalties :");

        TP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("LastName :");

        DT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        DL.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cashKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cashKeyTyped(evt);
            }
        });

        change.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                changeKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Cash*");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Change*");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(42, 42, 42)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbFname, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                        .addComponent(lbLname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbCTR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbDateRented, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbTotalP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(quantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rentedBook, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                        .addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbReturnDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel2Layout.createSequentialGroup()
                                .addComponent(change, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(3, 3, 3)))
                .addGap(46, 46, 46))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCTR, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFname, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLname, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rentedBook, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTotalP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDateRented, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cash, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(change, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jScrollPane3.setViewportView(roundPanel2);

        button1.setText("Book Return");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        ID.setText("id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(68, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
//       returnRentedBooks();
           if (lbCTR.getText().equals("") || rentedBook.getText().equals("") ||quantity.getText().equals("")||cash.getText().equals("")||change.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "invalid Data!");
        }else{
               
        returnRentedBooks();
           }
        
       
      
    }//GEN-LAST:event_button1ActionPerformed

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
    private javax.swing.JLabel DL;
    private javax.swing.JLabel DT;
    public javax.swing.JLabel ID;
    private javax.swing.JLabel TP;
    private library.button.Button button1;
    private library.textfield.TextField cash;
    private library.textfield.TextField change;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbCTR;
    private javax.swing.JLabel lbDateRented;
    private javax.swing.JLabel lbFname;
    private javax.swing.JLabel lbLname;
    private javax.swing.JLabel lbReturnDate;
    private javax.swing.JLabel lbTotalP;
    public library.swing.PanelItem panelItem1;
    private javax.swing.JLabel price;
    private javax.swing.JLabel quantity;
    private javax.swing.JLabel rentedBook;
    private library.components.RoundPanel roundPanel2;
    // End of variables declaration//GEN-END:variables
}
