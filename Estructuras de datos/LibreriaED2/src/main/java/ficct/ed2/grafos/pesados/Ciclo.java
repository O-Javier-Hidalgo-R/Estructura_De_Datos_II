/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.pesados;

import ficct.ed2.grafos.AdyacenteConPeso;
import ficct.ed2.grafos.GrafoPesadoAbs;
import ficct.ed2.grafos.utils.UtilMarcador;
import java.util.List;

/**
 *
 * @author OJavierHR
 */
public class Ciclo {

    private final UtilMarcador marcador;

    private Ciclo(GrafoPesadoAbs grafo) {
        final int CANTIDAD_VERTICES = grafo.cantidadDeVertices();
        marcador = new UtilMarcador(CANTIDAD_VERTICES);
    }

    private boolean buscarCiclo(GrafoPND grafo) {
        boolean hayCiclo = Boolean.FALSE;
        GrafoPND grafoAuxiliar = new GrafoPND(grafo.cantidadDeVertices());
        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            if (!marcador.estaMarcado(i)) {
                hayCiclo = hayCiclo || buscarCicloDesde(
                        i, grafo, grafoAuxiliar);
            }

        }
        return hayCiclo;
    }

    private boolean buscarCicloDesde(int verticeActual, GrafoPND grafo,
            GrafoPND grafoAuxiliar) {
        marcador.marcar(verticeActual);
        List<AdyacenteConPeso> adyacencias = grafo.getAdyacencias(verticeActual);
        boolean b = false;
        for (AdyacenteConPeso adyacencia : adyacencias) {
            if (marcador.estaMarcado(adyacencia.getIndiceVertice())) {
                if (!grafoAuxiliar.esAdyacente(verticeActual, adyacencia.getIndiceVertice())) {
                    return Boolean.TRUE;
                }
            } else {
                if (!grafoAuxiliar.esAdyacente(verticeActual, adyacencia.getIndiceVertice())) {
                    grafoAuxiliar.insertarArista(verticeActual, adyacencia.getIndiceVertice(), 0);
                }
                b = b || buscarCicloDesde(adyacencia.getIndiceVertice(), grafo, grafoAuxiliar);
            }
        }
        return b;
    }

    private boolean buscarCiclo(GrafoPD grafo) {
        boolean hayCiclo = Boolean.FALSE;
        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            hayCiclo = hayCiclo || buscarCicloDesde(
                    i, grafo);

        }
        return hayCiclo;
    }

    private boolean buscarCicloDesde(int verticeActual, GrafoPD grafo) {
        for (AdyacenteConPeso adyacencia : grafo.getAdyacencias(verticeActual)) {
            this.marcador.desmarcarTodos();
            trataVolver(adyacencia.getIndiceVertice(), grafo);
            if (marcador.estaMarcado(verticeActual)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private void trataVolver(int indiceVertice, GrafoPD grafo) {
        marcador.marcar(indiceVertice);
        for (AdyacenteConPeso adyacencia : grafo.getAdyacencias(indiceVertice)) {
            if (!marcador.estaMarcado(adyacencia.getIndiceVertice())) {
                trataVolver(adyacencia.getIndiceVertice(), grafo);
            }
        }
    }

    public static boolean hayCiclo(GrafoPND grafo) {
        Ciclo c = new Ciclo(grafo);
        return c.buscarCiclo(grafo);
    }

    public static boolean hayCiclo(GrafoPD grafo) {
        Ciclo c = new Ciclo(grafo);
        return c.buscarCiclo(grafo);
    }
}
