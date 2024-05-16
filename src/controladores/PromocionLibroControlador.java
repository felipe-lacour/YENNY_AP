package controladores;
import interfaces.PromoLibroRepository;
public class PromocionLibroControlador implements PromoLibroRepository{
	private final Connection connection;
	
	public PromocionLibroControlador = DatabaseConnection.getInstance().getConnection();
}
