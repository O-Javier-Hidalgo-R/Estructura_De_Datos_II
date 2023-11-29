/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java 
 * to edit this template
 */
package edu.uagrm.ficct.ed1.proyecto1.arbolbusqueda.binario;

import edu.uagrm.ficct.ed1.proyecto1.arbolbusqueda.IArbolBusqueda;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Clase que permite implementar un arbol de busqueda binario generico usando
 * solo algoritmos recursivos.
 *
 * @author OJavierHR
 * @param <K> Tipo de dato que llevaran los nodos del arbol como claves.
 * @param <V> Tipo de dato que llevaran los nodos del arbol como valores.
 */
public class ArbolBinarioBusquedaRecursivo<K extends Comparable<K>, V>
        implements IArbolBusqueda<K, V> {

    /**
     * Nodo inicial del arbol.
     */
    protected NodoBinario<K, V> raiz;

    /**
     * Constructor por defecto.
     */
    public ArbolBinarioBusquedaRecursivo() {

    }

    /**
     * Constructor que inicializa los valores del arbol con los recorridos
     * previos de un arbol ya establecidos.
     *
     * @param clavesInOrden Lista de claves in-orden.
     * @param valoresInOrden Lista de valores in-orden.
     * @param clavesNoInOrden Lista de valores pre-orden o post-Orden.
     * @param valoresNoInOrden Lista de valores pre-orden o post-orden.
     * @param usandoPreOrden Valor booleano que es true si se usa el recorrido
     * pre-orden para reconstruir o false si se usa el recorrido post-orden.
     */
    public ArbolBinarioBusquedaRecursivo(List<K> clavesInOrden,
            List<V> valoresInOrden, List<K> clavesNoInOrden,
            List<V> valoresNoInOrden, boolean usandoPreOrden) {
        if (usandoPreOrden) {
            this.raiz = reconstruirConPreOrden(clavesInOrden, valoresInOrden,
                    clavesNoInOrden, valoresNoInOrden);
        } else {
            this.raiz = reconstruirConPostOrden(clavesInOrden, valoresInOrden,
                    clavesNoInOrden, valoresNoInOrden);
        }
    }

    /**
     * Operacion auxiliar usada en el constructor:<br>
     * &nbsp;&nbsp;&nbsp;ArbolBinarioBusquedaRecursivo(clavesInOrden, List
     * valoresInOrden, List clavesNoInOrden, List valoresNoInOrden, boolean
     * usandoPreOrden). <br>
     * Se encarga de la recontrucion en caso de hacerlo desde un recorrido
     * pre-orden.
     *
     * @param clavesInOrden Lista con las claves in-orden.
     * @param valoresInOrden Lista con los valores in-orden.
     * @param clavesPreOrden Lista con las claves pre-orden.
     * @param valoresPreOrden Lista con los valores pre-orden.
     * @return Arbol binario reconstruido con el recorrido pre-preorden.
     */
    private NodoBinario<K, V> reconstruirConPreOrden(List<K> clavesInOrden,
            List<V> valoresInOrden, List<K> clavesPreOrden,
            List<V> valoresPreOrden) {
        if (clavesInOrden.isEmpty()) {
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        } else {
            K clavePadre = clavesPreOrden.get(0);
            V valorPadre = valoresPreOrden.get(0);
            int posicionClaveInOrden = indiceDeClave(clavePadre,
                    clavesInOrden);
            //parte izquierda
            List<K> clavesInOrdeIzq = clavesInOrden.subList(0,
                    posicionClaveInOrden);
            List<V> valoresInOrdeIzq = valoresInOrden.subList(0,
                    posicionClaveInOrden);
            List<K> clavesPreOrdeIzq = clavesPreOrden.subList(1,
                    posicionClaveInOrden + 1);
            List<V> valoresPreOrdeIzq = valoresPreOrden.subList(1,
                    posicionClaveInOrden + 1);
            //parte derecha
            List<K> clavesInOrdeDer = clavesInOrden.subList(
                    posicionClaveInOrden + 1, clavesInOrden.size());
            List<V> valoresInOrdeDer = valoresInOrden.subList(
                    posicionClaveInOrden + 1, clavesInOrden.size());
            List<K> clavesPreOrdeDer = clavesPreOrden.subList(
                    posicionClaveInOrden + 1, clavesInOrden.size());
            List<V> valoresPreOrdeDer = valoresPreOrden.subList(
                    posicionClaveInOrden + 1, clavesInOrden.size());
            //armamos
            NodoBinario<K, V> nodoPadre = new NodoBinario<>(clavePadre,
                    valorPadre);
            nodoPadre.setHijoIzquierdo(reconstruirConPreOrden(
                    clavesInOrdeIzq, valoresInOrdeIzq,
                    clavesPreOrdeIzq, valoresPreOrdeIzq)
            );
            nodoPadre.setHijoDerecho(reconstruirConPreOrden(
                    clavesInOrdeDer, valoresInOrdeDer,
                    clavesPreOrdeDer, valoresPreOrdeDer)
            );
            return nodoPadre;
        }
    }

    /**
     * Operacion auxiliar usada en el constructor:<br>
     * &nbsp;&nbsp;&nbsp;ArbolBinarioBusquedaRecursivo(clavesInOrden, List
     * valoresInOrden, List clavesNoInOrden, List valoresNoInOrden, boolean
     * usandoPreOrden). <br>
     * Se encarga de la recontrucion en caso de hacerlo desde un recorrido
     * post-orden.
     *
     * @param clavesInOrden Lista con las claves in-orden.
     * @param valoresInOrden Lista con los valores in-orden.
     * @param clavesPostOrden Lista con las claves post-orden.
     * @param valoresPostOrden Lista con los valores post-orden.
     * @return Arbol binario reconstruido con el recorrido pre-preorden.
     */
    private NodoBinario<K, V> reconstruirConPostOrden(List<K> clavesInOrden,
            List<V> valoresInOrden, List<K> clavesPostOrden,
            List<V> valoresPostOrden) {
        if (clavesInOrden.isEmpty()) {
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        } else {
            K clavePadre = clavesPostOrden.get(clavesPostOrden.size() - 1);
            V valorPadre = valoresPostOrden.get(valoresPostOrden.size() - 1);
            int posicionClaveInOrden = indiceDeClave(clavePadre,
                    clavesInOrden);
            //parte izquierda
            List<K> clavesInOrdeIzq = clavesInOrden.subList(0,
                    posicionClaveInOrden);
            List<V> valoresInOrdeIzq = valoresInOrden.subList(0,
                    posicionClaveInOrden);
            List<K> clavesPostOrdeIzq = clavesPostOrden.subList(0,
                    posicionClaveInOrden);
            List<V> valoresPostOrdenIzq = valoresPostOrden.subList(0,
                    posicionClaveInOrden);
            //parte derecha
            List<K> clavesInOrdeDer = clavesInOrden.subList(
                    posicionClaveInOrden + 1, clavesInOrden.size());
            List<V> valoresInOrdeDer = valoresInOrden.subList(
                    posicionClaveInOrden + 1, clavesInOrden.size());
            List<K> clavesPostOrdenDer = clavesPostOrden.subList(
                    posicionClaveInOrden, clavesInOrden.size() - 1);
            List<V> valoresPostOrdenDer = valoresPostOrden.subList(
                    posicionClaveInOrden, clavesInOrden.size() - 1);
            //armamos
            NodoBinario<K, V> nodoPadre = new NodoBinario<>(clavePadre,
                    valorPadre);
            nodoPadre.setHijoIzquierdo(reconstruirConPostOrden(
                    clavesInOrdeIzq, valoresInOrdeIzq,
                    clavesPostOrdeIzq,
                    valoresPostOrdenIzq));
            nodoPadre.setHijoDerecho(reconstruirConPostOrden(
                    clavesInOrdeDer, valoresInOrdeDer,
                    clavesPostOrdenDer,
                    valoresPostOrdenDer));
            return nodoPadre;
        }
    }

    /**
     * Operacion auxiliar usada en el constructor:<br>
     * &nbsp;&nbsp;&nbsp;ArbolBinarioBusquedaRecursivo(clavesInOrden, List
     * valoresInOrden, List clavesNoInOrden, List valoresNoInOrden, boolean
     * usandoPreOrden). <br>
     * Se encarga de señalar el indice de una clave dentro de una lista, en caso
     * de no encontrar dicha clave se retorna el valor invalido de -1.
     *
     * @param claveABuscar Valor de la clave a buscar en la lista.
     * @param listaDeClaves Lista con las claves en la que se quiere buscar.
     * @return Indice en el que se encontro la clave, -1 si no se encontro.
     */
    private int indiceDeClave(K claveABuscar, List<K> listaDeClaves) {
        for (int i = 0; i < listaDeClaves.size(); i++) {
            K claveActual = listaDeClaves.get(i);
            if (claveActual.compareTo(claveABuscar) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna la raiz del arbol.
     *
     * @return Muestra la raiz (nodo inicial del arbol).
     */
    public NodoBinario<K, V> getRaiz() {
        return raiz;
    }

    /**
     * Ingresa un valor a la raiz del arbol.
     *
     * @param raiz valor a insertar como raiz del arbol.
     */
    public void setRaiz(NodoBinario<K, V> raiz) {
        this.raiz = raiz;
    }

    @Override
    public void insertar(K claveAInsertar, V ValorAInsertar) {
        setRaiz(insertar(getRaiz(), new NodoBinario<>(
                claveAInsertar, ValorAInsertar)));
    }

    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;insertar(ClaveAInsertar, ValorAInsertar). <br>
     *
     * @param nodoActual Nodo que se usara para desplazarce por el arbol.
     * @param nodoAInsertar Nodo nuevo a insertar en el arbol.
     * @return arbol con el nodo insertardo.
     */
    private NodoBinario<K, V> insertar(NodoBinario<K, V> nodoActual,
            NodoBinario<K, V> nodoAInsertar) {
        if (NodoBinario.esVacio(nodoActual)) {
            return nodoAInsertar;
        }
        if (nodoAInsertar.getClave().compareTo(nodoActual.getClave()) == 0) {
            nodoActual.setValor(nodoAInsertar.getValor());
            return nodoActual;
        }
        if (nodoAInsertar.getClave().compareTo(nodoActual.getClave()) < 0) {
            nodoActual.setHijoIzquierdo(insertar(
                    nodoActual.getHijoIzquierdo(), nodoAInsertar));
        } else {
            nodoActual.setHijoDerecho(insertar(
                    nodoActual.getHijoDerecho(), nodoAInsertar));
        }
        return nodoActual;
    }

    @Override
    public V eliminar(K claveAEliminar) throws IllegalArgumentException {
        NodoBinario<K, V> nodoEliminado = new NodoBinario<>();
        this.raiz = eliminar(raiz, claveAEliminar, nodoEliminado);
        return nodoEliminado.getValor();
    }

    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;eliminar(K). <br>
     *
     * @param nodoActual Nodo que permite desplazarnos por el arbol.
     * @param claveAEliminar Clave con el valor a buscar en los nodos para
     * eliminar.
     * @return Arbol binario de busqueda con el nodo eliminado.
     */
    private NodoBinario<K, V> eliminar(NodoBinario<K, V> nodoActual,
            K claveAEliminar, NodoBinario<K, V> nodoEliminado) {
        if (NodoBinario.esVacio(nodoActual)) {
            throw new IllegalArgumentException("La clave a eliminar no existe "
                    + "en el arbol");
        }
        if (claveAEliminar.compareTo(nodoActual.getClave()) == 0) {
            if(nodoEliminado.getClave() == null){
                nodoEliminado.setClave(nodoActual.getClave());
                nodoEliminado.setValor(nodoActual.getValor());
            }
            if (nodoActual.esIncompleto()) {
                return (!nodoActual.esVacioHijoIzquierdo()) ? nodoActual.getHijoIzquierdo() : nodoActual.getHijoDerecho();
            }
            NodoBinario<K, V> nodoRemplazo = nodoMenor(nodoActual.getHijoDerecho());
            nodoActual.setHijoDerecho(eliminar(nodoActual.getHijoDerecho(), nodoRemplazo.getClave(), nodoEliminado));
            nodoActual.setClave(nodoRemplazo.getClave());
            nodoActual.setValor(nodoRemplazo.getValor());
            return nodoActual;
        }
        if(claveAEliminar.compareTo(nodoActual.getClave())<0){
            nodoActual.setHijoIzquierdo(eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar, nodoEliminado));
        }else{
            nodoActual.setHijoDerecho(eliminar(nodoActual.getHijoDerecho(), claveAEliminar, nodoEliminado));
        }
        return nodoActual;
    }
    
    /**
     * Operacion que permite encontrar el nodo menor desde un nodo determinando.
     *
     * @param nodoActual Nodo desde el que se quiere buscar el menor.
     * @return Nodo menor encontrado.
     */
    protected NodoBinario<K, V> nodoMenor(NodoBinario<K, V> nodoActual) {
        if (nodoActual.esVacioHijoIzquierdo()) {
            return nodoActual;
        }
        return nodoMenor(nodoActual.getHijoIzquierdo());
    }
    
    @Override
    public V buscar(K claveABuscar) {
        return buscar(getRaiz(), claveABuscar);
    }
    
    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;buscar(ClaveABuscar). <br>
     *
     * @param nodoActual Nodo que se usa para desplazarse por el arbol.
     * @param claveABuscar Clave que se quiere encontrar en el arbol.
     * @return el valor asocioado a la clave.
     */
    public V buscar(NodoBinario<K, V> nodoActual, K claveABuscar) {
        if (NodoBinario.esVacio(nodoActual)) {
            return null;
        } else if (claveABuscar.compareTo(nodoActual.getClave()) == 0) {
            return nodoActual.getValor();
        } else if (claveABuscar.compareTo(nodoActual.getClave()) < 0) {
            return buscar(nodoActual.getHijoIzquierdo(),
                    claveABuscar);
        } else {
            return buscar(nodoActual.getHijoDerecho(), claveABuscar);
        }
    }
    
    @Override
    public boolean contiene(K clave) {
        return ArbolBinarioBusquedaRecursivo.this.buscar(clave) != null;
    }
    
    @Override
    public int size() {
        return size(getRaiz());
    }

    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;size().<br>
     *
     * @param nodoActual Nodo que permite desplazarce por el arbol.
     * @return tamaño del arbolBB.
     */
    private int size(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esVacio(nodoActual)) {
            return 0;
        }
        return size(nodoActual.getHijoIzquierdo())
                + size(nodoActual.getHijoDerecho())
                + 1;
    }
    
    @Override
    public int nivel() {
        return nivel(raiz);
    }

    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;nivel(). <br>
     *
     * @param nodoActual Nodo que permite desplazarse por el arbol.
     * @return cantidad de niveles de arbol.
     */
    protected int nivel(NodoBinario<K, V> nodoActual) {
        if (!NodoBinario.esVacio(nodoActual)) {
            int izq = nivel(nodoActual.getHijoIzquierdo()) + 1;
            int der = nivel(nodoActual.getHijoDerecho()) + 1;
            if (izq > der) {
                return izq;
            } else {
                return der;
            }
        }
        return -1;
    }
    
    /**
     * Miestra la cantidad de generaciones del arbol contando a partir de uno
     * desde la raiz del arbol.
     *
     * @return Altura del arbol.
     */
    @Override
    public int altura() {
        return nivel(raiz) + 1;
    }
    
    @Override
    public boolean esVacio() {
        return NodoBinario.esVacio(raiz);
    }
    
    @Override
    public void vaciar() {
        setRaiz((NodoBinario<K, V>) NodoBinario.nodoVacio());
    }
    
    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        Queue<NodoBinario<K, V>> colaAux = new LinkedList<>();
        NodoBinario<K, V> nodoActual;
        /* Si necesitamos saber el nivel declaramos dos variables
            Una para identificar el nivel
            Una para contar los nodos en el nivel que estamos 
         */
        //int nodosEnNivel;
        //int nivel = 0;

        //colocamos en una cola la raiz si el arbol no esta vacio.
        if (!this.esVacio()) {
            colaAux.add(getRaiz());
        }
        //mientras la cola no este vacia...
        while (!colaAux.isEmpty()) {
            //Contamos los nodos en el nivel, que son los que estan en la cola
            //nodosEnNivel = colaAux.size();
            //mientras no quitemos los nodos del nivel actual no vamos al prox
            //while (nodosEnNivel > 0) {
            //visitamos el nodo en el tope de la cola y lo quitamos de ella
            nodoActual = colaAux.poll();
            //si tienen nodos hijos los agregamos en orden a la cola
            if (!nodoActual.esVacioHijoIzquierdo()) {
                colaAux.add(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esVacioHijoDerecho()) {
                colaAux.add(nodoActual.getHijoDerecho());
            }
            //indicamos que eliminamos un nodo del nivel actual de la cola
            //nodosEnNivel--;
            //hacemos lo que necesitamos con el nodo visitado
            recorrido.add(nodoActual.getClave());
            //}
            //indicamos que nos movemos al siguiente nivel
            //nivel++;
        }
        return recorrido;
    }

    @Override
    public List<K> recorridoPreOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnPreOrden(getRaiz(), recorrido);
        return recorrido;
    }

    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;recorridoPreOrden(). <br>
     *
     * @param nodoActual Nodo que permite desplazarnos por el arbol.
     * @param recorrido Lista que se usa para almacenar el orden en que se
     * visitaron los nodos.
     */
    private void recorridoEnPreOrden(NodoBinario<K, V> nodoActual,
            List<K> recorrido) {
        if (NodoBinario.esVacio(nodoActual)) {
            return;
        }
        recorrido.add(nodoActual.getClave());
        recorridoEnPreOrden(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPreOrden(nodoActual.getHijoDerecho(), recorrido);
    }
    
    @Override
    public List<K> recorridoInOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnInOrdenR(getRaiz(), recorrido);
        return recorrido;
    }

    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;recorridoInOrden(). <br>
     *
     * @param nodoActual Nodo que permite desplazarnos por el arbol.
     * @param recorrido Lista que se usa para almacenar el orden en que se
     * visitaron los nodos.
     */
    private void recorridoEnInOrdenR(NodoBinario<K, V> raiz,
            List<K> recorrido) {
        if (NodoBinario.esVacio(raiz)) {
            return;
        }
        recorridoEnInOrdenR(raiz.getHijoIzquierdo(), recorrido);
        recorrido.add(raiz.getClave());
        recorridoEnInOrdenR(raiz.getHijoDerecho(), recorrido);
    }
    
    @Override
    public List<K> recorridoPostOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnPosOrdenR(getRaiz(), recorrido);
        return recorrido;
    }

    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;recorridoPostOrden(). <br>
     *
     * @param nodoActual Nodo que permite desplazarnos por el arbol.
     * @param recorrido Lista que se usa para almacenar el orden en que se
     * visitaron los nodos.
     */
    private void recorridoEnPosOrdenR(NodoBinario<K, V> raiz,
            List<K> recorrido) {
        if (NodoBinario.esVacio(raiz)) {
            return;
        }
        recorridoEnPosOrdenR(raiz.getHijoIzquierdo(), recorrido);
        recorridoEnPosOrdenR(raiz.getHijoDerecho(), recorrido);
        recorrido.add(raiz.getClave());
    }
}
