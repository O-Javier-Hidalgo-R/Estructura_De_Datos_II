/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.pesados;

import ficct.ed2.grafos.AdyacenteConPeso;
import ficct.ed2.grafos.exceptions.GrafoCiclicoException;
import ficct.ed2.grafos.exceptions.GrafoNoConexoException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author OJavierHR
 */
public class OrdenamientoTopologico {
    /*
    private final List<Integer> recorrido;
    private final List<Integer> gradosEntrada;
    
    private OrdenamientoTopologico(GrafoPD grafo) {
        verificarGrafoEsDebilmenteConexo(grafo);
        verificarGrafoNoTieneCiclos(grafo);
        this.recorrido = new ArrayList<>();
        this.gradosEntrada = new ArrayList<>();
        
        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            gradosEntrada.add(grafo.gradoEntrada(i));
        }
        
        ordenarTopologicamente(grafo);
    }

    private void ordenarTopologicamente(GrafoPD grafo) {
        Queue<Integer> colaVertices = new LinkedList();
        int verticeDescolado;
        
        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            if(this.gradosEntrada.get(i) == 0){
                colaVertices.add(i);
            }
        }
        
        while(!colaVertices.isEmpty()){
            verticeDescolado = colaVertices.poll();
            this.recorrido.add(verticeDescolado);
            for (AdyacenteConPeso adyacenteVerticeDescolado : 
                    grafo.getAdyacencias(
                    verticeDescolado)) {
                disminuirGradoEntrada(
                        adyacenteVerticeDescolado.getIndiceVertice());
                if(this.gradosEntrada.get(
                        adyacenteVerticeDescolado.getIndiceVertice()) 
                        == 0){
                    colaVertices.add(
                            adyacenteVerticeDescolado.getIndiceVertice());
                }
            }
        }
    }
    
    private void disminuirGradoEntrada(int vertice) {
        this.gradosEntrada.set(vertice, this.gradosEntrada.get(
                vertice) - 1);
    }
    
    private List<Integer> getRecorrido() {
        return this.recorrido;
    }
    
    private void verificarGrafoEsDebilmenteConexo(GrafoPD grafo){
        if(!Conexion.esDebilmenteConexo(grafo)){
            throw new GrafoNoConexoException("El grafo no es debilmente"
                    + " conexo");
        }
    }

    private void verificarGrafoNoTieneCiclos(GrafoPD grafo) {
        if(Ciclo.hayCiclo(grafo)){
            throw new GrafoCiclicoException("El grafo tiene ciclos");
        }
    }
    
    public static List<Integer> getRecorrido(GrafoPD grafo){
        OrdenamientoTopologico orTopo = new OrdenamientoTopologico(grafo);
        return orTopo.getRecorrido();
    }
    */
}
