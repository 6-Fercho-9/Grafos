import Negocio.ErrorYaExisteAdyacencia;
import Negocio.Grafo;
import Negocio.BFS;
import Negocio.DFS;

public class PruebaDFS {
    public static void main(String[] args){
        Grafo grafo2=new Grafo(7);
        try {
            grafo2.insertarArista(3,6);
            grafo2.insertarArista(4,6);
            grafo2.insertarArista(2,6);
            grafo2.insertarArista(2,0);
            grafo2.insertarArista(4,0);
            grafo2.insertarArista(5,1);
            grafo2.insertarArista(2,5);
            grafo2.insertarArista(0,1);
            BFS grafo2BFS=new BFS(grafo2,4);
            DFS grafo2DFS=new DFS(grafo2,5);
            System.out.println("BFS: "+grafo2BFS.getRecorrido());
            System.out.println("DFS: "+grafo2DFS.getRecorrido());
        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }

    }
}
