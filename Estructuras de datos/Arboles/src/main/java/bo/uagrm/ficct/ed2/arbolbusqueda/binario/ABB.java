/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.binario;

import bo.uagrm.ficct.ed2.arbolbusqueda.IArbolBusqueda;
import java.util.ArrayList;
import java.util.List;

public class ABB<K extends Comparable<K>, V> implements IArbolBusqueda<K, V> {

    protected NodoBinario<K, V> raiz;

    /**
     * Constructor por defecto.
     */
    public ABB() {

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
     *
     * @param clavesInOrden Lista de claves in-orden.
     * @param valoresInOrden Lista de valores in-orden.
     * @param clavesNoInOrden Lista de valores pre-orden o post-Orden.
     * @param valoresNoInOrden Lista de valores pre-orden o post-orden.
     * @param usandoPreOrden Valor booleano que es true si se usa el recorrido
     * pre-orden para reconstruir o false si se usa el recorrido post-orden.
     */
    public ABB(List<K> clavesInOrden,
            List<V> valoresInOrden, List<K> clavesNoInOrden,
            List<V> valoresNoInOrden, boolean usandoPreOrden) {
        if (usandoPreOrden) {
            this.raiz = reconstruirConPreOrden(clavesInOrden,
                    clavesNoInOrden, valoresNoInOrden);
        } else {
            this.raiz = reconstruirConPostOrden(clavesInOrden,
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
    private NodoBinario<K, V> reconstruirConPreOrden(List<K> clavesInOrden, List<K> clavesPreOrden,
            List<V> valoresPreOrden) {
        if (clavesInOrden.isEmpty()) {
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        } else {
            K clavePadre = clavesPreOrden.get(0);
            V valorPadre = valoresPreOrden.get(0);
            int posicionClaveInOrden = indiceDeClave(clavePadre,
                    clavesInOrden);
            //armamos
            NodoBinario<K, V> nodoPadre = new NodoBinario<>(clavePadre,
                    valorPadre);
            nodoPadre.setHijoIzquierdo(reconstruirConPreOrden(
                    clavesInOrden.subList(0,
                    posicionClaveInOrden), 
                    clavesPreOrden.subList(1,
                    posicionClaveInOrden + 1), 
                    valoresPreOrden.subList(1,
                    posicionClaveInOrden + 1)));
            nodoPadre.setHijoDerecho(reconstruirConPreOrden(
                    clavesInOrden.subList(posicionClaveInOrden + 1, 
                            clavesInOrden.size()), 
                    clavesPreOrden.subList(
                    posicionClaveInOrden + 1, clavesInOrden.size()), 
                    valoresPreOrden.subList(
                    posicionClaveInOrden + 1, clavesInOrden.size())));
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
            List<K> clavesPostOrden, List<V> valoresPostOrden) {
        if (clavesInOrden.isEmpty()) {
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        } else {
            K clavePadre = clavesPostOrden.get(clavesPostOrden.size() - 1);
            V valorPadre = valoresPostOrden.get(valoresPostOrden.size() - 1);
            int posicionClaveInOrden = indiceDeClave(clavePadre,
                    clavesInOrden);
            //armamos
            NodoBinario<K, V> nodoPadre = new NodoBinario<>(clavePadre,
                    valorPadre);
            nodoPadre.setHijoIzquierdo(reconstruirConPostOrden(
                    clavesInOrden.subList(0,
                    posicionClaveInOrden),
                    clavesPostOrden.subList(0,
                    posicionClaveInOrden),
                    valoresPostOrden.subList(0,
                    posicionClaveInOrden)));
            nodoPadre.setHijoDerecho(reconstruirConPostOrden(
                    clavesInOrden.subList(
                    posicionClaveInOrden + 1, clavesInOrden.size()),
                    clavesPostOrden.subList(
                    posicionClaveInOrden, clavesInOrden.size() - 1),
                    valoresPostOrden.subList(
                    posicionClaveInOrden, clavesInOrden.size() - 1)));
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
     * Constructor por copia.
     * Copia los valores de un arbol de busqueda binario ya establecido al 
     * arbol actual del mismo tipo.
     * @param copiando arbol a copiar valores.
     */
    public ABB(ABB<K, V> copiando) {
        this.raiz = copiarValores(this.raiz, 
                copiando.raiz);
    }

    /**
     * OPerador Auxiliar para el constructor de copia.
     * @param nodoActualCopia nodo actual al que se copia los valores.
     * @param nodoActualCopiando nodo actual del que se obtiene los valores.
     * @return nodo copiado.
     */
    private NodoBinario<K, V> copiarValores(NodoBinario<K, V> nodoActualCopia, 
            NodoBinario<K, V> nodoActualCopiando) {
        if (NodoBinario.esVacio(nodoActualCopiando)) {
            return null;
        }
        nodoActualCopia = new NodoBinario<>(nodoActualCopiando.getClave(),
                nodoActualCopiando.getValor());
        nodoActualCopia.setHijoIzquierdo(copiarValores(
                nodoActualCopia.getHijoIzquierdo(),
                nodoActualCopiando.getHijoIzquierdo()));
        nodoActualCopia.setHijoDerecho(copiarValores(
                nodoActualCopia.getHijoDerecho(),
                nodoActualCopiando.getHijoDerecho()));
        return nodoActualCopia;
    }

    /**
     * Retorna la referencia a la raiz del arbol.
     *
     * @return Muestra la raiz (nodo inicial del arbol).
     */
    protected NodoBinario<K, V> getRaiz() {
        return raiz;
    }

    /**
     * Ingresa una referencia a la raiz del arbol.
     *
     * @param raiz valor a insertar como raiz del arbol.
     */
    protected void setRaiz(NodoBinario<K, V> raiz) {
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
            if (nodoEliminado.getClave() != NodoBinario.nodoVacio()) {
                nodoEliminado.setClave(nodoActual.getClave());
                nodoEliminado.setValor(nodoActual.getValor());
            }
            if (nodoActual.esIncompleto()) {
                return (!nodoActual.esVacioHijoIzquierdo())
                        ? nodoActual.getHijoIzquierdo()
                        : nodoActual.getHijoDerecho();
            }
            NodoBinario<K, V> nodoRemplazo
                    = nodoMenor(nodoActual.getHijoDerecho());
            nodoActual.setHijoDerecho(eliminar(
                    nodoActual.getHijoDerecho(),
                    nodoRemplazo.getClave(), nodoEliminado));
            nodoActual.setClave(nodoRemplazo.getClave());
            nodoActual.setValor(nodoRemplazo.getValor());
            return nodoActual;
        }
        if (claveAEliminar.compareTo(nodoActual.getClave()) < 0) {
            nodoActual.setHijoIzquierdo(eliminar(
                    nodoActual.getHijoIzquierdo(), claveAEliminar,
                    nodoEliminado));
        } else {
            nodoActual.setHijoDerecho(eliminar(
                    nodoActual.getHijoDerecho(), claveAEliminar,
                    nodoEliminado));
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
    private V buscar(NodoBinario<K, V> nodoActual, K claveABuscar) {
        if (NodoBinario.esVacio(nodoActual)) {
            return null;
        }
        if (claveABuscar.compareTo(nodoActual.getClave()) == 0) {
            return nodoActual.getValor();
        }
        if (claveABuscar.compareTo(nodoActual.getClave()) < 0) {
            return buscar(nodoActual.getHijoIzquierdo(), claveABuscar);
        } else {
            return buscar(nodoActual.getHijoDerecho(), claveABuscar);
        }
    }

    @Override
    public boolean contiene(K clave) {
        return buscar(clave) != null;
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
        return nivel(getRaiz());
    }

    /**
     * Operacion auxiliar usada en:<br>
     * &nbsp;&nbsp;&nbsp;nivel(). <br>
     *
     * @param nodoActual Nodo que permite desplazarse por el arbol.
     * @return cantidad de niveles de arbol.
     */
    protected int nivel(NodoBinario<K, V> nodoActual) {
        return (NodoBinario.esVacio(nodoActual)) ? 0 : altura() - 1;
    }

    /**
     * Miestra la cantidad de generaciones del arbol contando a partir de uno
     * desde la raiz del arbol.
     *
     * @return Altura del arbol.
     */
    @Override
    public int altura() {
        return altura(this.raiz);
    }

    protected int altura(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esVacio(nodoActual)) {
            return 0;
        }
        int izq = altura(nodoActual.getHijoIzquierdo()) + 1;
        int der = altura(nodoActual.getHijoDerecho()) + 1;
        return (izq > der) ? izq : der;
    }

    @Override
    public boolean esVacio() {
        return NodoBinario.esVacio(raiz);
    }

    @Override
    public void vaciar() {
        setRaiz((NodoBinario<K, V>) NodoBinario.nodoVacio());
    }

    /*
    Este recorrido hecho de una manera recursiva es muy inedificiente en 
    comparacion con su contraparte iterativa.
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
