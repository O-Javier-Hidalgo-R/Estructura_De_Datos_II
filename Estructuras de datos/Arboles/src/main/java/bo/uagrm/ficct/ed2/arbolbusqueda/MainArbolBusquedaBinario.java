/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda;

import bo.uagrm.ficct.ed2.arbolbusqueda.mvias.ArbolMVias;


/**
 *
 * @author OJavierHR
 */
public class MainArbolBusquedaBinario {

    public static void main(String[] args) {
        IArbolBusqueda<Integer, String> aB = new ArbolMVias<>(4);
        aB.insertar(80, "A");
        aB.insertar(120, "B");
        aB.insertar(200, "C");
        aB.insertar(50, "D");
        aB.insertar(70, "E");
        aB.insertar(75, "F");
        aB.insertar(98, "G");
        aB.insertar(110, "H");
        aB.insertar(130, "I");
        aB.insertar(140, "J");
        aB.insertar(150, "K");
        aB.insertar(400, "L");
        aB.insertar(500, "M");
        aB.insertar(560, "N");
        aB.insertar(72, "Ñ");
        aB.insertar(134, "O");
        aB.insertar(160, "P");
        aB.insertar(170, "Q");
        aB.insertar(190, "R");
        aB.insertar(158, "S");
        
        System.out.println(aB.recorridoPreOrden());
    }
}
