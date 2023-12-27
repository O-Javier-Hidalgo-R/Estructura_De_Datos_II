/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.pesados;

import ficct.ed2.grafos.GrafoPesadoAbs;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OJavierHR
 */
public class Floid {
    private static final float INFINITO_POSITIVO = Float.POSITIVE_INFINITY;
    private float[][] matrizPesos;
    private int[][] matrizPredecesores;

    public Floid(GrafoPesadoAbs grafo) {
        final int CANTIDAD_VERTICES = grafo.cantidadDeVertices();
        this.matrizPredecesores = new int[CANTIDAD_VERTICES][CANTIDAD_VERTICES];
        this.matrizPesos = grafo.getMatrizAdyacencias();
        for (int i = 0; i < CANTIDAD_VERTICES; i++) {
            for (int j = 0; j < CANTIDAD_VERTICES; j++) {
                this.matrizPredecesores[i][j] = -1;
                if(this.matrizPesos[i][j] == 0 && i != j){
                    matrizPesos[i][j] = INFINITO_POSITIVO;
                }
            }
        }
        calcularTodosLosCaminosMinimos();
    }

    private void calcularTodosLosCaminosMinimos() {
        final int CANTIDAD_VERTICES = this.matrizPesos.length;
        float pesoMenor;
        for (int k = 0; k < CANTIDAD_VERTICES; k++) {
            for (int i = 0; i < CANTIDAD_VERTICES; i++) {
                for (int j = 0; j < CANTIDAD_VERTICES; j++) {
                    pesoMenor = this.matrizPesos[i][k] + this.matrizPesos[k][j];
                    if(this.matrizPesos[i][j] > pesoMenor){
                        this.matrizPesos[i][j] = pesoMenor;
                        this.matrizPredecesores[i][j] = k;
                    }
                }
            }
        }
    }
    
    public float getPeso(int verticeInicio, int verticeDestino){
        return this.matrizPesos[verticeInicio][verticeDestino];
    }
    
    public List<Integer> getCamino(int verticeInicio, int verticeDestino){
        if(getPeso(verticeInicio, verticeDestino) == INFINITO_POSITIVO){
            return null;
        }
        if(verticeInicio == verticeDestino){
            return new ArrayList<>();
        }
        List<Integer> camino = new ArrayList<>();
        camino.add(verticeInicio);
        insertarCaminoDeEnMedio(camino, verticeInicio, verticeDestino);
        camino.add(verticeDestino);
        return camino;
    }

    private void insertarCaminoDeEnMedio(List<Integer> camino, 
            int verticeInicio, int verticeDestino) {
        int pivote = this.matrizPredecesores[verticeInicio][verticeDestino];
        if(pivote == -1){
            return;
        }
        insertarCaminoDeEnMedio(camino, 
                verticeInicio, pivote);
        camino.add(pivote);
    }
    
}
