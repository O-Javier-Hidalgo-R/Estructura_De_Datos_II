/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.binario.ejercicios;

import bo.uagrm.ficct.ed2.arbolbusqueda.IArbolBusqueda;
import bo.uagrm.ficct.ed2.arbolbusqueda.binario.ArbolBinarioBusquedaRecursivo;
import bo.uagrm.ficct.ed2.arbolbusqueda.binario.NodoBinario;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EjerciciosABBR<K extends Comparable<K>, V>
        extends ArbolBinarioBusquedaRecursivo<K, V> {

    public EjerciciosABBR() {
    }
    
    public EjerciciosABBR(EjerciciosABBR<K, V> copiando) {
        this.raiz = copiarReferencias(this.raiz, copiando.raiz);
    }
    
    private NodoBinario<K, V> copiarReferencias(NodoBinario<K, V> nodoActualCopia, NodoBinario<K, V> nodoActualCopiando) {
        if(NodoBinario.esVacio(nodoActualCopiando)){
            return null;
        }
        nodoActualCopia = new NodoBinario<>(nodoActualCopiando.getClave(), nodoActualCopiando.getValor());
        nodoActualCopia.setHijoIzquierdo(copiarReferencias(nodoActualCopia.getHijoIzquierdo(), nodoActualCopiando.getHijoIzquierdo()));
        nodoActualCopia.setHijoDerecho(copiarReferencias(nodoActualCopia.getHijoDerecho(), nodoActualCopiando.getHijoDerecho()));
        return nodoActualCopia;
    }
    
    /*
    Implementar un metodo que retorne si un arbol binario, tiene nodos
    completos, es decir que tenga sus dos hijos diferentes de cero en el nivel 
    n
     */
    public boolean tieneNodosCompletosEnNivel(int nivel) {
        return tieneNodosCompletosEnNivel(super.raiz, nivel);
    }

    private boolean tieneNodosCompletosEnNivel(NodoBinario<K, V> nodoActual, int nivel) {
        if (NodoBinario.esVacio(super.getRaiz())) {
            return false;
        }
        if (nivel == 0) {
            return !nodoActual.esIncompleto();
        }
        boolean parIzq = tieneNodosCompletosEnNivel(nodoActual.getHijoIzquierdo(), nivel - 1);
        boolean parDer = tieneNodosCompletosEnNivel(nodoActual.getHijoDerecho(), nivel - 1);
        return parIzq && parDer;
    }

    /*
        Implementar metodo que cuente las hojas del arbol 
     */
    public int contarHojas() {
        return contarHojas(super.getRaiz());
    }

    private int contarHojas(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esVacio(nodoActual)) {
            return 0;
        }
        if (nodoActual.esHoja()) {
            return 1;
        }
        int parIzq = contarHojas(nodoActual.getHijoIzquierdo());
        int parDer = contarHojas(nodoActual.getHijoDerecho());
        return parIzq + parDer;
    }

    /*
        Implementar metodo que cuente las hojas del arbol en un determinado 
        nivel
     */
    public int contarHojasNivel(int nivel) {
        return contarHojasNivel(super.getRaiz(), nivel);
    }

    private int contarHojasNivel(NodoBinario<K, V> nodoActual, int nivel) {
        if (NodoBinario.esVacio(nodoActual)) {
            return 0;
        }
        if (nivel == 0) {
            if (nodoActual.esHoja()) {
                return 1;
            }
            return 0;
        }
        int parIzq = contarHojas(nodoActual.getHijoIzquierdo());
        int parDer = contarHojas(nodoActual.getHijoDerecho());
        return parIzq + parDer;
    }

    /*
        funcion que retorne la lista de claves por niveles del arbol menos el
        nivel n
     */
    public List<K> mostrarNodosMenosN(int nivel) {
        return mostrarNodosMenosN(super.getRaiz(), nivel);
    }

    private List<K> mostrarNodosMenosN(NodoBinario<K, V> nodoActual, int nivel) {
        List<K> result = new LinkedList<>();
        int nivelesArbol = super.nivel();
        for (int i = 0; i <= nivelesArbol; i++) {
            if (i != nivel) {
                recorrerNivel(nodoActual, i, result);
            }
        }
        return result;
    }

    private void recorrerNivel(NodoBinario<K, V> raiz, int nivel, List<K> result) {
        if (NodoBinario.esVacio(raiz) || nivel < 0) {
            return;
        }
        if (nivel == 0) {
            result.add(raiz.getClave());
        }
        if (!raiz.esVacioHijoIzquierdo()) {
            recorrerNivel(raiz.getHijoIzquierdo(), nivel - 1, result);
        }
        if (!raiz.esVacioHijoDerecho()) {
            recorrerNivel(raiz.getHijoDerecho(), nivel - 1, result);
        }

    }

    /*
    Funcion que retorne los nodos que solo tienen hijos izquierdos en el arbol
     */
    public int nodosConSoloHijoIzquierdo() {
        return nodosConSoloHijoIzquierdo(this.raiz);
    }

    private int nodosConSoloHijoIzquierdo(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esVacio(nodoActual)) {
            return 0;
        }
        if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
            return nodosConSoloHijoIzquierdo(nodoActual.
                    getHijoIzquierdo()) + nodosConSoloHijoIzquierdo(
                            nodoActual.getHijoDerecho()) + 1;
        } else {
            return nodosConSoloHijoIzquierdo(nodoActual.
                    getHijoIzquierdo()) + nodosConSoloHijoIzquierdo(
                            nodoActual.getHijoDerecho());
        }
    }

    /*
    Funcion que retorne los nodos que solo tienen hijos izquierdos en un nivel 
    especifico del arbol
     */
    public int nodosConSoloHijoIzquierdoEnNivelN(int nivel) {
        return nodosConSoloHijoIzquierdoEnNivelN(this.getRaiz(), nivel);
    }

    private int nodosConSoloHijoIzquierdoEnNivelN(NodoBinario<K, V> nodoActual, int nivel) {
        if (NodoBinario.esVacio(nodoActual)) {
            return 0;
        }
        if (nivel == 0) {
            if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
                return 1;
            }
            return 0;
        }
        int parIzq = nodosConSoloHijoIzquierdoEnNivelN(
                nodoActual.getHijoIzquierdo(), nivel - 1);
        int parDer = nodosConSoloHijoIzquierdoEnNivelN(
                nodoActual.getHijoDerecho(), nivel - 1);
        return parIzq + parDer;
    }

    /*
    Funcion que retorne los nodos que solo tienen hijos izquierdos despues de un
    nivel especifico del arbol
     */
    public int nodosConSoloHijoIzquierdoDespuesNivelN(int nivel) {
        return nodosConSoloHijoIzquierdoDespuesNivelN(this.getRaiz(), nivel);
    }

    private int nodosConSoloHijoIzquierdoDespuesNivelN(NodoBinario<K, V> nodoActual, int nivel) {
        if (NodoBinario.esVacio(nodoActual)) {
            return 0;
        }
        if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho() && nivel < 0) {
            return nodosConSoloHijoIzquierdoDespuesNivelN(
                    nodoActual.getHijoIzquierdo(), nivel - 1)
                    + nodosConSoloHijoIzquierdoDespuesNivelN(
                            nodoActual.getHijoDerecho(), nivel - 1) + 1;
        }
        return nodosConSoloHijoIzquierdoDespuesNivelN(
                nodoActual.getHijoIzquierdo(), nivel - 1)
                + nodosConSoloHijoIzquierdoDespuesNivelN(
                        nodoActual.getHijoDerecho(), nivel - 1);

    }

    /*
    Funcion que retorne true si el arbol es similar a otro
    */
    public boolean similar(ArbolBinarioBusquedaRecursivo<?, ?> posibleSimilar){
        return similar(super.getRaiz(), posibleSimilar.getRaiz());
    }
    
    private boolean similar(NodoBinario<K, V> nodoActual, NodoBinario<?, ?> nodoSimilarActual) {
        if(NodoBinario.esVacio(nodoActual) || NodoBinario.esVacio(nodoSimilarActual)){
            return NodoBinario.esVacio(nodoActual) && NodoBinario.esVacio(nodoSimilarActual);
        }
        return similar(nodoActual.getHijoIzquierdo(), 
                nodoSimilarActual.getHijoIzquierdo()) && 
                similar(nodoActual.getHijoDerecho(), 
                        nodoSimilarActual.getHijoDerecho());
    }
    
    /*
    Funcion que retorne el reflejo del arbol.
    */
    public EjerciciosABBR<K, V> reflejo(){
        EjerciciosABBR<K, V> result = new EjerciciosABBR<>(this);
        result.reflejar(result.raiz);
        return result;
    }
    
    private void reflejar(NodoBinario<K, V> nodoActual) {
        if(NodoBinario.esVacio(nodoActual)){
            return;
        }
        NodoBinario<K, V> anteriorIzquierdo = nodoActual.getHijoIzquierdo();
        NodoBinario<K, V> anteriorDerecho = nodoActual.getHijoDerecho();
        nodoActual.setHijoIzquierdo(anteriorDerecho);
        nodoActual.setHijoDerecho(anteriorIzquierdo);
        reflejar(nodoActual.getHijoIzquierdo());
        reflejar(nodoActual.getHijoDerecho());
    }
    
    /*
    Funcion que retorne si un arbol es zurdo siendo este un arbol que:
        -es vacio
        -es raiz hoja
        -cada nodo tiene mas nodos a la izquierda que a la derecha
    */
    public boolean esZurdo(){
        return esZurdo(this.raiz);
    }
    
    private boolean esZurdo(NodoBinario<K, V> nodoActual) {
        if(NodoBinario.esVacio(nodoActual)){
            return true;
        }
        if(nodoActual.esHoja()){
            return true;
        }
        int cantIzquierda = cantNodos(nodoActual.getHijoIzquierdo());
        int cantDerecha = cantNodos(nodoActual.getHijoDerecho());
        return esZurdo(nodoActual.getHijoIzquierdo()) && 
                esZurdo(nodoActual.getHijoDerecho()) && 
                (cantIzquierda > cantDerecha);
    }
    
    private int cantNodos(NodoBinario<K, V> nodoActual){
        if(NodoBinario.esVacio(nodoActual)){
            return 0;
        }
        return cantNodos(nodoActual.getHijoIzquierdo()) +
                cantNodos(nodoActual.getHijoDerecho()) + 
                1;
    }
    
    public static void main(String[] arg) {
        EjerciciosABBR<Integer, String> aBBR = new EjerciciosABBR<>();
        
        aBBR.insertar(5, "");
        aBBR.insertar(3, "");
        /*aBBR.insertar(6, "");
        aBBR.insertar(2, "");
        aBBR.insertar(4, "");
        aBBR.insertar(1, "");*/

        System.out.println(aBBR.esZurdo());
    }
}
