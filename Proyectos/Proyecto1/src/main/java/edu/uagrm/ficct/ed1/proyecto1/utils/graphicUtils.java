/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uagrm.ficct.ed1.proyecto1.utils;

import edu.uagrm.ficct.ed1.proyecto1.app.models.MBook;
import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author OJavierHR
 */
public class graphicUtils {

    public static void setStyle(JComponent component, String styleName,
            Color color) {
        if (styleName != null) {
            component.putClientProperty("FlatLaf.styleClass",
                    styleName);
        }
        if (color != null) {
            component.setForeground(color);
        }
    }

    public static String setDate() {
        LocalDate now = LocalDate.now();
        return now.format(DateTimeFormatter.ofPattern("'Hoy es' "
                + "EEEE dd 'de' MMMM 'de' yyyy", Locale.getDefault()));
    }

    public static void showJPanel(JPanel panelShow, JPanel containerPanel) {
        panelShow.setSize(containerPanel.getSize());
        panelShow.setLocation(0, 0);

        containerPanel.removeAll();
        containerPanel.add(panelShow, BorderLayout.CENTER);
        containerPanel.revalidate();
        containerPanel.repaint();
    }

    public static void tableClear(DefaultTableModel model) {
        int a = model.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public static void instructionText(JTextField textField, String message) {
        textField.putClientProperty("JTextField.placeholderText", 
                message);
    }

    public static void TableBooksClear(DefaultTableModel model) {
        int a = model.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public static String setMainState(String state) {
        return "Main/" + state;
    }
}
