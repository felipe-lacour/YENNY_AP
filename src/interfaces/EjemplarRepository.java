package interfaces;

import java.util.List;

import modelos.Ejemplar;

public interface EjemplarRepository {
	List<Ejemplar> getAllUsers(); // llama a todos los usuarios de la bdd
    
	Ejemplar getUserById(int id); //llama solo a uno, por su id
    
    void addUser(Ejemplar Ejemplar); //añade usuarios a la bdd
    
    void updateUser(Ejemplar Ejemplar); //actualiza los usuarios de la bdd
    
    void deleteUser(int id); //eliminar usuarios de la bdd
}