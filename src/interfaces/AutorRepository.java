package interfaces;
import modelos.Autor;
import java.util.List;

public interface AutorRepository {
	List<Autor> getAllAutors(); // llama a todos los usuarios de la bdd
    
	Autor getAutorById(int id); //llama solo a uno, por su id
    
    void addAutor(Autor Autor); //añade usuarios a la bdd
    
    void updateAutor(Autor Autor); //actualiza los usuarios de la bdd
    
    void deleteAutor(int id); //eliminar usuarios de la bdd
}
