/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.mvias;

import bo.uagrm.ficct.ed2.arbolbusqueda.IArbolBusqueda;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArbolMVias<K extends Comparable<K>, V>
        implements IArbolBusqueda<K, V> {

    protected NodoNVias<K, V> raiz;
    protected int orden;

    public ArbolMVias(int orden) {
        this.orden = orden;
    }

    public NodoNVias<K, V> getRaiz() {
        return this.raiz;
    }

    @Override
    public void vaciar() {
        this.raiz = NodoNVias.nodoVacio();
    }

    @Override
    public boolean esVacio() {
        return this.raiz == NodoNVias.nodoVacio();
    }

    @Override
    public void insertar(K clave, V valor) {
        this.raiz = insertar(this.raiz, clave, valor);
    }

    private NodoNVias<K, V> insertar(NodoNVias<K, V> nodoActual,
            K claveAInsertar, V valorAInsertar) {
        if (NodoNVias.esVacio(nodoActual)) {
            return new NodoNVias<>(this.orden, claveAInsertar,
                    valorAInsertar);
        }
        if (!nodoActual.estanClavesLlenas()) {
            nodoActual = colocarDatoOrdenadamente(nodoActual,
                    claveAInsertar, valorAInsertar);
            return nodoActual;
        }
        int posBajar = busPosBajar(nodoActual, claveAInsertar);
        nodoActual.setHijo(posBajar, insertar(
                nodoActual.getHijo(posBajar), claveAInsertar,
                valorAInsertar));
        return nodoActual;
    }

    @Override
    public V buscar(K clave) {
        return buscar(this.raiz, clave);
    }

    @Override
    public boolean contiene(K clave) {
        return buscar(clave) != NodoNVias.datoVacio();
    }

    @Override
    public List<K> recorridoPorNiveles() {
        List<K> result = new LinkedList<>();
        Queue<NodoNVias> cola = new LinkedList<>();
        int nodosNivel;
        NodoNVias<K, V> nodoActual = raiz;

        if (NodoNVias.esVacio(raiz)) {
            return null;
        }

        cola.add(raiz);
        while (!cola.isEmpty()) {
            nodosNivel = cola.size();
            while (nodosNivel > 0) {
                nodoActual = cola.poll();
                for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias() + 1; i++) {
                    if (!nodoActual.esHijoVacio(i)) {
                        cola.add(nodoActual.getHijo(i));
                    }
                }
                result = colocarClavesLista(nodoActual, result);
                nodosNivel--;
            }
        }
        return result;
    }

    @Override
    public List<K> recorridoPreOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoPreOrden(this.raiz, recorrido);
        return recorrido;
    }

    @Override
    public List<K> recorridoInOrden() {
        List<K> result = new ArrayList<>();
        if (NodoNVias.esVacio(raiz)) {
            return null;
        }
        recorridoInOrden(raiz, result);
        return result;
    }

    @Override
    public List<K> recorridoPosOrden() {
        List<K> result = new ArrayList<>();
        if (NodoNVias.esVacio(raiz)) {
            return null;
        }
        recorridoPostOrden(raiz, result);
        return result;
    }

    @Override
    public V eliminar(K claveAEliminar) {
        parClaveValor<K, V> datosEliminados = new parClaveValor<>();
        this.raiz = eliminar(this.raiz, claveAEliminar, datosEliminados);
        return datosEliminados.valor;
    }

    @Override
    public int size() {
        return size(raiz);
    }

    @Override
    public int altura() {
        return nivel() + 1;
    }

    @Override
    public int nivel() {
        return nivel(raiz);
    }

    protected NodoNVias<K, V> colocarDatoOrdenadamente(NodoNVias<K, V> nodoActual, K clave, V valor) {
        int i;
        int pos;

        i = 0;
        while (i < nodoActual.cantidadDeClavesNoVacias() && clave.compareTo(nodoActual.getClave(i)) > 0) {
            i++;
        }
        if (nodoActual.getClave(i) == NodoNVias.datoVacio()) {
            nodoActual.setClave(i, clave);
            nodoActual.setValor(i, valor);
            return nodoActual;
        } else if (clave.compareTo(nodoActual.getClave(i)) == 0) {
            nodoActual.setValor(i, valor);
            return nodoActual;
        } else {
            pos = i;
            i = nodoActual.cantidadDeClavesNoVacias();
            while (i > pos) {
                nodoActual.setClave(i, nodoActual.getClave(i - 1));
                nodoActual.setValor(i, nodoActual.getValor(i - 1));
                i--;
            }
            nodoActual.setClave(pos, clave);
            nodoActual.setValor(pos, valor);
            return nodoActual;
        }
    }

    protected int busPosBajar(NodoNVias<K, V> nodoActual, K clave) {
        int lim = nodoActual.cantidadDeClavesNoVacias();
        int i = 0;
        while (i < lim && clave.compareTo(nodoActual.getClave(i)) > 0) {
            i++;
        }
        return i;
    }

    private List<K> colocarClavesLista(NodoNVias<K, V> nodoActual, List<K> result) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            result.add(nodoActual.getClave(i));
        }
        return result;
    }

    private NodoNVias<K, V> recorrerDatos(NodoNVias<K, V> nodoActual, int posABajar) {
        int i;
        int lim = nodoActual.cantidadDeClavesNoVacias();
        for (i = posABajar; i < lim - 1; i++) {
            nodoActual.setClave(i, nodoActual.getClave(i + 1));
            nodoActual.setValor(i, nodoActual.getValor(i + 1));
        }
        nodoActual.setClave(i, (K) NodoNVias.datoVacio());
        nodoActual.setValor(i, (V) NodoNVias.datoVacio());
        return nodoActual;
    }

    private parClaveValor<K, V> datosMenores(NodoNVias<K, V> nodoActual) {
        if (nodoActual.getHijo(0) == NodoNVias.nodoVacio()) {
            parClaveValor<K, V> datosMenores = new parClaveValor<>();
            datosMenores.clave = nodoActual.getClave(0);
            datosMenores.valor = nodoActual.getValor(0);
            return datosMenores;
        }
        return datosMenores(nodoActual.getHijo(0));
    }

    private parClaveValor<K, V> datosMayores(NodoNVias<K, V> nodoActual) {
        int posUltimaClave = nodoActual.cantidadDeClavesNoVacias() - 1;
        if (nodoActual.getHijo(posUltimaClave + 1) == NodoNVias.nodoVacio()) {
            parClaveValor<K, V> datosMenores = new parClaveValor<>();
            datosMenores.clave = nodoActual.getClave(posUltimaClave);
            datosMenores.valor = nodoActual.getValor(posUltimaClave);
            return datosMenores;
        }
        return datosMayores(nodoActual.getHijo(posUltimaClave + 1));
    }

    private parClaveValor<K, V> buscarValSucesorInOrden(NodoNVias<K, V> nodoActual, int i) {
        if (nodoActual.getHijo(i + 1) != NodoNVias.nodoVacio()) {
            return datosMenores(nodoActual.getHijo(i + 1));
        }
        return new parClaveValor<>(nodoActual.getClave(i + 1), nodoActual.getValor(i + 1));
    }

    private parClaveValor<K, V> buscarValPredecesorInOrden(NodoNVias<K, V> nodoActual, int i) {
        if (nodoActual.getHijo(i) != NodoNVias.nodoVacio()) {
            return datosMayores(nodoActual.getHijo(i));
        }
        return new parClaveValor<>(nodoActual.getClave(i - 1), nodoActual.getValor(i - 1));
    }

    private class parClaveValor<K extends Comparable<K>, V> {

        public K clave;
        public V valor;

        public parClaveValor() {
            this.clave = (K) NodoNVias.datoVacio();
            this.valor = (V) NodoNVias.datoVacio();
        }

        public parClaveValor(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    private NodoNVias<K, V> eliminar(NodoNVias<K, V> nodoActual,
            K claveAEliminar, parClaveValor<K, V> datosEliminados) {
        if (NodoNVias.esVacio(nodoActual)) {
            throw new IllegalArgumentException("La clave a eliminar no existe"
                    + " en el arbol");
        }
        int posABajar = busPosBajar(nodoActual, claveAEliminar);
        if (posABajar < nodoActual.cantidadDeClavesNoVacias()
                && claveAEliminar.compareTo(
                        nodoActual.getClave(posABajar)) == 0) {
            if (datosEliminados.clave == NodoNVias.datoVacio()) {
                datosEliminados.clave = nodoActual.getClave(posABajar);
                datosEliminados.valor = nodoActual.getValor(posABajar);
            }
            if (nodoActual.esHoja()) {
                nodoActual = recorrerDatos(nodoActual, posABajar);
                if (nodoActual.cantidadDeClavesNoVacias() == 0) {
                    return NodoNVias.nodoVacio();
                }
                return nodoActual;
            }
            parClaveValor<K, V> valoresDeRemplazo;
            if (this.hayHijosDelante(nodoActual, posABajar)) {
                valoresDeRemplazo = this.buscarValSucesorInOrden(nodoActual,
                        posABajar);
            } else {
                valoresDeRemplazo = this.buscarValPredecesorInOrden(nodoActual,
                        posABajar);
            }
            nodoActual = eliminar(nodoActual,
                    valoresDeRemplazo.clave, datosEliminados);
            nodoActual.setClave(posABajar, valoresDeRemplazo.clave);
            nodoActual.setValor(posABajar, valoresDeRemplazo.valor);
            return nodoActual;
        }
        nodoActual.setHijo(posABajar, eliminar(
                nodoActual.getHijo(posABajar), claveAEliminar,
                datosEliminados));
        return nodoActual;
    }
    /*
    private NodoNVias<K, V> eliminar(NodoNVias<K, V> nodoActual,
            K claveAEliminar, parClaveValor<K, V> datosEliminados) {
        if (NodoNVias.esVacio(nodoActual)) {
            throw new IllegalArgumentException("La clave a eliminar no existe"
                    + " en el arbol");
        }
        int i;
        int lim = nodoActual.cantidadDeClavesNoVacias();
        for (i = 0; i < lim; i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveAEliminar.compareTo(claveActual) == 0) {
                if (datosEliminados.clave == NodoNVias.datoVacio()) {
                    datosEliminados = new parClaveValor<>(claveActual,
                            nodoActual.getValor(i));
                }
                if (nodoActual.esHoja()) {
                    recorrerDatos(nodoActual, i);
                    if (nodoActual.cantidadDeClavesNoVacias() == 0) {
                        return NodoNVias.nodoVacio();
                    }
                    return nodoActual;
                }
                //si llego aqui la clave esta en un nodo no hoja
                parClaveValor<K, V> valoresDeRemplazo;
                if (this.hayHijosDelante(nodoActual, i)) {
                    valoresDeRemplazo = this.buscarValSucesorInOrden(nodoActual,
                            i);
                } else {
                    valoresDeRemplazo = this.buscarValPredecesorInOrden(
                            nodoActual, i);
                }
                nodoActual = eliminar(nodoActual,
                        valoresDeRemplazo.clave, datosEliminados);
                nodoActual.setClave(i, valoresDeRemplazo.clave);
                nodoActual.setValor(i, valoresDeRemplazo.valor);
                return nodoActual;
            }
            if (claveAEliminar.compareTo(claveActual) < 0) {
                nodoActual.setHijo(i, eliminar(
                        nodoActual.getHijo(i), claveAEliminar,
                        datosEliminados));
                return nodoActual;
            }
        }
        nodoActual.setHijo(i + 1, eliminar(nodoActual.getHijo(i + 1),
                claveAEliminar, datosEliminados));
        return nodoActual;
    }
    */

    private boolean hayHijosDelante(NodoNVias<K, V> nodoActual, int i) {
        int lim = nodoActual.cantidadDeClavesNoVacias();
        int j;
        for (j = i + 1; j <= lim; j++) {
            if (nodoActual.getHijo(j) != NodoNVias.nodoVacio()) {
                return true;
            }
        }
        return false;
    }

    protected K buscarClavePredecesoraInOrden(NodoNVias<K, V> nodoActual, int i) {
        int j;
        for (j = i - 1; j >= 0; j--) {
            if (!nodoActual.esHijoVacio(j + 1)) {
                return nodoActual.getHijo(j + 1).getClave(nodoActual.getHijo(j + 1).cantidadDeClavesNoVacias() - 1);
            }
            if (!nodoActual.esClaveVacia(j)) {
                return nodoActual.getClave(j);
            }
        }
        return nodoActual.getHijo(0).getClave(nodoActual.getHijo(0).cantidadDeClavesNoVacias() - 1);
    }

    private V buscar(NodoNVias<K, V> nodoActual, K clave) {
        if (NodoNVias.esVacio(nodoActual)) {
            return (V) NodoNVias.datoVacio();
        }
        int i;
        for (i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            if (clave.compareTo(nodoActual.getClave(i)) < 0) {
                return buscar(nodoActual.getHijo(i), clave);
            }
            if (clave.compareTo(nodoActual.getClave(i)) == 0) {
                return nodoActual.getValor(i);
            }
        }
        return buscar(nodoActual.getHijo(i), clave);
    }

    private void recorridoPreOrden(NodoNVias<K, V> nodoActual, List<K> recorridoPreOrden) {
        if(NodoNVias.esVacio(nodoActual)){
            return;
        }
        int lim = nodoActual.cantidadDeClavesNoVacias();
        for (int i = 0; i < lim; i++) {
            recorridoPreOrden.add(nodoActual.getClave(i));
            recorridoPreOrden(nodoActual.getHijo(i), recorridoPreOrden);
        }
        if(!NodoNVias.esVacio(nodoActual.getHijo(lim))){
            recorridoPreOrden(nodoActual.getHijo(lim), recorridoPreOrden);
        }
    }

    private void recorridoInOrden(NodoNVias<K, V> nodoActual, List<K> result) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            if (!NodoNVias.esVacio(nodoActual.getHijo(i))) {
                recorridoInOrden(nodoActual.getHijo(i), result);
            }
            result.add(nodoActual.getClave(i));
        }
        if (!NodoNVias.esVacio(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()))) {
            recorridoInOrden(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()), result);
        }
    }

    private void recorridoPostOrden(NodoNVias<K, V> nodoActual, List<K> result) {
        if (!nodoActual.esHijoVacio(0)) {
            recorridoPostOrden(nodoActual.getHijo(0), result);
        }
        if (!nodoActual.esHijoVacio(1)) {
            recorridoPostOrden(nodoActual.getHijo(1), result);
        }
        result.add(nodoActual.getClave(0));
        for (int i = 1; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            if (!nodoActual.esHijoVacio(i + 1)) {
                recorridoPostOrden(nodoActual.getHijo(i + 1), result);
            }
            result.add(nodoActual.getClave(i));
        }
    }

    private int size(NodoNVias<K, V> nodoActual) {
        int acu = 0;
        for (int i = 0; i <= nodoActual.cantidadDeClavesNoVacias(); i++) {
            if (!nodoActual.esHijoVacio(i)) {
                acu += size(nodoActual.getHijo(i));
            }
        }
        return nodoActual.cantidadDeClavesNoVacias() + acu;
    }

    private int nivel(NodoNVias<K, V> nodoActual) {
        int nivelMayor = 0;
        int supuestoNivelMayor = 0;
        for (int i = 0; i <= nodoActual.cantidadDeClavesNoVacias(); i++) {
            if (!nodoActual.esHijoVacio(i)) {
                supuestoNivelMayor = nivel(nodoActual.getHijo(i)) + 1;
            }
            if (supuestoNivelMayor > nivelMayor) {
                nivelMayor = supuestoNivelMayor;
            }
        }
        return nivelMayor;
    }

}
