/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.exceptions;

import java.io.IOException;

/**
 *
 * @author OJavierHR
 */
public class AristaYaExisteExcepcion extends IOException {

    public AristaYaExisteExcepcion() {
        super();
    }
    
    public AristaYaExisteExcepcion(String message){
        super(message);
    }
}
