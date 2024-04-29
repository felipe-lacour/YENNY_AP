
public class Autor {
	private int autorId;
	private String nombre;
	private String nacionalidad;
	
	public Autor(int autorId, String nombre, String nacionalidad) {
		super();
		this.autorId = autorId;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	public int getAutorId() {
		return autorId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
	
	
}
