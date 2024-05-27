package interfaces;

import java.util.List;

import modelos.Promocion;

public interface PromocionRepository {
	List<Promocion> getAllPromocion(); // llama a todos los usuarios de la bdd
    
	Promocion getPromocionById(int id); //llama solo a uno, por su id
    
    void addPromocion(Promocion Promocion); //añade usuarios a la bdd
    
    void updatePromocion(Promocion Promocion); //actualiza los usuarios de la bdd
    
    void deletePromocion(int id); //eliminar usuarios de la bdd
}