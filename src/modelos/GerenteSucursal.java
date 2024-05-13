package modelos;
import javax.swing.JOptionPane;

public class GerenteSucursal extends Usuario{

	public GerenteSucursal(int usuarioId, String nombre, int rol, int sucursalId, String pass, String userName) {
		super(usuarioId, nombre, rol, sucursalId, pass, userName);
	}

	public void Menu(SystemYENNY SystemYENNY) {
		boolean continuar = false;
		String[] opciones = {"Libros", "Editoriales", "Promociones", "Sagas", "Autores",
				"Encargar Libros", "Ver Informe de Venta", "Establecer Beneficios", "Administrar Vendedores"};
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
					String[] libros = SystemYENNY.getAll(SystemYENNY.getLibros());
					
					String librosAux = "";
					
					for (String string : libros) {
						librosAux += "\n" + string;
					}
					
					JOptionPane.showMessageDialog(null, librosAux);
					break;
				case 1:
					String[] editoriales = SystemYENNY.getAll(SystemYENNY.getEditoriales());
					
					String editorialesAux = "";
					
					for (String string : editoriales) {
						editorialesAux += "\n" + string;
					}
					
					JOptionPane.showMessageDialog(null, editorialesAux);
					break;
				case 2:
					String[] promociones = SystemYENNY.getAll(SystemYENNY.getPromociones());
					
					String promocionesAux = "";
					
					for (String string : promociones) {
						promocionesAux += "\n" + string;
					}
					
					JOptionPane.showMessageDialog(null, promocionesAux);
					break;
				case 3:
					String[] sagas= SystemYENNY.getAll(SystemYENNY.getSagas());
					
					String sagasAux = "";
					
					for (String string : sagas) {
						sagasAux += "\n" + string;
					}
					
					JOptionPane.showMessageDialog(null, sagasAux);
					break;
				case 4:
					String[] autores = SystemYENNY.getAll(SystemYENNY.getAutores());
					
					String autoresAux = "";
					
					for (String string : autores) {
						autoresAux += "\n" + string;
					}
					
					JOptionPane.showMessageDialog(null, autoresAux);
					break;
				case 5:
					JOptionPane.showMessageDialog(null, "Aqui el gerente de sucursal selecciona la editorial, el libro"
							+ "\ny cantidad de estos a recibir.");
					break;
				case 6:
					JOptionPane.showMessageDialog(null, "Aqui se le muestran las ventas de libros de la sucursal al gerente de sucursal.");
					break;
				case 7:
					JOptionPane.showMessageDialog(null, "Aqui el gerente de sucursal puede cargar y quitar beneficios de la sucursal.");
					break;
				case 8:
					JOptionPane.showMessageDialog(null, "Aqui el gerente de sucursal puede cargar y quitar vendedores de la sucursal.");
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
