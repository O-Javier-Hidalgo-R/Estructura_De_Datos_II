/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.pesados;

import ficct.ed2.grafos.AdyacenteConPeso;
import ficct.ed2.grafos.GrafoPesadoAbs;
import ficct.ed2.grafos.exceptions.AristaNoExisteExcepcion;
import ficct.ed2.grafos.exceptions.AristaYaExisteExcepcion;
import ficct.ed2.grafos.nopesado.GrafoNPND;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OJavierHR
 */
public class GrafoPND extends GrafoPesadoAbs {

    public GrafoPND() {
        super.listaDeAdyacencias = new ArrayList<>();
        super.cantAristas = 0;
    }

    public GrafoPND(int cantVertices) {
        super.verificaCantidadVertices(cantVertices);
        this.listaDeAdyacencias = new ArrayList<>();
        this.cantAristas = 0;
        for (int i = 0; i < cantVertices; i++) {
            this.listaDeAdyacencias.add(new ArrayList<>());
        }
    }
    
    public GrafoPND(GrafoPND grafoACopiar){
        this.listaDeAdyacencias = grafoACopiar.copiarListaDeAdyacencias();
        this.cantAristas = grafoACopiar.cantidadDeAristas();
    }

    @Override
    public void insertarArista(int vertice, int verticeAdyacente, int peso) {
        try {
            super.verificarAristaYaExiste(vertice,
                    verticeAdyacente);
            super.listaDeAdyacencias.get(vertice).add(new AdyacenteConPeso(
                    verticeAdyacente, peso));
            super.listaDeAdyacencias.get(verticeAdyacente).add(
                    new AdyacenteConPeso(vertice, peso));
            super.cantAristas++;
        } catch (AristaYaExisteExcepcion ex) {
            Logger.getLogger(GrafoPND.class.getName()).log(
                    Level.WARNING, "", ex);
        }
    }
    
    @Override
    public void eliminarArista(int vertice, int adyacenciaAlVertice) {
        try {
            super.verificarAristaNoExiste(vertice,
                    adyacenciaAlVertice);
            super.listaDeAdyacencias.get(vertice).remove(
                    (Object) new AdyacenteConPeso(
                            adyacenciaAlVertice));
            super.listaDeAdyacencias.get(adyacenciaAlVertice).remove(
                    (Object) new AdyacenteConPeso(vertice));
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
        for ( List<AdyacenteConPeso> listaDeAdyacencia : 
                super.listaDeAdyacencias) {
            listaDeAdyacencia.remove(
                    (Object) new AdyacenteConPeso(verticeAEliminar));
            for (int i = 0; i < listaDeAdyacencia.size(); i++) {
                int adyacencia = 
                        listaDeAdyacencia.get(i).getIndiceVertice();
                if (adyacencia > verticeAEliminar) {
                    listaDeAdyacencia.set(
                            i, new AdyacenteConPeso(adyacencia - 1));
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
