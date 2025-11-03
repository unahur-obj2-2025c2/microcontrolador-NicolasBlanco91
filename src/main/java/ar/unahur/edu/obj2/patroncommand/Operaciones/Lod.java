package ar.unahur.edu.obj2.patroncommand.Operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.IProgramable;

public class Lod extends Comando{

    private Integer addr;
        public Lod(Integer addr){
            this.addr = addr;
        }

    @Override
    protected void doExecute(IProgramable micro) {
        micro.setAcumuladorA((micro.getAddr(addr)));    
    }

}
