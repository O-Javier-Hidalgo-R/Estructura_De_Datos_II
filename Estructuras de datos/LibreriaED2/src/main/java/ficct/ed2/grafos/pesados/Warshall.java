/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.pesados;

/**
 *
 * @author OJavierHR
 */
/*
public class Warshall {
    
    private final boolean[][] matrizCaminos;
    
    public Warshall(boolean[][] matrizAdyacencias){
        this.matrizCaminos = matrizAdyacencias;
        ejecutarAlgoritmo();
    }
    
    private void ejecutarAlgoritmo() {
        final int NUMERO_VERTICES = matrizCaminos.length;
        for (int k = 0; k < NUMERO_VERTICES; k++) {
            for (int i = 0; i < NUMERO_VERTICES; i++) {
                for (int j = 0; j < NUMERO_VERTICES; j++) {
                    if(i != k || j != k){
                        matrizCaminos[i][j] = matrizCaminos[i][j] || 
                            (matrizCaminos[i][k] && matrizCaminos[k][j]);
                    }
                }
            }
        }
    }

    public boolean[][] getMatrizCaminos() {
        return matrizCaminos;
    }
    
    public boolean hayCamino(int vertice, int otroVertice){
        return matrizCaminos[vertice][otroVertice];
    }
    
    static public boolean esConexo(GrafoPND grafo){
        final int CANTIDAD_VERTICES = grafo.cantidadDeVertices();
        boolean[][] matrizCaminos = new Warshall(
                grafo.getMatrizAdyacencias()).getMatrizCaminos();
        for (int i = 0; i < CANTIDAD_VERTICES; i++) {
            for (int j = 0; j < CANTIDAD_VERTICES; j++) {
                if(i != j && !matrizCaminos[i][j]) {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }
    
    static public boolean esFuertementeConexo(
            GrafoPD grafo){
        final int CANTIDAD_VERTICES = grafo.cantidadDeVertices();
        boolean[][] matrizCaminos = new Warshall(
                grafo.getMatrizAdyacencias()).getMatrizCaminos();
        for (int i = 0; i < CANTIDAD_VERTICES; i++) {
            for (int j = 0; j < CANTIDAD_VERTICES; j++) {
                if(i != j && !matrizCaminos[i][j]) {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }
    
    static public boolean esCiclico(GrafoPD grafo){
        final int CANTIDAD_VERTICES = grafo.cantidadDeVertices();
        boolean[][] matrizCaminos = new Warshall(
                grafo.getMatrizAdyacencias()).getMatrizCaminos();
        for (int i = 0; i < CANTIDAD_VERTICES; i++) {
            if(matrizCaminos[i][i]){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
*/
