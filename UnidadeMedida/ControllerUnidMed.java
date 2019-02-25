package UnidadeMedida;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import System.SqlServer;

/**
 * Interface - objeto Controller Unidade de Medida
 * @author Danilo Salve
 * @since 23/02/2019
 * @version 1.0
 */

public class ControllerUnidMed implements IControllerUnidMed {
	IUnidMedDao oDao = new UnidMedDao();
	/**
	 * Valida ação antes de executar o commit dos dados
	 * @autor Danilo Salve
	 * @since 23/02/2019
	 * @version 1.0
	 */
	@Override
	public boolean VldCommit(IModelUnidMed oModel, int nOpc) {
		
		boolean lRet = true;		
		
		if (nOpc == 3 || nOpc == 4){
			if (lRet & oModel.getCod().isEmpty()){
				JOptionPane.showMessageDialog(null, "Um ou mais campos obrigatorios"
	    				+ "\nNão foram preenchidos."
						+ "\nCampo: Codigo","CAMPO OBRIGATORIO",JOptionPane.ERROR_MESSAGE);
				lRet = false;
			}
			
			if (lRet & oModel.getDesc().isEmpty()){
				JOptionPane.showMessageDialog(null, "Um ou mais campos obrigatorios"
	    				+ "\nNão foram preenchidos."
						+ "\nCampo: Descrição","CAMPO OBRIGATORIO",JOptionPane.ERROR_MESSAGE);
				lRet = false;
			}
			
			if (lRet & nOpc == 3 & oDao.ExistUnidMed(oModel.getCod())){
				lRet = false;
				JOptionPane.showMessageDialog(null, "ATENÇÃO"
	    				+ "\n Já existe Unidade de Medida "
						+ "\n Informada com Codigo " + oModel.getCod(),"EXISTE",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		return lRet;
	}
	/**
	 * Executa o commit dos dados
	 * @autor Danilo Salve
	 * @since 23/02/2019
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
