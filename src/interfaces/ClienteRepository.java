package interfaces;
import modelos.Cliente;
import java.util.List;


public interface ClienteRepository {
	List<Cliente> getAllClientes(); // llama a todos los usuarios de la bdd
    
	Cliente getClienteById(int id); //llama solo a uno, por su id
    
    void addCliente(Cliente Cliente); //añade usuarios a la bdd
    
    void updateCliente(Cliente Cliente); //actualiza los usuarios de la bdd
    
    void deleteCliente(int id); //eliminar usuarios de la bdd
}
