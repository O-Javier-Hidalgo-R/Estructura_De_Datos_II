/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.binario.ejercicios;

import bo.uagrm.ficct.ed2.arbolbusqueda.binario.ArbolBinarioBusquedaRecursivo;
import bo.uagrm.ficct.ed2.arbolbusqueda.binario.NodoBinario;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author OJavierHR
 * @param <K>
 * @param <V>
 */
public class EjerciciosABBI<K extends Comparable<K>, V>
        extends ArbolBinarioBusquedaRecursivo<K, V> {

    /*
        Implementar metodo que cuente las hojas del arbol 
     */
    public int contarHojas() {
        int hojas = 0;
        if (!esVacio()) {
            Stack<NodoBinario<K, V>> pilaAux = new Stack();
            NodoBinario<K, V> nodoActual;

            pilaAux.add(getRaiz());
            do {
                nodoActual = pilaAux.pop();
                if (!nodoActual.esVacioHijoDerecho()) {
                    pilaAux.add(nodoActual.getHijoDerecho());
                }
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    pilaAux.add(nodoActual.getHijoIzquierdo());
                }
                if (nodoActual.esHoja()) {
                    hojas++;
                }
            } while (!pilaAux.isEmpty());
        }
        return hojas;
    }

    /*
        Implementar metodo que cuente las hojas del arbol en un determinado 
        nivel
     */
    public int contarHojasNivel(int nivel) {
        int hojas = 0;
        if (!esVacio()) {
            Queue<NodoBinario<K, V>> colaAux = new LinkedList();
            NodoBinario<K, V> nodoActual;
            int nodosEnNivel;
            int nivelActual = 0;

            colaAux.add(getRaiz());
            while (!colaAux.isEmpty() && nivelActual <= nivel) {
                nodosEnNivel = colaAux.size();
                while (nodosEnNivel > 0) {
                    nodoActual = colaAux.poll();
                    if (!nodoActual.esVacioHijoIzquierdo()) {
                        colaAux.add(nodoActual.getHijoIzquierdo());
                    }
                    if (!nodoActual.esVacioHijoDerecho()) {
                        colaAux.add(nodoActual.getHijoDerecho());
                    }
                    if (nivelActual == nivel && nodoActual.esHoja()) {
                        hojas++;
                    }
                    nodosEnNivel--;
                }
                nivelActual++;
            }
        }
        return hojas;
    }

    /*
        funcion que retorne la lista de claves de los niveles del arbol menos el
        nivel n
     */
    public List<K> mostrarNodosMenosN(int n) {
        List<K> claves = new LinkedList<>();
        if (!this.esVacio()) {
            Queue<NodoBinario<K, V>> colaAux = new LinkedList();
            NodoBinario<K, V> nodoActual;
            int nodosEnNivel;
            int nivelActual = 0;

            colaAux.add(getRaiz());
            while (!colaAux.isEmpty()) {
                nodosEnNivel = colaAux.size();
                while (nodosEnNivel > 0) {
                    nodoActual = colaAux.poll();
                    if (!nodoActual.esVacioHijoIzquierdo()) {
                        colaAux.add(nodoActual.getHijoIzquierdo());
                    }
                    if (!nodoActual.esVacioHijoDerecho()) {
                        colaAux.add(nodoActual.getHijoDerecho());
                    }
                    if (nivelActual != n) {
                        claves.add(nodoActual.getClave());
                    }
                    nodosEnNivel--;
                }
                nivelActual++;
            }
        }
        return claves;
    }

    public static void main(String[] arg) {
        EjerciciosABBI<Integer, String> aBBI = new EjerciciosABBI<>();

        aBBI.insertar(8, "A");
        aBBI.insertar(3, "B");
        aBBI.insertar(10, "C");
        aBBI.insertar(1, "D");
        aBBI.insertar(6, "E");
        aBBI.insertar(14, "F");
        aBBI.insertar(4, "G");
        aBBI.insertar(7, "H");
        aBBI.insertar(13, "I");
        
        System.out.println(aBBI.mostrarNodosMenosN(2));
    }
}
