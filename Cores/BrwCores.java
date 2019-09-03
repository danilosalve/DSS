package Cores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.AbstractTableModel;

import Framework.FwCadastro;
import Framework.FwVldChar;

/**
 * Browse Cadastro de Cores 
 * @since 01/03/2019
 * @author Danilo Salve
 * @see package.Cores
 * @see method
 */
public class BrwCores extends JInternalFrame{
	private JPanel oPanelFundo;
    private JPanel oPanelBotoes;
    private JPanel oPanelPesq;
    private JTable oTable;
    private CoresTableModel oBrowse;
    List <IModelCores> lista;
    private JScrollPane oScroll;
    private JButton oBtInclui;
    private JButton oBtExclui;
    private JButton oBtAltera;
    private JButton oBtVisual;
    private JButton oBtPesq;
    private JTextField oTxPesq;
    private JComboBox oCbPesq;
    private ICoresDao oDao = new CoresDao();
    
    public BrwCores(){
    	super(" Cores | DSS", false, false ,false , true ); 
    	CriaTable();
        CriaJanela();
    }
    
    public void CriaTable(){        
    	oTable = new JTable(oBrowse);
    	LoadData();        
    }    
    private void LoadData(){
         
        lista = oDao.ListCores();
        oBrowse = new CoresTableModel(lista);
        oTable.setModel(oBrowse);
        TableAjust();
    }
    
    private void TableAjust(){
    	oTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	oTable.getColumnModel().getColumn(0).setPreferredWidth(100);
    	oTable.getColumnModel().getColumn(1).setPreferredWidth(700);
    }
    public void CriaJanela(){
    	oBtInclui = new JButton("Incluir");
        oBtExclui = new JButton("Excluir");
        oBtAltera = new JButton("Alterar");        
        oBtVisual = new JButton("Visualizar");
        oBtPesq = new JButton("Pesquisar");
        oCbPesq = new JComboBox();
        oTxPesq = new JTextField(30);
        oPanelBotoes = new JPanel();
        oPanelPesq = new JPanel();
        oScroll = new JScrollPane(oTable);
        oPanelFundo = new JPanel();
        oPanelFundo.setLayout(new BorderLayout());
        oPanelFundo.add(BorderLayout.CENTER, oScroll);
        oPanelBotoes.add(oBtInclui);
        oPanelBotoes.add(oBtExclui);
        oPanelBotoes.add(oBtAltera);
        oPanelBotoes.add(oBtVisual);
        oPanelPesq.add(oCbPesq);
        oPanelPesq.add(oTxPesq);
        oPanelPesq.add(oBtPesq);
        oPanelFundo.add(BorderLayout.SOUTH, oPanelBotoes);      
        oPanelFundo.add(BorderLayout.NORTH, oPanelPesq);    
        
        oCbPesq.setSize(100, 30);
        oCbPesq.addItem("ID");        
        oCbPesq.addItem("DESCRIÇÃO");
        		
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "EscPressed"
	    );
	    getRootPane().getActionMap().put("EscPressed", new AbstractAction() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		if (JOptionPane.showConfirmDialog(null,"Deseja realmente sair?")==JOptionPane.OK_OPTION){
	    			dispose();
	    		}
	    	}
	    });	 
	    	    
        getContentPane().add(oPanelFundo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 590);
        setVisible(true);
        oBtInclui.addActionListener(new oBtIncluiListener());
        oBtExclui.addActionListener(new oBtExcluiListener());
        oBtAltera.addActionListener(new oBtAlteraListener());
        oBtVisual.addActionListener(new oBtVisualListener());
        oBtPesq.addActionListener(new oBtPesqListener());
        DefineLayout();
        oTable.setAutoCreateRowSorter(true);
        oTable.setSelectionMode(0);
    }
    private void DefineLayout(){
    	//Definição das cores
    	oTable.setBackground(new Color(250, 250, 250));
    	oTable.getTableHeader().setBackground(Color.BLACK);
    	oTable.getTableHeader().setForeground(Color.WHITE);
    	oCbPesq.setBackground(new Color(250, 250, 250));
    	oPanelPesq.setBackground(new Color(250, 250, 250));
    	oPanelBotoes.setBackground(new Color(250, 250, 250));
    	oBtInclui.setBackground(new Color(0, 120, 0));	    
    	oBtVisual.setBackground(Color.BLACK);
    	oBtAltera.setBackground(Color.BLUE);
    	oBtExclui.setBackground(new Color(128, 0, 0));
	    oBtPesq.setBackground(new Color(0, 120, 0));
	    oBtInclui.setForeground(Color.WHITE);
	    oBtAltera.setForeground(Color.WHITE);
	    oBtVisual.setForeground(Color.WHITE);
	    oBtExclui.setForeground(Color.WHITE);
	    oBtPesq.setForeground(Color.WHITE);
	    //Adicionar Teclas de Atalho
	    oBtVisual.setMnemonic(KeyEvent.VK_V);
	    oBtExclui.setMnemonic(KeyEvent.VK_E);
	    oBtInclui.setMnemonic(KeyEvent.VK_I);
	    oBtAltera.setMnemonic(KeyEvent.VK_A);
	    oBtPesq.setMnemonic(KeyEvent.VK_P);
	    //Adiciona ToolTip
	    oBtVisual.setToolTipText("Visualizar registro selecionado");
	    oBtExclui.setToolTipText("Excluir o registro selecionado");
	    oBtInclui.setToolTipText("Incluir um novo registro");
	    oBtAltera.setToolTipText("Alterar o registro selecionado");
	    oBtPesq.setToolTipText("Pesquisar");
    }
    private class oBtIncluiListener implements ActionListener {
   	 
        public void actionPerformed(ActionEvent e) {			
			ViewCores oModelCor = new ViewCores(3, 0, oBrowse);		
			//FwCadastro oModelCor = new FwCadastro("Cores | DSS");
		    getParent().add(oModelCor);
		    oModelCor.setVisible(true);
        }
    }
    private class oBtAlteraListener implements ActionListener {
   	 
        public void actionPerformed(ActionEvent e) {			
        	int nLin = -1;
        	int nReg = 0;
        	nLin = oTable.getSelectedRow();
            if (nLin >= 0) {
            	nReg = (int) oTable.getValueAt(nLin,0);
            	ViewCores oModelCor = new ViewCores(4, nReg, oBrowse);		    
    		    getParent().add(oModelCor);
    		    oModelCor.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
            }
        }
        
    }
    private class oBtVisualListener implements ActionListener {
      	 
        public void actionPerformed(ActionEvent e) {			
        	int nLin = -1;
        	int nReg = 0;
        	nLin = oTable.getSelectedRow();
            if (nLin >= 0) {
            	nReg = (int) oTable.getValueAt(nLin,0);
            	ViewCores oModelCor = new ViewCores(2, nReg, oBrowse);		    
    		    getParent().add(oModelCor);
    		    oModelCor.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
            }
            
        }
    }
    private class oBtExcluiListener implements ActionListener {
     	 
        public void actionPerformed(ActionEvent e) {			
        	int nLin = -1;
        	int nReg = 0;
        	nLin = oTable.getSelectedRow();
            if (nLin >= 0) {
            	nReg = (int) oTable.getValueAt(nLin,0);
            	ViewCores oModelCor = new ViewCores(5, nReg, oBrowse);		    
    		    getParent().add(oModelCor);
    		    oModelCor.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
            }
        }
    }
    private class oBtPesqListener implements ActionListener {
    	 
        public void actionPerformed(ActionEvent e) {
        	int nIndex = oCbPesq.getSelectedIndex();
        	String cPesq = oTxPesq.getText();
        	IModelCores oModel = new ModelCores();
        	
        	List<IModelCores> oLista = new ArrayList<IModelCores>();
        	if (oTxPesq.getText().isEmpty()){
        		oBrowse.Refresh();
        	} else {
        		switch (nIndex){
        		case 0:
        			oBrowse.removeAll();        			
        			if (FwVldChar.isNumeric(cPesq)){
        				oModel = oDao.getCoresId(Integer.parseInt(cPesq));
            			if (oModel.getId()> 0){
            				oBrowse.addLine(oModel);
            			}        				
        			}        			
        			break;
        		case 1:
        			oBrowse.removeAll();        			
        			oLista = oDao.getCoresDesc(cPesq);
        			for (int i = 0; i < oLista.size() ; i++){
        				oModel = oLista.get(i);
        				if (oModel.getId()> 0){
                			oBrowse.addLine(oModel);            		        				
            			}        			
        			}
            		break;
        		}
        	}
        		
        }
    }
    
    public class CoresTableModel extends AbstractTableModel{
    	
    	private static final int COL_ID = 0;    	
    	private static final int COL_DESC = 1;	
    	private String[] colunas = new String[]{"Id", "Descrição"};
    	List<IModelCores> linhas;  	
    	
    	public void Refresh() {        
             
            lista = oDao.ListCores();
            oBrowse = new CoresTableModel(lista);
            oTable.setModel(oBrowse);
            TableAjust();
        }  
    	
    	public CoresTableModel(List<IModelCores> oModel) {
    		this.linhas = new ArrayList<>(oModel);
    	}    	 
	    public int getRowCount() {
	        return linhas.size();
	    }
	 
	    public int getColumnCount() {
	        return colunas.length;
	    }
    	 
    	public String getColumnName(int columnIndex) {
    	   return colunas[columnIndex];
    	}
    	 
	    public Class getColumnClass(int columnIndex) {
	        if (columnIndex == COL_ID) {
	            return Integer.class;
	        }
	        return String.class;
	    }
	 
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return false;
	    }
    	 
	    public Object getValueAt(int row, int column) {
	 
	        IModelCores m = linhas.get(row);
	 
	        if (column == COL_ID) {
	            return m.getId();	        
	        } else if (column == COL_DESC) {
	            return m.getDesc();    	        
	        }
	        return "";
	    }
    	 
	    public void setValueAt(Object aValue, int row, int column) {
	    	IModelCores u = linhas.get(row);
	        if (column == COL_ID) {
	            u.setId((Integer) aValue);	        
	        } else if (column == COL_DESC) {
	            u.setDesc(aValue.toString());
	        }
	    }
    	 
	    public IModelCores getArmazem(int indiceLinha) {
	        return linhas.get(indiceLinha);
	    }
    	 
	    public void addLine(IModelCores oModel) {
	        linhas.add(oModel);
	        int ultimoIndice = getRowCount() - 1;
	        fireTableRowsInserted(ultimoIndice, ultimoIndice);    	 
	    }
	 
	    public void updateLine(int indiceLinha, IModelCores oModel) {
	        linhas.set(indiceLinha, oModel);
	        fireTableRowsUpdated(indiceLinha, indiceLinha);    	 
	    }
	 
	    public void removeLine(int indiceLinha) {
	        linhas.remove(indiceLinha);
	        fireTableRowsDeleted(indiceLinha, indiceLinha);    	 
	    }
	    
	    public void removeAll(){
	    	int nI = oBrowse.getRowCount();
	    	if (nI > 0){	    		
	    		while (oBrowse.getRowCount() > 0){
	    			removeLine(--nI);	    			
	    		}	    		    		
	    	}
	    }
    }
}