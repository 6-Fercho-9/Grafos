package GrafosPesados;
import Negocio.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GrafoPesado {
    protected final List<List<AdyacenteConPeso>> listaDeAdyacencia;
    //constructor de copia
    public GrafoPesado(GrafoPesado unGrafo){
        this.listaDeAdyacencia=unGrafo.listaDeAdyacencia;
    }
    public GrafoPesado(){
        listaDeAdyacencia=new ArrayList<>();
    }
    public GrafoPesado(int cantidaDeVertices){
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
        List<AdyacenteConPeso> adyacenciasDelNuevoVertice=new ArrayList<>();
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
     * @throws ErrorYaExisteAdyacencia =lanza un error si ya existe una arista que conecta a esos 2 vertices
     *
     *
     *
    //si no existe adyacencia entonces toca insertar la arista
    //pero que pasa si la posicion inicial es igual a la posicion final es decir
    //queremos insertar una arista al mismo vertice haciendo un ciclo en ese vertice
    //en ese caso no deberia insertarse,es decir solo deberia insertarse una vez
     */
    public void insertarArista(int posicionInicial,int posicionFinal,double peso) throws ErrorYaExisteAdyacencia {
        //hacer validacion al peso
        //este existe adyacencia ya valida las posiciones asi que no hay de que preocuparse
        if(this.existeAdyacencia(posicionInicial,posicionFinal)){
            throw new ErrorYaExisteAdyacencia();
        }
        List<AdyacenteConPeso>adyacenciasDelOrigen=this.listaDeAdyacencia.get(posicionInicial);
        adyacenciasDelOrigen.add(new AdyacenteConPeso(posicionFinal,peso));//lo agrego y lo ordeno
        Collections.sort(adyacenciasDelOrigen);//lo agrego y lo ordeno
        if(posicionInicial!=posicionFinal){
            List<AdyacenteConPeso>adyacenciasDelDestino=this.listaDeAdyacencia.get(posicionFinal);
            adyacenciasDelDestino.add(new AdyacenteConPeso(posicionInicial,peso));
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
        List<AdyacenteConPeso> listaDeListas=this.listaDeAdyacencia.get(posDeVerticeOrigen);
        AdyacenteConPeso adyacenteDestino=new AdyacenteConPeso(posDeVerticeDestino);
        return listaDeListas.contains(adyacenteDestino);
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
        List<AdyacenteConPeso>adyacenciasDelVerticeB=this.listaDeAdyacencia.get(verticeB);
        List<AdyacenteConPeso>adyacenciasDelVerticeA=this.listaDeAdyacencia.get(verticeA);
        /*//indice a eliminar en el vertice A
        int indiceAEliminarA=adyacenciasDelVerticeA.indexOf((Integer)verticeB);
        //indice a eliminar en el vertice B
        int indiceAEliminarB=adyacenciasDelVerticeB.indexOf((Integer)verticeA);*/
        AdyacenteConPeso adyacenteConPesoverticeB=new AdyacenteConPeso(verticeB);
        adyacenciasDelVerticeA.remove(adyacenteConPesoverticeB);
        if(verticeA!=verticeB)//if por si es un vertice con ciclo
            adyacenciasDelVerticeB.remove(new AdyacenteConPeso(verticeA));

    }

    /**
     *  solo obtiene una lista con los adyacentes de un vertice sin su peso
     * @param posDeVertice=por de vertice que me interesa obtner los adyacentes
     * @return retorna una lista de adyacentes a un vertice
     */

    public Iterable<AdyacenteConPeso> adyacentesDelVertice(int posDeVertice){
        validarVertice(posDeVertice);
        List<AdyacenteConPeso> adyacentesDelVertice=this.listaDeAdyacencia.get(posDeVertice);//agarro la lista
        //de las posiciones donde puedo visitar a travez de la posicion de vertice
        List<AdyacenteConPeso> copiaAdyacencias=new ArrayList<>();
        for(AdyacenteConPeso adyacente:adyacentesDelVertice){
            //copiaAdyacencias.add(adyacente);
           copiaAdyacencias.add(adyacente);
        }
        return (Iterable)copiaAdyacencias;
    }
    public double peso(int posDeVerticeOrigen,int posDeVerticeDestino){
        /*if(!this.existeAdyacencia(posDeVerticeOrigen,posDeVerticeDestino)){
            //que lanze una excepcion(error arista no existe )
        }*/
        List<AdyacenteConPeso>adyacentesDelOrigen=this.listaDeAdyacencia.get(posDeVerticeOrigen);
        AdyacenteConPeso adyacenteDestino=new AdyacenteConPeso(posDeVerticeDestino);
        int posicionDelAdyacente= adyacentesDelOrigen.indexOf(adyacenteDestino);
        AdyacenteConPeso adyacenteDestinoConPeso=adyacentesDelOrigen.get(posicionDelAdyacente);
        return adyacenteDestinoConPeso.getPeso();

    }
    /*public Iterator<Integer> adyacentesDelVertice2(int posDeVertice){
        this.validarVertice(posDeVertice);
        return this.listaDeAdyacencia.get(posDeVertice).iterator();
    }*/

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
            AdyacenteConPeso elementoConVerticeAEliminar=new AdyacenteConPeso(numeroVerticeAEliminar);
            for (List<AdyacenteConPeso> listaDeAdyacencias:this.listaDeAdyacencia){
                //busco en la lista de adyacencias el vertice a eliminar
                int posicionVerticeAEliminar=listaDeAdyacencias.indexOf(new AdyacenteConPeso(numeroVerticeAEliminar));
                if(posicionVerticeAEliminar>=0)//si la posicion es valida entonces entra aca y lo elmina
                    listaDeAdyacencias.remove(posicionVerticeAEliminar);

                //para cada elemento de esa lista de adyacencias
                //si el elemento es mayor al vertice a eliminar entonces se decrementa en 1
                for (int i = 0; i < listaDeAdyacencias.size(); i++) {
                    AdyacenteConPeso elemento=listaDeAdyacencias.get(i);
                    if(elemento.compareTo(elementoConVerticeAEliminar)>0) {
                        elemento.setIndiceVertice(elemento.getIndiceVertice()-1);
                        listaDeAdyacencias.set(i, elemento);
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
        List<AdyacenteConPeso>adyacenciasDelVerticeB=this.listaDeAdyacencia.get(verticeB);
        List<AdyacenteConPeso>adyacenciasDelVerticeA=this.listaDeAdyacencia.get(verticeA);
        adyacenciasDelVerticeA.remove(new AdyacenteConPeso(verticeB));
        if(verticeA!=verticeB)//if por si es un vertice con ciclo
            adyacenciasDelVerticeB.remove(new AdyacenteConPeso(verticeA));

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
    /*public boolean existeCamino(int verticeA,int verticeB){
        this.validarVertice(verticeA);//valido los vertice
        this.validarVertice(verticeB);
        ControlMarcado marcados=new ControlMarcado(this.cantidadDeVertices());//instancio un control de marcados
        //metodo recursivo para retornar si existe camino
        return existeCamino(verticeA,verticeB,marcados);
    }*/

    /**
     * metodo para indicar si existe camino entre un vertice A y un vertice B
     *
     * @param verticeA=verticeA
     * @param verticeB=verticeB
     * @param marcados=lista de marcados
     * @return retorna true si existe camino en un vertice Ay un vertice B
     * caso contrario retorna false
     */
    /*private boolean existeCamino(int verticeA,int verticeB,ControlMarcado marcados){

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
    }*/

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
        GrafoPesado nuevoGrafo=new GrafoPesado(this.cantidadDeVertices());//creo un nuveo grafo
        try {
            return dfs.ejecutarDFSModificado(vertice,nuevoGrafo);
        } catch (ErrorYaExisteAdyacencia e) {
            throw new RuntimeException(e);
        }
    }
    //metodo para pasar de grafoPesado a no pesado
    public Grafo getGrafoNoPesado(){
        Grafo grafoNoPesado=new Grafo(this.cantidadDeVertices());
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            for (AdyacenteConPeso elementoConPeso:this.listaDeAdyacencia.get(i)){
                try {
                    if(!grafoNoPesado.existeAdyacencia(i,elementoConPeso.getIndiceVertice())){
                        grafoNoPesado.insertarArista(i,elementoConPeso.getIndiceVertice());
                    }
                } catch (ErrorYaExisteAdyacencia e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return grafoNoPesado;
    }

    /**
     * metodo para saber dado un vertice,de sus adyacentes de ese vertice el que tenga menor peso
     *
     * @param n dimension de la lista
     * @param adyacentesAlVertice la lista de adyacentes
     * @return retorna el vertice con menor peso
     */
    public int adyacenteConPesoMenor(int n,List<AdyacenteConPeso> adyacentesAlVertice){
        if(n==1){
            return adyacentesAlVertice.get(n-1).getIndiceVertice();
        }
        int hipotesisMenor=adyacenteConPesoMenor(n-1,adyacentesAlVertice);
        int menor=adyacentesAlVertice.get(n-1).getIndiceVertice();
        if(menor<hipotesisMenor){
            hipotesisMenor=menor;
        }
        return hipotesisMenor;
    }

    private int adyacenteConPesoMenor(int vertice){
        this.validarVertice(vertice);
        return this.adyacenteConPesoMenor(this.listaDeAdyacencia.get(vertice).size(),this.listaDeAdyacencia.get(vertice));
    }
    
}
