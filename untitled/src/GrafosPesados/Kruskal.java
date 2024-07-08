package GrafosPesados;

import Negocio.ControlMarcado;
import Negocio.ErrorNoExisteAdyacencia;
import Negocio.ErrorYaExisteAdyacencia;
import Negocio.Grafo;

import java.util.List;

public class Kruskal {
    private final GrafoPesado elGrafoOriginal;
    //private ControlMarcado marcados;
    private final GrafoPesado elArbolKruskal;
    private final List<Tupla> listaTupla;
    private double minimoPosible;
    public Kruskal(GrafoPesado elGrafoOriginal){
        this.elGrafoOriginal=elGrafoOriginal;
        this.elArbolKruskal=new GrafoPesado(this.elGrafoOriginal.cantidadDeVertices());//este es el que tendra el arbol
        this.listaTupla=Tupla.getSorteredTupleList(this.elGrafoOriginal);
        this.minimoPosible=0;
        this.ejecutarKruskal();
    }

    /**
     * el krsukal consiste en obtener los pares de vertices con sus pesos de manera ordenada
     * entonces se debe hacer eso,para eso esta la lista tupla
     * luego de eso se debe iterar sobre esa lista
     *
     * y solamente se inserta aristas entre el vertice A y vertice B
     * con su peso y si al insertar forma ciclos,entonces
     * se debe eliminar la arista insertada
     */
    public void ejecutarKruskal(){
        int verticeOrigen;
        int verticeDestino;
        for (Tupla tupla: this.listaTupla){
            try {
                verticeOrigen=tupla.getVerticeOrigen();
                verticeDestino=tupla.getVerticeDestino();
                this.elArbolKruskal.insertarArista(verticeOrigen,verticeDestino,tupla.getPeso());
                if(this.elArbolKruskal.existeCiclos(verticeOrigen)){
                    try {
                        this.elArbolKruskal.eliminarArista(verticeOrigen,verticeDestino);
                    } catch (ErrorNoExisteAdyacencia e) {
                        throw new RuntimeException(e);
                    }
                }else{//si no hay ciclo,entonces se debe sumar su peso para(como suma de aristas)
                    this.minimoPosible+=tupla.getPeso();//con esto obtenedremos el minimo posible
                }
            } catch (ErrorYaExisteAdyacencia e) {
                throw new RuntimeException(e);
            }
        }
    }

    public GrafoPesado getGrafoOriginal() {
        return elGrafoOriginal;
    }

    public GrafoPesado getArbolKruskal() {
        return elArbolKruskal;
    }

    public List<Tupla> getListaTupla() {
        return listaTupla;
    }
    public double getMinimoPosible(){
        return this.minimoPosible;
    }
}
