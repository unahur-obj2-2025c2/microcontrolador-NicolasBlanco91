package ar.unahur.edu.obj2.patroncommand.Operaciones;

import java.util.List;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.IProgramable;

public class Whnz extends Composite{

    public Whnz(List<IOperable> operaciones) {
        super(operaciones);
    }

    @Override
    public void doExecute(IProgramable micro){
        while(this.noEsCero(micro)){
            super.doExecute(micro);
        }
    }
}
