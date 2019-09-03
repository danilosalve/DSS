package Cores;

import java.util.List;

/**
 * Interface - contem os metodos utilizados na classe CoresDao
 * @author Danilo Salve
 * @since 01/03/2019
 * @version 1.0
 */

public interface ICoresDao {
	
	public boolean Atualizar(IModelCores oModel);
	public boolean Inserir(IModelCores oModel);
	public boolean Remover(int id);
	public boolean ExistCores(int nId);
	public List<IModelCores> ListCores();
	public IModelCores getCoresId(int id);
	public List<IModelCores> getCoresDesc(String cDesc);

}
