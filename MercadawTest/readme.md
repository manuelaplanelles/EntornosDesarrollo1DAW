# MercadawTest — Pruebas Unitarias con JUnit 5

**1º DAW – Entornos de Desarrollo | IES Mutxamel | Curso 2025/2026**  
**Autora:** Manuela Planelles Lucas

Pruebas unitarias desarrolladas con JUnit 5 para validar la lógica de la aplicación Mercadaw. Se testean las clases `Cliente`, `Pedido` y `Mercadaw`, cubriendo la gestión de pedidos, cálculo de importes, aplicación de promociones y autenticación de usuarios.

---

## Métodos probados

**Cliente**
- `insertarProducto()` — inserción de productos en el pedido
- `importePedido()` — cálculo del importe total
- `crearPedido()` — inicialización del pedido

**Pedido**
- `aplicarPromo3x2()` — promoción 3 por 2
- `aplicarPromo10()` — descuento del 10%
- `mostrarResumen()` — resumen del pedido
- `mostrarResumenOrdenado()` — resumen ordenado por unidades

**Mercadaw**
- `getClientes()` / `setClientes()` — gestión de la lista de clientes

---

## Casos normales

- Insertar un producto existente lo añade correctamente al pedido
- Insertar el mismo producto dos veces suma la cantidad
- El importe total se calcula correctamente según productos y cantidades
- La promoción 3x2 descuenta correctamente una unidad por cada tres
- El descuento del 10% se aplica correctamente sobre el importe
- Las promociones combinadas (3x2 + 10%) dan el resultado esperado
- Un cliente con credenciales correctas se encuentra en la lista

## Casos límite

- Insertar un producto inexistente no modifica el pedido
- Los métodos de resumen no lanzan excepciones con datos válidos

---

## Cobertura

- **Line:** 85%  
- **Branch:** 100%  
- Supera el mínimo requerido del 80%

![Cobertura](img/cobertura.png)

---

## Tests

<details>

<summary>Ver código completo — MercadawTest.java</summary>

```java

package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MercadawTest {

    private Cliente cliente;
    private Pedido pedido;

    @BeforeEach
    public void inicioPruebas() {
        cliente = new Cliente("manuela", "1234");
        cliente.crearPedido();
        pedido = cliente.getPedido();
    }

    @Test
    public void testInsertarProductoExistente() {
        cliente.insertarProducto("manzanas");
        assertTrue(pedido.getPedido().containsKey(Producto.MANZANAS));
    }

    @Test
    public void testInsertarProductoInexistente() {
        cliente.insertarProducto("xyz");
        assertTrue(pedido.getPedido().isEmpty());
    }

    @Test
    public void testCalculoImporte() {
        cliente.insertarProducto("manzanas");
        cliente.insertarProducto("pan");
        pedido.setImporte_total(cliente.importePedido());
        assertEquals(3.30, pedido.getImporte_total(), 0.01);
    }

    @Test
    public void testPromo3x2() {
        cliente.insertarProducto("pan");
        cliente.insertarProducto("pan");
        cliente.insertarProducto("pan");
        pedido.setImporte_total(cliente.importePedido());
        pedido.aplicarPromo3x2();
        assertEquals(2.00, pedido.getImporte_total(), 0.01);
    }

    @Test
    public void testPromo10() {
        cliente.insertarProducto("arroz");
        pedido.setImporte_total(cliente.importePedido());
        pedido.aplicarPromo10();
        assertEquals(3.15, pedido.getImporte_total(), 0.01);
    }

    @Test
    public void testPromocionesCombinadas() {
        cliente.insertarProducto("pan");
        cliente.insertarProducto("pan");
        cliente.insertarProducto("pan");
        pedido.setImporte_total(cliente.importePedido());
        pedido.aplicarPromo3x2();
        pedido.aplicarPromo10();
        assertEquals(1.80, pedido.getImporte_total(), 0.01);
    }

    @Test
    public void testAutenticacionCorrecta() {
        Mercadaw.setClientes(new java.util.ArrayList<>());
        Mercadaw.getClientes().add(new Cliente("manuela", "1234"));
        boolean encontrado = false;
        for (Cliente c : Mercadaw.getClientes()) {
            if (c.getUsuario().equals("manuela") && c.getContrasenya().equals("1234")) {
                encontrado = true;
            }
        }
        assertTrue(encontrado);
    }

    @Test
    public void testInsertarProductoRepetido() {
        cliente.insertarProducto("manzanas");
        cliente.insertarProducto("manzanas");
        assertEquals(2, pedido.getPedido().get(Producto.MANZANAS));
    }

    @Test
    public void testGettersSettersCliente() {
        cliente.setDireccion("Calle Nueva, 1");
        assertEquals("Calle Nueva, 1", cliente.getDireccion());
        cliente.setPromociones(true);
        assertTrue(cliente.isPromociones());
    }

    @Test
    public void testGettersSettersPedido() {
        pedido.setImporte_total(10.00);
        assertEquals(10.00, pedido.getImporte_total(), 0.01);
    }

    @Test
    public void testMostrarResumen() {
        cliente.insertarProducto("leche");
        pedido.setImporte_total(cliente.importePedido());
        assertDoesNotThrow(() -> pedido.mostrarResumen());
    }

    @Test
    public void testMostrarResumenOrdenado() {
        cliente.insertarProducto("leche");
        cliente.insertarProducto("pan");
        pedido.setImporte_total(cliente.importePedido());
        assertDoesNotThrow(() -> pedido.mostrarResumenOrdenado());
    }
}

```

</details>

---

## Tecnologías

- Java 21
- JUnit 5
- Maven
- IntelliJ IDEA
