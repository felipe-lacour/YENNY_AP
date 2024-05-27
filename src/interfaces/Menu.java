package interfaces;

import javax.swing.JOptionPane;
import modelos.Usuario;

public interface Menu extends Auxiliaries{

	void Menu();
	
	default Usuario logIn() {
		Usuario userNAux = null, userPAux = null, userAux = null;
		boolean userN = true;
		boolean userP = true;
		
		do {
			
			String userName = JOptionPane.showInputDialog("Ingrese su nombre de usuario.");
			
			for (Usuario user : usuarios) {
				if(user.getUserName().equals(userName)) {
					userN = true;
					userNAux = user;
					break;
				} else {
					userN = false;
				}
			}
			
		} while(!userN);
		
		do {
			
			String userPass = JOptionPane.showInputDialog("Ingrese su contraseña.");
			
			for (Usuario user : usuarios) {
				if(user.getPass().equals(userPass)) {
					userP = true;
					userPAux = user;
					break;
				} else {
					userP = false;
				}
			}
			
			if(userNAux == userPAux) {
				userP = true;
				userAux = userNAux;
			} else {
				userP = false;
			}
			
		} while(!userP);
		
		return userAux;
	}
	
	
}