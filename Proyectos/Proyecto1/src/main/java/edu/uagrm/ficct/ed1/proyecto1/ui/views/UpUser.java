package edu.uagrm.ficct.ed1.proyecto1.ui.views;

import bo.uagrm.ficct.ed2.arbolbusqueda.IArbolBusqueda;
import edu.uagrm.ficct.ed1.proyecto1.app.models.User;

public class UpUser extends javax.swing.JPanel {
    private static IArbolBusqueda<Long, User> rAB;
    private Long localKey;
    public static final int NEW_KEY = -1;

    public UpUser() {
        initComponents();
        InitStyles();
    }

    UpUser(IArbolBusqueda<Long, User> rAB, Long clave) {
        this();
        UpUser.rAB = rAB;
        newKeyOptionCheck.setVisible(false);
        this.localKey = clave;
        if (clave != UpUser.NEW_KEY) {
            showUpUser(this.localKey, rAB.buscar(clave));
            keyTxt.setEnabled(false);
            newKeyOptionCheck.setVisible(true);
        }
    }

    private void InitStyles() {
    }

    public Long getLocalKey() {
        return localKey;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        nameLbl = new javax.swing.JLabel();
        nameTxt = new javax.swing.JTextField();
        apPLbl = new javax.swing.JLabel();
        fistLastNameTxt = new javax.swing.JTextField();
        apMLbl = new javax.swing.JLabel();
        secontLastNameTxt = new javax.swing.JTextField();
        domLbl = new javax.swing.JLabel();
        addresTxt = new javax.swing.JTextField();
        upButton = new javax.swing.JButton();
        phoneLbl = new javax.swing.JLabel();
        phoneTxt = new javax.swing.JTextField();
        newKeyOptionCheck = new javax.swing.JRadioButton();
        keyLbl = new javax.swing.JLabel();
        keyTxt = new javax.swing.JTextField();

        title.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        title.setText("Registrar nuevo Usuario");

        nameLbl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nameLbl.setText("Nombre");

        nameTxt.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        apPLbl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        apPLbl.setText("Apellido Paterno");

        fistLastNameTxt.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        apMLbl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        apMLbl.setText("Apellido Materno");

        secontLastNameTxt.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        domLbl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        domLbl.setText("Domicilio");

        addresTxt.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        addresTxt.setToolTipText("");

        upButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        upButton.setText("Registrar");
        upButton.setBorderPainted(false);
        upButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });

        phoneLbl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        phoneLbl.setText("Teléfono");

        phoneTxt.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        phoneTxt.setToolTipText("");

        newKeyOptionCheck.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        newKeyOptionCheck.setText("Nueva Clave");
        newKeyOptionCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newKeyOptionCheckActionPerformed(evt);
            }
        });

        keyLbl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        keyLbl.setText("Clave");

        keyTxt.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newKeyOptionCheck))
                    .addComponent(upButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fistLastNameTxt)
                            .addComponent(apPLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameTxt)
                            .addComponent(nameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(keyTxt)
                            .addComponent(keyLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addresTxt)
                                    .addComponent(domLbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(phoneTxt)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(secontLastNameTxt))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(apMLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(phoneLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title)
                    .addComponent(newKeyOptionCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(domLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(keyLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(keyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(phoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(secontLastNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLbl)
                            .addComponent(phoneLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(apPLbl)
                            .addComponent(apMLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fistLastNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(upButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        rAB.insertar(Long.valueOf(keyTxt.getText()), createUser());
        Users.savedUser();
        Users.showAllUsers();
    }//GEN-LAST:event_upButtonActionPerformed

    private void newKeyOptionCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newKeyOptionCheckActionPerformed
        // TODO add your handling code here:
        if (newKeyOptionCheck.isSelected()) {
            keyLbl.setEnabled(true);
        } else {
            keyLbl.setText(String.valueOf(getLocalKey()));
            keyLbl.setEnabled(false);
        }
    }//GEN-LAST:event_newKeyOptionCheckActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addresTxt;
    private javax.swing.JLabel apMLbl;
    private javax.swing.JLabel apPLbl;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel domLbl;
    private javax.swing.JTextField fistLastNameTxt;
    private javax.swing.JLabel keyLbl;
    private javax.swing.JTextField keyTxt;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JRadioButton newKeyOptionCheck;
    private javax.swing.JLabel phoneLbl;
    private javax.swing.JTextField phoneTxt;
    private javax.swing.JTextField secontLastNameTxt;
    private javax.swing.JLabel title;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables

    private void showUpUser(Long localKey, User user) {
        keyTxt.setText(String.valueOf(localKey));
        nameTxt.setText(user.getName());
        fistLastNameTxt.setText(user.getFistLastName());
        secontLastNameTxt.setText(user.getSecondLastName());
        addresTxt.setText(user.getAddres());
        phoneTxt.setText(user.getPhone());
    }

    private User createUser() {
        String name = nameTxt.getText();
        String fistLastName = fistLastNameTxt.getText();
        String secondLastName = secontLastNameTxt.getText();
        String addres = addresTxt.getText();
        String phone = phoneTxt.getText();
        
        return new User(name, fistLastName, secondLastName, addres, phone, 0, 0);
    }
}