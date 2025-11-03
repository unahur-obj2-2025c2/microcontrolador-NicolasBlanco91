package ar.unahur.edu.obj2.patroncommand.execepciones;

import ar.unahur.edu.obj2.patroncommand.Exepciones.FueraDeRangoDeMemoriaException;
import ar.unahur.edu.obj2.patroncommand.Operaciones.*;
import ar.unahur.edu.obj2.patroncommand.invoker.Programa;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExcepcionesTest {

    private Microcontrolador micro;

    @BeforeEach
    void setUp() {
        micro = new Microcontrolador();
        micro.reset();
    }

    @Test
    void testLodFueraDeRangoInferior() {
        Lod lodNegativo = new Lod(-1);
        assertThrows(FueraDeRangoDeMemoriaException.class, () -> lodNegativo.execute(micro));
        assertEquals(0, micro.getProgramCounter());
    }

    @Test
    void testLodFueraDeRangoSuperior() {
        Lod lodFuera = new Lod(2000);
        assertThrows(FueraDeRangoDeMemoriaException.class, () -> lodFuera.execute(micro));
        assertEquals(0, micro.getProgramCounter());
    }

    @Test
    void testStrFueraDeRangoInferior() {
        micro.setAcumuladorA(42);
        Str strNegativo = new Str(-1);
        assertThrows(FueraDeRangoDeMemoriaException.class, () -> strNegativo.execute(micro));
        assertEquals(0, micro.getProgramCounter());
    }

    @Test
    void testStrFueraDeRangoSuperior() {
        micro.setAcumuladorA(999);
        Str strFuera = new Str(2048);
        assertThrows(FueraDeRangoDeMemoriaException.class, () -> strFuera.execute(micro));
        assertEquals(0, micro.getProgramCounter());
    }

    @Test
    void testProgramaSeDetieneAlFallar() {
        Programa p = new Programa();
        p.agregarOperacionLodv(123);
        p.agregarOperacionStr(2048); 
        p.agregarOperacionSwap();  

        assertThrows(FueraDeRangoDeMemoriaException.class, () -> p.run(micro));
        assertEquals(1, micro.getProgramCounter());
        assertEquals(123, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());
    }
}
