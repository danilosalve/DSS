package UnidadeMedida;

/**
 * Interface - contem os metodos utilizados no objeto Model Unidade de Medida
 * @author Danilo Salve
 * @since 20/02/2019
 * @version 1.0
 */

public interface IModelUnidMed {
	
	public void setId(int id);
	public void setCod(String cod);
	public void setDesc(String desc);
	public int getId();
	public String getCod();
	public String getDesc();
	
}
