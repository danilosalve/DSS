package UnidadeMedida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import System.SQLConect;

/**
 * Executa os comandos SQL para atualização da base de  dados
 * @author Danilo Salve
 * @version 1.0
 * @since 23/02/2019
 */

public class UnidMedDao implements IUnidMedDao {
	
	private final String INSERT = " INSERT INTO dbo.tbUnidMed (CD_UM, DESC_UM, D_E_L_E_T_) VALUES (?,?,?) ";
	private final String UPDATE = " UPDATE dbo.tbUnidMed SET CD_UM = ? , DESC_UM = ? WHERE ID_UM = ? ";
	private final String DELETE = " UPDATE dbo.tbUnidMed SET D_E_L_E_T_ = '*' WHERE ID_UM = ? ";
	private final String LIST = " SELECT ID_UM, CD_UM, DESC_UM FROM dbo.tbUnidMed WHERE D_E_L_E_T_ = ' ' ";
	private final String LISTBYID = "SELECT ID_UM, CD_UM, DESC_UM FROM dbo.tbUnidMed WHERE ID_UM = ? AND D_E_L_E_T_ = ' ' ";
	private final String LISTBYCOD = "SELECT ID_UM, CD_UM, DESC_UM FROM dbo.tbUnidMed WHERE CD_UM = ? AND D_E_L_E_T_ = ' ' ORDER BY 1 ASC ";
	private final String LISTDESC = "SELECT ID_UM, CD_UM, DESC_UM FROM dbo.tbUnidMed WHERE DESC_UM LIKE ? AND D_E_L_E_T_ = ' ' ORDER BY 3 ASC ";
	/**
	 * Inclui unidade de medida
	 * @autor Danilo Salve
	 * @since 23/02/2019
	 * @version 1.0
	 * @return boolean - Retorno logico se conseguiu inserir registro 
	 */
	public boolean Inserir(IModelUnidMed oModel){
		boolean lRet = false;
		if (oModel != null){
			 Connection conn = null;
	            try {
	                conn = SQLConect.getConexao();
	                PreparedStatement pstm;
	                pstm = conn.prepareStatement(INSERT);
	 
	                pstm.setString(1, oModel.getCod());
	                pstm.setString(2, oModel.getDesc());
	                pstm.setString(3, " ");
	 
	                pstm.execute();
	                
	                lRet = true;
	                SQLConect.fechaConexao(conn, pstm);
	 
	            } catch (Exception e) {
	            	lRet = false;
	                JOptionPane.showMessageDialog(null, "Erro ao inserir Unidade de medida no banco de "
	                        + "dados " + "\n" + e.getMessage());
	            }
	        } else {
	        	lRet = false;
	            System.out.println("O Unidade de medida enviado por parâmetro está vazio");
	        }
			return lRet;
		}
	/**
	 * Atualiza unidade de medida
	 * @autor Danilo Salve
	 * @since 23/02/2019
	 * @version 1.0
	 * @return boolean - Retorno logico se conseguiu atualizar registro 
	 */
	public boolean Atualizar(IModelUnidMed oModel) {
		boolean lRet = false;
        if (oModel != null) {
            Connection conn = null;
            
            try {
                conn = SQLConect.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);
 
                pstm.setString(1, oModel.getCod());            
                pstm.setString(2, oModel.getDesc());
                pstm.setInt(3, oModel.getId());                
                pstm.execute();
                lRet = true;
                SQLConect.fechaConexao(conn);
 
            } catch (Exception e) {
            	lRet = false;
                JOptionPane.showMessageDialog(null, "Erro ao atualizar Unidade de medida no banco de "
                        + "dados " + e.getMessage());
            }
        } else {
        	lRet = false;
            JOptionPane.showMessageDialog(null, "Objeto Unidada de Medida recebida por parâmetro está vazio");
        }
         
        return lRet;
    }
	/**
	 * Atualiza campo D_E_L_E_T_ - Exclui registro com delete logico
	 * @autor Danilo Salve
	 * @since 23/02/2019
	 * @version 1.0
	 * @return boolean - Retorno logico se conseguiu atualizar registro 
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
           JOptionPane.showMessageDialog(null, "Erro ao atualizar Unidade de medida no banco de "
           + "dados " + e.getMessage());
        } 
        return lRet;
    }
    /**
	 * Retorna uma lista de unidades de medida
	 * @autor Danilo Salve
	 * @since 23/02/2019
	 * @version 1.0
	 * @return List Model Unidade de Medida 
	 */
    public List<IModelUnidMed> getUnidMed() {
    	Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
    	ArrayList<IModelUnidMed> oLstUnidMed = new ArrayList<IModelUnidMed>();
    	try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	IModelUnidMed oModel = new ModelUnidMed();
 
            	oModel.setId(rs.getInt("ID_UM"));
            	oModel.setCod(rs.getString("CD_UM"));
            	oModel.setDesc(rs.getString("DESC_UM"));                
                oLstUnidMed.add(oModel);
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Unidade de medida " + e.getMessage());
        }    	
        return oLstUnidMed;
    }
    /**
	 * Retorna uma unidade de medida pelo id (Chave Primaria da tabela)
	 * @autor Danilo Salve
	 * @since 23/02/2019
	 * @version 1.0
	 * @return objeto Model Unidade de Medida 
	 */
    public IModelUnidMed getUnidMedById(int id) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        IModelUnidMed oModel = new ModelUnidMed();
        try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	oModel.setId(rs.getInt("ID_UM"));
            	oModel.setCod(rs.getString("CD_UM"));
            	oModel.setDesc(rs.getString("DESC_UM"));
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar unidade de medida" + e.getMessage());
        }
        return oModel;
    }
    
    /**
	 * Retorna uma unidade de medida pelo id (Chave Primaria da tabela)
	 * @autor Danilo Salve
	 * @since 23/02/2019
	 * @version 1.0
	 * @return objeto Model Unidade de Medida 
	 */
    public IModelUnidMed getUnidMedCod(String cCod) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        IModelUnidMed oModel = new ModelUnidMed();
        try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTBYCOD);
            pstm.setString(1, cCod);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	oModel.setId(rs.getInt("ID_UM"));
            	oModel.setCod(rs.getString("CD_UM"));
            	oModel.setDesc(rs.getString("DESC_UM"));
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar unidade de medida" + e.getMessage());
        }
        return oModel;
    }
    
    /**
	* Verifica se codigo da unidade de medida já foi utilizado
	 * @autor Danilo Salve
	 * @since 23/02/2019
	 * @version 1.0
	 * @return boolean 
	 */
    public boolean ExistUnidMed(String cCod) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        boolean lRet = false;
        try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTBYCOD);
            pstm.setString(1, cCod);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	lRet = true;
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Unidade de medida" + e.getMessage());
        }
        return lRet;
    }
    
    /**
	 * Retorna uma lista de unidades de medida
	 * @autor Danilo Salve
	 * @since 23/02/2019
	 * @version 1.0
	 * @return List Model Unidade de Medida 
	 */
    public List<IModelUnidMed> getUnidMedDesc(String cDesc) {
    	Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
    	ArrayList<IModelUnidMed> oLstUnidMed = new ArrayList<IModelUnidMed>();
    	try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTDESC);
            pstm.setString(1, cDesc + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
            	IModelUnidMed oModel = new ModelUnidMed();
 
            	oModel.setId(rs.getInt("ID_UM"));
            	oModel.setCod(rs.getString("CD_UM"));
            	oModel.setDesc(rs.getString("DESC_UM"));                
                oLstUnidMed.add(oModel);
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Unidade de medida " + e.getMessage());
        }    	
        return oLstUnidMed;
    }

}
