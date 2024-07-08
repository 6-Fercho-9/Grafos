package Negocio;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.naming.ldap.Control;

/**
 * esta clase realizara el metodo del recorrido del grafo mediante un BFS
 * para ello siempre hay que tener en cuenta que un recorrido en un grafo
 * se requiere uno el grafo,y 2 por donde queremos iniciar el recorrido
 * a diferencia del arbol
 * el grafo puede empezar por cualquierda de los vertices el recorrido
 * sin embargo en el arbol siempre empezaba por la raiz
 */
public class BFS {
    Grafo elGrafo;
    ControlMarcado marcados;
    List<Integer> recorrido;
    //dado que cada instancia del BFS obtendremos el recorrido en bfs del grafo
    //requerimos un grafo y la posicion(vertice) por donde queremos hacer el recorrido
    public BFS(Grafo unGrafo,int vertice){
        this.elGrafo=unGrafo;
        this.recorrido=new ArrayList<>();
        this.marcados=new ControlMarcado(elGrafo.cantidadDeVertices());
        this.ejecutarBFS(vertice);
    }
    public void ejecutarBFS(int vertice){
        this.elGrafo.validarVertice(vertice);
        Queue<Integer> cola=new LinkedList<>();
        cola.offer(vertice);
        marcados.marcarVertice(vertice);//marco el vertice que ya visite
        do{
            int verticeActual=cola.poll();//vertice auxiliar
            recorrido.add(verticeActual);
            //obtengo los adyacentes a ese vertice
            Iterable<Integer> adyancentesDelVertice=elGrafo.adyacentesDelVertice(verticeActual);
            //para cada elemento de los adyacentes,si no estan marcados
            //los agrego a la cola y lo marco,para no volver por ahi
            for(Integer elemento:adyancentesDelVertice){
                if(!this.marcados.estaMarcado(elemento)){
                    cola.offer(elemento);
                    marcados.marcarVertice(elemento);
                }
            }
        }while(!cola.isEmpty());
    }
    public Iterable<Integer> getRecorrido(){
        return this.recorrido;
    }
    //para saber si llegue a un vertice o algo asi,la respuesta a esto nos la puede dar la lista de marcados
    public boolean llegueATodos(){
        return this.marcados.estanTodosMarcados();
    }
    public boolean llegueAVertice(int posicion){
        this.elGrafo.validarVertice(posicion);
        return this.marcados.estaMarcado(posicion);
    }
}
