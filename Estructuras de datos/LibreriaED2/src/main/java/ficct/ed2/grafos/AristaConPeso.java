/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos;

/**
 *
 * @author OJavierHR
 */
public class AristaConPeso implements Comparable<AristaConPeso>{
    
    private int verticeOrigen;
    private int verticeDestino;
    private int peso;

    public AristaConPeso(){
        
    }
    
    public AristaConPeso(int verticeOrigen, int verticeDestino, int Peso) {
        this.verticeOrigen = verticeOrigen;
        this.verticeDestino = verticeDestino;
        this.peso = Peso;
    }

    public int getVerticeOrigen() {
        return verticeOrigen;
    }

    public void setVerticeOrigen(int verticeOrigen) {
        this.verticeOrigen = verticeOrigen;
    }

    public int getVerticeDestino() {
        return verticeDestino;
    }

    public void setVerticeDestino(int verticeDestino) {
        this.verticeDestino = verticeDestino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int Peso) {
        this.peso = Peso;
    }

    @Override
    public int compareTo(AristaConPeso o) {
        Integer estePeso = this.peso;
        Integer otroPeso = o.peso;
        
        return estePeso.compareTo(otroPeso);
    }

    @Override
    public boolean equals(Object aristaAComparar) {
        if(this == aristaAComparar){
            return true;
        }
        if(aristaAComparar == null || 
                this.getClass() != aristaAComparar.getClass()){
            return false;
        }
        AristaConPeso that = (AristaConPeso) aristaAComparar;
        if(this.peso == that.peso && this.verticeDestino == that.verticeDestino
                && this.verticeOrigen == that.verticeOrigen){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }
    
    @Override
    public String toString() {
        return "[" + this.verticeOrigen + ", " + this.verticeDestino + 
                ", " + this.peso + "]";
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.verticeOrigen;
        hash = 59 * hash + this.verticeDestino;
        hash = 59 * hash + this.peso;
        return hash;
    }
    
}
