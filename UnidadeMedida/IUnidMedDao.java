package UnidadeMedida;

import java.util.List;

/**
 * Interface - contem os metodos utilizados na classe UnidMedDao
 * @author Danilo Salve
 * @since 23/02/2019
 * @version 1.0
 */
public interface IUnidMedDao {
	public boolean Atualizar(IModelUnidMed oModel);
	public boolean Inserir(IModelUnidMed oModel);
	public boolean Remover(int id);
	public boolean ExistUnidMed(String cCod);
	public List<IModelUnidMed> getUnidMed();
	public IModelUnidMed getUnidMedById(int id);
	public IModelUnidMed getUnidMedCod(String cCod);
	public List<IModelUnidMed> getUnidMedDesc(String cDesc);
}
