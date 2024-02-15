/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package library.forms;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.SwingUtilities;
import library.components.BookItem;
import library.components.RenterItem;
import library.event.EventRenter;
import library.model.ModelRenter;

/**
 *
 * @author USER
 */
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
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String inputString1 = renter.returnDate.getText(); // Assuming date is an object with the getText() method
        String inputString2 = renter.dateToday.getText(); // Assuming returnDate is an object with the getText() method

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
 
    public RenterData() {
        initComponents();
        setOpaque(false);
    }

  private EventRenter event;
  
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        panelItem1 = new library.swing.PanelItem();

        jScrollPane2.setViewportView(panelItem1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 188, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    public library.swing.PanelItem panelItem1;
    // End of variables declaration//GEN-END:variables
}
