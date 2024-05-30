package interfaces;
import modelos.Cliente;
import java.util.List;


public interface ClienteRepository {
	List<Cliente> getAllClientes(); // llama a todos los clientes de la bdd
    
	Cliente getClienteById(int id); //llama solo a uno, por su id
    
    int addCliente(Cliente Cliente); //añade clientes a la bdd
    
    void updateCliente(Cliente Cliente); //actualiza los clientes de la bdd
    
    void deleteCliente(int id); //eliminar clientes de la bdd
}
