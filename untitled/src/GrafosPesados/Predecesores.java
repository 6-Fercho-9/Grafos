package GrafosPesados;

import java.util.ArrayList;
import java.util.List;

public class Predecesores {
    public static final byte POSICION_INVALIDA=-1;
    private final List<Integer> predecesores;
    public Predecesores(){
        predecesores=new ArrayList<>();
    }
    public Predecesores(int n){
        this.predecesores=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            this.predecesores.add((int)Predecesores.POSICION_INVALIDA);
        }
    }
    public void validarPosicion(int posicion){
        if(posicion<0||posicion>=this.predecesores.size())
            throw new IllegalArgumentException("Error.. posicion no valida");
    }
    public void setValor(int posicion,int valor){
        this.validarPosicion(posicion);
        this.predecesores.set(posicion,valor);
    }
    public int getValor(int posicion){
        this.validarPosicion(posicion);
        return this.predecesores.get(posicion);
    }
    @Override
    public String toString(){
        return this.predecesores.toString();
    }
}
