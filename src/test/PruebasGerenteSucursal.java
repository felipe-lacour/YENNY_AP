package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import controladores.EjemplarControlador;
import controladores.LibroControlador;
import controladores.PromocionControlador;
import controladores.VentaControlador;
import modelos.Ejemplar;
import modelos.GerenteSucursal;
import modelos.Libro;
import modelos.Promocion;
import modelos.Venta;

import java.util.List;
import java.util.LinkedList;

public class PruebasGerenteSucursal {

    @Test
    public void pruebaVerEjemplares() {
        EjemplarControlador ejemplarControlador = new EjemplarControlador();
        LibroControlador libroControlador = new LibroControlador();
        
        GerenteSucursal gerente = new GerenteSucursal(1, "Jane Doe", 3, 1, "password", "jdoe");
        
        boolean flag = false;
        for (Ejemplar ejemplar : ejemplarControlador.getAllEjemplar()) {
            if (ejemplar.getSucursalId() == gerente.getSucursalId()) {
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
    public void pruebaAdministrarPromociones() {
        PromocionControlador promocionControlador = new PromocionControlador();
        GerenteSucursal gerente = new GerenteSucursal(1, "Jane Doe", 3, 1, "password", "jdoe");

        Promocion nuevaPromocion = new Promocion(0, "Descuento Verano", false, gerente.getSucursalId(), 15.0);
        promocionControlador.addPromo(nuevaPromocion);

        boolean flag = false;
        for (Promocion promo : promocionControlador.getAllPromos()) {
            if (promo.getNombre().equals("Descuento Verano") && promo.getSucursalId() == gerente.getSucursalId()) {
                flag = true;
                break;
            }
        }
        
        assertEquals(true, flag);
    }

    @Test
    public void pruebaVerInformeVenta() {
        VentaControlador ventaControlador = new VentaControlador();
        EjemplarControlador ejemplarControlador = new EjemplarControlador();
        LibroControlador libroControlador = new LibroControlador();
        
        GerenteSucursal gerente = new GerenteSucursal(1, "Jane Doe", 3, 1, "password", "jdoe");
        
        boolean flag = false;
        List<Venta> ventasSucursal = ventaControlador.getSomeSales("sucursal_id", gerente.getSucursalId());
        
        if (!ventasSucursal.isEmpty()) {
            for (Venta venta : ventasSucursal) {
                List<Ejemplar> ejemplares = ejemplarControlador.getEjemplarByField("venta_id", venta.getVentaId());
                if (!ejemplares.isEmpty()) {
                    flag = true;
                    break;
                }
            }
        }
        
        assertEquals(true, flag);
    }
}
