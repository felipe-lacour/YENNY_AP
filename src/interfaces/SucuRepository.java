package interfaces;
import java.util.List;

import modelos.Sucursal;

public interface SucuRepository {
	List <Sucursal> getAllUsers();
	
	Sucursal getUserById(int id);
	
	void addUser(Sucursal user);
	
	void updateUser(Sucursal user);
	
	void deteleUser(int id);
}
