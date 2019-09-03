package Armazem;

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
 * @since 25/02/2019
 */

public class ArmazemDao implements IArmazemDao {
	private final String INSERT = " INSERT INTO dbo.tbArmazem (DESC_ARM , BLOQ_ARM, D_E_L_E_T_) VALUES (?,?,?) ";
	private final String UPDATE = " UPDATE dbo.tbArmazem SET DESC_ARM = ? , BLOQ_ARM  = ? WHERE ID_ARM = ? ";
	private final String DELETE = " UPDATE dbo.tbArmazem SET D_E_L_E_T_ = '*' WHERE ID_ARM = ? ";
	private final String LIST = " SELECT ID_ARM, DESC_ARM , BLOQ_ARM FROM dbo.tbArmazem WHERE D_E_L_E_T_ = ' ' ";
	private final String LISTBYID = "SELECT ID_ARM, DESC_ARM , BLOQ_ARM FROM dbo.tbArmazem WHERE ID_ARM = ? AND D_E_L_E_T_ = ' ' ";
	private final String LISTDESC = "SELECT ID_ARM, DESC_ARM , BLOQ_ARM FROM dbo.tbArmazem WHERE DESC_ARM LIKE ? AND D_E_L_E_T_ = ' ' ORDER BY 3 ASC ";
	
	/**
	 * Inclui Armazem
	 * @autor Danilo Salve
	 * @since 25/02/2019
	 * @version 1.0
	 * @return boolean lRet - Retorno logico se conseguiu inserir registro 
	 */
	@Override
	public boolean Inserir(IModelArmazem oModel) {
		boolean lRet = false;
		String cBloq = "2";
		if (oModel != null){
			Connection conn = null;
            try {
            	conn = SQLConect.getConexao();
            	PreparedStatement pstm;
            	pstm = conn.prepareStatement(INSERT);
            
            	if (oModel.getBloq()){
            		cBloq = "1";
            	} else {
            		cBloq = "2";
            	}
 
            	pstm.setString(1, oModel.getDesc());
            	pstm.setString(2, cBloq);
            	pstm.setString(3, " ");
 
            	pstm.execute();
            
            	lRet = true;
            	SQLConect.fechaConexao(conn, pstm);
 
            } catch (Exception e) {
            	lRet = false;
                JOptionPane.showMessageDialog(null, "Erro ao inserir Armazem no banco de "
                    + "dados " + "\n" + e.getMessage());
            }
		} else {
			lRet = false;
			System.out.println("Objeto enviado por parâmetro está vazio");
		}
		return lRet;	
	}
	/**
	 * Atualiza Armazem
	 * @autor Danilo Salve
	 * @since 25/02/2019
	 * @version 1.0
	 * @return boolean lRet - Retorna se conseguiu inserir registro 
	 */
	@Override	
	public boolean Atualizar(IModelArmazem oModel) {
		boolean lRet = false;
		String cBloq = "2";
        if (oModel != null) {
            Connection conn = null;
            
            try {
                conn = SQLConect.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);
                
                if (oModel.getBloq()){
                	cBloq = "1";
                } else {
                	cBloq = "2";
                }
 
                pstm.setString(1, oModel.getDesc());            
                pstm.setString(2, cBloq);
                pstm.setInt(3, oModel.getId());                
                pstm.execute();
                lRet = true;
                SQLConect.fechaConexao(conn);
 
            } catch (Exception e) {
            	lRet = false;
                JOptionPane.showMessageDialog(null, "Erro ao atualizar Armazem no banco de "
                        + "dados " + e.getMessage());
            }
        } else {
        	lRet = false;
            JOptionPane.showMessageDialog(null, "Objeto recebido por parâmetro está vazio");
        }
         
        return lRet;
    }
	/**
	 * Atualiza campo D_E_L_E_T_ - Exclui registro com delete logico
	 * @autor Danilo Salve
	 * @since 25/02/2019
	 * @version 1.0
	 * @return boolean lRet- Retorna se conseguiu atualizar registro 
	 */
	@Override
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
           JOptionPane.showMessageDialog(null, "Erro ao atualizar Armazem no banco de "
           + "dados " + e.getMessage());
        } 
        return lRet;
    }
	/**
	* Verifica se codigo do armazem existe
	 * @autor Danilo Salve
	 * @since 25/02/2019
	 * @version 1.0
	 * @return boolean lRet
	 */
	@Override
	public boolean ExistArmazem(int nId) {
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
            JOptionPane.showMessageDialog(null, "Erro ao listar Armazem" + e.getMessage());
        }
        return lRet;
	}
    /**
	 * Lista todos Armazens
	 * @autor Danilo Salve
	 * @since 25/02/2019
	 * @version 1.0
	 * @return List IModelArmazem 
	 */
	@Override
	public List<IModelArmazem> ListArmazem() {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
    	ArrayList<IModelArmazem> oLista = new ArrayList<IModelArmazem>();
   
    	try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	IModelArmazem oModel = new ModelArmazem();
 
            	oModel.setId(rs.getInt("ID_ARM"));
            	oModel.setDesc(rs.getString("DESC_ARM"));
            	if (rs.getInt("BLOQ_ARM") == 1){
            		oModel.setBloq(true);
            	} else {
            		oModel.setBloq(false);
            	}
            	oLista.add(oModel);
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Armazem " + e.getMessage());
        }    	
        return oLista;
	}
	/**
	 * Retorna um Armazem utilizando um Id (Chave Primaria da tabela)
	 * @autor Danilo Salve
	 * @since 25/02/2019
	 * @version 1.0
	 * @return IModelArmazem oModel  
	 */
	@Override
	public IModelArmazem getArmazemId(int id) {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        IModelArmazem oModel = new ModelArmazem();
        try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	oModel.setId(rs.getInt("ID_ARM"));
            	oModel.setDesc(rs.getString("DESC_ARM"));
            	if (rs.getInt("BLOQ_ARM") == 1){
            		oModel.setBloq(true);
            	} else {
            		oModel.setBloq(false);
            	}
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Armazem" + e.getMessage());
        }
        return oModel;
	}
    /**
	 * Lista armazens utilizando filtro de Descrição%
	 * @autor Danilo Salve
	 * @since 25/02/2019
	 * @version 1.0
	 * @return List IModelArmazem 
	 */
	@Override
	public List<IModelArmazem> getArmazemDesc(String cDesc) {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;    	
    	ArrayList<IModelArmazem> oLista = new ArrayList<IModelArmazem>();
    	try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTDESC);
            pstm.setString(1, cDesc + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
            	IModelArmazem oModel = new ModelArmazem();
            	 
            	oModel.setId(rs.getInt("ID_ARM"));
            	oModel.setDesc(rs.getString("DESC_ARM"));
            	if (rs.getInt("BLOQ_ARM") == 1){
            		oModel.setBloq(true);
            	} else {
            		oModel.setBloq(false);
            	}
            	oLista.add(oModel);
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Armazem " + e.getMessage());
        }    	
        return oLista;
	}

}
