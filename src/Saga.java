
public class Saga {
	private int sagaId;
	private String nombre;
	private int numeroLibros;
	private int numeroSaga;
	
	public Saga(int sagaId, String nombre, int numeroLibros, int numeroSaga) {
		super();
		this.sagaId = sagaId;
		this.nombre = nombre;
		this.numeroLibros = numeroLibros;
		this.numeroSaga = numeroSaga;
	}

	public int getSagaId() {
		return sagaId;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNumeroLibros() {
		return numeroLibros;
	}

	public int getNumeroSaga() {
		return numeroSaga;
	}

	public void setSagaId(int sagaId) {
		this.sagaId = sagaId;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNumeroLibros(int numeroLibros) {
		this.numeroLibros = numeroLibros;
	}

	public void setNumeroSaga(int numeroSaga) {
		this.numeroSaga = numeroSaga;
	}
	
}
