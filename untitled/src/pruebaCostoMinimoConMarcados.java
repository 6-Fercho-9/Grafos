import GrafosPesados.ControlCostos;
import Negocio.ControlMarcado;

public class pruebaCostoMinimoConMarcados {
    public static void main(String[] args){
        ControlMarcado marcados=new ControlMarcado(7);
        marcados.marcarVertice(0);
        marcados.marcarVertice(4);
        marcados.marcarVertice(6);
        marcados.marcarVertice(3);
        marcados.marcarVertice(5);
        ControlCostos costos=new ControlCostos(7);
        costos.setCosto(0,60);
        costos.setCosto(1,80);
        costos.setCosto(2,61);
        costos.setCosto(3,31);
        costos.setCosto(4,90);
        costos.setCosto(5,26);
        costos.setCosto(6,0);
        System.out.println(costos.buscarCostoMinimo(marcados));
        System.out.println(costos);
    }
}
