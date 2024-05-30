package modelos;
import java.time.LocalDate;

public class Venta {
    private Integer ventaId;
    private Integer metodoPagoId;
    private LocalDate fecha;
    private int sucursalId;
    
    public Venta(int ventaId, int metodoPagoId, LocalDate fecha, int sucursalId) {
        super();
        this.ventaId = ventaId;
        this.metodoPagoId = metodoPagoId;
        this.fecha = fecha;
        this.sucursalId = sucursalId;
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

	public int getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}
    
    
}
