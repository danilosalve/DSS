package Tamanho;
import javax.swing.JOptionPane;

/**
 * Contem as validações relacionadas as regras de negócio
 * @author Danilo Salve
 * @since 03/03/2019
 * @version 1.0
 */

public class ControllerTamanho implements IControllerTamanho {
	private ITamanhoDao oDao = new TamanhoDao();
	/**
	 * Valida ação antes de executar o commit dos dados
	 * @autor Danilo Salve
	 * @since 03/03/2019
	 * @param IModelTamanho oModel 
	 * @param int nOpc - Ação a ser executada
	 * @version 1.0
	 */
	@Override
	public boolean VldCommit(IModelTamanho oModel, int nOpc) {
		boolean lRet = true;
		if (nOpc == 3){
			lRet = !oDao.ExistTamanho(oModel.getCodigo());
			if (!lRet){
				JOptionPane.showMessageDialog(null, "Tamanho ja cadastrado","EXIST",JOptionPane.ERROR_MESSAGE);
			}			
		}
		
		if (nOpc == 3 || nOpc == 4){
			
			if (lRet & oModel.getCodigo().isEmpty()){
				JOptionPane.showMessageDialog(null, "Um ou mais campos obrigatorios"
	    				+ "\nNão foram preenchidos."
						+ "\nCampo: Código","CAMPO OBRIGATORIO",JOptionPane.ERROR_MESSAGE);
				lRet = false;
			}
			
			if (lRet & oModel.getDescricao().isEmpty()){
				JOptionPane.showMessageDialog(null, "Um ou mais campos obrigatorios"
	    				+ "\nNão foram preenchidos."
						+ "\nCampo: Descrição","CAMPO OBRIGATORIO",JOptionPane.ERROR_MESSAGE);
				lRet = false;
			}
		}		
		return lRet;
	}
	/**
	 * Executa o commit dos dados
	 * @autor Danilo Salve
	 * @since 03/03/2019
	 * @param IModelTamanho oModel 
	 * @param int nOpc - Ação a ser executada
	 * @version 1.0
	 */
	@Override
	public boolean ExecCommit(IModelTamanho oModel, int nOpc) {
	boolean lRet = true;
		
		switch (nOpc){
		case 3:
			if (oDao.Inserir(oModel)){
				JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!","Sucesso",
						+ JOptionPane.INFORMATION_MESSAGE);
			} else {
				lRet = false;
			}
			break;
		case 4:
			if (oDao.Atualizar(oModel)){
				JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!","Sucesso",
						+ JOptionPane.INFORMATION_MESSAGE);
			} else {
				lRet = false;
			}
			break;
		case 5:
			if (oDao.Remover(oModel.getCodigo())){
				JOptionPane.showMessageDialog(null, "Cadastro excluido com sucesso!","Sucesso",
						+ JOptionPane.INFORMATION_MESSAGE);
			} else {
				lRet = false;
			}
			break;
		}		
		return lRet;

	}

}
