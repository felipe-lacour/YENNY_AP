package controladores;

import interfaces.SagaRepository;
import modelos.Saga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SagaControlador implements SagaRepository {
    private final Connection connection;

    public SagaControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Saga> getAllUsers() {
        List<Saga> sagas = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Sagas");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Saga saga = new Saga(
                        resultSet.getInt("saga_id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("numero_libros"),
                        resultSet.getInt("numero_saga")
                );
                sagas.add(saga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sagas;
    }

    @Override
    public Saga getUserById(int id) {
        Saga saga = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Sagas WHERE saga_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                saga = new Saga(
                        resultSet.getInt("saga_id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("numero_libros"),
                        resultSet.getInt("numero_saga")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saga;
    }

    @Override
    public void addUser(Saga saga) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Sagas (nombre, numero_libros, numero_saga) VALUES (?, ?, ?)");
            statement.setString(1, saga.getNombre());
            statement.setInt(2, saga.getNumeroLibros());
            statement.setInt(3, saga.getNumeroSaga());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Saga insertada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(Saga saga) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Sagas SET nombre = ?, numero_libros = ?, numero_saga = ? WHERE saga_id = ?");
            statement.setString(1, saga.getNombre());
            statement.setInt(2, saga.getNumeroLibros());
            statement.setInt(3, saga.getNumeroSaga());
            statement.setInt(4, saga.getSagaId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Saga actualizada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deteleUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Sagas WHERE saga_id = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Saga eliminada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


