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
        assertEquals(6.699999999999999, eval.calcularNotaFinal(6, 7, 8));
    }

    //obtenerResultado
    @Test
    void testObtenerResultadoSuspenso(){
        assertEquals("Suspenso", eval.obtenerResultado(4.0));
    }
    @Test
    void testObtenerResultadoAprobado(){
        assertEquals("Aprobado", eval.obtenerResultado(5.0));
    }
    @Test
    void testObtenerResultadoNotable(){
        assertEquals("Notable", eval.obtenerResultado(7.0));
    }
    @Test
    void testObtenerResultadoSobresaliente(){
        assertEquals("Sobresaliente", eval.obtenerResultado(9.0));
    }

    @Test
    void testCasosLimiteAprobado1(){
        assertEquals("Aprobado", eval.obtenerResultado(4.9));
    }
    @Test
    void testCasosLimiteAprobado2(){
        assertEquals("Aprobado", eval.obtenerResultado(5.0));
    }
    @Test
    void testCasosLimiteNotable1(){
        assertEquals("Notable", eval.obtenerResultado(6.9));
    }
    @Test
    void testCasosLimiteNotable2(){
        assertEquals("Notable", eval.obtenerResultado(7.0));
    }
    @Test
    void testCasosLimiteSobresaliente1(){
        assertEquals("Sobresaliente", eval.obtenerResultado(8.9));
    }
    @Test
    void testCasosLimiteSobresaliente2(){
        assertEquals("Sobresaliente", eval.obtenerResultado(9.0));
    }

    @Test
    void testestaAprobadoTrue() {
        assertTrue(eval.estaAprobado(5.0));
    }
    @Test
    void testestaAprobadoFalse() {
        assertFalse(eval.estaAprobado(4.0));
    }

    @Test
    void testcalcularMedia() {
        double[] notas = {5.0,7.0,8.0};
        assertEquals(6.666666666666667, eval.calcularMedia(notas));
    }

    @Test
    void testNotaNegativa(){
        assertThrows(IllegalArgumentException.class, () -> {
            eval.calcularNotaFinal(-1, 5, 5);
        });
    }

    @Test
    void testNotaMayor10(){
        assertThrows(IllegalArgumentException.class, () -> {
            eval.calcularNotaFinal(11, 5, 5);
        });

    }
    @Test
    void testArrayVacio(){
        assertThrows(IllegalArgumentException.class, () -> {
            eval.calcularMedia(new double[]{});
        });

    }


}