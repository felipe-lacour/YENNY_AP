package controladores;

import interfaces.ExportRepository;
import modelos.Export;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExportControlador implements ExportRepository{
	private final Connection connection;
	
	public ExportControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
	
    @Override
    public List<Export> getAllExports() {
        List<Export> exports = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM export");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Export export = new Export(resultSet.getInt("exportId"), resultSet.getInt("libroId"), resultSet.getInt("cantidad"), resultSet.getString("destino"), resultSet.getDate("fecha").toLocalDate());
            	exports.add(export);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exports;
    }
    
    @Override
    public Export getExportById(int id) {
    	Export export = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM export WHERE exportId = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	export = new Export(resultSet.getInt("exportId"), resultSet.getInt("libroId"), resultSet.getInt("cantidad"), resultSet.getString("destino"), resultSet.getDate("fecha").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return export;
    }
    
	@Override
    public void addExport(Export export) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO export (libroId, cantidad, destino, fecha) VALUES (?, ?, ?, ?)");
            statement.setInt(1, export.getLibroId());
            statement.setInt(2, export.getCantidad());
            statement.setString(3, export.getDestino());
            statement.setDate(4, java.sql.Date.valueOf(export.getFecha()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Exportacion realizada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
    public void updateExport(Export export) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE export SET libroId = ?, cantidad = ?, destino = ?, fecha = ? WHERE exportId = ?");
            statement.setInt(1, export.getLibroId());
            statement.setInt(2, export.getCantidad());
            statement.setString(3, export.getDestino());
            statement.setDate(4, java.sql.Date.valueOf(export.getFecha()));
            statement.setInt(5, export.getExportId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Libro actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteExport(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM export WHERE exportId = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Exportacion cancelada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}