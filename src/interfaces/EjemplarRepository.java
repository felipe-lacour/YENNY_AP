package interfaces;

import java.util.List;

import modelos.Ejemplar;

public interface EjemplarRepository {
	List<Ejemplar> getAllEjemplar(); // llama a todos los usuarios de la bdd
    
	Ejemplar getEjemplarById(int id); //llama solo a uno, por su id
    
    void addEjemplar(Ejemplar Ejemplar); //añade usuarios a la bdd
    
    void updateEjemplar(Ejemplar Ejemplar); //actualiza los usuarios de la bdd
    
    void deleteEjemplar(int id); //eliminar usuarios de la bdd
}