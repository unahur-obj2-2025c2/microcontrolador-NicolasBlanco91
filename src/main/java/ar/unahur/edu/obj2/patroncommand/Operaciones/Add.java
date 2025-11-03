package ar.unahur.edu.obj2.patroncommand.Operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.IProgramable;

public class Add extends Comando{

    @Override
    protected void doExecute(IProgramable micro) {
        Integer resultado = micro.getAcumuladorA() + micro.getAcumuladorB();
        micro.setAcumuladorA(resultado);
        micro.setAcumuladorB(0);
    }

}
