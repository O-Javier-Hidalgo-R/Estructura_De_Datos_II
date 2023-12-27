/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos;

import ficct.ed2.grafos.exceptions.AristaNoExisteExcepcion;
import ficct.ed2.grafos.exceptions.AristaYaExisteExcepcion;
import ficct.ed2.grafos.exceptions.VerticeNoValidoExcepcion;

/**
 *
 * @author OJavierHR
 */
public abstract class GrafoPesadoAbs extends GrafoAbs<AdyacenteConPeso>{

    @Override
    public boolean esAdyacente(int vertice, int supestaAdyacencia) 
            throws VerticeNoValidoExcepcion {
        super.verificarVerticeValido(vertice);
        super.verificarVerticeValido(supestaAdyacencia);
        return super.listaDeAdyacencias.get(vertice).contains(
                new AdyacenteConPeso(supestaAdyacencia));
    }
    
    public int getPesoAdyacencia(int vertice, int verticeAdyacente) 
            throws AristaNoExisteExcepcion {
        super.verificarVerticeValido(vertice);
        super.verificarVerticeValido(verticeAdyacente);
        for (AdyacenteConPeso adyacenteConPeso : super.listaDeAdyacencias.get(
                vertice)) {
            if(adyacenteConPeso.equals(new AdyacenteConPeso(
                    verticeAdyacente))){
                return adyacenteConPeso.getPeso();
            }
        }
        throw new AristaNoExisteExcepcion("La arista " + vertice + " -> " + 
                verticeAdyacente + " no existe");
    }
    
    public float[][] getMatrizAdyacencias() {
        final int CANTIDAD_VERTICES = super.listaDeAdyacencias.size();
        float[][] matrizAdyacencias = 
                new float[CANTIDAD_VERTICES][CANTIDAD_VERTICES];
        for (int i = 0; i < CANTIDAD_VERTICES; i++) {
            for (int j = 0; j < CANTIDAD_VERTICES; j++) {
                try {
                    matrizAdyacencias[i][j] = getPesoAdyacencia(i, j);
                } catch (AristaNoExisteExcepcion ex) {
                    matrizAdyacencias[i][j] = 0;
                }
            }
        }
        return matrizAdyacencias;
    }

    public abstract void insertarArista(int vertice, int verticeAdyacente, 
            int peso)
            throws VerticeNoValidoExcepcion, AristaYaExisteExcepcion;

    @Override
    public abstract void eliminarVertice(int verticeAEliminar)
            throws VerticeNoValidoExcepcion;

    @Override
    public abstract void eliminarArista(int vertice, int adyacenciaAlVertice)
            throws VerticeNoValidoExcepcion, AristaNoExisteExcepcion;

    protected void verificarAristaYaExiste(int vertice,
            int supuestoVerticeAdyacente)
            throws AristaYaExisteExcepcion {
        if (this.esAdyacente(vertice, supuestoVerticeAdyacente)) {
            throw new AristaYaExisteExcepcion("Ya existe la arista "
                    + vertice + " -> " + supuestoVerticeAdyacente);
        }
    }

    protected void verificarAristaNoExiste(int vertice,
            int supuestoVerticeAdyacente)
            throws AristaNoExisteExcepcion {
        if (!this.esAdyacente(vertice, supuestoVerticeAdyacente)) {
            throw new AristaNoExisteExcepcion("No existe la arista "
                    + vertice + " -> " + supuestoVerticeAdyacente);
        }
    }
    
}
