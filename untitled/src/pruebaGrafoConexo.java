import Negocio.ErrorYaExisteAdyacencia;
import Negocio.Grafo;
public class pruebaGrafoConexo {
    public static void main(String[] args){
        Grafo grafo1=new Grafo(7);
        Grafo grafo2=new Grafo(7);
        try {
            grafo1.insertarArista(0,1);
            grafo1.insertarArista(0,2);
            grafo1.insertarArista(1,2);

            grafo1.insertarArista(2,3);
            grafo1.insertarArista(1,3);

            grafo1.insertarArista(2,5);
            grafo1.insertarArista(3,5);

            grafo1.insertarArista(6,5);
            grafo1.insertarArista(2,6);

            grafo1.insertarArista(2,4);
            grafo1.insertarArista(4,6);

            grafo1.insertarArista(0,4);

            grafo2.insertarArista(1,0);
            grafo2.insertarArista(0,6);
            grafo2.insertarArista(2,4);
            grafo2.insertarArista(2,3);
            grafo2.insertarArista(3,5);
            grafo2.insertarArista(5,4);
            System.out.println(grafo1.toString());


        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }
    }
}
