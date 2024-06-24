package interfaces;
import java.util.List;

import modelos.Promocion;
public interface PromocionRepository {
	List <Promocion> getAllPromos();
	
	Promocion getPromoById(int id);
		
		void addPromo(Promocion promo);
		
		void updatePromo(Promocion promo);
		
		void deletePromo(int id);

		int lastPromo();
}
