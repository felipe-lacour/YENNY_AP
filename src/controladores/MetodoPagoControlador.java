package controladores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.MetodoPagoRepository;
import modelos.MetodoPago;
public class MetodoPagoControlador implements MetodoPagoRepository{
	private final Connection connection;
	
	public MetodoPagoControlador() {
		this.connection= DatabaseConnection.getInstance().getConnection();
	}

	@Override
	public List<MetodoPago> getAllMethods() {
        List<MetodoPago> methods = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Metodos_de_Pago ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	MetodoPago method = new MetodoPago(resultSet.getInt("metodo_pago_id"), resultSet.getInt("cliente_id"), resultSet.getString("tipo"), resultSet.getString("detalles"));
                methods.add(method);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return methods;
	}

	@Override
	public MetodoPago getMethodById(int id) {
        MetodoPago method = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Metodos_de_Pago WHERE metodo_pago_id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	method = new MetodoPago(resultSet.getInt("metodo_pago_id"), resultSet.getInt("cliente_id"), resultSet.getString("tipo"), resultSet.getString("detalles"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return method;
	}

	@Override
	public void addMethod(MetodoPago method) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Metodos_de_Pago (cliente_id, tipo, detalles) VALUES (?, ?, ?)");
            statement.setInt(1, method.getClienteId());
            statement.setString(2, method.getTipo());
            statement.setString(3, method.getDetalles());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Metodo insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void updateMethod(MetodoPago method) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Metodo_de_Pago SET cliente_id = ?, tipo = ?, detalles = ? WHERE metodo_pago_id = ?");
            statement.setInt(1, method.getClienteId());
            statement.setString(2, method.getTipo());
            statement.setString(3, method.getDetalles());
            statement.setInt(4, method.getMetodoPagoId());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Metodo de Pago actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void deleteMethod(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Metodo_de_Pago WHERE metodo_pago_id = ?");
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Metodo de Pago eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}