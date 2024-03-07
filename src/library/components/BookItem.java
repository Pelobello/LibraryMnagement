/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package library.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import library.model.ModelItem;


public class BookItem extends javax.swing.JPanel {

   
    public boolean isSelected() {
        return selected;
    }

    
    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public ModelItem getData() {
        return data;
    }

   
    public void setData(ModelItem data) {
        this.data = data;
        bookImage.setImage(data.getCoverImage());
        bookTitle.setText(data.getBookTitle());
        bookAuthor.setText(data.getBookAuthor());
        publisher.setText(data.getPublisher());
        publicationDate.setText(data.getPublicationDate());
        bookCategory.setText(data.getBookCategory());
        language.setText(data.getLanguage());
        format.setText(data.getFormat());
        edition.setText(data.getEdition());
        pageCount.setText(Integer.toString(data.getPageCount()));
        quantity.setText(Integer.toString(data.getQuantity()));
        price.setText(Integer.toString(data.getPrice()));
        id.setText(Integer.toString(data.getId()));
    }

    
    private void hideLable(){
         publisher.setVisible(false);
        publicationDate.setVisible(false);
        bookDescription.setVisible(false);
        bookCategory.setVisible(false);
        language.setVisible(false);
        format.setVisible(false);
        edition.setVisible(false);
        pageCount.setVisible(false);
        quantity.setVisible(false);
        bookAuthor.setVisible(false);
        price.setVisible(false);
       
    }
    
    
    public BookItem() {
        initComponents();
        setOpaque(false);
        hideLable();
       
    }
     private boolean selected;
     private ModelItem data;
 

 @Override
    public void paint(Graphics g) {
         Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(234, 246, 246));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        if (isSelected()) {
            g2.setColor(new Color(200,156,255));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
        }
        
        g2.dispose();
        super.paint(g); 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bookTitle = new javax.swing.JLabel();
        bookAuthor = new javax.swing.JLabel();
        quantity = new javax.swing.JLabel();
        publisher = new javax.swing.JLabel();
        publicationDate = new javax.swing.JLabel();
        bookDescription = new javax.swing.JLabel();
        bookCategory = new javax.swing.JLabel();
        language = new javax.swing.JLabel();
        format = new javax.swing.JLabel();
        edition = new javax.swing.JLabel();
        pageCount = new javax.swing.JLabel();
        bookImage = new library.components.PictureBox();
        price = new javax.swing.JLabel();
        id = new javax.swing.JLabel();

        bookTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bookTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookTitle.setText("Title");

        bookAuthor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bookAuthor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        quantity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        bookImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/library/image/calculusBook.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(publisher, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(publicationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bookDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bookCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(language, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(format, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edition, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pageCount, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bookAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(bookImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(138, Short.MAX_VALUE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(124, 124, 124)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bookImage, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bookAuthor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(publisher)
                    .addComponent(publicationDate)
                    .addComponent(bookDescription)
                    .addComponent(bookCategory)
                    .addComponent(language)
                    .addComponent(format)
                    .addComponent(edition)
                    .addComponent(pageCount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(price))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(401, Short.MAX_VALUE)
                    .addComponent(id)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bookAuthor;
    private javax.swing.JLabel bookCategory;
    private javax.swing.JLabel bookDescription;
    private library.components.PictureBox bookImage;
    private javax.swing.JLabel bookTitle;
    private javax.swing.JLabel edition;
    private javax.swing.JLabel format;
    private javax.swing.JLabel id;
    private javax.swing.JLabel language;
    private javax.swing.JLabel pageCount;
    private javax.swing.JLabel price;
    private javax.swing.JLabel publicationDate;
    private javax.swing.JLabel publisher;
    private javax.swing.JLabel quantity;
    // End of variables declaration//GEN-END:variables
}
