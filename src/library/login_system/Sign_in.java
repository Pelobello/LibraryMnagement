
package library.login_system;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import library.controller.PopulateBooksController;
import library.controller.PopulateDashboardController;
import library.forms.AddBooks;
import library.forms.DashBoard;
import library.main.Main;
import library.userId.User_Id_Constructor;


public class Sign_in extends javax.swing.JFrame {
    private Main main;
    private DashBoard dashboard;
    private PopulateBooksController populateBooks;
    private User_Id_Constructor userIdC;
    private AddBooks addbooks;
    
    public Sign_in() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        main = new Main();
        dashboard = new DashBoard();
        addbooks = new AddBooks();
       
        initMoving(this);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMoving = new library.components.RoundPanel();
        roundPanel1 = new library.components.RoundPanel();
        userName = new library.textfield.TextField();
        passWord = new library.textfield.TextField();
        SignIn = new library.button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelMoving.setBackground(new java.awt.Color(255, 255, 255));
        panelMoving.setRoundBottomLeft(30);
        panelMoving.setRoundBottomRight(30);
        panelMoving.setRoundTopLeft(30);
        panelMoving.setRoundTopRight(30);

        userName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        passWord.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        SignIn.setText("Sign in");
        SignIn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        SignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passWord, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(SignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128))))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(passWord, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
        panelMoving.setLayout(panelMovingLayout);
        panelMovingLayout.setHorizontalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addGap(466, 466, 466)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(516, Short.MAX_VALUE))
        );
        panelMovingLayout.setVerticalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovingLayout.createSequentialGroup()
                .addContainerGap(256, Short.MAX_VALUE)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(235, 235, 235))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignInActionPerformed
       String dashboardUser = "142";
       
        
       
        main.setVisible(true);
        main.id.setText(dashboardUser);
        
        main.refreshDashboardUI();
 
       setVisible(false);
    }//GEN-LAST:event_SignInActionPerformed

    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sign_in().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private library.button.Button SignIn;
    private library.components.RoundPanel panelMoving;
    private library.textfield.TextField passWord;
    private library.components.RoundPanel roundPanel1;
    private library.textfield.TextField userName;
    // End of variables declaration//GEN-END:variables
}
