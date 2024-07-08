package GrafosPesados;

import Negocio.DFS;

import java.util.ArrayList;
import java.util.List;

public class Tupla {
    private int verticeOrigen;
    private int verticeDestino;
    private double peso;

    public Tupla(int verticeOrigen,int verticeDestino,Double peso){
        this.verticeOrigen=verticeOrigen;
        this.verticeDestino=verticeDestino;
        this.peso=peso;
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

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * sera static por que no requerira instanciar objetos
     * sera propio de la clase
     *
     * metodo para obtemer un lista de tuplas ordenada
     * donde recibe un grafo y en base a ese grafo se recibe cada par de vertices con su peso
     * de manera ordenada
     * @param elGrafoPesado el grafo pesado
     * @return retorna una lista de tuplas ordenadas en base al peso
     * probando un comparato de manera funcional
     * con funcion lambda
     *
     *
     * la logica en esto es
     * antes de hacer un dfs
     * agarro todas las combinaciones de adyacentes en una lista
     *
     * luego de hacer eso recien hago un dfs
     * solamente agrego a la lista los no marcados
     */
    public static List<Tupla> getSorteredTupleList(GrafoPesado elGrafoPesado){
        List<Tupla> lista=new ArrayList<>();
        DFS dfs=new DFS(elGrafoPesado);
        dfs.ejecutarDFSRecursivoPesadoParaTupla(0,lista);
        lista.sort((tupla1,tupla2)->{
            return Double.compare(tupla1.getPeso(),tupla2.getPeso());});
        return lista;

    }
    @Override
    public String toString(){
        return this.getVerticeOrigen()+" "+this.getVerticeDestino()+" "+this.getPeso();
    }
}
