package interfaces;
import java.util.List;

import modelos.Saga;

public interface SagaRepository {
	List <Saga> getAllSagas();
	
	Saga getSagaById(int id);
		
	void addSaga(Saga user);
	
	void updateSaga(Saga user);
	
	void deteleSaga(int id);
}
