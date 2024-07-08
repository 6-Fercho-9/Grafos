import Negocio.Digrafo;
import Negocio.ErrorNoExisteAdyacencia;
import Negocio.ErrorYaExisteAdyacencia;

public class pruebasDigrafo {
    public static void main(String[] args){
        Digrafo digrafo=new Digrafo(5);
        Digrafo digrafo1=new Digrafo(8);
        try {
            digrafo.insertarArista(0,1);
            digrafo.insertarArista(1,0);
            digrafo.insertarArista(1,1);
            digrafo.insertarArista(2,3);
            digrafo.insertarArista(3,0);
            digrafo.insertarArista(4,0);


            digrafo1.insertarArista(1,0);
            digrafo1.insertarArista(2,1);
            digrafo1.insertarArista(2,4);
            digrafo1.insertarArista(0,2);
            digrafo1.insertarArista(0,3);
            digrafo1.insertarArista(0,4);
            digrafo1.insertarArista(4,6);
            digrafo1.insertarArista(6,4);
            digrafo1.insertarArista(3,6);
            digrafo1.insertarArista(3,5);
            digrafo1.insertarArista(5,3);
            digrafo1.insertarArista(5,7);
            digrafo1.insertarArista(7,5);
            digrafo1.insertarArista(7,6);

            //try {
                /*digrafo.eliminarArista(1,1);
                digrafo.eliminarArista(0,1);
                digrafo.eliminarArista(4,0);
                digrafo.eliminarArista(1,0);*/
                /*digrafo1.eliminarVertice(0);
                digrafo1.eliminarVertice(4);
                digrafo1.eliminarVertice(3);
                digrafo1.eliminarVertice(4);*/
            /*} catch (ErrorNoExisteAdyacencia e) {
                throw new RuntimeException(e);
            }*/
            System.out.println("Grado de entrada: "+digrafo1.gradoDeEntrada(5));
            System.out.println("Grado de salida: "+digrafo1.gradoDeSalida(5));
            System.out.println("DiGrafo 1:\n"+digrafo1.toString());
            System.out.println(digrafo.toString());

        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }
    }
}
