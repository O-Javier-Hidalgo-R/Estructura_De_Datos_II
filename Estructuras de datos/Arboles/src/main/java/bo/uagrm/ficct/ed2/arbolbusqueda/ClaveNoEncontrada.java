/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda;

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
