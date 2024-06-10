package modelos;

import interfaces.Menu;
import vista.AddUser;
import controladores.UsuarioControlador;
import controladores.SucuControlador;
import controladores.VentaControlador;
import controladores.LibroControlador;
import controladores.PromocionControlador;

import java.util.LinkedList;
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
				administrarPromociones();
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
    	UsuarioControlador usuarioControlador = new UsuarioControlador();
        String[] opciones = {"Agregar Usuario", "Modificar Usuario", "Eliminar Usuario"};
        int eleccion = JOptionPane.showOptionDialog(null, "¿Qué operación desea realizar?", "Administrar Usuarios", 0, 0, null, opciones, opciones[0]);

        switch (eleccion) {
            case 0:
                agregarUsuario(usuarioControlador);
                break;
            case 1:
                modificarUsuario(usuarioControlador);
                break;
            case 2:
                eliminarUsuario(usuarioControlador);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Operación inválida!");
                break;
        }
    } // hello
    
    private void agregarUsuario(UsuarioControlador usuarioControlador) {
        AddUser dialog = new AddUser(null);
        dialog.setVisible(true);
    }
    
    private void modificarUsuario(UsuarioControlador usuarioControlador) {
    	String id;
    	do {
    		id = JOptionPane.showInputDialog("Ingrese el ID del usuario a modificar:");
        } while (!verifyIntInput(id));
        int usuarioId = Integer.parseInt(id);
        Usuario usuario = usuarioControlador.getUserById(usuarioId);
        if (usuario == null) {
        	JOptionPane.showMessageDialog(null, "No hay usuario registrado con ese ID");
        	return;
        }
        
    	String nombre, roll;
    	int sucursalId;
    	do {
    		nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
    	} while (!verifyStrInput(nombre));
        String userName = JOptionPane.showInputDialog("Ingrese el username:");
        String pass = JOptionPane.showInputDialog("Ingrese la contraseña:");
        do {
        	roll = JOptionPane.showInputDialog("Ingrese el rol (1 para Vendedor, 2 para Gerente Sucursal):");
        } while (!verifyIntInput(roll) || ((Integer.parseInt(roll)) > 2)  || ((Integer.parseInt(roll)) == 0));
        int rol = Integer.parseInt(roll);
        
        SucuControlador sucuControlador = new SucuControlador();
        do {
        	sucursalId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la sucursal:"));
        } while (sucuControlador.getBranchById(sucursalId) == null);

        usuario.setNombre(nombre);
        usuario.setUserName(userName);
        usuario.setPass(pass);
        usuario.setRol(rol);
        usuario.setSucursalId(sucursalId);

        usuarioControlador.updateUser(usuario);
        JOptionPane.showMessageDialog(null, "Usuario modificado exitosamente!");
    }
    
    private void eliminarUsuario(UsuarioControlador usuarioControlador) {
    	String id;
    	do {
    		id = JOptionPane.showInputDialog("Ingrese el ID del usuario a modificar:");
        } while (!verifyIntInput(id));
        int usuarioId = Integer.parseInt(id);
        Usuario usuario = usuarioControlador.getUserById(usuarioId);
        if (usuario == null) {
        	JOptionPane.showMessageDialog(null, "No hay usuario registrado con ese ID");
        	return;
        }
        
        usuarioControlador.deleteUser(usuarioId);
        JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente!");
    }
    
    private void administrarSucursales() {
    	SucuControlador sucuControlador = new SucuControlador();
        String[] opciones = {"Ver Sucursales", "Agregar Sucursal", "Eliminar Sucursal"};
        int eleccion = JOptionPane.showOptionDialog(null, "¿Qué operación desea realizar?", "Administrar Sucursales", 0, 0, null, opciones, opciones[0]);

        switch (eleccion) {
            case 0:
                verSucursales(sucuControlador);
                break;
            case 1:
                agregarSucursal(sucuControlador);
                break;
            case 2:
                eliminarSucursal(sucuControlador);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Operación inválida!");
                break;
        }
    }
    
    private void verSucursales(SucuControlador sucuControlador) {
        StringBuilder mensaje = new StringBuilder("Sucursales Registradas:\n");
        for (Sucursal sucursal : sucuControlador.getAllBranches()) {
            mensaje.append("ID: ").append(sucursal.getSucursalId()).append(", Ubicación: ").append(sucursal.getUbicacion()).append(", Nombre: ").append(sucursal.getNombre()).append("\n");
        }
        
        JOptionPane.showMessageDialog(null, mensaje.toString());
    }
    
    private void agregarSucursal(SucuControlador sucuControlador) {
    	String ubicacion, nombre;
    	do {
    		ubicacion = JOptionPane.showInputDialog("Ingrese la ubicación de la sucursal:");
    	} while (!verifyStrInput(ubicacion));
    	do {
    		nombre = JOptionPane.showInputDialog("Ingrese el nombre de la sucursal:");
    	} while (!verifyStrInput(nombre));

        Sucursal nuevaSucursal = new Sucursal(0, ubicacion, nombre);
        sucuControlador.addBranch(nuevaSucursal);
        JOptionPane.showMessageDialog(null, "Sucursal agregada exitosamente!");
    }
    
    private void eliminarSucursal(SucuControlador sucuControlador) {
    	List<Sucursal> sucus = sucuControlador.getAllBranches();
    	String[] sucursales = new String[sucus.size()];
    	int i = 0;
    	for (Sucursal sucu : sucus) {
    		sucursales[i] = sucu.getNombre() + ", en " + sucu.getUbicacion();
    		i++;
        }
    	
    	String sucursal = (String) JOptionPane.showInputDialog(null, "Seleccione la sucursal a eliminar:", "Quitar sucursal", JOptionPane.QUESTION_MESSAGE, null, sucursales, sucursales[0]);
    	for (Sucursal sucu : sucus) {
    		if (sucursal.contains(sucu.getNombre()) && sucursal.contains(sucu.getUbicacion())) {
    			sucuControlador.deteleBranch(sucu.getSucursalId());
    	        JOptionPane.showMessageDialog(null, "Sucursal eliminada exitosamente!");
    	        return;
    		}
        }
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
    
    private void administrarPromociones() {
        PromocionControlador promocionControlador = new PromocionControlador();

        String[] opciones = {"Agregar Promoción", "Quitar Promoción", "Modificar Promoción"};
        int eleccion = JOptionPane.showOptionDialog(null, "¿Qué operación desea realizar?", "Administrar Promociones", 0, 0, null, opciones, opciones[0]);

        switch(eleccion) {
            case 0:
                agregarPromocion(promocionControlador);
                break;
            case 1:
                quitarPromocion(promocionControlador);
                break;
            case 2:
                modificarPromocion(promocionControlador);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Operación inválida!");
                break;
        }
    }
    
    private void agregarPromocion(PromocionControlador promocionControlador) {
    	String nombre, desc;
    	do {
    		nombre = JOptionPane.showInputDialog("Ingrese el nombre de la promoción:");
    	} while (!verifyStrInput(nombre));
        boolean esDelClub = true;
        Integer sucursalId = (Integer) null;
        do {
    		desc = JOptionPane.showInputDialog("Ingrese el porcentaje de descuento:");
    	} while (!verifyDouInput(desc) || ((Double.parseDouble(desc)) > 80));
        double descuento = Double.parseDouble(desc);

        Promocion nuevaPromocion = new Promocion(0, nombre, esDelClub, sucursalId, descuento);
        promocionControlador.addPromo(nuevaPromocion);

        JOptionPane.showMessageDialog(null, "Promoción agregada exitosamente!");
    }

    private void quitarPromocion(PromocionControlador promocionControlador) {
    	List<Promocion> promociones = new LinkedList<Promocion>();
    	for (Promocion promo : promocionControlador.getAllPromos()) {
    		if (promo.isEsDelClub()) {
    			promociones.add(promo);
    		}
    	}
    	
        if (promociones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay promociones para eliminar.");
            return;
        }
        
        String[] opciones = new String[promociones.size()];
        for (int i = 0; i < promociones.size(); i++) {
        	opciones[i] = ("ID: " + promociones.get(i).getPromocionId() + ", Nombre: " + promociones.get(i).getNombre());
        }

        String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione la promoción a eliminar:", "Quitar Promoción", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        
        if (seleccion != null) {
            int promocionId = Integer.parseInt(seleccion.split(",")[0].split(":")[1].trim());
            promocionControlador.deletePromo(promocionId);
            JOptionPane.showMessageDialog(null, "Promoción eliminada exitosamente!");
        }
    }

    private void modificarPromocion(PromocionControlador promocionControlador) {
    	List<Promocion> promociones = new LinkedList<Promocion>();
    	for (Promocion promo : promocionControlador.getAllPromos()) {
    		if (promo.isEsDelClub()) {
    			promociones.add(promo);
    		}
    	}
    	
        if (promociones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay promociones para modificar.");
            return;
        }
        
        String[] opciones = new String[promociones.size()];
        for (int i = 0; i < promociones.size(); i++) {
        	opciones[i] = ("ID: " + promociones.get(i).getPromocionId() + ", Nombre: " + promociones.get(i).getNombre());
        }
        
        String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione la promoción a eliminar:", "Quitar Promoción", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion != null) {
            int promocionId = Integer.parseInt(seleccion.split(",")[0].split(":")[1].trim());
            Promocion promocion = promocionControlador.getPromoById(promocionId);
            
	        String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la promoción:", promocion.getNombre());
	        double descuento = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo porcentaje de descuento:", promocion.getDescuento()));

	        promocion.setNombre(nombre);
	        promocion.setDescuento(descuento);
	        promocionControlador.updatePromo(promocion);
	        JOptionPane.showMessageDialog(null, "Promoción modificada exitosamente!");
        }
    }
}