import Negocio.Digrafo;
import Negocio.ErrorNoCumpleRequisitos;
import Negocio.ErrorYaExisteAdyacencia;
import Negocio.OrdenamientoTopologico;

public class pruebaOrdenamientoTopologico {
    public static void main(String[] args){
        Digrafo digrafo1=new Digrafo(10);
        try {
            digrafo1.insertarArista(9,3);
            digrafo1.insertarArista(2,3);
            digrafo1.insertarArista(2,1);
            digrafo1.insertarArista(2,5);
            digrafo1.insertarArista(5,0);
            digrafo1.insertarArista(5,7);
            digrafo1.insertarArista(0,8);
            digrafo1.insertarArista(7,8);
            digrafo1.insertarArista(6,8);
            digrafo1.insertarArista(4,6);
            digrafo1.insertarArista(1,4);
            digrafo1.insertarArista(3,4);
            digrafo1.insertarArista(3,6);

            System.out.println(digrafo1.toString());
            OrdenamientoTopologico grafo1OT=new OrdenamientoTopologico(digrafo1);
            System.out.println("Ordenamiento Topologico: "+grafo1OT.getRecorrido());
        } catch (ErrorYaExisteAdyacencia | ErrorNoCumpleRequisitos e) {
            throw new RuntimeException(e);
        }
    }
}
