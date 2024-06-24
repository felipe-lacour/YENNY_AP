package modelos;

import javax.swing.JOptionPane;

import interfaces.Menu;
import vista.AddSale;
import vista.ViewClients;
import vista.ViewSpecimens;

public class Vendedor extends Usuario implements Menu{

	public Vendedor(int usuarioId, String nombre, int rol, int sucursalId, String pass, String userName) {
		super(usuarioId, nombre, rol, sucursalId, pass, userName);
	}

	@Override
	public void Menu() {
		int eleccion = 0;
		String[] opciones = {"Ver ejemplares", "Administrar Clientes", 
							 "Realizar Venta", "Salir"};
		do {
			eleccion = JOptionPane.showOptionDialog(null, "¿Que operacion desea realizar?", "Elija por favor", 0, 0, null, opciones, opciones);
			
			switch(eleccion) {
				case 0:
					ViewSpecimens frame2 = new ViewSpecimens(this);
					frame2.setVisible(true);
					break;
				case 1:
                    ViewClients frame = new ViewClients(this);
                    frame.setVisible(true);
					break;
				case 2:
                    AddSale frame1 = new AddSale(this);
                    frame1.setVisible(true);
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "Nos re vimos!");
					return;
				default:
					JOptionPane.showMessageDialog(null, "Re ilegal!");
					break;
			}
		} while(eleccion != 3);
	}
}