/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java 
 * to edit this template
 */
package ficct.ed2.grafos;

import ficct.ed2.grafos.exceptions.AristaNoExisteExcepcion;
import ficct.ed2.grafos.exceptions.CantidadVerticesNoValidosExcepcion;
import ficct.ed2.grafos.exceptions.VerticeNoValidoExcepcion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OJavierHR
 * @param <Adyacencia>
 */
abstract class GrafoAbs<Adyacencia extends Comparable<Adyacencia>> {

    protected List<List<Adyacencia>> listaDeAdyacencias;
    protected int cantAristas;

    public List<Adyacencia> getAdyacencias(int vertice) {
        verificarVerticeValido(vertice);
        return this.listaDeAdyacencias.get(vertice);
    }

    public int cantidadDeAristas() {
        return this.cantAristas;
    }

    public int cantidadDeVertices() {
        return this.listaDeAdyacencias.size();
    }

    public void insertarVertice() {
        this.listaDeAdyacencias.add(new ArrayList<>());
    }

    public boolean esCompleto() {
        for (List<Adyacencia> listaDeAdyacencia : this.listaDeAdyacencias) {
            if (listaDeAdyacencia.size() < this.cantidadDeVertices() - 1) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public boolean esVacio() {
        return this.listaDeAdyacencias.isEmpty();
    }

    protected List<List<Adyacencia>> copiarListaDeAdyacencias() {
        List<List<Adyacencia>> copiaListaAdyacencias = new ArrayList<>();
        for (List<Adyacencia> adyacencia : this.listaDeAdyacencias) {
            List<Adyacencia> copiaAdyacencia = new ArrayList<>();
            for (Adyacencia a : adyacencia) {
                copiaAdyacencia.add(a);
            }
            copiaListaAdyacencias.add(copiaAdyacencia);
        }
        return copiaListaAdyacencias;
    }

    public abstract boolean esAdyacente(int vertice, int supestaAdyacencia)
            throws VerticeNoValidoExcepcion;

    public abstract void eliminarArista(int vertice, int adyacenciaAlVertice)
            throws VerticeNoValidoExcepcion, AristaNoExisteExcepcion;

    public abstract void eliminarVertice(int verticeAEliminar)
            throws VerticeNoValidoExcepcion;

    protected void verificaCantidadVertices(int supuestaCantidadVerticesValida) {
        if (supuestaCantidadVerticesValida < 0) {
            throw new CantidadVerticesNoValidosExcepcion("Cantidad de "
                    + "vertices invalido. La cantidad debe ser >= 0");
        }
    }

    protected void verificarVerticeValido(int verticeAVerificar) {
        final int CANT_VERTICES = this.cantidadDeVertices();
        if (verticeAVerificar < 0
                || verticeAVerificar >= CANT_VERTICES) {
            throw new VerticeNoValidoExcepcion("El vertice: "
                    + verticeAVerificar
                    + " no es valido para el grafo, debe ser >= 0 y < "
                    + CANT_VERTICES);
        }
    }
}
