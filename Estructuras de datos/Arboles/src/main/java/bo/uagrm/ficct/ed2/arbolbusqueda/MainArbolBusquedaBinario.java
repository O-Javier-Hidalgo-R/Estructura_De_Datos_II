/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package bo.uagrm.ficct.ed2.arbolbusqueda;

import bo.uagrm.ficct.ed2.arbolbusqueda.binario.ArbolBinarioBusquedaIterativo;
import bo.uagrm.ficct.ed2.arbolbusqueda.binario.ArbolBinarioBusquedaRecursivo;
import bo.uagrm.ficct.ed2.arbolbusqueda.mvias.ArbolB;

/**
 *
 * @author OJavierHR
 */
public class MainArbolBusquedaBinario {
    public static void main(String[] args) {
        IArbolBusqueda<Integer, String> arbolBusqueda = new ArbolB<>(3);
        arbolBusqueda.insertar(1, "");
        arbolBusqueda.insertar(2, "");
        arbolBusqueda.insertar(3, "");
        arbolBusqueda.insertar(4, "");
        arbolBusqueda.insertar(5, "");
        arbolBusqueda.insertar(6, "");
        arbolBusqueda.insertar(7, "");
        arbolBusqueda.insertar(8, "");
        arbolBusqueda.insertar(9, "");
        arbolBusqueda.insertar(10, "");
        arbolBusqueda.insertar(11, "");
        
        System.out.println(arbolBusqueda.recorridoPorNiveles());
        System.out.println(arbolBusqueda.recorridoPreOrden());
        System.out.println(arbolBusqueda.recorridoInOrden());
        System.out.println(arbolBusqueda.recorridoPostOrden());
        System.out.println(arbolBusqueda.size());
        System.out.println(arbolBusqueda.nivel());
    }
}
