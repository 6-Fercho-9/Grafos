package GrafosPesados;
import Negocio.ControlMarcado;
import Negocio.ErrorYaExisteAdyacencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Prim {
    private GrafoPesado elGrafoOriginal;
    private GrafoPesado elArbolPrim;
    private ControlMarcado marcados;
    private double minimoPosible;


    public Prim(GrafoPesado elGrafoOriginal){
        this.elGrafoOriginal=elGrafoOriginal;
        this.elArbolPrim=new GrafoPesado(this.elGrafoOriginal.cantidadDeVertices());
        this.marcados=new ControlMarcado(this.elGrafoOriginal.cantidadDeVertices());
        this.minimoPosible=0;
        this.ejecutarPrim();
    }
    public void ejecutarPrim(){
        List<Integer> listaDeVertices=new ArrayList<>();
        List<Integer> listaDeVerticesPrima=new ArrayList<>();
        List<Tupla>listaTupla=new ArrayList<>();
        this.crearListaVertices(listaDeVerticesPrima);

        int verticeRemovido=listaDeVerticesPrima.remove(0);
        listaDeVertices.add(verticeRemovido);
        this.marcados.marcarVertice(verticeRemovido);
        int verticeInicio;
        int verticeDestino;
        while(!listaDeVerticesPrima.isEmpty()){
            listaTupla.clear();
            Tupla tuplaMenorCosto=this.adyacenteMenorCosto(listaDeVertices,listaTupla);
            verticeInicio=tuplaMenorCosto.getVerticeOrigen();
            verticeDestino=tuplaMenorCosto.getVerticeDestino();
            //System.out.println(tuplaMenorCosto);
            this.minimoPosible+=tuplaMenorCosto.getPeso();
            try {
                this.elArbolPrim.insertarArista(verticeInicio,verticeDestino,tuplaMenorCosto.getPeso());
            } catch (ErrorYaExisteAdyacencia e) {
                throw new RuntimeException(e);
            }
            listaDeVerticesPrima.remove((Integer)verticeDestino);//se remueve el objeto vertice destino
            listaDeVertices.add(verticeDestino);//se lo agrega a la lista de vertices
            this.marcados.marcarVertice(verticeDestino);
        }
    }
    public Tupla adyacenteMenorCosto(List<Integer> listaVertices,List<Tupla> listaTupla){
        for (Integer vertice: listaVertices){
            Iterable<AdyacenteConPeso> adyacentesAlVerticeConPeso=this.elGrafoOriginal.adyacentesDelVertice(vertice);
            for (AdyacenteConPeso adyacente: adyacentesAlVerticeConPeso){
                int verticeAdyacenteConPeso = adyacente.getIndiceVertice();
                if(!this.marcados.estaMarcado(verticeAdyacenteConPeso)){
                    listaTupla.add(new Tupla(vertice,verticeAdyacenteConPeso,this.elGrafoOriginal.peso(vertice,verticeAdyacenteConPeso)));
                }
            }
        }
        //me retorna la tupla mas pequeña
        /**
         * usando un lambda,otra forma de hacerlo es creando una clase anonima con el comparator
         *
         */
        return Collections.min(listaTupla,(e1,e2)->{
            return Double.compare(e1.getPeso(),e2.getPeso());
        });
    }

    public void crearListaVertices(List<Integer> listaVertices){
        for (int i = 0; i < this.elGrafoOriginal.cantidadDeVertices() ; i++) {
            listaVertices.add(i);
        }
    }
    public GrafoPesado getElGrafoOriginal() {
        return elGrafoOriginal;
    }

    public GrafoPesado getElArbolPrim() {
        return elArbolPrim;
    }

    public ControlMarcado getMarcados() {
        return marcados;
    }

    public double getMinimoPosible() {
        return minimoPosible;
    }

}
