import GrafosPesados.DigrafoPesado;
import GrafosPesados.Floyd;
import Negocio.ErrorYaExisteAdyacencia;

public class pruebaFloyd {
    public static void main(String[] args){
        DigrafoPesado digrafo1=new DigrafoPesado(6);
        try {
            digrafo1.insertarArista(3,1,3);
            digrafo1.insertarArista(1,2,1);
            digrafo1.insertarArista(3,2,2);
            digrafo1.insertarArista(2,5,7);
            digrafo1.insertarArista(2,4,4);
            digrafo1.insertarArista(4,1,6);
            digrafo1.insertarArista(5,4,3);
            digrafo1.insertarArista(4,5,2);
            digrafo1.insertarArista(3,5,4);
            digrafo1.eliminarVertice(0);
            Floyd f1=new Floyd(digrafo1);
            f1.generarCamino(0,4);
            System.out.println(f1.getCamino());
        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }


    }
}
