package interfaces;

import java.util.List;

import modelos.Editorial;

public interface EditorialRepository {
	List<Editorial> getAllEditorials(); // llama a todos los usuarios de la bdd
    
	Editorial getEditorialById(int id); //llama solo a uno, por su id
    
    void addEditorial(Editorial Editorial); //añade usuarios a la bdd
    
    void updateEditorial(Editorial Editorial); //actualiza los usuarios de la bdd
    
    void deleteEditorial(int id); //eliminar usuarios de la bdd
}
