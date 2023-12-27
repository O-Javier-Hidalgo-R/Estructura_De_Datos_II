/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.pesados;

import ficct.ed2.grafos.AdyacenteConPeso;
import ficct.ed2.grafos.GrafoPesadoAbs;
import ficct.ed2.grafos.exceptions.AristaNoExisteExcepcion;
import ficct.ed2.grafos.utils.UtilMarcador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OJavierHR
 */
public class Djkstra {

    private final static int INT_INFINITE = Integer.MAX_VALUE;
    private final List<Integer> costos;
    private final UtilMarcador marcador;
    private final List<Integer> predecesores;
    private final GrafoPesadoAbs grafo;

    public Djkstra(GrafoPesadoAbs grafo, int verticeInicio) {
        this.grafo = grafo;
        this.costos = new ArrayList<>();
        this.marcador = new UtilMarcador();
        this.predecesores = new ArrayList<>();

        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            costos.add(INT_INFINITE);
            marcador.insertarMarca(Boolean.FALSE);
            predecesores.add(-1);
        }
        
        comenzarDesde(verticeInicio);
    }
    
    private Djkstra(GrafoPesadoAbs grafo) {
        this.grafo = grafo;
        this.costos = new ArrayList<>();
        this.marcador = new UtilMarcador();
        this.predecesores = new ArrayList<>();

        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            costos.add(INT_INFINITE);
            marcador.insertarMarca(Boolean.FALSE);
            predecesores.add(-1);
        }
    }

    private void comenzarDesde(int verticeInicial) {
        this.costos.set(verticeInicial, 0);
        recorrerDesde(verticeInicial);
    }

    private void recorrerDesde(int verticeActual) {
        this.marcador.marcar(verticeActual);
        for (AdyacenteConPeso adyacencia
                : grafo.getAdyacencias(verticeActual)) {
            final int VERTICE_ADYACENTE = adyacencia.getIndiceVertice();
            try {
                if (this.costos.get(VERTICE_ADYACENTE)
                        > this.costos.get(verticeActual)
                        + grafo.getPesoAdyacencia(verticeActual,
                                VERTICE_ADYACENTE)) {
                    this.costos.set(VERTICE_ADYACENTE,
                            grafo.getPesoAdyacencia(
                                    verticeActual,
                                    VERTICE_ADYACENTE) +
                                    this.costos.get(verticeActual));
                    this.predecesores.set(VERTICE_ADYACENTE,
                            verticeActual);
                }
            } catch (AristaNoExisteExcepcion ex) {
            }
        }
        int noMarcadoMenorCosto = INT_INFINITE;
        for (AdyacenteConPeso adyacencia : this.grafo.getAdyacencias(verticeActual)) {
            if(!this.marcador.estaMarcado(adyacencia.getIndiceVertice())
                    && this.costos.get(adyacencia.getIndiceVertice()) < noMarcadoMenorCosto){
                noMarcadoMenorCosto = this.getCosto(adyacencia.getIndiceVertice());
            }
        }
        if(noMarcadoMenorCosto != INT_INFINITE){
            recorrerDesde(this.costos.indexOf(noMarcadoMenorCosto));
        }
    }

    public List<Integer> getCamino(int verticeDestino) {
        if (verticeDestino == -1) {
            return new ArrayList<>();
        }
        List<Integer> camino = new ArrayList<>(getCamino(this.predecesores.get(verticeDestino)));
        camino.add(verticeDestino);
        return camino;
    }

    public int getCosto(int verticeDestino) {
        return this.costos.get(verticeDestino);
    }
    
    public static List<Integer> getCamino(GrafoPesadoAbs grafo, 
            int verticeInicio, int verticeDestino){
        Djkstra d = new Djkstra(grafo);
        d.comenzarDesde(verticeInicio);
        return d.getCamino(verticeDestino);
    }
    
    public static int getCosto(GrafoPesadoAbs grafo, 
            int verticeInicio, int verticeDestino){
        Djkstra d = new Djkstra(grafo);
        d.comenzarDesde(verticeInicio);
        return d.getCosto(verticeDestino);
    }
}
