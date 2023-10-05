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
        implements IArbolBusqueda<K, V>
{   
    protected NodoNVias<K, V> raiz;
    protected int orden;

    public ArbolMVias(int orden){
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
        this.raiz = insertar(this. raiz, clave, valor);
    }
    
    private NodoNVias<K, V> insertar(NodoNVias<K, V> nodoActual, K clave, V valor) {
        if (NodoNVias.esVacio(nodoActual)) {
            return new NodoNVias<>(this.orden, clave, valor);
        }
        if(nodoActual.esHoja() && !nodoActual.estanClavesLlenas()){
            nodoActual = colocarDatoOrdenadamente(nodoActual, clave, valor);
        }else{
            int posBajar = busPosBajar(nodoActual, clave);
            nodoActual.setHijo(posBajar, insertar(nodoActual.getHijo(posBajar), clave, valor));
        }
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
        
        if(NodoNVias.esVacio(raiz)){
            return null;
        }
        
        cola.add(raiz);
        while(!cola.isEmpty()){
            nodosNivel = cola.size();
            while(nodosNivel > 0){
                nodoActual = cola.poll();
                for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias()+1; i++) {
                    if(!nodoActual.esHijoVacio(i)){
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
        List<K> result = new ArrayList<>();
        if(NodoNVias.esVacio(raiz)){
            return null;
        }
        recorridoPreOrden(raiz, result);
        return result;
    }

    @Override
    public List<K> recorridoInOrden() {
        List<K> result = new ArrayList<>();
        if(NodoNVias.esVacio(raiz)){
            return null;
        }
        recorridoInOrden(raiz, result);
        return result;
    }

    @Override
    public List<K> recorridoPostOrden() {
        List<K> result = new ArrayList<>();
        if(NodoNVias.esVacio(raiz)){
            return null;
        }
        recorridoPostOrden(raiz, result);
        return result;
    }

    @Override
    public V eliminar(K clave) throws IllegalArgumentException {
        V valorAEliminar = (V) NodoNVias.datoVacio();
        this.raiz = eliminar(raiz, clave, valorAEliminar);
        return valorAEliminar;
    }

    @Override
    public int size() {
        return size(raiz);
    }

    @Override
    public int altura() {
        return nivel()+1;
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
        if(nodoActual.getClave(i) == NodoNVias.datoVacio()){
            nodoActual.setClave(i, clave);
            nodoActual.setValor(i, valor);
            return nodoActual;
        }else if(clave.compareTo(nodoActual.getClave(i)) == 0){
            nodoActual.setValor(i, valor);
            return nodoActual;
        }else{
            pos = i;
            i = nodoActual.cantidadDeClavesNoVacias();
            while(i > pos){
                nodoActual.setClave(i, nodoActual.getClave(i-1));
                nodoActual.setValor(i, nodoActual.getValor(i-1));
                i--;
            }
            nodoActual.setClave(pos, clave);
            nodoActual.setValor(pos, valor);
            return nodoActual;
        }
    }

    protected int busPosBajar(NodoNVias<K, V> nodoActual, K clave) {
        int i = 0;
        while(i < nodoActual.cantidadDeClavesNoVacias()){
            if(clave.compareTo(nodoActual.getClave(i)) < 0){
                return i;
            }
            i++;
        }
        return i++;
    }

    private List<K> colocarClavesLista(NodoNVias<K, V> nodoActual, List<K> result) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            result.add(nodoActual.getClave(i));
        }
        return result;
    }

    private NodoNVias<K, V> eliminar(NodoNVias<K, V> nodoActual, K claveAEliminar, V valorAEliminar) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if(claveAEliminar.compareTo(claveActual) == 0){
                valorAEliminar = nodoActual.getValor(i);
                if(nodoActual.esHoja()){
                    this.eliminarDatosNodo(nodoActual, i);
                    if(nodoActual.cantidadDeClavesNoVacias() == 0){
                        return NodoNVias.nodoVacio();
                    }
                    return nodoActual;
                }
                //si llego aqui la clave esta en un nodo no hoja
                K claveDeRemplazo;
                if(this.hayHijosDelante(nodoActual, i)){
                    claveDeRemplazo = this.buscarClaveSucesorInOrden(nodoActual, i);
                }else{
                    claveDeRemplazo = this.buscarClavePredecesoraInOrden(nodoActual, i);
                }
                V valorDeRemplazo = this.buscar(claveDeRemplazo);
                nodoActual = eliminar(nodoActual, claveDeRemplazo, valorAEliminar);
                nodoActual.setClave(i, claveDeRemplazo);
                nodoActual.setValor(i, valorDeRemplazo);
                return nodoActual;
            }
            if(claveAEliminar.compareTo(claveActual)<0){
                NodoNVias<K, V> supuestoNuevoHijo = this.eliminar(nodoActual.getHijo(i), claveAEliminar, valorAEliminar);
                nodoActual.setHijo(i, supuestoNuevoHijo);
                return nodoActual;
            }
        }
        return null;
    }

    private void eliminarDatosNodo(NodoNVias<K, V> nodoActual, int i) {
        int j;
        for (j = i; j < nodoActual.cantidadDeClavesNoVacias()-1; j++) {
            nodoActual.setClave(j, nodoActual.getClave(j+1));
            nodoActual.setValor(j, nodoActual.getValor(j+1));
        }
        nodoActual.setClave(j, (K) NodoNVias.datoVacio());
        nodoActual.setValor(j, (V) NodoNVias.datoVacio());
    }

    private boolean hayHijosDelante(NodoNVias<K, V> nodoActual, int i) {
        int j;
        for (j = i+1; j < nodoActual.cantidadDeClavesNoVacias(); j++) {
            if(!nodoActual.esHijoVacio(j)){
                return true;
            }
            if(!nodoActual.esClaveVacia(j)){
                return true;
            }
        }
        return !nodoActual.esHijoVacio(j);
    }

    private K buscarClaveSucesorInOrden(NodoNVias<K, V> nodoActual, int i) {
        int j;
        for (j = i+1; j < nodoActual.cantidadDeClavesNoVacias(); j++) {
            if(!nodoActual.esHijoVacio(j)){
                return nodoActual.getHijo(j).getClave(0);
            }
            if(!nodoActual.esClaveVacia(j)){
                return nodoActual.getClave(j);
            }
        }
        return nodoActual.getHijo(j).getClave(0);
    }

    protected K buscarClavePredecesoraInOrden(NodoNVias<K, V> nodoActual, int i) {
        int j;
        for (j = i-1; j >= 0; j--) {
            if(!nodoActual.esHijoVacio(j+1)){
                return nodoActual.getHijo(j+1).getClave(nodoActual.getHijo(j+1).cantidadDeClavesNoVacias()-1);
            }
            if(!nodoActual.esClaveVacia(j)){
                return nodoActual.getClave(j);
            }
        }
        return nodoActual.getHijo(0).getClave(nodoActual.getHijo(0).cantidadDeClavesNoVacias()-1);
    }

    private V buscar(NodoNVias<K, V> nodoActual, K clave) {
        if(NodoNVias.esVacio(nodoActual)){
            return (V) NodoNVias.datoVacio();
        }
        int i;
        for (i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            if(clave.compareTo(nodoActual.getClave(i))<0){
                return buscar(nodoActual.getHijo(i), clave);
            }
            if(clave.compareTo(nodoActual.getClave(i)) == 0){
                return nodoActual.getValor(i);
            }
        }
        return buscar(nodoActual.getHijo(i), clave);
    }

    private void recorridoPreOrden(NodoNVias<K, V> nodoActual, List<K> result) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            result.add(nodoActual.getClave(i));
            if(!NodoNVias.esVacio(nodoActual.getHijo(i))){
                recorridoPreOrden(nodoActual.getHijo(i), result);
            }
        }
        if(!NodoNVias.esVacio(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()))){
            recorridoPreOrden(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()), result);
        }
    }

    private void recorridoInOrden(NodoNVias<K, V> nodoActual, List<K> result) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            if(!NodoNVias.esVacio(nodoActual.getHijo(i))){
                recorridoInOrden(nodoActual.getHijo(i), result);
            }
            result.add(nodoActual.getClave(i));
        }
        if(!NodoNVias.esVacio(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()))){
            recorridoInOrden(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()), result);
        }
    }

    private void recorridoPostOrden(NodoNVias<K, V> nodoActual, List<K> result) {
        if(!nodoActual.esHijoVacio(0)){
            recorridoPostOrden(nodoActual.getHijo(0), result);
        }
        if(!nodoActual.esHijoVacio(1)){
            recorridoPostOrden(nodoActual.getHijo(1), result);
        }
        result.add(nodoActual.getClave(0));
        for (int i = 1; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            if(!nodoActual.esHijoVacio(i+1)){
               recorridoPostOrden(nodoActual.getHijo(i+1), result);
            }
            result.add(nodoActual.getClave(i));
        }
    }

    private int size(NodoNVias<K, V> nodoActual) {
        int acu = 0;
        for (int i = 0; i <= nodoActual.cantidadDeClavesNoVacias(); i++) {
            if(!nodoActual.esHijoVacio(i)){
                acu += size(nodoActual.getHijo(i));
            }
        }
        return nodoActual.cantidadDeClavesNoVacias() + acu;
    }

    private int nivel(NodoNVias<K, V> nodoActual) {
        int nivelMayor = 0;
        int supuestoNivelMayor = 0;
        for (int i = 0; i <= nodoActual.cantidadDeClavesNoVacias(); i++) {
            if(!nodoActual.esHijoVacio(i)){
                supuestoNivelMayor = nivel(nodoActual.getHijo(i))+1;
            }
            if(supuestoNivelMayor > nivelMayor){
                nivelMayor = supuestoNivelMayor;
            }
        }
        return nivelMayor;
    }
    
}
