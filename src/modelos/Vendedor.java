package modelos;
import javax.swing.JOptionPane;

import controladores.EjemplarControlador;
import controladores.ClienteControlador;
import controladores.LibroControlador;
import controladores.MetodoPagoControlador;
import interfaces.Menu;

public class Vendedor extends Usuario implements Menu{

	public Vendedor(int usuarioId, String nombre, int rol, int sucursalId, String pass, String userName) {
		super(usuarioId, nombre, rol, sucursalId, pass, userName);
	}

	@Override
	public void Menu() {
		int eleccion = 0;
		String[] opciones = {"Ver ejemplares", "Registrar Cliente", "Registrar al Club del Libro", 
							 "Realizar Venta", "Pedir Libro Faltante", "Salir"};
		do {
			eleccion = JOptionPane.showOptionDialog(null, "¿Que operacion desea realizar?", "Elija por favor", 0, 0, null, opciones, opciones);
			
			switch(eleccion) {
				case 0:
					verEjemplares();
					break;
				case 1:
					registrarCliente();
					break;
				case 2:
					registrarClubLibro();
					break;
				case 3:

					break;
				case 4:
					
					break;
				case 5:
					JOptionPane.showMessageDialog(null, "Nos re vimos!");
					return;
				default:
					JOptionPane.showMessageDialog(null, "Re ilegal!");
					break;
			}
		} while(eleccion != 5);
	}
	
	private void verEjemplares() {
	    EjemplarControlador ejemplarControlador = new EjemplarControlador();
	    LibroControlador libroControlador = new LibroControlador();
	    
	    StringBuilder mensaje = new StringBuilder("Ejemplares Disponibles:\n");
	    for (Ejemplar ejemplar : ejemplarControlador.getAllEjemplar()) {
	    	Libro libro = libroControlador.getBookById(ejemplar.getLibroId());
	    	
	        if (ejemplar.getSucursalId() == this.getSucursalId()) {
	            mensaje.append("ID: ").append(ejemplar.getEjemplarId())
	                .append(", Libro: ").append(libro.getTitulo())
	                .append(", Precio: ").append(ejemplar.getPrecio())
	                .append(", Edición Especial: ").append(ejemplar.isEdicionEspecial())
	                .append(", Fecha Edición: ").append(ejemplar.getFechaEdicion())
	                .append(", Número Edición: ").append(ejemplar.getNumeroEdicion())
	                .append(", Idioma: ").append(ejemplar.getIdioma())
	                .append("\n");
	        }
	    }
	    
	    JOptionPane.showMessageDialog(null, mensaje.toString());
	}
	
    private void registrarCliente() {
    	ClienteControlador clienteControlador = new ClienteControlador();
    	
    	String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del cliente:");
        String[] generos = {"Masculino", "Femenino", "No Binario"};
        String genero = (String) JOptionPane.showInputDialog(null, "Seleccione el género del cliente:", "Indentifique cliente", JOptionPane.QUESTION_MESSAGE, null, generos, generos[0]);
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del cliente:"));

        Cliente nuevoCliente = new Cliente(0, nombre, apellido, genero, edad, this.getSucursalId(), false);
        clienteControlador.addCliente(nuevoCliente);

        JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente!");
    }
	
    private void registrarClubLibro() {
    	ClienteControlador clienteControlador = new ClienteControlador();
    	MetodoPagoControlador metodoPagoControlador = new MetodoPagoControlador();
    	
    	int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente:"));
    	Cliente client = clienteControlador.getClienteById(id);
    	
    	for (Cliente cliente : clienteControlador.getAllClientes()) {

	    }
    	
    	
    }
}
