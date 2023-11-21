/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda;

import bo.uagrm.ficct.ed2.arbolbusqueda.mvias.ArbolB;
import bo.uagrm.ficct.ed2.arbolbusqueda.mvias.ArbolMVias;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author OJavierHR
 */
public class MainArbolBusquedaBinario {

    public static void main(String[] args) throws ClaveNoEncontrada {
        IArbolBusqueda<Integer, Integer> aB = new ArbolB<>(5);
        
        
        for (int i = 1; i <= 20; i++) {
            aB.insertar(i, i);
        }
        
        System.out.println(aB.recorridoPorNiveles());
        System.out.println(aB.recorridoPreOrden());
        System.out.println(aB.recorridoInOrden());
        
        for (int i = 20; i >= 1; i--) {
            System.out.println(aB.eliminar(i));
        }
        
        /*aB.eliminar(20);
        aB.eliminar(19);
        aB.eliminar(18);
        aB.eliminar(17);*/
        
        System.out.println(aB.recorridoPorNiveles().size());
        System.out.println(aB.recorridoPreOrden());
        System.out.println(aB.recorridoInOrden());
    }
}
