package Produto;

import java.util.List;

/**
 * Classe Responsavel pela camada de comunicaçao com a base de dados
 * @author Danilo Salve
 * @version 1.0
 * @since 16/03/2019
 */

public class ProdutoDao implements IProdutoDao {

	@Override
	public boolean Atualizar(IModelProduto oModel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Inserir(IModelProduto oModel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Remover(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ExistProduto(int nId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<IModelProduto> ListProdutos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IModelProduto getProdutoId(int nId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IModelProduto> getProdutoDesc(String cDesc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IModelProduto> getFabricanteDesc(String cFabricante) {
		// TODO Auto-generated method stub
		return null;
	}

}
