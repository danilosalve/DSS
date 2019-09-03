package Categoria;

import java.util.List;

/**
 * Interface - contem os metodos utilizados na classe CategDao
 * @author Danilo Salve
 * @since 03/03/2019
 * @version 1.0
 */

public interface ICategDao {
	public boolean Atualizar(IModelCateg oModel);
	public boolean Inserir(IModelCateg oModel);
	public boolean Remover(int id);
	public boolean ExistCateg(int nId);
	public List<IModelCateg> ListCategorias();
	public IModelCateg getCategoriaId(int id);
	public List<IModelCateg> getCategoriasDesc(String cDesc);
}
