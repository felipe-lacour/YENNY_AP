package interfaces;

import java.util.List;

import modelos.Editorial;

public interface EditorialRepository {
	List<Editorial> getAllUsers(); // llama a todos los usuarios de la bdd
    
	Editorial getUserById(int id); //llama solo a uno, por su id
    
    void addUser(Editorial Editorial); //añade usuarios a la bdd
    
    void updateUser(Editorial Editorial); //actualiza los usuarios de la bdd
    
    void deleteUser(int id); //eliminar usuarios de la bdd
}
