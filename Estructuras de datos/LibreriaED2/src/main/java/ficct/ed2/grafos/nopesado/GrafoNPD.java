/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.nopesado;

import ficct.ed2.grafos.GrafoNoPesadoAbs;
import ficct.ed2.grafos.exceptions.AristaNoExisteExcepcion;
import ficct.ed2.grafos.exceptions.AristaYaExisteExcepcion;
import ficct.ed2.grafos.exceptions.CantidadVerticesNoValidosExcepcion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OJavierHR
 */
public class GrafoNPD extends GrafoNoPesadoAbs{

    public GrafoNPD() {
        super.listaDeAdyacencias = new ArrayList<>();
        super.cantAristas = 0;
    }

    public GrafoNPD(int cantVertices) 
            throws CantidadVerticesNoValidosExcepcion {
        super.verificaCantidadVertices(cantVertices);
        this.listaDeAdyacencias = new ArrayList<>();
        this.cantAristas = 0;
        for (int i = 0; i < cantVertices; i++) {
            this.listaDeAdyacencias.add(new ArrayList<>());
        }
    }
    
    public GrafoNPD(GrafoNPD grafoACopiar){
        this.listaDeAdyacencias = grafoACopiar.copiarListaDeAdyacencias();
        this.cantAristas = grafoACopiar.cantidadDeAristas();
    }
    
    @Override
    public void insertarArista(int vertice, int verticeAdyacente) {
        try {
            super.verificarAristaYaExiste(vertice,
                    verticeAdyacente);
            super.listaDeAdyacencias.get(vertice).add(verticeAdyacente);
            super.cantAristas++;
        } catch (AristaYaExisteExcepcion ex) {
            Logger.getLogger(GrafoNPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void eliminarArista(int vertice, int adyacenciaAlVertice) {
        try {
            super.verificarAristaNoExiste(vertice,
                    adyacenciaAlVertice);
            super.listaDeAdyacencias.get((int) vertice).remove(
                    adyacenciaAlVertice);
            super.cantAristas--;
        } catch (AristaNoExisteExcepcion ex) {
            Logger.getLogger(GrafoNPD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarVertice(int verticeAEliminar){
        super.verificarVerticeValido(verticeAEliminar);
        int aristasEliminadas = this.gradoSalida(verticeAEliminar);
        super.listaDeAdyacencias.remove((int) verticeAEliminar);
        for (List<Integer> listaDeAdyacencia : super.listaDeAdyacencias) {
            if(listaDeAdyacencia.remove((Object) verticeAEliminar)){
                aristasEliminadas++;
            }
            for (int i = 0; i < listaDeAdyacencia.size(); i++) {
                int adyacencia = listaDeAdyacencia.get(i);
                if (adyacencia > verticeAEliminar) {
                    listaDeAdyacencia.set(i, adyacencia - 1);
                }
            }
        }
        super.cantAristas -= aristasEliminadas;
    }
    
    public int gradoSalida(int vertice){
        super.verificarVerticeValido(vertice);
        return this.listaDeAdyacencias.get(vertice).size();
    }
    
    public int gradoEntrada(int vertice){
        super.verificarVerticeValido(vertice);
        int contGradosSalida = 0;
        for (List<Integer> listaDeAdyacencia : this.listaDeAdyacencias) {
            if(listaDeAdyacencia.contains((Object)vertice)){
                contGradosSalida++;
            }
        }
        return contGradosSalida;
    }
}
