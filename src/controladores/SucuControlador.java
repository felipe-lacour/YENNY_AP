package controladores;

import interfaces.SucuRepository;
import modelos.Sucursal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SucuControlador implements SucuRepository {
    private final Connection connection;

    public SucuControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Sucursal> getAllBranches() {
        List<Sucursal> sucursales = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM sucursales");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Sucursal sucursal = new Sucursal(
                        resultSet.getInt("sucursal_id"),
                        resultSet.getString("ubicacion"),
                        resultSet.getString("nombre")
                );
                sucursales.add(sucursal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sucursales;
    }

    @Override
    public Sucursal getBranchById(int id) {
        Sucursal sucursal = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM sucursales WHERE sucursal_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                sucursal = new Sucursal(
                        resultSet.getInt("sucursal_id"),
                        resultSet.getString("ubicacion"),
                        resultSet.getString("nombre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sucursal;
    }

    @Override
    public void addBranch(Sucursal sucursal) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO sucursales (ubicacion, nombre) VALUES (?, ?)");
            statement.setString(1, sucursal.getUbicacion());
            statement.setString(2, sucursal.getNombre());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Sucursal insertada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateBranch(Sucursal sucursal) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE sucursales SET ubicacion = ?, nombre = ? WHERE sucursal_id = ?");
            statement.setString(1, sucursal.getUbicacion());
            statement.setString(2, sucursal.getNombre());
            statement.setInt(3, sucursal.getSucursalId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Sucursal actualizada exitosamente");
                return true;
            }
            return false;    
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }

    @Override
    public void deteleBranch(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM sucursales WHERE sucursal_id = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Sucursal eliminada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

