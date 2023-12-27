/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficct.ed2.grafos;

/**package ficct.ed2.grafos.pesados;

 *
 * @author OJavierHR
 */
public class AdyacenteConPeso implements Comparable<AdyacenteConPeso>{
    private int indiceVertice;
    private int peso;

    public AdyacenteConPeso(int indiceVertice) {
        this.indiceVertice = indiceVertice;
        this.peso = 0;
    }

    public AdyacenteConPeso(int indiceVertice, int peso) {
        this.indiceVertice = indiceVertice;
        this.peso = peso;
    }

    public int getIndiceVertice() {
        return indiceVertice;
    }

    public void setIndiceVertice(int indiceVertice) {
        this.indiceVertice = indiceVertice;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    @Override
    public int compareTo(AdyacenteConPeso adyacenteConPesoAComparar) {
        Integer esteIndiceVertice = this.indiceVertice;
        Integer indiceVerticeAComparar = 
                adyacenteConPesoAComparar.indiceVertice;
        return esteIndiceVertice.compareTo(indiceVerticeAComparar);
    }

    @Override
    public boolean equals(Object adyacenteAComparar) {
        if(this == adyacenteAComparar){
            return true;
        }
        if(adyacenteAComparar == null || 
                this.getClass() != adyacenteAComparar.getClass()){
            return false;
        }
        AdyacenteConPeso that = (AdyacenteConPeso) adyacenteAComparar;
        return this.getIndiceVertice() == that.getIndiceVertice();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.indiceVertice;
        hash = 17 * hash + this.peso;
        return hash;
    }
    
}
