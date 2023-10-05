/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java 
 * to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.binario;

import bo.uagrm.ficct.ed2.arbolbusqueda.IArbolBusqueda;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite implementar un arbol de busqueda binario generico usando
 * solo algoritmos recursivos.<br>
 * Se les llama binario por que son arboles de busqueda con las siguientes
 * particularidades:
 * <p>
 * &nbsp;&nbsp;&nbsp;<b>a)</b> Todos los nodos solo pueden tener dos hijos uno
 * "derecho" y uno "izquierdo"<br>
 * &nbsp;&nbsp;&nbsp;<b>b)</b> Todos los nodos a la izquierda de uno en
 * especifico deben de ser menores al mismo.<br>
 * &nbsp;&nbsp;&nbsp;<b>c)</b> Todos los nodos a la derecha de uno en especifico
 * deben de ser mayores al mismo.<p>
 * ejemplo:
 * <p>
 * <img src = "imagenes/arbolBinario1.png" style="width:600px" alt = "ejemplo de
 * arbol binario">
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
     * previos de un arbol ya establecidos. <br>
     * Para ello se necesitan de dos recorridos en profundidad, uno in-orden y y
     * uno pre o post orden.<p>
     * En caso de reconstruir con pre-Orden el algoritmo es el siguiente:<br>
     * &nbsp;&nbsp;&nbsp;<b>a)</b> Se inserta como el padre un nodo nuevo con la
     * clave y valor que esten primero en el recorrido in-orden.<br>
     * &nbsp;&nbsp;&nbsp;<b>b)</b> Se asigna a la izquierda el sub arbol que
     * resultara de las sub-listas de las claves y valores de los sub-recorridos
     * in-orden y preorden: <br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b> Sub recorrido in-orden:
     * Desde inicio hasta un nodo antes del que se inserto como nodo padre en el
     * paso anterior.<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b> Sub recorrido pre-orden:
     * Desde el segundo elemento de la lista y que es del mismo tamaño que la
     * sublista in-orden.<br>
     * &nbsp;&nbsp;&nbsp;<b>c)</b> Se asigna a la derecha el sub arbol que
     * resultara de las sub-listas de las claves y valores de los sub-recorridos
     * in-orden y preorden:<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b> Sub recorrido in-orden:
     * Desde el nodo posterior al nodo padre insertado en el anterior paso "a)"
     * el ultimo nodo.<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b> Sub recorrido pre-orden:
     * Desde el indice resultado del tamaño de la sublista usada para la parte
     * izquierda +1, hasta el final.<br>
     * &nbsp;&nbsp;&nbsp;<b>d)</b> Repetir los anteriores pasos hasta que las
     * listas se vacien.<br>
     * Un ejemplo grafico seria:
     * <p>
     * <img src = "imagenes/arbolBinario2.png" style="width:600px" alt = "
       ejemplo de reconstruccion por recorrido pre-orden"> <p>
     * En caso de reconstruir con post-Orden el algoritmo es el siguiente:<br>
     * &nbsp;&nbsp;&nbsp;<b>a)</b> Se inserta como el padre un nodo nuevo con la
     * clave y valor que esten ultimos en el recorrido post-orden.<br>
     * &nbsp;&nbsp;&nbsp;<b>b)</b> Se asigna a la izquierda el sub arbol que
     * resultara de las sub-listas de las claves y valores de los sub-recorridos
     * in-orden y preorden:<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b> Sub recorrido in-orden:
     * Desde inicio hasta un nodo antes del que se inserto como nodo padre en el
     * paso anterior.<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b> Sub recorrido pre-orden:
     * Desde el primer elemento de la lista y que es del mismo tamaño que la
     * sublista in-orden.<br>
     * &nbsp;&nbsp;&nbsp;<b>c)</b> Se asigna a la derecha el sub arbol que
     * resultara de las sub-listas de las claves y valores de los sub-recorridos
     * in-orden y preorden:<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b> Sub recorrido in-orden:
     * Desde el nodo posterior al nodo padre insertado en el anterior paso "a)"
     * el ultimo nodo.<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b> Sub recorrido pre-orden:
     * Desde el indice resultado del tamaño de la sublista usada para la parte
     * izquierda, hasta el penultimo nodo.<br>
     * &nbsp;&nbsp;&nbsp;<b>d)</b> Repetir los anteriores pasos hasta que las
     * listas se vacien.<br>
     * Un ejemplo grafico seria: <p>
     * <img src = "imagenes/arbolBinario3.png" style="width:600px" alt = "
       ejemplo de reconstruccion por recorrido post-orden">
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

    /**
     * Vacia el arbol, elimina todos los valores dentro del arbol.
     */
    @Override
    public void vaciar() {
        setRaiz((NodoBinario<K, V>) NodoBinario.nodoVacio());
    }

    /**
     * Verifica si el arbol esta vacio.
     *
     * @return True si el arbol esta vacio.
     */
    @Override
    public boolean esVacio() {
        return NodoBinario.esVacio(raiz);
    }

    /**
     * Inserta un nuevo nodo (por sus parametros) en el lugar que le corresponda
     * en funcion de la clave, en caso de ya estar en el arbol la clave
     * sobreescribe el valor del nodo. <br>
     * El algoritmo consiste en preguntar en cada nodo si la clave a insertar es
     * menor, mayor o igual para tomar las siguientes acciones:
     * <p>
     * &nbsp;&nbsp;&nbsp;<b>a) Si es menor: </b> El subarbol izquierdo sera el
     * producto del resultado de la recursion para el nodo izquierdo.<br>
     * &nbsp;&nbsp;&nbsp;<b>b) Si es mayor: </b> El subarbol derecho sera el
     * producto del resultado de la recursion para el nodo derecho.<br>
     * &nbsp;&nbsp;&nbsp;<b>c) Si es igual: </b> Sobreescribimos el valor y
     * termina la recursion.<br>
     * &nbsp;&nbsp;&nbsp;<b>d) Si es nulo: </b> Retornamos el nodo nuevo.<br>
     * Graficamente podria representarse como:
     * <p>
     * <img src = "imagenes/arbolBinario4.png" style="width:600px" alt = "
     * ejemplo de reconstruccion por recorrido post-orden">
     *
     * @param claveAInsertar valor de la clave del nodo a insertar.
     * @param ValorAInsertar valor del nodo a insertar.
     */
    @Override
    public void insertar(K claveAInsertar, V ValorAInsertar) {
        setRaiz(insertarR(getRaiz(), new NodoBinario<>(
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
    private NodoBinario<K, V> insertarR(NodoBinario<K, V> nodoActual,
            NodoBinario<K, V> nodoAInsertar) {
        if (NodoBinario.esVacio(nodoActual)) {
            return nodoAInsertar;
        }
        if (nodoAInsertar.getClave().compareTo(nodoActual.getClave()) == 0) {
            nodoActual.setValor(nodoAInsertar.getValor());
        }
        if (nodoAInsertar.getClave().compareTo(nodoActual.getClave()) < 0) {
            nodoActual.setHijoIzquierdo(insertarR(
                    nodoActual.getHijoIzquierdo(), nodoAInsertar));
        } else {
            nodoActual.setHijoDerecho(insertarR(
                    nodoActual.getHijoDerecho(), nodoAInsertar));
        }
        return nodoActual;
    }

    /**
     * Funcion que retorna el valor de un nodo buscado por valor de la clave,
     * retorna null si no existe. <br>
     * El funcionamiento es similar al de insercion, se desplaza por los nodos
     * comenzando por la raiz en criterio de busqueda hasta hallar un nodo con
     * la clave a buscar o de llegar hasta uno vacio (no existiendo la clave)
     * retorna nulo.
     *
     * @param claveABuscar Clave a buscar dentro del arbol.
     * @return Valor que se encuentre en el nodo a buscar asociado a la clave,
     * null si no se encunetra ninguno.
     */
    @Override
    public V buscar(K claveABuscar) {
        return buscarR(getRaiz(), claveABuscar);
    }

    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;buscar(ClaveABuscar). <br>
     *
     * @param nodoActual Nodo que se usa para desplazarse por el arbol.
     * @param claveABuscar Clave que se quiere encontrar en el arbol.
     * @return el valor asocioado a la clave.
     */
    public V buscarR(NodoBinario<K, V> nodoActual, K claveABuscar) {
        if (NodoBinario.esVacio(nodoActual)) {
            return null;
        } else if (claveABuscar.compareTo(nodoActual.getClave()) == 0) {
            return nodoActual.getValor();
        } else if (claveABuscar.compareTo(nodoActual.getClave()) < 0) {
            return buscarR(nodoActual.getHijoIzquierdo(),
                    claveABuscar);
        } else {
            return buscarR(nodoActual.getHijoDerecho(), claveABuscar);
        }
    }

    /**
     * Valida si en el arbol existe un nodo con la clave especificada por
     * parametros. <br>
     * Para la validacion se aprovecha del algoritmo de busqueda, para ello se
     * retorna al valor booleano para busquedas con resultado diferente de vacio
     * (encontro la clave).
     *
     * @param clave Clave a verificar existencia.
     * @return True si la clave ya existe en el arbol.
     */
    @Override
    public boolean contiene(K clave) {
        return buscar(clave) != null;
    }

    /**
     * Retorna el recorrido que resulta del desplazamiento por los nodos
     * respetando como criterio el numero de nivel y el orden de izquierda a
     * derecha. <br>
     * El algoritmo para el recorrido por niveles a diferencia de los
     * de profundidad usa como estructura auxiliar una cola y no una pila por lo
     * que no se puede implementar recursivamente de una manera mas directa.<br>
     * Para realizar este recorrido se debe:<br>
     * &nbsp;&nbsp;&nbsp;<b>1er.-</b> Calcular la cantidad de niveles que tiene
     * el arbol.<br>
     * &nbsp;&nbsp;&nbsp;<b>2do.-</b> Iterativamente señalar los niveles a
     * recorrer individualmente mediante recursion donde para recorrer el nivel:
     * <br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b> Se declara una variable para
     * indicar el nivel que se quiere visitar.<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b> En caso de llegar a un nodo
     * nulo se entiende que en la rama no existe nodo en el nivel y no es
     * necesario avanzar recursivamente.<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b> Cuando finalemente la
     * variable de nivel llegue a cero se visita el nodo.<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b> Se avanza recursivamente por
     * el arbol, primero por el hijo izquierdo y luego por el derecho pero con
     * cada nivel avanzado se descuenta en la variable de "nivel".<br>
     * Graficamente se veria algo asi:
     * <p>
     * <img src = "imagenes/arbolBinario5.png" style="width:900px" alt = "
     * ejemplo de recorrido por niveles">
     *
     * @return lista con el recorrido.
     */
    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        int nivel = nivel();
        for (int i = 0; i <= nivel; i++) {
            recorrerNivel(getRaiz(), i, recorrido);
        }
        return recorrido;
    }

    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;recorridoPorNiveles(). <br>
     *
     * @param nodoActual Nodo que permite desplazarnos por el arbol.
     * @param nivel Variable de nivel que permite saber cuando se llega a un
     * nodo del nivel que se quiere visitar.
     * @param recorrido Lista que se usa para almacenar el orden en que se
     * visitaron los nodos.
     */
    private void recorrerNivel(NodoBinario<K, V> nodoActual, int nivel,
            List<K> recorrido) {
        if (!NodoBinario.esVacio(nodoActual)) {
            if (nivel == 0) {
                recorrido.add(nodoActual.getClave());
            } else {
                recorrerNivel(nodoActual.getHijoIzquierdo(),
                        nivel - 1, recorrido);
                recorrerNivel(nodoActual.getHijoDerecho(),
                        nivel - 1, recorrido);
            }
        }
    }

    /**
     * Retorna el recorrido que resulta del desplazamiento por los nodos
     * siguiendo un recorrido preorden. <br>
     * El funcionamiento es simple, se comienza desde la raiz y desde ahi: <br>
     * &nbsp;&nbsp;&nbsp;<b>*</b> Si el nodo es vacio retornar vacio.<br>
     * &nbsp;&nbsp;&nbsp;<b>*</b> Se visita para hacer lo que se necesite.<br>
     * &nbsp;&nbsp;&nbsp;<b>*</b> Se avanza hacia la izquierda con recursion y
     * se repiten los paso de arriba.<br>
     * &nbsp;&nbsp;&nbsp;<b>*</b> Se avanza hacia la derecha con recursion y se
     * repiten los paso de arriba.<br>
     * Graficamente se veria algo asi:
     * <p>
     * <img src = "imagenes/arbolBinario6.png" style="width:900px" alt = "
     * ejemplo de recorrido pre-orden">
     *
     * @return lista con el recorrido en preorden
     */
    @Override
    public List<K> recorridoPreOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoEnPreOrdenR(getRaiz(), recorrido);
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
    private void recorridoEnPreOrdenR(NodoBinario<K, V> nodoActual,
            List<K> recorrido) {
        if (NodoBinario.esVacio(nodoActual)) {
            return;
        }
        recorrido.add(nodoActual.getClave());
        recorridoEnPreOrdenR(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPreOrdenR(nodoActual.getHijoDerecho(), recorrido);
    }

    /**
     * Retorna el recorrido que resulta del desplazamiento por los nodos
     * siguiendo un recorrido in-orden. <br>
     * El funcionamiento es simple, se comienza desde la raiz y desde ahi: <br>
     * &nbsp;&nbsp;&nbsp;<b>*</b> Si el nodo es vacio retornar vacio.<br>
     * &nbsp;&nbsp;&nbsp;<b>*</b> Se avanza hacia la izquierda con recursion y
     * se repiten los paso de arriba.<br>
     * &nbsp;&nbsp;&nbsp;<b>*</b> Se visita para hacer lo que se necesite.<br>
     * &nbsp;&nbsp;&nbsp;<b>*</b> Se avanza hacia la derecha con recursion y se
     * repiten los paso de arriba.<br>
     * Graficamente se veria algo asi:
     * <p>
     * <img src = "imagenes/arbolBinario7.png" style="width:900px" alt = "
     * ejemplo de recorrido in-orden">
     *
     * @return lista con el recorrido en in-orden.
     */
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

    /**
     * Retorna el recorrido que resulta del desplazamiento por los nodos
     * siguiendo un recorrido post-orden. <br>
     * El funcionamiento es simple, se comienza desde la raiz y desde ahi: <br>
     * &nbsp;&nbsp;&nbsp;<b>*</b> Si el nodo es vacio retornar vacio.<br>
     * &nbsp;&nbsp;&nbsp;<b>*</b> Se avanza hacia la izquierda con recursion y
     * se repiten los paso de arriba.<br>
     * &nbsp;&nbsp;&nbsp;<b>*</b> Se avanza hacia la derecha con recursion y se
     * repiten los paso de arriba.<br>
     * &nbsp;&nbsp;&nbsp;<b>*</b> Se visita para hacer lo que se necesite.<br>
     * Graficamente se veria algo asi:
     * <p>
     * <img src = "imagenes/arbolBinario8.png" style="width:900px" alt = "
     * ejemplo de recorrido post-orden">
     *
     * @return recorrido posorden
     */
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

    /**
     * Elimina el nodo con la clave especificada por parametros y retorna el
     * valor del nodo que se elimino. <br>
     * El algoritmo funciona de la siguiente manera:<br>
     * &nbsp;&nbsp;&nbsp;<b>*</b> Se busca el nodo a eliminar, de no encontrarlo
     * se invoca a una excepcion.<br>
     * &nbsp;&nbsp;&nbsp;<b>*</b> Una vez encontrado el nodo a eliminar puede
     * caer en uno de dos casos:<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>1)El nodo es incompleto.-</b> En
     * este caso se lo puede eliminar haciendo que el nodo sea remplazado por el
     * unico nodo que tienen o cualquiera de los dos de ser vacio.<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>2)El nodo es completo.-</b> En
     * este caso se debe de buscar un nodo para sustituir la clave y valor del
     * nodo a eliminar, siendo este el nodo sucesor inOrden del nodo, posterior
     * a la sustitucion se elimina el nodo que se uso de remplazo.<br>
     * <b>NOTA:</b> Para encontrar el nodo sucesor inOrden de un nodo se
     * desplaza mientras existan nodos diferentes de vacio en el orden uno hacia
     * la derecha y todos los nodos que se pueda a la izquierda.
     * <img src = "imagenes/arbolBinario9.png" style="width:900px" alt = "
     * ejemplo de eliminacion de nodo">
     *
     * @param claveAEliminar valor usado para especificar el nodo a eliminar
     * @throws IllegalArgumentException Si la clave a eliminar no es existe en 
     * ningun nodo del arbol.
     */
    @Override
    public V eliminar(K claveAEliminar) throws IllegalArgumentException {
        V valorEliminado = buscar(claveAEliminar);
        if (valorEliminado == null) {
            throw new IllegalArgumentException("No existe nodo con la clave "
                    + claveAEliminar + " en el arbol");
        }
        setRaiz(eliminarR(getRaiz(), claveAEliminar));
        return valorEliminado;
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
    private NodoBinario<K, V> eliminarR(NodoBinario<K, V> nodoActual,
            K claveAEliminar) {
        if (NodoBinario.esVacio(nodoActual)) {
            return nodoActual;
        } else if (claveAEliminar.compareTo(nodoActual.getClave()) == 0) {
            if (nodoActual.esIncompleto()) {
                if (claveAEliminar.compareTo(nodoActual.getClave()) < 0) {
                    return nodoActual.getHijoIzquierdo();
                } else {
                    return nodoActual.getHijoDerecho();
                }
            } else {
                NodoBinario<K, V> nodoRemplazo = nodoMenor(
                        nodoActual.getHijoDerecho());
                nodoActual.setClave(nodoRemplazo.getClave());
                nodoActual.setValor(nodoRemplazo.getValor());
                nodoActual.setHijoDerecho(eliminarR(
                        nodoActual.getHijoIzquierdo(),
                        claveAEliminar));
            }
        } else {
            if (claveAEliminar.compareTo(nodoActual.getClave()) < 0) {
                nodoActual.setHijoIzquierdo(eliminarR(
                        nodoActual.getHijoIzquierdo(),
                        claveAEliminar));
            } else {
                nodoActual.setHijoDerecho(eliminarR(
                        nodoActual.getHijoDerecho(), claveAEliminar));
            }
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

    /**
     * Retorna la cantidad de nodos no vacios en el arbol.
     *
     * @return tamaño del arbolBB.
     */
    @Override
    public int size() {
        return sizeR(getRaiz());
    }

    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;size().<br>
     *
     * @param nodoActual Nodo que permite desplazarce por el arbol.
     * @return tamaño del arbolBB.
     */
    private int sizeR(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esVacio(nodoActual)) {
            return 0;
        }
        return sizeR(nodoActual.getHijoIzquierdo())
                + sizeR(nodoActual.getHijoDerecho())
                + 1;
    }

    /**
     * Miestra la cantidad de generaciones del arbol contando a partir de uno
     * desde la raiz del arbol.
     *
     * @return Altura del arbol.
     */
    @Override
    public int altura() {
        return nivelR(raiz) + 1;
    }

    /**
     * Miestra la cantidad de generaciones del arbol contando a partir de cero
     * desde la raiz del arbol.
     *
     * @return Nivel del arbol.
     */
    @Override
    public int nivel() {
        return nivelR(raiz);
    }

    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;nivel(). <br>
     *
     * @param nodoActual Nodo que permite desplazarse por el arbol.
     * @return cantidad de niveles de arbol.
     */
    protected int nivelR(NodoBinario<K, V> nodoActual) {
        if (!NodoBinario.esVacio(nodoActual)) {
            int izq = nivelR(nodoActual.getHijoIzquierdo()) + 1;
            int der = nivelR(nodoActual.getHijoDerecho()) + 1;
            if (izq > der) {
                return izq;
            } else {
                return der;
            }
        }
        return -1;
    }
}
