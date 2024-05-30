package modelos;
import javax.swing.JOptionPane;
import controladores.LibroControlador;
import interfaces.Menu;

public class GerenteSucursal extends Usuario implements Menu{

	public GerenteSucursal(int usuarioId, String nombre, int rol, int sucursalId, String pass, String userName) {
		super(usuarioId, nombre, rol, sucursalId, pass, userName);
	}

	@Override
	public void Menu() {
		LibroControlador libroControlador = new LibroControlador();
		int eleccion = 0;
		String[] opciones = {"Ver libros", "Administrar promociones", "Encargar Libros", 
							 "Ver Informe de Venta", "Administrar Vendedores", "Salir"};
		do {
			eleccion = JOptionPane.showOptionDialog(null, "¿Que operacion desea realizar?", "Elija por favor", 0, 0, null, opciones, opciones);
			
			switch(eleccion) {
				case 0:

					break;
				case 1:

					break;
				case 2:
					Libro libro = new Libro(0, "", null, 0, 0);
                    libro.menuSolicitarLibro(libroControlador);
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
}
