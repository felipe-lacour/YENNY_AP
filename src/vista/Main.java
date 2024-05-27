package vista;
import modelos.Usuario;

public class Main{

	public static void main(String[] args) {
		Vista nueva = new Vista();
		Usuario usuarioActivo = nueva.logIn();
		usuarioActivo.Menu();
	}

}
