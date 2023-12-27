/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.pesados;

import ficct.ed2.grafos.AdyacenteConPeso;
import ficct.ed2.grafos.GrafoNoPesadoAbs;
import ficct.ed2.grafos.GrafoPesadoAbs;
import ficct.ed2.grafos.utils.UtilMarcador;
import java.util.List;

/**
 *
 * @author OJavierHR
 */
public class Conexion {
    
    private final GrafoPesadoAbs grafo;
    private UtilMarcador marcador;

    private Conexion(GrafoPesadoAbs grafo) {
        this.grafo = grafo;
        this.marcador = new UtilMarcador(
                grafo.cantidadDeVertices());
    }

    private void recorrerMarcandoDesde(int verticeInicial) {
        if (this.marcador.estaMarcado(verticeInicial)) {
            return;
        }
        this.marcador.marcar(verticeInicial);
        List<AdyacenteConPeso> adyacenciasVerticeInicial = 
                this.grafo.getAdyacencias(verticeInicial);
        for (AdyacenteConPeso adyacenciaVerticeInicial : 
                adyacenciasVerticeInicial) {
            if (!this.marcador.estaMarcado(
                    adyacenciaVerticeInicial.getIndiceVertice())) {
                recorrerMarcandoDesde(
                        adyacenciaVerticeInicial.getIndiceVertice());
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

    public static boolean esConexo(GrafoPND grafo) {
        Conexion conexion = new Conexion(grafo);
        conexion.recorrerMarcandoDesde(0);
        return conexion.estanTodosMarcados();
    }

    public static boolean esDebilmenteConexo(GrafoPD grafo) {
        Conexion conexion = new Conexion(grafo);
        conexion.recorrerMarcandoDesde(0);
        marcarAdyacentesAYaMarcados(conexion, grafo);
        return conexion.estanTodosMarcados();
    }
    
    private static void marcarAdyacentesAYaMarcados(Conexion conexion, 
            GrafoPD grafo) {
        if(conexion.estanTodosMarcados()){
            return;
        }
        for (Integer noMarcado : conexion.obtenerNoMarcados()) {
            for (AdyacenteConPeso adyacenciaNoMarcado : grafo.getAdyacencias(
                    noMarcado)) {
                if(conexion.estaMarcado(
                        adyacenciaNoMarcado.
                        getIndiceVertice())){
                    conexion.recorrerMarcandoDesde(noMarcado);
                    marcarAdyacentesAYaMarcados(conexion, grafo);
                }
            }
        }
    }
    
    public static boolean esFuertementeConexo(GrafoPD grafo){
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
