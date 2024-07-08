package Negocio;

import java.util.ArrayList;

import java.util.*;
/**
 * por defecto la clase grafo empieza desde el vertice 0 en adelante
 */
public class Grafo {
    protected final List<List<Integer>> listaDeAdyacencia;
    //constructor de copia
    public Grafo(Grafo unGrafo){
        this.listaDeAdyacencia=unGrafo.listaDeAdyacencia;
    }
    public Grafo(){
        listaDeAdyacencia=new ArrayList<>();
    }
    public Grafo(int cantidaDeVertices){
        if(cantidaDeVertices<=0){
            throw new IllegalArgumentException("Error..Cantidad de vertices Invalido..");
        }
        this.listaDeAdyacencia=new ArrayList<>();//instancio
        for (int i = 0; i <cantidaDeVertices ;i++) {
            this.insertarVertice();
        }
    }
    //este metodo lo que hace es,simplemente insertar a la lista de listas
    //una lista de enteros

    public void insertarVertice(){
        List<Integer> adyacenciasDelNuevoVertice=new ArrayList<>();
        this.listaDeAdyacencia.add(adyacenciasDelNuevoVertice);
    }

    /**
     *
     * la logica en esto esta en que si digo grafo.insertarArista(2,3)
     * en la vida real
     *          (2)---(3)
     *insertar grafo.insertarArista(2,2),
     *           ----
     *          |   |
     *          |-(2)---(3)
     *
     *          eso simula un ciclo en el vertice 2
     *
     *  esto representado a nivel programacion seria(asumiendo que new grafo(4) un grafo de 4 vertices)
     *  lista
     *      0
     *      1
     *      2--[2,3]
     *      3--[2]
     *      (estos 2 son islas)
     *      los adyacentes del 0 son ninguno
     *      los adyacentes del 1 son ninguno

     *      los adyacentes del 2 son el propio 2 y el 3
     *      los adyacentes del 3 son el 2
     *
     *      acuerdese que si hay una coneccion de un vertice A a un vertice B
     *      en un grafo no dirigido
     *      entonces la conexion es paralela?
     *      si hago insertarArista(1,3)
     *      en la lista en la posicion 1 se agrega el elemento 3
     *      y en la lista en la posicion 3 se agrega el elemento 1
     *      por eso digo que es paralela
     *
     * Cosas a tener en cuenta en que cada que se inserta un elemento ,esa sublista debe estar ordenada
     * por eso se usa el colecction.sort
     * lo otro a tener en cuenta es que siempre se agrega el elemento a la lista por eso
     * si inserto un (1,1)
     * lo insertara una vez el uno si no hubiera el if(posInicio!=posFinal)
     * entonces habrian 2 unos en una lista lo cual generaria inconsistencia
     *
     * entonces si queremos insertar una lista de m a n donde m=n
     * entonces no entra al if
     * pero si m!=n entra
     *
     *
     * @param posicionInicial=posicion inicial del vertice o llamalo posicion vertice A
     * @param posicionFinal=posicion Final o destino del vertice o llamalo posicion del vertice B
     * @throws ErrorYaExisteAdyacencia=lanza un error si ya existe una arista que conecta a esos 2 vertices
     *
     *
     *
       //si no existe adyacencia entonces toca insertar la arista
        //pero que pasa si la posicion inicial es igual a la posicion final es decir
        //queremos insertar una arista al mismo vertice haciendo un ciclo en ese vertice
        //en ese caso no deberia insertarse,es decir solo deberia insertarse una vez
     */
    public void insertarArista(int posicionInicial,int posicionFinal) throws ErrorYaExisteAdyacencia {
        //este existe adyacencia ya valida las posiciones asi que no hay de que preocuparse
       if(this.existeAdyacencia(posicionInicial,posicionFinal)){
           throw new ErrorYaExisteAdyacencia();
       }
        List<Integer>adyacenciasDelOrigen=this.listaDeAdyacencia.get(posicionInicial);
        adyacenciasDelOrigen.add(posicionFinal);//lo agrego y lo ordeno
        Collections.sort(adyacenciasDelOrigen);//lo agrego y lo ordeno
       if(posicionInicial!=posicionFinal){
           List<Integer>adyacenciasDelDestino=this.listaDeAdyacencia.get(posicionFinal);
           adyacenciasDelDestino.add(posicionInicial);
           Collections.sort(adyacenciasDelDestino);
       }


    }
    //este metodo es un procedimiento que si queremos acceder a un vertice que no se puede
    //nos lanza un error en tiempo de ejecucion
    //si todo esta bien simplemente sigue
    public void validarVertice(int nroVertice){
        if(nroVertice<0||nroVertice>=this.listaDeAdyacencia.size()){
            throw new IllegalArgumentException("Error..Posicion No valida..");
        }
    }

    /**
     *
     * @param posDeVerticeOrigen=posicion del Vertice A
     * @param posDeVerticeDestino=posicion del vertice B
     * @return retorna true si existe adyacencia
     * es decir si el vertice B esta conectado con el vertice A
     * no es paralelo es en un solo sentido
     * retorna false si no
     */
    public boolean existeAdyacencia(int posDeVerticeOrigen,int posDeVerticeDestino){
        //valido las posiciones de origen y destino
        this.validarVertice(posDeVerticeOrigen);
        this.validarVertice(posDeVerticeDestino);
        //si son posiciones validas entonces
        //primero me posiciono en la lista de listas
        List<Integer> listaDeListas=this.listaDeAdyacencia.get(posDeVerticeOrigen);
        return listaDeListas.contains(posDeVerticeDestino);
    }

    /**
     * Metodo para eliminar arista entre 2 vertices
     * @param verticeA posicion del vertice A
     * @param verticeB posicion del vertice B
     * @throws ErrorNoExisteAdyacencia =error por si no existe adyacencias

     * primero valido los vertices si son correcto
     * supongase
     *  (1)---(5)
     *  a nivel programacion lalista va de 0 a 5
     *  en nivel posicion va de 0..5
     *  ahora si digamos le digo elimina arista de 1 a 4,son valores validos,pero
     *  no existe adyacencias en estos,entonces deberia soltar algun error
     *
     *  pero en el caso de que si exista lo elimina
     *  al ser grafo no dirigido
     *  si hay que eliminar arista de (1,5)
     *  en la lista
     *  0--ninguna adyacencia
     *  1--5
     *  2--ninguna adyacencia
     *  3--ninguna adyacencia
     *  4--ninguna adyacencia
     *  5--1
     *  entonces debo remover las adyacencia del 1 que es el 5
     *  y la adyacencia del 5 que es el 1
     *  luego el if
     *  if(posicionInicio!=posicionFinal)//if por si es un vertice con ciclo
     *  es por si queremos eliminar vertice con m=n
     *  para que solo elimine una vez y nada mas
     *
     */
    public void eliminarArista(int verticeA,int verticeB)throws ErrorNoExisteAdyacencia{
        this.validarVertice(verticeA);
        this.validarVertice(verticeB);
        //por si las posiciones estan correctas,pero si no hay coneccion entre aristas
        //entonces si no existe adyacencias que mande un error
        if(!this.existeAdyacencia(verticeA,verticeB))
            throw new ErrorNoExisteAdyacencia();
        List<Integer>adyacenciasDelVerticeB=this.listaDeAdyacencia.get(verticeB);
        List<Integer>adyacenciasDelVerticeA=this.listaDeAdyacencia.get(verticeA);
        /*//indice a eliminar en el vertice A
        int indiceAEliminarA=adyacenciasDelVerticeA.indexOf((Integer)verticeB);
        //indice a eliminar en el vertice B
        int indiceAEliminarB=adyacenciasDelVerticeB.indexOf((Integer)verticeA);*/
        adyacenciasDelVerticeA.remove((Integer) verticeB);
        if(verticeA!=verticeB)//if por si es un vertice con ciclo
            adyacenciasDelVerticeB.remove((Integer) verticeA);

    }

    /**
     *
     * metodo para obtener los adyacentes en esta posicion
     * @param posDeVertice=posicion para obtener los adyacentes a esta posicion
     * @return nos retorna un iterable,pero basicamente nos retorna una lista con los
     * adyacentes a un vertice
     *
     */
    public Iterable<Integer> adyacentesDelVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<Integer> adyacentesDelVertice=this.listaDeAdyacencia.get(posDeVertice);//agarro la lista
        //de las posiciones donde puedo visitar a travez de la posicion de vertice
        List<Integer> copiaAdyacencias=new ArrayList<>();
        for(Integer adyacente:adyacentesDelVertice){
            copiaAdyacencias.add(adyacente);
        }
        return (Iterable) copiaAdyacencias;
    }
    public Iterator<Integer> adyacentesDelVertice2(int posDeVertice){
        this.validarVertice(posDeVertice);
        return this.listaDeAdyacencia.get(posDeVertice).iterator();
    }

    /**
     * metodo para eliminar un vertice del grafo
     * @param numeroVerticeAEliminar=posicion del vertice a eliminar
     */
    public void eliminarVertice(int numeroVerticeAEliminar){
        this.validarVertice(numeroVerticeAEliminar);
        this.listaDeAdyacencia.remove(numeroVerticeAEliminar);//paso 1-elimino el vertice
        //paso 2, de todos los demas lugares se debe eliminar el vertice a eliminar
        if(!listaDeAdyacencia.isEmpty()){
            //con este for navego en toda la lista de vertices
            for (List<Integer> listaDeAdyacencias:this.listaDeAdyacencia){
                //busco en la lista de adyacencias el vertice a eliminar
                int posicionVerticeAEliminar=listaDeAdyacencias.indexOf(numeroVerticeAEliminar);
                if(posicionVerticeAEliminar>=0)//si la posicion es valida entonces entra aca y lo elmina
                    listaDeAdyacencias.remove(posicionVerticeAEliminar);

                //para cada elemento de esa lista de adyacencias
                //si el elemento es mayor al vertice a eliminar entonces se decrementa en 1
                for (int i = 0; i < listaDeAdyacencias.size(); i++) {
                    int elemento=listaDeAdyacencias.get(i);
                    if(listaDeAdyacencias.get(i)>numeroVerticeAEliminar)
                        listaDeAdyacencias.set(i,elemento-1);

                }
            }
        }
    }

    /**
     *
     * @param posicionVerticeAEliminar=posicion del vertice a eliminar
     */
    //falta mejorar no funciona reutilizando el eliminar arista
    public void eliminarVertice2(int posicionVerticeAEliminar){
        validarVertice(posicionVerticeAEliminar);
        this.listaDeAdyacencia.remove(posicionVerticeAEliminar);//paso 1,elimino el vertice
        if(!this.listaDeAdyacencia.isEmpty()){
            for (int i = 0; i < this.listaDeAdyacencia.size(); i++) {
                //paso 2
                //elimino las aristas que conectan con el vertice a eliminar
                this.eliminarArista2(i,posicionVerticeAEliminar);
            }
            //para navegar en la adyacencia
            for (List<Integer> adyacentesAlVertice:this.listaDeAdyacencia) {
                //paso 3
                //si existen elementos en la lista de adyacencias mayores a la posicion a eliminar
                //entonces se decrementta su elemento
                for (int j = 0; j < adyacentesAlVertice.size(); j++) {
                    int elemento=adyacentesAlVertice.get(j);
                    if(elemento>posicionVerticeAEliminar){
                        adyacentesAlVertice.set(j,elemento-1);
                    }
                }
            }
        }

    }

    /**
     *
     * @param verticeA=vertice A
     * @param verticeB=vertice B
        //metodo de eliminar Arista2 a diferencia del primero este no retorna una excepcion
        //simplemente si no hay adyacencia entre vertices entonces no hace nada solamente acaba
        //el ciclo
     *
     */
    public void eliminarArista2(int verticeA,int verticeB){
        this.validarVertice(verticeA);
        this.validarVertice(verticeB);
        //por si las posiciones estan correctas,pero si no hay coneccion entre aristas
        //entonces si no existe adyacencias que mande un error
        if(!this.existeAdyacencia(verticeA,verticeB))
            return ;
        List<Integer>adyacenciasDelVerticeB=this.listaDeAdyacencia.get(verticeB);
        List<Integer>adyacenciasDelVerticeA=this.listaDeAdyacencia.get(verticeA);
        adyacenciasDelVerticeA.remove((Integer)verticeB);
        if(verticeA!=verticeB)//if por si es un vertice con ciclo
            adyacenciasDelVerticeB.remove((Integer) verticeA);

    }

    /**
     *
     * @return retorna la cantidad de vertices que existe en el grafo
     */
    public int cantidadDeVertices(){
        return this.listaDeAdyacencia.size();
    }

    /**
     * @param posicionDelVertice=posicion del vertice que queremos obtener la cantidad de aristas
     * @return retorna la cantida de aristas dado un vertice
     */
    public int cantidadDeAristas(int posicionDelVertice){
        this.validarVertice(posicionDelVertice);
        return this.listaDeAdyacencia.get(posicionDelVertice).size();
    }

    @Override
    public String toString(){
        String s="";
        for (int i = 0; i < this.listaDeAdyacencia.size(); i++) {
            s+=i+": "+this.listaDeAdyacencia.get(i).toString()+"\n";
        }
        return s;
    }

    /**
     *
     * @param verticeA=vertice A
     * @param verticeB=vertice B
     *
     * @return retorna true si existe camino entre un vertice A y un vertice B
     * y retorna false si no existe camino entre un vertice A y un vertice B
     */
    public boolean existeCamino(int verticeA,int verticeB){
        this.validarVertice(verticeA);//valido los vertice
        this.validarVertice(verticeB);
        ControlMarcado marcados=new ControlMarcado(this.cantidadDeVertices());//instancio un control de marcados
        //metodo recursivo para retornar si existe camino
        return existeCamino(verticeA,verticeB,marcados);
    }

    /**
     * metodo para indicar si existe camino entre un vertice A y un vertice B
     *
     * @param verticeA=verticeA
     * @param verticeB=verticeB
     * @param marcados=lista de marcados
     * @return retorna true si existe camino en un vertice Ay un vertice B
     * caso contrario retorna false
     */
    private boolean existeCamino(int verticeA,int verticeB,ControlMarcado marcados){

        marcados.marcarVertice(verticeA);//lo marco
        Iterable<Integer> adyacentesAlVertice=this.adyacentesDelVertice(verticeA);//saco los adyacentes al vertice
        for(Integer elemento:adyacentesAlVertice){
            if(!marcados.estaMarcado(elemento)){//y si su primer elemento de la lista de adyacencias
                if(elemento==verticeB){//si no esta marcado capaz y sea ese elemento el que estoy buscando
                    return true;
                }
                //si es ese elemento y no esta marcado entonces itero en DFS sobre el
                return existeCamino(elemento,verticeB,marcados);
            }
        }
        return false;
    }

    /**
     * metodo que nos dice si existe ciclos en ungrafo dado un vertice
     * pero por defecto ese vertice empieza en 0
     * si se le da otro valor puede que no de
     * @param vertice=vertice por donde empezar a iterar
     * @return retorna true si existe ciclos y false si no
     *
     * quizas se podria solucionar el problema si pongo el verificar si estan todos marcados
     * en el inicio si estan todos marcados entonces,retorna false
     */
    public boolean existeCiclos(int vertice){
        this.validarVertice(vertice);
        DFS dfs=new DFS(this);
        Grafo nuevoGrafo=new Grafo(this.cantidadDeVertices());//creo un nuveo grafo
        try {
            return dfs.ejecutarDFSModificado(vertice,nuevoGrafo);
        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * un grafo es conexo si existe camino para cada par de vertices
     * entonces,la idea es si recorro en dfs,empezando por 0 en defecto,entonces
     * si recorro todo el grafo(si todos estan marcados) quiere decir que es conexo
     * si no quiere decir que tiene islas y por lo tanto no es grafo conexo
     *
     * recalcar tambien que sirve para cualquier vertice
     * por defecto le mandamos 0,pero sirve para cualquier vertice
     *
     * @return retorna true si el grafo es conexo,si no false
     */
    public boolean esConexo(){
        DFS dfs=new DFS(this);
        dfs.ejecutarDFSRecursivo2(0);
        return dfs.getMarcados().estanTodosMarcados();
    }

    /**
     * metodo para saber la cantidad de isalas que existe en un grafo
     *
     * no recibe parametros,pero tambien sirve para cualquier vertice
     * por defecto le mando que empieze por 0
     *
     * @return retorna la cantidad de islas que existe en un grafo
     */
    public int cantidadIslas(){
        this.validarVertice(0);
        return cantidadIslas(0,new DFS(this));
    }

    /**
     * metodo amigo que hace el trabajo de la recursion
     * @param vertice=vertice por donde iniciar
     * @param unDFS=un dfs que ya tiene instanciado un control marcado y un grafo
     * @return la cantidad de islas que existe en el grafo
     * la logica es la siguiente
     * primero ejecuta en dfs sobre el grafo
     * si estan todos marcados retorna 1
     * por solo existe una isla
     * si no quiere decir que existen mas islas
     * si existen mas islas,debo buscar un nuevo vertice que no haya sido visitado
     * para ejecutar un dfs sobre el
     * y asi sucesivamente
     */
    private int cantidadIslas(int vertice,DFS unDFS){
        unDFS.ejecutarDFSRecursivo2(vertice);
        int nuevoVertice=unDFS.getMarcados().buscarPosicionNoMarcada();
        if(unDFS.getMarcados().estanTodosMarcados())
            return 1;
        int cantidadIslas=this.cantidadIslas(nuevoVertice,unDFS);
        cantidadIslas++;
        return cantidadIslas;
    }

}
