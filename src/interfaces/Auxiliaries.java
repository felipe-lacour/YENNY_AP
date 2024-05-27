package interfaces;
import javax.swing.JOptionPane;

public interface Auxiliaries {

	default Boolean verifyStrInput(String aux) {
		if (aux.equals("")) {
			JOptionPane.showMessageDialog(null, "El formato ingresado es invalido, por favor vuelva a intentarlo");
			return false;
		} else {
			for (int i = 0; i < aux.length(); i++) {
				if ((!Character.isAlphabetic(aux.charAt(i))) && (aux.charAt(i) != ' ')) {
					JOptionPane.showMessageDialog(null, "El formato ingresado es invalido, por favor vuelva a intentarlo");
					return false;
				}
			}
		}
		return true;
	}

	default Boolean verifyMailInput(String aux) {
		if ((aux.equals("")) || (aux.charAt(0) == '@') || (aux.charAt(aux.length() - 1) == '@')  || (aux.contains(" "))) {
			JOptionPane.showMessageDialog(null, "El formato ingresado es invalido, por favor vuelva a intentarlo");
			return false;
		} else {
			int cant = 0;
			
			for (int i = 0; i < aux.length(); i++) {
				if (aux.charAt(i) == '@') {
					cant++;
					
					if (cant > 1) {
						JOptionPane.showMessageDialog(null, "El formato ingresado es invalido, por favor vuelva a intentarlo");
						return false;
					}
				}
			}
			if (cant < 1) {
				JOptionPane.showMessageDialog(null, "El formato ingresado es invalido, por favor vuelva a intentarlo");
				return false;
			}
		}
		return true;
	}

	default Boolean verifyDouInput(String aux) {
		int cantPuntos = 0;
		
		if (aux.equals("")) {
			JOptionPane.showMessageDialog(null, "El formato ingresado es invalido, por favor vuelva a intentarlo");
			return false;
		} else {
			for (int i = 0; i < aux.length(); i++) {
				if (!Character.isDigit(aux.charAt(i))) {
					if (aux.charAt(i) == '.') {
						cantPuntos++;
						
						if (cantPuntos > 1) {
							JOptionPane.showMessageDialog(null, "El formato ingresado es invalido, por favor vuelva a intentarlo");
							return false;
						}
					} else {
						JOptionPane.showMessageDialog(null, "El formato ingresado es invalido, por favor vuelva a intentarlo");
						return false;
					}
				}
			}
		}
		return true;
	}
}
