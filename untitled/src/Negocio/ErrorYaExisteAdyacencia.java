package Negocio;

public class ErrorYaExisteAdyacencia extends Exception{
    public ErrorYaExisteAdyacencia(){
        super("Error..ya existe adyacencia entre vertices..");
    }
    public ErrorYaExisteAdyacencia(String args){
        super(args);
    }
}
