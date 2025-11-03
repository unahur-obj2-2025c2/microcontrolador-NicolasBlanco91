package ar.unahur.edu.obj2.patroncommand.invoker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.unahur.edu.obj2.patroncommand.Operaciones.Add;
import ar.unahur.edu.obj2.patroncommand.Operaciones.Composite;
import ar.unahur.edu.obj2.patroncommand.Operaciones.IOperable;
import ar.unahur.edu.obj2.patroncommand.Operaciones.Ifnz;
import ar.unahur.edu.obj2.patroncommand.Operaciones.Lodv;
import ar.unahur.edu.obj2.patroncommand.Operaciones.Swap;
import ar.unahur.edu.obj2.patroncommand.Operaciones.Whnz;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.IProgramable;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Microcontrolador;

public class ProgramaTest {
    
    private Programa p = new Programa();
    private IProgramable micro = new Microcontrolador();

    @BeforeEach
    void setUp(){
        p.vaciarLista();
        micro.reset();
    }

    @Test
    void hacerAvanzar3PosicionesElProgramCounter(){
        Integer cantidadDePosiciones = 3; 
        for (int i = 0; i < cantidadDePosiciones; i++) {
            p.agregarOperacionNop();
        }

        p.run(micro);

        assertEquals(cantidadDePosiciones, micro.getProgramCounter());
    }

    @Test 
    void sumar20Mas17(){
        p.agregarOperacionLodv(20);
        p.agregarOperacionSwap();
        p.agregarOperacionLodv(17);
        p.agregarOperacionAdd();

        p.run(micro);

        assertEquals(37, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());
        assertEquals(4, micro.getProgramCounter()); 
    } 

    @Test 
    void sumar2Mas8Mas5(){
        p.agregarOperacionLodv(2);
        p.agregarOperacionStr(0);
        p.agregarOperacionLodv(8);
        p.agregarOperacionSwap();
        p.agregarOperacionLodv(5);
        p.agregarOperacionAdd();
        p.agregarOperacionSwap();
        p.agregarOperacionLod(0);
        p.agregarOperacionAdd();

        p.run(micro);

        assertEquals(15, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());
    }
    @Test
    void testIfnzEjecutaBloqueSiANoEsCero() {
        List<IOperable> operables = new ArrayList<>();
        operables.add(new Lodv(100)); 
        operables.add(new Swap());    
        List<IOperable> programaPrincipal = new ArrayList<>();
        programaPrincipal.add(new Lodv(5));
        programaPrincipal.add(new Ifnz(operables)); 
        programaPrincipal.add(new Lodv(99)); 
        micro.run(programaPrincipal);
        assertEquals(99, micro.getAcumuladorA());
        assertEquals(100, micro.getAcumuladorB()); 
        assertEquals(5, micro.getProgramCounter()); 
    }

    @Test
    void testIfnzNoEjecutaBloqueSiAEsCero() {
        
        List<IOperable> operables = new ArrayList<>();
        operables.add(new Lodv(100)); 
        operables.add(new Swap());

        List<IOperable> programaPrincipal = new ArrayList<>();
        programaPrincipal.add(new Lodv(0));        
        programaPrincipal.add(new Ifnz(operables)); 
        programaPrincipal.add(new Lodv(99));     

        micro.run(programaPrincipal);

        assertEquals(99, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());  
        assertEquals(3, micro.getProgramCounter());
    }

    

    @Test
    void testWhnzNoEjecutaBucleSiAEsCero() {
    
        List<IOperable> bloqueWhile = new ArrayList<>();
        bloqueWhile.add(new Lodv(1000));

        List<IOperable> programaPrincipal = new ArrayList<>();
        programaPrincipal.add(new Lodv(0));          
        programaPrincipal.add(new Whnz(bloqueWhile));
        programaPrincipal.add(new Lodv(77));

        micro.run(programaPrincipal);

        assertEquals(77, micro.getAcumuladorA());
        assertEquals(3, micro.getProgramCounter());
    }
 
     @Test
    void testCompositeEjecutaOperacionesInternas() {
        Microcontrolador micro = new Microcontrolador();
        micro.reset();

        List<IOperable> operable= Arrays.asList(
                new Lodv(10),
                new Swap(),
                new Lodv(5),
                new Add()
        );
        Composite composite = new Composite(operable);

        composite.execute(micro);

        assertEquals(15, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());
        assertEquals(5, micro.getProgramCounter());
    }


    @Test
    void testIfnzYWhnzCubrenAmbasRamas() {
        Microcontrolador micro = new Microcontrolador();
        micro.reset();
        List<IOperable> bloque = Arrays.asList(new Lodv(10));
        micro.setAcumuladorA(1);
        Ifnz ifnz = new Ifnz(bloque);
        ifnz.execute(micro);
        assertEquals(10, micro.getAcumuladorA());

        micro.setAcumuladorA(0);
        Ifnz ifnzNo = new Ifnz(bloque);
        ifnzNo.execute(micro);
        assertEquals(0, micro.getAcumuladorA());

        micro.setAcumuladorA(0);
        Whnz whnz = new Whnz(bloque);
        whnz.execute(micro);
        assertEquals(0, micro.getAcumuladorA());
    }

    @Test
    void testWhnzEjecutaHastaQueASeaCero() {
        Microcontrolador micro = new Microcontrolador();
        micro.reset();

        // Cuerpo: A = A - 1 (simulado con SWAP + LODV(-1) + ADD)
        List<IOperable> cuerpo = Arrays.asList(
                new Swap(),
                new Lodv(-1),
                new Add()
        );

        Whnz whnz = new Whnz(cuerpo);

        micro.setAcumuladorA(3);
        whnz.execute(micro);

        assertEquals(0, micro.getAcumuladorA());
        assertTrue(micro.getProgramCounter() > 0);
    }

    @Test
    void testCopiarDesde() {
        
        IProgramable microOrigen = new Microcontrolador();
        microOrigen.reset(); 
        microOrigen.setAcumuladorA(10);
        microOrigen.setAcumuladorB(20);
        microOrigen.incProgramCounter(); 
        microOrigen.incProgramCounter(); 
        microOrigen.incProgramCounter();
        IProgramable microDestino = new Microcontrolador();
        microDestino.reset();
        microDestino.copiarDesde(microOrigen);
        
        assertEquals(10, microDestino.getAcumuladorA());
        assertEquals(20, microDestino.getAcumuladorB());
        assertEquals(3, microDestino.getProgramCounter());
    }
}
   

