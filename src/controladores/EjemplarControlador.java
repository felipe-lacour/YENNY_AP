package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<Ejemplar> getAllEjemplar() {
        List<Ejemplar> ejemplares = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ejemplares");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Ejemplar ejemplar = new Ejemplar(
                    resultSet.getInt("ejemplar_id"),
                    resultSet.getInt("libro_id"),
                    resultSet.getInt("sucursal_id"),
                    resultSet.getString("isbn"),
                    resultSet.getDouble("precio"),
                    resultSet.getBoolean("tapa_dura"),
                    resultSet.getBoolean("edicion_especial"),
                    resultSet.getDate("fecha_edicion").toLocalDate(),
                    resultSet.getInt("numero_edicion"),
                    resultSet.getBoolean("firmado"),
                    resultSet.getString("idioma"),
                    resultSet.getDate("fecha_adquisicion").toLocalDate(),
                    resultSet.getInt("venta_id") == 0 ? null : resultSet.getInt("venta_id")
                );
                ejemplares.add(ejemplar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ejemplares;
    }

    @Override
    public Ejemplar getEjemplarById(int id) {
        Ejemplar ejemplar = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ejemplares WHERE ejemplar_id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                ejemplar = new Ejemplar(
                    resultSet.getInt("ejemplar_id"),
                    resultSet.getInt("libro_id"),
                    resultSet.getInt("sucursal_id"),
                    resultSet.getString("isbn"),
                    resultSet.getDouble("precio"),
                    resultSet.getBoolean("tapa_dura"),
                    resultSet.getBoolean("edicion_especial"),
                    resultSet.getDate("fecha_edicion").toLocalDate(),
                    resultSet.getInt("numero_edicion"),
                    resultSet.getBoolean("firmado"),
                    resultSet.getString("idioma"),
                    resultSet.getDate("fecha_adquisicion").toLocalDate(),
                    resultSet.getInt("venta_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ejemplar;
    }
    
    @Override
    public void addEjemplar(Ejemplar ejemplar) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO ejemplares (libro_id, sucursal_id, isbn, precio, tapa_dura, edicion_especial, fecha_edicion, numero_edicion, firmado, idioma, fecha_adquisicion, venta_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setInt(1, ejemplar.getLibroId());
            statement.setInt(2, ejemplar.getSucursalId());
            statement.setString(3, ejemplar.getIsbn());
            statement.setDouble(4, ejemplar.getPrecio());
            statement.setBoolean(5, ejemplar.isTapaDura());
            statement.setBoolean(6, ejemplar.isEdicionEspecial());
            statement.setDate(7, Date.valueOf(ejemplar.getFechaEdicion()));
            statement.setInt(8, ejemplar.getNumeroEdicion());
            statement.setBoolean(9, ejemplar.isFirmado());
            statement.setString(10, ejemplar.getIdioma());
            statement.setDate(11, Date.valueOf(ejemplar.getFechaAdquisicion()));
            if (ejemplar.getVentaId() == 0) {
            	statement.setNull(12, 0);
            } else {
            	statement.setInt(12, ejemplar.getVentaId());
            }

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Ejemplar insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEjemplar(Ejemplar ejemplar) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "UPDATE ejemplares SET libro_id = ?, sucursal_id = ?, isbn = ?, precio = ?, tapa_dura = ?, edicion_especial = ?, fecha_edicion = ?, numero_edicion = ?, firmado = ?, idioma = ?, fecha_adquisicion = ?, venta_id = ? WHERE ejemplar_id = ?"
            );
            statement.setInt(1, ejemplar.getLibroId());
            
            if(ejemplar.getSucursalId() == 0) {
            	statement.setNull(2, 0);
            } else {
                statement.setInt(2, ejemplar.getSucursalId());
            }
            statement.setString(3, ejemplar.getIsbn());
            statement.setDouble(4, ejemplar.getPrecio());
            statement.setBoolean(5, ejemplar.isTapaDura());
            statement.setBoolean(6, ejemplar.isEdicionEspecial());
            statement.setDate(7, Date.valueOf(ejemplar.getFechaEdicion()));
            statement.setInt(8, ejemplar.getNumeroEdicion());
            statement.setBoolean(9, ejemplar.isFirmado());
            statement.setString(10, ejemplar.getIdioma());
            statement.setDate(11, Date.valueOf(ejemplar.getFechaAdquisicion()));
            if (ejemplar.getVentaId() == 0) {
            	statement.setNull(12, 0);
            } else {
            	statement.setInt(12, ejemplar.getVentaId());
            }
            statement.setInt(13, ejemplar.getEjemplarId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Ejemplar actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEjemplar(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ejemplares WHERE ejemplar_id = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Ejemplar eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Ejemplar> getEjemplarByField(String campo, int id) {
    	List<Ejemplar> ejemplares = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ejemplares WHERE " + campo + " = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Ejemplar ejemplar = new Ejemplar(
                    resultSet.getInt("ejemplar_id"),
                    resultSet.getInt("libro_id"),
                    resultSet.getInt("sucursal_id"),
                    resultSet.getString("isbn"),
                    resultSet.getDouble("precio"),
                    resultSet.getBoolean("tapa_dura"),
                    resultSet.getBoolean("edicion_especial"),
                    resultSet.getDate("fecha_edicion").toLocalDate(),
                    resultSet.getInt("numero_edicion"),
                    resultSet.getBoolean("firmado"),
                    resultSet.getString("idioma"),
                    resultSet.getDate("fecha_adquisicion").toLocalDate(),
                    resultSet.getInt("venta_id")
                );
                ejemplares.add(ejemplar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ejemplares;
    }
}