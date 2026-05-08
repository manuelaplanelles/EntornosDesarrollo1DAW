package org.example;

public class EvaluacionFinal {

    public double calcularNotaFinal(double examen, double practicas, double proyecto) {
        validarNota(examen);
        validarNota(practicas);
        validarNota(proyecto);

        return examen * 0.5 + practicas * 0.3 + proyecto * 0.2;
    }

    public String obtenerResultado(double notaFinal) {
        validarNota(notaFinal);

        int nota = (int) notaFinal;         //elimina los decimales sin redondear.

        switch (nota) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                return "Suspenso";

            case 5:
            case 6:
                return "Aprobado";

            case 7:
            case 8:
                return "Notable";

            case 9:
            case 10:
                return "Sobresaliente";

            default:
                throw new IllegalArgumentException("Nota inválida");
        }
    }

    public boolean estaAprobado(double notaFinal) {
        validarNota(notaFinal);
        return notaFinal >= 5;
    }

    public double calcularMedia(double[] notas) {
        if (notas == null || notas.length == 0) {
            throw new IllegalArgumentException("Debe haber al menos una nota");
        }

        double suma = 0;

        for (int i = 0; i < notas.length; i++) {
            validarNota(notas[i]);
            suma += notas[i];
        }

        return suma / notas.length;
    }

    private void validarNota(double nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 10");
        }
    }
}