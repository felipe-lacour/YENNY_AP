package modelos;

import java.time.LocalDate;

public class Export {
	private int exportId;
	private int libroId;
	private int cantidad;
	private String destino;
	private LocalDate fecha;
	
	public Export(int exportId, int libroId, int cantidad, String destino, LocalDate fecha) {
		this.exportId = exportId;
		this.libroId = libroId;
		this.cantidad = cantidad;
		this.destino = destino;
		this.fecha = fecha;
	}

	public int getExportId() {
		return exportId;
	}

	public void setExportId(int exportId) {
		this.exportId = exportId;
	}

	public int getLibroId() {
		return libroId;
	}

	public void setLibroId(int libroId) {
		this.libroId = libroId;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}
