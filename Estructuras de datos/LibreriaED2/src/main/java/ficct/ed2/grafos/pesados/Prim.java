/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.pesados;

import ficct.ed2.grafos.AdyacenteConPeso;
import ficct.ed2.grafos.AristaConPeso;
import java.util.List;
import ficct.ed2.grafos.exceptions.GrafoNoConexoException;
import java.util.ArrayList;

/**
 *
 * @author OJavierHR
 */
public class Prim {
    List<Integer> yaCreados;
    GrafoPND grafoOriginal;
    GrafoPND arbolExpancionDeCostosMinimos;

    private Prim(GrafoPND grafoOriginal) {
        verificarGrafoConexo(grafoOriginal);
        final int CANTIDAD_VERTICES = grafoOriginal.cantidadDeVertices();
        this.yaCreados = new ArrayList<>();
        this.grafoOriginal = grafoOriginal;
        this.arbolExpancionDeCostosMinimos = 
                new GrafoPND(CANTIDAD_VERTICES);
        if(!grafoOriginal.esVacio()){
            iniciarBuscandoDesde(0);
        }
    }
    
    private void iniciarBuscandoDesde(int verticeInicial) {
        this.yaCreados.add(verticeInicial);
        recorrerBuscandoAristaMenor();
    }

    private void recorrerBuscandoAristaMenor() {
        if(this.yaCreados.size() == this.grafoOriginal.cantidadDeVertices()){
            return;
        }
        AristaConPeso aristaMenor = new AristaConPeso();
        aristaMenor.setPeso(Integer.MAX_VALUE);
        for (Integer yaCreado : this.yaCreados) {
            for (AdyacenteConPeso adyacenciaAlYaCreado : 
                    this.grafoOriginal.getAdyacencias(yaCreado)) {
                if(!this.yaCreados.contains(
                        adyacenciaAlYaCreado.getIndiceVertice()) && 
                        adyacenciaAlYaCreado.getPeso() < aristaMenor.getPeso()){
                    aristaMenor = new AristaConPeso(yaCreado, 
                            adyacenciaAlYaCreado.getIndiceVertice(), 
                            adyacenciaAlYaCreado.getPeso());
                }
            }
        }
        this.yaCreados.add(aristaMenor.getVerticeDestino());
        this.arbolExpancionDeCostosMinimos.insertarArista(
                aristaMenor.getVerticeOrigen(), 
                aristaMenor.getVerticeDestino(), 
                aristaMenor.getPeso());
        recorrerBuscandoAristaMenor();
    }
    
    private GrafoPND getArbolExpancionDeCostosMinimos(){
        return this.arbolExpancionDeCostosMinimos;
    }
    
    private void verificarGrafoConexo(GrafoPND grafoOriginal) {
        if(!Conexion.esConexo(grafoOriginal)){
            throw new GrafoNoConexoException(
                    "El grafo original no es conexo");
        }
    }
    
    public static GrafoPND arbolExpancionCostosMinimos(
            GrafoPND grafoOriginal){
        Prim p = new Prim(grafoOriginal);
        return p.getArbolExpancionDeCostosMinimos();
    }
}
