package Negocio;

public class pruebaCiclosGrafo {
    public static void main(String[] args){
        Grafo grafo1=new Grafo(5);
        Grafo grafo2=new Grafo(3);
        Grafo grafo3=new Grafo(6);
        Grafo grafo4=new Grafo(3);
        Grafo grafo5=new Grafo(10);
        try {
            grafo1.insertarArista(1,3);
            grafo1.insertarArista(3,0);
            grafo1.insertarArista(0,4);
            grafo1.insertarArista(1,4);

            grafo2.insertarArista(0,1);
            grafo2.insertarArista(1,2);

            grafo3.insertarArista(0,4);
            grafo3.insertarArista(4,1);
            grafo3.insertarArista(3,2);
            grafo3.insertarArista(2,5);
            grafo3.insertarArista(3,5);

            grafo4.insertarArista(0,1);
            grafo4.insertarArista(1,2);
            grafo4.insertarArista(2,0);

            grafo5.insertarArista(0,2);
            grafo5.insertarArista(5,2);
            grafo5.insertarArista(1,6);
            grafo5.insertarArista(3,6);
            grafo5.insertarArista(7,4);
            grafo5.insertarArista(7,9);
            grafo5.insertarArista(4,8);
            grafo5.insertarArista(9,8);
            System.out.println(grafo5.existeCiclos(1));

          //  System.out.println(grafo1.toString());
        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }
    }

}
