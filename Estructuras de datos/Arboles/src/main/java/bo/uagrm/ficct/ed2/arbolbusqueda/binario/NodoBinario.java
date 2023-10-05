/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda.binario;

/**
 * Clase que se usa para implementar un nodo binario usado en arboles binarios
 * de busqueda y avl. <br>
 * Graficamente puede representarse de esta manera: <p>
 * <img src = "imagenes/nodoBinario1.png" style="width:300px" alt = "
   reresentacion grafica del nodo binario">
 * 
 * @author OJavierHR
 * @param <K> Tipo de dato (comparable) para la clave.
 * @param <V> Tipo de dato para el valor.
 */
public class NodoBinario<K extends Comparable<K>, V> {

    /**
     * Identificador del nodo que se usara como criterio de busqueda.
     */
    private K clave;

    /**
     * Valor o valores que se almacenaran en el nodo para su uso posterior.
     */
    private V valor;

    /**
     * Referencia del nodo al hijo derecho del actual, la clave tiene que ser
     * mayor que el actual.
     */
    private NodoBinario hijoDerecho;

    /**
     * Referencia del nodo al hijo izquierdo del actual, la clave tiene que ser
     * menor que el actual.
     */
    private NodoBinario hijoIzquierdo;

    /**
     * Constructor vacio de la clase.
     */
    public NodoBinario() {

    }

    /**
     * Constructor parametrizado (las referencias a los hijos comienzan vacias).
     *
     * @param clave Clave del nodo a crearse.
     * @param valor Valor del nodo a crearse.
     */
    public NodoBinario(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    /**
     * Muestra el valor de la clave del nodo.
     *
     * @return clave Clave a mostrar.
     */
    public K getClave() {
        return clave;
    }

    /**
     * Cambiar el valor de la clave del nodo.
     *
     * @param clave Clave a insertar.
     */
    public void setClave(K clave) {
        this.clave = clave;
    }

    /**
     * Muestra el valor del atributo valor del nodo.
     *
     * @return Valor a mostrar.
     */
    public V getValor() {
        return valor;
    }

    /**
     * Cambiar el valor del atributo valor del nodo.
     *
     * @param valor Valor a insertar en el nodo.
     */
    public void setValor(V valor) {
        this.valor = valor;
    }

    /**
     * Muestra el hijo derecho del nodo.
     *
     * @return Valor del hijo derecho.
     */
    public NodoBinario<K, V> getHijoDerecho() {
        return hijoDerecho;
    }

    /**
     * Cambiar el valor del hijo derecho del nodo.
     *
     * @param hijoDerecho Valor a insertar en el hijo derecho.
     */
    public void setHijoDerecho(NodoBinario hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    /**
     * Muestra el hijo izquierdo del nodo.
     *
     * @return Valor del hijo izquierdo.
     */
    public NodoBinario<K, V> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    /**
     * Cambiar el valor del hijo izquierdo del nodo.
     *
     * @param hijoIzquierdo Valor a insertar del hijo izquierdo.
     */
    public void setHijoIzquierdo(NodoBinario hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    /**
     * Valida si el hijo izquierdo esta vacio.
     *
     * @return True si el hijo izquierdo esta vacio.
     */
    public boolean esVacioHijoIzquierdo() {
        return esVacio(hijoIzquierdo);
    }

    /**
     * Valida si el hijo derecho esta vacio.
     *
     * @return True si el hijo derecho esta vacio
     */
    public boolean esVacioHijoDerecho() {
        return esVacio(hijoDerecho);
    }

    /**
     * Valida si el nodo no tiene hijos.
     *
     * @return True si el nodo es hoja.
     */
    public boolean esHoja() {
        return esVacioHijoDerecho() && esVacioHijoIzquierdo();
    }

    /**
     * Valida si el nodo es incompleto (no tiene hijos o solo tiene uno)
     *
     * @return True si el nodo es incompleto.
     */
    public boolean esIncompleto() {
        return esVacioHijoDerecho() || esVacioHijoIzquierdo();
    }

    /**
     * Muestra una representacion de la clase mediante un string.
     *
     * @return String con la representacion del nodo.
     */
    @Override
    public String toString() {
        return "(" + clave + ", " + valor + ')';
    }

    /**
     * Funcion compartida que valida si el nodo pasado por parametros esta
     * vacio.
     *
     * @param nodo Nodo binario a validar como vacio.
     * @return True si el nodo esta vacio.
     */
    public static boolean esVacio(NodoBinario nodo) {
        return nodo == null;
    }

    /**
     * Funcion compartida de la clase nodo que retorna un nodo vacio.
     *
     * @return Nodo con valores vacios.
     */
    public static NodoBinario<?, ?> nodoVacio() {
        return null;
    }
}
