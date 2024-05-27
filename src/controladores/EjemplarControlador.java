package controladores;

import java.sql.Connection;
import java.sql.Date;
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
	    public List<Ejemplar> getAllEjemplar() {
	        List<Ejemplar> Ejemplars = new ArrayList<>();
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ejemplar ");
	            ResultSet resultSet = statement.executeQuery();
	       
	            while (resultSet.next()) {
				
	            	Ejemplar Ejemplar = new Ejemplar(resultSet.getInt("ejemplar_id"),resultSet.getInt("libro_id"), resultSet.getInt("sucursal_id"),
	            			resultSet.getString("isbn"),resultSet.getDouble("precio"),resultSet.getString("condicion"),resultSet.getBoolean("tapa_dura"),
	            			resultSet.getBoolean("edicion_esecial"),resultSet.getDate("fecha_edicion").toLocalDate(),resultSet.getInt("numero_edicion"),
	            			resultSet.getBoolean("firmado"),resultSet.getString("idioma"),resultSet.getString("caracteristicas_especiales"),
	            			resultSet.getDate("fecha_adquisicion").toLocalDate(),resultSet.getInt("venta_iD"));
	                Ejemplars.add(Ejemplar);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return Ejemplars;
	    }

	    @Override
	    public Ejemplar getEjemplarById(int id) {
	    	Ejemplar Ejemplar = null;
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ejemplares WHERE id = ?");
	            statement.setInt(1, id);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            if (resultSet.next()) {
	                Ejemplar = new Ejemplar(resultSet.getInt("ejemplar_id"),resultSet.getInt("libro_id"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return Ejemplar;
	    }
	    
		@Override
	    public void addEjemplar(Ejemplar Ejemplar) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("INSERT INTO ejemplares(ejemplar_id, libro_id, sucursal_id, isbn, precio, condicion, tapa_dura, edicion_especial, fecha_edicion, numero_edicion, firmado, idioma, caracteristicas_especiales, fecha_adquisicion, cantidad, venta_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	            statement.setInt(1, Ejemplar.getEjemplarId());
	            statement.setInt(2, Ejemplar.getLibroId());
	            statement.setInt(3, Ejemplar.getSucursalId());
	            statement.setString(4, Ejemplar.getIsbn());
	            statement.setDouble(5, Ejemplar.getPrecio());
	            statement.setString(6, Ejemplar.getCondicion());
	            statement.setBoolean(7, Ejemplar.isTapaDura());
	            statement.setBoolean(8, Ejemplar.isEdicionEspecial());
	            statement.setDate(9, Date.valueOf(Ejemplar.getFechaEdicion()));
	            statement.setBoolean(10, Ejemplar.isFirmado());
	            statement.setString(11, Ejemplar.getIdioma());
	            statement.setString(12, Ejemplar.getCaracteristicasEspeciales());
	            statement.setDate(13, Date.valueOf(Ejemplar.getFechaAdquisicion()));
	           // statement.setInt(14, Ejemplar.());
	            statement.setInt(15, Ejemplar.getVentaId());
	            
	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Ejemplar insertado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

		@Override
	    public void updateEjemplar(Ejemplar Ejemplar) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("UPDATE ejemplares SET ejemplar_id=?,libro_id=?,sucursal_id=?,isbn=?,precio=?,condicion=?,tapa_dura=?,edicion_especial=?,fecha_edicion=?,numero_edicion=?,firmado=?,idioma=?,caracteristicas_especiales=?,fecha_adquisicion=?,cantidad=?,venta_id=? WHERE id=?");
	            statement.setInt(1, Ejemplar.getEjemplarId());
	            statement.setInt(2, Ejemplar.getLibroId());
	            statement.setInt(3, Ejemplar.getSucursalId());
	            statement.setString(4, Ejemplar.getIsbn());
	            statement.setDouble(5, Ejemplar.getPrecio());
	            statement.setString(6, Ejemplar.getCondicion());
	            statement.setBoolean(7, Ejemplar.isTapaDura());
	            statement.setBoolean(8, Ejemplar.isEdicionEspecial());
	            statement.setDate(9, Date.valueOf(Ejemplar.getFechaEdicion()));
	            statement.setBoolean(10, Ejemplar.isFirmado());
	            statement.setString(11, Ejemplar.getIdioma());
	            statement.setString(12, Ejemplar.getCaracteristicasEspeciales());
	            statement.setDate(13, Date.valueOf(Ejemplar.getFechaAdquisicion()));
	            statement.setInt(15, Ejemplar.getVentaId());
	            
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
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM ejemplares WHERE id = ?");
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


