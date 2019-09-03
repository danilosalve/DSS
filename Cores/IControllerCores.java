package Cores;

public interface IControllerCores {
	public boolean VldCommit(IModelCores oModel, int nOpc);
	public boolean ExecCommit(IModelCores oModel, int nOpc);
}
