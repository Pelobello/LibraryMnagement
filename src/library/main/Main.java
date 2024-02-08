
package library.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import library.controller.AddBooksController;
import library.controller.PopulateBooksController;
import library.database.DatabaseConnection;
import library.event.EventItem;
import library.forms.AddBooks;
import library.forms.DashBoard;
import library.forms.MyLibrary;
import library.forms.AddCustomer;
import library.forms.RentBooks;
import library.model.ModelItem;
import library.model.ModelRentData;



public class Main extends javax.swing.JFrame {

      private DashBoard discover;
      private MyLibrary myLibrary;
      private AddBooks addBooks;
      private AddCustomer setting;
      private RentBooks rentBooks;
      private ModelRentData modelRentData;
      private AddBooksController addBooksController ;
      private PopulateBooksController populateBooks;
    public Main()  {
      
    initComponents();
        discover = new DashBoard();
        myLibrary = new MyLibrary();
        addBooks = new AddBooks();
        setting = new AddCustomer();
        rentBooks = new RentBooks();
        
        
         initMoving(this);
    
         testData();
  initMainComponents();
        
         
    }
    public void initMainComponents(){
        setBackground(new Color(0,0,0,0));
        Font poppinsFont = new Font("Khula", Font.ITALIC, 16);
        bookDescription.setFont(poppinsFont);
        populateBooks = new PopulateBooksController(myLibrary);
        populateBooks.populate(id.getText()); 
        roundPanel4.setLayout(new BorderLayout());
        bookDescription.setBackground(new Color(15,4,76,255));
        roundPanel4.setLayout(new BorderLayout());
        addBooks.userId.setText(id.getText());
        bookQuantity.setVisible(false);
        Forms(discover);
    }
    
    //Populate and refresh data from Books shelf
    public void refreshUI(){
        myLibrary.panelItem1.removeAll();
        myLibrary.panelItem1.repaint();
        myLibrary.panelItem1.revalidate();
        populateBooks.populate(id.getText());
    }

   //ChangePanel Forms
  private void Forms(Component com){
      roundPanel4.removeAll();
      roundPanel4.add(com);
      repaint();
      revalidate();
      
  }
  
  
  
  private int x;
  private int y;

    public void initMoving(JFrame frame){
        panelMoving.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
                x = e.getX();
                y = e.getY();
            }
            
        });
        panelMoving.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
             frame.setLocation(e.getXOnScreen()-x, e.getYOnScreen()-y);
                
                
            }
        });
    }
    public void testData(){
       
  myLibrary.setEvent(new EventItem() {
            @Override
            public void itemClick(Component com, ModelItem item) {
             myLibrary.setSelected(com);
                showItem(item);
            
            }
 
       });
}

   public void showItem(ModelItem data){
       String quantityToString = Integer.toString(data.getQuantity());
       bookTitle.setText(data.getBookTitle());
       bookAuthor.setText(data.getBookAuthor());
       bookDescription.setText(data.getBookDescription());
       bookQuantity.setText(quantityToString);
     
   }
   
   
   
   
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new library.components.RoundPanel();
        roundPanel3 = new library.components.RoundPanel();
        roundPanel4 = new library.components.RoundPanel();
        panelMoving = new library.components.RoundPanel();
        button1 = new library.button.Button();
        button3 = new library.button.Button();
        button4 = new library.button.Button();
        button5 = new library.button.Button();
        id = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        roundPanel5 = new library.components.RoundPanel();
        bookTitle = new javax.swing.JLabel();
        bookAuthor = new javax.swing.JLabel();
        roundPanel2 = new library.components.RoundPanel();
        pictureBox1 = new library.components.PictureBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookDescription = new library.swing.TextPane();
        bookQuantity = new javax.swing.JLabel();
        button2 = new library.button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        roundPanel1.setBackground(new java.awt.Color(249, 246, 247));
        roundPanel1.setRoundBottomLeft(25);
        roundPanel1.setRoundBottomRight(25);
        roundPanel1.setRoundTopLeft(25);
        roundPanel1.setRoundTopRight(25);

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel3.setRoundBottomLeft(20);
        roundPanel3.setRoundBottomRight(25);
        roundPanel3.setRoundTopRight(25);

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1291, Short.MAX_VALUE)
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel4.setRoundBottomLeft(40);
        roundPanel4.setRoundBottomRight(40);
        roundPanel4.setRoundTopLeft(40);
        roundPanel4.setRoundTopRight(40);
        roundPanel4.setLayout(new java.awt.BorderLayout());

        panelMoving.setBackground(new java.awt.Color(255, 255, 255));
        panelMoving.setRoundBottomLeft(25);
        panelMoving.setRoundTopLeft(25);

        button1.setBackground(new java.awt.Color(245, 238, 230));
        button1.setBorder(null);
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/button/icons8_mobile_home_45px.png"))); // NOI18N
        button1.setText("Dashboard");
        button1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        button1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        button1.setIconTextGap(20);
        button1.setRippleColor(new java.awt.Color(255, 102, 153));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button3.setBackground(new java.awt.Color(245, 238, 230));
        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/button/icons8_mobile_home_45px.png"))); // NOI18N
        button3.setText("My Library");
        button3.setFont(new java.awt.Font("Segoe UI", 0, 27)); // NOI18N
        button3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        button3.setIconTextGap(20);
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setBackground(new java.awt.Color(245, 238, 230));
        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/button/icons8_mobile_home_45px.png"))); // NOI18N
        button4.setText("Add Books");
        button4.setFont(new java.awt.Font("Segoe UI", 0, 27)); // NOI18N
        button4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        button4.setIconTextGap(20);
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        button5.setBackground(new java.awt.Color(245, 238, 230));
        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/button/icons8_mobile_home_45px.png"))); // NOI18N
        button5.setText("Add Customers");
        button5.setFont(new java.awt.Font("Segoe UI", 0, 27)); // NOI18N
        button5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        button5.setIconTextGap(20);
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        id.setForeground(new java.awt.Color(102, 102, 102));
        id.setText("142");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("userId:");

        javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
        panelMoving.setLayout(panelMovingLayout);
        panelMovingLayout.setHorizontalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addGroup(panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMovingLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelMovingLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panelMovingLayout.setVerticalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(106, 106, 106)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        roundPanel5.setBackground(new java.awt.Color(15, 4, 76));
        roundPanel5.setRoundBottomRight(25);

        bookTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        bookTitle.setForeground(new java.awt.Color(255, 255, 255));
        bookTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookTitle.setText("Title");

        bookAuthor.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        bookAuthor.setForeground(new java.awt.Color(255, 255, 255));
        bookAuthor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookAuthor.setText("Author");

        roundPanel2.setRoundBottomLeft(20);
        roundPanel2.setRoundBottomRight(20);
        roundPanel2.setRoundTopLeft(20);
        roundPanel2.setRoundTopRight(20);

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );

        bookDescription.setEditable(false);
        bookDescription.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bookDescription.setForeground(new java.awt.Color(255, 255, 255));
        bookDescription.setMargin(new java.awt.Insets(10, 20, 20, 10));
        bookDescription.setPreferredSize(new java.awt.Dimension(25, 25));
        jScrollPane1.setViewportView(bookDescription);

        bookQuantity.setForeground(new java.awt.Color(255, 255, 255));
        bookQuantity.setText("0");

        button2.setText("Rent");
        button2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bookTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bookAuthor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bookQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(bookAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(bookQuantity)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        Forms(discover);
    }//GEN-LAST:event_button1ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        Forms(myLibrary);
        refreshUI();
        

    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        Forms(addBooks);
    }//GEN-LAST:event_button4ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        Forms(setting);
    }//GEN-LAST:event_button5ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        
             Forms(rentBooks);
        int newQuantity = Integer.parseInt(bookQuantity.getText());
        modelRentData = new ModelRentData(bookTitle.getText(), newQuantity);
        rentBooks.showBookData(modelRentData);
    
    }//GEN-LAST:event_button2ActionPerformed

   
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bookAuthor;
    private library.swing.TextPane bookDescription;
    private javax.swing.JLabel bookQuantity;
    private javax.swing.JLabel bookTitle;
    private library.button.Button button1;
    private library.button.Button button2;
    private library.button.Button button3;
    private library.button.Button button4;
    private library.button.Button button5;
    public javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private library.components.RoundPanel panelMoving;
    private library.components.PictureBox pictureBox1;
    private library.components.RoundPanel roundPanel1;
    private library.components.RoundPanel roundPanel2;
    private library.components.RoundPanel roundPanel3;
    private library.components.RoundPanel roundPanel4;
    private library.components.RoundPanel roundPanel5;
    // End of variables declaration//GEN-END:variables
}
