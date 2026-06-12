package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestionExamenFinalTest {
private GestionExamenFinal alumno;

    @BeforeEach
    void setUp() {
        alumno = new GestionExamenFinal("Manuela", 4.5, 3, true);
    }

    //constructor correcto y comprobación de getters
    @Test
    void testConstructorNombre(){
        assertEquals("Manuela", alumno.getNombreAlumno());
    }

    @Test
    void testConstructorNota(){
        assertEquals(4.5, alumno.getNotaExamen());
    }
    @Test
    void testConstructorTarea(){
        assertEquals(3,alumno.getTareasEntregadas());
    }

    @Test
    void testConstructorAsistencia(){
        assertTrue(alumno.isAsistenciaMinima());
    }

    //setters --> modificar nombre, modificar nota, modificar tarea, modificar asistencia
    @Test
    void testSetNombre(){
        alumno.setNombreAlumno("Lucia");
        assertEquals("Lucia", alumno.getNombreAlumno());
    }
    @Test
    void testSetNota(){
        alumno.setNotaExamen(5);
        assertEquals(5, alumno.getNotaExamen());
    }
    @Test
    void testSetTarea(){
        alumno.setTareasEntregadas(10);
        assertEquals(10, alumno.getTareasEntregadas());
    }
    @Test
    void testSetAsistencia(){
        alumno.setAsistenciaMinima(false);
        assertFalse(alumno.isAsistenciaMinima());
    }

    //metodo entregarTarea() --> Entrega correcta,	Varias entregas consecutivas, Caso límite con 0 tareas, Caso erróneo con tareas negativas.
    @Test
    void testEntegarTareaCorrecta(){
        assertTrue(alumno.entregarTarea());
        assertEquals(4,alumno.getTareasEntregadas());
    }
    @Test
    void testEntregasVariasTareas(){
        alumno.setTareasEntregadas(3);
        alumno.entregarTarea();
        alumno.entregarTarea();
        alumno.entregarTarea();
        assertEquals(6, alumno.getTareasEntregadas());
    }
    @Test
    void testEntregarTareaConCero(){
        alumno.setTareasEntregadas(0);
        boolean resultado = alumno.entregarTarea();
        assertTrue(resultado);
        assertEquals(1, alumno.getTareasEntregadas());
    }
    @Test
    void testEntregarTareaNegativa(){
        alumno.setTareasEntregadas(-2);
        assertFalse(alumno.entregarTarea());
    }

    //metodo puedePresentarseRecuperacion() --> suspenso con asistencia y tareas suficientes, suspenso sin asistencia, alumno aprobado, suspenso con pocas tareas, caso límite nota = 5 y nota=1.
    //metodo calcularNotaFinal() --> cálculo normal, Cálculo con bonificación, Nota final superior a 10, Nota de proyecto igual a 0, Nota de examen igual a 0.)
    //metodo clasificarResultado() --> Suspenso, Aprobado, Notable, Sobresaliente.
    //metodo calcularPuntosExtra() --> 0 preguntas, 1 pregunta, 3 preguntas, 5 preguntas, Valor elevado.
    //metodo tieneDerechoMatriculaHonor() --> Cumple todos los requisitos, No cumple nota, No cumple asistencia.
    //metodo calcularPorcentajeTareas() --> 0%, 50%, 100%, Más del 100%, división entre 0.
}