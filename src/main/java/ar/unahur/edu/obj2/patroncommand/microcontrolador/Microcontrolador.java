package ar.unahur.edu.obj2.patroncommand.microcontrolador;

import java.util.Arrays;
import java.util.List;

import ar.unahur.edu.obj2.patroncommand.Exepciones.FueraDeRangoDeMemoriaException;
import ar.unahur.edu.obj2.patroncommand.Operaciones.IOperable;

public class Microcontrolador implements IProgramable{

    private Integer acumuladorA;
    private Integer acumuladorB;
    private Integer programConuter;
    private List<Integer> memoriaDatos = Arrays.asList(new Integer[1024]);



    @Override
    public void run(List<IOperable> operaciones) {
        operaciones.forEach(o -> o.execute(this));
    }

    @Override
    public void incProgramCounter() {
        programConuter ++;
    }

    @Override
    public Integer getProgramCounter() {
        return programConuter;
    }

    @Override
    public void setAcumuladorA(Integer value) {
        acumuladorA = value;
    }

    @Override
    public Integer getAcumuladorA() {
        return acumuladorA;
    }

    @Override
    public void setAcumuladorB(Integer value) {
        acumuladorB = value;
    }

    @Override
    public Integer getAcumuladorB() {
        return acumuladorB;
    }

    private void posicionValida(Integer dirMmemoria){
        if( dirMmemoria < 0 || dirMmemoria >= memoriaDatos.size()){
            throw new FueraDeRangoDeMemoriaException("La direccion de memoria ingresada esta fuera de rango.");
        }
    }

    @Override
    public void setAddr(Integer addr) {
        posicionValida(addr);
        memoriaDatos.set(addr, acumuladorA);
    }
    
    @Override
    public Integer getAddr(Integer addr) {
        posicionValida(addr);
        return memoriaDatos.get(addr);
    }
    
    @Override
    public void reset() {
        acumuladorA = 0;
        acumuladorB = 0;
        programConuter = 0;
        memoriaDatos = Arrays.asList(new Integer[1024]);
    }

    @Override
    public IProgramable copiar() {
        Microcontrolador nuevo = new Microcontrolador();
        nuevo.acumuladorA = this.acumuladorA;
        nuevo.acumuladorB = this.acumuladorB;
        nuevo.programConuter = this.programConuter;
        return nuevo;
    }

    @Override
    public void copiarDesde(IProgramable programable) {
        programConuter = programable.getProgramCounter();
        acumuladorA = programable.getAcumuladorA();
        acumuladorB = programable.getAcumuladorB();
    }

}
