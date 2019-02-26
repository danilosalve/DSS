package Armazem;

/**
 * Interface - contem os metodos utilizados no objeto Model Armazem
 * @author Danilo Salve
 * @since 25/02/2019
 * @version 1.0
 */

public interface IModelArmazem {

	public void setId(int nId);	
	public void setDesc(String cDesc);
	public void setBloq(boolean lBloq);
	public int getId();
	public String getDesc();
	public boolean getBloq();
	
}
