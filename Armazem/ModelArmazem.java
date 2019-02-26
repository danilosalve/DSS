package Armazem;

import UnidadeMedida.IModelUnidMed;

/** Classe Model contem os dados do objeto Armazem
 * @author Danilo Otavio Lima Salve
 * @since 25/02/2019
 * @version 1.0
 * @see IModelArmazem
 */

public class ModelArmazem implements IModelArmazem {

	private int nId;
	private String cDesc;
	private boolean lBloq;
	
	@Override
	public void setId(int nId) {
		this.nId = nId;
	}

	@Override
	public void setDesc(String cDesc) {
		this.cDesc = cDesc;
	}

	@Override
	public void setBloq(boolean lBloq) {
		this.lBloq = lBloq;
	}

	@Override
	public int getId() {
		return nId;
	}

	@Override
	public String getDesc() {
		return cDesc;
	}

	@Override
	public boolean getBloq() {
		return lBloq;
	}

}
