package ar.unahur.edu.obj2.patroncommand.Operaciones;

import java.util.ArrayList;
import java.util.List;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.IProgramable;

public class Composite extends Comando{

    List<IOperable> operaciones = new ArrayList<>();
    
    public Composite(List<IOperable> operaciones) {
        this.operaciones = operaciones;
    }

    @Override
    protected void doExecute(IProgramable micro) {
       micro.run(operaciones);
    }

    public Boolean noEsCero(IProgramable micro){
        return !micro.getAcumuladorA().equals(0);
    }
}
