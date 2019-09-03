package Cores;

/** Classe Model contem os dados do objeto Cores
 * @author Danilo Otavio Lima Salve
 * @since 01/03/2019
 * @version 1.0
 * @see IModelCores
 */

public class ModelCores implements IModelCores {
	
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
	
	public ModelCores(){
		this (0,null);
	}

	public ModelCores(int nId, String cDesc) {
		this.nId = nId;
		this.cDesc = cDesc;
	}
	
	public String toString(){
		return cDesc;
	}
	
}
