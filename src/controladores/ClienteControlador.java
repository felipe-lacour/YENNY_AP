package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

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
	            	Cliente cliente = new Cliente(resultSet.getInt("cliente_id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("genero"), resultSet.getInt("edad"), resultSet.getInt("lugar_compra"), resultSet.getBoolean("club_libros"));
	                Clientes.add(cliente);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return Clientes;
	    }

	    @Override
	    public Cliente getClienteById(int id) {
	        Cliente cliente = null;
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM clientes WHERE cliente_id = ?");
	            statement.setInt(1, id);

	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                cliente = new Cliente(resultSet.getInt("cliente_id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("genero"), resultSet.getInt("edad"), resultSet.getInt("lugar_compra"), resultSet.getBoolean("club_libros"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return cliente;
	    }
	    
		@Override
	    public int addCliente(Cliente cliente) {
	        String sql = "INSERT INTO clientes (nombre, apellido, genero, edad, lugar_compra, club_libros) VALUES (?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	            statement.setString(1, cliente.getNombre());
	            statement.setString(2, cliente.getApellido());
	            statement.setString(3, cliente.getGenero());
	            statement.setInt(4, cliente.getEdad());
	            statement.setInt(5, cliente.getLugar_de_compra());
	            statement.setBoolean(6, cliente.isClubLibros());

	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	                    if (generatedKeys.next()) {
	                        int generatedId = generatedKeys.getInt(1);
	                        System.out.println("Cliente registrado exitosamente con ID: " + generatedId);
	                        return generatedId;
	                    } else {
	                        throw new SQLException("Creating client failed, no ID obtained.");
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return -1;
	    }

		@Override
	    public void updateCliente(Cliente cliente) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("UPDATE clientes SET nombre = ?, apellido = ?, genero = ?, edad = ?, lugar_compra = ?, club_libros = ? WHERE cliente_id = ?");
	            statement.setString(1, cliente.getNombre());
	            statement.setString(2, cliente.getApellido());
	            statement.setString(3, cliente.getGenero());
	            statement.setInt(4, cliente.getEdad());
	            statement.setInt(5, cliente.getLugar_de_compra());
	            statement.setBoolean(6, cliente.isClubLibros());
	            statement.setInt(7, cliente.getClienteId());

	            int rowsUpdated = statement.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("Cliente actualizado exitosamente");
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