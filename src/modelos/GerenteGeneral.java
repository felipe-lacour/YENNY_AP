package modelos;
import javax.swing.JOptionPane;

public class GerenteGeneral extends Usuario{

	public GerenteGeneral(int usuarioId, String nombre, int rol, int sucursalId, String pass, String userName) {
		super(usuarioId, nombre, rol, sucursalId, pass, userName);
	}

	public void Menu(SystemYENNY SystemYENNY) {
		boolean continuar = false;
		String[] opciones = {"Ver Ventas", "Usuarios", "Sucursales", "Establecer Beneficios Globales",
				"Pedir Exportacion de Libros"};
		String[] opciones1 = {"Si", "No"};
		do {
			int eleccion = JOptionPane.showOptionDialog(null, 
					"¿Que operacion desea realizar?", 
					"Elija por favor", 
					0, 
					0, 
					null, opciones, opciones);
			
			switch(eleccion) {
				case 0:
					String[] ventas = SystemYENNY.getAll(SystemYENNY.getVentas());
					
					String ventasAux = "";
					
					for (String string : ventas) {
						ventasAux += "\n" + string;
					}
					
					JOptionPane.showMessageDialog(null, ventasAux);
					break;
				case 1:
					String[] usuarios = SystemYENNY.getAll(SystemYENNY.getUsuarios());
					
					String usuariosAux = "";
					
					for (String string : usuarios) {
					 usuariosAux += "\n" + string;
					}
					
					JOptionPane.showMessageDialog(null, usuariosAux);
					break;
				case 2:
					String[] sucursales = SystemYENNY.getAll(SystemYENNY.getSucursales());
					
					String sucursalesAux = "";
					
					for (String string : sucursales) {
						sucursalesAux += "\n" + string;
					}
					
					JOptionPane.showMessageDialog(null, sucursalesAux);
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "Aqui el gerente general puede cargar y quitar"
							+ "\nbeneficios de todas las sucursales.");
					break;
				case 4:

					JOptionPane.showMessageDialog(null, "Aqui el gerente general puede seleccionar el libro"
							+ "\nque se va a exportar, la cantidad y el destino.");
					break;
			}
			
			int eleccionFinal = JOptionPane.showOptionDialog(null, 
					"¿Desea realizar otra operacion?", 
					"Elija por favor", 
					0, 
					0, 
					null, opciones1, opciones1);
			
			if(eleccionFinal == 0) {
				continuar = true;
			} else {
				continuar = false;
			}
		} while(continuar);
	}
}
