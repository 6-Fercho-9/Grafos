package Negocio;

public class ErrorNoExisteAdyacencia extends Exception{
    public ErrorNoExisteAdyacencia(){
        super("Error...No Existe Adyacencia entre los vertices");
    }
    public ErrorNoExisteAdyacencia(String args){
        super(args);
    }
}
