package controladores;

import interfaces.VentaRepositorio;
import modelos.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaControlador implements VentaRepositorio {
    private final Connection connection;

    public VentaControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Venta> getAllSales() {
        List<Venta> ventas = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ventas");
            ResultSet resultSet = statement.executeQuery();
       
            while (resultSet.next()) {
            	Venta venta = new Venta(resultSet.getInt("usuario_id"), resultSet.getString("nombre"), resultSet.getInt("rol"), resultSet.getInt("sucursal_id"), resultSet.getString("pass"), resultSet.getString("userName"));
            	ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    @Override
    public Venta getSaleById(int id) {
    	Venta venta = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ventas WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                Venta venta = new Venta(resultSet.getInt("usuario_id"), resultSet.getString("nombre"), resultSet.getInt("rol"), resultSet.getInt("sucursal_id"), resultSet.getString("pass"), resultSet.getString("userName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venta;
    }
    
	@Override
    public void addSale(Venta venta) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ventas (nombre, rol, sucursal_id, pass, userName) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, usuario.getNombre());
            statement.setInt(2, usuario.getRol());
            statement.setInt(3, usuario.getSucursalId());
            statement.setString(4, usuario.getPass());
            statement.setString(5, usuario.getUserName());
            statement.setInt(6, usuario.getUsuarioId());
            
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Venta insertada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
    public void updateSale(Venta venta) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE ventas SET nombre = ?, rol = ?, sucursal_id = ?, pass = ?, userName = ? WHERE id = ?");
            statement.setString(1, usuario.getNombre());
            statement.setInt(2, usuario.getRol());
            statement.setInt(3, usuario.getSucursalId());
            statement.setString(4, usuario.getPass());
            statement.setString(5, usuario.getUserName());
            statement.setInt(6, usuario.getUsuarioId());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Venta actualizada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSale(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ventas WHERE id = ?");
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Venta eliminada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
