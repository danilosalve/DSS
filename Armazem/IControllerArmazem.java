package Armazem;

public interface IControllerArmazem {
	public boolean VldCommit(IModelArmazem oModel, int nOpc);
	public boolean ExecCommit(IModelArmazem oModel, int nOpc);
}
