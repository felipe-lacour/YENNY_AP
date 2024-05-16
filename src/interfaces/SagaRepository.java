package interfaces;
import java.util.List;

import modelos.Saga;

public interface SagaRepository {
List <Saga> getAllUsers();
	
Saga getUserById(int id);
	
	void addUser(Saga user);
	
	void updateUser(Saga user);
	
	void deteleUser(int id);
}
