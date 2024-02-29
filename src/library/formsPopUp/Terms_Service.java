/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package library.formsPopUp;

/**
 *
 * @author USER
 */
public class Terms_Service extends javax.swing.JPanel {

    
    public Terms_Service() {
        initComponents();
        setOpaque(false);
        terms();
        
        
    }

   private void terms(){
       String termsText = "Terms of Service\n" +
"\n" +
"Welcome to the Bibleothica Harmony platform, which is owned and operated by Name? These terms of service (which includes our Code of Conduct and our Privacy Policy) (\"Terms of Service\") are a contract between you and Portfolio. By using Portfolio (the \"Site\" and application), creating your account, and using the Site or our mobile applications to borrow and/or rent books, reading, or to access and view Portfolio content or other user content (together with the Site, the \"Services\"), you’re agreeing to these Terms of Service. If you don’t agree to any of these terms, you can’t use the Portfolio Services.\n" +
"\n" +
"You Need to be 18\n" +
"Portfolio Services are only for people 18 years old and over. If we learn someone under 18 is using Portfolio Services, we’ll terminate their account.\n" +
"\n" +
"You Need an Account\n" +
"You can’t browse very much on the Site or use the Services without registering for an account. To get the most out of the Portfolio platform, you’ll need to register, choose an account name, and set a password.\n" +
"\n" +
"You’re responsible for all the activity on your account, and for keeping your password confidential. If you share your account information with anyone, that other person may be able to take control of the account, and we may not be able to determine who is the proper account holder. We will not have any liability to you (or anyone you share your account information with) because of your or their actions under those circumstances. If you find out that someone’s used your account without your permission, you should report it immediately.\n" +
"\n" +
"Your Book is Yours\n" +
"All the books you’ve borrow, or rent will be under your supervision. You must make sure that you have understood the legal policies and consequences when paying for the amount declared for each book. Remember that every book has different prices depending upon the class and type of it. Renting a book will only last for ilang buwan. After this span of period, you will get no access to the book you add to your library.\n" +
"\n" +
"\n" +
"\n" +
"Things You Should and Shouldn’t Do\n" +
"We expect all the users to behave responsibly and help keep this a nice place. Please review the Terms of Service—which is designed to ensure that everyone understands what the Portfolio platform is all about and the values we treasure, to create a safe, fun, and caring environment for bookworms and wizards. Without restricting what’s in the Code of Conduct, don’t do any of these things on the Services:";
   TermsAndService.setText(termsText);
   
   }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new library.components.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TermsAndService = new javax.swing.JTextPane();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setRoundBottomLeft(90);
        roundPanel1.setRoundBottomRight(90);
        roundPanel1.setRoundTopLeft(90);
        roundPanel1.setRoundTopRight(90);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        TermsAndService.setEditable(false);
        TermsAndService.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(TermsAndService);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane TermsAndService;
    private javax.swing.JScrollPane jScrollPane1;
    private library.components.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
