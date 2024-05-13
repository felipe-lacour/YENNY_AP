package controladores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import interfaces.AutorRepository;
import modelos.Autor;

public class AutorControlador implements AutorRepository {
	
 private final Connection connection;

	   public AutorControlador() {
	        this.connection = DatabaseConnection.getInstance().getConnection();
	    }

	    @Override
	    public List<Autor> getAllUsers() {
	        List<Autor> users = new ArrayList<>();
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM autor ");
	            ResultSet resultSet = statement.executeQuery();
	       
	            while (resultSet.next()) {
	            	Autor user = new Autor(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("nacionalidad"));
	                users.add(user);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return users;
	    }

	    @Override
	    public Autor getUserById(int id) {
	    	Autor user = null;
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM autor WHERE id = ?");
	            statement.setInt(1, id);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            if (resultSet.next()) {
	                user = new Autor(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("nacionalidad"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return user;
	    }
	    
		@Override
	    public void addUser(Autor Autor) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("INSERT INTO autor (name, nacionalidad) VALUES (?, ?)");
	            statement.setString(1, Autor.getNombre());
	            statement.setString(2, Autor.getNacionalidad());
	            
	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Autor insertado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

		@Override
	    public void updateUser(Autor Autor) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("UPDATE autor SET name = ?, email = ? WHERE id = ?");
	            statement.setString(1, Autor.getNombre());
	            statement.setString(2, Autor.getNacionalidad());
	            statement.setInt(3, Autor.getAutorId());
	            
	            int rowsUpdated = statement.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("Autor actualizado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void deleteUser(int id) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM autor WHERE id = ?");
	            statement.setInt(1, id);
	            
	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Autor eliminado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


	  
	}


