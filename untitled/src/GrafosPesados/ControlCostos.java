package GrafosPesados;
import Negocio.ControlMarcado;

import java.util.List;
import java.util.ArrayList;
public class ControlCostos {
    public static final double INFINITO=Double.POSITIVE_INFINITY;
    private final List<Double> costos;
    public ControlCostos(){
        this.costos=new ArrayList<>();
    }
    public ControlCostos(int n){
        this.costos=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            this.costos.add(ControlCostos.INFINITO);
        }
    }
    public void agregarCosto(double costo){
        this.costos.add(costo);
    }
    public void validarPosicion(int posicion){
        if(posicion<0||posicion>=this.costos.size())
            throw new IllegalArgumentException("Error.. posicion no valida");
    }
    public void setCosto(int posicion,double valor){
        this.validarPosicion(posicion);
        this.costos.set(posicion,valor);
    }
    public double getCosto(int posicion){
        this.validarPosicion(posicion);
        return this.costos.get(posicion);
    }
    public List<Double> getCostos(){
        return this.costos;
    }
    public double calcularCosto(int posicion){
        this.validarPosicion(posicion);
        return this.costos.get(posicion);
    }
    public int buscarCostoMinimo(ControlMarcado marcados){
        int posicionNoMarcada=marcados.buscarPosicionNoMarcada();
        if(posicionNoMarcada==-1){
            return -1;
        }
        double minimo=this.costos.get(posicionNoMarcada);
        int vertice=posicionNoMarcada;
        for (int i = 0; i < this.costos.size(); i++) {
            if(!marcados.estaMarcado(i)){
                double costoActual=costos.get(i);
                if(costoActual<minimo){
                    minimo=costoActual;
                    vertice=i;
                }
            }
        }
        return vertice;
    }
    @Override
    public String toString(){
        return this.costos.toString();
    }
}
