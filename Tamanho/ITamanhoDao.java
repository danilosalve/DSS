package Tamanho;

import java.util.List;

/**
 * Interface - contem os metodos utilizados na classe TamanhoDao
 * @author Danilo Salve
 * @since 03/03/2019
 * @version 1.0
 */

public interface ITamanhoDao {
	public boolean Atualizar(IModelTamanho oModel);
	public boolean Inserir(IModelTamanho oModel);
	public boolean Remover(String cCodigo);
	public boolean ExistTamanho(String cCodigo);
	public List<IModelTamanho> ListTamanhos();
	public IModelTamanho getCodigo(String cCodigo);
	public List<IModelTamanho> getTamanhoDesc(String cDescricao);
}
