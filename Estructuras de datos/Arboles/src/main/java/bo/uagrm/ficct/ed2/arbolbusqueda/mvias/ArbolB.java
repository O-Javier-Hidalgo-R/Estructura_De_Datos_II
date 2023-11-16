/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.mvias;

import java.util.Stack;

public class ArbolB<K extends Comparable<K>, V> extends ArbolMVias<K, V> {

    private final int NRO_MINIMO_HIJOS;
    private final int NRO_MINIMO_DATOS;
    private final int NRO_MAXIMO_HIJOS;
    private final int NRO_MAXIMO_DATOS;
    private final int POSICION_INVALIDA = -1;
    private final int VAL_MEDIO = (orden-1) / 2;

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
    private int existeClaveEnNodo(NodoNVias<K, V> nodoActual, K claveAInsertar) {
        int result = POSICION_INVALIDA;
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            if (claveAInsertar.compareTo(nodoActual.getClave(i)) == 0) {
                result = i;
            }
        }
        return result;
    }

    private void dividir(NodoNVias<K, V> nodoActual,
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
    }

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

    @Override
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
            nodoActual.setClave(posicionDelPredecesor, clavePredecesora);
            nodoActual.setValor(posicionDelPredecesor, valorPredecesor);
            if (nodoDelPredecesor.cantidadDeClavesNoVacias() < this.NRO_MINIMO_DATOS) {
                this.prestarseOFusionar(nodoDelPredecesor, pilaDeAncestros);
            }
        }
        return valorARetornar;
    }

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
    private NodoNVias<K, V> buscarNodoClave(K ClaveABuscar, Stack<NodoNVias<K, V>> pilaDeAncestros) {
        return buscarNodoClave(ClaveABuscar, super.raiz, pilaDeAncestros);
    }

    private NodoNVias<K, V> buscarNodoClave(K ClaveABuscar, NodoNVias<K, V> nodoActual, Stack<NodoNVias<K, V>> pilaDeAncestros) {
        int posABajar = super.busPosBajar(nodoActual, ClaveABuscar);
        if (ClaveABuscar.compareTo(nodoActual.getClave(posABajar)) == 0) {
            return nodoActual;
        } else {
            pilaDeAncestros.add(nodoActual);
            return buscarNodoClave(ClaveABuscar, nodoActual.getHijo(posABajar), pilaDeAncestros);
        }
    }

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

    private NodoNVias<K, V> busNodoDelPredecesor(Stack<NodoNVias<K, V>> pilaDeAncestros, NodoNVias<K, V> nodoSalida) {
        NodoNVias<K, V> nodoActual = nodoSalida;
        while (!NodoNVias.esVacio(nodoActual)) {
            pilaDeAncestros.push(nodoActual);
            nodoActual = nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias());
        }
        nodoActual = pilaDeAncestros.pop();
        return nodoActual;
    }

    private void prestarseOFusionar(NodoNVias<K, V> nodoActual, Stack<NodoNVias<K, V>> pilaDeAncestros) {
        NodoNVias<K, V> nodoPadre = pilaDeAncestros.peek();
        NodoNVias<K, V> nodoHermanoIzquierdo = NodoNVias.nodoVacio();
        NodoNVias<K, V> nodoHermanoDerecho = NodoNVias.nodoVacio();
        int posBajoElPadre = 0;

        while (nodoPadre.getHijo(posBajoElPadre) != nodoActual) {
            posBajoElPadre++;
        }

        if (posBajoElPadre + 1 < nodoPadre.cantidadDeClavesNoVacias() + 1) {
            nodoHermanoDerecho = nodoPadre.getHijo(posBajoElPadre + 1);
        }

        if (0 < nodoPadre.cantidadDeClavesNoVacias() - 1) {
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
            for (i = lim; i > 0; i++) {
                nodoActual.setClave(i, nodoActual.getClave(i+1));
                nodoActual.setValor(i, nodoActual.getValor(i+1));
                nodoActual.setHijo(i, nodoActual.getHijo(i+1));
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
    }

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

    private void apilarRamaHastaHoja(Stack<NodoNVias<K, V>> ramaHastaHoja, K claveAInsertar) {
        int posABajar = busPosBajar(ramaHastaHoja.peek(), claveAInsertar);
        if (ramaHastaHoja.peek().getClave(posABajar).compareTo(claveAInsertar) == 0) {
            return;
        }
        if (!ramaHastaHoja.peek().estanClavesLlenas()) {
            return;
        }
        ramaHastaHoja.add(ramaHastaHoja.peek().getHijo(posABajar));
        apilarRamaHastaHoja(ramaHastaHoja, claveAInsertar);
    }

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
            nodoActual.setHijo(j+1, NodoNVias.nodoVacio());
        }
    }

    private void fusionConElDeAdelante(NodoNVias<K, V> nodoActual, Stack<NodoNVias<K, V>> pilaDeAncestros) {
        if(nodoActual.cantidadDeClavesNoVacias() > NRO_MINIMO_DATOS){
            return;
        }
        NodoNVias<K, V> nodoPadre = pilaDeAncestros.peek();
        int posNodoActualEnElPadre = busPosNodoHijo(nodoPadre, nodoActual);
        NodoNVias<K, V> hermanoIzquierdo = nodoActual;
        K claveABajar = nodoPadre.getClave(posNodoActualEnElPadre);
        V ValorABajar = nodoPadre.getValor(posNodoActualEnElPadre);
        int ultimaPosVaciaHermanoIzquierdo = hermanoIzquierdo.cantidadDeClavesNoVacias();
        NodoNVias<K, V>hermanoDerecho = nodoPadre.getHijo(posNodoActualEnElPadre+1);
        
        
        hermanoIzquierdo.setClave(ultimaPosVaciaHermanoIzquierdo, claveABajar);
        hermanoIzquierdo.setValor(ultimaPosVaciaHermanoIzquierdo, ValorABajar);
        ultimaPosVaciaHermanoIzquierdo++;
        int i;
        int limNodoHermanoDerecho = hermanoDerecho.cantidadDeClavesNoVacias();
        for (i = 0; i < limNodoHermanoDerecho; i++) {
            hermanoIzquierdo.setClave(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getClave(i));
            hermanoIzquierdo.setValor(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getValor(i));
            hermanoIzquierdo.setHijo(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getHijo(i));
            ultimaPosVaciaHermanoIzquierdo++;
        }
        hermanoIzquierdo.setHijo(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getHijo(i));
        int limNodoPadre = nodoPadre.cantidadDeClavesNoVacias();
        for (i = posNodoActualEnElPadre; i < limNodoPadre; i++) {
            nodoPadre.setClave(i, nodoPadre.getClave(i+1));
            nodoPadre.setValor(i, nodoPadre.getValor(i+1));
            nodoPadre.setHijo(i+1, nodoPadre.getHijo(i+2));
        }
        nodoPadre.setHijo(posNodoActualEnElPadre + 1, hermanoDerecho);
        nodoPadre.setHijo(posNodoActualEnElPadre, hermanoIzquierdo);
        prestarseOFusionar(pilaDeAncestros.pop(), pilaDeAncestros);
    }

    private void fusionConElDeAtras(NodoNVias<K, V> nodoActual, Stack<NodoNVias<K, V>> pilaDeAncestros) {
        if(nodoActual.cantidadDeClavesNoVacias() > NRO_MINIMO_DATOS){
            return;
        }
        NodoNVias<K, V> nodoPadre = pilaDeAncestros.peek();
        int posNodoActualEnElPadre = busPosNodoHijo(nodoPadre, nodoActual);
        NodoNVias<K, V> hermanoIzquierdo = nodoPadre.getHijo(posNodoActualEnElPadre - 1);
        K claveABajar = nodoPadre.getClave(posNodoActualEnElPadre-1);
        V ValorABajar = nodoPadre.getValor(posNodoActualEnElPadre-1);
        eliminarDatoDeNodos(nodoPadre, posNodoActualEnElPadre-1);
        int ultimaPosVaciaHermanoIzquierdo = hermanoIzquierdo.cantidadDeClavesNoVacias();
        NodoNVias<K, V>hermanoDerecho = nodoActual;
        
        hermanoIzquierdo.setClave(ultimaPosVaciaHermanoIzquierdo, claveABajar);
        hermanoIzquierdo.setValor(ultimaPosVaciaHermanoIzquierdo, ValorABajar);
        ultimaPosVaciaHermanoIzquierdo++;
        int i;
        int limNodoHermanoDerecho = hermanoDerecho.cantidadDeClavesNoVacias();
        for (i = 0; i < limNodoHermanoDerecho; i++) {
            hermanoIzquierdo.setClave(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getClave(i));
            hermanoIzquierdo.setValor(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getValor(i));
            hermanoIzquierdo.setHijo(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getHijo(i));
            ultimaPosVaciaHermanoIzquierdo++;
        }
        hermanoIzquierdo.setHijo(ultimaPosVaciaHermanoIzquierdo, hermanoDerecho.getHijo(i));
        int limNodoPadre = nodoPadre.cantidadDeClavesNoVacias();
        for (i = posNodoActualEnElPadre; i < limNodoPadre; i++) {
            nodoPadre.setClave(i, nodoPadre.getClave(i+1));
            nodoPadre.setValor(i, nodoPadre.getValor(i+1));
            nodoPadre.setHijo(i+1, nodoPadre.getHijo(i+2));
        }
        nodoPadre.setHijo(posNodoActualEnElPadre, hermanoDerecho);
        nodoPadre.setHijo(posNodoActualEnElPadre - 1, hermanoIzquierdo);
        prestarseOFusionar(pilaDeAncestros.pop(), pilaDeAncestros);
    }

    private int busPosNodoHijo(NodoNVias<K, V> nodoPadre, NodoNVias<K, V> nodoActual) {
        int limNodoPadre = nodoPadre.cantidadDeClavesNoVacias();
        for (int i = 0; i <= limNodoPadre; i++) {
            if(nodoPadre.getHijo(i) == nodoActual){
                return i;
            }
        }
        return -1;
    }
}
