package controladores;

import interfaces.UsuarioRepositorio;
import modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioControlador implements UsuarioRepositorio {
    private final Connection connection;

    public UsuarioControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Usuario> getAllUsers() {
        List<Usuario> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuarios");
            ResultSet resultSet = statement.executeQuery();
       
            while (resultSet.next()) {
            	Usuario user = new Usuario(resultSet.getInt("usuario_id"), resultSet.getString("nombre"), resultSet.getInt("rol"), resultSet.getInt("sucursal_id"), resultSet.getString("pass"), resultSet.getString("userName"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Usuario getUserById(int id) {
        Usuario user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                user = new Usuario(resultSet.getInt("usuario_id"), resultSet.getString("nombre"), resultSet.getInt("rol"), resultSet.getInt("sucursal_id"), resultSet.getString("pass"), resultSet.getString("userName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
	@Override
    public void addUser(Usuario usuario) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO usuarios (nombre, rol, sucursal_id, pass, userName) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, usuario.getNombre());
            statement.setInt(2, usuario.getRol());
            statement.setInt(3, usuario.getSucursalId());
            statement.setString(4, usuario.getPass());
            statement.setString(5, usuario.getUserName());
            statement.setInt(6, usuario.getUsuarioId());
            
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuario insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
    public void updateUser(Usuario usuario) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET nombre = ?, rol = ?, sucursal_id = ?, pass = ?, userName = ? WHERE id = ?");
            statement.setString(1, usuario.getNombre());
            statement.setInt(2, usuario.getRol());
            statement.setInt(3, usuario.getSucursalId());
            statement.setString(4, usuario.getPass());
            statement.setString(5, usuario.getUserName());
            statement.setInt(6, usuario.getUsuarioId());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Usuario actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Usuario eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
