import java.time.LocalDate;

public class Venta {
	private int ventaId;
	private int metodoPagoId;
	private LocalDate fecha;
	
	public Venta(int ventaId, int metodoPagoId, LocalDate fecha) {
		super();
		this.ventaId = ventaId;
		this.metodoPagoId = metodoPagoId;
		this.fecha = fecha;
	}

	public int getVentaId() {
		return ventaId;
	}

	public int getMetodoPagoId() {
		return metodoPagoId;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setVentaId(int ventaId) {
		this.ventaId = ventaId;
	}

	public void setMetodoPagoId(int metodoPagoId) {
		this.metodoPagoId = metodoPagoId;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
}
