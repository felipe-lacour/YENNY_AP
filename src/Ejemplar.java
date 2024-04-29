import java.time.LocalDate;

public class Ejemplar {
	private int ejemplarId;
	private int libroId;
	private int sucursalId;
	private String isbn;
	private double precio;
	private String condicion;
	private boolean tapaDura;
	private boolean edicionEspecial;
	private LocalDate fechaEdicion;
	private int numeroEdicion;
	private boolean firmado;
	private String idioma;
	private String caracteristicasEspeciales;
	private LocalDate fechaAdquisicion;
	private Integer ventaId;
	
	public Ejemplar(int ejemplarId, int libroId, int sucursalId, String isbn, double precio, String condicion,
			boolean tapaDura, boolean edicionEspecial, LocalDate fechaEdicion, int numeroEdicion, boolean firmado,
			String idioma, String caracteristicasEspeciales, LocalDate fechaAdquisicion, Integer ventaId) {
		super();
		this.ejemplarId = ejemplarId;
		this.libroId = libroId;
		this.sucursalId = sucursalId;
		this.isbn = isbn;
		this.precio = precio;
		this.condicion = condicion;
		this.tapaDura = tapaDura;
		this.edicionEspecial = edicionEspecial;
		this.fechaEdicion = fechaEdicion;
		this.numeroEdicion = numeroEdicion;
		this.firmado = firmado;
		this.idioma = idioma;
		this.caracteristicasEspeciales = caracteristicasEspeciales;
		this.fechaAdquisicion = fechaAdquisicion;
		this.ventaId = ventaId;
	}

	public int getEjemplarId() {
		return ejemplarId;
	}

	public int getLibroId() {
		return libroId;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public String getIsbn() {
		return isbn;
	}

	public double getPrecio() {
		return precio;
	}

	public String getCondicion() {
		return condicion;
	}

	public boolean isTapaDura() {
		return tapaDura;
	}

	public boolean isEdicionEspecial() {
		return edicionEspecial;
	}

	public LocalDate getFechaEdicion() {
		return fechaEdicion;
	}

	public int getNumeroEdicion() {
		return numeroEdicion;
	}

	public boolean isFirmado() {
		return firmado;
	}

	public String getIdioma() {
		return idioma;
	}

	public String getCaracteristicasEspeciales() {
		return caracteristicasEspeciales;
	}

	public LocalDate getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public Integer getVentaId() {
		return ventaId;
	}

	public void setEjemplarId(int ejemplarId) {
		this.ejemplarId = ejemplarId;
	}

	public void setLibroId(int libroId) {
		this.libroId = libroId;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public void setTapaDura(boolean tapaDura) {
		this.tapaDura = tapaDura;
	}

	public void setEdicionEspecial(boolean edicionEspecial) {
		this.edicionEspecial = edicionEspecial;
	}

	public void setFechaEdicion(LocalDate fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public void setNumeroEdicion(int numeroEdicion) {
		this.numeroEdicion = numeroEdicion;
	}

	public void setFirmado(boolean firmado) {
		this.firmado = firmado;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public void setCaracteristicasEspeciales(String caracteristicasEspeciales) {
		this.caracteristicasEspeciales = caracteristicasEspeciales;
	}

	public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public void setVentaId(Integer ventaId) {
		this.ventaId = ventaId;
	}
	
	
}
