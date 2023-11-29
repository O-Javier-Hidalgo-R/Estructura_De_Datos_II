/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uagrm.ficct.ed1.proyecto1.arbolbusqueda.mvias;

import edu.uagrm.ficct.ed1.proyecto1.arbolbusqueda.ClaveNoEncontrada;
import edu.uagrm.ficct.ed1.proyecto1.arbolbusqueda.EsArbolVacioException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArbolB<K extends Comparable<K>, V> extends ArbolMVias<K, V> {

    private final int NRO_MINIMO_HIJOS;
    private final int NRO_MINIMO_DATOS;
    private final int NRO_MAXIMO_HIJOS;
    private final int NRO_MAXIMO_DATOS;
    private final int POSICION_INVALIDA = -1;
    private final int VAL_MEDIO = (orden - 1) / 2;

    public ArbolB(int orden) {
        super(orden);
        this.NRO_MAXIMO_HIJOS = orden;
        this.NRO_MAXIMO_DATOS = orden - 1;
        this.NRO_MINIMO_DATOS = NRO_MAXIMO_DATOS / 2;
        this.NRO_MINIMO_HIJOS = NRO_MINIMO_DATOS + 1;
    }

    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if (super.esVacio()) {
            super.raiz = new NodoNVias<>(super.orden + 1, claveAInsertar,
                    valorAInsertar);
            return;
        }
        Stack<NodoNVias<K, V>> ramaHastaHoja = new Stack<>();
        NodoNVias<K, V> nodoActual = this.raiz;
        int posABajar;
        while (!NodoNVias.esVacio(nodoActual)) {
            ramaHastaHoja.add(nodoActual);
            posABajar = busPosBajar(nodoActual, claveAInsertar);
            if (NodoNVias.esVacio(nodoActual) && claveAInsertar.compareTo(nodoActual.getClave(posABajar)) == 0) {
                nodoActual.setValor(posABajar, valorAInsertar);
                return;
            }
            nodoActual = nodoActual.getHijo(posABajar);
        }
        insertarDatoNodoOrdenadamente(ramaHastaHoja.peek(), claveAInsertar, valorAInsertar, NodoNVias.nodoVacio());
        dividir(ramaHastaHoja);
    }

    /*@Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
    if (super.esVacio()) {
    super.raiz = new NodoNVias<>(super.orden+1, claveAInsertar,
    valorAInsertar);
    return;
    }
    Stack<NodoNVias<K, V>> ramaHastaHoja = new Stack<>();
    NodoNVias<K, V> nodoActual = this.raiz;
    while (!NodoNVias.esVacio(nodoActual)) {
    int posicionClaveExistente = existeClaveEnNodo(nodoActual,
    claveAInsertar);
    if (posicionClaveExistente != POSICION_INVALIDA) {
    nodoActual.setValor(posicionClaveExistente, valorAInsertar);
    nodoActual = NodoNVias.nodoVacio();
    } else {
    if (nodoActual.esHoja()) {
    super.colocarDatoOrdenadamente(nodoActual,
    claveAInsertar, valorAInsertar);
    if (nodoActual.cantidadDeClavesNoVacias()
    > this.NRO_MAXIMO_DATOS) {
    this.dividir(nodoActual, ramaHastaHoja);
    }
    nodoActual = NodoNVias.nodoVacio();
    } else {
    int posParaBajar = super.busPosBajar(nodoActual, claveAInsertar);
    ramaHastaHoja.push(nodoActual);
    nodoActual = nodoActual.getHijo(posParaBajar);
    }
    }
    }
    }*/
 /*private int existeClaveEnNodo(NodoNVias<K, V> nodoActual, K claveAInsertar) {
    int result = POSICION_INVALIDA;
    for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
    if (claveAInsertar.compareTo(nodoActual.getClave(i)) == 0) {
    result = i;
    }
    }
    return result;
    }*/

 /*private void dividir(NodoNVias<K, V> nodoActual,
    Stack<NodoNVias<K, V>> pilaDeAncestros) {
    final int POS_MEDIANA = NRO_MAXIMO_DATOS / 2;
    NodoNVias<K, V> nodoParteDerecha = new NodoNVias<>(super.orden);
    int j = 0;
    int i = 0;
    K claveMedio = nodoActual.getClave(POS_MEDIANA);
    V valorMedio = nodoActual.getValor(POS_MEDIANA);
    
    //Copiamos los valores del nodo actual al que sera la parte derecha
    for (i = POS_MEDIANA + 1; i < NRO_MAXIMO_HIJOS; i++) {
    nodoParteDerecha.setClave(j,
    nodoActual.getClave(i));
    nodoParteDerecha.setValor(j,
    nodoActual.getValor(i));
    nodoParteDerecha.setHijo(j,
    nodoActual.getHijo(i));
    j++;
    }
    nodoParteDerecha.setHijo(j,
    nodoActual.getHijo(i));
    
    //Eliminamos las referencia del nodo actual que ya no sirven para
    //usarlo como el nodo izquierdo
    for (i = POS_MEDIANA; i < NRO_MAXIMO_HIJOS; i++) {
    nodoActual.setClave(i, (K) NodoNVias.datoVacio());
    nodoActual.setValor(i, (V) NodoNVias.datoVacio());
    nodoActual.setHijo(i + 1, NodoNVias.nodoVacio());
    }
    
    if (nodoActual == super.raiz) {
    NodoNVias<K, V> nodoNuevo = new NodoNVias<>(super.orden);
    //Colocamos los valores que llevara el nodo nuevo;
    nodoNuevo.setClave(0, claveMedio);
    nodoNuevo.setValor(0, valorMedio);
    nodoNuevo.setHijo(0, nodoActual);
    nodoNuevo.setHijo(1, nodoParteDerecha);
    
    //La raiz ahora es el nodo nuevo
    super.raiz = nodoNuevo;
    } else {
    NodoNVias<K, V> nodoPadre = pilaDeAncestros.pop();
    insertarDatoNodoOrdenadamente(nodoPadre, claveMedio, valorMedio, nodoParteDerecha);
    if (nodoPadre.cantidadDeClavesNoVacias() > NRO_MAXIMO_DATOS) {
    dividir(nodoPadre, pilaDeAncestros);
    }
    }
    }*/
    private void insertarDatoNodoOrdenadamente(NodoNVias<K, V> nodoNVias,
            K clave, V valor, NodoNVias<K, V> HijoDerecho) {
        int i = 0;
        //identidico donde voy a insertar el nodo en el nodo (posicion)
        while (nodoNVias.getClave(i) != (K) NodoNVias.datoVacio()
                && clave.compareTo(nodoNVias.getClave(i)) > 0) {
            i++;
        }
        //Empujo los valores y hijos hacia adelante 
        int l = nodoNVias.cantidadDeClavesNoVacias() - 1;
        for (int j = l; j >= i; j--) {
            nodoNVias.setClave(j + 1, nodoNVias.getClave(j));
            nodoNVias.setValor(j + 1, nodoNVias.getValor(j));
        }
        for (int j = l + 1; j >= i + 1; j--) {
            nodoNVias.setHijo(j + 1, nodoNVias.getHijo(j));
        }

        //al fin inserto los valores
        nodoNVias.setClave(i, clave);
        nodoNVias.setValor(i, valor);
        nodoNVias.setHijo(i + 1, HijoDerecho);
    }

    /*@Override
    public V eliminar(K claveAEliminar) throws IllegalArgumentException {
    if (claveAEliminar == (K) NodoNVias.datoVacio()) {
    throw new IllegalArgumentException("La clave a eliminar no puede "
    + "ser nula");
    }
    
    Stack<NodoNVias<K, V>> pilaDeAncestros = new Stack<>();
    NodoNVias<K, V> nodoActual = this.buscarNodoClave(claveAEliminar, pilaDeAncestros);
    
    if (NodoNVias.esVacio(nodoActual)) {
    throw new IllegalArgumentException("La clave no existe en el arbol");
    }
    int posicionDelaClaveEnElnodo = super.busPosBajar(nodoActual, claveAEliminar);
    V valorARetornar = nodoActual.getValor(posicionDelaClaveEnElnodo);
    
    if (nodoActual.esHoja()) {
    eliminarDatoDeNodos(nodoActual, posicionDelaClaveEnElnodo);
    if (nodoActual.cantidadDeClavesNoVacias() < this.NRO_MINIMO_DATOS) {
    if (pilaDeAncestros.isEmpty()) {
    if (nodoActual.cantidadDeClavesNoVacias() == 0) {
    super.vaciar();
    }
    } else {
    this.prestarseOFusionar(nodoActual, pilaDeAncestros);
    }
    }
    } else {
    //creo que no funciona
    pilaDeAncestros.push(nodoActual);
    NodoNVias<K, V> nodoDelPredecesor = this.busNodoDelPredecesor(pilaDeAncestros, nodoActual.getHijo(posicionDelaClaveEnElnodo));
    int posicionDelPredecesor = nodoDelPredecesor.cantidadDeClavesNoVacias() - 1;
    K clavePredecesora = nodoDelPredecesor.getClave(posicionDelPredecesor);
    V valorPredecesor = nodoDelPredecesor.getValor(posicionDelPredecesor);
    eliminarDatoDeNodos(nodoDelPredecesor, posicionDelPredecesor);
    nodoActual.setClave(posicionDelaClaveEnElnodo, clavePredecesora);
    nodoActual.setValor(posicionDelaClaveEnElnodo, valorPredecesor);
    if (nodoDelPredecesor.cantidadDeClavesNoVacias() < this.NRO_MINIMO_DATOS) {
    this.prestarseOFusionar(nodoDelPredecesor, pilaDeAncestros);
    }
    }
    return valorARetornar;
    }*/

 /*
    private void eliminar(NodoNVias<K, V> nodoActual, NodoNVias<K, V> nodoPadre, int posBajoElPadre, K claveAEliminar) {
        if(NodoNVias.esVacio(nodoActual)){
            throw new IllegalArgumentException("No existe nodo con la clave "
                    + claveAEliminar);
        }
        int posABajar = super.busPosBajar(nodoActual, claveAEliminar);
        //si la clave esta en el nodo actual
        if(nodoActual.getClave(posABajar) != (K) NodoNVias.datoVacio() && 
                nodoActual.getClave(posABajar).compareTo(
                        claveAEliminar) == 0){
            if(nodoActual.esHoja()){
                if(nodoActual.cantidadDeClavesNoVacias() >= NRO_MINIMO_DATOS || 
                        nodoActual == super.raiz){
                    nodoActual.setClave(posABajar, 
                            (K) NodoNVias.datoVacio());
                    nodoActual.setValor(posABajar, 
                            (V) NodoNVias.datoVacio());
                    return;
                }
                if(posBajoElPadre + 1 < NRO_MAXIMO_HIJOS && nodoPadre.cantidadDeClavesNoVacias() - 1 >= NRO_MINIMO_DATOS){
                    nodoActual.setClave(posABajar, nodoPadre.getClave(posBajoElPadre));
                    nodoActual.setValor(posABajar, nodoPadre.getValor(posBajoElPadre));
                    nodoPadre.setClave(posBajoElPadre, nodoPadre.getHijo(posBajoElPadre + 1).getClave(0));
                    nodoPadre.setValor(posBajoElPadre, nodoPadre.getHijo(posBajoElPadre + 1).getValor(0));
                    nodoPadre.getHijo(posBajoElPadre + 1).setClave(0, (K) NodoNVias.nodoVacio());
                    nodoPadre.getHijo(posBajoElPadre + 1).setValor(0, (V) NodoNVias.nodoVacio());
                    return;
                }
                if(posBajoElPadre - 1 > 0 && nodoPadre.cantidadDeClavesNoVacias() - 1 >= NRO_MINIMO_DATOS){
                    nodoActual.setClave(posABajar, nodoPadre.getClave(posBajoElPadre - 1));
                    nodoActual.setValor(posABajar, nodoPadre.getValor(posBajoElPadre - 1));
                    nodoPadre.setClave(posBajoElPadre-1, nodoPadre.getHijo(posBajoElPadre -1).getClave(nodoPadre.getHijo(posBajoElPadre -1).cantidadDeClavesNoVacias()));
                    nodoPadre.setValor(posBajoElPadre-1, nodoPadre.getHijo(posBajoElPadre -1).getValor(nodoPadre.getHijo(posBajoElPadre -1).cantidadDeClavesNoVacias()));
                    nodoPadre.getHijo(posBajoElPadre - 1).setClave(nodoPadre.getHijo(posBajoElPadre - 1).cantidadDeClavesNoVacias(), (K) NodoNVias.nodoVacio());
                    nodoPadre.getHijo(posBajoElPadre - 1).setValor(nodoPadre.getHijo(posBajoElPadre - 1).cantidadDeClavesNoVacias(), (V) NodoNVias.nodoVacio());
                    return;
                }
                if(posBajoElPadre == NRO_MAXIMO_HIJOS - 1){
                    fusionarConHermanoDeAtras(nodoPadre, nodoActual, posBajoElPadre);
                    return;
                }else{
                    fusionarConHermanoDeAdelante(nodoPadre, nodoActual, posBajoElPadre);
                    return;
                }
            }
            K clavePredecesorInOrden = nodoActual.getHijo(posABajar).getClave(nodoActual.cantidadDeClavesNoVacias() - 1);
            V valorPredecesorInOrden = nodoActual.getHijo(posABajar).getValor(nodoActual.cantidadDeClavesNoVacias() - 1);
            nodoActual.setClave(posABajar, clavePredecesorInOrden);
            nodoActual.setValor(posABajar, valorPredecesorInOrden);
            eliminar(nodoActual.getHijo(posABajar), nodoActual, posABajar, clavePredecesorInOrden);
        }
        eliminar(nodoActual.getHijo(posABajar), nodoActual, posABajar, claveAEliminar);
    }

    private void fusionarConHermanoDeAtras(NodoNVias<K, V> nodoPadre, NodoNVias<K, V> nodoActual, int posBajoElPadre) {
        
    }

    private void fusionarConHermanoDeAdelante(NodoNVias<K, V> nodoPadre, NodoNVias<K, V> nodoActual, int posBajoElPadre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     */
 /*private NodoNVias<K, V> buscarNodoClave(K ClaveABuscar, Stack<NodoNVias<K, V>> pilaDeAncestros) {
    return buscarNodoClave(ClaveABuscar, super.raiz, pilaDeAncestros);
    }
    
    private NodoNVias<K, V> buscarNodoClave(K ClaveABuscar, NodoNVias<K, V> nodoActual, Stack<NodoNVias<K, V>> pilaDeAncestros) {
    int posABajar = super.busPosBajar(nodoActual, ClaveABuscar);
    if (posABajar < nodoActual.cantidadDeClavesNoVacias() && ClaveABuscar.compareTo(nodoActual.getClave(posABajar)) == 0) {
    return nodoActual;
    } else {
    pilaDeAncestros.add(nodoActual);
    return buscarNodoClave(ClaveABuscar, nodoActual.getHijo(posABajar), pilaDeAncestros);
    }
    }*/
    private void eliminarDatoDeNodos(NodoNVias<K, V> nodoActual, int posDatoAEliminar) {
        int i;
        for (i = posDatoAEliminar; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            nodoActual.setClave(i, nodoActual.getClave(i + 1));
            nodoActual.setValor(i, nodoActual.getValor(i + 1));
            nodoActual.setHijo(i, nodoActual.getHijo(i + 1));
        }
        nodoActual.setClave(i, (K) NodoNVias.datoVacio());
        nodoActual.setValor(i, (V) NodoNVias.datoVacio());
        nodoActual.setHijo(i, nodoActual.getHijo(i + 1));
        nodoActual.setHijo(i + 1, NodoNVias.nodoVacio());
    }

    /*private NodoNVias<K, V> busNodoDelPredecesor(Stack<NodoNVias<K, V>> pilaDeAncestros, NodoNVias<K, V> nodoSalida) {
    NodoNVias<K, V> nodoActual = nodoSalida;
    while (!NodoNVias.esVacio(nodoActual)) {
    pilaDeAncestros.push(nodoActual);
    nodoActual = nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias());
    }
    nodoActual = pilaDeAncestros.pop();
    return nodoActual;
    }*/

 /*private void prestarseOFusionar(NodoNVias<K, V> nodoActual, Stack<NodoNVias<K, V>> pilaDeAncestros) {
    int limNodoActual = nodoActual.cantidadDeClavesNoVacias();
    if(pilaDeAncestros.isEmpty()){
    return;
    }
    if(nodoActual.cantidadDeClavesNoVacias() >= NRO_MINIMO_DATOS){
    return;
    }
    NodoNVias<K, V> nodoPadre = pilaDeAncestros.peek();
    NodoNVias<K, V> nodoHermanoIzquierdo = NodoNVias.nodoVacio();
    NodoNVias<K, V> nodoHermanoDerecho = NodoNVias.nodoVacio();
    int posBajoElPadre = 0;
    
    while (nodoPadre.getHijo(posBajoElPadre) != nodoActual) {
    posBajoElPadre++;
    }
    
    if (posBajoElPadre + 1 <= nodoPadre.cantidadDeClavesNoVacias()) {
    nodoHermanoDerecho = nodoPadre.getHijo(posBajoElPadre + 1);
    }
    
    if (posBajoElPadre - 1 >= 0) {
    nodoHermanoIzquierdo = nodoPadre.getHijo(posBajoElPadre - 1);
    }
    
    if (nodoHermanoDerecho != NodoNVias.nodoVacio() && nodoHermanoDerecho.cantidadDeClavesNoVacias() > NRO_MINIMO_DATOS) {
    //se coloca en la ultima posicion del nodo actual el valor a bajar del padre
    int lim = nodoActual.cantidadDeClavesNoVacias();
    nodoActual.setClave(lim, nodoPadre.getClave(posBajoElPadre));
    nodoActual.setValor(lim, nodoPadre.getValor(posBajoElPadre));
    lim++;
    //sube el valor del hermano derecho al padre
    nodoPadre.setClave(posBajoElPadre, nodoHermanoDerecho.getClave(0));
    nodoPadre.setValor(posBajoElPadre, nodoHermanoDerecho.getValor(0));
    //el primer hijo del hermano derecho pasa a ser el ultimo del nodo actual
    nodoActual.setHijo(lim, nodoHermanoDerecho.getHijo(0));
    //se recorren los valores e hijos del hermano derecho en -1
    lim = nodoHermanoDerecho.cantidadDeClavesNoVacias();
    int i;
    for (i = 0; i < lim; i++) {
    nodoHermanoDerecho.setClave(i, nodoHermanoDerecho.getClave(i+1));
    nodoHermanoDerecho.setClave(i, nodoHermanoDerecho.getClave(i+1));
    nodoHermanoDerecho.setHijo(i, nodoHermanoDerecho.getHijo(i+1));
    }
    nodoHermanoDerecho.setHijo(i, nodoHermanoDerecho.getHijo(i+1));
    return;
    }
    
    if (nodoHermanoIzquierdo != NodoNVias.nodoVacio() && nodoHermanoIzquierdo.cantidadDeClavesNoVacias() > NRO_MINIMO_DATOS) {
    //se recorren los valores del nodo actual +1
    int lim = nodoActual.cantidadDeClavesNoVacias();
    int i;
    for (i = lim; i > 0; i--) {
    nodoActual.setClave(i, nodoActual.getClave(i-1));
    nodoActual.setValor(i, nodoActual.getValor(i-1));
    nodoActual.setHijo(i, nodoActual.getHijo(i-1));
    }
    nodoActual.setHijo(i, nodoActual.getHijo(i+1));
    //se coloca en la posicion 0 el valor que baja
    nodoActual.setClave(0, nodoPadre.getClave(posBajoElPadre-1));
    nodoActual.setValor(0, nodoPadre.getValor(posBajoElPadre-1));
    //se sube el ultimo valor del hermano izquierdo
    lim = nodoHermanoIzquierdo.cantidadDeClavesNoVacias();
    nodoPadre.setClave(posBajoElPadre-1, nodoHermanoIzquierdo.getClave(lim-1));
    nodoPadre.setValor(posBajoElPadre-1, nodoHermanoIzquierdo.getValor(lim-1));
    //se elimina el ultimo valor del hermano izquierdo
    nodoHermanoIzquierdo.setClave(lim-1, (K) NodoNVias.datoVacio());
    nodoHermanoIzquierdo.setValor(lim-1, (V) NodoNVias.datoVacio());
    //se coloca en el hijo 0 del nodo actual el hijo despues del
    nodoActual.setHijo(0, nodoHermanoIzquierdo.getHijo(lim));
    //se elimina el ultimo hijo del hermano izquierdo
    nodoHermanoIzquierdo.setHijo(lim, NodoNVias.nodoVacio());
    return;
    }
    if(nodoHermanoDerecho != NodoNVias.nodoVacio()){
    fusionConElDeAdelante(nodoActual, pilaDeAncestros);
    }else{
    fusionConElDeAtras(nodoActual, pilaDeAncestros);
    }
    }*/
    private void dividir(Stack<NodoNVias<K, V>> ramaActual) {
        NodoNVias<K, V> nodoActual = ramaActual.pop();
        if (nodoActual.cantidadDeClavesNoVacias() <= NRO_MAXIMO_DATOS) {
            return;
        }
        K claveASubir = nodoActual.getClave(VAL_MEDIO);
        V valorASubir = nodoActual.getValor(VAL_MEDIO);
        if (nodoActual == raiz) {
            NodoNVias<K, V> nodoNuevo = new NodoNVias<>(orden + 1, claveASubir, valorASubir);
            NodoNVias<K, V> parteDerecha = new NodoNVias<>(orden + 1);
            PartirHaciaDerecha(nodoActual, parteDerecha);
            nodoNuevo.setHijo(0, nodoActual);
            nodoNuevo.setHijo(1, parteDerecha);
            this.raiz = nodoNuevo;
            return;
        }
        NodoNVias<K, V> nodoPadre = ramaActual.peek();
        NodoNVias<K, V> parteDerecha = new NodoNVias<>(orden + 1);
        PartirHaciaDerecha(nodoActual, parteDerecha);
        insertarDatoNodoOrdenadamente(nodoPadre, claveASubir, valorASubir, parteDerecha);
        dividir(ramaActual);
    }

    /*private void apilarRamaHastaHoja(Stack<NodoNVias<K, V>> ramaHastaHoja, K claveAInsertar) {
    int posABajar = busPosBajar(ramaHastaHoja.peek(), claveAInsertar);
    if (ramaHastaHoja.peek().getClave(posABajar).compareTo(claveAInsertar) == 0) {
    return;
    }
    if (!ramaHastaHoja.peek().estanClavesLlenas()) {
    return;
    }
    ramaHastaHoja.add(ramaHastaHoja.peek().getHijo(posABajar));
    apilarRamaHastaHoja(ramaHastaHoja, claveAInsertar);
    }*/
    private void PartirHaciaDerecha(NodoNVias<K, V> nodoActual, NodoNVias<K, V> parteDerecha) {
        int i = 0;
        int j;
        //Coloca en la parte derecha los valores correctos del nodo actual
        for (j = VAL_MEDIO + 1; j < orden; j++) {
            parteDerecha.setClave(i, nodoActual.getClave(j));
            parteDerecha.setValor(i, nodoActual.getValor(j));
            parteDerecha.setHijo(i, nodoActual.getHijo(j));
            i++;
        }
        parteDerecha.setHijo(i, nodoActual.getHijo(j));
        //Elimina la parte derecha del nodo actual 
        for (j = VAL_MEDIO; j < orden; j++) {
            nodoActual.setClave(j, (K) NodoNVias.datoVacio());
            nodoActual.setValor(j, (V) NodoNVias.datoVacio());
            nodoActual.setHijo(j + 1, NodoNVias.nodoVacio());
        }
    }

    /*private void fusionConElDeAdelante(NodoNVias<K, V> nodoActual, Stack<NodoNVias<K, V>> pilaDeAncestros) {
    NodoNVias<K, V> nodoPadre = pilaDeAncestros.peek();
    int posNodoActualEnElPadre = busPosNodoHijo(nodoPadre, nodoActual);
    NodoNVias<K, V> hermanoIzquierdo = nodoActual;
    K claveABajar = nodoPadre.getClave(posNodoActualEnElPadre);
    V ValorABajar = nodoPadre.getValor(posNodoActualEnElPadre);
    int ultimaPosVaciaHermanoIzquierdo = hermanoIzquierdo.cantidadDeClavesNoVacias();
    NodoNVias<K, V>hermanoDerecho = nodoPadre.getHijo(posNodoActualEnElPadre+1);
    
    //Baja el valor al hijo izquierdo desde el padre
    hermanoIzquierdo.setClave(ultimaPosVaciaHermanoIzquierdo, claveABajar);
    hermanoIzquierdo.setValor(ultimaPosVaciaHermanoIzquierdo, ValorABajar);
    ultimaPosVaciaHermanoIzquierdo++;
    //coloca los valores del hermano derecho en el izquierdo
    int i;
    int limNodoHermanoDerecho = hermanoDerecho.cantidadDeClavesNoVacias();
    for (i = 0; i < limNodoHermanoDerecho; i++) {
    hermanoIzquierdo.setClave(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getClave(i));
    hermanoIzquierdo.setValor(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getValor(i));
    hermanoIzquierdo.setHijo(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getHijo(i));
    ultimaPosVaciaHermanoIzquierdo++;
    }
    hermanoIzquierdo.setHijo(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getHijo(i));
    //Recorre los valores en el padre
    int limNodoPadre = nodoPadre.cantidadDeClavesNoVacias();
    for (i = posNodoActualEnElPadre; i < limNodoPadre; i++) {
    nodoPadre.setClave(i, nodoPadre.getClave(i+1));
    nodoPadre.setValor(i, nodoPadre.getValor(i+1));
    nodoPadre.setHijo(i+1, nodoPadre.getHijo(i+2));
    }
    prestarseOFusionar(pilaDeAncestros.pop(), pilaDeAncestros);
    }*/

 /*private void fusionConElDeAtras(NodoNVias<K, V> nodoActual, Stack<NodoNVias<K, V>> pilaDeAncestros) {
    NodoNVias<K, V> nodoPadre = pilaDeAncestros.peek();
    int posNodoActualEnElPadre = busPosNodoHijo(nodoPadre, nodoActual);
    NodoNVias<K, V> hermanoIzquierdo = nodoPadre.getHijo(posNodoActualEnElPadre - 1);
    K claveABajar = nodoPadre.getClave(posNodoActualEnElPadre-1);
    V ValorABajar = nodoPadre.getValor(posNodoActualEnElPadre-1);
    eliminarDatoDeNodos(nodoPadre, posNodoActualEnElPadre-1);
    int ultimaPosVaciaHermanoIzquierdo = hermanoIzquierdo.cantidadDeClavesNoVacias();
    NodoNVias<K, V>hermanoDerecho = nodoActual;
    
    //Baja el valor al hijo izquierdo desde el padre
    hermanoIzquierdo.setClave(ultimaPosVaciaHermanoIzquierdo, claveABajar);
    hermanoIzquierdo.setValor(ultimaPosVaciaHermanoIzquierdo, ValorABajar);
    ultimaPosVaciaHermanoIzquierdo++;
    //coloca los valores del hermano derecho en el izquierdo
    int i;
    int limNodoHermanoDerecho = hermanoDerecho.cantidadDeClavesNoVacias();
    for (i = 0; i < limNodoHermanoDerecho; i++) {
    hermanoIzquierdo.setClave(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getClave(i));
    hermanoIzquierdo.setValor(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getValor(i));
    hermanoIzquierdo.setHijo(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getHijo(i));
    ultimaPosVaciaHermanoIzquierdo++;
    }
    hermanoIzquierdo.setHijo(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getHijo(i));
    nodoPadre.setHijo(posNodoActualEnElPadre-1, hermanoIzquierdo);
    //Recorre los valores en el padre
    /*int limNodoPadre = nodoPadre.cantidadDeClavesNoVacias();
    for (i = posNodoActualEnElPadre-1; i < limNodoPadre; i++) {
    nodoPadre.setClave(i, nodoPadre.getClave(i+1));
    nodoPadre.setValor(i, nodoPadre.getValor(i+1));
    nodoPadre.setHijo(i+1, nodoPadre.getHijo(i+2));
    }
    prestarseOFusionar(pilaDeAncestros.pop(), pilaDeAncestros);
}*/

 /*private int busPosNodoHijo(NodoNVias<K, V> nodoPadre, NodoNVias<K, V> nodoActual) {
    int limNodoPadre = nodoPadre.cantidadDeClavesNoVacias();
    for (int i = 0; i <= limNodoPadre; i++) {
    if(nodoPadre.getHijo(i) == nodoActual){
    return i;
    }
    }
    return -1;
    }*/
    @Override
    public V eliminar(K claveAEliminar) {
        try {
            NodoNVias<K, V> nodoActual = this.raiz;
            Stack<NodoNVias<K, V>> pilaAncenstros = new Stack<>();
            //Busca la clave en los nodos, apilando los ancestros por la que
            //pasa. Si no se encuentra lanza una excepcion (Excepcion lanzada
            //desde el metodo "posClaveAEliminar").
            int posClaveAEliminar
                    = buscarApilandoLaClaveEnLosNodosDesde(nodoActual,
                            claveAEliminar,
                            pilaAncenstros);
            nodoActual = pilaAncenstros.pop();
            //Reserva el valor.
            V valorEliminando = nodoActual.getValor(posClaveAEliminar);
            //Si no esta en una hoja.
            if (!nodoActual.esHoja()) {
                //Busca un remplazo apilando la rama por la que pasa.
                NodoNVias<K, V> nodoRemplazo = nodoActual;
                int posValoresRemplazo = busValRemplazoApilandoDesde(
                        nodoRemplazo, posClaveAEliminar,
                        pilaAncenstros);
                nodoRemplazo = pilaAncenstros.pop();
                //Cambio los valores del nodo con la clave a eliminar con los 
                //valores del remplazo.
                nodoActual.setClave(posClaveAEliminar,
                        nodoRemplazo.getClave(posValoresRemplazo));
                nodoActual.setValor(posClaveAEliminar,
                        nodoRemplazo.getValor(posValoresRemplazo));
                //Elimina los valores remplazo recorriendo los valores de
                //izquierda a derecha.
                recorrerValoresHojaDesde(posValoresRemplazo, nodoRemplazo);
                //Equilibra los nodos que se requiera desapilando desde el
                //nodo remplazo.
                prestarOFusionar(nodoRemplazo, pilaAncenstros);
                //Finalemente retorno el valor.
                return valorEliminando;
            }
            //Elimina la clave en nodo actual recorriendo los valores de
            //izquierda a derecha.
            eliminarDatoDeNodos(nodoActual, posClaveAEliminar);
            //Equilibra los nodos que se requiera desapilando desde el
            //nodo Actual.
            prestarOFusionar(nodoActual, pilaAncenstros);
            //Retorno el valor.
            return valorEliminando;
        } catch (ClaveNoEncontrada ex) {
            Logger.getLogger(ArbolB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private int buscarApilandoLaClaveEnLosNodosDesde(
            NodoNVias<K, V> nodoActual, K claveABuscar,
            Stack<NodoNVias<K, V>> ancenstrosDelNodoActual)
            throws ClaveNoEncontrada {
        if (NodoNVias.esVacio(nodoActual)) {
            throw new ClaveNoEncontrada("ArbolB");
        }
        int posABajar = busPosBajar(nodoActual, claveABuscar);
        if (posABajar < nodoActual.cantidadDeClavesNoVacias()
                && claveABuscar.compareTo(nodoActual.getClave(posABajar))
                == 0) {
            ancenstrosDelNodoActual.add(nodoActual);
            return posABajar;
        }
        ancenstrosDelNodoActual.add(nodoActual);
        return buscarApilandoLaClaveEnLosNodosDesde(
                nodoActual.getHijo(posABajar), claveABuscar,
                ancenstrosDelNodoActual);
    }

    private int busValRemplazoApilandoDesde(NodoNVias<K, V> nodoActual, int posAbuscarRemplazo, Stack<NodoNVias<K, V>> pilaAncenstros) {
        //Se supone que no estamos en una arbolB vacio.
        //Se supone que no estamos en una hoja del arbolB.
        pilaAncenstros.add(nodoActual);
        nodoActual = nodoActual.getHijo(posAbuscarRemplazo);
        return busValMayorApilandoLosAncestros(nodoActual, pilaAncenstros);
    }

    private int busValMayorApilandoLosAncestros(NodoNVias<K, V> nodoActual, Stack<NodoNVias<K, V>> pilaAncenstros) {
        //Se supone que no empezamos en un nodo vacio nunca.
        int cantClaves = nodoActual.cantidadDeClavesNoVacias();
        if (nodoActual.esHoja()) {
            pilaAncenstros.add(nodoActual);
            return cantClaves - 1;
        }
        pilaAncenstros.add(nodoActual);
        return busValMayorApilandoLosAncestros(nodoActual.getHijo(cantClaves), pilaAncenstros);
    }

    private void recorrerValoresHojaDesde(int posValoresRemplazo, NodoNVias<K, V> nodoActual) {
        int canClavesNodoActual = nodoActual.cantidadDeClavesNoVacias();
        int i;
        for (i = posValoresRemplazo; i < canClavesNodoActual - 1; i++) {
            nodoActual.setClave(i, nodoActual.getClave(i + 1));
            nodoActual.setValor(i, nodoActual.getValor(i + 1));
        }
        nodoActual.setClave(i, (K) NodoNVias.datoVacio());
        nodoActual.setValor(i, (V) NodoNVias.datoVacio());
    }

    private void prestarOFusionar(NodoNVias<K, V> nodoActual,
            Stack<NodoNVias<K, V>> pilaAncenstros) {
        //Si la pila de ancestros esta vacia terminamos el proceso 
        //(nodo actual es raiz).
        if (pilaAncenstros.isEmpty()) {
            return;
        }
        int canClavesNodoActual = nodoActual.cantidadDeClavesNoVacias();
        //Si el nodo actual esta equilibrado termina el proceso.
        if (canClavesNodoActual >= NRO_MINIMO_DATOS) {
            return;
        }
        //Encontramos el hermano izquierdo y derecho del nodo actual. (Si no 
        //existe son nulos).
        NodoNVias<K, V> hermanoIzquierdo = NodoNVias.nodoVacio();
        NodoNVias<K, V> hermanoDerecho = NodoNVias.nodoVacio();
        
        NodoNVias<K, V> nodoPadre = pilaAncenstros.peek();
        int posNodoActualEnHijosDelPadre = busPosHijo(nodoPadre, nodoActual);
        if (posNodoActualEnHijosDelPadre == 0) {
            hermanoDerecho = nodoPadre.getHijo(posNodoActualEnHijosDelPadre + 1);
        }else if (posNodoActualEnHijosDelPadre == nodoPadre.cantidadDeClavesNoVacias()) {
            hermanoIzquierdo = nodoPadre.getHijo(posNodoActualEnHijosDelPadre - 1);
        }else{
        hermanoDerecho = nodoPadre.getHijo(posNodoActualEnHijosDelPadre + 1);
        hermanoIzquierdo = nodoPadre.getHijo(posNodoActualEnHijosDelPadre - 1);
        }
        
        //Se presta del hermano derecho de poder.
        if (!NodoNVias.esVacio(hermanoDerecho) && hermanoDerecho.cantidadDeClavesNoVacias() > NRO_MINIMO_DATOS) {
            prestarDeAdelante(nodoActual, pilaAncenstros.peek(), hermanoDerecho);
            //FINALIZA
            return;
        }
        //Se presta del hermano izquierdo de poder.
        if (!NodoNVias.esVacio(hermanoIzquierdo) && hermanoIzquierdo.cantidadDeClavesNoVacias() > NRO_MINIMO_DATOS) {
            prestarDeAtras(nodoActual, pilaAncenstros.peek(), hermanoIzquierdo);
            //FINALIZA
            return;
        }
        //Se fusiona con el hermano derecho de poder.
        if (!NodoNVias.esVacio(hermanoDerecho)) {
            fusionarAdelante(nodoActual, pilaAncenstros.peek(), hermanoDerecho);
            //Recursion para el padre.
            prestarOFusionar(pilaAncenstros.pop(), pilaAncenstros);
            return;
        }
        //Se fusiona con el hermano izquierdo.
        fusionarAtras(hermanoIzquierdo, pilaAncenstros.peek(), nodoActual);
        //Recursion para el padre.
        prestarOFusionar(pilaAncenstros.pop(), pilaAncenstros);
    }

    private int busPosHijo(NodoNVias<K, V> nodoActual, NodoNVias<K, V> nodoHijo) {
        //Suponemos que el nodo actual tiene los valores buscados en algun hijo
        int cantClavesNodoActual = nodoActual.cantidadDeClavesNoVacias();
        int i;
        for (i = 0; i < cantClavesNodoActual; i++) {
            if (nodoActual.getHijo(i) == nodoHijo) {
                return i;
            }
        }
        return i;
    }

    private void prestarDeAdelante(NodoNVias<K, V> nodoActual, NodoNVias<K, V> nodoPadre, NodoNVias<K, V> hermanoDerecho) {
        int posNodoActualEnElPadre = busPosHijo(nodoPadre, nodoActual);
        //Bajo los valores correspondiente del nodo padre a la derecha del actual
        int cantClavesNodoActual = nodoActual.cantidadDeClavesNoVacias();
        nodoActual.setClave(cantClavesNodoActual, nodoPadre.getClave(posNodoActualEnElPadre));
        nodoActual.setValor(cantClavesNodoActual, nodoPadre.getValor(posNodoActualEnElPadre));
        //Suben a la posicion correspondiente del nodo padre los valores de la primer posicion del hermano derecho
        nodoPadre.setClave(posNodoActualEnElPadre, hermanoDerecho.getClave(0));
        nodoPadre.setValor(posNodoActualEnElPadre, hermanoDerecho.getValor(0));
        //Reubicamos a la derecha del nodo actual el primer hijo del hermano derecho.
        cantClavesNodoActual++;
        nodoActual.setHijo(cantClavesNodoActual, hermanoDerecho.getHijo(0));
        //Recorremos los valores e hijos del hermano derecho en -1.
        recorrerValoresEHijosAtras(hermanoDerecho, 0);
    }

    private void recorrerValoresEHijosAtras(NodoNVias<K, V> nodoActual, int posInicialARecorrerValores){
        int canClavesNodoActual = nodoActual.cantidadDeClavesNoVacias();
        int i;
        for (i = posInicialARecorrerValores; i < canClavesNodoActual-1; i++) {
            nodoActual.setClave(i, nodoActual.getClave(i+1));
            nodoActual.setValor(i, nodoActual.getValor(i+1));
            nodoActual.setHijo(i, nodoActual.getHijo(i+1));
        }
        nodoActual.setHijo(i, nodoActual.getHijo(i+1));
        nodoActual.setClave(i, (K) NodoNVias.datoVacio());
        nodoActual.setValor(i, (V) NodoNVias.datoVacio());
        nodoActual.setHijo(i+1, NodoNVias.nodoVacio());
    }

    private void prestarDeAtras(NodoNVias<K, V> nodoActual, NodoNVias<K, V> nodoPadre, NodoNVias<K, V> hermanoIzquierdo) {
        int posNodoActualEnElPadre = busPosHijo(nodoPadre, nodoActual);
        //Recorro los valores del nodoActual en +1
        recorrerValoresEHijosAdelante(nodoActual, 0);
        //Bajo los Valores en la posicion correspondientes al nodo actual -1 
        //del padre a la primer poscion de los valores en el nodo actual.
        nodoActual.setClave(0, nodoPadre.getClave(posNodoActualEnElPadre-1));
        nodoActual.setValor(0, nodoPadre.getValor(posNodoActualEnElPadre-1));
        //En la posicion correspondiente -1 del padre colocamos los valores de 
        //la derecha del hermano izquierdo.
        int cantClavesHermanoIzquierdo = hermanoIzquierdo.cantidadDeClavesNoVacias();
        nodoPadre.setClave(posNodoActualEnElPadre-1, hermanoIzquierdo.getClave(cantClavesHermanoIzquierdo-1));
        nodoPadre.setValor(posNodoActualEnElPadre-1, hermanoIzquierdo.getValor(cantClavesHermanoIzquierdo-1));
        //Elimino los valores que subieron en el hermano izquierdo.
        hermanoIzquierdo.setClave(cantClavesHermanoIzquierdo-1, (K) NodoNVias.datoVacio());
        hermanoIzquierdo.setValor(cantClavesHermanoIzquierdo-1, (V) NodoNVias.datoVacio());
        //Reubico el hijo de la derecha del hermano izquierdo a la primer 
        //posicion del nodo actual
        nodoActual.setHijo(0, hermanoIzquierdo.getHijo(cantClavesHermanoIzquierdo));
        //Elimino la referencia reubicada del hermano izquierdo.
        hermanoIzquierdo.setHijo(cantClavesHermanoIzquierdo, NodoNVias.nodoVacio());
    }

    private void recorrerValoresEHijosAdelante(NodoNVias<K, V> nodoActual, int posInicialARecorrerValores) {
        int cantClaveNodoActual = nodoActual.cantidadDeClavesNoVacias();
        int i;
        for (i = cantClaveNodoActual; i > posInicialARecorrerValores; i--) {
            nodoActual.setClave(i, nodoActual.getClave(i-1));
            nodoActual.setValor(i, nodoActual.getValor(i-1));
            nodoActual.setHijo(i+1, nodoActual.getHijo(i-1));
        }
        
        nodoActual.setClave(i, (K) NodoNVias.datoVacio());
        nodoActual.setValor(i, (V) NodoNVias.datoVacio());
        nodoActual.setHijo(i+1, NodoNVias.nodoVacio());
    }

    private void fusionarAdelante(NodoNVias<K, V> nodoActual, NodoNVias<K, V> nodoPadre, NodoNVias<K, V> hermanoDerecho) {
        //Bajamos el valor correspondiente al nodo actual del nodo padre a la derecha del nodo actual.
        int posNodoActualEnELPadre = busPosHijo(nodoPadre, nodoActual);
        int cantidadClavesNodoActual = nodoActual.cantidadDeClavesNoVacias();
        nodoActual.setClave(cantidadClavesNodoActual, nodoPadre.getClave(posNodoActualEnELPadre));
        nodoActual.setValor(cantidadClavesNodoActual, nodoPadre.getValor(posNodoActualEnELPadre));
        //Colocamos todos los valores e hijos del hermano derecho al nodo actual.
        cantidadClavesNodoActual++;
        int canClavesHermanoDerecho = hermanoDerecho.cantidadDeClavesNoVacias();
        int i;
        int j = cantidadClavesNodoActual;
        for (i = 0; i < canClavesHermanoDerecho; i++) {
            nodoActual.setClave(j, hermanoDerecho.getClave(i));
            nodoActual.setValor(j, hermanoDerecho.getValor(i));
            nodoActual.setHijo(j, hermanoDerecho.getHijo(i));
            j++;
        }
        nodoActual.setHijo(j, hermanoDerecho.getHijo(i));
       //Recoremos los valores e hijos del nodo padre.
       recorrerValoresEHijosExceptoElPrimeroDesde(nodoPadre, posNodoActualEnELPadre);
       //Si el nodo padre era la raiz y esta vacio, la nueva raiz es el nodo actual.
       if(nodoPadre == this.raiz && nodoPadre.cantidadDeClavesNoVacias() == 0){
           this.raiz = nodoActual;
       }
    }

    private void recorrerValoresEHijosExceptoElPrimeroDesde(NodoNVias<K, V> nodoActual, int posDelValorInicialARecorrer) {
        NodoNVias<K, V> nodoRescatado = nodoActual.getHijo(posDelValorInicialARecorrer);
        recorrerValoresEHijosAtras(nodoActual, posDelValorInicialARecorrer);
        nodoActual.setHijo(posDelValorInicialARecorrer, nodoRescatado);
    }

    private void fusionarAtras(NodoNVias<K, V> hermanoIzquierdo, NodoNVias<K, V> nodoPadre, NodoNVias<K, V> nodoActual) {
        //Se baja los valores del padre relativo al hermanoIzquierdo.
        int posDelHermanoIzquierdoEnELPadre = busPosHijo(nodoPadre, hermanoIzquierdo);
        int cantClavesEnElHermanoIzquierdo = hermanoIzquierdo.cantidadDeClavesNoVacias();
        hermanoIzquierdo.setClave(cantClavesEnElHermanoIzquierdo, nodoPadre.getClave(posDelHermanoIzquierdoEnELPadre));
        hermanoIzquierdo.setValor(cantClavesEnElHermanoIzquierdo, nodoPadre.getValor(posDelHermanoIzquierdoEnELPadre));
        cantClavesEnElHermanoIzquierdo++;
        //Se copian todos los valores e hijos del nodo actual al hermano Izquierdo.
        int canClavesNodoActual = nodoActual.cantidadDeClavesNoVacias();
        int i;
        int j = cantClavesEnElHermanoIzquierdo;
        for (i = 0; i < canClavesNodoActual; i++) {
            hermanoIzquierdo.setClave(j, nodoActual.getClave(i));
            hermanoIzquierdo.setValor(j, nodoActual.getValor(i));
            hermanoIzquierdo.setHijo(j, nodoActual.getHijo(i));
            j++;
        }
        hermanoIzquierdo.setHijo(j, nodoActual.getHijo(i));
       //Recoremos los valores e hijos del nodo padre.
       recorrerValoresEHijosExceptoElPrimeroDesde(nodoPadre, posDelHermanoIzquierdoEnELPadre);
       //Si el nodo padre era la raiz y esta vacio, la nueva raiz es el nodo actual.
       if(nodoPadre == this.raiz && nodoPadre.cantidadDeClavesNoVacias() == 0){
           this.raiz = hermanoIzquierdo;
       }
    }   
}
