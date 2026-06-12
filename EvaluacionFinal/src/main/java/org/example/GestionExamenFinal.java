package org.example;

public class GestionExamenFinal {

    private String nombreAlumno;
    private double notaExamen;
    private int tareasEntregadas;
    private boolean asistenciaMinima;

    public GestionExamenFinal(String nombreAlumno, double notaExamen, int tareasEntregadas, boolean asistenciaMinima) {

        this.nombreAlumno = nombreAlumno;
        this.notaExamen = notaExamen;
        this.tareasEntregadas = tareasEntregadas;
        this.asistenciaMinima = asistenciaMinima;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public double getNotaExamen() {
        return notaExamen;
    }

    public void setNotaExamen(double notaExamen) {
        this.notaExamen = notaExamen;
    }

    public int getTareasEntregadas() {
        return tareasEntregadas;
    }

    public void setTareasEntregadas(int tareasEntregadas) {
        this.tareasEntregadas = tareasEntregadas;
    }

    public boolean isAsistenciaMinima() {
        return asistenciaMinima;
    }

    public void setAsistenciaMinima(boolean asistenciaMinima) {
        this.asistenciaMinima = asistenciaMinima;
    }

    public boolean entregarTarea() {

        if (tareasEntregadas < 0) {
            return false;
        }

        tareasEntregadas++;
        return true;
    }

    public boolean puedePresentarseRecuperacion() {

        if (!asistenciaMinima) {
            return false;
        }

        if (notaExamen >= 5) {
            return false;
        }

        return tareasEntregadas >= 3;
    }

    public double calcularNotaFinal(double notaProyecto) {

        double notaFinal =
                (notaExamen * 0.7) +
                (notaProyecto * 0.3);

        if (tareasEntregadas >= 5) {
            notaFinal += 0.5;
        }

        if (notaFinal > 10) {
            notaFinal = 10;
        }

        return notaFinal;
    }

    public String clasificarResultado(double notaFinal) {

        if (notaFinal < 5) {
            return "Suspenso";
        } else if (notaFinal < 7) {
            return "Aprobado";
        } else if (notaFinal < 9) {
            return "Notable";
        } else {
            return "Sobresaliente";
        }
    }

    public int calcularPuntosExtra(int preguntasBien) {

        int puntos = 0;

        for (int i = 1; i <= preguntasBien; i++) {

            if (i % 3 == 0) {
                puntos += 2;
            } else {
                puntos += 1;
            }
        }

        return puntos;
    }

    public boolean tieneDerechoMatriculaHonor(double notaFinal) {

        return notaFinal >= 9.5
                && asistenciaMinima
                && tareasEntregadas >= 5;
    }

    public double calcularPorcentajeTareas(int tareasRealizadas,
                                           int tareasTotales) {

        if (tareasTotales == 0) {
            return 0;
        }

        return (tareasRealizadas * 100.0)
                / tareasTotales;
    }
}
