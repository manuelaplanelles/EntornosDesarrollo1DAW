package org.example;

public interface RepositorioBanco {
    double obtenerSaldo(String cuenta);
    void actualizarSaldo(String cuenta, double nuevoSaldo);
}
