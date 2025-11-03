package ar.unahur.edu.obj2.patroncommand.Exepciones;

public class FueraDeRangoDeMemoriaException extends RuntimeException{
    
    public FueraDeRangoDeMemoriaException(String mensaje){
        super(mensaje);
    }
}
