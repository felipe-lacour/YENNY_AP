package modelos;

import java.util.List;

import javax.swing.JOptionPane;

import controladores.LibroControlador;

public class Libro {
	private int libroId;
	private String titulo;
	private Integer sagaId;
	private Integer editorialId;
	private Integer autorId;
	
	public Libro(int libroId, String titulo, Integer sagaId, int editorialId, int autorId) {
		this.libroId = libroId;
		this.titulo = titulo;
		this.sagaId = sagaId;
		this.editorialId = editorialId;
		this.autorId = autorId;
	}

	public int getLibroId() {
		return libroId;
	}

	public String getTitulo() {
		return titulo;
	}

	public Integer getSagaId() {
		return sagaId;
	}

	public int getEditorialId() {
		return editorialId;
	}

	public int getAutorId() {
		return autorId;
	}

	public void setLibroId(int libroId) {
		this.libroId = libroId;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setSagaId(Integer sagaId) {
		this.sagaId = sagaId;
	}

	public void setEditorialId(int editorialId) {
		this.editorialId = editorialId;
	}

	public void setAutorId(int autorId) {
		this.autorId = autorId;
	}
	
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
	 
	 
	
	
}

