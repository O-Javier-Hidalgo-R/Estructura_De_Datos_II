/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.nopesado;

import ficct.ed2.grafos.utils.UtilMarcador;
import ficct.ed2.grafos.GrafoNoPesadoAbs;
import java.util.List;

/**
 *
 * @author OJavierHR
 */
public class Ciclo {

    private final GrafoNPND grafoAuxiliar;
    private final UtilMarcador marcador;
    private boolean bCiclo;

    private Ciclo(GrafoNPND grafo) {
        final int CANTIDAD_VERTICES = grafo.cantidadDeVertices();
        grafoAuxiliar = new GrafoNPND(CANTIDAD_VERTICES);
        marcador = new UtilMarcador(CANTIDAD_VERTICES);
        bCiclo = Boolean.FALSE;
        for (int i = 0; i < CANTIDAD_VERTICES; i++) {
            if (!bCiclo && !marcador.estaMarcado(i)) {
                DFSModificado(i, grafo);
            }
        }
    }

    private void DFSModificado(int verticeActual, GrafoNoPesadoAbs grafo) {
        if (marcador.estaMarcado(verticeActual)) {
            return;
        }
        marcador.marcar(verticeActual);
        List<Integer> adyacentesVerticeActual
                = grafo.getAdyacencias(verticeActual);
        for (Integer verticeAdyacente : adyacentesVerticeActual) {
            if (marcador.estaMarcado(verticeAdyacente)) {
                if (!bCiclo && !grafoAuxiliar.esAdyacente(verticeActual,
                        verticeAdyacente)) {
                    bCiclo = Boolean.TRUE;
                    return;
                }
            } else {
                grafoAuxiliar.insertarArista(verticeActual,
                        verticeAdyacente);
                DFSModificado(verticeAdyacente, grafo);
            }
        }
    }

    private boolean hayCiclo() {
        return bCiclo;
    }
    
    public static boolean hayCiclo(GrafoNPND grafo){
        Ciclo ciclo = new Ciclo(grafo);
        return ciclo.hayCiclo();
    }
    
    public static boolean hayCiclo(GrafoNPD grafo){
        return Warshall.esCiclico(grafo);
    }
}
