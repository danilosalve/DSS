package Fabricante;

public interface IControllerFabricante {
	public boolean VldCommit(IModelFabricante oModel, int nOpc);
	public boolean ExecCommit(IModelFabricante oModel, int nOpc);
}
