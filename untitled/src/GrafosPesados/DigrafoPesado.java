package GrafosPesados;

import Negocio.ErrorNoExisteAdyacencia;
import Negocio.ErrorYaExisteAdyacencia;

import java.util.Collections;
import java.util.List;

public class DigrafoPesado extends GrafoPesado{
    public DigrafoPesado(){
        super();
    }
    public DigrafoPesado(int nroVertices){
        super(nroVertices);
    }
    @Override
    public void insertarArista(int verticeA,int verticeB,double peso) throws ErrorYaExisteAdyacencia {
        super.validarVertice(verticeA);
        super.validarVertice(verticeB);
        if(super.existeAdyacencia(verticeA,verticeB))
            throw new ErrorYaExisteAdyacencia();
        //obtengo las adyacencias del verticeA
        List<AdyacenteConPeso> adyacenciasVerticeA=super.listaDeAdyacencia.get(verticeA);
        //le agrego el una nueva adyacencia al vertice A
        adyacenciasVerticeA.add(new AdyacenteConPeso(verticeB,peso));
        //lo ordeno
        Collections.sort(adyacenciasVerticeA);
    }
    @Override
    public void eliminarArista(int verticeA,int verticeB) throws ErrorNoExisteAdyacencia {
        //si no existe adyacencia error,aunque podria ser un return directo
        //por defecto tambien en la existencia de adyacencia ya marca error si hay posiciones invalidas
        if(!super.existeAdyacencia(verticeA,verticeB))
            throw new ErrorNoExisteAdyacencia();
        this.listaDeAdyacencia.get(verticeA).remove(new AdyacenteConPeso(verticeB));
    }
    /*@Override
    public void eliminarVertice(int verticeAEliminar) {
        super.validarVertice(verticeAEliminar);
        super.listaDeAdyacencia.remove(verticeAEliminar);//paso 1 elimino el vertice
        if(!super.listaDeAdyacencia.isEmpty()){
            //con este for tengo acceso a cada lista de adyacentes
            for(List<Integer> adyacenciasDelVertice:super.listaDeAdyacencia){
                adyacenciasDelVertice.remove((Integer) verticeAEliminar);
                //con este for tengo acceso a cada adyacente de un vertice
                for (int i = 0; i < adyacenciasDelVertice.size(); i++) {
                    int elemento=adyacenciasDelVertice.get(i);
                    if(elemento>verticeAEliminar)
                        adyacenciasDelVertice.set(i,elemento-1);

                }
            }
        }
    }*/

    /**
     * metodo para saber el grado de entrada de un vertice
     * el grado de entrada es para saber cuandtos arcos le llegan a un vertice
     * tiempo O(E) donde E representa a la cantidad de aristas que tenga el vertice V
     * si un vertice tiene E aristas de entrada entonces este metodo tardara O(E)
     * @param vertice=vertice
     * @return retorna la cantidad de aristas que le llegan a un determinado vertice
     */
    public int gradoDeEntrada(int vertice){
        super.validarVertice(vertice);
        int c=0;
        for(List<AdyacenteConPeso> adyacentesDelVertice:super.listaDeAdyacencia)
            if(adyacentesDelVertice.contains(new AdyacenteConPeso(vertice)))
                c++;

        return c;
    }

    /**
     * metodo para saber el grado de salida del vertice
     * es decir las aristas o arcos que salen de un vertice hacias otros
     * @param vertice
     * @return retorna la cantidad de aristas que salen de un determinado vertice
     */
    public int gradoDeSalida(int vertice){
        super.validarVertice(vertice);
        return super.listaDeAdyacencia.get(vertice).size();
    }
}
