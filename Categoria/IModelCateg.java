package Categoria;

/**
 * Interface da classe Model Categoria
 * @author Danilo Salve
 * @since 03/03/2019
 * @version 1.0
 */

public interface IModelCateg {
	
	public void setId(int nId);
	public void setDesc(String cDesc);
	public int getId();
	public String getDesc();
	public String toString();
	
}
