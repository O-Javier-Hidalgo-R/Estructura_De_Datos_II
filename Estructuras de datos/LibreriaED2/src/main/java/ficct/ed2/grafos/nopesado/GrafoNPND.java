/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.nopesado;

import ficct.ed2.grafos.GrafoNoPesadoAbs;
import ficct.ed2.grafos.exceptions.AristaNoExisteExcepcion;
import ficct.ed2.grafos.exceptions.AristaYaExisteExcepcion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OJavierHR
 */
public class GrafoNPND extends GrafoNoPesadoAbs {

    public GrafoNPND() {
        super.listaDeAdyacencias = new ArrayList<>();
        super.cantAristas = 0;
    }

    public GrafoNPND(int cantVertices) {
        super.verificaCantidadVertices(cantVertices);
        this.listaDeAdyacencias = new ArrayList<>();
        this.cantAristas = 0;
        for (int i = 0; i < cantVertices; i++) {
            this.listaDeAdyacencias.add(new ArrayList<>());
        }
    }
    
    public GrafoNPND(GrafoNPND grafoACopiar){
        this.listaDeAdyacencias = grafoACopiar.copiarListaDeAdyacencias();
        this.cantAristas = grafoACopiar.cantidadDeAristas();
    }

    @Override
    public void insertarArista(int vertice, int verticeAdyacente) {
        try {
            super.verificarAristaYaExiste(vertice,
                    verticeAdyacente);
            super.listaDeAdyacencias.get(vertice).add(verticeAdyacente);
            super.listaDeAdyacencias.get(verticeAdyacente).add(
                    vertice);
            super.cantAristas++;
        } catch (AristaYaExisteExcepcion ex) {
            Logger.getLogger(GrafoNPND.class.getName()).log(
                    Level.WARNING, "", ex);
        }
    }
    
    @Override
    public void eliminarArista(int vertice, int adyacenciaAlVertice) {
        try {
            super.verificarAristaNoExiste(vertice,
                    adyacenciaAlVertice);
            super.listaDeAdyacencias.get(vertice).remove(
                    (Object) adyacenciaAlVertice);
            super.listaDeAdyacencias.get(adyacenciaAlVertice).remove(
                    (Object) vertice);
            super.cantAristas--;
        } catch (AristaNoExisteExcepcion ex) {
            Logger.getLogger(GrafoNPND.class.getName()).log(
                    Level.WARNING, "", ex);
        }
    }

    @Override
    public void eliminarVertice(int verticeAEliminar){
        super.verificarVerticeValido(verticeAEliminar);
        int aristasEliminadas = this.grado(verticeAEliminar);
        super.listaDeAdyacencias.remove((int) verticeAEliminar);
        for (List<Integer> listaDeAdyacencia : super.listaDeAdyacencias) {
            listaDeAdyacencia.remove((Object) verticeAEliminar);
            for (int i = 0; i < listaDeAdyacencia.size(); i++) {
                int adyacencia = listaDeAdyacencia.get(i);
                if (adyacencia > verticeAEliminar) {
                    listaDeAdyacencia.set(i, adyacencia - 1);
                }
            }
        }
        super.cantAristas -= aristasEliminadas;
    }
    
    public int grado(int vertice){
        super.verificarVerticeValido(vertice);
        return this.listaDeAdyacencias.get(vertice).size();
    }
    
}
