
import ficct.ed2.grafos.pesados.*;
import ficct.ed2.grafos.utils.UtilMatriz;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author OJavierHR
 */
public class main {

    public static void main(String[] args) {
        
        GrafoPD grafo = new GrafoPD(5);
        grafo.insertarArista(0, 1, 1);
        grafo.insertarArista(1, 3, 4);
        grafo.insertarArista(1, 4, 7);
        grafo.insertarArista(2, 0, 3);
        grafo.insertarArista(2, 1, 2);
        grafo.insertarArista(2, 4, 4);
        grafo.insertarArista(3, 0, 6);
        grafo.insertarArista(3, 4, 2);
        grafo.insertarArista(4, 3, 3);
        

        System.out.println(UtilMatriz.toString(grafo.getMatrizAdyacencias()));
        System.out.println("Cantidad de vertices: " + grafo.cantidadDeVertices());
        System.out.println("Cantidad de aristas: " + grafo.cantidadDeAristas());
        System.out.println();
        
        Floid f = new Floid(grafo);
        System.out.println(f.getPeso(4, 4));
        System.out.println(f.getCamino(4, 4));
    }

}
