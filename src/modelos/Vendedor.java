package modelos;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import controladores.EjemplarControlador;
import controladores.ClienteControlador;
import controladores.LibroControlador;
import controladores.MetodoPagoControlador;
import controladores.PromocionControlador;
import controladores.VentaControlador;
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
					realizarVenta();
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
	
    private int registrarCliente() {
    	ClienteControlador clienteControlador = new ClienteControlador();
    	
    	String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del cliente:");
        String[] generos = {"Masculino", "Femenino", "No Binario"};
        String genero = (String) JOptionPane.showInputDialog(null, "Seleccione el género del cliente:", "Indentifique cliente", JOptionPane.QUESTION_MESSAGE, null, generos, generos[0]);
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del cliente:"));

        Cliente nuevoCliente = new Cliente(0, nombre, apellido, genero, edad, this.getSucursalId(), false);
        int aux = clienteControlador.addCliente(nuevoCliente);

        JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente! El ID del cliente es " + aux);
        
        return aux;
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
    	
    public void realizarVenta() {
        String[] opcionesAux = {"Si", "No"};
        
        EjemplarControlador ejemplarControlador = new EjemplarControlador();
        MetodoPagoControlador metodoControlador = new MetodoPagoControlador();
        VentaControlador ventaControlador = new VentaControlador();
        PromocionControlador promocionControlador = new PromocionControlador();
        LibroControlador libroControlador = new LibroControlador();
        ClienteControlador clienteControlador = new ClienteControlador();
        
        List<Ejemplar> allEjemplares = ejemplarControlador.getAllEjemplar();
        List<Libro> allLibros = libroControlador.getAllBooks();
        List<Promocion> allPromociones = promocionControlador.getAllPromos();
        List<MetodoPago> allMethods = metodoControlador.getAllMethods();
        
        List<Libro> seleccionLibro = new ArrayList<>();
        List<Ejemplar> seleccionEjemplar = new ArrayList<>();
        List<Ejemplar> ejemplaresAux = new ArrayList<>();
        
        String seleccion = "";
        String opcionesLibros = this.stringLibroBuilder(allLibros);
        
        while(!seleccion.equalsIgnoreCase("esc") && !seleccion.equalsIgnoreCase("finalizar")) {
            seleccion = "Seleccione el libro que desea seleccionar.\n\nFinalizar (esc)\n\n";
            seleccion = JOptionPane.showInputDialog(seleccion + opcionesLibros);
            
            if(seleccion != null && !seleccion.equalsIgnoreCase("esc") && !seleccion.equalsIgnoreCase("finalizar")) {
                try {
                    int seleccionInt = Integer.parseInt(seleccion);
                    Libro bookAux = libroControlador.getBookById(seleccionInt);
                    if (bookAux != null) {
                        seleccionLibro.add(bookAux);
                    } else {
                        JOptionPane.showMessageDialog(null, "Libro no encontrado.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido o 'esc'/'finalizar' para salir.");
                }
            }
        }
        
        JOptionPane.showMessageDialog(null, this.stringLibroBuilder(seleccionLibro));
        
        for (Libro libro : seleccionLibro) {
            for (Ejemplar ejemplar : allEjemplares) {
                if(ejemplar.getLibroId() == libro.getLibroId() && ejemplar.getVentaId() == null) {
                    ejemplaresAux.add(ejemplar);
                }
            }
        }
        
        seleccion = "Seleccione los ejemplares que desea seleccionar.\n\nFinalizar (esc)\n\n";
        
        while(!seleccion.equalsIgnoreCase("esc") && !seleccion.equalsIgnoreCase("finalizar") && ejemplaresAux.size() > 0) {
            seleccion = "Seleccione los ejemplares que desea seleccionar.\n\nFinalizar (esc)\n\n";
            String opcionesEjemplares = this.stringEjemplarBuilder(ejemplaresAux, libroControlador);
            seleccion = JOptionPane.showInputDialog(seleccion + opcionesEjemplares);
            
            if(seleccion != null && !seleccion.equalsIgnoreCase("esc") && !seleccion.equalsIgnoreCase("finalizar")) {
                try {
                    int seleccionInt = Integer.parseInt(seleccion);
                    Ejemplar ejemplarAux = ejemplarControlador.getEjemplarById(seleccionInt);
                    if (ejemplarAux != null) {
                        seleccionEjemplar.add(ejemplarAux);
                        
                        int idLibro = ejemplarAux.getLibroId();
                        
                        Iterator<Ejemplar> iterator = ejemplaresAux.iterator();
                        while (iterator.hasNext()) {
                            Ejemplar ejemplar = iterator.next();
                            if (ejemplar.getLibroId() == idLibro) {
                                iterator.remove();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ejemplar no encontrado.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido o 'esc'/'finalizar' para salir.");
                }
            }
        }
        
        if(ejemplaresAux.size() > 0) {
        	int eleccion = JOptionPane.showOptionDialog(null, "¿El comprador es un cliente registrado?", "Cliente", 0, 0, null, opcionesAux, opcionesAux[0]);
            LocalDate ahora = LocalDate.now();
            int ventaId = -1;
            
            if(eleccion == 1) {
                eleccion = JOptionPane.showOptionDialog(null, "¿El comprador desea registrarse?", "Cliente", 0, 0, null, opcionesAux, opcionesAux[0]);
                switch(eleccion) {
                    case 0:
                        int idClienteNuevo = registrarCliente();
                        String tipo = JOptionPane.showInputDialog("Ingrese el tipo de metodo de pago.");
                        String detalles = JOptionPane.showInputDialog("Ingrese los detalles del metodo de pago.");
                        
                        metodoControlador.addMethod(new MetodoPago(0, idClienteNuevo, tipo, detalles));
                        
                        MetodoPago methodAux = null;
                        allMethods = metodoControlador.getAllMethods();
                        
                        for (MetodoPago method : allMethods) {
                            if(method.getClienteId() == idClienteNuevo) {
                                methodAux = method;
                                break;
                            }
                        }
                        
                        if (methodAux != null) {
                            Venta currentSale = new Venta(0, methodAux.getMetodoPagoId(), ahora, this.getSucursalId());
                            ventaId = ventaControlador.addSale(currentSale);
                            JOptionPane.showMessageDialog(null, "Venta realizada con exito!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al registrar el método de pago.");
                        }
                        break;
                        
                    case 1:
                    	Venta currentSale = new Venta(0, -1, ahora, this.getSucursalId());
                        ventaId = ventaControlador.addSale(currentSale);
                        JOptionPane.showMessageDialog(null, "Venta realizada con exito!");
                        break;
                        
                    default: 
                        JOptionPane.showMessageDialog(null, "Operación cancelada.");
                        break;
                }
            } else {
                try {
                    int clienteId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Id del cliente"));
                    Cliente clienteAux = clienteControlador.getClienteById(clienteId);
                    
                    if (clienteAux != null) {
                        List<MetodoPago> metodosCliente = new ArrayList<>();
                        
                        for (MetodoPago metodoPago : allMethods) {
                            if(metodoPago.getClienteId() == clienteAux.getClienteId()) {
                                metodosCliente.add(metodoPago);
                            }
                        }
                        
                        if (metodosCliente.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El cliente no tiene métodos de pago registrados.");
                        } else {
                            String seleccionMetodo = "Elija el metodo de pago asociado a su cuenta.\n\n";
                            seleccionMetodo += stringMethodBuilder(metodosCliente);
                            
                            int metodoSeleccionado = Integer.parseInt(JOptionPane.showInputDialog(seleccionMetodo));
                            
                            if (metodoSeleccionado >= 0 && metodoControlador.getMethodById(metodoSeleccionado) != null) {
                                MetodoPago metodoAux = metodoControlador.getMethodById(metodoSeleccionado);
                                
                                Venta currentSale = new Venta(0, metodoAux.getMetodoPagoId(), ahora, this.getSucursalId());
                                ventaId = ventaControlador.addSale(currentSale);
                                JOptionPane.showMessageDialog(null, "Venta realizada con exito!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Selección de método de pago inválida.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "ID de cliente inválido.");
                }
            }
            
            if (ventaId != -1) {
                for (Ejemplar ejemplar : seleccionEjemplar) {
                    try {
                        ejemplar.setVentaId(ventaId);
                        ejemplarControlador.updateEjemplar(ejemplar);
                    } catch(Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al actualizar los ejemplares: " + e.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo completar la venta.");
            }
        } else {
        	JOptionPane.showMessageDialog(null, "Lo sentimos, no hay ejemplares disponibles.\n\nCompra cancelada.");
        }
        
    }


    	
	public String stringLibroBuilder(List<Libro> libros) {
	    StringBuilder stringBuilder = new StringBuilder();
	    
	    for (int i = 0; i < libros.size(); i++) {
	        stringBuilder.append(libros.get(i).getLibroId())
	                     .append(": ")
	                     .append(libros.get(i).getTitulo())
	                     .append("    ");
	        
	        if ((i + 1) % 4 == 0) {
	            stringBuilder.append("\n\n");
	        }
	    }
	    
	    return stringBuilder.toString();
	}
    	
	public String stringEjemplarBuilder(List<Ejemplar> ejemplares, LibroControlador libroControlador) {
	    StringBuilder stringBuilder = new StringBuilder();
		for (Ejemplar ejemplar : ejemplares) {
			String tapaDura;
			
			if(ejemplar.isTapaDura()) {
				tapaDura = "(Tapa Dura)";
			} else {
				tapaDura = "(Tapa Blanda)";
			}
				
			stringBuilder.append(ejemplar.getEjemplarId())
						.append(": ").append(libroControlador.getBookById(ejemplar.getLibroId()).getTitulo())
						.append(false).append(" " + tapaDura).append("\n").append("$ ").append(ejemplar.getPrecio()).append("\n\n");
		}
		
	    return stringBuilder.toString();
	}
	
	public String stringMethodBuilder(List<MetodoPago> metodosPago) {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (MetodoPago metodoPago : metodosPago) {
			stringBuilder.append(metodoPago.getMetodoPagoId())
							.append(": ")
							.append(metodoPago.getTipo()).append(" (")
							.append(metodoPago.getDetalles()).append(")\n\n");
		}
		
		return stringBuilder.toString();
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