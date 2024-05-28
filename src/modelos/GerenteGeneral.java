package modelos;
import javax.swing.JOptionPane;
import interfaces.Menu;

public class GerenteGeneral extends Usuario implements Menu{

	public GerenteGeneral(int usuarioId, String nombre, int rol, int sucursalId, String pass, String userName) {
		super(usuarioId, nombre, rol, sucursalId, pass, userName);
	}

	@Override
	public void Menu() {
		int eleccion = 0;
		String[] opciones = {"Ver Ventas", "Administrar usuarios", "Administrar sucursales", 
							 "Administrar beneficios", "Pedir Exportacion de Libros", "Salir"};
		do {
			eleccion = JOptionPane.showOptionDialog(null, "¿Que operacion desea realizar?", "Elija por favor", 0, 0, null, opciones, opciones);
			
			switch(eleccion) {
			case 0:

				break;
			case 1:

				break;
			case 2:

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
