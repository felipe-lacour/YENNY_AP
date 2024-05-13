package vista;
import java.time.LocalDate;

import modelos.SystemYENNY;
import modelos.Usuario;

public class Main {

	public static void main(String[] args) {
		
		SystemYENNY systemYenny = new SystemYENNY();
		systemYenny.generateData();
		
		Usuario currentUser = systemYenny.logIn();
		
		currentUser.Menu(systemYenny);
		
	}

}
