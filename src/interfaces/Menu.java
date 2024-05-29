package interfaces;
import javax.swing.JOptionPane;
import controladores.UsuarioControlador;
import modelos.GerenteGeneral;
import modelos.GerenteSucursal;
import modelos.Usuario;
import modelos.Vendedor;

public interface Menu extends Auxiliaries{

	void Menu();
	
	default Usuario logIn() {
		boolean found = false;
		
		UsuarioControlador controlador = new UsuarioControlador();
		
		do {
			String userName = JOptionPane.showInputDialog("Ingrese su nombre de usuario:");
			
			for (Usuario user : controlador.getAllUsers()) {
				if(user.getUserName().equalsIgnoreCase(userName)) {
					int contador = 0;
					
					do{
						String userPass = JOptionPane.showInputDialog("Ingrese su contraseña:");
						
						if(user.getPass().equals(userPass)) {
							switch (user.getRol()) {
	                        case 1:
	                            return new Vendedor(user.getUsuarioId(), user.getNombre(), user.getRol(), user.getSucursalId(), user.getPass(), user.getUserName());
	                        case 2:
	                        	return new GerenteSucursal(user.getUsuarioId(), user.getNombre(), user.getRol(), user.getSucursalId(), user.getPass(), user.getUserName());
	                        case 3:
	                        	return new GerenteGeneral(user.getUsuarioId(), user.getNombre(), user.getRol(), user.getSucursalId(), user.getPass(), user.getUserName());
	                        default:
	                        	return user;
							}
						} else {
							JOptionPane.showMessageDialog(null, "La contraseña ingresada es incorrecta, por favor vuelva a intentarlo");
							contador++;
						}
					} while(contador != 5);
					break;
				}
			}
			JOptionPane.showMessageDialog(null, "El usuario ingresado no existe, por favor vuelva a intentarlo");
		} while(!found);
		
		return null;
	}
}