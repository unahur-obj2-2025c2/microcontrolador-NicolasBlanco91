package ar.unahur.edu.obj2.patroncommand.invoker;

import java.util.ArrayList;
import java.util.List;

import ar.unahur.edu.obj2.patroncommand.Operaciones.Add;
import ar.unahur.edu.obj2.patroncommand.Operaciones.IOperable;
import ar.unahur.edu.obj2.patroncommand.Operaciones.Lod;
import ar.unahur.edu.obj2.patroncommand.Operaciones.Lodv;
import ar.unahur.edu.obj2.patroncommand.Operaciones.Nop;
import ar.unahur.edu.obj2.patroncommand.Operaciones.Str;
import ar.unahur.edu.obj2.patroncommand.Operaciones.Swap;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.IProgramable;

public class Programa {
    
    List<IOperable> operaciones;

    public Programa() {
        operaciones = new ArrayList<>();
    }

    public void agregarOperacionNop(){
        operaciones.add(new Nop());
    }

    public void agregarOperacionAdd(){
        operaciones.add(new Add());
    }

    public void agregarOperacionSwap(){
        operaciones.add(new Swap());
    }

    public void agregarOperacionLod(Integer direccionEnMemoria){
        operaciones.add(new Lod(direccionEnMemoria));
    }

    public void agregarOperacionStr(Integer direccionEnMemoria){
        operaciones.add(new Str(direccionEnMemoria));
    }

    public void agregarOperacionLodv(Integer valor){
        operaciones.add(new Lodv(valor));
    }

    public void vaciarLista(){
        operaciones.clear();
    }

    public void run(IProgramable micro){
        micro.run(operaciones);
    }

}
