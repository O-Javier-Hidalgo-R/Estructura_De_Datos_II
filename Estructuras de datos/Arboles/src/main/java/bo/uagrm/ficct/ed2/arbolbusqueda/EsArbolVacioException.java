/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda;

/**
 *
 * @author OJavierHR
 */
public class EsArbolVacioException extends Exception {

    public EsArbolVacioException(String tipoArbol) {
        super(("¡¡¡El Arbol " + tipoArbol +
                " esta vacio!!").toUpperCase());
    }
    
}
