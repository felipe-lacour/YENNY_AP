package interfaces;
import java.util.List;

import modelos.Sucursal;

public interface SucuRepository {
	List <Sucursal> getAllBranches();
	
	Sucursal getBranchById(int id);
	
	void addBranch(Sucursal user);
	
	boolean updateBranch(Sucursal user);
	
	void deteleBranch(int id);
}
