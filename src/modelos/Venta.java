package modelos;
import java.time.LocalDate;

import controladores.PromocionControlador;
import controladores.VentaControlador;

public class Venta {
	private int ventaId;
	private int metodoPagoId;
	private LocalDate fecha;
	private VentaControlador controlVenta;
	private PromocionControlador controlPromocion;
	
	public Venta(int ventaId, int metodoPagoId, LocalDate fecha) {
		super();
		this.ventaId = ventaId;
		this.metodoPagoId = metodoPagoId;
		this.fecha = fecha;
		this.controlVenta = new VentaControlador();
		this.controlPromocion = new PromocionControlador();
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

