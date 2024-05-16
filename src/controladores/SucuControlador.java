package controladores;
import interfaces.SucuRepository;
public class SucuControlador implements SucuRepository{
	private final Connection connection;
	
	public SucuControlador = DatabaseConnection.getInstance().getConnection();
	
}
