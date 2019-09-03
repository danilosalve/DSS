package Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import System.SQLConect;

public class CategDao implements ICategDao {
	private final String INSERT = " INSERT INTO dbo.tbCategoria (DESC_CATEG) VALUES (?) ";
	private final String UPDATE = " UPDATE dbo.tbCategoria SET DESC_CATEG = ? WHERE ID_CATEG = ? ";
	private final String DELETE = " UPDATE dbo.tbCategoria SET D_E_L_E_T_ = '*' WHERE ID_CATEG = ? ";
	private final String LIST = " SELECT ID_CATEG, DESC_CATEG FROM dbo.tbCategoria WHERE D_E_L_E_T_ = ' ' ORDER BY 1 ASC ";
	private final String LISTBYID = "SELECT ID_CATEG, DESC_CATEG FROM dbo.tbCategoria WHERE ID_CATEG = ? AND D_E_L_E_T_ = ' ' ORDER BY 1 ASC ";
	private final String LISTDESC = "SELECT ID_CATEG, DESC_CATEG FROM dbo.tbCategoria WHERE DESC_CATEG LIKE ? AND D_E_L_E_T_ = ' ' ORDER BY 2 ASC ";

	/**
	 * Altera Categoria
	 * @autor Danilo Salve
	 * @since 03/03/2019
	 * @version 1.0
	 * @return boolean lRet - Retorno logico se conseguiu inserir registro 
	 */
	@Override
	public boolean Atualizar(IModelCateg oModel) {
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
                JOptionPane.showMessageDialog(null, "Erro ao atualizar Categoria no banco de "
                        + "dados " + e.getMessage());
            }
        } else {
        	lRet = false;
            JOptionPane.showMessageDialog(null, "Objeto recebido por parâmetro está vazio");
        }
         
        return lRet;
	}
	/**
	 * Inclui Categoria
	 * @autor Danilo Salve
	 * @since 03/03/2019
	 * @version 1.0
	 * @return boolean lRet - Retorno logico se conseguiu inserir registro 
	 */
	@Override
	public boolean Inserir(IModelCateg oModel) {
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
                JOptionPane.showMessageDialog(null, "Erro ao inserir Categoria no banco de "
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
	 * @since 03/03/2019
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
           JOptionPane.showMessageDialog(null, "Erro ao atualizar Categoria no banco de "
           + "dados " + e.getMessage());
        } 
        return lRet;
	}
	/**
	* Verifica se codigo do existe
	 * @autor Danilo Salve
	 * @since 03/03/2019
	 * @version 1.0
	 * @return boolean lRet
	 */
	@Override
	public boolean ExistCateg(int nId) {
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
            JOptionPane.showMessageDialog(null, "Erro ao listar Categoria" + e.getMessage());
        }
        return lRet;
	}
	
	/**
	 * Lista todas Categorias
	 * @autor Danilo Salve
	 * @since 03/03/2019
	 * @version 1.0
	 * @return List IModelCategoria 
	 */
	@Override
	public List<IModelCateg> ListCategorias() {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
    	ArrayList<IModelCateg> oLista = new ArrayList<IModelCateg>();
   
    	try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	IModelCateg oModel = new ModelCateg();
 
            	oModel.setId(rs.getInt("ID_CATEG"));
            	oModel.setDesc(rs.getString("DESC_CATEG"));
            	oLista.add(oModel);
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Categoria " + e.getMessage());
        }    	
        return oLista;
	}
	/**
	 * Retorna uma cor utilizando um Id (Chave Primaria da tabela)
	 * @autor Danilo Salve
	 * @since 03/03/2019
	 * @version 1.0
	 * @return IModelCateg oModel  
	 */
	@Override
	public IModelCateg getCategoriaId(int id) {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        IModelCateg oModel = new ModelCateg();
        try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	oModel.setId(rs.getInt("ID_CATEG"));
            	oModel.setDesc(rs.getString("DESC_CATEG"));
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Categoria" + e.getMessage());
        }
        return oModel;
	}
	/**
	 * Lista cores utilizando filtro de Descrição%
	 * @autor Danilo Salve
	 * @since 03/03/2019
	 * @version 1.0
	 * @return List IModelCateg 
	 */
	@Override
	public List<IModelCateg> getCategoriasDesc(String cDesc) {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;    	
    	ArrayList<IModelCateg> oLista = new ArrayList<IModelCateg>();
    	try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTDESC);
            pstm.setString(1, cDesc + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
            	IModelCateg oModel = new ModelCateg();            	 
            	oModel.setId(rs.getInt("ID_CATEG"));
            	oModel.setDesc(rs.getString("DESC_CATEG"));            	
            	oLista.add(oModel);
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Categorias " + e.getMessage());
        }    	
        return oLista;
	}
}
