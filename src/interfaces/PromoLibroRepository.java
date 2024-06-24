package interfaces;

import java.util.List;

import modelos.PromocionLibro;

public interface PromoLibroRepository {
List <PromocionLibro> getAllPromoLibros();
	
PromocionLibro getPromoLibroById(int id);
	
	void addPromoLibro(PromocionLibro user);
	
	void updatePromoLibro(PromocionLibro user);
	
	void detelePromoLibro(int id);
 

}
