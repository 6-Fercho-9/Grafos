package Negocio;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Stack;

import GrafosPesados.AdyacenteConPeso;
import GrafosPesados.GrafoPesado;
import GrafosPesados.Tupla;

import javax.swing.text.AbstractDocument;

public class DFS {
    private List<Integer> recorrido;
    private ControlMarcado marcados;
    private Grafo elgrafo;
    private GrafoPesado elGrafoPesado;


    public DFS(GrafoPesado unGrafoPesado){
        this.elGrafoPesado=unGrafoPesado;
        this.marcados=new ControlMarcado(this.elGrafoPesado.cantidadDeVertices());
    }
    public DFS(GrafoPesado unGrafoPesado,int vertice){
        unGrafoPesado.validarVertice(vertice);
        this.recorrido=new ArrayList<>();
        this.elGrafoPesado=unGrafoPesado;
        this.marcados=new ControlMarcado(unGrafoPesado.cantidadDeVertices());
        //this.ejecutarDFSRecursivoPesado(vertice);
    }
    public DFS(Grafo unGrafo){
        //unGrafo.validarVertice(0);
        this.elgrafo=unGrafo;
        this.marcados=new ControlMarcado(this.elgrafo.cantidadDeVertices());
    }
    public DFS(Grafo unGrafo,int vertice){
        //primero lo validamos el vertice
        unGrafo.validarVertice(vertice);
        this.recorrido=new ArrayList<>();
        this.elgrafo=unGrafo;
        this.marcados=new ControlMarcado(unGrafo.cantidadDeVertices());
        this.ejecutarDFSRecursivo(vertice);
    }

    public void setRecorrido(List<Integer> lista){
        this.recorrido=lista;
    }
    public void setGrafo(Grafo grafo){
        this.elgrafo=grafo;
    }
    public void setMarcados(ControlMarcado marcados){
        this.marcados=marcados;
    }
    public ControlMarcado getMarcados(){
        return this.marcados;
    }
    /**
     * este metodo realiza el recorrido DFS
     * pero la diferencia es que este usa la pila y para cargar los adyacentes
     * en este caso lo carga desde el final hasta el inicio
     * digamos cuando este en un verticeActual 3 sus adyacentes sean 4,9,2
     * al cargar a la pila
     * seria
     * 2<-cima
     * 9
     * 4
     *pila
     *
     * @param vertice=vertice
     */
    public void ejecutarDFS(int vertice){
        Stack<Integer> pila=new Stack<>();
        pila.push(vertice);//lo meto a la pila
        this.marcados.marcarVertice(vertice);//lo marco en el vector de vertices
        do{
            int verticeActual=pila.pop();//saco de la pila
            this.recorrido.add(verticeActual);//lo agrego a la lista de recorridos(lo visito)
            //obtengo los adyacentes al verticeactual
            Iterable<Integer> adyacentesAlVertice=this.elgrafo.adyacentesDelVertice(verticeActual);
            //de la lista de adyacentes los elementos que no estan marcados
            //lo meto a la pila y los marco
            for(Integer elemento:adyacentesAlVertice){
                if(!this.marcados.estaMarcado(elemento)) {
                    pila.push(elemento);
                    this.marcados.marcarVertice(elemento);
                }
            }
        }while(!pila.isEmpty());
    }

    /**
     * metodo para ejecutar un DFS,pero cuando toque agarrar a una lista de adyacencias
     * entonces,carga desde el inicio hasta el final
     * si digamos los adyacentes al vertice 3 son 4,5,2
     * en la pila deberia quedar
     * 4
     * 5
     * 2
     * pila
     * @param vertice
     */
    public void ejecutarDFS2(int vertice){
        Stack<Integer>pila=new Stack<>();
        pila.push(vertice);//meto a la pila el vertice por donde empezare a recorrer
        this.marcados.marcarVertice(vertice);//lo marco
        do{
            int verticeActual=pila.pop();
            this.recorrido.add(verticeActual);//agregar a la lista de recorridos el vertice Actual
            List<Integer> adyacentesAlVertice= (List<Integer>) this.elgrafo.adyacentesDelVertice(verticeActual);
            this.cargarPilaDesdeElFinal(pila,marcados,adyacentesAlVertice);
        }while(!pila.isEmpty());

    }
    private void cargarPilaDesdeElFinal(Stack<Integer>pila,ControlMarcado marcados,List<Integer>adyacentes){
        cargarPilaDesdeElFinal(pila,marcados,adyacentes,adyacentes.size());
    }
    //metodo para caragar la pila desde el final
    //si la lista tiene 3,4,9
    //la pila queda

    /**
     * 3
     * 4
     * 9
     * pila
     * @param pila=representa a la pila de adyacencias
     * @param marcados=representa al vector de marcados
     * @param adyacentes =represneta a la lista de adyacentes de un vertice
     * @param n=representa al tamaño de la lista de adyacentes
     *
     *         con un iterable no se puede obtener el size
     */
    private void cargarPilaDesdeElFinal(Stack<Integer>pila,ControlMarcado marcados,List<Integer>adyacentes,int n){
        if(n>0){
            int elemento=adyacentes.get(n-1);//agarro el ultimo elemento de la lista de adyacentes
            if(!marcados.estaMarcado(elemento)){//si no esta marcado lo agrego a la pila y lo marco ese elemento
                pila.push(elemento);
                marcados.marcarVertice(elemento);
            }
            cargarPilaDesdeElFinal(pila,marcados,adyacentes,n-1);
        }
    }

    /**
     *
     * este ejecutarBFS recursivo
     * es similar al agarrar los adyacentes desde el final al inicio
     *
     * por que en una pila
     * si la lista es [3,5,7]
     * cargando desde el final al inicio quedaria
     * 3<-cima
     * 5
     * 7
     * y si suponiendo esa lista fueran los adyacentes al 9
     * la cima esta en 3
     * entonces el siguiente elemento en la siguiente iteracion es el 3
     *
     * con el recursivo si el vertice actual es 9
     * y sus adyacentes son [3,5,7]
     * no requiero cargar a una pila
     * pero si requiero saber cual sera el proximo adyacente
     * en este caso tomar el primer elemento ya serviria
     * seria el 3
     * al volverse a llamar ahora el vertice actual es 3
     * y asi..
     *
     * @param vertice=vertice por donde comenzamos a hacer el recorrido
     */
    public void ejecutarDFSRecursivo(int vertice){
        this.recorrido.add(vertice);//lo visito o agrego a la lista de recorrido
        this.marcados.marcarVertice(vertice);//lo marco
        Iterable<Integer> adyacentesDelVertice=this.elgrafo.adyacentesDelVertice(vertice);
        //saco los adyacentes al vertice
        //para cada elemento le hago un bfs
        for (Integer elemento:adyacentesDelVertice){
            //si ese elemento no esta marcado//ejecuto el BFS
            if(!this.marcados.estaMarcado(elemento))
                ejecutarDFSRecursivo(elemento);
        }
        //
    }
    //para saber si llegue a un vertice o algo asi,la respuesta a esto nos la puede dar la lista de marcados
    public boolean llegueATodos(){
        return this.marcados.estanTodosMarcados();
    }
    //metodo para saber si llegue a un determinado vertice
    public boolean llegueAVertice(int posicion){
        this.elgrafo.validarVertice(posicion);
        return this.marcados.estaMarcado(posicion);
    }
    public Iterable<Integer> getRecorrido(){
        return this.recorrido;
    }
    //metodo para obtener el grafo
    public Grafo getGrafo(){
        return this.elgrafo;
    }

    /**
     *
     * @param vertice=vertice por donde iniciar el recorrido
     * @param nuevoGrafo=un grafo sin aristas
     * @return true si existe ciclo,false si no
     * @throws ErrorYaExisteAdyacencia=error retorna si existe adyacencia al insertar aristas en el nuevografo
     */
    public boolean ejecutarDFSModificado(int vertice,Grafo nuevoGrafo)throws ErrorYaExisteAdyacencia{
        List<Boolean> listaBoolean=new ArrayList<>();
        return ejecutarDFSModificado(vertice,nuevoGrafo,listaBoolean);
    }
    /**
     * este metodo es para saber si existe ciclos en un grafo
     * si existe ciclos la lista de boolean tendra valores mayor a 0
     * sin embargo dado que esta hecho como un void
     * si existe ciclo
     * en un grafo supongase en una isla que recorra en dfs
     * la lista tendra 2 de dimension
     * es decir tendra 2 elementos true
     * esto es por la recursion en si
     *
     * en conclusion si encuentra un ciclo en la lista tendra el doble
     * @param vertice=vertice por donde comenzar
     * @param nuevoGrafo=un grafo nuevo
     * @param listaBoolean
     * @return= retorna true si existe ciclo,si no false
     * @throws ErrorYaExisteAdyacencia
     */

    private boolean ejecutarDFSModificado(int vertice,Grafo nuevoGrafo,List<Boolean> listaBoolean) throws ErrorYaExisteAdyacencia{
         ejecutarDFSModificadoR(vertice,nuevoGrafo,listaBoolean);
         boolean pilloCiclo=listaBoolean.size()>0;
         if(pilloCiclo)
             return true;
        //caso base si estan todos marcados quiere decir que no hay ciclo
        //y para de llamarse a si mismo
        if(this.marcados.estanTodosMarcados())
            return false;
        //en el caso de que no haya pillado ciclo,quizas falta revisar otra isla,entonces
        //busco una posicion no marcada y me llamo a mi mismo
        int elemento=this.marcados.buscarPosicionNoMarcada();
        return this.ejecutarDFSModificado(elemento,nuevoGrafo,listaBoolean);
    }

    /**
     *
     * este metodo lo que hace es recorrer todo un grafo en dfs
     * pero si encuentra un ciclo para la recursion,en consecuencia
     * en la lista tendra el doble por la recursion
     * @param vertice vertice por donde empezar
     * @param nuevoGrafo copia del grafo para insertarle aristas
     * @param listaBoolean lista de boolean para controlar si existe ciclo
     * @throws ErrorYaExisteAdyacencia error por si ya existe la adyacencia
     */
    private void ejecutarDFSModificadoR(int vertice,Grafo nuevoGrafo,List<Boolean> listaBoolean) throws ErrorYaExisteAdyacencia {
        this.marcados.marcarVertice(vertice);
        Iterable<Integer>adyacentesAlVertice=this.elgrafo.adyacentesDelVertice(vertice);
        for(Integer elemento:adyacentesAlVertice){
            if(!this.marcados.estaMarcado(elemento)){
                //inserto una arista entre el elementoAdyacente y el vertice,del nuevo grafo
                nuevoGrafo.insertarArista(elemento,vertice);
                this.ejecutarDFSModificadoR(elemento,nuevoGrafo,listaBoolean);
            }else{//si esta marcado
                if(!nuevoGrafo.existeAdyacencia(elemento,vertice)){
                    listaBoolean.add(Boolean.TRUE);
                    return;
                }
            }
        }
    }

    /**
     *
     * metodo para ver si en la lista de adyacencias esta marcada
     * si esta marcada quiere decir que existe todavia vertices no visitados
     * si sale falso quiere decir que ya recorrio todo
     * @param adyacencias
     * @return
     */

    private boolean existeAlgunMarcadoEnLasAdyacencias(Iterable<Integer> adyacencias){
        for(Integer elemento: adyacencias){
            if(this.marcados.estaMarcado(elemento)){
                return true;
            }
        }
        return false;
    }
    public void ejecutarDFSRecursivo2(int vertice){
        this.marcados.marcarVertice(vertice);//lo marco
        Iterable<Integer> adyacentesDelVertice=this.elgrafo.adyacentesDelVertice(vertice);
        //saco los adyacentes al vertice
        //para cada elemento le hago un bfs
        for (Integer elemento:adyacentesDelVertice){
            //si ese elemento no esta marcado//ejecuto el BFS
            if(!this.marcados.estaMarcado(elemento))
                ejecutarDFSRecursivo2(elemento);
        }
        //
    }


    /**
     * ejecutarDFS para grafoPesado
     * @param vertice
     */
    public void ejecutarDFSRecursivoPesadoParaTupla(int vertice,List<Tupla> listaTuplas){
        //this.recorrido.add(vertice);//lo visito o agrego a la lista de recorrido
        this.marcados.marcarVertice(vertice);//lo marco
        //saco sus adyacentes
        Iterable<AdyacenteConPeso> adyacentesDelVertice=this.elGrafoPesado.adyacentesDelVertice(vertice);

        for(AdyacenteConPeso elemento:adyacentesDelVertice){
            if(!this.marcados.estaMarcado(elemento.getIndiceVertice())){
                listaTuplas.add(new Tupla(vertice, elemento.getIndiceVertice(), this.elGrafoPesado.peso(vertice, elemento.getIndiceVertice())));
            }
        }
        //saco los adyacentes al vertice
        //para cada elemento le hago un bfs
        for (AdyacenteConPeso elemento:adyacentesDelVertice){
            //si ese elemento no esta marcado//ejecuto el BFS
            if(!this.marcados.estaMarcado(elemento.getIndiceVertice()))
                ejecutarDFSRecursivoPesadoParaTupla(elemento.getIndiceVertice(),listaTuplas);
        }
        //
    }

    /**
     * metodos para grafos pesados
     * @param vertice
     * @param nuevoGrafo
     * @return
     * @throws ErrorYaExisteAdyacencia
     */
    public boolean ejecutarDFSModificado(int vertice,GrafoPesado nuevoGrafo)throws ErrorYaExisteAdyacencia{
        List<Boolean> listaBoolean=new ArrayList<>();
        return ejecutarDFSModificado(vertice,nuevoGrafo,listaBoolean);
    }

    private boolean ejecutarDFSModificado(int vertice,GrafoPesado nuevoGrafo,List<Boolean> listaBoolean) throws ErrorYaExisteAdyacencia{
        ejecutarDFSModificadoR(vertice,nuevoGrafo,listaBoolean);
        boolean pilloCiclo=listaBoolean.size()>0;
        if(pilloCiclo)
            return true;
        //caso base si estan todos marcados quiere decir que no hay ciclo
        //y para de llamarse a si mismo
        if(this.marcados.estanTodosMarcados())
            return false;
        //en el caso de que no haya pillado ciclo,quizas falta revisar otra isla,entonces
        //busco una posicion no marcada y me llamo a mi mismo
        int elemento=this.marcados.buscarPosicionNoMarcada();
        return this.ejecutarDFSModificado(elemento,nuevoGrafo,listaBoolean);
    }

    private void ejecutarDFSModificadoR(int vertice,GrafoPesado nuevoGrafo,List<Boolean> listaBoolean) throws ErrorYaExisteAdyacencia {
        this.marcados.marcarVertice(vertice);
        Iterable<AdyacenteConPeso>adyacentesAlVertice=this.elGrafoPesado.adyacentesDelVertice(vertice);
        for(AdyacenteConPeso elemento:adyacentesAlVertice){
            if(!this.marcados.estaMarcado(elemento.getIndiceVertice())){
                //inserto una arista entre el elementoAdyacente y el vertice,del nuevo grafo
                nuevoGrafo.insertarArista(elemento.getIndiceVertice(),vertice,this.elGrafoPesado.peso(elemento.getIndiceVertice(),vertice));
                this.ejecutarDFSModificadoR(elemento.getIndiceVertice(),nuevoGrafo,listaBoolean);
            }else{//si esta marcado
                if(!nuevoGrafo.existeAdyacencia(elemento.getIndiceVertice(),vertice)){
                    listaBoolean.add(Boolean.TRUE);
                    return;
                }
            }
        }
    }


}
