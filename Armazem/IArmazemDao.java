package Armazem;
import java.util.List;

/**
 * Interface - contem os metodos utilizados na classe ArmazemDao
 * @author Danilo Salve
 * @since 25/02/2019
 * @version 1.0
 */
public interface IArmazemDao {
	
	public boolean Atualizar(IModelArmazem oModel);
	public boolean Inserir(IModelArmazem oModel);
	public boolean Remover(int id);
	public boolean ExistArmazem(int nId);
	public List<IModelArmazem> ListArmazem();
	public IModelArmazem getArmazemId(int id);
	public List<IModelArmazem> getArmazemDesc(String cDesc);
	
}
