package ar.unahur.edu.obj2.patroncommand.Operaciones;

import java.util.List;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.IProgramable;

public class Ifnz extends Composite{

    public Ifnz(List<IOperable> operaciones) {
        super(operaciones);
    }
    
    @Override
    public void doExecute(IProgramable micro) {
        if (this.noEsCero(micro)) {
            super.doExecute(micro);
        }
    }
}
