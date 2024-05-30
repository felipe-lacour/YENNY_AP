package modelos;

import interfaces.Menu;
import controladores.UsuarioControlador;
import controladores.SucuControlador;
import controladores.VentaControlador;
import controladores.LibroControlador;

import java.util.List;
import javax.swing.JOptionPane;

public class GerenteGeneral extends Usuario implements Menu{

	public GerenteGeneral(int usuarioId, String nombre, int rol, int sucursalId, String pass, String userName) {
		super(usuarioId, nombre, rol, sucursalId, pass, userName);
	}

	@Override
	public void Menu() {
		int eleccion = 0;
		String[] opciones = {"Ver Ventas", "Administrar usuarios", "Administrar sucursales", 
							 "Administrar beneficios", "Pedir Exportacion de Libros", "Salir"};
		do {
			eleccion = JOptionPane.showOptionDialog(null, "¿Que operacion desea realizar?", "Elija por favor", 0, 0, null, opciones, opciones);
			
			switch(eleccion) {
			case 0:
				verVentas();
				break;
			case 1:
				administrarUsuarios();
				break;
			case 2:
				administrarSucursales();
				break;
			case 3:

				break;
			case 4:
				exportarLibros();
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "Nos re vimos!");
				return;
			default:
				JOptionPane.showMessageDialog(null, "Re ilegal!");
				break;
			}
		} while(eleccion != 5);
	}
	
    private void verVentas() {
    	VentaControlador ventaControlador = new VentaControlador();
    	
        StringBuilder mensaje = new StringBuilder("Ventas Realizadas:\n");
        for (Venta venta : ventaControlador.getAllSales()) {
            mensaje.append("ID: ").append(venta.getVentaId()).append(", Metodo Pago: ").append(venta.getMetodoPagoId()).append(", Fecha: ").append(venta.getFecha()).append("\n");
        }
        
        JOptionPane.showMessageDialog(null, mensaje.toString());
    }
    
    private void administrarUsuarios() {
        String[] opciones = {"Agregar Usuario", "Modificar Usuario", "Eliminar Usuario"};
        int eleccion = JOptionPane.showOptionDialog(null, "¿Qué operación desea realizar?", "Administrar Usuarios", 0, 0, null, opciones, opciones[0]);

        switch (eleccion) {
            case 0:
                agregarUsuario();
                break;
            case 1:
                modificarUsuario();
                break;
            case 2:
                eliminarUsuario();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Operación inválida!");
                break;
        }
    }
    
    private void agregarUsuario() {
    	UsuarioControlador usuarioControlador = new UsuarioControlador();
    	
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
        String userName = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        String pass = JOptionPane.showInputDialog("Ingrese la contraseña:");
        int rol = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el rol (1 para Vendedor, 2 para Gerente Sucursal):"));
        int sucursalId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la sucursal:"));

        Usuario nuevoUsuario = new Usuario(0, nombre, rol, sucursalId, pass, userName);
        usuarioControlador.addUser(nuevoUsuario);
        JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente!");
    }
    
    private void modificarUsuario() {
    	UsuarioControlador usuarioControlador = new UsuarioControlador();
    	
        int usuarioId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario a modificar:"));
        Usuario usuario = usuarioControlador.getUserById(usuarioId);
        
        if (usuario != null) {
            String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del usuario:", usuario.getNombre());
            String userName = JOptionPane.showInputDialog("Ingrese el nuevo nombre de usuario:", usuario.getUserName());
            String pass = JOptionPane.showInputDialog("Ingrese la nueva contraseña:", usuario.getPass());
            int rol = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo rol (1 para Vendedor, 2 para Gerente Sucursal):", usuario.getRol()));
            int sucursalId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo ID de la sucursal:", usuario.getSucursalId()));

            usuario.setNombre(nombre);
            usuario.setUserName(userName);
            usuario.setPass(pass);
            usuario.setRol(rol);
            usuario.setSucursalId(sucursalId);

            usuarioControlador.updateUser(usuario);
            JOptionPane.showMessageDialog(null, "Usuario modificado exitosamente!");
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado!");
        }
    }
    
    private void eliminarUsuario() {
    	UsuarioControlador usuarioControlador = new UsuarioControlador();
    	
        int usuarioId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario a eliminar:"));
        
        usuarioControlador.deleteUser(usuarioId);
        JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente!");
    }
    
    private void administrarSucursales() {
        String[] opciones = {"Ver Sucursales", "Agregar Sucursal", "Eliminar Sucursal"};
        int eleccion = JOptionPane.showOptionDialog(null, "¿Qué operación desea realizar?", "Administrar Sucursales", 0, 0, null, opciones, opciones[0]);

        switch (eleccion) {
            case 0:
                verSucursales();
                break;
            case 1:
                agregarSucursal();
                break;
            case 2:
                eliminarSucursal();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Operación inválida!");
                break;
        }
    }
    
    private void verSucursales() {
    	SucuControlador sucuControlador = new SucuControlador();
        
        StringBuilder mensaje = new StringBuilder("Sucursales Registradas:\n");
        for (Sucursal sucursal : sucuControlador.getAllBranches()) {
            mensaje.append("ID: ").append(sucursal.getSucursalId()).append(", Ubicación: ").append(sucursal.getUbicacion()).append(", Nombre: ").append(sucursal.getNombre()).append("\n");
        }
        
        JOptionPane.showMessageDialog(null, mensaje.toString());
    }
    
    private void agregarSucursal() {
    	SucuControlador sucuControlador = new SucuControlador();
    	
        String ubicacion = JOptionPane.showInputDialog("Ingrese la ubicación de la sucursal:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la sucursal:");

        Sucursal nuevaSucursal = new Sucursal(0, ubicacion, nombre);
        sucuControlador.addBranch(nuevaSucursal);
        JOptionPane.showMessageDialog(null, "Sucursal agregada exitosamente!");
    }
    
    private void eliminarSucursal() {
    	SucuControlador sucuControlador = new SucuControlador();
    	
        int sucursalId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la sucursal a eliminar:"));
        
        sucuControlador.deteleBranch(sucursalId);
        JOptionPane.showMessageDialog(null, "Sucursal eliminada exitosamente!");
    }
    
    private void exportarLibros() {
    	LibroControlador libroControlador = new LibroControlador();
        List<Libro> libros = libroControlador.getAllBooks();
        String[] titulos = libros.stream().map(Libro::getTitulo).toArray(String[]::new);

        String titulo = (String) JOptionPane.showInputDialog(null, "Seleccione el libro a exportar:", "Exportación de Libros", JOptionPane.QUESTION_MESSAGE, null, titulos, titulos[0]);
        Libro libroSeleccionado = libros.stream().filter(libro -> libro.getTitulo().equals(titulo)).findFirst().orElse(null);

        if (libroSeleccionado != null) {
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad a exportar:"));
            String pais = JOptionPane.showInputDialog("Ingrese el país de destino:");

            JOptionPane.showMessageDialog(null, "Se ha solicitado la exportación de " + cantidad + " ejemplares del libro \"" + libroSeleccionado.getTitulo() + "\" a " + pais);
        } else {
            JOptionPane.showMessageDialog(null, "Libro no encontrado!");
        }
    }
}