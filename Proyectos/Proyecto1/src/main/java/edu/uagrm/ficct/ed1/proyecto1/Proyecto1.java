/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uagrm.ficct.ed1.proyecto1;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import edu.uagrm.ficct.ed1.proyecto1.ui.forms.FormMain;

/**
 * Proyecto de "Arboles" presentado para la materia de estructura de datos II.
 * Impartida por el docente Roberto Carlos Vaca Pinto. Gestion 2/2023.
 * @author OJavierHR
 */
public class Proyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatArcDarkOrangeIJTheme.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormMain().setVisible(true);
        });
    }
}
