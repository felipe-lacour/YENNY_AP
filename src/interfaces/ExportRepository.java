package interfaces;

import java.util.List;
import modelos.Export;

public interface ExportRepository {
    List<Export> getAllExports(); // llama a todos los export de la bdd
    
    Export getExportById(int id); //llama solo a uno, por su id
    
    void addExport(Export export); //añade export a la bdd
    
    void updateExport(Export export); //actualiza los export de la bdd
    
    void deleteExport(int id); //eliminar exports de la bdd
}