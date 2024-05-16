package controladores;
import interfaces.SagaRepository;
public class SagaControlador implements SagaRepository{
	private final Connection connection;
	
	public SagaControlador = DatabaseConnection.getInstance().getConnection();
}
