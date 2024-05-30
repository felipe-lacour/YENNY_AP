package test;
import static org.junit.Assert.*;
import java.util.List;
import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;
import controladores.DatabaseConnection;
import controladores.LibroControlador;
import modelos.GerenteSucursal;
import modelos.Libro;
import interfaces.LibroRepositorio;


public class LibreriaTest {
	private LibroControlador libroControlador;
	private GerenteSucursal gerenteSucursal;
	private Libro libro;
	 
	@Before
    public void setUp() {
        libroControlador = new LibroControlador();
        gerenteSucursal = new GerenteSucursal(1, "GerenteSucursal", 1, 1, "password", "pedro");
        libro = new Libro(1, "Harry Potter y la piedra filosofal", null, 1, 1);
        libroControlador.addBook(libro);
    }
	@Test
    public void testObtenerLibrosDisponibles() {
        List<Libro> libros = libroControlador.getAllBooks();
        assertTrue("Debe haber libros disponibles.", libros.size() > 0);
    }
	@Test
    public void testSolicitarLibroPorNombreExistente() {
        solicitarLibroPorNombre(libroControlador, "Harry Potter y la piedra filosofal", 10);
    }
	public void solicitarLibroPorNombre(LibroControlador libroControlador, String nombre, int cantidad) {
        List<Libro> libros = libroControlador.getAllBooks();
        boolean libroEncontrado = false;

        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(nombre)) {
                if (cantidad > 0) {
                    System.out.println("Libro solicitado exitosamente. Cantidad: " + cantidad);
                } else {
                    System.out.println("Cantidad no válida. La cantidad debe ser mayor que cero.");
                }
                libroEncontrado = true;
                break;
            }
        }

        if (!libroEncontrado) {
            fail("El libro no está disponible.");
        }
    }
	 @Test
	    public void testSolicitarLibroSinCantidad() {
		 solicitarLibroPorNombre(libroControlador, "Libro de Prueba", 0);
	    }
	 
	 
	
 
}
