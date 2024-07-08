import Negocio.AlgoritmoWharshall;
import Negocio.Digrafo;
import Negocio.ErrorYaExisteAdyacencia;
import Negocio.Grafo;

import java.util.Arrays;

public class pruebaAlgoritmoDeWharshall {
    public static void main(String[] args){
        Digrafo digrafo1=new Digrafo(4);
        Grafo graf=new Grafo();
        Digrafo digrafo2=new Digrafo(5);
        Digrafo digrafo3=new Digrafo(10);
        try {
            digrafo1.insertarArista(0,1);
            digrafo1.insertarArista(1,2);
            digrafo1.insertarArista(1,0);
            digrafo1.insertarArista(2,3);

            digrafo2.insertarArista(0,0);
            digrafo2.insertarArista(0,3);
            digrafo2.insertarArista(3,0);
            digrafo2.insertarArista(2,3);
            digrafo2.insertarArista(2,4);
            digrafo2.insertarArista(4,4);
            digrafo2.insertarArista(1,1);
            digrafo2.insertarArista(4,1);

            digrafo3.insertarArista(9,3);
            digrafo3.insertarArista(2,3);
            digrafo3.insertarArista(2,1);
            digrafo3.insertarArista(2,5);
            digrafo3.insertarArista(5,0);
            digrafo3.insertarArista(5,7);
            digrafo3.insertarArista(0,8);
            digrafo3.insertarArista(7,8);
            digrafo3.insertarArista(6,8);
            digrafo3.insertarArista(4,6);
            digrafo3.insertarArista(1,4);
            digrafo3.insertarArista(3,4);
            digrafo3.insertarArista(3,6);

            AlgoritmoWharshall algW=new AlgoritmoWharshall(digrafo1);
            AlgoritmoWharshall algW2=new AlgoritmoWharshall(digrafo2);
            AlgoritmoWharshall algW3=new AlgoritmoWharshall(digrafo3);
            System.out.println(Arrays.deepToString(algW.getMatriz()));
            System.out.println(Arrays.deepToString(algW2.getMatriz()));
            System.out.println(Arrays.deepToString(algW3.getMatriz()));
            //System.out.println(algW3.tieneUnoTodaDiagonalPrincipal());
        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }
    }
}
