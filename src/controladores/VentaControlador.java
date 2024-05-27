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
            	Venta venta = new Venta(resultSet.getInt("venta_id"), resultSet.getInt("metodo_pago_id"), resultSet.getDate("fecha").toLocalDate());
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
                venta = new Venta(resultSet.getInt("venta_id"), resultSet.getInt("metodo_pago_id"), resultSet.getDate("fecha").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venta;
    }
    
	@Override
    public void addSale(Venta venta) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ventas (metodo_pago_id, fecha) VALUES (?, ?)");
            statement.setInt(1, venta.getMetodoPagoId());
            statement.setDate(2, venta.getFecha());
            
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
            PreparedStatement statement = connection.prepareStatement("UPDATE ventas SET metodo_pago_id = ?, fecha = ? WHERE id = ?");
            statement.setInt(1, venta.getMetodoPagoId());
            statement.setDate(2, venta.getFecha());
            statement.setInt(3, venta.getVentaId());
            
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
