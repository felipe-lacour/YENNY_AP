package interfaces;
import modelos.Autor;
import java.util.List;

public interface AutorRepository {
	List<Autor> getAllUsers(); // llama a todos los usuarios de la bdd
    
	Autor getUserById(int id); //llama solo a uno, por su id
    
    void addUser(Autor user); //añade usuarios a la bdd
    
    void updateUser(Autor user); //actualiza los usuarios de la bdd
    
    void deleteUser(int id); //eliminar usuarios de la bdd
}
