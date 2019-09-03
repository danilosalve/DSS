package Categoria;

/**
 * Model Categoria
 * @author Danilo Salve
 * @since 03/03/2019
 * @version 1.0
 */

public class ModelCateg implements IModelCateg {
	
	private int nId;
	private String cDesc;

	@Override
	public void setId(int nId) {
		this.nId = nId;
	}

	@Override
	public void setDesc(String cDesc) {
		this.cDesc = cDesc;
	}

	@Override
	public int getId() {
		return nId;
	}

	@Override
	public String getDesc() {
		return cDesc;
	}
	
	public String toString(){
		return cDesc;
	}
}
