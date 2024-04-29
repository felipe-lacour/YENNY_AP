import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		
		SystemYENNY systemYenny = new SystemYENNY();
		systemYenny.generateData();
		
		Usuario currentUser = systemYenny.logIn();
		
		currentUser.Menu(systemYenny);
		
	}

}
