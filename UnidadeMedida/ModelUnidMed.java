package UnidadeMedida;

/** Classe Model contem os dados do objeto Unidade de Medida
 * @author Danilo Otavio Lima Salve
 * @since 20/02/2019
 * @version 1.0
 * @see IModelUnidMed
 */

public class ModelUnidMed implements IModelUnidMed {
	
	private int id;
	private String cod;
	private String desc;
	
	@Override
	public void setId(int id){
		this.id = id;
	}
	
	@Override
	public void setCod(String cod) {
		this.cod = cod;
	}

	@Override
	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getCod() {
		return cod;
	}

	@Override
	public String getDesc() {
		return desc;
	}
	
	public String toString(){
		return cod + " | " + desc;
	}
}
