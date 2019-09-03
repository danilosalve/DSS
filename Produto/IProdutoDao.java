package Produto;

import java.util.List;

public interface IProdutoDao {
	public boolean Atualizar(IModelProduto oModel);
	public boolean Inserir(IModelProduto oModel);
	public boolean Remover(int id);
	public boolean ExistProduto(int nId);
	public List<IModelProduto> ListProdutos();
	public IModelProduto getProdutoId(int nId);
	public List<IModelProduto> getProdutoDesc(String cDesc);
	public List<IModelProduto> getFabricanteDesc(String cFabricante);
}
