package modelos;

import interfaces.Menu;
import vista.ViewBooks;
import vista.ViewPromos;
import vista.ViewUsers;
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
							 "Administrar beneficios", "Pedir Exportacion de Libros", "Ver libros", "Salir"};
		do {
			eleccion = JOptionPane.showOptionDialog(null, "¿Que operacion desea realizar?", "Elija por favor", 0, 0, null, opciones, opciones);
			
			switch(eleccion) {
			case 0:
				verVentas();
				break;
			case 1:
				ViewUsers frame1 = new ViewUsers(null);
				frame1.setVisible(true);
				break;
			case 2:
				administrarSucursales();
				break;
			case 3:
				ViewPromos frame2 = new ViewPromos(this);
				frame2.setVisible(true);
				break;
			case 4:
				exportarLibros();
				break;
			case 5:
				ViewBooks frame = new ViewBooks(null);
				frame.setVisible(true);
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "Nos re vimos!");
				return;
			default:
				JOptionPane.showMessageDialog(null, "Re ilegal!");
				break;
			}
		} while(eleccion != 6);
	}
	
    private void verVentas() {
    	VentaControlador ventaControlador = new VentaControlador();
    	
        StringBuilder mensaje = new StringBuilder("Ventas Realizadas:\n");
        for (Venta venta : ventaControlador.getAllSales()) {
            mensaje.append("ID: ").append(venta.getVentaId()).append(", Metodo Pago: ").append(venta.getMetodoPagoId()).append(", Fecha: ").append(venta.getFecha()).append("\n");
        }
        
        JOptionPane.showMessageDialog(null, mensaje.toString());
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
}