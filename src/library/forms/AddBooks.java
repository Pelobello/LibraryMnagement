
package library.forms;

import com.kitfox.svg.app.data.Handler;
import com.raven.datechooser.DateChooser;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import library.database.DatabaseConnection;
import library.main.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import library.controller.AddBooksController;
import library.controller.PopulateBooksController;
import library.controller.Update_Delete_BookData;
import library.model.ModelItem;



public class AddBooks extends javax.swing.JPanel {
   private AddBooksController addBooksControll;
   private Main main;
   private PopulateBooksController populateBooks;
   private MyLibrary library;
   private Update_Delete_BookData udB;
    Connection MyCon;
    
   private DateChooser dateChooser = new DateChooser();
    public AddBooks() {
        initComponents();
        setOpaque(false);
        udB = new Update_Delete_BookData();
        dateChooser.setDateFormat(new SimpleDateFormat("MMMM dd, yyyy"));
        dateChooser.setTextField(bDate);
        library = new MyLibrary();   
        populateBooks = new PopulateBooksController(library);
        userId.setVisible(false);
        init();
        
    }
    
   
 
    private void init() {
       try {
           DatabaseConnection.getInstance().ConnectToDatabase();
       } catch (ClassNotFoundException |SQLException e) {
          e.printStackTrace();
       }
    }
    public void setTextFieldToNone(){
        bTitle.setText("");
        bAuthor.setText("");
        bPublisher.setText("");
        bookID.setText("");
        bookPrice.setText("");
        pCount.setText("");
        quantity.setText("");
        bDescription.setText("");
        pic.setImage(new ImageIcon(getClass().getResource("/library/image/no image Available.jpg")));
        pic.repaint();
    }

  public void addBooksButton() {
    Icon picIcon = pic.getImage();
    String selectCategory = bCategory.getSelectedItem().toString();
    String selectLanguage = bLanguage.getSelectedItem().toString();
    String selectFormat = bFormat.getSelectedItem().toString();
    String selectEdition = bEdition.getSelectedItem().toString();
    int addPageCount = Integer.parseInt(pCount.getText());
    int addQuantity = Integer.parseInt(quantity.getText());
     int addbookPrice = Integer.parseInt(bookPrice.getText());
    byte[] imageBytes;

    
        if (bTitle.getText().equals("")||bAuthor.getText().equals("") ||bPublisher.getText().equals("")) {
          JOptionPane.showMessageDialog(this, "Please Fill out the empty Fields!");
      }
        else{
             try { 
        imageBytes = convertImageIconToByteArray(picIcon);

        addBooksControll = new AddBooksController(userId.getText(), bTitle.getText(), bAuthor.getText(), bPublisher.getText()
                                        , bDate.getText(), bDescription.getText(), selectCategory, selectLanguage,
                                                selectFormat, selectEdition, addPageCount, addQuantity,addbookPrice, new ImageIcon(imageBytes));
        addBooksControll.addBookToDatabase();
        setTextFieldToNone();
        JOptionPane.showMessageDialog(this, "Successfully Added!");
    } catch (IOException | SQLException | ClassNotFoundException ex) {
        try {
            DatabaseConnection.getInstance().getConnection().rollback();
        } catch (SQLException rollbackException) {
            rollbackException.printStackTrace();
        }

        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } 
            
        }
  
}
   public void updateBooksButton() {
    Icon picIcon = pic.getImage();
    String selectCategory = bCategory.getSelectedItem().toString();
    String selectLanguage = bLanguage.getSelectedItem().toString();
    String selectFormat = bFormat.getSelectedItem().toString();
    String selectEdition = bEdition.getSelectedItem().toString();
    int addPageCount = Integer.parseInt(pCount.getText());
    int addQuantity = Integer.parseInt(quantity.getText());
     int addbookPrice = Integer.parseInt(bookPrice.getText());
     
    byte[] imageBytes;

    
        if (bTitle.getText().equals("")||bAuthor.getText().equals("") ||bPublisher.getText().equals("")) {
          JOptionPane.showMessageDialog(this, "Please Fill out the empty Fields!");
      }
        else{
             try { 
        imageBytes = convertImageIconToByteArray(picIcon);
        
        ModelItem data = new ModelItem(bTitle.getText(), bAuthor.getText(), bPublisher.getText(), bDate.getText(), bDescription.getText(), selectCategory, selectLanguage, selectFormat, selectEdition, addPageCount, addQuantity, addbookPrice, picIcon);
         int idData = Integer.parseInt(bookID.getText());
        data.setId(idData);
        udB.updateBookDat(data);
        setTextFieldToNone();
        JOptionPane.showMessageDialog(this, "Successfully Updated!");
    } catch (IOException ex) {
        try {
            DatabaseConnection.getInstance().getConnection().rollback();
        } catch (SQLException rollbackException) {
            rollbackException.printStackTrace();
        }

        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } 
            
        }
  
}
  private boolean alreadyExisting(){
      try {
          String sql = "SELECT * FROM library_data WHERE userId = ? AND BTitle = ?";
          PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
          p.setString(1, userId.getText());
          p.setString(2, bTitle.getText());
          
          ResultSet rs = p.executeQuery();
          
          
          return rs.next();
          
      } catch (Exception e) {
          e.printStackTrace();
          return false;
      }
  }
    private byte[] convertImageIconToByteArray(Icon icon) throws IOException {
    BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
    icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", outputStream);

    return outputStream.toByteArray();
}
    public void selectImage(){
              JFileChooser imgChooser = new JFileChooser();
    FileNameExtensionFilter fn = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
    imgChooser.addChoosableFileFilter(fn);

    int showOpenDialog = imgChooser.showOpenDialog(null);

    if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
        File selectedFile = imgChooser.getSelectedFile();

        String fileName = selectedFile.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("jpeg")) {
            JOptionPane.showMessageDialog(this, "Not an image, Please try again!!");
        } else {
            if (selectedFile != null) {
                try {
                    BufferedImage originalImage = ImageIO.read(selectedFile);             
                    int targetWidth = 800;
                    int targetHeight = (int) ((double) originalImage.getHeight() / originalImage.getWidth() * targetWidth);
                    Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);                
                    ImageIcon imgIcon = new ImageIcon(resizedImage);
                    pic.setImage(imgIcon);
                    pic.repaint();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No file selected");
            }
        }
    }
    }
    private void deleteBooksData(){
        ModelItem item = new ModelItem();
        if (bookID.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Invalid Book To Delete");
        }else{
            int idToInt = Integer.parseInt(bookID.getText());
            item.setId(idToInt);
            udB.deleteBooksData(item);
            
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bTitle = new library.textfield.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bAuthor = new library.textfield.TextField();
        jLabel3 = new javax.swing.JLabel();
        bPublisher = new library.textfield.TextField();
        jLabel4 = new javax.swing.JLabel();
        bDate = new library.textfield.TextField();
        jLabel5 = new javax.swing.JLabel();
        pic = new library.components.PictureBox();
        button1 = new library.button.Button();
        addBook = new library.button.Button();
        jLabel8 = new javax.swing.JLabel();
        bCategory = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        bLanguage = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        bFormat = new javax.swing.JComboBox<>();
        bEdition = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        addImage = new library.button.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        bDescription = new javax.swing.JTextPane();
        quantity = new library.textfield.TextField();
        pCount = new library.textfield.TextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        userId = new javax.swing.JLabel();
        bookPrice = new library.textfield.TextField();
        jLabel7 = new javax.swing.JLabel();
        addBook1 = new library.button.Button();
        bookID = new javax.swing.JLabel();

        bTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Book Title*");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Book Author*");

        bAuthor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Publisher*");

        bPublisher.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Publication Date*");

        bDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Description");

        pic.setImage(new javax.swing.ImageIcon(getClass().getResource("/library/image/no image Available.jpg"))); // NOI18N

        button1.setBackground(new java.awt.Color(97, 103, 122));
        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setText("Update");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        addBook.setBackground(new java.awt.Color(97, 103, 122));
        addBook.setForeground(new java.awt.Color(0, 0, 0));
        addBook.setText("Add ");
        addBook.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        addBook.setPreferredSize(new java.awt.Dimension(75, 47));
        addBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Select Book Category*");

        bCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Select Language");

        bLanguage.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bLanguage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Select Format*");

        bFormat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bFormat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        bFormat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFormatActionPerformed(evt);
            }
        });

        bEdition.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bEdition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Edition*");

        addImage.setForeground(new java.awt.Color(56, 122, 159));
        addImage.setText("Click Here to add Image");
        addImage.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addImageMouseEntered(evt);
            }
        });
        addImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addImageActionPerformed(evt);
            }
        });

        bDescription.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bDescription.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        bDescription.setDragEnabled(true);
        bDescription.setPreferredSize(new java.awt.Dimension(25, 25));
        jScrollPane2.setViewportView(bDescription);

        jScrollPane1.setViewportView(jScrollPane2);

        quantity.setText("0");
        quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quantityKeyTyped(evt);
            }
        });

        pCount.setText("0");
        pCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pCountKeyTyped(evt);
            }
        });

        jLabel6.setText("PageCount:*");

        jLabel9.setText("Quantity:*");

        bookPrice.setText("0");
        bookPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bookPriceKeyTyped(evt);
            }
        });

        jLabel7.setText("Price*");

        addBook1.setBackground(new java.awt.Color(97, 103, 122));
        addBook1.setForeground(new java.awt.Color(0, 0, 0));
        addBook1.setText("Delete");
        addBook1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        addBook1.setPreferredSize(new java.awt.Dimension(75, 47));
        addBook1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBook1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bPublisher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bAuthor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)))
                    .addComponent(bEdition, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bFormat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bLanguage, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(315, 315, 315))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bookPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)))
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pCount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addImage, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(addBook, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(addBook1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userId, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addImage, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(userId))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addBook1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bookID))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bDate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pCount, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bEdition, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed

         int reply = JOptionPane.showConfirmDialog(this, "Are you sure you want to Update this Book Data?");
        if (reply == JOptionPane.YES_OPTION) {
            updateBooksButton();
            setTextFieldToNone();
        }
        else if (reply == JOptionPane.CANCEL_OPTION) {
            setTextFieldToNone();
        }else{
        
        }
            
        
       
        
       
    }//GEN-LAST:event_button1ActionPerformed

    private void addBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookActionPerformed
        if (alreadyExisting()) {
            JOptionPane.showMessageDialog(this, "You Input a Already Existing Data");
        }else{
             int reply = JOptionPane.showConfirmDialog(this, "Are you sure you want to Add this Book Data?");
        if (reply == JOptionPane.YES_OPTION) {
            addBooksButton();
            setTextFieldToNone();
        }
        else if (reply == JOptionPane.CANCEL_OPTION) {
            setTextFieldToNone();
        }else{
            
        }
        }
    }//GEN-LAST:event_addBookActionPerformed

    private void addImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addImageActionPerformed
        selectImage();
    }//GEN-LAST:event_addImageActionPerformed

    private void addImageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addImageMouseEntered
        addImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_addImageMouseEntered

    private void bFormatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFormatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bFormatActionPerformed

    private void pCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pCountKeyTyped
         char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_pCountKeyTyped

    private void quantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyTyped
         char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_quantityKeyTyped

    private void bookPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bookPriceKeyTyped
         char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_bookPriceKeyTyped

    private void addBook1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBook1ActionPerformed
        if (alreadyExisting()) {
            
        }
        
        int reply = JOptionPane.showConfirmDialog(this, "Are you sure you want to Delete this Book Data?");
        if (reply == JOptionPane.YES_OPTION) {
            deleteBooksData(); 
            setTextFieldToNone();
        }
        else if (reply == JOptionPane.CANCEL_OPTION) {
            setTextFieldToNone();
        }else{
            
        }
    }//GEN-LAST:event_addBook1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private library.button.Button addBook;
    private library.button.Button addBook1;
    private library.button.Button addImage;
    public library.textfield.TextField bAuthor;
    public javax.swing.JComboBox<String> bCategory;
    public library.textfield.TextField bDate;
    public javax.swing.JTextPane bDescription;
    public javax.swing.JComboBox<String> bEdition;
    public javax.swing.JComboBox<String> bFormat;
    public javax.swing.JComboBox<String> bLanguage;
    public library.textfield.TextField bPublisher;
    public library.textfield.TextField bTitle;
    public javax.swing.JLabel bookID;
    public library.textfield.TextField bookPrice;
    private library.button.Button button1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public library.textfield.TextField pCount;
    public library.components.PictureBox pic;
    public library.textfield.TextField quantity;
    public javax.swing.JLabel userId;
    // End of variables declaration//GEN-END:variables
}
