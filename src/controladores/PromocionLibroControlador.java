package controladores;

import interfaces.PromoLibroRepository;
import modelos.PromocionLibro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromocionLibroControlador implements PromoLibroRepository {
    private final Connection connection;

    public PromocionLibroControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<PromocionLibro> getAllPromoLibros() {
        List<PromocionLibro> promocionesLibros = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM promociones_libros");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PromocionLibro promocionLibro = new PromocionLibro(
                        resultSet.getInt("promocion_id"),
                        resultSet.getInt("libro_id")
                );
                promocionesLibros.add(promocionLibro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promocionesLibros;
    }

    @Override
    public PromocionLibro getPromoLibroById(int id) {
        PromocionLibro promocionLibro = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM promociones_libros WHERE libro_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                promocionLibro = new PromocionLibro(
                        resultSet.getInt("promocion_id"),
                        resultSet.getInt("libro_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promocionLibro;
    }

    @Override
    public void addPromoLibro(PromocionLibro promocionLibro) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO promociones_libros (promocion_id, libro_id) VALUES (?, ?)");
            statement.setInt(1, promocionLibro.getPromocionId());
            statement.setInt(2, promocionLibro.getLibroId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Promoción de libro insertada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePromoLibro(PromocionLibro promocionLibro) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE promociones_libros SET promocion_id = ?, libro_id = ? WHERE libro_id = ?");
            statement.setInt(1, promocionLibro.getPromocionId());
            statement.setInt(2, promocionLibro.getLibroId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Promoción de libro actualizada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void detelePromoLibro(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM promociones_libros WHERE libro_id = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Promoción de libro eliminada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
