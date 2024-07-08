import Negocio.ErrorNoExisteAdyacencia;
import Negocio.ErrorYaExisteAdyacencia;
import Negocio.Grafo;
import Negocio.BFS;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Grafo graph=new Grafo(5);
        Grafo grafo1=new Grafo(6);
        Grafo grafo2=new Grafo(7);
        try {
            graph.insertarArista(2,0);
            graph.insertarArista(4,1);
            graph.insertarArista(2,3);
            graph.insertarArista(2,4);
            graph.insertarVertice();
            graph.insertarArista(3,4);
            graph.insertarArista(3,1);
            graph.existeCamino(0,0);
            System.out.println("Graph existe camino? :"+graph.existeCamino(1,5));

            grafo1.insertarArista(0,0);
            grafo1.insertarArista(0,4);
            grafo1.insertarArista(3,2);
            grafo1.insertarArista(3,3);
        //    grafo1.insertarArista(4,0);
            grafo1.insertarArista(4,1);
            grafo1.insertarArista(5,1);
            grafo1.insertarArista(5,5);
            grafo1.insertarArista(1,2);


            grafo2.insertarArista(3,6);
            grafo2.insertarArista(4,6);
            grafo2.insertarArista(2,6);
            grafo2.insertarArista(2,0);
            grafo2.insertarArista(4,0);
            grafo2.insertarArista(5,1);
            grafo2.insertarArista(2,5);
            grafo2.insertarArista(0,1);
            //grafo2.insertarArista(2,0);
            System.out.println("Grafo2: \n"+grafo2.toString());
            BFS recorridoBFS=new BFS(grafo2,4);
            System.out.println("Grafo2: recorrido BFS: "+recorridoBFS.getRecorrido());

            System.out.println(grafo1.toString());

            graph.eliminarArista2(1,3);

                System.out.println(grafo1.toString());


            //System.out.println(graph.toString());
        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }

    }
}
                //prueba para eliminar arista
                /*grafo1.eliminarArista2(1,4);
                grafo1.eliminarArista2(3,3);
                grafo1.eliminarArista2(5,5);
                grafo1.eliminarArista2(0,4);
                grafo1.eliminarArista2(2,1);
                grafo1.eliminarArista2(0,0);
                grafo1.eliminarArista2(0,0);*/
                //prueba para eliminar vertices
                /*grafo1.eliminarVertice2(0);
                    grafo1.eliminarVertice2(2);
                    grafo1.eliminarVertice(0);
                    grafo1.eliminarVertice(2);
                    grafo1.eliminarVertice(1);
                    grafo1.eliminarVertice(0);*/
