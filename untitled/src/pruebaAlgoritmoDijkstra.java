import GrafosPesados.AdyacenteConPeso;
import GrafosPesados.DigrafoPesado;
import GrafosPesados.Dijkstra;
import GrafosPesados.GrafoPesado;
import Negocio.ErrorYaExisteAdyacencia;

public class pruebaAlgoritmoDijkstra {
    public static void main(String[] args){
        DigrafoPesado digrafoPesado1=new DigrafoPesado(6);
        GrafoPesado grafoPesado1=new GrafoPesado(10);
        GrafoPesado grafoPesado2=new GrafoPesado(7);
        try {
            digrafoPesado1.insertarArista(0,1,50);
            digrafoPesado1.insertarArista(0,2,10);
            digrafoPesado1.insertarArista(2,1,5);
            digrafoPesado1.insertarArista(3,0,80);
            digrafoPesado1.insertarArista(1,3,50);
            digrafoPesado1.insertarArista(3,5,20);
            digrafoPesado1.insertarArista(1,4,15);
            digrafoPesado1.insertarArista(4,5,20);
            digrafoPesado1.insertarArista(5,1,40);
            digrafoPesado1.insertarArista(0,5,100);
            digrafoPesado1.insertarArista(5,2,70);
            digrafoPesado1.insertarArista(0,4,60);

            grafoPesado1.insertarArista(8,2,7);
            grafoPesado1.insertarArista(8,1,7);
            grafoPesado1.insertarArista(8,0,6);

            grafoPesado1.insertarArista(2,1,1);

            grafoPesado1.insertarArista(1,0,2);

            grafoPesado1.insertarArista(2,4,3);
            grafoPesado1.insertarArista(1,4,4);
            grafoPesado1.insertarArista(1,3,3);
            grafoPesado1.insertarArista(0,3,4);

            grafoPesado1.insertarArista(4,3,3);

            grafoPesado1.insertarArista(4,5,5);
            grafoPesado1.insertarArista(4,6,9);
            grafoPesado1.insertarArista(3,6,6);
            grafoPesado1.insertarArista(3,7,4);

            grafoPesado1.insertarArista(5,6,2);
            grafoPesado1.insertarArista(6,7,1);

            grafoPesado1.insertarArista(5,9,6);
            grafoPesado1.insertarArista(6,9,7);
            grafoPesado1.insertarArista(7,9,6);

            grafoPesado2.insertarArista(0,1,2);
            grafoPesado2.insertarArista(0,2,6);
            grafoPesado2.insertarArista(1,3,5);
            grafoPesado2.insertarArista(2,3,8);
            grafoPesado2.insertarArista(3,5,15);
            grafoPesado2.insertarArista(3,4,10);
            grafoPesado2.insertarArista(5,4,6);
            grafoPesado2.insertarArista(4,6,2);
            grafoPesado2.insertarArista(5,6,6);
            Dijkstra dijkstra1=new Dijkstra(digrafoPesado1,0,5);
            Dijkstra dijkstra2=new Dijkstra(grafoPesado1,8,9);
            Dijkstra dijkstra3=new Dijkstra(grafoPesado2,6);
            System.out.println(digrafoPesado1.toString());
            System.out.println("Recorrido: de 0..5 "+dijkstra1.getRecorrido());
            System.out.println("Costo Minimo: de 0..5 "+dijkstra1.getCostoMinimo());

            System.out.println(grafoPesado1.toString());
            System.out.println("Recorrido: de 8..9 "+dijkstra2.getRecorrido());
            System.out.println("Costo Minimo: de 8..9 "+dijkstra2.getCostoMinimo());


            System.out.println(grafoPesado2.toString());
            System.out.println("Recorrido: de 8..9 "+dijkstra3.getRecorrido());
            System.out.println(dijkstra3.getPredecesores());
            System.out.println(dijkstra3.getCostos());
            //System.out.println("Costo Minimo: de 8..9 "+dijkstra3.getCostoMinimo());
        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }
    }
}
