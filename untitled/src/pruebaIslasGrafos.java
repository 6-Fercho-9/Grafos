import Negocio.ErrorYaExisteAdyacencia;
import Negocio.Grafo;

public class pruebaIslasGrafos {
    public static void main(String[] args){
        Grafo grafo1=new Grafo(17);
        Grafo grafo2=new Grafo(8);
        try {
            grafo1.insertarArista(0,1);
            grafo1.insertarArista(0,2);
            grafo1.insertarArista(2,1);
            grafo1.insertarArista(1,1);
            grafo1.insertarArista(2,2);
            grafo1.insertarArista(0,0);
            grafo1.insertarArista(3,4);
            grafo1.insertarArista(6,7);
            grafo1.insertarArista(6,6);
            grafo1.insertarArista(7,7);
            grafo1.insertarArista(8,8);
            grafo1.insertarArista(9,9);
            grafo1.insertarArista(10,10);
            grafo1.insertarArista(11,11);
            grafo1.insertarArista(12,13);
            grafo1.insertarArista(14,13);
            grafo1.insertarArista(15,14);
            grafo1.insertarArista(16,12);
            grafo1.insertarArista(16,13);
            grafo1.insertarArista(16,14);
            grafo1.insertarArista(16,15);


       //     System.out.println(grafo2.cantidadIslas(0));
        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }
    }
}
