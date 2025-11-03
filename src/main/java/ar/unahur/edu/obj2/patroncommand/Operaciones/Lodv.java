package ar.unahur.edu.obj2.patroncommand.Operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.IProgramable;

public class Lodv extends Comando{

    private Integer val;

    
    
    public Lodv(Integer val) {
        this.val = val;
    }

    @Override
    protected void doExecute(IProgramable micro) {
        micro.setAcumuladorA(val);
    }

}
