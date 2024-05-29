package controladores;
import interfaces.PromocionRepository;

import modelos.Promocion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromocionControlador implements PromocionRepository {
    private final Connection connection;

    public PromocionControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Promocion> getAllPromos() {
        List<Promocion> promociones = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Promociones");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Promocion promocion = new Promocion(
                        resultSet.getInt("promocion_id"),
                        resultSet.getString("nombre"),
                        resultSet.getBoolean("es_del_club"),
                        resultSet.getInt("sucursal_id"),
                        resultSet.getDouble("descuento")
                );
                promociones.add(promocion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promociones;
    }

    @Override
    public Promocion getPromoById(int id) {
        Promocion promocion = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Promociones WHERE promocion_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                promocion = new Promocion(
                        resultSet.getInt("promocion_id"),
                        resultSet.getString("nombre"),
                        resultSet.getBoolean("es_del_club"),
                        resultSet.getInt("sucursal_id"),
                        resultSet.getDouble("descuento")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promocion;
    }

    @Override
    public void addPromo(Promocion promocion) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Promociones (nombre, es_del_club, sucursal_id, descuento) VALUES (?, ?, ?, ?)");
            statement.setString(1, promocion.getNombre());
            statement.setBoolean(2, promocion.isEsDelClub());
            statement.setInt(3, promocion.getSucursalId());
            statement.setDouble(4, promocion.getDescuento());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Promoción insertada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePromo(Promocion promocion) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Promociones SET nombre = ?, es_del_club = ?, sucursal_id = ?, descuento = ? WHERE promocion_id = ?");
            statement.setString(1, promocion.getNombre());
            statement.setBoolean(2, promocion.isEsDelClub());
            statement.setInt(3, promocion.getSucursalId());
            statement.setDouble(4, promocion.getDescuento());
            statement.setInt(5, promocion.getPromocionId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Promoción actualizada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void deletePromo(int id) {
		try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Promociones WHERE promocion_id = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Promoción eliminada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}

