package Cores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import System.SQLConect;

/**
 * Classe Responsavel pela camada de comunicaçao com a base de dados
 * @author Danilo Salve
 * @version 1.0
 * @since 01/03/2019
 */

public class CoresDao implements ICoresDao {
	
	private final String INSERT = " INSERT INTO dbo.tbCores (DESC_COR) VALUES (?) ";
	private final String UPDATE = " UPDATE dbo.tbCores SET DESC_COR = ? WHERE ID_COR = ? ";
	private final String DELETE = " UPDATE dbo.tbCores SET D_E_L_E_T_ = '*' WHERE ID_COR = ? ";
	private final String LIST = " SELECT ID_COR, DESC_COR FROM dbo.tbCores WHERE D_E_L_E_T_ = ' ' ORDER BY 1 ASC ";
	private final String LISTBYID = "SELECT ID_COR, DESC_COR FROM dbo.tbCores WHERE ID_COR = ? AND D_E_L_E_T_ = ' ' ORDER BY 1 ASC ";
	private final String LISTDESC = "SELECT ID_COR, DESC_COR FROM dbo.tbCores WHERE DESC_COR LIKE ? AND D_E_L_E_T_ = ' ' ORDER BY 2 ASC ";

	/**
	 * Altera Cor
	 * @autor Danilo Salve
	 * @since 01/03/2019
	 * @version 1.0
	 * @return boolean lRet - Retorno logico se conseguiu inserir registro 
	 */
	@Override
	public boolean Atualizar(IModelCores oModel) {
		boolean lRet = false;
		
        if (oModel != null) {
            Connection conn = null;            
            try {
                conn = SQLConect.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);
                pstm.setString(1, oModel.getDesc());
                pstm.setInt(2, oModel.getId());
                pstm.execute();
                lRet = true;
                SQLConect.fechaConexao(conn);
 
            } catch (Exception e) {
            	lRet = false;
                JOptionPane.showMessageDialog(null, "Erro ao atualizar Cor no banco de "
                        + "dados " + e.getMessage());
            }
        } else {
        	lRet = false;
            JOptionPane.showMessageDialog(null, "Objeto recebido por parâmetro está vazio");
        }
         
        return lRet;
	}
	/**
	 * Inclui Cor
	 * @autor Danilo Salve
	 * @since 01/03/2019
	 * @version 1.0
	 * @return boolean lRet - Retorno logico se conseguiu inserir registro 
	 */
	@Override
	public boolean Inserir(IModelCores oModel) {
		boolean lRet = false;
		if (oModel != null){
			Connection conn = null;
            try {
            	conn = SQLConect.getConexao();
            	PreparedStatement pstm;
            	pstm = conn.prepareStatement(INSERT);
             
            	pstm.setString(1, oModel.getDesc());
            	pstm.execute();
            
            	lRet = true;
            	SQLConect.fechaConexao(conn, pstm);
 
            } catch (Exception e) {
            	lRet = false;
                JOptionPane.showMessageDialog(null, "Erro ao inserir Cor no banco de "
                    + "dados " + "\n" + e.getMessage());
            }
		} else {
			lRet = false;
			System.out.println("Objeto enviado por parâmetro está vazio");
		}
		return lRet;
	}

	/**
	 * Atualiza campo D_E_L_E_T_ - Exclui registro com delete logico
	 * @autor Danilo Salve
	 * @since 01/03/2019
	 * @version 1.0
	 * @return boolean lRet- Retorna se conseguiu atualizar registro 
	 */
	public boolean Remover(int id) {
		boolean lRet = false;
        
    	Connection conn = null;
    	try {
    	   conn = SQLConect.getConexao();
           PreparedStatement pstm;
           pstm = conn.prepareStatement(DELETE);
 
           pstm.setInt(1, id);
           pstm.execute();
           lRet = true;
           SQLConect.fechaConexao(conn);
 
    	} catch (Exception e) {
    	   lRet = false;
           JOptionPane.showMessageDialog(null, "Erro ao atualizar Cor no banco de "
           + "dados " + e.getMessage());
        } 
        return lRet;
	}
	/**
	* Verifica se codigo do existe
	 * @autor Danilo Salve
	 * @since 01/03/2019
	 * @version 1.0
	 * @return boolean lRet
	 */
	@Override
	public boolean ExistCores(int nId) {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        boolean lRet = false;
        try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTBYID);
            pstm.setInt(1, nId);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	lRet = true;
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Cor" + e.getMessage());
        }
        return lRet;
	}
	
	/**
	 * Lista todas Cores
	 * @autor Danilo Salve
	 * @since 01/03/2019
	 * @version 1.0
	 * @return List IModelCor 
	 */
	@Override
	public List<IModelCores> ListCores() {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
    	ArrayList<IModelCores> oLista = new ArrayList<IModelCores>();
   
    	try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	IModelCores oModel = new ModelCores();
 
            	oModel.setId(rs.getInt("ID_COR"));
            	oModel.setDesc(rs.getString("DESC_COR"));
            	oLista.add(oModel);
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Cor " + e.getMessage());
        }    	
        return oLista;
	}
	/**
	 * Retorna uma cor utilizando um Id (Chave Primaria da tabela)
	 * @autor Danilo Salve
	 * @since 01/03/2019
	 * @version 1.0
	 * @return IModelCores oModel  
	 */
	@Override
	public IModelCores getCoresId(int id) {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        IModelCores oModel = new ModelCores();
        try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	oModel.setId(rs.getInt("ID_COR"));
            	oModel.setDesc(rs.getString("DESC_COR"));
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Cor" + e.getMessage());
        }
        return oModel;
	}
	/**
	 * Lista cores utilizando filtro de Descrição%
	 * @autor Danilo Salve
	 * @since 01/03/2019
	 * @version 1.0
	 * @return List IModelCores 
	 */
	@Override
	public List<IModelCores> getCoresDesc(String cDesc) {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;    	
    	ArrayList<IModelCores> oLista = new ArrayList<IModelCores>();
    	try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTDESC);
            pstm.setString(1, cDesc + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
            	IModelCores oModel = new ModelCores();            	 
            	oModel.setId(rs.getInt("ID_COR"));
            	oModel.setDesc(rs.getString("DESC_COR"));            	
            	oLista.add(oModel);
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Cores " + e.getMessage());
        }    	
        return oLista;
	}

}
