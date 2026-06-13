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
    @Test
    void testPuedePresentarseSuspensoConAsistenciaYTareas(){
        assertTrue(alumno.puedePresentarseRecuperacion());
    }

    @Test
    void testPuedePresentarseSinAsistencia(){
        alumno.setAsistenciaMinima(false);
        assertFalse(alumno.puedePresentarseRecuperacion());
    }
    @Test
    void testPuedePresentarseAlumnoAprobado(){
        alumno.setNotaExamen(7.0);
        assertFalse(alumno.puedePresentarseRecuperacion());
    }

    @Test
    void testPuedePresentarsePocasTareas(){
        alumno.setTareasEntregadas(2);
        assertFalse(alumno.puedePresentarseRecuperacion());
    }

    @Test
    void testPuedePresentarseCasosLimitesCinco(){
        alumno.setNotaExamen(5.0);
        assertFalse(alumno.puedePresentarseRecuperacion());
    }
    @Test
    void testPuedePresentarseCasosLimitesUno(){
        alumno.setNotaExamen(1.0);
        assertTrue(alumno.puedePresentarseRecuperacion());
    }


    //metodo calcularNotaFinal() --> cálculo normal, Cálculo con bonificación, Nota final superior a 10, Nota de proyecto igual a 0, Nota de examen igual a 0.)
    @Test
    void testeCalcularNotaFinalNormal(){
        assertEquals(5.55, alumno.calcularNotaFinal(8.0));
    }
    @Test
    void testCalcularNotaFinalBonificacion(){
        alumno.setTareasEntregadas(5);
        //5.55 + 0.5 = 6.05
        assertEquals(6.05, alumno.calcularNotaFinal(8.0));
   }
    @Test
    void testCalcularNotaFinalSuperiorDiez(){
        alumno.setNotaExamen(10);
        alumno.setTareasEntregadas(5);
        //10 + 0.5 = 10.5 → tope a 10
        assertEquals(10.0, alumno.calcularNotaFinal(10.0));
    }
    @Test
    void testCalcularNotaFinalProyectoIgualCero(){
        //proyecto = 0 → 4.5×0.7 + 0×0.3 = 3.15
        assertEquals(3.15, alumno.calcularNotaFinal(0.0));
    }
    @Test
    void testCalcularNotaFinalExamenIgualCero(){
        alumno.setNotaExamen(0);
        // 0×0.7 + 8×0.3 = 2.4
        assertEquals(2.4, alumno.calcularNotaFinal(8.0));
    }

    //metodo clasificarResultado() --> Suspenso, Aprobado, Notable, Sobresaliente.
    @Test
    void testclasificarResultadoSuspenso(){
        assertEquals("Suspenso", alumno.clasificarResultado(4.0));
    }
    @Test
    void testclasificarResultadoAprobado(){
        assertEquals("Aprobado", alumno.clasificarResultado(6.0));
    }
    @Test
    void testclasificarResultadoNotable(){
        assertEquals("Notable", alumno.clasificarResultado(8.0));
    }
    @Test
    void testclasificarResultadoSobresaliente(){
        assertEquals("Sobresaliente", alumno.clasificarResultado(10.0));
    }

    //metodo calcularPuntosExtra() --> 0 preguntas, 1 pregunta, 3 preguntas, 5 preguntas, Valor elevado.

    @Test
    void calcularPuntosExtraCero(){
        assertEquals(0,alumno.calcularPuntosExtra(0));
    }
    @Test
    void calcularPuntosExtraUna(){
        assertEquals(1,alumno.calcularPuntosExtra(1));
    }
    @Test
    void calcularPuntosExtraTres(){
        assertEquals(4, alumno.calcularPuntosExtra(3));
    }
    @Test
    void calcularPuntosExtraCinco(){
        assertEquals(6, alumno.calcularPuntosExtra(5));
    }
    @Test
    void calcularPuntosExtraValorElevado(){
        assertEquals(8, alumno.calcularPuntosExtra(6));
    }

    //metodo tieneDerechoMatriculaHonor() --> Cumple todos los requisitos, No cumple nota, No cumple asistencia.
    @Test
    void testTieneDerechoMatriculaHonorCumpleRequisitos(){
        alumno.setTareasEntregadas(5);
        assertTrue(alumno.tieneDerechoMatriculaHonor(9.5));
    }
    @Test
    void testTieneDerechoMatriculaHonorNoCumpleNota(){
        alumno.setTareasEntregadas(5);
        assertFalse(alumno.tieneDerechoMatriculaHonor(9.0));
    }
    @Test
    void testTieneDerechoMatriculaHonorNoCumpleAsistencia(){
        alumno.setTareasEntregadas(5);
        alumno.setAsistenciaMinima(false);
        assertFalse(alumno.tieneDerechoMatriculaHonor(9.5));
    }

    //metodo calcularPorcentajeTareas() --> 0%, 50%, 100%, Más del 100%, división entre 0.

    @Test
    void testCalcularPorcentajeTareasCero(){
        assertEquals(0.0, alumno.calcularPorcentajeTareas(0, 10));
    }
    @Test
    void testCalcularPorcentajeTareasCincuenta(){
        assertEquals(50.0, alumno.calcularPorcentajeTareas(5, 10));
    }
    @Test
    void testCalcularPorcentajeTareasCien(){
        assertEquals(100.0, alumno.calcularPorcentajeTareas(10, 10));
    }
    @Test
    void testCalcularPorcentajeTareasMasCien(){
        assertEquals(150.0, alumno.calcularPorcentajeTareas(15, 10));
    }
    @Test
    void testCalcularPorcentajeTareasDivisionCero(){
        assertEquals(0.0, alumno.calcularPorcentajeTareas(5,0));
    }

}