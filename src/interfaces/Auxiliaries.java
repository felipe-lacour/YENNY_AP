package interfaces;

public interface Auxiliaries {

	default Boolean verifyStrInput(String aux) {
		if (aux.equals("")) {
			return false;
		} else {
			for (int i = 0; i < aux.length(); i++) {
				if ((!Character.isAlphabetic(aux.charAt(i))) && (aux.charAt(i) != ' ')) {
					return false;
				}
			}
		}
		return true;
	}
	
	default Boolean verifyIntInput(String aux) {
		if (aux.equals("")) {
			return false;
		} else {
			for (int i = 0; i < aux.length(); i++) {
				if (!Character.isDigit(aux.charAt(i))) {
					return false;
				}
			}
		}
		return true;
	}

	default Boolean verifyDouInput(String aux) {
		int cantPuntos = 0;
		
		if (aux.equals("")) {
			return false;
		} else {
			for (int i = 0; i < aux.length(); i++) {
				if (!Character.isDigit(aux.charAt(i))) {
					if (aux.charAt(i) == '.') {
						cantPuntos++;
						
						if (cantPuntos > 1) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}
}
