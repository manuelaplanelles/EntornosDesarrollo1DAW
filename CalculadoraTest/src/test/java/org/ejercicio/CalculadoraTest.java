package org.ejercicio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CalculadoraTest {

    @Test
    public void testSuma() {
        Calculadora calc = new Calculadora(3, 5);
        int valorEsperado = 8;
        int valorObtenido = calc.suma();
        assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void testResta() {
        Calculadora calc = new Calculadora(10, 4);
        int valorEsperado = 6;
        int valorObtenido = calc.resta();
        assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void testMultiplica() {
        Calculadora calc = new Calculadora(3, 4);
        int valorEsperado = 12;
        int valorObtenido = calc.multiplica();
        assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void testDivide() {
        Calculadora calc = new Calculadora(10, 2);
        int valorEsperado = 5;
        int valorObtenido = calc.divide();
        assertEquals(valorEsperado, valorObtenido);
    }
}