import Negocio.Digrafo;
import Negocio.ErrorYaExisteAdyacencia;

public class pruebaDigrafoFuertementeConexo {
    public static void main(String[] args){
        Digrafo digrafo1=new Digrafo(5);
        try {
            digrafo1.insertarArista(0,1);
            digrafo1.insertarArista(1,3);
            digrafo1.insertarArista(3,4);
            digrafo1.insertarArista(4,0);
            digrafo1.insertarArista(1,2);
            digrafo1.insertarArista(2,3);
            System.out.println(digrafo1.esFuertementeConexo3());
        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }
    }
}
