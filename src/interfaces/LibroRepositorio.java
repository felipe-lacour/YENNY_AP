package interfaces;

import java.util.List;
import modelos.Libro;

public interface LibroRepositorio {
	
    List<Libro> getAllBooks(); // llama a todos los usuarios de la bdd
    
    Libro getBookById(int id); //llama solo a uno, por su id
    
    void addBook(Libro book); //añade usuarios a la bdd
    
    void updateBook(Libro book); //actualiza los usuarios de la bdd
    
    void deleteBook(int id); //eliminar usuarios de la bdd
}