package interfaces;

import java.util.List;

import modelos.PromocionLibro;

public interface PromoLibroRepository {
List <PromocionLibro> getAllUsers();
	
PromocionLibro getUserById(int id);
	
	void addUser(PromocionLibro user);
	
	void updateUser(PromocionLibro user);
	
	void deteleUser(int id);
 

}
