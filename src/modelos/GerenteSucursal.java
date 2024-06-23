package modelos;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import controladores.EjemplarControlador;
import controladores.LibroControlador;
import controladores.PromocionControlador;
import controladores.VentaControlador;
import interfaces.Menu;
import vista.ViewSpecimens;
import vista.ViewUsers;

public class GerenteSucursal extends Usuario implements Menu{

	public GerenteSucursal(int usuarioId, String nombre, int rol, int sucursalId, String pass, String userName) {
		super(usuarioId, nombre, rol, sucursalId, pass, userName);
	}

	@Override
	public void Menu() {
		int eleccion = 0;
		String[] opciones = {"Administrar stock", "Administrar promociones", "Administrar Vendedores", 
							 "Ver Informe de Venta", "Salir"};
		do {
			eleccion = JOptionPane.showOptionDialog(null, "¿Que operacion desea realizar?", "Elija por favor", 0, 0, null, opciones, opciones);
			
			switch(eleccion) {
				case 0:
					ViewSpecimens frame2 = new ViewSpecimens(this);
					frame2.setVisible(true);
					break;
				case 1:
					administrarPromociones();
					break;
				case 2:
					ViewUsers frame1 = new ViewUsers(this);
					frame1.setVisible(true);
					break;
				case 3:
					verInformeVenta();
					break;
				case 4:
					JOptionPane.showMessageDialog(null, "Nos re vimos!");
					return;
				default:
					JOptionPane.showMessageDialog(null, "Re ilegal!");
					break;
			}
			
		} while(eleccion != 4);
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
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la promoción:");
        boolean esDelClub = false;
        int sucursalId = this.getSucursalId();
        double descuento = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el porcentaje de descuento:"));

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
            
	        String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la promoción:", promocion.getNombre());
	        double descuento = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo porcentaje de descuento:", promocion.getDescuento()));

	        promocion.setNombre(nombre);
	        promocion.setDescuento(descuento);
	        promocionControlador.updatePromo(promocion);
	        JOptionPane.showMessageDialog(null, "Promoción modificada exitosamente!");
        }
    }
    
    private void verInformeVenta() {
    	VentaControlador ventaControlador = new VentaControlador();
    	EjemplarControlador ejemplarControlador = new EjemplarControlador();
    	LibroControlador libroControlador = new LibroControlador();
    	
    	List<Venta> ventasSucursal = ventaControlador.getSomeSales("sucursal_id", this.getSucursalId());
    	
    	if(ventasSucursal.size() > 0) {
        	StringBuilder stringBuilder = new StringBuilder();
        	stringBuilder.append("Cantidad de ventas en la sucursal: " + ventasSucursal.size() + "\n\n");
        	
        	for (Venta venta : ventasSucursal) {
    			stringBuilder.append(venta.getVentaId() + ": ")
    							.append(venta.getFecha() + "\n")
    							.append("Libros:\n");
    			
    			List<Ejemplar> ejemplares = ejemplarControlador.getEjemplarByField("venta_id", venta.getVentaId());
    			for (Ejemplar ejemplar : ejemplares) {
    				int ejemplarIdAux = ejemplar.getEjemplarId();
    				
					stringBuilder.append("    ID: " + ejemplarIdAux)
									.append(" Titulo: " + libroControlador.getBookById(ejemplarIdAux).getTitulo() + "\n");
				}
    			
    			stringBuilder.append("\n\n");
    		}
        	
        	JOptionPane.showMessageDialog(null, stringBuilder.toString());
        	
    	} else {
    		JOptionPane.showMessageDialog(null, "No hay ventas registradas para esta sucursal.");
    	}
    }
}
