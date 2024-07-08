import GrafosPesados.DigrafoPesado;
import GrafosPesados.GrafoPesado;
import Negocio.ErrorYaExisteAdyacencia;
import Negocio.Grafo;

public class pruebaGrafoPesado {
    public static void main(String [] args){
        GrafoPesado grafo1=new GrafoPesado(10);
        DigrafoPesado digrafo1=new DigrafoPesado(4);
        try {
            grafo1.insertarArista(0,0,10);
            grafo1.insertarArista(0,1,80);
            grafo1.insertarArista(0,2,90);
            grafo1.insertarArista(2,1,60);
            grafo1.insertarArista(2,3,200);
            grafo1.insertarArista(1,3,300);
            grafo1.insertarArista(3,3,600);

            digrafo1.insertarArista(1,0,80);
            digrafo1.insertarArista(1,2,10);
            digrafo1.insertarArista(0,2,60);
            digrafo1.insertarArista(2,3,100);
            digrafo1.insertarArista(3,0,90);
            System.out.println(digrafo1.toString());
            digrafo1.eliminarVertice(0);
            System.out.println(digrafo1.toString());
        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }
    }
}
