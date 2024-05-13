package modelos;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import interfaces.Auxiliaries;

public class SystemYENNY implements Auxiliaries{
	private LinkedList<Autor> autores= new LinkedList<>();
	private LinkedList<Editorial> editoriales = new LinkedList<>();
	private LinkedList<Saga> sagas= new LinkedList<>();
	private LinkedList<Libro> libros = new LinkedList<>();
	private LinkedList<Ejemplar> ejemplares = new LinkedList<>();
	private LinkedList<Cliente> clientes = new LinkedList<>();
	private LinkedList<MetodoPago> metodosPago = new LinkedList<>();
	private LinkedList<Sucursal> sucursales = new LinkedList<>();
	private LinkedList<Usuario> usuarios = new LinkedList<>();
	private LinkedList<Venta> ventas = new LinkedList<>();
	private LinkedList<Promocion> promociones = new LinkedList<>();
	private LinkedList<PromocionLibro> promocionesLibros = new LinkedList<>();
	private boolean data;
	
	public SystemYENNY() {
		this.data = false;
	}
	
	public void generateData() {
		if(this.isData()) {
			JOptionPane.showMessageDialog(null, "La data ya ha sido generada.");
			return;
		} else {
			this.setData(true);
		}
		
		Autor[] autores = {
			    new Autor(1, "Gabriel García Márquez", "Colombiano"),
			    new Autor(2, "Haruki Murakami", "Japonés"),
			    new Autor(3, "Virginia Woolf", "Británica"),
			    new Autor(4, "Mark Twain", "Estadounidense"),
			    new Autor(5, "Jane Austen", "Británica"),
			    new Autor(6, "Charles Dickens", "Británico"),
			    new Autor(7, "J.K. Rowling", "Británica"),
			    new Autor(8, "Ernest Hemingway", "Estadounidense"),
			    new Autor(9, "George Orwell", "Británico"),
			    new Autor(10, "Fiodor Dostoievski", "Ruso")
			};
		
		for (Autor autor : autores) {
			this.getAutores().add(autor);
		}
		Editorial[] editoriales = {
			    new Editorial(1, "Planeta"),
			    new Editorial(2, "Penguin Random House"),
			    new Editorial(3, "Anagrama"),
			    new Editorial(4, "Alfaguara"),
			    new Editorial(5, "Debolsillo"),
			    new Editorial(6, "Salamandra"),
			    new Editorial(7, "Siglo XXI"),
			    new Editorial(8, "Akal"),
			    new Editorial(9, "Gredos"),
			    new Editorial(10, "Alianza Editorial")
			};
		
		for (Editorial editorial : editoriales) {
			this.getEditoriales().add(editorial);
		}
		
		Saga[] sagas = {
			    new Saga(1, "Harry Potter", 7, 1),
			    new Saga(2, "El Señor de los Anillos", 3, 1),
			    new Saga(3, "Los Juegos del Hambre", 3, 1),
			    new Saga(4, "Divergente", 3, 1),
			    new Saga(5, "Crepúsculo", 4, 1),
			    new Saga(6, "Fundación", 5, 1),
			    new Saga(7, "Maze Runner", 3, 1),
			    new Saga(8, "Percy Jackson y los Dioses del Olimpo", 5, 1),
			    new Saga(9, "Canción de Hielo y Fuego", 5, 1),
			    new Saga(10, "La Rueda del Tiempo", 14, 1)
			};
		
		for (Saga saga : sagas) {
			this.getSagas().add(saga);
		}
		
		Libro[] libros = {
			    new Libro(1, "Cien años de soledad", null, 1, 1),
			    new Libro(2, "Kafka en la orilla", null, 2, 2),
			    new Libro(3, "Mrs. Dalloway", null, 3, 3),
			    new Libro(4, "Las aventuras de Tom Sawyer", null, 4, 4),
			    new Libro(5, "Orgullo y Prejuicio", null, 5, 5),
			    new Libro(6, "Oliver Twist", null, 6, 6),
			    new Libro(7, "Harry Potter y la piedra filosofal", 1, 7, 7),
			    new Libro(8, "El viejo y el mar", null, 8, 8),
			    new Libro(9, "1984", null, 9, 9),
			    new Libro(10, "Crimen y castigo", null, 10, 10)
			};
		
		for (Libro libro : libros) {
			this.getLibros().add(libro);
		}
		
		Ejemplar[] ejemplares = {
			    new Ejemplar(1, 1, 1, "978-3-16-148410-0", 350.00, "Nuevo", true, false, 
			    		LocalDate.of(2021, 3, 15), 1, false, "Español", "Edición de coleccionista", LocalDate.of(2021, 3, 20), null),
			    new Ejemplar(2, 2, 1, "978-3-16-148410-1", 400.00, "Usado", false, true, LocalDate.of(2019, 7, 12), 2, true, "Inglés", null, LocalDate.of(2019, 8, 5),null),
			    // Add additional Ejemplares ensuring they reference existing libro_id and sucursal_id...
			};
		
		for (Ejemplar ejemplar : ejemplares) {
			this.getEjemplares().add(ejemplar);
		}
		
		Cliente[] clientes = {
			    new Cliente(1, "Masculino", 25, 1, true),
			    new Cliente(2, "Femenino", 32, 3, false),
			    new Cliente(3, "No binario", 20, 6, true),
			    new Cliente(4, "Masculino", 45, 7, true),
			    new Cliente(5, "Femenino", 27, 8, false),
			    new Cliente(6, "Masculino", 31, 9, true),
			    new Cliente(7, "Femenino", 29, 10, true),
			    new Cliente(8, "No binario", 35, 2, false),
			    new Cliente(9, "Masculino", 22, 4, true),
			    new Cliente(10, "Femenino", 30, 5, true)
			};
		
		for (Cliente cliente : clientes) {
			this.getClientes().add(cliente);
		}
		
		MetodoPago[] metodosPago = {
			    new MetodoPago(1, 1, "Tarjeta de crédito", "Visa termina en 1234"),
			    new MetodoPago(2, 2, "PayPal", "cuenta@ejemplo.com"),
			    new MetodoPago(3, 3, "Tarjeta de débito", "MasterCard termina en 5678"),
			    new MetodoPago(4, 4, "Efectivo", "Pago en tienda"),
			    new MetodoPago(5, 5, "Transferencia bancaria", "Banco: BBVA, Cuenta: 1122334455"),
			    new MetodoPago(6, 6, "Tarjeta de crédito", "Amex termina en 7890"),
			    new MetodoPago(7, 7, "Tarjeta de débito", "Visa termina en 3456"),
			    new MetodoPago(8, 8, "PayPal", "otracuenta@ejemplo.com"),
			    new MetodoPago(9, 9, "Efectivo", "Pago directo en evento"),
			    new MetodoPago(10, 10, "Tarjeta de crédito", "Discover termina en 0123")
			};
		
		for (MetodoPago metodoPago : metodosPago) {
			this.getMetodosPago().add(metodoPago);
		}
		
		Sucursal[] sucursales = {
			    new Sucursal(1, "Calle Falsa 123, Ciudad", "Sucursal Centro"),
			    new Sucursal(2, "Avenida Siempreviva 456, Ciudad", "Sucursal Norte"),
			    new Sucursal(3, "Plaza Springfield 789, Ciudad", "Sucursal Este"),
			    new Sucursal(4, "Bulevar del Parque 321, Ciudad", "Sucursal Oeste"),
			    new Sucursal(5, "Calle Prado 654, Ciudad", "Sucursal Sur"),
			    new Sucursal(6, "Calle Nueva 987, Ciudad", "Sucursal Universitaria"),
			    new Sucursal(7, "Avenida Principal 123, Ciudad", "Sucursal Comercial"),
			    new Sucursal(8, "Calle Secundaria 456, Ciudad", "Sucursal Local"),
			    new Sucursal(9, "Avenida del Bosque 789, Ciudad", "Sucursal Natural"),
			    new Sucursal(10, "Plaza Mayor 321, Ciudad", "Sucursal Principal")
			};
		
		for (Sucursal sucursal : sucursales) {
			this.getSucursales().add(sucursal);
		}
		
		Usuario[] usuarios = {
			    new Usuario(1, "Juan Pérez", 2, 1, "password1", "juanperez"),
			    new Usuario(2, "María López", 1, 1, "password2", "marialopez"),
			    new Usuario(3, "Carlos Jiménez", 2, 2, "password3", "carlosjimenez"),
			    new Usuario(4, "Laura García", 3, 2, "password4", "lauragarcia"),
			    new Usuario(5, "Roberto Fernández", 1, 3, "password5", "robertofernandez"),
			    new Usuario(6, "Ana Martínez", 1, 3, "password6", "anamartinez"),
			    new Usuario(7, "Lucía Hernández", 2, 4, "password7", "luciahernandez"),
			    new Usuario(8, "Miguel Ángel Domínguez", 1, 4, "password8", "migueldominguez"),
			    new Usuario(9, "Sofía Gómez", 1, 5, "password9", "sofiagomez"),
			    new Usuario(10, "Diego Rodríguez", 2, 5, "password10", "diegorodriguez")
			};
		
		for (Usuario usuario : usuarios) {
			Usuario usuarioAux;
			
			// int usuarioId, String nombre, int rol, int sucursalId, String pass, String userName
			
			switch(usuario.getRol()) {
				case 1: 
					usuarioAux = new Vendedor(usuario.getUsuarioId(), usuario.getNombre(), usuario.getRol(), usuario.getSucursalId(), usuario.getPass(), usuario.getUserName());
					break;
				case 2:
					usuarioAux = new GerenteSucursal(usuario.getUsuarioId(), usuario.getNombre(), usuario.getRol(), usuario.getSucursalId(), usuario.getPass(), usuario.getUserName());
					break;
				case 3:
					usuarioAux = new GerenteGeneral(usuario.getUsuarioId(), usuario.getNombre(), usuario.getRol(), usuario.getSucursalId(), usuario.getPass(), usuario.getUserName());
					break;
				default:
					usuarioAux = usuario;
					break;
			}
			
			this.getUsuarios().add(usuarioAux);
		}

		Venta[] ventas = {
			    new Venta(1, 1, LocalDate.of(2023, 4, 10)),
			    new Venta(2, 2, LocalDate.of(2023, 4, 11)),
			    new Venta(3, 3, LocalDate.of(2023, 4, 12)),
			    new Venta(4, 4, LocalDate.of(2023, 4, 13)),
			    new Venta(5, 5, LocalDate.of(2023, 4, 14)),
			    new Venta(6, 6, LocalDate.of(2023, 4, 15)),
			    new Venta(7, 7, LocalDate.of(2023, 4, 16)),
			    new Venta(8, 8, LocalDate.of(2023, 4, 17)),
			    new Venta(9, 9, LocalDate.of(2023, 4, 18)),
			    new Venta(10, 10, LocalDate.of(2023, 4, 19))
			};
		
		for (Venta venta : ventas) {
			this.getVentas().add(venta);
		}
		
		Promocion[] promociones = {
			    new Promocion(1, "Descuento Verano", false, 1, 10.00),
			    new Promocion(2, "Promo Club de Lectura", true, 2, 15.00),
			    new Promocion(3, "Descuento de Autor", false, 3, 5.00),
			    new Promocion(4, "Oferta Especial", false, 4, 20.00),
			    new Promocion(5, "Black Friday", false, 5, 25.00),
			    new Promocion(6, "Cyber Monday", false, 6, 30.00),
			    new Promocion(7, "Aniversario Tienda", false, 7, 15.00),
			    new Promocion(8, "Feria del Libro", true, 8, 50.00),
			    new Promocion(9, "Fin de Semana Sorpresa", false, 9, 12.00),
			    new Promocion(10, "Descuento Estudiante", true, 10, 8.00)
			};
		
		for (Promocion promocion : promociones) {
			this.getPromociones().add(promocion);
		}
		
		PromocionLibro[] promocionesLibros = {
			    new PromocionLibro(1, 1),
			    new PromocionLibro(1, 2),
			    new PromocionLibro(2, 3),
			    new PromocionLibro(2, 4),
			    new PromocionLibro(3, 5),
			    new PromocionLibro(3, 6),
			    new PromocionLibro(4, 7),
			    new PromocionLibro(4, 8),
			    new PromocionLibro(5, 9),
			    new PromocionLibro(5, 10)
			};
		
		for (PromocionLibro promocionLibro : promocionesLibros) {
			this.getPromocionesLibros().add(promocionLibro);
		}
	}

	public boolean isData() {
		return data;
	}

	public void setData(boolean data) {
		this.data = data;
	}

	public LinkedList<Autor> getAutores() {
		return autores;
	}

	public LinkedList<Editorial> getEditoriales() {
		return editoriales;
	}

	public LinkedList<Saga> getSagas() {
		return sagas;
	}

	public LinkedList<Libro> getLibros() {
		return libros;
	}

	public LinkedList<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public LinkedList<Cliente> getClientes() {
		return clientes;
	}

	public LinkedList<MetodoPago> getMetodosPago() {
		return metodosPago;
	}

	public LinkedList<Sucursal> getSucursales() {
		return sucursales;
	}

	public LinkedList<Usuario> getUsuarios() {
		return usuarios;
	}

	public LinkedList<Venta> getVentas() {
		return ventas;
	}

	public LinkedList<Promocion> getPromociones() {
		return promociones;
	}

	public LinkedList<PromocionLibro> getPromocionesLibros() {
		return promocionesLibros;
	}
	
	public <T> void addSomething(T aux, LinkedList<T> Lista) {
		Lista.add((T) aux);
	}
	
	public <T> void deleteSomething(int aux, LinkedList<T> Lista) {
		Lista.remove(aux);
	}
	
	public <T> T getSomethingById(int aux, LinkedList<T> Lista) {
		T objAux = null;
		for (T obj : Lista) {
			if(this.getId(obj) == aux && this.getId(obj) != null) {
				objAux = obj;
				break;
			}
		}
		
		return objAux;
	}
	
	public <T> String[] getAll(LinkedList<T> Lista) {
		String[] string = new String[Lista.size()];

		for (int i = 0; i < string.length; i++) {
			T obj = Lista.get(i);
			String stringAux = "";
			
			stringAux += "\n" + this.stringMaker(obj, i);
			
			string[i] = stringAux;
		}
		
		return string;
	}
	
	private <T> Integer getId(T Obj) {
		if(Obj instanceof Autor) {
			return ((Autor) Obj).getAutorId();
		} else if(Obj instanceof Cliente) {
			return ((Cliente) Obj).getClienteId();
		} else if(Obj instanceof Editorial) {
			return ((Editorial) Obj).getEditorialId();
		} else if(Obj instanceof Ejemplar) {
			return ((Ejemplar) Obj).getEjemplarId();
		} else if(Obj instanceof Usuario) {
			return ((Usuario) Obj).getUsuarioId();
		} else if(Obj instanceof Libro) {
			return ((Libro) Obj).getLibroId();
		} else if(Obj instanceof MetodoPago) {
			return ((MetodoPago) Obj).getMetodoPagoId();
		} else if(Obj instanceof Promocion) {
			return ((Promocion) Obj).getPromocionId();
		} else if(Obj instanceof Saga) {
			return ((Saga) Obj).getSagaId();
		} else if(Obj instanceof Sucursal) {
			return ((Sucursal) Obj).getSucursalId();
		} else if(Obj instanceof Venta) {
			return ((Venta) Obj).getVentaId();
		} else {
			return null;
		}
	}
	
	private <T> String stringMaker(T obj, int i) {
		if(obj instanceof Autor) {
			
			return i + ". " + ((Autor) obj).getNombre() 
					+ "\nNacionalidad: " + ((Autor) obj).getNacionalidad() 
					+ "\nId: " + ((Autor) obj).getAutorId();
			
		} else if(obj instanceof Cliente) {
			
			return i + ". Lugar de Compra: " + ((Cliente) obj).getLugar_de_compra() 
					+ "\nEdad: " + ((Cliente) obj).getEdad() 
					+ "\nId: " + ((Cliente) obj).getClienteId();
			
		} else if(obj instanceof Editorial) {
			
			return i + ". " + ((Editorial) obj).getNombre() 
					+ "\nId: " + ((Editorial) obj).getEditorialId();
			
		} else if(obj instanceof Ejemplar) {
			Libro auxL = this.getSomethingById(((Ejemplar) obj).getLibroId(), this.getLibros());
			Sucursal auxS = this.getSomethingById(((Ejemplar) obj).getSucursalId(), this.getSucursales());
			Autor auxA = this.getSomethingById(auxL.getAutorId(), this.getAutores());
			
			return i + ". Titulo: " + auxL.getTitulo()  
					+ "\nSucursal: " + auxS.getUbicacion() + " | " + auxS.getNombre()
					+ "\nAutor: " + auxA.getNombre()
					+ "\nISBN: " + ((Ejemplar) obj).getIsbn()
					+ "\nId: " + ((Ejemplar) obj).getEjemplarId();
			
		} else if(obj instanceof Usuario) {
			Sucursal auxS = this.getSomethingById(((Usuario) obj).getSucursalId(), this.getSucursales());
			
			return i + ". Lugar de Compra: " + ((Usuario) obj).getNombre()
					+ "\nEdad: " + ((Usuario) obj).getRol()
					+ "\nSucursal: " + auxS.getUbicacion() 
					+ " | " 
					+ auxS.getNombre();
			
		} else if(obj instanceof Libro) {
			Autor auxA = this.getSomethingById(((Libro) obj).getAutorId() , this.getAutores());
			Saga auxSa = ((Libro) obj).getSagaId() != null ? this.getSomethingById(((Libro) obj).getSagaId(), this.getSagas()) : null;
			
			String auxSaString = auxSa != null ? auxSa.getNombre() : "N/A"; 
			
			Editorial auxE = this.getSomethingById(((Libro) obj).getEditorialId() , this.getEditoriales());
			
			return i + ". Titulo: " + ((Libro) obj).getTitulo() 
					+ "\nAutor: " + auxA.getNombre() 
					+ "\nSaga: " + auxSaString
					+ "\nEditorial: " + auxE.getNombre() 
					+ "\nId: " + ((Libro) obj).getLibroId();
			
		} else if(obj instanceof MetodoPago) {
			return i + ". Detalles: " + ((MetodoPago) obj).getDetalles() 
					+ "\n Tipo: " + ((MetodoPago) obj).getTipo() 
					+ "\n Id: " + ((MetodoPago) obj).getMetodoPagoId();
			
		} else if(obj instanceof Promocion) {
			Sucursal auxS = this.getSomethingById(((Promocion) obj).getSucursalId(), this.getSucursales());
			
			return i + ". Nombre: " + ((Promocion) obj).getNombre() 
					+ "\nClub: " + ((Promocion) obj).isEsDelClub() 
					+ "\nDescuento: " + ((Promocion) obj).getDescuento() + "%"
					+ "\nSucursal: " + auxS.getUbicacion()
					+ "\nId: " + ((Promocion) obj).getPromocionId();
			
		} else if(obj instanceof Saga) {
			
			return i + ". Nombre: " + ((Saga) obj).getNombre() 
					+ "\nNumero de Libros: " + ((Saga) obj).getNumeroLibros() 
					+ "\nNumero Saga: " + ((Saga) obj).getNumeroSaga() 
					+ "\nId: " + ((Saga) obj).getSagaId();
			
		} else if(obj instanceof Sucursal) {
			
			return i + ". Ubicacion: " + ((Sucursal) obj).getUbicacion() 
					+ "\nNombre: " + ((Sucursal) obj).getNombre() 
					+ "\nId: " + ((Sucursal) obj).getSucursalId();
			
		} else if(obj instanceof Venta) {
			MetodoPago auxMP = this.getSomethingById(((Venta) obj).getMetodoPagoId(), this.getMetodosPago());
			Cliente auxC = this.getSomethingById(auxMP.getClienteId(), this.getClientes());
			
			return i + " Lugar de compra: " + auxC.getLugar_de_compra()
					+ ". Metodo de Pago: " + auxMP.getDetalles() + " | " 
					+  auxMP.getTipo()
					+ "\nFecha: " + ((Venta) obj).getFecha().toString() 
					+ "\nId: " + ((Venta) obj).getVentaId();
			
		} else {
			return null;
		}
	}
	
	public Usuario logIn() {
		Usuario userNAux = null, userPAux = null, userAux = null;
		boolean userN = true;
		boolean userP = true;
		
		do {
			
			String userName = JOptionPane.showInputDialog("Ingrese su nombre de usuario.");
			
			for (Usuario user : usuarios) {
				if(user.getUserName().equals(userName)) {
					userN = true;
					userNAux = user;
					break;
				} else {
					userN = false;
				}
			}
			
		} while(!userN);
		
		do {
			
			String userPass = JOptionPane.showInputDialog("Ingrese su contraseña.");
			
			for (Usuario user : usuarios) {
				if(user.getPass().equals(userPass)) {
					userP = true;
					userPAux = user;
					break;
				} else {
					userP = false;
				}
			}
			
			if(userNAux == userPAux) {
				userP = true;
				userAux = userNAux;
			} else {
				userP = false;
			}
			
		} while(!userP);
		
		return userAux;
	}
	
}
