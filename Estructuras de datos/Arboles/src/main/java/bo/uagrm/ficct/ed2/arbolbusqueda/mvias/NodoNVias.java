/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.mvias;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author OJavierHR
 */
public class NodoNVias<K, V>{
    private List<K> listaDeClaves;
    private List<V> listaDeValores;
    private List<NodoNVias<K ,V>> listaDeHijos;
    
    public NodoNVias(int orden){
        listaDeHijos = new LinkedList<>();
        listaDeClaves = new LinkedList<>();
        listaDeValores = new LinkedList<>();
        for (int i = 0; i < orden - 1; i++) {
            listaDeClaves.add((K) nodoVacio());
            listaDeHijos.add(nodoVacio());
            listaDeValores.add((V) nodoVacio());
        }
        listaDeHijos.add(null);
    }
    
    public NodoNVias(int orden, K clave, V valor){
        this(orden);
        listaDeValores.set(0, valor);
        listaDeClaves.set(0, clave);
    }
    
    public static NodoNVias nodoVacio(){
        return null;
    }
    
    public static Object datoVacio(){
        return null;
    }
    
    public void setClave(int posicion, K clave){
        listaDeClaves.set(posicion, clave);
    }
    
    public K getClave(int posicion){
        return listaDeClaves.get(posicion);
    }
    
    public void setValor(int posicion, V valor){
        listaDeValores.set(posicion, valor);
    }
    
    public V getValor(int posicion){
        return listaDeValores.get(posicion);
    }
    
    public void setHijo(int posicion, NodoNVias<K, V> hijo){
        listaDeHijos.set(posicion, hijo);
    }
    
    public NodoNVias<K, V> getHijo(int posicion){
        return listaDeHijos.get(posicion);
    }
    
    public static boolean esVacio(NodoNVias nodoN){
        return nodoN == nodoVacio();
    }
    
    public boolean esClaveVacia(int posicion){
        return listaDeClaves.get(posicion) ==  datoVacio();
    }
    
    public boolean esHijoVacio(int posicion){
        return listaDeHijos.get(posicion) == nodoVacio();
    }
    
    public boolean esHoja(){
        for (int i = 0; i < listaDeHijos.size(); i++) {
            if(!esHijoVacio(i)){
                return false;
            }
        }
        return true;
    }
    
    public boolean estanClavesLlenas(){
        for (int i = 0; i < listaDeClaves.size(); i++) {
            if(esClaveVacia(i)){
                return false;
            }
        }
        return true;
    }
    
    public int cantidadDeClavesNoVacias(){
        int cantNoVacias = 0;
        for (int i = 0; i < listaDeClaves.size() && 
                !esClaveVacia(i); i++) 
        {
            cantNoVacias++;
        }
        return cantNoVacias;
    }
    
    public NodoNVias<K, V> generarNodoTemporal(){
        NodoNVias<K, V> result = new NodoNVias<>(this.listaDeHijos.size() + 1);
        for (int i = 0; i < this.cantidadDeClavesNoVacias(); i++) {
            result.listaDeClaves.set(i, 
                    this.listaDeClaves.get(i));
            result.listaDeValores.set(i, 
                    this.listaDeValores.get(i));
        }
        return result;
    }
}
