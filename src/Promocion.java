
public class Promocion {
	private int promocionId;
	private String nombre;
	private boolean esDelClub;
	private Integer sucursalId;
	private double descuento;
	
	public Promocion(int promocionId, String nombre, boolean esDelClub, Integer sucursalId, double descuento) {
		super();
		this.promocionId = promocionId;
		this.nombre = nombre;
		this.esDelClub = esDelClub;
		this.sucursalId = sucursalId;
		this.descuento = descuento;
	}

	public int getPromocionId() {
		return promocionId;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean isEsDelClub() {
		return esDelClub;
	}

	public Integer getSucursalId() {
		return sucursalId;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setPromocionId(int promocionId) {
		this.promocionId = promocionId;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEsDelClub(boolean esDelClub) {
		this.esDelClub = esDelClub;
	}

	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	
	
}
