
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import library.controller.AddBooksController;
import library.controller.PopulateBooksController;
import library.controller.PopulateDashboardController;
import library.controller.PopulateRenterController;
import library.database.DatabaseConnection;
import library.event.EventItem;
import library.event.EventRenter;
import library.forms.AddBooks;
import library.forms.DashBoard;
import library.forms.MyLibrary;
import library.forms.AddCustomer;
import library.forms.RentBooks;
import library.forms.RenterData;
import static library.main.Main.main;
import library.model.ModelItem;
import library.model.ModelRentData;
import library.model.ModelRenter;

public class Main extends javax.swing.JFrame {

      private DashBoard dashboard;
      private MyLibrary myLibrary;
      private AddBooks addBooks;
      private AddCustomer setting;
      private RentBooks rentBooks;
      private RenterData renterData;
      private ModelRentData modelRentData;
      private AddBooksController addBooksController ;
      private PopulateBooksController populateBooks;
      private PopulateDashboardController populateDashboard;
      private PopulateRenterController populateRenter;
     
    public Main() throws SQLException, ClassNotFoundException, ParseException  {
      
    initComponents();
        dashboard = new DashBoard();
        myLibrary = new MyLibrary();
        addBooks = new AddBooks();
        setting = new AddCustomer();
        rentBooks = new RentBooks();  
        renterData = new RenterData();
        testRenterData();
//      refreshDashboardUI();
//         initMoving(this);  
//
        testData();      
        initMainComponents();
   
    }
    public void initMainComponents() throws SQLException, ClassNotFoundException{
        setBackground(new Color(0,0,0,0));
        Font poppinsFont = new Font("Khula", Font.ITALIC, 16);
        bookDescription.setFont(poppinsFont);
        populateBooks = new PopulateBooksController(myLibrary);
        populateBooks.populate(id.getText()); 
        populateRenter = new PopulateRenterController(renterData);
       populateRenter.populateData(id.getText());
//        refreshDashboardUI();
      
        roundPanel4.setLayout(new BorderLayout());
        search.setText("Search Borrower");
        bookDescription.setBackground(new Color(15,4,76,255));
        roundPanel4.setLayout(new BorderLayout());
        addBooks.userId.setText(id.getText());
        dashboard.dataUID.setText(id.getText());
        
        
       
        bookQuantity.setVisible(false);
        Forms(dashboard);
         if (this.getExtendedState()==Main.MAXIMIZED_BOTH) {
            this.setExtendedState(Main.NORMAL);
        }
        else{
            this.setExtendedState(Main.MAXIMIZED_BOTH);
        }
         textRemover();     
    }
  
    //Populate and refresh data from Books shelf
    public void refreshUI(){
        myLibrary.panelItem1.removeAll();
        myLibrary.panelItem1.repaint();
        myLibrary.panelItem1.revalidate();
        populateBooks.populate(id.getText());        
    }
    public void refreshRenter(){
        renterData.panelItem1.removeAll();
        renterData.panelItem1.repaint();
        renterData.panelItem1.revalidate();
        populateRenter.populateData(id.getText());
        
        
    }
    public void refreshRentBooksUI(){
        rentBooks.userId.removeAll();  
        rentBooks.userId.repaint();
        rentBooks.userId.revalidate();
        rentBooks.userId.setText(id.getText());
    }
    
    public void searchBooks(){
        myLibrary.panelItem1.removeAll();
        myLibrary.panelItem1.repaint();
        myLibrary.panelItem1.revalidate();
        populateBooks.searchBooks(id.getText(), search.getText());
       
    }
    public void searchRenter(){
        dashboard.customerTable.removeAll();
        dashboard.customerTable.repaint();
        dashboard.customerTable.revalidate();
        dashboard.searchRenterData(id.getText(), search.getText());    
    }

    public void refreshDashboardUI() throws SQLException, ClassNotFoundException {      
    dashboard.chart.clear();
    dashboard.chart.start();
    dashboard.testData(id.getText());
    dashboard.dataUID.removeAll();
    dashboard.dataUID.repaint();
    dashboard.dataUID.revalidate();
    dashboard.dataUID.setText(id.getText());
    dashboard.chart.repaint();
    dashboard.chart.revalidate(); 
    dashboard.populateRenterData(id.getText());
    }
      public static String generateCTR() {
        
        UUID uuid = UUID.randomUUID();

        String userId = uuid.toString().replace("-", "").substring(0, 6);

        return userId;
    }

  private void Forms(Component com){
      roundPanel4.removeAll();
      roundPanel4.add(com);
      repaint();
      revalidate();  
  }
  private void textRemover(){
      bookTitle.setText("");
      bookAuthor.setText("");
      bookDescription.setText("");
      pictureImage.setImage(null);
      pictureImage.repaint();
      bookTitle.setVisible(false);
      bookAuthor.setVisible(false);
      bookDescription.setVisible(false);
      rentButton.setVisible(false);
      scrollBookDes.setVisible(false);
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
             scrollBookDes.setVisible(true);
              rentButton.setVisible(true);
               bookTitle.setVisible(true);
                 bookAuthor.setVisible(true);
            bookDescription.setVisible(true);           
            }
       });
}
    public void testRenterData() throws ParseException {
     renterData.setEvent(new EventRenter() {
         @Override
         public void itemClick(Component com, ModelRenter item) {
           renterData.setSelected(com);
         }
     });
//     String dateRented = "2024-02-10";
//        String returnDate = "2024-02-11";
//        String returnDate2 = "2024-02-10";
//
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
//
//       Date rentedDate = df.parse(dateRented);
//        Date dateReturn = df.parse(returnDate);
//        Date dateReturn2 = df.parse(returnDate2);
//      
//     renterData.addRenter(new ModelRenter("31sd2", "Dazzle", "Pelobello", "test", 200, 200, 3, rentedDate, dateReturn));
//       renterData.addRenter(new ModelRenter("31sd2", "Dazzle", "Pelobello", "test", 200, 200, 3, rentedDate, dateReturn2));
    }
   public void showItem(ModelItem data){
       pictureImage.setImage(data.getCoverImage());
       pictureImage.repaint();
       String quantityToString = Integer.toString(data.getQuantity());
        String priceToString = Integer.toString(data.getPrice());
       bookTitle.setText(data.getBookTitle());
       bookAuthor.setText(data.getBookAuthor());
       bookDescription.setText(data.getBookDescription());
       bookQuantity.setText(quantityToString);
       bookPrice.setText(priceToString);
     
   }
 
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new library.components.RoundPanel();
        roundPanel3 = new library.components.RoundPanel();
        jButton1 = new javax.swing.JButton();
        search = new library.textfield.TextField();
        roundPanel4 = new library.components.RoundPanel();
        panelMoving = new library.components.RoundPanel();
        button3 = new library.button.Button();
        button4 = new library.button.Button();
        button5 = new library.button.Button();
        id = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        button6 = new library.button.Button();
        button7 = new library.button.Button();
        roundPanel5 = new library.components.RoundPanel();
        bookTitle = new javax.swing.JLabel();
        bookAuthor = new javax.swing.JLabel();
        scrollBookDes = new javax.swing.JScrollPane();
        bookDescription = new library.swing.TextPane();
        bookQuantity = new javax.swing.JLabel();
        rentButton = new library.button.Button();
        pictureImage = new library.components.PictureBox();
        bookPrice = new javax.swing.JLabel();

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

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("x");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        search.setForeground(new java.awt.Color(51, 51, 51));
        search.setText("Search");
        search.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        search.setShadowColor(new java.awt.Color(252, 209, 209));
        search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFocusLost(evt);
            }
        });
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(697, 697, 697)
                .addComponent(jButton1)
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        button3.setBackground(new java.awt.Color(245, 238, 230));
        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/image/icons8_Books_40px.png"))); // NOI18N
        button3.setText("My Library");
        button3.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        button3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        button3.setIconTextGap(20);
        button3.setShadowColor(new java.awt.Color(245, 163, 202));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setBackground(new java.awt.Color(245, 238, 230));
        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/image/icons8_Add_Book_40px.png"))); // NOI18N
        button4.setText("Add Books");
        button4.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        button4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        button4.setIconTextGap(20);
        button4.setShadowColor(new java.awt.Color(245, 163, 202));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        button5.setBackground(new java.awt.Color(245, 238, 230));
        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/image/icons8_customer_40px.png"))); // NOI18N
        button5.setText("Add Customer");
        button5.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        button5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        button5.setIconTextGap(20);
        button5.setShadowColor(new java.awt.Color(245, 163, 202));
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        id.setForeground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("userId:");

        button6.setBackground(new java.awt.Color(245, 238, 230));
        button6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/image/icons8_combo_chart_40px.png"))); // NOI18N
        button6.setText("Dashboard");
        button6.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        button6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        button6.setIconTextGap(20);
        button6.setShadowColor(new java.awt.Color(245, 163, 202));
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        button7.setBackground(new java.awt.Color(245, 238, 230));
        button7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/image/icons8_customer_40px.png"))); // NOI18N
        button7.setText("Renters");
        button7.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        button7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        button7.setIconTextGap(20);
        button7.setShadowColor(new java.awt.Color(245, 163, 202));
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });

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
                            .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(110, 110, 110)
                .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        bookDescription.setEditable(false);
        bookDescription.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bookDescription.setForeground(new java.awt.Color(255, 255, 255));
        bookDescription.setMargin(new java.awt.Insets(10, 20, 20, 10));
        bookDescription.setPreferredSize(new java.awt.Dimension(25, 25));
        scrollBookDes.setViewportView(bookDescription);

        bookQuantity.setForeground(new java.awt.Color(255, 255, 255));

        rentButton.setText("Rent");
        rentButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rentButtonActionPerformed(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                                .addComponent(bookPrice)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bookQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                                .addComponent(rentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pictureImage, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollBookDes))
                        .addContainerGap())))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(pictureImage, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(bookAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollBookDes, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(rentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookQuantity)
                    .addComponent(bookPrice))
                .addGap(26, 26, 26))
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
                    .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(37, 37, 37))))
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
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        Forms(myLibrary);
        search.setText("Search Books");
        refreshUI();

    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        Forms(addBooks);
        SwingUtilities.invokeLater(() -> {
        addBooks.userId.removeAll();
        addBooks.userId.repaint();
        addBooks.userId.revalidate();
        addBooks.userId.setText(id.getText());
    });
        
        textRemover();
    }//GEN-LAST:event_button4ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        Forms(setting);
        textRemover();
    }//GEN-LAST:event_button5ActionPerformed

    private void rentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rentButtonActionPerformed
            
             Forms(rentBooks);
            int newQuantity = Integer.parseInt(bookQuantity.getText());
            int newPrice = Integer.parseInt(bookPrice.getText());
            modelRentData = new ModelRentData(bookTitle.getText(), newQuantity,newPrice);
            rentBooks.showBookData(modelRentData);
            refreshRentBooksUI();
            String genCtr = generateCTR();
            rentBooks.ctr.repaint();
            rentBooks.ctr.revalidate();
            rentBooks.ctr.setText(genCtr);
            rentButton.setVisible(false);
    
    }//GEN-LAST:event_rentButtonActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
           Forms(dashboard);
           search.setText("Search Borrower");
          try {
              refreshDashboardUI();
          } catch (SQLException ex) {
              Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
          }
           textRemover();
    }//GEN-LAST:event_button6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        
        if (search.getText().equals("")) {
            try {
                
                refreshUI();
                refreshDashboardUI();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            searchBooks();
            searchRenter();
            search.setText("");
        }
    }//GEN-LAST:event_searchActionPerformed

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
      search.setText("");
    }//GEN-LAST:event_searchFocusGained

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost
       search.setText("Seach Books");
    }//GEN-LAST:event_searchFocusLost

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        Forms(renterData);
        refreshRenter();
    }//GEN-LAST:event_button7ActionPerformed

   
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
                try {
                    new Main().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bookAuthor;
    private library.swing.TextPane bookDescription;
    private javax.swing.JLabel bookPrice;
    private javax.swing.JLabel bookQuantity;
    private javax.swing.JLabel bookTitle;
    private library.button.Button button3;
    private library.button.Button button4;
    private library.button.Button button5;
    private library.button.Button button6;
    private library.button.Button button7;
    public javax.swing.JLabel id;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private library.components.RoundPanel panelMoving;
    private library.components.PictureBox pictureImage;
    private library.button.Button rentButton;
    private library.components.RoundPanel roundPanel1;
    private library.components.RoundPanel roundPanel3;
    private library.components.RoundPanel roundPanel4;
    private library.components.RoundPanel roundPanel5;
    private javax.swing.JScrollPane scrollBookDes;
    private library.textfield.TextField search;
    // End of variables declaration//GEN-END:variables
}
