/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.nopesado;

import ficct.ed2.grafos.GrafoNoPesadoAbs;
import ficct.ed2.grafos.utils.UtilMarcador;
import java.util.List;

/**
 *
 * @author OJavierHR
 */
public class Conexion {
    
    private final GrafoNoPesadoAbs grafo;
    private UtilMarcador marcador;

    private Conexion(GrafoNoPesadoAbs grafo) {
        this.grafo = grafo;
        this.marcador = new UtilMarcador(
                grafo.cantidadDeVertices());
    }

    private void recorrerMarcandoDesde(int verticeInicial) {
        if (this.marcador.estaMarcado(verticeInicial)) {
            return;
        }
        this.marcador.marcar(verticeInicial);
        List<Integer> adyacenciasVerticeInicial
                = this.grafo.getAdyacencias(verticeInicial);
        for (Integer adyacenciaVerticeInicial : adyacenciasVerticeInicial) {
            if (!this.marcador.estaMarcado(adyacenciaVerticeInicial)) {
                recorrerMarcandoDesde(adyacenciaVerticeInicial);
            }
        }
    }

    private boolean estaMarcado(int supuestoVerticeMarcado) {
        return this.marcador.estaMarcado(supuestoVerticeMarcado);
    }

    private boolean estanTodosMarcados() {
        return this.marcador.estanTodosMarcados();
    }

    private List<Integer> obtenerNoMarcados() {
        return this.marcador.getNoMarcados();
    }
    
    private void vaciarMarcador() {
        this.marcador = new UtilMarcador(
                grafo.cantidadDeVertices());
    }

    public static boolean esConexo(GrafoNPND grafo) {
        Conexion conexion = new Conexion(grafo);
        conexion.recorrerMarcandoDesde(0);
        return conexion.estanTodosMarcados();
    }

    public static boolean esDebilmenteConexo(GrafoNPD grafo) {
        Conexion conexion = new Conexion(grafo);
        conexion.recorrerMarcandoDesde(0);
        marcarAdyacentesAYaMarcados(conexion, grafo);
        return conexion.estanTodosMarcados();
    }
    
    private static void marcarAdyacentesAYaMarcados(Conexion conexion, 
            GrafoNPD grafo) {
        if(conexion.estanTodosMarcados()){
            return;
        }
        for (Integer noMarcado : conexion.obtenerNoMarcados()) {
            for (Integer adyacenciaNoMarcado : grafo.getAdyacencias(
                    noMarcado)) {
                if(conexion.estaMarcado(adyacenciaNoMarcado)){
                    conexion.recorrerMarcandoDesde(noMarcado);
                    marcarAdyacentesAYaMarcados(conexion, grafo);
                }
            }
        }
    }
    
    public static boolean esFuertementeConexo(GrafoNPD grafo){
        Conexion conexion = new Conexion(grafo);
        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            conexion.vaciarMarcador();
            conexion.recorrerMarcandoDesde(i);
            if(!conexion.estanTodosMarcados()){
                return false;
            }
        }
        return true;
    }
}
