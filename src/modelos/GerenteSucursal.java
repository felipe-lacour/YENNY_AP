package modelos;

import java.util.List;

import javax.swing.JOptionPane;

import controladores.EjemplarControlador;
import controladores.LibroControlador;
import controladores.VentaControlador;
import interfaces.Menu;
import vista.ViewPromos;
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
					ViewPromos frame3 = new ViewPromos(this);
					frame3.setVisible(true);
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
