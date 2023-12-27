/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OJavierHR
 */
public class UtilMarcador {
 
    protected List<Boolean> marcas;

    public UtilMarcador(int cantVerticesNoVisitados) {
        marcas = new ArrayList<>();
        for (int i = 0; i < cantVerticesNoVisitados; i++) {
            marcas.add(Boolean.FALSE);
        }
    }

    public UtilMarcador() {
        marcas = new ArrayList<>();
    }
    
    public List<Boolean> getMarcas() {
        return marcas;
    }
    
    public void desmarcarTodos(){
        for (int i = 0; i < this.marcas.size(); i++) {
            marcas.set(i, Boolean.FALSE);
        }
    }
    
    public boolean estaMarcado(int vertice){
        return this.marcas.get(vertice);
    }
    
    public void marcar(int verticeVisitado) {
        this.marcas.set(verticeVisitado, Boolean.TRUE);
    }
    
    public boolean estanTodosMarcados(){
        for (Boolean marcado : marcas) {
            if(!marcado){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
    
    public List<Integer> getMarcados(){
        List<Integer> marcados = new ArrayList<>();
        for (int i = 0; i < this.marcas.size(); i++) {
            if(this.marcas.get(i)){
                marcados.add(i);
            }
        }
        return marcados;
    }
    
    public List<Integer> getNoMarcados(){
        List<Integer> marcados = new ArrayList<>();
        for (int i = 0; i < this.marcas.size(); i++) {
            if(!this.marcas.get(i)){
                marcados.add(i);
            }
        }
        return marcados;
    }

    public void insertarMarca(Boolean b) {
        this.marcas.add(b);
    }
}
