package UnidadeMedida;

/**
 * Interface - contem os metodos utilizados no objeto Controller Unidade de Medida
 * @author Danilo Salve
 * @since 23/02/2019
 * @version 1.0
 */

public interface IControllerUnidMed {
	
	public boolean VldCommit(IModelUnidMed oModel, int nOpc);
	public boolean ExecCommit(IModelUnidMed oModel, int nOpc);
}
