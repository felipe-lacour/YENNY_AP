package modelos;

public class Libro {
	private int libroId;
	private String titulo;
	private Integer sagaId;
	private Integer editorialId;
	private Integer autorId;
	private byte[] img;
	
	public Libro(int libroId, String titulo, Integer sagaId, int editorialId, int autorId, byte[] img) {
		this.libroId = libroId;
		this.titulo = titulo;
		this.sagaId = sagaId;
		this.editorialId = editorialId;
		this.autorId = autorId;
		this.img = img;
	}

	public int getLibroId() {
		return libroId;
	}

	public String getTitulo() {
		return titulo;
	}

	public Integer getSagaId() {
		return sagaId;
	}

	public int getEditorialId() {
		return editorialId;
	}

	public int getAutorId() {
		return autorId;
	}

	public void setLibroId(int libroId) {
		this.libroId = libroId;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setSagaId(Integer sagaId) {
		this.sagaId = sagaId;
	}

	public void setEditorialId(int editorialId) {
		this.editorialId = editorialId;
	}

	public void setAutorId(int autorId) {
		this.autorId = autorId;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
}

