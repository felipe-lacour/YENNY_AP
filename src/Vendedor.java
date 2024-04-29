import javax.swing.JOptionPane;

public class Vendedor extends Usuario {

	public Vendedor(int usuarioId, String nombre, int rol, int sucursalId, String pass, String userName) {
		super(usuarioId, nombre, rol, sucursalId, pass, userName);
	}

	public void Menu(SystemYENNY SystemYENNY) {
		boolean continuar = false;
		String[] opciones = {"Ejemplares", "Clientes", "Metodos de Pago"};
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
