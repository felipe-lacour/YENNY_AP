package controladores;
import interfaces.PromocionRepository;
public class PromocionControlador implements PromocionRepository{
	private final Connection connection;
	
	public PromocionControlador = DatabaseConnection.getInstance().getConnection();
}
