package Negocio;

public class ErrorNoCumpleRequisitos extends Exception{
    public ErrorNoCumpleRequisitos(){
        super("Error No Cumple Requisitos para Ordenamiento Topologico");
    }
    public ErrorNoCumpleRequisitos(String args){
        super(args);
    }
}
