package org.example;

public class BancoService {
    private final RepositorioBanco repositorio;

    public BancoService(RepositorioBanco repositorio) {
        this.repositorio = repositorio;
    }

    public void depositar(String cuenta, double monto) {
        double saldoActual = repositorio.obtenerSaldo(cuenta);
        repositorio.actualizarSaldo(cuenta, saldoActual + monto);
    }

    public void retirar(String cuenta, double monto) {
        double saldoActual = repositorio.obtenerSaldo(cuenta);
        if (saldoActual < monto) {
            throw new IllegalArgumentException("Fondos insuficientes");
        }
        repositorio.actualizarSaldo(cuenta, saldoActual - monto);
    }

    public double consultarSaldo(String cuenta) {
        return repositorio.obtenerSaldo(cuenta);
    }
}
