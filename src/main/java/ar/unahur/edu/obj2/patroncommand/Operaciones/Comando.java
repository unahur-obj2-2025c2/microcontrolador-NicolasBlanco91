package ar.unahur.edu.obj2.patroncommand.Operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.IProgramable;

public abstract class Comando implements IOperable{

    IProgramable microPrevio;

    @Override
    public void execute(IProgramable micro) {
        microPrevio = micro.copiar();
        doExecute(micro);
        micro.incProgramCounter();
    }

    protected abstract void doExecute(IProgramable micro);

    @Override
    public void undo(IProgramable micro) {
       micro.copiarDesde(microPrevio);
    }
}
