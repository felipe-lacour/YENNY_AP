package interfaces;

import java.util.List;

import modelos.Editorial;

public interface EditorialRepository {
	List<Editorial> getAllEditorials(); // llama a todos las editoriales de la bdd
    
	Editorial getEditorialById(int id); //llama solo a una, por su id
    
    void addEditorial(Editorial Editorial); //añade editoriales a la bdd
    
    void updateEditorial(Editorial Editorial); //actualiza las editoriales de la bdd
    
    void deleteEditorial(int id); //eliminar editoriales de la bdd
}
