package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvaluacionFinalTest {

    EvaluacionFinal eval;           //declarar como objeto a la clase desde 0 para poder trabajar para que empieces desde 0

    @BeforeEach                     //antes de ejecutar cada test, ejecuta primero este método.
    void inicioPruebas(){
        eval = new EvaluacionFinal();
    }

    @Test
    void testCalcularNotaFinalCasoCorrecto() {
        //uso el objeto ya creado antes
        assertEquals(6.7, eval.calcularNotaFinal(6, 7, 8));
    }

    @org.junit.jupiter.api.Test
    void obtenerResultado() {
    }

    @org.junit.jupiter.api.Test
    void estaAprobado() {
    }

    @org.junit.jupiter.api.Test
    void calcularMedia() {
    }
}