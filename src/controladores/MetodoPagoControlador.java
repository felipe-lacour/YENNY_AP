package controladores;
import java.sql.Connection;
import java.util.List;

import interfaces.MetodoPagoRepository;
import modelos.MetodoPago;
public class MetodoPagoControlador implements MetodoPagoRepository{
	private final Connection connection;
	
	public MetodoPagoControlador() {
		this.connection= DatabaseConnection.getInstance().getConnection();
	}

	@Override
	public List<MetodoPago> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetodoPago getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(MetodoPago user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(MetodoPago user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deteleUser(int id) {
		// TODO Auto-generated method stub
		
	}
}
