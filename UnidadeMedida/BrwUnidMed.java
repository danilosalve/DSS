package UnidadeMedida;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import Framework.FwVldChar;

/**
 * Browse Cadastro de Unidade de Medida
 * 
 * @since 24/02/2019
 * @author Danilo Salve
 * @see package.UnidadeMedida
 * @see method
 */
public class BrwUnidMed extends JInternalFrame {
	
	private JPanel oPanelFundo;
    private JPanel oPanelBotoes;
    private JPanel oPanelPesq;
    private JTable oTable;
    private UnidMedTableModel oBrowse;
    List <IModelUnidMed> lista;
    private JScrollPane oScroll;
    private JButton oBtInclui;
    private JButton oBtExclui;
    private JButton oBtAltera;
    private JButton oBtVisual;
    private JButton oBtPesq;
    private JTextField oTxPesq;
    private JComboBox oCbPesq;    
    public BrwUnidMed(){
    	super(" Unidade de Medida | DSS", false, false ,false , true ); 
        CriaTable();
        CriaJanela();
    }    
    public void CriaTable(){        
    	oTable = new JTable(oBrowse);
    	LoadData();        
    }    
    private void LoadData() {        
        IUnidMedDao oDao = new UnidMedDao(); 
        lista = oDao.getUnidMed();
        oBrowse = new UnidMedTableModel(lista);
        oTable.setModel(oBrowse);
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
        oCbPesq.addItem("CÓDIGO");
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
        
    }
    private class oBtIncluiListener implements ActionListener {
    	 
        public void actionPerformed(ActionEvent e) {			
			ViewUnidMed oModelUM = new ViewUnidMed(3, 0, oBrowse);		    
		    getParent().add(oModelUM);
		    oModelUM.setVisible(true);
        }
    }
    private class oBtAlteraListener implements ActionListener {
   	 
        public void actionPerformed(ActionEvent e) {			
        	int nLin = -1;
        	int nReg = 0;
        	nLin = oTable.getSelectedRow();
            if (nLin >= 0) {
            	nReg = (int) oTable.getValueAt(nLin,0);
            	ViewUnidMed oModelUM = new ViewUnidMed(4, nReg, oBrowse);		    
    		    getParent().add(oModelUM);
    		    oModelUM.setVisible(true);
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
            	ViewUnidMed oModelUM = new ViewUnidMed(2, nReg, oBrowse);		    
    		    getParent().add(oModelUM);
    		    oModelUM.setVisible(true);
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
            	ViewUnidMed oModelUM = new ViewUnidMed(5, nReg, oBrowse);		    
    		    getParent().add(oModelUM);
    		    oModelUM.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
            }
        }
    }
    private class oBtPesqListener implements ActionListener {
    	 
        public void actionPerformed(ActionEvent e) {
        	int nIndex = oCbPesq.getSelectedIndex();
        	String cPesq = oTxPesq.getText();
        	IModelUnidMed oModel = new ModelUnidMed();
        	IUnidMedDao oDao = new UnidMedDao();
        	List<IModelUnidMed> oLstUnidMed = new ArrayList<IModelUnidMed>();
        	if (oTxPesq.getText().isEmpty()){
        		oBrowse.Refresh();
        	} else {
        		switch (nIndex){
        		case 0:
        			oBrowse.removeAll();        			
        			if (FwVldChar.isNumeric(cPesq)){
        				oModel = oDao.getUnidMedById(Integer.parseInt(cPesq));
            			if (oModel.getId()> 0){
            				oBrowse.addLine(oModel);
            			}        				
        			}        			
        			break;
        		case 1:
        			oBrowse.removeAll();        			
        			oModel = oDao.getUnidMedCod(cPesq);
            		if (oModel.getId()> 0){
            			oBrowse.addLine(oModel);            		        				
        			}        			
        			break;
        		case 2:
        			oBrowse.removeAll();
        			oLstUnidMed = oDao.getUnidMedDesc(cPesq);
        			for (int i = 0; i < oLstUnidMed.size() ; i++){
        				oModel = oLstUnidMed.get(i);
        				if (oModel.getId()> 0){
                			oBrowse.addLine(oModel);            		        				
            			}        			
        			}
        			break;
        		}
        	}
        		
        }
    }   
    public class UnidMedTableModel extends AbstractTableModel{
    	
    	private static final int COL_ID = 0;
    	private static final int COL_COD = 1;
    	private static final int COL_DESC = 2;	
    	private String[] colunas = new String[]{"Id", "Código", "Descrição"};
    	List<IModelUnidMed> linhas;
    	
    	public void Refresh() {        
            IUnidMedDao oDao = new UnidMedDao(); 
            lista = oDao.getUnidMed();
            oBrowse = new UnidMedTableModel(lista);
            oTable.setModel(oBrowse);
        }  
    	
    	public UnidMedTableModel(List<IModelUnidMed> oModel) {
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
	 
	        IModelUnidMed m = linhas.get(row);
	 
	        if (column == COL_ID) {
	            return m.getId();
	        } else if (column == COL_COD) {
	            return m.getCod();
	        } else if (column == COL_DESC) {
	            return m.getDesc();    	        
	        }
	        return "";
	    }
    	 
	    public void setValueAt(Object aValue, int row, int column) {
	    	IModelUnidMed u = linhas.get(row);
	        if (column == COL_ID) {
	            u.setId((Integer) aValue);
	        } else if (column == COL_COD) {
	            u.setCod(aValue.toString());
	        } else if (column == COL_DESC) {
	            u.setDesc(aValue.toString());
	        }
	    }
    	 
	    public IModelUnidMed getUnidMed(int indiceLinha) {
	        return linhas.get(indiceLinha);
	    }
    	 
	    public void addLine(IModelUnidMed oModel) {
	        linhas.add(oModel);
	        int ultimoIndice = getRowCount() - 1;
	        fireTableRowsInserted(ultimoIndice, ultimoIndice);    	 
	    }
	 
	    public void updateLine(int indiceLinha, IModelUnidMed oModel) {
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
