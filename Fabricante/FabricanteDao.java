package Fabricante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Pessoa.*;
import System.SQLConect;

/**
 * Classe Responsavel pela camada de comunicaçao com a base de dados
 * @author Danilo Salve
 * @version 1.0
 * @since 04/03/2019
 */

public class FabricanteDao implements IFabricanteDao {
	
	private final String INSERT = " INSERT INTO dbo.tbFabricante (NOME_FAB, BLOQ_FAB, TIPO_FAB) VALUES (?,?,?) ";
	private final String UPDATE = " UPDATE dbo.tbFabricante SET NOME_FAB = ?, BLOQ_FAB = ?, TIPO_FAB = ? WHERE ID_FAB = ? ";
	private final String DELETE = " UPDATE dbo.tbFabricante SET D_E_L_E_T_ = '*' WHERE ID_FAB = ? ";
	private final String LIST = " SELECT ID_FAB, NOME_FAB, BLOQ_FAB, TIPO_FAB FROM dbo.tbFabricante WHERE D_E_L_E_T_ = ' ' ORDER BY 1 ASC ";
	private final String LISTBYID = "SELECT ID_FAB, NOME_FAB, BLOQ_FAB, TIPO_FAB FROM dbo.tbFabricante WHERE ID_FAB = ? AND D_E_L_E_T_ = ' ' ORDER BY 1 ASC ";
	private final String LISTDESC = "SELECT ID_FAB, NOME_FAB, BLOQ_FAB, TIPO_FAB FROM dbo.tbFabricante WHERE NOME_FAB LIKE ? AND D_E_L_E_T_ = ' ' ORDER BY 2 ASC ";

	@Override
	public boolean Atualizar(IModelFabricante oModel) {
		boolean lRet = false;
		int nBloq = 2;
		
        if (oModel != null) {
            Connection conn = null;            
            try {
                conn = SQLConect.getConexao();
                PreparedStatement pstm;
                
                if (oModel.getPessoa().isBloqueado()){
                	nBloq = 1;
                }
                pstm = conn.prepareStatement(UPDATE);
                pstm.setString(1, oModel.getPessoa().getNome());
                pstm.setInt(2, nBloq);
                pstm.setInt(3, oModel.getTipo());
                pstm.setInt(4, oModel.getPessoa().getId());
                pstm.execute();
                lRet = true;
                SQLConect.fechaConexao(conn);
 
            } catch (Exception e) {
            	lRet = false;
                JOptionPane.showMessageDialog(null, "Erro ao atualizar Fabricante no banco de "
                        + "dados " + e.getMessage());
            }
        } else {
        	lRet = false;
            JOptionPane.showMessageDialog(null, "Objeto recebido por parâmetro está vazio");
        }         
        return lRet;
	}

	@Override
	public boolean Inserir(IModelFabricante oModel) {
		boolean lRet = false;
		int nBloq = 2;
		
		if (oModel != null){
			Connection conn = null;
            try {
            	conn = SQLConect.getConexao();
            	PreparedStatement pstm;
            	pstm = conn.prepareStatement(INSERT);
            	if (oModel.getPessoa().isBloqueado()){
                	nBloq = 1;
                }
            	pstm.setString(1, oModel.getPessoa().getNome());
                pstm.setInt(2, nBloq);
                pstm.setInt(3, oModel.getTipo());
            	pstm.execute();
            
            	lRet = true;
            	SQLConect.fechaConexao(conn, pstm);
 
            } catch (Exception e) {
            	lRet = false;
                JOptionPane.showMessageDialog(null, "Erro ao inserir Fabricante no banco de "
                    + "dados " + "\n" + e.getMessage());
            }
		} else {
			lRet = false;
			System.out.println("Objeto enviado por parâmetro está vazio");
		}
		return lRet;
	}

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
           JOptionPane.showMessageDialog(null, "Erro ao atualizar Fabricante no banco de "
           + "dados " + e.getMessage());
        } 
        return lRet;

	}

	public boolean ExistFabricantes(int nId) {
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
            JOptionPane.showMessageDialog(null, "Erro ao listar Fabricante" + e.getMessage());
        }
        return lRet;
	}

	public List<IModelFabricante> ListFabricantes() {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
    	ArrayList<IModelFabricante> oLista = new ArrayList<IModelFabricante>();
   
    	try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	IModelFabricante oModel = new ModelFabricante();
            	IPessoa oPessoa = new Pessoa();
            	
            	oPessoa.setId(rs.getInt("ID_FAB"));
            	oPessoa.setNome(rs.getString("NOME_FAB"));
            	if (rs.getInt("BLOQ_FAB") == 1){
            		oPessoa.setStatus(true);
            	}
            	oModel.setPessoa(oPessoa);
            	oModel.setTipo(rs.getInt("TIPO_FAB"));
            	oLista.add(oModel);
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Fabricante " + e.getMessage());
        }    	
        return oLista;

	}

	public IModelFabricante getFabricanteId(int nId) {
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        IModelFabricante oModel = new ModelFabricante();
        try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTBYID);
            pstm.setInt(1, nId);
            rs = pstm.executeQuery();
            while (rs.next()) {
            	IPessoa oPessoa = new Pessoa();            	
            	oPessoa.setId(rs.getInt("ID_FAB"));
            	oPessoa.setNome(rs.getString("NOME_FAB"));
            	if (rs.getInt("BLOQ_FAB") == 1){
            		oPessoa.setStatus(true);
            	}
            	oModel.setPessoa(oPessoa);
            	oModel.setTipo(rs.getInt("TIPO_FAB"));
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Fabricante" + e.getMessage());
        }
        return oModel;
	}

	public List<IModelFabricante> getFabricantesDesc(String cDesc){
		Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;    	
    	ArrayList<IModelFabricante> oLista = new ArrayList<IModelFabricante>();
    	try {
            conn = SQLConect.getConexao();
            pstm = conn.prepareStatement(LISTDESC);
            pstm.setString(1, cDesc + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
            	IModelFabricante oModel = new ModelFabricante();
            	IPessoa oPessoa = new Pessoa();
            	
            	oPessoa.setId(rs.getInt("ID_FAB"));
            	oPessoa.setNome(rs.getString("NOME_FAB"));
            	if (rs.getInt("BLOQ_FAB") == 1){
            		oPessoa.setStatus(true);
            	}
            	oModel.setPessoa(oPessoa);
            	oModel.setTipo(rs.getInt("TIPO_FAB"));
            	oLista.add(oModel);
            }
            SQLConect.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Fabricante " + e.getMessage());
        }    	
        return oLista;
	}
}
