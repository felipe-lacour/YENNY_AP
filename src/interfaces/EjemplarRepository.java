package interfaces;

import java.util.List;
import modelos.Ejemplar;

public interface EjemplarRepository {
	List<Ejemplar> getAllEjemplar(); // llama a todos los ejemplares de la bdd
    
	Ejemplar getEjemplarById(int id); //llama solo a uno, por su id
    
    void addEjemplar(Ejemplar Ejemplar); //añade ejemplares a la bdd
    
    void updateEjemplar(Ejemplar Ejemplar); //actualiza los ejemplares de la bdd
    
    void deleteEjemplar(int id); //eliminar ejemplares de la bdd
}