package interfaces;

import java.util.List;
import modelos.Venta;

public interface VentaRepositorio {
	
    List<Venta> getAllSales(); // llama a todos los Ventas de la bdd
    
    Venta getSaleById(int id); //llama solo a una, por su id
    
    int addSale(Venta venta); //añade Ventas a la bdd
    
    void updateSale(Venta venta); //actualiza las Ventas de la bdd
    
    void deleteSale(int id); //eliminar Ventas de la bdd
}
