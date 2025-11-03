package ar.unahur.edu.obj2.patroncommand.Operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.IProgramable;

public class Swap extends Comando{

    @Override
    protected void doExecute(IProgramable micro) {
        Integer valorA = micro.getAcumuladorA();
        micro.setAcumuladorA(micro.getAcumuladorB());
        micro.setAcumuladorB(valorA);
    }

}
