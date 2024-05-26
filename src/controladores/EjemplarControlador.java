package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import interfaces.EjemplarRepository;
import modelos.Ejemplar;

public class EjemplarControlador implements EjemplarRepository {
	
 private final Connection connection;

	   public EjemplarControlador() {
	        this.connection = DatabaseConnection.getInstance().getConnection();
	    }

	    @Override
	    public List<Ejemplar> getAllUsers() {
	        List<Ejemplar> users = new ArrayList<>();
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ejemplar ");
	            ResultSet resultSet = statement.executeQuery();
	       
	            while (resultSet.next()) {
				
	            	Ejemplar user = new Ejemplar(resultSet.getInt("ejemplar_id"),resultSet.getInt("libro_id"), resultSet.getInt("sucursal_id"),
	            			resultSet.getString("isbn"),resultSet.getDouble("precio"),resultSet.getString("condicion"),resultSet.getBoolean("tapa_dura"),
	            			resultSet.getBoolean("edicion_esecial"),resultSet.getDate("fecha_edicion").toLocalDate(),resultSet.getInt("numero_edicion"),
	            			resultSet.getBoolean("firmado"),resultSet.getString("idioma"),resultSet.getString("caracteristicas_especiales"),
	            			resultSet.getDate("fecha_adquisicion").toLocalDate(),resultSet.getInt("venta_iD"));
	                users.add(user);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return users;
	    }

	    @Override
	    public Ejemplar getUserById(int id) {
	    	Ejemplar user = null;
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM autor WHERE id = ?");
	            statement.setInt(1, id);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            if (resultSet.next()) {
	                user = new Ejemplar(resultSet.getInt("ejemplar_id"),resultSet.getInt("libro_id"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return user;
	    }
	    
		@Override
	    public void addUser(Ejemplar Ejemplar) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("INSERT INTO editorial (name) VALUES (?, ?)");
	            statement.setInt(1, Ejemplar.getEjemplarId());
	            
	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Ejemplar insertado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

		@Override
	    public void updateUser(Ejemplar Ejemplar) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("UPDATE autor SET name = ?, email = ? WHERE id = ?");
	            statement.setInt(1, Ejemplar.getEjemplarId());
	            statement.setInt(2, Ejemplar.getLibroId());
	            
	            int rowsUpdated = statement.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("Ejemplar actualizado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void deleteUser(int id) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM editorial WHERE id = ?");
	            statement.setInt(1, id);
	            
	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Ejemplar eliminado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


	  
	}


