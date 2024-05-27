package modelos;
import javax.swing.JOptionPane;

public class Vendedor extends Usuario implements interfaces.Menu{

	public Vendedor(int usuarioId, String nombre, int rol, int sucursalId, String pass, String userName) {
		super(usuarioId, nombre, rol, sucursalId, pass, userName);
	}

	@Override
	public void Menu() {
		boolean continuar = false;
		String[] opciones = {"Ejemplares", "Clientes", "Metodos de Pago", "Registrar al Club del Libro", "Registrar Cliente", 
				"Realizar Venta", "Pedir Libro Faltante"};
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
					String[] ejemplares = SystemYENNY.getAll(SystemYENNY.getEjemplares());
					
					String ejemplaresAux = "";
					
					for (String string : ejemplares) {
						ejemplaresAux += "\n" + string;
					}
					
					JOptionPane.showMessageDialog(null, ejemplaresAux);
					break;
				case 1:
					String[] clientes = SystemYENNY.getAll(SystemYENNY.getClientes());
					
					String clientesAux = "";
					
					for (String string : clientes) {
						clientesAux += "\n" + string;
					}
					
					JOptionPane.showMessageDialog(null, clientesAux);
					break;
				case 2:
					String[] metodosPago = SystemYENNY.getAll(SystemYENNY.getMetodosPago());
					
					String metodosPagoAux = "";
					
					for (String string : metodosPago) {
						metodosPagoAux += "\n" + string;
					}
					
					JOptionPane.showMessageDialog(null, metodosPagoAux);
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "Aqui el vendedor puede registrar a un cliente al club del libro."
							+ "\nPidiendo codigo de cliente y cargando o seleccionando un metodo de pago.");
					break;
				case 4:
					JOptionPane.showMessageDialog(null, "Aqui el vendedor puede registrar a un cliente pidiendo la informacion necesaria.");
					break;
				case 5:
					JOptionPane.showMessageDialog(null, "Aqui el vendedor puede cargar una venta, pidiendo un metodo de pago "
							+ "\no cargando codigo cliente y seleccionando un metodo de pago.");
					break;
				case 6:
					JOptionPane.showMessageDialog(null, "Aqui el vendedor puede buscar libros faltantes en sucursales cercanas y pedir los mismos.\nDebe tener la informacion de contacto del cliente.");
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
