package GrafosPesados;

import Negocio.ControlMarcado;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * falta hacer la validacion del infinito
 */
public class Dijkstra {
    private static final int POSICION_INVALIDA=-1;
    private final ControlMarcado marcados;
    private final ControlCostos costos;
    private final Predecesores predecesores;
    private final GrafoPesado elGrafo;
    private final List<Integer> recorrido;
    private double costoMinimo;
    //private final List<Integer>
    public Dijkstra(GrafoPesado unGrafo,int verticeInicio,int verticeFin){
        this.elGrafo=unGrafo;
        this.elGrafo.validarVertice(verticeInicio);
        this.elGrafo.validarVertice(verticeFin);
        this.recorrido=new ArrayList<>();
        this.predecesores=new Predecesores(unGrafo.cantidadDeVertices());
        this.marcados=new ControlMarcado(unGrafo.cantidadDeVertices());
        this.costos=new ControlCostos(unGrafo.cantidadDeVertices());
        this.costos.setCosto(verticeInicio,0);
        this.ejecutarDijkstra(verticeInicio,verticeFin);
        this.generarRecorrido(verticeFin);//aca me genera el camino de como llego de A a B tomando el cuenta A,pero falta B
        this.recorrido.add(verticeFin);//agrego el vertice final
        this.setCostoMinimo(this.costos.getCosto(verticeFin));
    }
    public Dijkstra(GrafoPesado unGrafo,int verticeInicio){
        this.elGrafo=unGrafo;
        this.elGrafo.validarVertice(verticeInicio);
        this.recorrido=new ArrayList<>();
        this.predecesores=new Predecesores(unGrafo.cantidadDeVertices());
        this.marcados=new ControlMarcado(unGrafo.cantidadDeVertices());
        this.costos=new ControlCostos(unGrafo.cantidadDeVertices());
        this.costos.setCosto(verticeInicio,0);
        this.ejecutarDijkstra(verticeInicio);

    }

    public double getCostoMinimo(){
        return this.costoMinimo;
    }
    private void setCostoMinimo(double costo){
        this.costoMinimo=costo;
    }
    public void ejecutarDijkstra(int verticeInicio,int verticeFin){
            this.marcados.marcarVertice(verticeInicio);
            Iterable<AdyacenteConPeso> adyacentes=this.elGrafo.adyacentesDelVertice(verticeInicio);
            for(AdyacenteConPeso elemento: adyacentes){
                int vertice=elemento.getIndiceVertice();
                if(!this.marcados.estaMarcado(vertice)){
                    double sumaPesoCosto=(this.elGrafo.peso(verticeInicio,vertice)+this.costos.getCosto(verticeInicio));
                    if(this.costos.calcularCosto(vertice)>sumaPesoCosto){
                        this.costos.setCosto(vertice,sumaPesoCosto);
                        this.predecesores.setValor(vertice,verticeInicio);
                    }
                }
            }
            if(verticeInicio!=verticeFin){
                int nuevoVerticeInicio=this.costos.buscarCostoMinimo(this.marcados);
                if(nuevoVerticeInicio!=Dijkstra.POSICION_INVALIDA ) {//este if no se si es necsario
                    this.ejecutarDijkstra(nuevoVerticeInicio, verticeFin);
                }
            }
    }

    public List<Integer> getRecorrido(){
        return this.recorrido;
    }
    public void generarRecorrido(int vertice){
        if(predecesores.getValor(vertice)!=Dijkstra.POSICION_INVALIDA){
            int verticePredecesor=predecesores.getValor(vertice);
            this.generarRecorrido(verticePredecesor);
            this.recorrido.add(verticePredecesor);
        }
    }
    public ControlMarcado getMarcados(){
        return this.marcados;
    }
    public ControlCostos getCostos(){
        return this.costos;
    }
    public Predecesores getPredecesores(){
        return this.predecesores;
    }
    public void ejecutarDijkstra(int verticeInicio){
        this.marcados.marcarVertice(verticeInicio);
        this.recorrido.add(verticeInicio);
        Iterable<AdyacenteConPeso> adyacentes=this.elGrafo.adyacentesDelVertice(verticeInicio);
        for(AdyacenteConPeso elemento: adyacentes){
            int vertice=elemento.getIndiceVertice();
            if(!this.marcados.estaMarcado(vertice)){
                double sumaPesoCosto=(this.elGrafo.peso(verticeInicio,vertice)+this.costos.getCosto(verticeInicio));
                if(this.costos.calcularCosto(vertice)>sumaPesoCosto){
                    this.costos.setCosto(vertice,sumaPesoCosto);
                    this.predecesores.setValor(vertice,verticeInicio);
                }
            }
        }
        //sacara una posicion invalida cuando todos esten marcados
        //asi que no es necesario preguntar si todos estan marcados
        //se asumer que si el vertice inicio es -1,ya estan todos marcados
        verticeInicio=this.costos.buscarCostoMinimo(this.marcados);
        if(verticeInicio!=Dijkstra.POSICION_INVALIDA){
            boolean esCostoInfinito=this.costos.getCosto(verticeInicio)==ControlCostos.INFINITO;
            if(!esCostoInfinito)
                this.ejecutarDijkstra(verticeInicio);
        }
        //va parar si el vertice inicio es==-1 o si es costo infinito
    }
}
