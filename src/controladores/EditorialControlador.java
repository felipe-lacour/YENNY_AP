package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import interfaces.EditorialRepository;
import modelos.Editorial;

public class EditorialControlador implements EditorialRepository {
	
 private final Connection connection;

	   public EditorialControlador() {
	        this.connection = DatabaseConnection.getInstance().getConnection();
	    }

	    @Override
	    public List<Editorial> getAllEditorials() {
	        List<Editorial> Editorials = new ArrayList<>();
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM editoriales ");
	            ResultSet resultSet = statement.executeQuery();
	       
	            while (resultSet.next()) {
	            	Editorial Editorial = new Editorial(resultSet.getInt("editorial_id"), resultSet.getString("name"));
	                Editorials.add(Editorial);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return Editorials;
	    }

	    @Override
	    public Editorial getEditorialById(int id) {
	    	Editorial Editorial = null;
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM editoriales WHERE id = ?");
	            statement.setInt(1, id);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            if (resultSet.next()) {
	                Editorial = new Editorial(resultSet.getInt("id"), resultSet.getString("name"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return Editorial;
	    }
	    
		@Override
	    public void addEditorial(Editorial Editorial) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("INSERT INTO editoriales (name) VALUES (?, ?)");
	            statement.setString(1, Editorial.getNombre());
	            
	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Editorial insertado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

		@Override
	    public void updateEditorial(Editorial Editorial) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("UPDATE editoriales SET name = ?, email = ? WHERE id = ?");
	            statement.setString(1, Editorial.getNombre());
	            statement.setInt(2, Editorial.getEditorialId());
	            
	            int rowsUpdated = statement.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("Editorial actualizado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void deleteEditorial(int id) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM editoriales WHERE id = ?");
	            statement.setInt(1, id);
	            
	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Editorial eliminado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


	  
	}


