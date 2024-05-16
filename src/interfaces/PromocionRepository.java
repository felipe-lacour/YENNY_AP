package interfaces;
import java.util.List;

import modelos.Promocion;
public interface PromocionRepository {
	List <Promocion> getAllUsers();
	
	Promocion getUserById(int id);
		
		void addUser(Promocion user);
		
		void updateUser(Promocion user);
		
		void deteleUser(int id);
}
