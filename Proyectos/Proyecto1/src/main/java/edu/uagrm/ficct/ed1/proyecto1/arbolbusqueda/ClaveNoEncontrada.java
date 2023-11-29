/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uagrm.ficct.ed1.proyecto1.arbolbusqueda;

/**
 *
 * @author OJavierHR
 */
public class ClaveNoEncontrada extends Exception {

    public ClaveNoEncontrada(String tipoArbol) {
        super(("!!!La clave no se encontro en el " + 
                tipoArbol + "¡¡¡").toUpperCase());
    }
}
