package ar.unahur.edu.obj2.patroncommand.Operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.IProgramable;

public interface IOperable {

    void execute(IProgramable micro);

    void undo(IProgramable micro);
}
