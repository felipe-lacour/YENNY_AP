
public class Cliente {
	private int clienteId;
	private String genero;
	private int edad;
	private int lugar_de_compra;
	private boolean clubLibros;
	
	public Cliente(int clienteId, String genero, int edad, int lugar_de_compra, boolean clubLibros) {
		super();
		this.clienteId = clienteId;
		this.genero = genero;
		this.edad = edad;
		this.lugar_de_compra = lugar_de_compra;
		this.clubLibros = clubLibros;
	}

	public int getClienteId() {
		return clienteId;
	}

	public String getGenero() {
		return genero;
	}

	public int getEdad() {
		return edad;
	}

	public int getLugar_de_compra() {
		return lugar_de_compra;
	}

	public boolean isClubLibros() {
		return clubLibros;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setLugar_de_compra(int lugar_de_compra) {
		this.lugar_de_compra = lugar_de_compra;
	}

	public void setClubLibros(boolean clubLibros) {
		this.clubLibros = clubLibros;
	}
	
	
}
