package Negocio;

import java.util.Collection;
import java.util.*;
public class Digrafo extends Grafo{

    public Digrafo(){
        super();
    }
    public Digrafo(int nroVertices){
        super(nroVertices);
    }
    @Override
    public void insertarArista(int verticeA,int verticeB) throws ErrorYaExisteAdyacencia{
        super.validarVertice(verticeA);
        super.validarVertice(verticeB);
        if(super.existeAdyacencia(verticeA,verticeB))
            throw new ErrorYaExisteAdyacencia();
        //obtengo las adyacencias del verticeA
        List<Integer> adyacenciasVerticeA=super.listaDeAdyacencia.get(verticeA);
        //le agrego el una nueva adyacencia al vertice A
        adyacenciasVerticeA.add(verticeB);
        //lo ordeno
        Collections.sort(adyacenciasVerticeA);
    }
    @Override
    public void eliminarArista(int verticeA,int verticeB) throws ErrorNoExisteAdyacencia{
        //si no existe adyacencia error,aunque podria ser un return directo
        //por defecto tambien en la existencia de adyacencia ya marca error si hay posiciones invalidas
        if(!super.existeAdyacencia(verticeA,verticeB))
            throw new ErrorNoExisteAdyacencia();
        this.listaDeAdyacencia.get(verticeA).remove((Integer) verticeB);
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
        for(List<Integer> adyacentesDelVertice:super.listaDeAdyacencia)
            if(adyacentesDelVertice.contains((Integer) vertice))
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
//falta probar
    public boolean existeCamino(int verticeA,int verticeB){
        super.validarVertice(verticeA);
        super.validarVertice(verticeB);
        ControlMarcado marcados=new ControlMarcado(this.cantidadDeVertices()) ;
        return !marcados.estanTodosMarcados();
    }
    public void ejecutarDFS(int verticeA,int verticeB,ControlMarcado marcados){
        if(verticeA==verticeB)//si llegan a ser iguales en un punto acaba la recursion
            return;
        marcados.marcarVertice(verticeA);
        Iterable<Integer> adyacentesAlVertice=this.adyacentesDelVertice(verticeA);
        for (Integer elemento: adyacentesAlVertice){
            if(!marcados.estaMarcado(elemento)){
                ejecutarDFS(elemento,verticeB,marcados);
            }
        }
    }

    /**
     * metodo para pasar de digrafo a grafo
     * @return retorna un grafo del digrafo
     * @throws ErrorYaExisteAdyacencia error por si ya existe adyacencias al hacer la insercion
     */
    public Grafo getGrafo() throws ErrorYaExisteAdyacencia {
        Grafo grafo=new Grafo(this.cantidadDeVertices());
        for (int origen=0;origen<grafo.cantidadDeVertices();origen++){
            Iterable<Integer> adyacentesAlVertice=this.adyacentesDelVertice(origen);
            for(Integer destino:adyacentesAlVertice){
                //si no existe adyacencia en el grafo entonces le agrego una arista
                if(!grafo.existeAdyacencia(origen,destino)){
                    grafo.insertarArista(origen,destino);
                }
            }
        }
        return grafo;
    }

    /**
     * metodo para saber si un digrafo es debilmente conexo,pasando a grafo
     * y ejecutando un dfs sobre el
     * si estan todos marcados quiere decir que es debilmente conexo si no no
     * la teoria dice que un digrafo es debilmente conexo si al pasarlo a grafo este es conexo
     * si al pasarlo a grafo este no es conexo,entonces
     * @return true si es debilmente conexo,false si no
     */
    public boolean esDebilmenteConexo(){
        try {
            Grafo grafo=this.getGrafo();
            return grafo.esConexo();
        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }
    }
    public boolean esFuertementeConexo(){
        AlgoritmoWharshall algw=new AlgoritmoWharshall(this);
        return algw.tieneUnoTodaDiagonalPrincipal();
    }

    public boolean esFuertementeConexo2(){
        DFS dfs=new DFS(this);
        for (int i=0;i<this.cantidadDeVertices();i++){
            dfs.ejecutarDFSRecursivo2(i);//ejecuto dfs
            //si no estan todos marcados quiere decir que no hay camino de un vertice A un vertice B
            if(!dfs.getMarcados().estanTodosMarcados()){
                return false;
            }
            //si estan todos marcados que desmarque todos y ejecute dfs sobre el sgte vertice
            dfs.getMarcados().desmarcarTodos();
        }
        return true;
    }

    /**
     * metodo para saber si es fuertemente conexo version recursiva
     * la explicacion es envase a la teoria
     * un digrafo es fuertemente conexo si existe camino para cada par de vertices
     *
     * la logica es hacer dfs para cada vertice
     * si en cada vertice al recorrer en dfs se marcan todos
     * debe avanzar
     * y esto finaliza si al recorrer en dfs no estan marcados todos
     * entonces ahi retorna false
     *
     * tambien si es fuertemente conexo visita todos y basicamente cuando
     * n decrementa a 0,entonces se llego a todos los vertices
     * por lo tanto si se llego a todos los vertices,entonces es fuertemente conexo
     * @return true si es fuertemente conexo,false si no
     */
    public boolean esFuertementeConexo3(){
        DFS dfs=new DFS(this);
        return esFuertementeConexo3(0, dfs,this.cantidadDeVertices());
    }
    private boolean esFuertementeConexo3(int vertice,DFS dfs,int n){
        dfs.ejecutarDFSRecursivo2(vertice);
        if(!dfs.getMarcados().estanTodosMarcados()){
            return false;
        }
        n=n-1;
        if(n==0){//si n llega a 0 quiere decir que recorrio todos los vertice
            //y existe camino para cada vertice
            return true;
        }
        dfs.getMarcados().desmarcarTodos();
        vertice++;
        return esFuertementeConexo3(vertice,dfs,n);
    }

    /**
     * metodo para saber si existe ciclos en los vertices version recursiva
     * @return true si existe ciclos en los vertices,false si no
     */
    public boolean tieneCiclosLosVertices(){
        return tieneCiclosLosVertices(this.cantidadDeVertices());
    }
    private boolean tieneCiclosLosVertices(int n){
        if(n==0){
            return false;
        }
        if(super.existeAdyacencia(n-1,n-1)){
            return true;
        }
        return tieneCiclosLosVertices(n-1);
    }

    /**
     * metodo para saber si existe ciclos en los vertices
     * version iterativa
     * @return true si existe ciclos en los vertices,false si no
     */
    public boolean tieneCiclosLosVerticesI(){
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            if(super.existeAdyacencia(i,i)){
                return true;
            }
        }
        return false;
    }


}
