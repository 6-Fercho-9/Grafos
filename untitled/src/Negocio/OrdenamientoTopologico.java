package Negocio;
import java.util.*;
public class OrdenamientoTopologico {
    private final Digrafo elGrafo;
    private final List<Integer>recorrido;
    public OrdenamientoTopologico(Digrafo elGrafo) throws ErrorNoCumpleRequisitos{
        recorrido=new ArrayList<>();
        this.elGrafo=elGrafo;
        if(!this.cumpleRequisitos()){
            throw new ErrorNoCumpleRequisitos();
        }
        this.ejecutarOrdenamientoTopologico();
    }
    public void ejecutarOrdenamientoTopologico() {
            Queue<Integer> cola=new LinkedList<>();
            List<Integer> listaConGradosDeEntrada=new ArrayList<>();
            this.cargarColaConEntradas0(cola,listaConGradosDeEntrada);//cargo la cola con entradas 0
            do{
                int verticeActual=cola.poll();//saco el vertice Actual
                this.recorrido.add(verticeActual);
                Iterable<Integer> adyacentesAlVerticeActual=this.elGrafo.adyacentesDelVertice(verticeActual);//O(E):E=aristas
                for(Integer elemento:adyacentesAlVerticeActual){//iteramos sobre los adyacentes del vertice actual
                    int gradoVertice=listaConGradosDeEntrada.get(elemento)-1;
                    listaConGradosDeEntrada.set(elemento,gradoVertice);
                    if(gradoVertice==0){
                        cola.offer(elemento);
                    }
                }
            }while(!cola.isEmpty());//O(n)

    }

    /**
     * para que pueda pasar y se ejecute correctamente el ordenamiento topologico
     * entonces las condiciones que debe cumplir son las siguientes
     * el grafo debe ser dirigido(diGrafo),debe ser conexo,debe ser sin ciclos
     *
     * //@return
     */

    /**
     * este metodo carga la lista con los grados de entrada de los vertices
     * y carga una cola con los vertices
     *el tiempo del metodo de grado de entrada de vertice es O(V)
     * y dado que el metodo grado de entrada tiene un coste O(E)
     * pero ambos metodos son independientes de si
     * El tiempo es O(V*E) supuestamente
     *
     * es O(n^2) cuando un ciclo interno depende del ciclo externo
     * for(i=1;i<n;i++)
     *   for(j=1;j<n;j++)
     * ahi si es cuadratico
     *
     * for(i=1;i<n;i++)
     *     for(j=1;j<m;j++)
     * aca seria O(n*m)
     * son 2 for independientes
     * por eso al recorrer una matriz es lineal tambien O(n*m)
     * @param cola=cola para los vertices
     * @param listaConGradosDeEntrada=lista para almacenar los grados de entrada de un vertice
     *
     *
     */
    private void cargarColaConEntradas0(Queue<Integer>cola,List<Integer> listaConGradosDeEntrada){
        for (int i = 0; i < this.elGrafo.cantidadDeVertices(); i++) {
            int gradoDeEntradaDelVertice=this.elGrafo.gradoDeEntrada(i);
            listaConGradosDeEntrada.add(gradoDeEntradaDelVertice);//agrego a la lista un vertice
            if(gradoDeEntradaDelVertice==0){
                cola.offer(i);//agrego a la cola el vertice con grado de entrada 0
            }
        }
    }
    public Iterable<Integer> getRecorrido(){
        return this.recorrido;
    }

    public boolean cumpleRequisitos(){
        boolean esDebilmenteConexo=this.elGrafo.esDebilmenteConexo();
        return esDebilmenteConexo&&!this.elGrafo.tieneCiclosLosVerticesI();
    }

}
