package Produto;

public class ControllerProduto implements IControllerProduto {

	@Override
	public boolean VldCommit(IModelProduto oModel, int nOpc) {
		return false;
	}

	@Override
	public boolean ExecCommit(IModelProduto oModel, int nOpc) {
		return false;
	}
}
