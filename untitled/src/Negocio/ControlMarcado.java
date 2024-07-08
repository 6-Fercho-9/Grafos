package Negocio;
import java.util.ArrayList;
import java.util.List;

/**
 * clase para tener un arreglo
 * o vector de marcados
 */
public class ControlMarcado {
    private final List<Boolean> marcados;

    /**
     *
     * @param nroDeVertices=numero de vertices que tendra la lista de marcados
     */
    public ControlMarcado(int nroDeVertices){
        this.marcados=new ArrayList<>();
        for (int i = 0; i < nroDeVertices; i++)
            this.marcados.add(Boolean.FALSE);
    }
    //metodo para marcar con verdadero a una posicion de la lista de marcados
    public void marcarVertice(int posDeVertice){
        this.marcados.set(posDeVertice, Boolean.TRUE);
    }
    //metodo para desmarcar con falso a una posicion de la lista de marcados
    public void desmarcarVertice(int posDeVertice){
        this.marcados.set(posDeVertice, Boolean.FALSE);
    }
    //metodo para desmarcar con falso toda la lista de marcados
    public void desmarcarTodos(){
        for (int i = 0; i < this.marcados.size(); i++)
            this.marcados.set(i, Boolean.FALSE);

    }

    /**
     *
     * @return //retorna si toda lista esta marcada con true,si no false
     */
    public boolean estanTodosMarcados(){
        for (Boolean marcado : this.marcados) {
            if(!marcado){
                return false;
            }
        }
        return true;
    }


    /**
     *
     * metodo para saber si esta marcado en una determinada posicion
     * @param posicion=posicion que nos sirve para verificar si esta marcada
     * @return true si esta marcada en esa posicion,false si no
     *
     */
    public boolean estaMarcado(int posicion){
        return this.marcados.get(posicion);
    }

    /**
     * metodo para buscar el primer espacio con un boolean false
     * si esta en false quiere decir que no esta marcado
     * si esta en true quiere decir que si esta marcado
     * @return retorna la posicion donde existe un elemento no marcado en la lis ta de marcados
     */
    public int buscarPosicionNoMarcada(){
        for (int i = 0; i < this.marcados.size(); i++) {
            if(this.marcados.get(i)== Boolean.FALSE){
                return i;
            }
        }
        return -1;
    }
    @Override
    public String toString(){
        return this.marcados.toString();
    }
}
