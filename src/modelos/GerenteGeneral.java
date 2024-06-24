package modelos;

import interfaces.Menu;
import vista.ViewBooks;
import vista.ViewBranches;
import vista.ViewPromos;
import vista.ViewUsers;
import controladores.SucuControlador;
import controladores.VentaControlador;
import controladores.LibroControlador;

import java.util.List;
import javax.swing.JOptionPane;

public class GerenteGeneral extends Usuario implements Menu{

	public GerenteGeneral(int usuarioId, String nombre, int rol, int sucursalId, String pass, String userName) {
		super(usuarioId, nombre, rol, sucursalId, pass, userName);
	}

	@Override
	public void Menu() {
		int eleccion = 0;
		String[] opciones = {"Ver Ventas", "Administrar usuarios", "Administrar sucursales", 
							 "Administrar beneficios", "Pedir Exportacion de Libros", "Ver libros", "Salir"};
		do {
			eleccion = JOptionPane.showOptionDialog(null, "¿Que operacion desea realizar?", "Elija por favor", 0, 0, null, opciones, opciones);
			
			switch(eleccion) {
			case 0:
				verVentas();
				break;
			case 1:
				ViewUsers frame1 = new ViewUsers(null);
				frame1.setVisible(true);
				break;
			case 2:
				ViewBranches frame3 = new ViewBranches(null);
				frame3.setVisible(true);
				break;
			case 3:
				ViewPromos frame2 = new ViewPromos(null);
				frame2.setVisible(true);
				break;
			case 4:
				exportarLibros();
				break;
			case 5:
				ViewBooks frame = new ViewBooks(null);
				frame.setVisible(true);
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "Nos re vimos!");
				return;
			default:
				JOptionPane.showMessageDialog(null, "Re ilegal!");
				break;
			}
		} while(eleccion != 6);
	}
	
    private void verVentas() {
    	VentaControlador ventaControlador = new VentaControlador();
    	
        StringBuilder mensaje = new StringBuilder("Ventas Realizadas:\n");
        for (Venta venta : ventaControlador.getAllSales()) {
            mensaje.append("ID: ").append(venta.getVentaId()).append(", Metodo Pago: ").append(venta.getMetodoPagoId()).append(", Fecha: ").append(venta.getFecha()).append("\n");
        }
        
        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    private void exportarLibros() {
    	LibroControlador libroControlador = new LibroControlador();
        List<Libro> libros = libroControlador.getAllBooks();
        String[] titulos = libros.stream().map(Libro::getTitulo).toArray(String[]::new);

        String titulo = (String) JOptionPane.showInputDialog(null, "Seleccione el libro a exportar:", "Exportación de Libros", JOptionPane.QUESTION_MESSAGE, null, titulos, titulos[0]);
        Libro libroSeleccionado = libros.stream().filter(libro -> libro.getTitulo().equals(titulo)).findFirst().orElse(null);

        if (libroSeleccionado != null) {
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad a exportar:"));
            String pais = JOptionPane.showInputDialog("Ingrese el país de destino:");

            JOptionPane.showMessageDialog(null, "Se ha solicitado la exportación de " + cantidad + " ejemplares del libro \"" + libroSeleccionado.getTitulo() + "\" a " + pais);
        } else {
            JOptionPane.showMessageDialog(null, "Libro no encontrado!");
        }
    }
}