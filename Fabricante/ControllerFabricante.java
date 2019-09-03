package Fabricante;

import javax.swing.JOptionPane;

/**
 * Contem as valida��es relacionadas as regras de neg�cio
 * @author Danilo Salve
 * @since 04/03/2019
 * @version 1.0
 */

public class ControllerFabricante implements IControllerFabricante{
	
	IFabricanteDao oDao = new FabricanteDao();
	/**
	 * Valida a��o antes de executar o commit dos dados
	 * @autor Danilo Salve
	 * @since 04/03/2019
	 * @param IModelFabricante oModel 
	 * @param int nOpc - A��o a ser executada
	 * @version 1.0
	 */
	@Override
	public boolean VldCommit(IModelFabricante oModel, int nOpc) {
		boolean lRet = true;
		if (nOpc == 3 || nOpc == 4){
			if (lRet & oModel.getPessoa().getNome().trim().isEmpty()){
				JOptionPane.showMessageDialog(null, "Um ou mais campos obrigatorios"
	    				+ "\nN�o foram preenchidos."
						+ "\nCampo: Nome","CAMPO OBRIGATORIO",JOptionPane.ERROR_MESSAGE);
				lRet = false;
			}
		}		
		return lRet;
	}
	/**
	 * Executa o commit dos dados
	 * @autor Danilo Salve
	 * @since 04/03/2019
	 * @param IModelFabricante oModel 
	 * @param int nOpc - A��o a ser executada
	 * @version 1.0
	 */
	@Override
	public boolean ExecCommit(IModelFabricante oModel, int nOpc) {
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
			if (oDao.Remover(oModel.getPessoa().getId())){
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

