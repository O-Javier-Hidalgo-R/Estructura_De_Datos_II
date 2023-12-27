/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos;

import ficct.ed2.grafos.exceptions.AristaYaExisteExcepcion;
import ficct.ed2.grafos.exceptions.AristaNoExisteExcepcion;
import ficct.ed2.grafos.exceptions.VerticeNoValidoExcepcion;

/**
 *
 * @author OJavierHR
 */
public abstract class GrafoNoPesadoAbs extends GrafoAbs<Integer> {

    @Override
    public boolean esAdyacente(int vertice, int supuestoVerticeAdyacente) {
        super.verificarVerticeValido(vertice);
        super.verificarVerticeValido(supuestoVerticeAdyacente);
        return super.listaDeAdyacencias.get(vertice).contains(
                supuestoVerticeAdyacente);
    }

    public boolean[][] getMatrizAdyacencias() {
        final int CANTIDAD_VERTICES = super.listaDeAdyacencias.size();
        boolean[][] matrizAdyacencias = 
                new boolean[CANTIDAD_VERTICES][CANTIDAD_VERTICES];
        for (int i = 0; i < CANTIDAD_VERTICES; i++) {
            for (int j = 0; j < CANTIDAD_VERTICES; j++) {
                matrizAdyacencias[i][j]
                        = this.esAdyacente(i, j);
            }
        }
        return matrizAdyacencias;
    }

    public abstract void insertarArista(int vertice, int verticeAdyacente)
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
