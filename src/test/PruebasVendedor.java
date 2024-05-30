package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import controladores.ClienteControlador;
import controladores.EjemplarControlador;
import controladores.LibroControlador;
import controladores.VentaControlador;
import modelos.Cliente;
import modelos.Ejemplar;
import modelos.Libro;
import modelos.Vendedor;
import modelos.Venta;

public class PruebasVendedor {

    @Test
    public void pruebaVerEjemplares() {
        EjemplarControlador ejemplarControlador = new EjemplarControlador();
        LibroControlador libroControlador = new LibroControlador();
        
        Vendedor vendedor = new Vendedor(1, "John Doe", 2, 1, "password", "jdoe");
        
        boolean flag = false;
        for (Ejemplar ejemplar : ejemplarControlador.getAllEjemplar()) {
            if (ejemplar.getSucursalId() == vendedor.getSucursalId()) {
                Libro libro = libroControlador.getBookById(ejemplar.getLibroId());
                if (libro != null) {
                    flag = true;
                    break;
                }
            }
        }
        
        assertEquals(true, flag);
    }

    @Test
    public void pruebaRegistrarCliente() {
        ClienteControlador clienteControlador = new ClienteControlador();
        Vendedor vendedor = new Vendedor(1, "John Doe", 2, 1, "password", "jdoe");

        Cliente nuevoCliente = new Cliente(0, "Nombre", "Apellido", "Masculino", 25, vendedor.getSucursalId(), false);
        int aux = clienteControlador.addCliente(nuevoCliente);

        assertEquals(true, aux > 0);
    }

    @Test
    public void pruebaRealizarVenta() {
        EjemplarControlador ejemplarControlador = new EjemplarControlador();
        VentaControlador ventaControlador = new VentaControlador();
        Vendedor vendedor = new Vendedor(1, "John Doe", 2, 1, "password", "jdoe");
        
        boolean flag = false;
        for (Ejemplar ejemplar : ejemplarControlador.getAllEjemplar()) {
            if (ejemplar.getSucursalId() == vendedor.getSucursalId()) {
                Venta venta = new Venta(0, 1, java.time.LocalDate.now(), vendedor.getSucursalId());
                int ventaId = ventaControlador.addSale(venta);
                if (ventaId > 0) {
                    flag = true;
                    break;
                }
            }
        }
        
        assertEquals(true, flag);
    }
}
