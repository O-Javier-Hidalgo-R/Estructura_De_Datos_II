/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bo.uagrm.ficct.ed2.arbolbusqueda;

import java.util.List;

/**
 * Interfaz usada para implementar el comportamiento regular o comun de un arbol
 * de busqueda, se usa dos variables genericas para indicar los valores y claves
 * que se almacenaran en la estructura (binario, avl, mvias, b). <br>
 * Siendo un arbol de busqueda una estructura de datos que es:<br>
 * &nbsp;&nbsp;&nbsp;<b>a) Jerarquica.-</b> Los componentes (en este caso nodos)
 * estan a distinto nivel, razon por la cual tambien se dice que no son lineales
 * al no estar uno sucesivo del otro.<br>
 * &nbsp;&nbsp;&nbsp;<b>b) Organizada.-</b> El contenido esta dispuesto
 * siguiendo algun criterio de orden.<br>
 * &nbsp;&nbsp;&nbsp;<b>c) Dinamica.-</b> El tamaño y contenido de los datos
 * pueden cambian durante la ejecucion.<br>
 * ejemplos:<p>
 * &nbsp;&nbsp;&nbsp;<b>Arbol de busqueda binario</b><p>
 * <img src = "imagenes/arbolBusquedaBinario1.png" style="width:400px" alt = "ejemplo de
   arbol binario"><p>
 * &nbsp;&nbsp;&nbsp;<b>Arbol de busqueda nVias</b><p>
 * <img src = "imagenes/arbolBusquedaNVias1.png" style="width:400px" alt = "ejemplo de
   arbol n-Vias">
 * 
 * @author OJavierHR
 * @param <K> Atributo generico que implementa la interfaz "Comparable", usado
 * para implementar las claves (Keys) de los nodos, que se usan como
 * identificador de cada nodo.
 * @param <V> Atributo generico usado para implementar el valor o valores
 * (Values) de los datos que se implementaran en el nodo.
 */
public interface IArbolBusqueda<K extends Comparable<K>, V> {

    /**
     * Operacion que elimina todos los datos en el arbol de busqueda.
     */
    void vaciar();

    /**
     * Valida si el arbol esta vacio.
     *
     * @return True si el arbol esta vacio.
     */
    boolean esVacio();

    /**
     * Inserta un nodo nuevo al arbol con una clave y valor ingresado por
     * parametros. Si la clave ya existe en algun nodo remplaza el valor del
     * mismo por el especificado.
     *
     * @param claveAInsertar Clave del nodo a insertar.
     * @param valorAInsertar Valor del nodo a insertar.
     */
    void insertar(K claveAInsertar, V valorAInsertar);

    /**
     * Retorna el valor del nodo con la clave especificada.
     *
     * @param claveABuscar Clave a buscar en el arbol.
     * @return Valor del nodo buscado mediante la clave.
     */
    V buscar(K claveABuscar);

    /**
     * Verifica si existe un nodo con la clave especificada.
     *
     * @param claveAValidar Clave a validar en el arbol.
     * @return True si existe el nodo con la clave ingresada.
     */
    boolean contiene(K claveAValidar);

    /**
     * Retorna una lista con las claves de los nodos recorriendo el arbol
     * mediante sus niveles.
     *
     * @return List con las claves del recorrido.
     */
    List<K> recorridoPorNiveles();

    /**
     * Retorna una lista con las claves de los nodos recorriendo el arbol en
     * pre-orden.
     *
     * @return List con las claves del recorrido.
     */
    List<K> recorridoPreOrden();

    /**
     * Retorna una lista con las claves de los nodos recorriendo el arbol en
     * in-orden.
     *
     * @return List con las claves del recorrido.
     */
    List<K> recorridoInOrden();

    /**
     * Retorna una lista con las claves de los nodos recorriendo el arbol en
     * post-orden.
     *
     * @return List con las claves del recorrido.
     */
    List<K> recorridoPostOrden();

    /**
     * Elimina el nodo con la clave especificada y retorna el valor.
     *
     * @param claveAEliminar Clave que se quiere eliminar del arbol de busqueda
     * @return Valor del nodo eliminado.
     * @throws IllegalArgumentException Excepcion en tiempo de ejecucion que se
     * invoca cuando la clave a eliminar no existe en el arbol.
     */
    V eliminar(K claveAEliminar) throws IllegalArgumentException;

    /**
     * Retorna la cantidad de claves en el arbol.
     *
     * @return Cantidad de nodos no nulos.
     */
    int size();

    /**
     * Retorna la cantidad de generaciones en el arbol a partir de la raiz y 
     * contando desde uno.
     *
     * @return Altura del arbol.
     */
    int altura();

    /**
     * Retorna la cantidad de generaciones en el arbol a partir de la raiz y 
     * contando desde cero.
     *
     * @return Niveles del arbol.
     */
    int nivel();
}
