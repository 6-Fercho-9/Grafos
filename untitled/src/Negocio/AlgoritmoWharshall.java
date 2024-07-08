package Negocio;

public class AlgoritmoWharshall {
    byte[][] matriz;//matriz siempre cuadrada
    Digrafo elDiGrafo;
    public AlgoritmoWharshall(Digrafo unGrafo){
        this.elDiGrafo=unGrafo;
        int n=this.elDiGrafo.cantidadDeVertices();
        this.matriz=new byte[n][n];
        this.pasarDeListasAMatriz();
        this.ejecutarWharShall2();
    }

    /**
     * metodo parapasar de listas a matriz
     */
    public void pasarDeListasAMatriz(){
        for (int i = 0; i < this.elDiGrafo.cantidadDeVertices(); i++) {
            Iterable<Integer> adyacentesAlVertice=this.elDiGrafo.adyacentesDelVertice(i);
            for(Integer elemento:adyacentesAlVertice){
                this.matriz[i][elemento]=1;
            }
        }
    }
    public void ejecutarWharShall(){
        for (int k = 0; k < this.matriz.length; k++) {
            for (int i = 0; i < this.matriz.length ; i++) {
                for (int j = 0; j <this.matriz.length; j++) {
                    if(this.matriz[i][j]<this.matriz[i][k]*this.matriz[k][j]){
                        this.matriz[i][j]=1;
                    }
                }
            }
        }
    }
    public void ejecutarWharShall2(){
        for (int k = 0; k < this.matriz.length; k++) {
            for (int i = 0; i < this.matriz.length ; i++) {
                for (int j = 0; j <this.matriz.length; j++) {
                    if(this.matriz[i][k]==1&&this.matriz[k][j]==1){
                        this.matriz[i][j]=1;
                    }
                }
            }
        }
    }
    public boolean tieneUnoTodaDiagonalPrincipal(){
        for (int i = 0; i < this.matriz.length; i++) {
            if(this.matriz[i][i]==0){
                return false;
            }
        }
        return true;
    }
    public byte[][] getMatriz(){
        return this.matriz;
    }

    public Digrafo getDigrafo(){
        return this.elDiGrafo;
    }
}
