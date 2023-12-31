package edu.uagrm.ficct.ed1.proyecto1.ui.views;

import edu.uagrm.ficct.ed1.proyecto1.app.models.MBook;
import edu.uagrm.ficct.ed1.proyecto1.arbolbusqueda.IArbolBusqueda;
import edu.uagrm.ficct.ed1.proyecto1.utils.appUtils;
import java.util.List;

public class UpBook extends javax.swing.JPanel {
    private static IArbolBusqueda<Long, MBook> rAB;
    private long localKey;
    public static final long NEW_KEY = -1;

    public UpBook() {
        initComponents();
        //InitStyles();
    }

    public UpBook(IArbolBusqueda<Long, MBook> rAB, Long clave) {
        this();
        UpBook.rAB = rAB;
        newKeyOptionCheck.setVisible(false);
        this.localKey = clave;
        if (clave != UpBook.NEW_KEY) {
            showBookUp(this.localKey, rAB.buscar(this.localKey));
            keyText.setEnabled(false);
            newKeyOptionCheck.setVisible(true);
        }
    }

    private Long getLocalKey() {
        return this.localKey;
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
        titleViewLabel = new javax.swing.JLabel();
        titlelabel = new javax.swing.JLabel();
        titleText = new javax.swing.JTextField();
        dateLabel = new javax.swing.JLabel();
        dateText = new javax.swing.JTextField();
        authorLabel = new javax.swing.JLabel();
        authorText = new javax.swing.JTextField();
        categoryLabel = new javax.swing.JLabel();
        categoryText = new javax.swing.JTextField();
        publisherLabel = new javax.swing.JLabel();
        publisherText = new javax.swing.JTextField();
        pagsLabel = new javax.swing.JLabel();
        pagesText = new javax.swing.JTextField();
        descriptionLabel = new javax.swing.JLabel();
        stockLabel = new javax.swing.JLabel();
        stockTxt = new javax.swing.JTextField();
        availableText = new javax.swing.JTextField();
        availableLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionText = new javax.swing.JTextArea();
        keyText = new javax.swing.JTextField();
        keylabel = new javax.swing.JLabel();
        lenguageLabel = new javax.swing.JLabel();
        lenguageText = new javax.swing.JTextField();
        newKeyOptionCheck = new javax.swing.JRadioButton();
        upButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(584, 383));

        bg.setPreferredSize(new java.awt.Dimension(584, 383));

        titleViewLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        titleViewLabel.setText("Subir nuevo Libro");

        titlelabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        titlelabel.setText("Título");

        titleText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        dateLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dateLabel.setText("Fecha");

        dateText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        authorLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        authorLabel.setText("Autor");

        authorText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        categoryLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        categoryLabel.setText("Categoría");

        categoryText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        publisherLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        publisherLabel.setText("Edición");

        publisherText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        pagsLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pagsLabel.setText("Páginas");

        pagesText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        pagesText.setToolTipText("");

        descriptionLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        descriptionLabel.setText("Descripción");

        stockLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        stockLabel.setText("Stock");
        stockLabel.setToolTipText("");

        stockTxt.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        stockTxt.setToolTipText("");

        availableText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        availableText.setToolTipText("");

        availableLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        availableLabel.setText("Disponibles");

        descriptionText.setColumns(20);
        descriptionText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        descriptionText.setLineWrap(true);
        descriptionText.setRows(5);
        descriptionText.setWrapStyleWord(true);
        jScrollPane1.setViewportView(descriptionText);

        keyText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        keylabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        keylabel.setText("Clave");

        lenguageLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lenguageLabel.setText("Idioma");

        lenguageText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lenguageText.setToolTipText("");

        newKeyOptionCheck.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        newKeyOptionCheck.setText("Nueva Clave");
        newKeyOptionCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newKeyOptionCheckActionPerformed(evt);
            }
        });

        upButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        upButton.setText("Subir");
        upButton.setBorderPainted(false);
        upButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(titleViewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newKeyOptionCheck))
                    .addComponent(upButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(stockLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lenguageText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                    .addComponent(lenguageLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(authorText, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(authorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(stockTxt))
                                .addGap(7, 7, 7)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(availableLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                    .addComponent(pagesText, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(pagsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(availableText)
                                    .addComponent(dateText, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(keylabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(keyText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleText)
                            .addComponent(titlelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(descriptionLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(publisherLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(publisherText))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(categoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(categoryText))))))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newKeyOptionCheck, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(titleViewLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keylabel)
                    .addComponent(titlelabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(authorLabel)
                    .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(publisherLabel)
                    .addComponent(categoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(authorText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(publisherText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoryText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLabel)
                    .addComponent(lenguageLabel)
                    .addComponent(pagsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lenguageText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pagesText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stockLabel)
                            .addComponent(availableLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stockTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(availableText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(upButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

   /**
     * Evento al pulsar el jRadioButton "nueva clave".
     * @param evt 
     */
    private void newKeyOptionCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newKeyOptionCheckActionPerformed
        // TODO add your handling code here:
        if (newKeyOptionCheck.isSelected()) {
            keyText.setEnabled(true);
        } else {
            keyText.setText(String.valueOf(getLocalKey()));
            keyText.setEnabled(false);
        }
    }//GEN-LAST:event_newKeyOptionCheckActionPerformed

    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        // TODO add your handling code here:
        rAB.insertar(Long.valueOf(keyText.getText()), createBook());
        Books.savedBooks();
        Books.showAllBooks();
    }//GEN-LAST:event_upButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorLabel;
    private javax.swing.JTextField authorText;
    private javax.swing.JLabel availableLabel;
    private javax.swing.JTextField availableText;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JTextField categoryText;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateText;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextArea descriptionText;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField keyText;
    private javax.swing.JLabel keylabel;
    private javax.swing.JLabel lenguageLabel;
    private javax.swing.JTextField lenguageText;
    private javax.swing.JRadioButton newKeyOptionCheck;
    private javax.swing.JTextField pagesText;
    private javax.swing.JLabel pagsLabel;
    private javax.swing.JLabel publisherLabel;
    private javax.swing.JTextField publisherText;
    private javax.swing.JLabel stockLabel;
    private javax.swing.JTextField stockTxt;
    private javax.swing.JTextField titleText;
    private javax.swing.JLabel titleViewLabel;
    private javax.swing.JLabel titlelabel;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables

    private void showBookUp(Long key, MBook book) {
        keyText.setText(String.valueOf(key));
        titleText.setText(book.getTitle());
        dateText.setText(book.getDate());
        authorText.setText(book.getAuthor());
        categoryText.setText(book.getCategoriesList().toString());
        publisherText.setText(book.getPublisher());
        lenguageText.setText(book.getLanguage());
        pagesText.setText(String.valueOf(book.getPages()));
        descriptionText.setText(book.getDescription());
        stockTxt.setText(String.valueOf(book.getStock()));
        availableText.setText(String.valueOf(book.getAvailable()));
    }

    private MBook createBook() {
        String title = titleText.getText();
        String author = authorText.getText();
        String description = descriptionText.getText();
        List<String> categoriesList = appUtils.stringToList(
                categoryText.getText());
        String language = lenguageText.getText();
        String publisher = publisherText.getText();
        String date = dateText.getText();
        int pages = 0;
        int stock = 0;
        int available = 0;
        
        if (!pagesText.getText().equals("")) {
            pages = Integer.parseInt(pagesText.getText());
        }
        if (!stockTxt.getText().equals("")) {
            stock = Integer.parseInt(stockTxt.getText());
        }
        if (!availableText.getText().equals("")) {
            available = Integer.parseInt(availableText.getText());
        }
        return new MBook(title, date, author, categoriesList, publisher, 
                description, language, pages, stock, available);
    }
}
