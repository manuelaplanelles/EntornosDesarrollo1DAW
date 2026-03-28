import org.example.BancoService;
import org.example.RepositorioBanco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BancoServiceTest {

    @Mock
    private RepositorioBanco repositorio;

    @InjectMocks
    private BancoService bancoService;

    @InjectMocks
    @Spy
    private BancoService bancoSpy;

    // Test 1 — Depositar aumenta el saldo correctamente
    @Test
    void testDepositar() {
        when(repositorio.obtenerSaldo("cuenta1")).thenReturn(100.0);

        bancoService.depositar("cuenta1", 50.0);

        verify(repositorio).actualizarSaldo("cuenta1", 150.0);
    }

    // Test 2 — Retirar disminuye el saldo correctamente
    @Test
    void testRetirar() {
        when(repositorio.obtenerSaldo("cuenta1")).thenReturn(200.0);

        bancoService.retirar("cuenta1", 80.0);

        verify(repositorio).actualizarSaldo("cuenta1", 120.0);
    }

    // Test 3 — Retirar con fondos insuficientes lanza excepción
    @Test
    void testRetirarFondosInsuficientes() {
        when(repositorio.obtenerSaldo("cuenta1")).thenReturn(50.0);

        assertThrows(IllegalArgumentException.class, () -> {
            bancoService.retirar("cuenta1", 100.0);
        });
    }

    // Test 4 — Consultar saldo devuelve el valor correcto
    @Test
    void testConsultarSaldo() {
        when(repositorio.obtenerSaldo("cuenta1")).thenReturn(300.0);

        double saldo = bancoService.consultarSaldo("cuenta1");

        assertEquals(300.0, saldo);
    }

    // Test 5 — Retornos múltiples
    @Test
    void testRetornosMultiples() {
        when(repositorio.obtenerSaldo("cuenta1")).thenReturn(100.0, 150.0);

        assertEquals(100.0, bancoService.consultarSaldo("cuenta1"));
        assertEquals(150.0, bancoService.consultarSaldo("cuenta1"));
    }

    // Test 6 — Verificación de orden de llamadas
    @Test
    void testOrdenDeLlamadas() {
        when(repositorio.obtenerSaldo("cuenta1")).thenReturn(100.0);

        bancoService.depositar("cuenta1", 50.0);

        InOrder inOrder = inOrder(repositorio);
        inOrder.verify(repositorio).obtenerSaldo("cuenta1");
        inOrder.verify(repositorio).actualizarSaldo("cuenta1", 150.0);
    }

    // Test 7 — Spy mantiene comportamiento real
    @Test
    void testSpy() {
        when(repositorio.obtenerSaldo("cuenta1")).thenReturn(500.0);

        double saldo = bancoSpy.consultarSaldo("cuenta1");

        assertEquals(500.0, saldo);
    }
}
