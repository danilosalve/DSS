package Fabricante;

import java.util.List;

public interface IFabricanteDao {
	public boolean Atualizar(IModelFabricante oModel);
	public boolean Inserir(IModelFabricante oModel);
	public boolean Remover(int id);
	public boolean ExistFabricantes(int nId);
	public List<IModelFabricante> ListFabricantes();
	public IModelFabricante getFabricanteId(int nId);
	public List<IModelFabricante> getFabricantesDesc(String cDesc);
}
