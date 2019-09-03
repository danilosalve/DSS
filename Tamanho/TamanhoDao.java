package Tamanho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import System.SQLConect;

public class TamanhoDao implements ITamanhoDao{
	
	private final String INSERT = " INSERT INTO dbo.tbTamanho (COD_TAM,DESC_TAM,PESO_TAM,ALT_TAM,LARG_TAM,COMP_TAM) Values (?,?,?,?,?,?) ";
	private final String UPDATE = " UPDATE dbo.tbTamanho SET DESC_TAM = ?, PESO_TAM = ?, ALT_TAM = ?, LARG_TAM = ?,COMP_TAM = ? WHERE COD_TAM = ? ";
	private final String DELETE = " UPDATE dbo.tbTamanho SET D_E_L_E_T_ = '*' WHERE COD_TAM = ? ";
	private final String LIST = " SELECT COD_TAM,DESC_TAM,PESO_TAM,ALT_TAM,LARG_TAM,COMP_TAM FROM dbo.tbTamanho WHERE D_E_L_E_T_ = ' ' ORDER BY 1 ASC ";
	private final String LISTBYID = "SELECT COD_TAM,DESC_TAM,PESO_TAM,ALT_TAM,LARG_TAM,COMP_TAM FROM dbo.tbTamanho WHERE COD_TAM = ? AND D_E_L_E_T_ = ' ' ORDER BY 1 ASC ";
	private final String LISTDESC = "SELECT COD_TAM,DESC_TAM,PESO_TAM,ALT_TAM,LARG_TAM,COMP_TAM FROM dbo.tbTamanho WHERE DESC_TAM LIKE ? AND D_E_L_E_T_ = ' ' ORDER BY 2 ASC ";

	/**
	 * Altera Tamanho
	 * @autor Danilo Salve
	 * @since 03/03/2019
	 * @version 1.0
	 * @return boolean lRet - Retorno logico se conseguiu inserir registro 
	 */
	public boolean Atualizar(IModelTamanho oModel) {
		boolean lRet = false;
		
        if (oModel != null) {
            Connection conn = null;            
            try {
                conn = SQLConect.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);
                pstm.setString(1, oModel.getDescricao());
                pstm.setDouble(2, oModel.getPeso());
                pstm.setDouble(3, oModel.getAltura());
                pstm.setDouble(4, oModel.getLargura());
                pstm.setDouble(5, oModel.getComprimento());
                pstm.setString(6, oModel.getCodigo());
                pstm.execute();
                lRet = true;
                SQLConect.fechaConexao(conn);
 
            } catch (Exception e) {
            	lRet = false;
                JOptionPane.showMessageDialog(null, "Erro ao atualizar Tamanho no banco de "
                        + "dados " + e.getMessage());
            }
        } else {
        	lRet = false;
            JOptionPane.showMessageDialog(null, "Objeto recebido por parâmetro está vazio");
        }
         
        return lRet;
	}
	/**
	 * Inclui Tamanho
	 * @autor Danilo Salve
	 * @since 03/03/2019
	 * @version 1.0
	 * @return boolean lRet - Retorno logico se conseguiu inserir registro 
	 */
	public boolean Inserir(IModelTamanho oModel) {
		boolean lRet = false;
		if (oModel != null){
			Connection conn = null;
            try {
            	conn = SQLConect.getConexao();
            	PreparedStatement pstm;
            	pstm = conn.prepareStatement(INSERT);
             
            	pstm.setString(1, oModel.getCodigo());
            	pstm.setString(2, oModel.getDescricao());
                pstm.setDouble(3, oModel.getPeso());
                pstm.setDouble(4, oModel.getAltura());
                pstm.setDouble(5, oModel.getLargura());
                pstm.setDouble(6, oModel.getComprimento());                
            	pstm.execute();
            
            	lRet = true;
            	SQLConect.fechaConexao(conn, pstm);
 
            } catch (Exception e) {
            	lRet = false;
                JOptionPane.showMessageDialog(null, "Erro ao inserir Tamanho no banco de "
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
	public boolean Remover(String cCodigo) {
		boolean lRet = false;
        
    	Connection conn = null;
    	try {
    	   conn = SQLConect.getConexao();
           PreparedStatement pstm;
           pstm = conn.prepareStatement(DELETE);
 
           pstm.setString(1, cCodigo);
           pstm.execute();
           lRet = true;
           SQLConect.fechaConexao(conn);
 
    	} catch (Exception e) {
    	   lRet = false;
           JOptionPane.showMessageDialog(null, "Erro ao atualizar Tamanho no banco de "
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
	public boolean ExistTamanho(String cCodigo) {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        boolean lRet = false;
        try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTBYID);
            pstm.setString(1, cCodigo);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	lRet = true;
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Tamanho" + e.getMessage());
        }
        return lRet;
	}
	
	/**
	 * Lista todos Tamanhos
	 * @autor Danilo Salve
	 * @since 03/03/2019
	 * @version 1.0
	 * @return List IModelTamanho 
	 */
	public List<IModelTamanho> ListTamanhos() {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
    	ArrayList<IModelTamanho> oLista = new ArrayList<IModelTamanho>();
   
    	try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	IModelTamanho oModel = new ModelTamanho();
            	
            	oModel.setCodigo(rs.getString("COD_TAM"));
            	oModel.setDescricao(rs.getString("DESC_TAM"));
            	oModel.setPeso(rs.getDouble("PESO_TAM"));
            	oModel.setAltura(rs.getDouble("ALT_TAM"));
            	oModel.setLargura(rs.getDouble("LARG_TAM"));
            	oModel.setComprimento(rs.getDouble("COMP_TAM"));
            	oLista.add(oModel);
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Tamanho " + e.getMessage());
        }    	
        return oLista;
	}
	/**
	 * Retorna uma cor utilizando um Id (Chave Primaria da tabela)
	 * @autor Danilo Salve
	 * @since 03/03/2019
	 * @version 1.0
	 * @return IModelTamanho oModel  
	 */
	public IModelTamanho getCodigo(String cCodigo) {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        IModelTamanho oModel = new ModelTamanho();
        try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTBYID);
            pstm.setString(1, cCodigo);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	oModel.setCodigo(rs.getString("COD_TAM"));
            	oModel.setDescricao(rs.getString("DESC_TAM"));
            	oModel.setPeso(rs.getDouble("PESO_TAM"));
            	oModel.setAltura(rs.getDouble("ALT_TAM"));
            	oModel.setLargura(rs.getDouble("LARG_TAM"));
            	oModel.setComprimento(rs.getDouble("COMP_TAM"));
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Tamanho" + e.getMessage());
        }
        return oModel;
	}
	/**
	 * Lista cores utilizando filtro de Descrição%
	 * @autor Danilo Salve
	 * @since 03/03/2019
	 * @version 1.0
	 * @return List IModelTamanho 
	 */
	public List<IModelTamanho> getTamanhoDesc(String cDescricao) {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;    	
    	ArrayList<IModelTamanho> oLista = new ArrayList<IModelTamanho>();
    	try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTDESC);
            pstm.setString(1, cDescricao + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
            	IModelTamanho oModel = new ModelTamanho();            	 
            	oModel.setCodigo(rs.getString("COD_TAM"));
            	oModel.setDescricao(rs.getString("DESC_TAM"));
            	oModel.setPeso(rs.getDouble("PESO_TAM"));
            	oModel.setAltura(rs.getDouble("ALT_TAM"));
            	oModel.setLargura(rs.getDouble("LARG_TAM"));
            	oModel.setComprimento(rs.getDouble("COMP_TAM"));            	
            	oLista.add(oModel);
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Tamanhos " + e.getMessage());
        }    	
        return oLista;
	}
}
