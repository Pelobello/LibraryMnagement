/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package library.forms;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import library.components.BookItem;
import library.controller.PopulateBooksController;
import library.database.DatabaseConnection;
import library.event.EventItem;
import library.main.Main;
import library.model.ModelItem;
import library.model.ModelRentData;


public class MyLibrary extends javax.swing.JPanel {
    public void setEvent(EventItem event) {
        this.event = event;
    }

   private PopulateBooksController populateControll;
   
    private Main main;
   
    public MyLibrary() {
        initComponents();
      
        setOpaque(false);
    }
    
   
private EventItem event;
 
  public void addBooks(ModelItem data){
      BookItem item = new BookItem();
      item.setData(data);
      item.addMouseListener(new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
         if (SwingUtilities.isLeftMouseButton(e)) {
                    event.itemClick(item, data);
                }               
                super.mousePressed(e);
          
          }
      });

      panelItem1.add(item);
      
          
      
      repaint();
      revalidate();
 
  }

  public void setSelected(Component item){
        for (Component com : panelItem1.getComponents()) {
            BookItem i =(BookItem)com;
            if (i.isSelected()) {
                i.setSelected(false);
            }
            ((BookItem) item).setSelected(true);
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel2 = new library.components.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelItem1 = new library.swing.PanelItem();

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel2.setRoundBottomLeft(40);
        roundPanel2.setRoundBottomRight(40);
        roundPanel2.setRoundTopLeft(40);
        roundPanel2.setRoundTopRight(40);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportView(panelItem1);

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public library.swing.PanelItem panelItem1;
    private library.components.RoundPanel roundPanel2;
    // End of variables declaration//GEN-END:variables
}
