/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.binario;

/**
 * Clase de arbol AVL, es un tipo de arbol de busqueda binario pero que mantiene
 * las ramas equilibradas para optimizar la busqueda en el evitando la
 * deformacion de la estructura. <br>
 * Se puede decir que un arbol de busqueda avl es un arbol de busqueda binario
 * pero que al insertar o eliminar un nodo se asegura que las ramas sigan 
 * un criterio de "equilibrio."
 * 
 * @author OJavierHR
 * @param <K> Tipo de dato (comparable) que llevaran los nodos del arbol como 
 * claves.
 * @param <V> Tipo de dato que llevaran los nodos del arbol como dato o datos.
 */
public class ArbolAVL<K extends Comparable<K>, V> extends ArbolBinarioBusquedaRecursivo<K, V> {
    
    /**
     * 
     * @param clave
     * @param valor 
     */
    @Override
    public void insertar(K clave, V valor){
        setRaiz(insertar(getRaiz(), clave, valor));
    }

    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;insertar(clave, valor). <br>
     * 
     * @param nodoActual Nodo que permite desplazarce por el arbol.
     * @param claveAInsertar Clave del nuevo nodo a insertar. 
     * @param valorAInsertar Valor del nuevo nodo a insertar.
     * @return Raiz con el nodo nuevo ya insertado.
     */
    private NodoBinario<K, V> insertar(NodoBinario<K, V> nodoActual, 
            K claveAInsertar, V valorAInsertar) {
        if (NodoBinario.esVacio(nodoActual)) {
            return new NodoBinario<>(claveAInsertar, valorAInsertar);
        } else if (claveAInsertar.compareTo(nodoActual.getClave()) == 0) {
            nodoActual.setValor(valorAInsertar);
            return nodoActual;
        } else {
            if (claveAInsertar.compareTo(nodoActual.getClave()) < 0) {
                nodoActual.setHijoIzquierdo(insertar(
                        nodoActual.getHijoIzquierdo(), claveAInsertar, 
                        valorAInsertar));
            } else {
                nodoActual.setHijoDerecho(insertar(
                        nodoActual.getHijoDerecho(), 
                        claveAInsertar, valorAInsertar));
            }
            return balancear(nodoActual);
        }
    }
    
    /**
     * 
     * @param clave
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public V eliminar(K clave) throws IllegalArgumentException{
        NodoBinario<K, V> nodoEliminado = new NodoBinario<>();
        setRaiz(eliminar(getRaiz(), clave, nodoEliminado));
        if(NodoBinario.esVacio(nodoEliminado)){
            throw new IllegalArgumentException("la clave: " + clave + " no "
                    + "se encuentra en el arbol");
        }
        return nodoEliminado.getValor();
    }
    
    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;eliminar(clave). <br>
     * 
     * @param nodoActual Nodo que permite desplazarce por el arbol.
     * @param claveAInsertar Clave del nuevo nodo a insertar. 
     * @param valorAInsertar Valor del nuevo nodo a insertar.
     * @return Raiz con el nodo nuevo ya insertado.
     */
    private NodoBinario<K, V> eliminar(NodoBinario<K, V> nodoActual, K clave, NodoBinario<K, V> nodoEliminado) {
        if (!NodoBinario.esVacio(nodoActual)) {
            if (clave.compareTo(nodoActual.getClave()) == 0) {
                nodoEliminado = nodoActual;
                if (nodoActual.esIncompleto()) {
                    if (!nodoActual.esVacioHijoIzquierdo()) {
                        return nodoActual.getHijoIzquierdo();
                    } else {
                        return nodoActual.getHijoDerecho();
                    }
                } else {
                    NodoBinario<K, V> nodoRemplazo = nodoMenor(nodoActual.getHijoDerecho());
                    nodoActual.setClave(nodoRemplazo.getClave());
                    nodoActual.setValor(nodoRemplazo.getValor());
                    nodoActual.setHijoDerecho(eliminar(nodoActual.getHijoDerecho(), nodoRemplazo.getClave(), nodoEliminado));
                }
            } else if (clave.compareTo(nodoActual.getClave()) < 0) {
                nodoActual.setHijoIzquierdo(eliminar(nodoActual.getHijoIzquierdo(), clave, nodoEliminado));
            } else {
                nodoActual.setHijoDerecho(eliminar(nodoActual.getHijoDerecho(), clave, nodoEliminado));
            }
        }
        return balancear(nodoActual);
    }

    /**
     * 
     * @param nodoActual
     * @return 
     */
    private NodoBinario<K, V> balancear(NodoBinario<K, V> nodoActual) {
        
        final int LIM_SUP = 1;
        final int LIM_INF = -1;
        
        if (!NodoBinario.esVacio(nodoActual)) {
            int alturaIzq = nivelR(nodoActual.getHijoIzquierdo());
            int alturaDer = nivelR(nodoActual.getHijoDerecho());
            int diferencia = alturaIzq - alturaDer;
            if ((diferencia > LIM_SUP)) {
                NodoBinario<K, V> hijoIzquierdo = nodoActual.getHijoIzquierdo();
                alturaIzq = nivelR(hijoIzquierdo.getHijoIzquierdo());
                alturaDer = nivelR(hijoIzquierdo.getHijoDerecho());
                if (alturaDer > alturaIzq) {
                    return rotacionDobleDerecha(nodoActual);
                } else {
                    return rotacionSimpleDerecha(nodoActual);
                }
            } else if (diferencia < LIM_INF) {
                NodoBinario<K, V> hijoDerecho = nodoActual.getHijoDerecho();
                alturaIzq = nivelR(hijoDerecho.getHijoIzquierdo());
                alturaDer = nivelR(hijoDerecho.getHijoDerecho());
                if (alturaIzq > alturaDer) {
                    return rotacionDobleIzquierda(nodoActual);
                } else {
                    return rotacionSimpleizquierda(nodoActual);
                }
            }
        }
        return nodoActual;
    }

    private NodoBinario<K, V> rotacionDobleDerecha(NodoBinario<K, V> nodoActual) {
        nodoActual.setHijoIzquierdo(rotacionSimpleizquierda(nodoActual.getHijoIzquierdo()));
        return rotacionSimpleDerecha(nodoActual);
    }
    
    private NodoBinario<K, V> rotacionDobleIzquierda(NodoBinario<K, V> nodoActual) {
        nodoActual.setHijoDerecho(rotacionDobleDerecha(nodoActual.getHijoDerecho()));
        return rotacionSimpleizquierda(nodoActual);
    }

    private NodoBinario<K, V> rotacionSimpleDerecha(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRota = nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoQueRota.getHijoDerecho());
        nodoQueRota.setHijoDerecho(nodoActual);
        return nodoQueRota;
    }

    private NodoBinario rotacionSimpleizquierda(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRota = nodoActual.getHijoDerecho();
        nodoActual.setHijoDerecho(nodoQueRota.getHijoIzquierdo());
        nodoQueRota.setHijoIzquierdo(nodoActual);
        return nodoQueRota;
    }
}
