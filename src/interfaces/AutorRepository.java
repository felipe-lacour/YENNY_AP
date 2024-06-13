package interfaces;
import modelos.Autor;
import java.util.List;

public interface AutorRepository {
	List<Autor> getAllAutors(); // llama a todos los autores de la bdd
    
	Autor getAutorById(int id); //llama solo a uno, por su id
    
    void addAutor(Autor Autor); //añade autores a la bdd
    
    void updateAutor(Autor Autor); //actualiza los autores de la bdd
    
    void deleteAutor(int id); //eliminar autores de la bdd
}
