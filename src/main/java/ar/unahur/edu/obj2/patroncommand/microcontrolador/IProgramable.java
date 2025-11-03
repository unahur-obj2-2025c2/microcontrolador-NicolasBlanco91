package ar.unahur.edu.obj2.patroncommand.microcontrolador;

import java.util.List;

import ar.unahur.edu.obj2.patroncommand.Operaciones.IOperable;

public interface IProgramable {

    void run(List<IOperable> operaciones);

    void incProgramCounter();

    Integer getProgramCounter();

    void setAcumuladorA(Integer value);

    Integer getAcumuladorA();

    void setAcumuladorB(Integer value);

    Integer getAcumuladorB();

    void setAddr(Integer addr);

    Integer getAddr(Integer addr);

    void reset();

    IProgramable copiar();

    void copiarDesde(IProgramable programable);

}
