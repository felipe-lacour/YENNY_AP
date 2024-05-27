package modelos;

import modelos.Promocion;

import javax.swing.JOptionPane;

import controladores.PromocionControlador;
import interfaces.PromocionRepository;


public class ClubDelLibro {
	private PromocionControlador PromocionControlador;
	
	public ClubDelLibro() {
		this.PromocionControlador= new PromocionControlador();
	}
	public void agregarPromocion(Promocion promocion) {
		PromocionControlador.addPromocion(promocion);
	}
	public void actualizarPromocion(Promocion promocion) {
		PromocionControlador.updatePromocion(promocion);
	}
	public void eliminarPromocion(int id) {
		PromocionControlador.deletePromocion(id);
	}
	public Promocion idPromocion(int id) {
		return PromocionControlador.getPromocionById(id);
	}
	public void mostrarTodasPromociones() {
		for(Promocion promocion : PromocionControlador.getAllPromocion()) {
			JOptionPane.showMessageDialog(null, promocion.getNombre()+ ": "+ "\n Descuento: "+promocion.getDescuento()+"%");
		}
	}

	public void NuevaPromocion() {
		String nom,opc;
		boolean esClub= false;
		int suc;
		double dsc;
		nom= JOptionPane.showInputDialog("Ingrese el nombre de la promocion");
		suc=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de sucursal donde es valida la promocion"));
		dsc=Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad de descuento"));
		opc=JOptionPane.showInputDialog("La promocione es solo valida para el club del libro \n si o no ");
		if (opc.equalsIgnoreCase("si")) {
			esClub=true;
		} else if(opc.equalsIgnoreCase("no")){
			esClub=true;
		}
		Promocion nuevaPromocion = new Promocion(0,nom,esClub,suc,dsc);
		this.agregarPromocion(nuevaPromocion);
	}
	
	
	public void ModPromocion() {
		String opc, nom;
		int dat;
		boolean esClub= false;
		int suc;
		double dsc;
		this.mostrarTodasPromociones();
		dat=Integer.parseInt("Seleccione la promocion a modificar");
		opc=JOptionPane.showInputDialog("Ingrese lo que quiere modificar de la promocion \n nombre - sucursal \n solo club del libro - descuento - s para salir");
		Promocion PromocionExis = this.idPromocion(dat);
		if (PromocionExis != null) {
			if (opc.equalsIgnoreCase("Nombre")&& opc.equalsIgnoreCase("sucursal")&& opc.equalsIgnoreCase("solo club del libro")&& opc.equalsIgnoreCase("descuento")){
				nom= JOptionPane.showInputDialog("Ingrese el nombre de la promocion");
				suc=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de sucursal donde es valida la promocion"));
				dsc=Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad de descuento"));
				opc=JOptionPane.showInputDialog("La promocione es solo valida para el club del libro \n si o no ");
				if (opc.equalsIgnoreCase("si")) {
					esClub=true;
				} else if(opc.equalsIgnoreCase("no")){
					esClub=true;
				}
				PromocionExis.setNombre(nom);
				PromocionExis.setEsDelClub(esClub);
				PromocionExis.setSucursalId(suc);
				PromocionExis.setDescuento(dsc);
				this.actualizarPromocion(PromocionExis);
		} else if (opc.equalsIgnoreCase("Nombre")){ 
			nom= JOptionPane.showInputDialog("Ingrese el nombre de la promocion");
			PromocionExis.setNombre(nom);
			this.actualizarPromocion(PromocionExis);
		}else if(opc.equalsIgnoreCase("sucursal")) {
			suc=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de sucursal donde es valida la promocion"));
			PromocionExis.setSucursalId(suc);
			this.actualizarPromocion(PromocionExis);
		}else if(opc.equalsIgnoreCase("solo club del libro")) {
			opc=JOptionPane.showInputDialog("La promocione es solo valida para el club del libro \n si o no ");
			if (opc.equalsIgnoreCase("si")) {
				esClub=true;
				
			} else if(opc.equalsIgnoreCase("no")){
				esClub=true;
			}
			PromocionExis.setEsDelClub(esClub);
			this.actualizarPromocion(PromocionExis);
		}else if(opc.equalsIgnoreCase("descuento")) {
			dsc=Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad de descuento"));
			PromocionExis.setDescuento(dsc);
			this.actualizarPromocion(PromocionExis);
		}
	}
		
	}
	
	public void EliminarPromocion() {
		int opc;
		this.mostrarTodasPromociones();
		opc= Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de la promocion a eliminar"));
		this.eliminarPromocion(opc);
	}
	
	
}
