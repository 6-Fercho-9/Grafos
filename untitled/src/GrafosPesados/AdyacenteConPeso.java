package GrafosPesados;

import java.util.Objects;

public class AdyacenteConPeso implements Comparable<AdyacenteConPeso>{
    private int indiceVertice;
    private double peso;

    public AdyacenteConPeso(){

    }
    public AdyacenteConPeso(int indiceVertice, double peso) {
        this.indiceVertice = indiceVertice;
        this.peso = peso;
    }

    public AdyacenteConPeso(int indiceVertice) {
        this.indiceVertice = indiceVertice;
    }

    public int getIndiceVertice() {
        return indiceVertice;
    }

    public void setIndiceVertice(int indiceVertice) {
        this.indiceVertice = indiceVertice;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public boolean equals(Object elOtroObjeto) {
        if (elOtroObjeto == null)
            return false;
        AdyacenteConPeso elOtroAdyacente = (AdyacenteConPeso) elOtroObjeto;
        return this.compareTo(elOtroAdyacente)==0;//si al compararlos es igual a 0
        //entonces retorna true,si no false
    }

    @Override
    public int hashCode() {
        return Objects.hash(indiceVertice, peso);
    }

    @Override
    public int compareTo(AdyacenteConPeso o) {
        //validar segun criterio
        /*return this.getIndiceVertice()>o.getIndiceVertice()?1:
                this.getIndiceVertice()<o.getIndiceVertice()?-1:0;*/
        return this.getIndiceVertice()-o.getIndiceVertice();
    }
    @Override
    public String toString(){
        return "["+this.getIndiceVertice()+"|"+this.getPeso()+"]";
    }
}
