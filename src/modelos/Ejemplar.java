package modelos;
import java.time.LocalDate;

public class Ejemplar {
	private int ejemplarId;
	private int libroId;
	private int sucursalId;
	private String isbn;
	private double precio;
	private boolean tapaDura;
	private boolean edicionEspecial;
	private LocalDate fechaEdicion;
	private int numeroEdicion;
	private boolean firmado;
	private String idioma;
	private LocalDate fechaAdquisicion;
	private Integer ventaId;
	
	public Ejemplar(int ejemplarId, int libroId, int sucursalId, String isbn, double precio,
			boolean tapaDura, boolean edicionEspecial, LocalDate fechaEdicion, int numeroEdicion, boolean firmado,
			String idioma, LocalDate fechaAdquisicion, Integer ventaId) {
		super();
		this.ejemplarId = ejemplarId;
		this.libroId = libroId;
		this.sucursalId = sucursalId;
		this.isbn = isbn;
		this.precio = precio;
		this.tapaDura = tapaDura;
		this.edicionEspecial = edicionEspecial;
		this.fechaEdicion = fechaEdicion;
		this.numeroEdicion = numeroEdicion;
		this.firmado = firmado;
		this.idioma = idioma;
		this.fechaAdquisicion = fechaAdquisicion;
		this.ventaId = ventaId;
	}

	public int getEjemplarId() {
		return ejemplarId;
	}

	public void setEjemplarId(int ejemplarId) {
		this.ejemplarId = ejemplarId;
	}

	public int getLibroId() {
		return libroId;
	}

	public void setLibroId(int libroId) {
		this.libroId = libroId;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isTapaDura() {
		return tapaDura;
	}

	public void setTapaDura(boolean tapaDura) {
		this.tapaDura = tapaDura;
	}

	public boolean isEdicionEspecial() {
		return edicionEspecial;
	}

	public void setEdicionEspecial(boolean edicionEspecial) {
		this.edicionEspecial = edicionEspecial;
	}

	public LocalDate getFechaEdicion() {
		return fechaEdicion;
	}

	public void setFechaEdicion(LocalDate fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public int getNumeroEdicion() {
		return numeroEdicion;
	}

	public void setNumeroEdicion(int numeroEdicion) {
		this.numeroEdicion = numeroEdicion;
	}

	public boolean isFirmado() {
		return firmado;
	}

	public void setFirmado(boolean firmado) {
		this.firmado = firmado;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public LocalDate getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public Integer getVentaId() {
		return ventaId;
	}

	public void setVentaId(Integer ventaId) {
		this.ventaId = ventaId;
	}
}
