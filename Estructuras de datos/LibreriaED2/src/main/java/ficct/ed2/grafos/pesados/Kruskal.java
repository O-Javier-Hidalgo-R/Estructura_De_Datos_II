/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.pesados;

import ficct.ed2.grafos.AdyacenteConPeso;
import ficct.ed2.grafos.AristaConPeso;
import ficct.ed2.grafos.exceptions.GrafoNoConexoException;
import ficct.ed2.grafos.utils.UtilMarcador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author OJavierHR
 */
public class Kruskal {

    private final List<AristaConPeso> aristas;
    private final UtilMarcador marcador;
    private final GrafoPND grafo;
    
    private Kruskal(GrafoPND grafo) {
        verificarGrafoConexo(grafo);
        this.grafo = grafo;
        this.aristas = new ArrayList<>();
        this.marcador = new UtilMarcador(
                grafo.cantidadDeVertices());
        insertarAristas(0);
        Collections.sort(this.aristas);
    }

    private void insertarAristas(int verticeActual) {
        this.marcador.marcar(verticeActual);
        
        for (AdyacenteConPeso adyacencia : this.grafo.getAdyacencias(verticeActual)) {
            if(!this.marcador.estaMarcado(adyacencia.getIndiceVertice())){
                this.aristas.add(new AristaConPeso(verticeActual, adyacencia.getIndiceVertice(), adyacencia.getPeso()));
            }
        }
        
        for (AdyacenteConPeso adyacencia : 
                this.grafo.getAdyacencias(verticeActual)) {
            if(!this.marcador.estaMarcado(adyacencia.getIndiceVertice())){
                insertarAristas(adyacencia.getIndiceVertice());
            }
        }
    }

    private void verificarGrafoConexo(GrafoPND grafo) {
        if(!Conexion.esConexo(grafo)){
            throw new GrafoNoConexoException("El grafo no es conexo");
        }
    }
    
    private GrafoPND getArbolExpacionCostosMinimos() {
        GrafoPND arbolExpacionCostosMinimos = new GrafoPND(this.grafo.cantidadDeVertices());
        for (AristaConPeso arista : this.aristas) {
            arbolExpacionCostosMinimos.insertarArista(
                    arista.getVerticeOrigen(), 
                    arista.getVerticeDestino(), 
                    arista.getPeso());
            if(Ciclo.hayCiclo(arbolExpacionCostosMinimos)){
                arbolExpacionCostosMinimos.eliminarArista(
                        arista.getVerticeOrigen(), 
                        arista.getVerticeDestino());
            }
        }
        return arbolExpacionCostosMinimos;
    }
    
    public static GrafoPND arbolExpancionCostosMinimos(GrafoPND grafo){
        Kruskal k = new Kruskal(grafo);
        return k.getArbolExpacionCostosMinimos();
    }
}
