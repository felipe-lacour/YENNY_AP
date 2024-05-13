package interfaces;
import javax.swing.JOptionPane;

public interface Auxiliaries {
	
	default String checker(boolean isNumber, String message, String title, String secondMessage) {
		boolean checked = true;
		String string = (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.DEFAULT_OPTION, null, null, "");
		
		do {
			checked = true;
			if(!isNumber) {
				for (int i = 0; i < string.length(); i++) {
					if(!Character.isAlphabetic(string.charAt(i)) && string.charAt(i) != ' ') {
						checked = false;
					}
				}
			} else {
				for (int i = 0; i < string.length(); i++) {
					if(!Character.isDigit(string.charAt(i)) && string.charAt(i) != '.') {
						checked = false;
					}
				}
			}
			
		    if (!checked || string.length() == 0 || string == null) {
		        checked = false;
		        string = (String) JOptionPane.showInputDialog(null, secondMessage, title, JOptionPane.DEFAULT_OPTION, null, null, "");
		    }
			
		} while (!checked);
		
		return string;
	}
}
