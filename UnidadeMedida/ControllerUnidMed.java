package UnidadeMedida;

import javax.swing.JOptionPane;

/**
 * Classe Controller - Implementa��o da regra de negocio
 * @author Danilo Salve
 * @since 23/02/2019
 * @version 1.0
 */

public class ControllerUnidMed implements IControllerUnidMed {
	private final IUnidMedDao oDao = new UnidMedDao();
	/**
	 * Valida a��o antes de executar o commit dos dados
	 * @autor Danilo Salve
	 * @since 23/02/2019
	 * @param IModelUnidMed oModel 
	 * @param int nOpc - A��o a ser executada
	 * @version 1.0
	 */
	@Override
	public boolean VldCommit(IModelUnidMed oModel, int nOpc) {
		
		boolean lRet = true;		
		
		if (nOpc == 3 || nOpc == 4){
			if (lRet & oModel.getCod().isEmpty()){
				JOptionPane.showMessageDialog(null, "Um ou mais campos obrigatorios"
	    				+ "\nN�o foram preenchidos."
						+ "\nCampo: Codigo","CAMPO OBRIGATORIO",JOptionPane.ERROR_MESSAGE);
				lRet = false;
			}
			
			if (lRet & oModel.getDesc().isEmpty()){
				JOptionPane.showMessageDialog(null, "Um ou mais campos obrigatorios"
	    				+ "\nN�o foram preenchidos."
						+ "\nCampo: Descri��o","CAMPO OBRIGATORIO",JOptionPane.ERROR_MESSAGE);
				lRet = false;
			}
			
			if (lRet & nOpc == 3 & oDao.ExistUnidMed(oModel.getCod())){
				lRet = false;
				JOptionPane.showMessageDialog(null, "ATEN��O"
	    				+ "\n J� existe Unidade de Medida "
						+ "\n Informada com Codigo " + oModel.getCod(),"EXISTE",JOptionPane.ERROR_MESSAGE);
			}
		}		
		return lRet;
	}
	/**
	 * Executa o commit dos dados
	 * @autor Danilo Salve
	 * @since 23/02/2019
	 * @param IModelUnidMed oModel 
	 * @param int nOpc - A��o a ser executada
	 * @version 1.0
	 */
	@Override
	public boolean ExecCommit(IModelUnidMed oModel, int nOpc) {

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
			if (oDao.Remover(oModel.getId())){
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
