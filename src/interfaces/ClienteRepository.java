package interfaces;
import modelos.Cliente;
import java.util.List;


public interface ClienteRepository {
	List<Cliente> getAllUsers(); // llama a todos los usuarios de la bdd
    
	Cliente getUserById(int id); //llama solo a uno, por su id
    
    void addUser(Cliente Cliente); //añade usuarios a la bdd
    
    void updateUser(Cliente Cliente); //actualiza los usuarios de la bdd
    
    void deleteUser(int id); //eliminar usuarios de la bdd
}
