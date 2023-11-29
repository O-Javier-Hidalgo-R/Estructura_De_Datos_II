/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uagrm.ficct.ed1.proyecto1.arbolbusqueda.binario;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Stack;

import edu.uagrm.ficct.ed1.proyecto1.arbolbusqueda.IArbolBusqueda;

/**
 * Clase que permite la implementacion de una estructura de un arbol binario de
 * busqueda (usando solo instrucciones iterativas).
 * <pre>
 * Un arbol binario de busqueda es un arbol de busqueda que usa como criterio
 * de orden que:
 *  a) Cada nodo puede ser padre de como mucho dos hijos (binario).
 *  b) Todos los nodos a la izquierda del padre son menores que el.
 *  c) Todos los nodos a la derecha del padre son mayores que el.
 * </pre>
 *
 * @author OJavierHR
 * @param <K> tipo de dato que sera la clave de los nodos del arbol, debe de ser
 * una clase comparable.
 * @param <V> tipo de dato que sera el valor de los nodos del arbol, puede ser
 * cualquier clase.
 */
public class ArbolBinarioBusquedaIterativo<K extends Comparable<K>, V>
        implements IArbolBusqueda<K, V> {

    /**
     * Nodo inicial del arbol.
     */
    protected NodoBinario<K, V> raiz;

    /**
     * Constructor por defecto.
     */
    public ArbolBinarioBusquedaIterativo() {
    }

    /**
     * Retorna la refrencia de la raiz del arbol.
     *
     * @return raiz
     */
    public NodoBinario<K, V> getRaiz() {
        return raiz;
    }

    /**
     * Ingresa una referencia nueva a la raiz.
     *
     * @param raiz
     */
    public void setRaiz(NodoBinario<K, V> raiz) {
        this.raiz = raiz;
    }

    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        //si el arbol es vacio la raiz es el nodo a insertar
        if (esVacio()) {
            setRaiz(new NodoBinario<>(claveAInsertar,
                    valorAInsertar));
        } else {
            NodoBinario<K, V> nodoActual = getRaiz();
            NodoBinario<K, V> nodoAnterior = new NodoBinario<>();

            //Buscamos el nodo padre para el nodo a insertar...
            while (!NodoBinario.esVacio(nodoActual)) {
                nodoAnterior = nodoActual;
                if (claveAInsertar.compareTo(nodoActual.getClave()) == 0) {
                    /*Si la clave a insertar ya existe en el arbol 
                      sobreescribimos el valor asociado.*/
                    nodoActual.setValor(valorAInsertar);
                    return;
                }
                if (claveAInsertar.compareTo(nodoActual.getClave()) < 0) {
                    nodoActual = nodoActual.getHijoIzquierdo();
                } else {
                    nodoActual = nodoActual.getHijoDerecho();
                }
            }

            //Insertamos el nodo nuevo donde corresponda en relacion a su padre
            if (claveAInsertar.compareTo(
                    nodoAnterior.getClave()) < 0) {
                nodoAnterior.setHijoIzquierdo(new NodoBinario(
                        claveAInsertar, valorAInsertar));
            } else {
                nodoAnterior.setHijoDerecho(new NodoBinario(
                        claveAInsertar, valorAInsertar));
            }
        }
    }

    @Override
    public V eliminar(K claveAEliminar) throws IllegalArgumentException {
        NodoBinario<K, V> nodoEliminando = getRaiz();
        NodoBinario<K, V> nodoPadreEliminando = new NodoBinario<>();

        //Buscamor el nodo a eliminar y su padre...
        while (!NodoBinario.esVacio(nodoEliminando)
                && claveAEliminar.compareTo(nodoEliminando.getClave()) != 0) {
            nodoPadreEliminando = nodoEliminando;
            if (claveAEliminar.compareTo(nodoEliminando.getClave()) < 0) {
                nodoEliminando = nodoEliminando.getHijoIzquierdo();
            } else {
                nodoEliminando = nodoEliminando.getHijoDerecho();
            }
        }

        //Lanza una excepcion sin no existe la clave a insertar...
        if (NodoBinario.esVacio(nodoEliminando)) {
            throw new IllegalArgumentException("La clave a eliminar no existe"
                    + " en el arbol");
        }

        NodoBinario<K, V> nodoRemplazo;
        // Reservamos el valor a eliminar para retornarlo...
        V valorAEliminar = nodoEliminando.getValor();

        //caso 1) si el nodo es incompleto (solo tiene un hijo o ninguno)
        if (nodoEliminando.esIncompleto()) {
            //como es incompleto el "remplazo" es o el izquierdo o el derecho
            if (!nodoEliminando.esVacioHijoIzquierdo()) {
                nodoRemplazo = nodoEliminando.getHijoIzquierdo();
            } else {
                nodoRemplazo = nodoEliminando.getHijoDerecho();
            }
            //si el nodo a eliminar es la raiz la nueva raiz es el remplazo
            if (nodoEliminando == this.raiz) {
                setRaiz(nodoRemplazo);
                //si no hacemos que el padre del nodo a eliminar ignore el eliminado
            } else if (nodoPadreEliminando.getHijoIzquierdo()
                    == nodoEliminando) {
                nodoPadreEliminando.setHijoIzquierdo(nodoRemplazo);
            } else {
                nodoPadreEliminando.setHijoDerecho(nodoRemplazo);
            }
            //caso 2) si el nodo es completo
        } else {
            nodoPadreEliminando = nodoEliminando;

            //Buscamos el remplazo (sucesor inOrden)
            nodoRemplazo = nodoEliminando.getHijoDerecho();
            while (!nodoRemplazo.esVacioHijoIzquierdo()) {
                nodoPadreEliminando = nodoRemplazo;
                nodoRemplazo = nodoRemplazo.getHijoIzquierdo();
            }

            //eliminamos el nodo remplazo
            if (nodoPadreEliminando.getHijoDerecho() == nodoRemplazo) {
                nodoPadreEliminando.setHijoDerecho(
                        nodoRemplazo.getHijoDerecho());
            } else {
                nodoPadreEliminando.setHijoIzquierdo(
                        nodoRemplazo.getHijoDerecho());
            }

            //Sobreescribimos el nodo a eliminar con el nodo remplazo
            nodoEliminando.setValor(nodoRemplazo.getValor());
            nodoEliminando.setClave(nodoRemplazo.getClave());
        }
        return valorAEliminar;
    }

    @Override
    public V buscar(K claveABuscar) {
        //el nodo actual de la busqueda comienza en la raiz
        NodoBinario<K, V> nodoActual = getRaiz();
        //buscamos el valor con la clave a buscar
        while (!NodoBinario.esVacio(nodoActual)) {
            //retornamos el valor si encontramos la clave
            if (claveABuscar.compareTo(nodoActual.getClave()) == 0) {
                return nodoActual.getValor();
            } else if (claveABuscar.compareTo(nodoActual.getClave()) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                nodoActual = nodoActual.getHijoDerecho();
            }
        }
        //retornamos nulo si no encontramos la clave a buscar
        return null;
    }

    @Override
    public boolean contiene(K ClaveAVerificar) {
        return buscar(ClaveAVerificar) != null;
    }

    @Override
    public int size() {
        int contador = 0;
        if (!esVacio()) {
            NodoBinario<K, V> nodoActual;
            int nodoEnNivel;
            Queue<NodoBinario<K, V>> colaAux = new LinkedList();

            colaAux.add(getRaiz());
            while (!colaAux.isEmpty()) {
                nodoEnNivel = colaAux.size();
                while (nodoEnNivel > 0) {
                    nodoActual = colaAux.poll();
                    if (!nodoActual.esVacioHijoIzquierdo()) {
                        colaAux.add(nodoActual.getHijoIzquierdo());
                    }
                    if (!nodoActual.esVacioHijoDerecho()) {
                        colaAux.add(nodoActual.getHijoDerecho());
                    }
                    nodoEnNivel--;
                    contador++;
                }
            }
        }
        return contador;
    }

    @Override
    public int nivel() {
        return (this.esVacio()) ? 0 : nivel() - 1;
    }

    @Override
    public int altura() {
        int nivelActual = 0;
        if (!this.esVacio()) {
            NodoBinario<K, V> nodoActual;
            Queue<NodoBinario<K, V>> colaAux = new LinkedList<>();
            int nodosEnNivel;

            colaAux.add(getRaiz());
            do {
                nodosEnNivel = colaAux.size();
                do {
                    nodoActual = colaAux.poll();
                    if (!nodoActual.esVacioHijoIzquierdo()) {
                        colaAux.add(nodoActual.getHijoIzquierdo());
                    }
                    if (!nodoActual.esVacioHijoDerecho()) {
                        colaAux.add(nodoActual.getHijoDerecho());
                    }
                    nodosEnNivel--;
                } while (nodosEnNivel > 0);
                nivelActual++;
            } while (!colaAux.isEmpty());
        }
        return nivelActual;
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
        // si es vacio retorna lista vacia.
        if (esVacio()) {
            return recorrido;
        }

        Stack<NodoBinario<K, V>> pilaNodos = new Stack<>();
        NodoBinario<K, V> nodoActual;
        
        //colocamos en la pila la raiz
        pilaNodos.add(this.raiz);
        do {
            //desapilamos el nodo a visitar de la pila auxiliar
            nodoActual = pilaNodos.pop();
            //si el nodo visitado tiene hijo derecho apilamos en la pila aux
            if (!nodoActual.esVacioHijoDerecho()) {
                pilaNodos.add(nodoActual.getHijoDerecho());
            }
            //si el nodo visitado tiene hijo izquierdo apilamos en la pila aux
            if (!nodoActual.esVacioHijoIzquierdo()) {
                pilaNodos.add(nodoActual.getHijoIzquierdo());
            }
            //hacemos lo que tengamos que hacer con el nodo visitado
            recorrido.add(nodoActual.getClave());
            //revisamos el mientras 
        } while (!pilaNodos.isEmpty());
        return recorrido;
    }

    @Override
    public List<K> recorridoInOrden() {
        List<K> recorrido = new LinkedList<>();
        if (this.esVacio()) {
            return recorrido;
        }
        NodoBinario<K, V> nodoActual;
        Stack<NodoBinario<K, V>> pilaNodos = new Stack<>();
        inserRamaIzqAPila(this.raiz, pilaNodos);
        do {
            nodoActual = pilaNodos.pop();
            if (!nodoActual.esVacioHijoDerecho()) {
                inserRamaIzqAPila(nodoActual.getHijoDerecho(),
                        pilaNodos);
            }
            recorrido.add(nodoActual.getClave());
        } while (!pilaNodos.isEmpty());
        return recorrido;
    }

    private void inserRamaIzqAPila(NodoBinario<K, V> nodoActual, Stack<NodoBinario<K, V>> pilaNodos) {
        while (!NodoBinario.esVacio(nodoActual)) {
            pilaNodos.push(nodoActual);
            nodoActual = nodoActual.getHijoIzquierdo();
        }
    }

    //metodo alternativo
    /*public List<K> recorridoInOrden() {
    List<K> recorrido = new ArrayList<>();
    
    NodoBinario<K, V> nodoActual = getRaiz();
    Stack<NodoBinario<K, V>> pilaAux = new Stack<>();
    
    //mientras la pila no este vacia o el nodo actual no sea vacio...
    while (!pilaAux.isEmpty() || !NodoBinario.esVacio(nodoActual)) {
    //si el nodo actual no es vacio lo apilamos y vamos a la izquierda
    if (!NodoBinario.esVacio(nodoActual)) {
    pilaAux.push(nodoActual);
    nodoActual = nodoActual.getHijoIzquierdo();
    //si no desapilamos un nodo y vamos a la derecha
    } else {
    nodoActual = pilaAux.pop();
    //hacemos lo que necesitamos con el nodo
    recorrido.add(nodoActual.getClave());
    nodoActual = nodoActual.getHijoDerecho();
    }
    }
    
    return recorrido;
    }*/
    @Override
    public List<K> recorridoPostOrden() {
        List<K> recorrido = new ArrayList<>();
        if (this.esVacio()) {
            return recorrido;
        }
        Stack<NodoBinario<K, V>> pilaNodos = new Stack<>();
        NodoBinario<K, V> nodoVisitado = new NodoBinario<>();
        inserRamaIzqAPila(this.raiz, pilaNodos);
        do {
            if (!pilaNodos.peek().esVacioHijoDerecho()
                    && pilaNodos.peek().getHijoDerecho() != nodoVisitado) {
                inserRamaIzqAPila(pilaNodos.peek().getHijoDerecho(),
                        pilaNodos);
            } else {
                nodoVisitado = pilaNodos.pop();
                recorrido.add(nodoVisitado.getClave());
            }
        } while (!pilaNodos.isEmpty());
        return recorrido;
    }
    
    //metodo Alternativo
    /*public List<K> recorridoPosOrden() {
        List<K> recorrido = new LinkedList<>();
        if (!esVacio()) {
            NodoBinario<K, V> nodoActual = getRaiz();
            NodoBinario<K, V> nodoUltimoDesapilado = new NodoBinario<>();
            Stack<NodoBinario<K, V>> pilaAux = new Stack<>();
            while (!pilaAux.isEmpty() || !NodoBinario.esVacio(nodoActual)) {
                if (!NodoBinario.esVacio(nodoActual)) {
                    pilaAux.push(nodoActual);
                    nodoActual = nodoActual.getHijoIzquierdo();
                } else {
                    nodoActual = pilaAux.peek().getHijoDerecho();
                    if (NodoBinario.esVacio(nodoActual)
                            || nodoActual == nodoUltimoDesapilado) {
                        nodoUltimoDesapilado = pilaAux.pop();
                        recorrido.add(nodoUltimoDesapilado.getClave());
                        nodoActual
                                = (NodoBinario<K, V>) NodoBinario.nodoVacio();
                    }
                }
            }
        }
        return recorrido;
    }*/
}
