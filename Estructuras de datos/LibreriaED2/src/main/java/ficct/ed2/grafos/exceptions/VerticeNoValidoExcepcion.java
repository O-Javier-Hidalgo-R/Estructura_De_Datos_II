/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.exceptions;

/**
 *
 * @author OJavierHR
 */
public class VerticeNoValidoExcepcion extends RuntimeException {

    public VerticeNoValidoExcepcion(){
        super();
    }
    public VerticeNoValidoExcepcion(String message) {
        super(message);
    }
}
