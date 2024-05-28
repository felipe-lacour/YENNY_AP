package controladores;

import interfaces.LibroRepositorio;
import modelos.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroControlador implements LibroRepositorio {
    private final Connection connection;

    public LibroControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Libro> getAllBooks() {
        List<Libro> books = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM libros ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	Libro book = new Libro(resultSet.getInt("libro_id"), resultSet.getString("titulo"), resultSet.getInt("saga_id"), resultSet.getInt("editorial_id"), resultSet.getInt("autor_id"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Libro getBookById(int id) {
        Libro book = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM libros WHERE libro_id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                book = new Libro(resultSet.getInt("libro_id"), resultSet.getString("titulo"), resultSet.getInt("saga_id"), resultSet.getInt("editorial_id"), resultSet.getInt("autor_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
    
	@Override
    public void addBook(Libro libro) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO libros (titulo, saga_id, editorial_id, autor_id) VALUES (?, ?, ?, ?)");
            statement.setString(1, libro.getTitulo());
            statement.setInt(2, libro.getSagaId());
            statement.setInt(3, libro.getEditorialId());
            statement.setInt(4, libro.getAutorId());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Libro insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
    public void updateBook(Libro libro) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE libros SET titulo = ?, saga_id = ?, editorial_id = ?, autor_id = ? WHERE libro_id = ?");
            statement.setString(1, libro.getTitulo());
            statement.setInt(2, libro.getSagaId());
            statement.setInt(3, libro.getEditorialId());
            statement.setInt(4, libro.getAutorId());
            statement.setInt(5, libro.getLibroId());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Libro actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM libros WHERE libro_id = ?");
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Libro eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}