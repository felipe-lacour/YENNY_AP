package modelos;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    	List<MetodoPago> metodos = new LinkedList<MetodoPago>();;
    	
    	int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente:"));
    	Cliente client = clienteControlador.getClienteById(id);
    	
    	for (MetodoPago metodo : metodoPagoControlador.getAllMethods()) {
    		if (metodo.getClienteId() == client.getClienteId()) {
    			metodos.add(metodo);
    		}
	    }
    	
    	if (!metodos.isEmpty()) {
    		List<String> listaMetodosRegistrados = metodos.stream().map(MetodoPago::getTipo).collect(Collectors.toList());
    		listaMetodosRegistrados.add("Agregar metodo de pago");
    		String[] metodosRegistrados = listaMetodosRegistrados.toArray(new String[0]);

    		String eleccion = (String) JOptionPane.showInputDialog(null, "Seleccione el metodo de pago:", "Seleccion de pago", JOptionPane.QUESTION_MESSAGE, null, metodosRegistrados, metodosRegistrados[0]);
    	
    		if (eleccion.equalsIgnoreCase("Agregar metodo de pago")) {
    			agregarMetodoPago(id);
    		}
    	} else {
    		JOptionPane.showMessageDialog(null, "Para registrarse al club del libro \ndebe tener un metodo de pago asociado a su cuenta \nPor favor ingrese uno");
    		agregarMetodoPago(id);
    	}
    	
    	JOptionPane.showMessageDialog(null, "Se ha registrado el usuario al club del libro!");
    	client.setClubLibros(true);
    	clienteControlador.updateCliente(client);
    }
    
    private void agregarMetodoPago(int id) {
        MetodoPagoControlador metodoPagoControlador = new MetodoPagoControlador();
        
        String tipo = JOptionPane.showInputDialog("Ingrese el tipo de método de pago: \nPor ejemplo: Tarjeta de Crédito, Débito, PayPal");
        String detalles = JOptionPane.showInputDialog("Ingrese los detalles del método de pago:");

        MetodoPago nuevoMetodo = new MetodoPago(0, id, tipo, detalles);
        metodoPagoControlador.addMethod(nuevoMetodo);

        JOptionPane.showMessageDialog(null, "Método de pago agregado exitosamente!");
    }
}