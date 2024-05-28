package modelos;
import javax.swing.JOptionPane;

import interfaces.Menu;

public class Usuario implements Menu{
	private int usuarioId;
	private String nombre;
	private int rol;
	private int sucursalId;
	private String pass;
	private String userName;
	
	public Usuario(int usuarioId, String nombre, int rol, int sucursalId, String pass, String userName) {
		super();
		this.usuarioId = usuarioId;
		this.nombre = nombre;
		this.rol = rol;
		this.sucursalId = sucursalId;
		this.pass = pass;
		this.userName = userName;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public String getNombre() {
		return nombre;
	}

	public int getRol() {
		return rol;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public String getPass() {
		return pass;
	}

	public String getUserName() {
		return userName;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public void Menu() {
		JOptionPane.showMessageDialog(null, "Si estas viendo esto algo salio muy mal...");
		
	}
}
