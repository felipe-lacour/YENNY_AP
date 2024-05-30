package test;
import static org.junit.Assert.*;

import java.util.List;
import javax.swing.JOptionPane;
import org.junit.Test;
import controladores.DatabaseConnection;
import controladores.LibroControlador;
import modelos.GerenteSucursal;
import modelos.Libro;
import interfaces.LibroRepositorio;


public class LibreriaTest {
	private LibroControlador libroControlador;
	 private GerenteSucursal gerenteSucursal;
	 
	 @Test
	 public void testSolicitarLibroPorNombreExistente() {
	        Libro nuevoLibro = new Libro(0, "Libro Existente", null, 1, 1);
	        libroControlador.addBook(nuevoLibro);
	        solicitarLibroPorNombre(libroControlador, "Libro Existente", 10);
	        List<Libro> libros = libroControlador.getAllBooks();
	        boolean libroEncontrado = libros.stream().anyMatch(libro -> libro.getTitulo().equals("Libro Existente"));
	        assertTrue(libroEncontrado);
	    }
	 @Test
	    public void testSolicitarLibroSinCantidad() {
	        Libro nuevoLibro = new Libro(0, "Libro Sin Cantidad", null, 1, 1);
	        libroControlador.addBook(nuevoLibro);
	        solicitarLibroPorNombre(libroControlador, "Libro Sin Cantidad", 0);
	        List<Libro> libros = libroControlador.getAllBooks();
	        boolean libroEncontrado = libros.stream().anyMatch(libro -> libro.getTitulo().equals("Libro Sin Cantidad"));
	        assertTrue(libroEncontrado);
	    }
	 @Test
	 public void solicitarLibroPorNombre(LibroControlador libroControlador, String nombre, int cantidad) {
	        List<Libro> libros = libroControlador.getAllBooks();
	        boolean libroEncontrado = false;
	        for (Libro libro : libros) {
	            if (libro.getTitulo().equalsIgnoreCase(nombre)) {
	                JOptionPane.showMessageDialog(null, "Libro solicitado exitosamente.");
	                libroEncontrado = true;
	                break;
	            }
	        }
	        if (!libroEncontrado) {
	            JOptionPane.showMessageDialog(null, "El libro no está disponible.");
	        }
	    }
	 @Test
	 public void menuSolicitarLibro(LibroControlador libroControlador) {
	        List<Libro> libros = libroControlador.getAllBooks();
	        if (libros.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "No hay libros disponibles.");
	            return;
	        }

	        String[] titulosLibros = libros.stream().map(Libro::getTitulo).toArray(String[]::new);
	        String libroSeleccionado = (String) JOptionPane.showInputDialog(null, "Seleccione un libro:", "Libros disponibles", JOptionPane.QUESTION_MESSAGE, null, titulosLibros, titulosLibros[0]);

	        if (libroSeleccionado != null) {
	            String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad que desea solicitar:");
	            if (cantidadStr != null) {
	                int cantidad = Integer.parseInt(cantidadStr);
	                solicitarLibroPorNombre(libroControlador, libroSeleccionado, cantidad);
	            }
	        }
	    }
	 
	
 
}
