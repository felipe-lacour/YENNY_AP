package interfaces;
import java.util.List;

import modelos.MetodoPago;
public interface MetodoPagoRepository {
List <MetodoPago> getAllUsers();
	
MetodoPago getUserById(int id);
		
		void addUser(MetodoPago user);
		
		void updateUser(MetodoPago user);
		
		void deteleUser(int id);
}
