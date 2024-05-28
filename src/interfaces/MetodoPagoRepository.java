package interfaces;
import java.util.List;

import modelos.MetodoPago;
public interface MetodoPagoRepository {
	List <MetodoPago> getAllMethods();
	
	MetodoPago getMethodById(int id);
		
	void addMethod(MetodoPago user);
		
	void updateMethod(MetodoPago user);
		
	void deleteMethod(int id);
}
