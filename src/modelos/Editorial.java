package modelos;

public class Editorial {
	private int editorialId;
	private String nombre;
	
	public Editorial(int editorialId, String nombre) {
		super();
		this.editorialId = editorialId;
		this.nombre = nombre;
	}

	public int getEditorialId() {
		return editorialId;
	}

	public void setEditorialId(int editorialId) {
		this.editorialId = editorialId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
    @Override
    public String toString() {
        return nombre; // Return the display name
    }
}
