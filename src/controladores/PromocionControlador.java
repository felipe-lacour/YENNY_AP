package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import interfaces.PromocionRepository;
import modelos.Promocion;

public class PromocionControlador implements PromocionRepository {
	
 private final Connection connection;

	   public PromocionControlador() {
	        this.connection = DatabaseConnection.getInstance().getConnection();
	    }

	    @Override
	    public List<Promocion> getAllPromocion() {
	        List<Promocion> Promocions = new ArrayList<>();
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM promociones ");
	            ResultSet resultSet = statement.executeQuery();
	       
	            while (resultSet.next()) {
				
	            	Promocion Promocion = new Promocion(resultSet.getInt("promocion_id"),resultSet.getString("nombre"), resultSet.getBoolean("es_del_club"),
	            			resultSet.getInt("sucursal_id"),resultSet.getDouble("descuento"));
	                Promocions.add(Promocion);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return Promocions;
	    }

	    @Override
	    public Promocion getPromocionById(int id) {
	    	Promocion Promocion = null;
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM promociones WHERE id = ?");
	            statement.setInt(1, id);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            if (resultSet.next()) {
	                Promocion = new Promocion(resultSet.getInt("promocion_id"),resultSet.getString("nombre"), resultSet.getBoolean("es_del_club"),
	            			resultSet.getInt("sucursal_id"),resultSet.getDouble("descuento"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return Promocion;
	    }
	    
		@Override
	    public void addPromocion(Promocion Promocion) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("INSERT INTO promociones (promocion_id, nombre, es_del_club, sucursal_id, descuento) VALUES (?, ?, ?, ?, ?)");
	            statement.setInt(1, Promocion.getPromocionId());
	            statement.setString(2, Promocion.getNombre());
	            statement.setBoolean(3, Promocion.isEsDelClub());
	            statement.setInt(4, Promocion.getSucursalId());
	            statement.setDouble(5, Promocion.getDescuento());
	            
	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Promocion insertada exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

		@Override
	    public void updatePromocion(Promocion Promocion) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("UPDATE promociones SET promocion_id = ?, nombre = ?, es_del_club = ?, sucursal_id = ?, descuento = ? WHERE id = ?");
	            statement.setInt(1, Promocion.getPromocionId());
	            statement.setString(2, Promocion.getNombre());
	            statement.setBoolean(3, Promocion.isEsDelClub());
	            statement.setInt(4, Promocion.getSucursalId());
	            statement.setDouble(5, Promocion.getDescuento());
	            
	            int rowsUpdated = statement.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("Ejemplar actualizado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void deletePromocion(int id) {
	        try {
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM Promocion WHERE id = ?");
	            statement.setInt(1, id);
	            
	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Promocion eliminado exitosamente");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


	  
	}


