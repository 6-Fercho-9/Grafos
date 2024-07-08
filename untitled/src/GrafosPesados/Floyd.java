package GrafosPesados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Floyd {
    private double[][] matrizdePesos;
    private int[][] matrizdePredecesores;
    private DigrafoPesado elDigrafoPesado;
    private static double INFINITO=Double.POSITIVE_INFINITY;
    private static byte PREDECESOR_NO_VALIDO=-1;
    private final List<Integer> camino;
    public Floyd(DigrafoPesado elDigrafoPesado){
        this.camino=new ArrayList<>();
        this.elDigrafoPesado=elDigrafoPesado;
        int n=this.elDigrafoPesado.cantidadDeVertices();
        this.instanciarMatrizDePesosConInfinitoeInstanciarPredecesores(n,n);
        this.pasarDeListasAMatriz();
        this.ejecutarFloyd();
    }
    public boolean existeCamino(int verticeA,int verticeB){
        if(matrizdePesos[verticeA][verticeB]!=Floyd.INFINITO){
            return true;
        }
        return false;
    }
    public void generarCamino(int verticeA,int verticeB){
        boolean existeCamino=this.existeCamino(verticeA,verticeB);
        if(!existeCamino){
            return;
        }
        this.generarCamino(verticeA,verticeB,camino);
    }

    private int generarCamino(int verticeA,int verticeB,List<Integer> recorrido){
            if(matrizdePredecesores[verticeA][verticeB]==verticeB){
                if(recorrido.isEmpty()){
                    recorrido.add(verticeA,verticeB);
                }else{
                    recorrido.add(verticeB);
                }
               return verticeB;
            }
            int verticeAuxiliar=generarCamino(verticeA,matrizdePredecesores[verticeA][verticeB],
                    recorrido);
            if(matrizdePredecesores[verticeAuxiliar][verticeB]!=verticeB){
                return generarCamino(verticeAuxiliar,verticeB,recorrido);
            }else{
                return verticeB;
            }
    }

    public void ejecutarFloyd(){
        for (int k = 0; k < this.matrizdePesos.length; k++) {
            for (int i = 0; i < this.matrizdePesos.length; i++) {
                for (int j = 0; j < this.matrizdePesos.length; j++) {
                    if(i!=j){//para que ignore la diagonal principal
                        if(this.matrizdePesos[i][j]>(this.matrizdePesos[i][k]+this.matrizdePesos[k][j])){
                            this.matrizdePesos[i][j]=this.matrizdePesos[i][k]+this.matrizdePesos[k][j];
                            this.matrizdePredecesores[i][j]=k;
                        }
                    }
                }
            }
        }
    }

    public void instanciarPredecesores(int n,int m){
        this.matrizdePredecesores =new int[n][m];
        for (int i = 0; i< matrizdePredecesores.length; i++){
            for (int j = 0; j< matrizdePredecesores.length; j++){
                if(i==j){
                    this.matrizdePredecesores[i][j]=Floyd.PREDECESOR_NO_VALIDO;
                }else{
                    this.matrizdePredecesores[i][j]=j;
                }
            }
        }
    }

    public void instanciarMatrizDePesosConInfinitoeInstanciarPredecesores(int n,int m){
        this.matrizdePesos=new double[n][m];
        this.matrizdePredecesores=new int[n][m];
        for (int i=0;i<matrizdePesos.length;i++){
            for (int j=0;j<matrizdePesos.length;j++){
                if(i==j){
                    this.matrizdePesos[i][j]=0;
                    this.matrizdePredecesores[i][j]=Floyd.PREDECESOR_NO_VALIDO;
                }else{
                    this.matrizdePesos[i][j]=Floyd.INFINITO;
                    this.matrizdePredecesores[i][j]=j;
                }
            }
        }
    }

    public List<Integer> getCamino() {
        return camino;
    }

    public void setearValorADiagonalPrincipal(double valor){
        for (int i = 0; i < this.matrizdePesos.length; i++) {
            this.matrizdePesos[i][i]=valor;
        }
    }
    public void pasarDeListasAMatriz(){
        for (int i = 0; i < this.elDigrafoPesado.cantidadDeVertices(); i++) {
            Iterable<AdyacenteConPeso> adyacentesAlVertice=this.elDigrafoPesado.adyacentesDelVertice(i);
            for(AdyacenteConPeso elemento:adyacentesAlVertice){
                this.matrizdePesos[i][elemento.getIndiceVertice()]=this.elDigrafoPesado.peso(i, elemento.getIndiceVertice());
            }
        }
    }
}
