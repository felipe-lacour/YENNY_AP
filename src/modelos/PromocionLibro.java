package modelos;

public class PromocionLibro {
	private int promocionId;
	private int libroId;
	
	
	public PromocionLibro(int promocionId, int libroId) {
		super();
		this.promocionId = promocionId;
		this.libroId = libroId;
	}


	public int getPromocionId() {
		return promocionId;
	}


	public int getLibroId() {
		return libroId;
	}


	public void setPromocionId(int promocionId) {
		this.promocionId = promocionId;
	}


	public void setLibroId(int libroId) {
		this.libroId = libroId;
	}
}
