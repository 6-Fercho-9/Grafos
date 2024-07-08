import Negocio.Digrafo;
import Negocio.ErrorYaExisteAdyacencia;

public class pruebaDigrafoToGrafo {
    public static void main(String[] args){
        Digrafo digrafo1=new Digrafo(4);
        Digrafo digrafo2=new Digrafo(6);
        try {
            digrafo1.insertarArista(0,1);
            digrafo1.insertarArista(0,3);
            digrafo1.insertarArista(2,1);
            digrafo1.insertarArista(2,3);

            digrafo2.insertarArista(0,1);
            digrafo2.insertarArista(2,1);
            digrafo2.insertarArista(2,3);
            digrafo2.insertarArista(3,4);
            digrafo2.insertarArista(4,5);
            digrafo2.insertarArista(5,2);
            digrafo2.insertarArista(0,5);
            System.out.println(digrafo2.getGrafo().toString());
        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }
    }
}
