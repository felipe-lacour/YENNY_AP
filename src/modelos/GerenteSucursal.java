package modelos;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import controladores.EjemplarControlador;
import controladores.LibroControlador;
import controladores.PromocionControlador;
import controladores.VentaControlador;
import interfaces.Auxiliaries;
import interfaces.Menu;

public class GerenteSucursal extends Usuario implements Menu, Auxiliaries{

	public GerenteSucursal(int usuarioId, String nombre, int rol, int sucursalId, String pass, String userName) {
		super(usuarioId, nombre, rol, sucursalId, pass, userName);
	}

	@Override
	public void Menu() {
		int eleccion = 0;
		String[] opciones = {"Ver libros", "Administrar promociones", "Encargar Libros", 
							 "Ver Informe de Venta", "Administrar Vendedores", "Salir"};
		do {
			eleccion = JOptionPane.showOptionDialog(null, "¿Que operacion desea realizar?", "Elija por favor", 0, 0, null, opciones, opciones);
			
			switch(eleccion) {
				case 0:
					verEjemplares();
					break;
				case 1:
					administrarPromociones();
					break;
				case 2:

					break;
				case 3:
					verInformeVenta();
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
	
    private void administrarPromociones() {
        PromocionControlador promocionControlador = new PromocionControlador();

        String[] opciones = {"Agregar Promoción", "Quitar Promoción", "Modificar Promoción"};
        int eleccion = JOptionPane.showOptionDialog(null, "¿Qué operación desea realizar?", "Administrar Promociones", 0, 0, null, opciones, opciones[0]);

        switch(eleccion) {
            case 0:
                agregarPromocion(promocionControlador);
                break;
            case 1:
                quitarPromocion(promocionControlador);
                break;
            case 2:
                modificarPromocion(promocionControlador);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Operación inválida!");
                break;
        }
    }
    
    private void agregarPromocion(PromocionControlador promocionControlador) {
    	String nombre = "";
    	
    	do {
    		nombre = JOptionPane.showInputDialog("Ingrese el nombre de la promoción: (Recuerde solo utilizar letras");
    	} while (!verifyStrInput(nombre));
    	
        boolean esDelClub = false;
        int sucursalId = this.getSucursalId();
        String descuentoStr = "";
        
        do {
        	descuentoStr = JOptionPane.showInputDialog("Ingrese el porcentaje de descuento: (Recuerde solo utilizar numeros)");
        } while (!verifyDouInput(descuentoStr));
        
        double descuento = Double.parseDouble(descuentoStr);
        
        Promocion nuevaPromocion = new Promocion(0, nombre, esDelClub, sucursalId, descuento);
        promocionControlador.addPromo(nuevaPromocion);

        JOptionPane.showMessageDialog(null, "Promoción agregada exitosamente!");
    }

    private void quitarPromocion(PromocionControlador promocionControlador) {
    	List<Promocion> promociones = new LinkedList<Promocion>();
    	for (Promocion promo : promocionControlador.getAllPromos()) {
    		if (!promo.isEsDelClub() && promo.getSucursalId() == this.getSucursalId()) {
    			promociones.add(promo);
    		}
    	}
    	
        if (promociones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay promociones para eliminar.");
            return;
        }
        
        String[] opciones = new String[promociones.size()];
        for (int i = 0; i < promociones.size(); i++) {
        	opciones[i] = ("ID: " + promociones.get(i).getPromocionId() + ", Nombre: " + promociones.get(i).getNombre());
        }

        String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione la promoción a eliminar:", "Quitar Promoción", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        
        if (seleccion != null) {
            int promocionId = Integer.parseInt(seleccion.split(",")[0].split(":")[1].trim());
            promocionControlador.deletePromo(promocionId);
            JOptionPane.showMessageDialog(null, "Promoción eliminada exitosamente!");
        }
    }

    private void modificarPromocion(PromocionControlador promocionControlador) {
    	boolean stringVerified = false;
    	List<Promocion> promociones = new LinkedList<Promocion>();
    	for (Promocion promo : promocionControlador.getAllPromos()) {
    		if (!promo.isEsDelClub() && promo.getSucursalId() == this.getSucursalId()) {
    			promociones.add(promo);
    		}
    	}
    	
        if (promociones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay promociones para modificar.");
            return;
        }
        
        String[] opciones = new String[promociones.size()];
        for (int i = 0; i < promociones.size(); i++) {
        	opciones[i] = ("ID: " + promociones.get(i).getPromocionId() + ", Nombre: " + promociones.get(i).getNombre());
        }
        
        String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione la promoción a eliminar:", "Quitar Promoción", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion != null) {
            int promocionId = Integer.parseInt(seleccion.split(",")[0].split(":")[1].trim());
            Promocion promocion = promocionControlador.getPromoById(promocionId);
            String nombre = ""; String descuentoAux = "";
            
            
            do {
            	nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la promoción: (Recuerde solo utilizar letras)", promocion.getNombre());
            } while (!verifyStrInput(nombre));
            
            do {
            	descuentoAux = JOptionPane.showInputDialog("Ingrese el nuevo porcentaje de descuento: (Recuerde solo utilizar numeros)", promocion.getDescuento());
            } while (!verifyDouInput(descuentoAux));
            
            double descuento = Double.parseDouble(descuentoAux);
	        

	        promocion.setNombre(nombre);
	        promocion.setDescuento(descuento);
	        promocionControlador.updatePromo(promocion);
	        JOptionPane.showMessageDialog(null, "Promoción modificada exitosamente!");
        }
    }
    
    private void verInformeVenta() {/*
    	VentaControlador ventaControlador = new VentaControlador();
    	for (Venta venta : ventaControlador.getAllSales()) {
    		if (venta.() == this.getSucursalId()) {
    			promociones.add(promo);
    		}
    	}*/
    }
}
