/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.nopesado;

import ficct.ed2.grafos.utils.UtilMarcador;
import ficct.ed2.grafos.GrafoNoPesadoAbs;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author OJavierHR
 */
public class BFSNoPesado {
    
    private final List<Integer> recorrido;
    private final UtilMarcador marcador;

    private BFSNoPesado(int canVerticesGrafo) {
        this.recorrido = new LinkedList<>();
        this.marcador = new UtilMarcador(canVerticesGrafo);
    }
    
    private void marcar(int verticeAMarcar){
        this.marcador.marcar(verticeAMarcar);
    }
    
    private List<Integer> getRecorrido() {
        return this.recorrido;
    }
    
    private void insertarVerticeRecorrido(int verticeAInsertar){
        this.recorrido.add(verticeAInsertar);
    }
    
    private boolean estaMarcado(int marcado){
        return this.marcador.estaMarcado(marcado);
    }
    
    public static List<Integer> getRecorrido(GrafoNoPesadoAbs grafo, 
            int verticeInicial){
        BFSNoPesado bfs = new BFSNoPesado(
                grafo.cantidadDeVertices());
        Queue<Integer> colaVertices = new LinkedList<>();
        int verticeVisitado;
        colaVertices.add(verticeInicial);
                
        while (!colaVertices.isEmpty()) {
            verticeVisitado = colaVertices.poll();
            bfs.insertarVerticeRecorrido(verticeVisitado);
            bfs.marcar(verticeVisitado);
            for (Integer verticeAdyacente : grafo.getAdyacencias(
                    verticeVisitado)) {
                if(!bfs.estaMarcado(verticeAdyacente)){
                    colaVertices.add(verticeAdyacente);
                }
            }
        }
        return bfs.getRecorrido();
    }
    
}
