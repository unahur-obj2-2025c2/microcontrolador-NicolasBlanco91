package ar.unahur.edu.obj2.patroncommand.Operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.IProgramable;

public class Str extends Comando{

    private Integer addr;
    
    public Str(Integer addr) {
        this.addr = addr;
    }

    @Override
    protected void doExecute(IProgramable micro) {
        micro.setAddr(addr);
    }
}
