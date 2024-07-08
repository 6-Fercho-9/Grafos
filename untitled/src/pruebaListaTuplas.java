import GrafosPesados.GrafoPesado;
import GrafosPesados.Tupla;
import Negocio.ErrorYaExisteAdyacencia;
import Negocio.Grafo;

import java.util.List;

public class pruebaListaTuplas {
    public static void main(String[] args){
        GrafoPesado grafo1=new GrafoPesado(11);
        try {

            grafo1.insertarArista(1,2,5);
            grafo1.insertarArista(1,4,8);
            grafo1.insertarArista(1,3,10);

            grafo1.insertarArista(2,4,6);
            grafo1.insertarArista(2,6,5);

            grafo1.insertarArista(3,4,7);
            grafo1.insertarArista(3,5,8);
            grafo1.insertarArista(3,8,15);

            grafo1.insertarArista(4,6,11);
            grafo1.insertarArista(4,5,5);

            grafo1.insertarArista(5,7,4);
            grafo1.insertarArista(5,8,3);

            grafo1.insertarArista(6,7,9);
            grafo1.insertarArista(6,9,7);

            grafo1.insertarArista(7,9,4);
            grafo1.insertarArista(8,7,12);

            grafo1.insertarArista(10,9,7);
            grafo1.insertarArista(10,7,6);
            grafo1.insertarArista(10,8,12);
            grafo1.eliminarVertice(0);
            Grafo g1=grafo1.getGrafoNoPesado();
            System.out.println(grafo1);
            System.out.println(g1);
            List<Tupla> listaTupla=Tupla.getSorteredTupleList(grafo1);
            for (Tupla t:listaTupla)
                System.out.println(t);

        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }
    }
}
