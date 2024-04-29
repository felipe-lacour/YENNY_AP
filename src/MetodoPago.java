
public class MetodoPago {
	private int metodoPagoId;
	private int clienteId;
	private String tipo;
	private String detalles;
	
	public MetodoPago(int metodoPagoId, int clienteId, String tipo, String detalles) {
		super();
		this.metodoPagoId = metodoPagoId;
		this.clienteId = clienteId;
		this.tipo = tipo;
		this.detalles = detalles;
	}

	public int getMetodoPagoId() {
		return metodoPagoId;
	}

	public int getClienteId() {
		return clienteId;
	}

	public String getTipo() {
		return tipo;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setMetodoPagoId(int metodoPagoId) {
		this.metodoPagoId = metodoPagoId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
}
