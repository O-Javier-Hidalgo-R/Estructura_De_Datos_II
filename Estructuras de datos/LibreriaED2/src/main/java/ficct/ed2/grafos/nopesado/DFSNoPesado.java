/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.nopesado;

import ficct.ed2.grafos.utils.UtilMarcador;
import ficct.ed2.grafos.GrafoNoPesadoAbs;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author OJavierHR
 */
public class DFSNoPesado {

    private final List<Integer> recorrido;
    private final UtilMarcador marcador;

    private DFSNoPesado(int canVerticesGrafo) {
        this.recorrido = new LinkedList<>();
        this.marcador = new UtilMarcador(canVerticesGrafo);
    }
    
    private boolean estaMarcado(int vertice) {
        return this.marcador.estaMarcado(vertice);
    }
    
    private List<Integer> getRecorrido() {
        return recorrido;
    }
    
    private void adicionarVerticeRecorrido(int vertice) {
        this.recorrido.add(vertice);
    }
    
    private void marcar(int verticeAMarcar){
        this.marcador.marcar(verticeAMarcar);
    }

    private void recorrerDesde(int verticeActual, GrafoNoPesadoAbs grafo) {
        if (estaMarcado(verticeActual)) {
            return;
        }
        adicionarVerticeRecorrido(verticeActual);
        marcar(verticeActual);
        for (Integer verticeAdyacente : grafo.getAdyacencias(
                verticeActual)) {
            recorrerDesde(verticeAdyacente, grafo);
        }
    }
    
    public static List<Integer> getRecorrido(GrafoNoPesadoAbs grafo, 
            int verticeInicial){
        DFSNoPesado dfs = new DFSNoPesado(
                grafo.cantidadDeVertices());
        dfs.recorrerDesde(verticeInicial, grafo);
        return dfs.getRecorrido();
    }
}
