package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import interfaces.ClienteRepository;
import modelos.Cliente;

public class ClienteControlador implements ClienteRepository {
	
 private final Connection connection;

	   public ClienteControlador() {
	        this.connection = DatabaseConnection.getInstance().getConnection();
	    }

	    @Override
	    public List<Cliente> getAllClientes() {
	        List<Cliente> Clientes = new ArrayList<>();
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM clientes ");
	            ResultSet resultSet = statement.executeQuery();
	       
	            while (resultSet.next()) {
	            	Cliente Cliente = new Cliente(resultSet.getInt("cliente_id"), resultSet.getString("genero"), 
	            			resultSet.getInt("edad"),resultSet.getInt("lugar_compra"),resultSet.getBoolean("club_libros"));
	                Clientes.add(Cliente);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return Clientes;
	    }

	    @Override
	    public Cliente getClienteById(int id) {
	    	Cliente Cliente = null;
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM clientes WHERE id = ?");
	            statement.setInt(1, id);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            if (resultSet.next()) {
	                Cliente = new Cliente(resultSet.getInt("cliente_id"), resultSet.getString("genero"), resultSet.getInt("genero"),resultSet.getInt("lugar_compra"),resultSet.getBoolean("club_libros"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return Cliente;
	    }
	    
		@Override
	    public void addCliente(Cliente Cliente) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("INSERT INTO clientes (genero, edad, lugar_compra, club_libros) VALUES (?, ?, ?, ?)");
	            statement.setString(1, Cliente.getGenero());
	            statement.setInt(2, Cliente.getEdad());
	            statement.setInt(3, Cliente.getLugar_de_compra());
	            statement.setBoolean(4, Cliente.isClubLibros());
	            
	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Cliente insertado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

		@Override
	    public void updateCliente(Cliente Cliente) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("UPDATE clientes SET cliente_id = ?, genero = ?, edad = ?, lugar_compra = ?, club_libros = ? WHERE cliente_id = ?");
	            statement.setString(1, Cliente.getGenero());
	            statement.setInt(2, Cliente.getEdad());
	            statement.setInt(3, Cliente.getLugar_de_compra());
	            statement.setBoolean(4, Cliente.isClubLibros());
	            
	            int rowsUpdated = statement.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("Autor actualizado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void deleteCliente(int id) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM clientes WHERE cliente_id = ?");
	            statement.setInt(1, id);
	            
	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Cliente eliminado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


	  
	}
