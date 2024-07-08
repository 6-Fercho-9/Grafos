import Negocio.Digrafo;
import Negocio.ErrorYaExisteAdyacencia;

public class pruebaDigrafoDebilmenteConexo {
    public static void main(String[]args){
        Digrafo digrafo1=new Digrafo(7);
        try {
            digrafo1.insertarArista(1,2);
            digrafo1.insertarArista(2,3);
            digrafo1.insertarArista(3,4);
            digrafo1.insertarArista(4,5);
            digrafo1.insertarArista(5,6);
            digrafo1.insertarArista(6,1);


        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }
    }
}
