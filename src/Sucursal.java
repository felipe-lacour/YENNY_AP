
public class Sucursal {
	private int sucursalId;
	private String ubicacion;
	private String nombre;
	
	public Sucursal(int sucursalId, String ubicacion, String nombre) {
		super();
		this.sucursalId = sucursalId;
		this.ubicacion = ubicacion;
		this.nombre = nombre;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
