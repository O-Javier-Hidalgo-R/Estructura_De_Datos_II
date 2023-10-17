/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package bo.uagrm.ficct.ed2.arbolbusqueda;

import bo.uagrm.ficct.ed2.arbolbusqueda.binario.ABB;
import bo.uagrm.ficct.ed2.arbolbusqueda.binario.ArbolBinarioBusquedaIterativo;
import bo.uagrm.ficct.ed2.arbolbusqueda.binario.ArbolBinarioBusquedaRecursivo;
import bo.uagrm.ficct.ed2.arbolbusqueda.mvias.ArbolB;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author OJavierHR
 */
public class MainArbolBusquedaBinario {
    public static void main(String[] args) {
        
        List<Integer> clavesInOrden = Arrays.asList(1, 3, 4, 6, 7, 8, 10, 13, 14);
        List<String> valoresInOrden = Arrays.asList("c", "b", "e", "d", "f", "a", "g", "i", "h");
        List<Integer> clavesNoInOrden = Arrays.asList(8, 3, 1, 6, 4, 7, 10, 14, 13);
        List<String> valoresNoInOrden = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i");
        
        ABB<Integer, String> arbolBusqueda =
        new ABB<>(clavesInOrden,
        valoresInOrden, clavesNoInOrden, valoresNoInOrden,
        true);
        
        /*
        ABB<Integer, String> arbolBusqueda = new ABB<>();
        arbolBusqueda.insertar(5, "a");
        arbolBusqueda.insertar(1, "b");
        arbolBusqueda.insertar(10, "c");
        */
        
        System.out.println(arbolBusqueda.recorridoPorNiveles());
        System.out.println(arbolBusqueda.recorridoPreOrden());
        System.out.println(arbolBusqueda.recorridoInOrden());
        System.out.println(arbolBusqueda.recorridoPosOrden());
        System.out.println(arbolBusqueda.nivel());
    }
}
