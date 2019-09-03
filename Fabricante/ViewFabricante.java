package Fabricante;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import Fabricante.BrwFabricante.FabricanteTableModel;
import Framework.TamanhoFixoJTextField;
import Pessoa.*;

public class ViewFabricante extends JInternalFrame implements ActionListener, FocusListener {

	private JPanel 		oPanel 		= new JPanel();
	private JButton 	oBtCancel	= new JButton("Cancelar");
	private JButton 	oBtCommit	= new JButton("Confirmar");	
	private JLabel  	oLbId   	= new JLabel("Id: ");	
	private JLabel  	oLbNome     = new JLabel("Descrição: ");	
	private JLabel		oLbTipo		= new JLabel("Tipo: ");
	private JTextField 	oTxId 	 	= new JTextField(6);
	private JTextField 	oTxNome	 	= new JTextField(60);
	private JCheckBox 	oChkBlql   	= new JCheckBox("Bloqueado");
	private JComboBox 	oCbTipo		= new JComboBox();
	private int nOpcx = 3;
	private int posX1 = 50, posX2 = 130, posX3 = posX2 - 10, posY = 0,alt = 30, sep = 20;
	private IModelFabricante oModel = new ModelFabricante();
	private IPessoa oPessoa = new Pessoa();
	private IControllerFabricante oControl = new ControllerFabricante();
	public FabricanteTableModel oBrowse;
	
	public ViewFabricante(String cTitulo){
		super(cTitulo);
		CriaTela(nOpcx);
	}
	
	public ViewFabricante(String cTitulo, int nOpc, int nReg) {
		super(cTitulo);
		nOpcx = nOpc;
		CriaTela(nOpcx);
		CarregarDados(nReg);
	}
	
	private void CriaTela(int nOpcx){
		
		this.setSize(800, 590);		
        getContentPane().setLayout(null);		
		
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
	    
	    oCbTipo.setSize(100, 30);
	    oCbTipo.addItem("1 - Nacional");        
	    oCbTipo.addItem("2 - Estrangeiro");
				
		posY += alt + sep;
		addLabel(oLbId, posX1, posY, 100, alt);  
		addJTextField(oTxId, posX2, posY, 60, alt);
		posY += alt + sep;
		addLabel(oLbNome, posX1, posY, 100, alt);
		addJTextField(oTxNome, posX2, posY, 600, alt);
		posY += alt + sep;
		addLabel(oLbTipo, posX1, posY, 100, alt);
		addJComboBox(oCbTipo, posX2, posY, 200, alt);
		posY += alt + sep;
		addJCheckBox(oChkBlql, posX1, posY, 100, alt);
		
		//define se campo é editavel
		oTxId.setEditable(false);
	    if (nOpcx == 2 || nOpcx == 5){
	    	oTxNome.setEditable(false);
	    	oChkBlql.setEnabled(false);
	    	oCbTipo.setEnabled(false);
        }
		//adicina tooltip
	    oTxId.setToolTipText("Informe o codigo do Fabricante");
		oTxNome.setToolTipText("Informe a descrição do Fabricante");
		oTxNome.setToolTipText("Selecione o tipo do Fabricante");

		//define o tamanho maximo aceito no JTextField		
        oTxNome.setDocument(new TamanhoFixoJTextField(60));        
        oTxNome.addFocusListener(this);
		
		oPanel.setBounds(20, 20, 740, 485);
		oPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));			
		getContentPane().add(oPanel);
		
		oBtCancel.setBounds(665, 515, 89, 23);
		oBtCancel.setToolTipText("Cancelar operação");
		oBtCancel.setMnemonic(KeyEvent.VK_F);  
		oBtCancel.addActionListener(this); 
		getContentPane().add(oBtCancel);
		
		oBtCommit.setBounds(540, 516, 105, 23);
		oBtCommit.setToolTipText("Confirmar a operação");
		oBtCommit.setMnemonic(KeyEvent.VK_C);	
		oBtCommit.addActionListener(this);
		getContentPane().add(oBtCommit);
		
		DefineLayout();
	}
	
	private void DefineLayout(){
		oPanel.setBackground(new Color(250, 250, 250));	    
	    oBtCommit.setBackground(new Color(0, 120, 0));	    
	    oBtCancel.setBackground(new Color(0, 0, 0));	    
	    oBtCommit.setForeground(Color.WHITE);
	    oBtCancel.setForeground(Color.WHITE);
	    oChkBlql.setBackground(new Color(250, 250, 250));
	    oCbTipo.setBackground(new Color(250, 250, 250));
	}	
	
	private void CarregarDados(int nReg){		
		
		IFabricanteDao oDao = new FabricanteDao();
		
		oModel = oDao.getFabricanteId(nReg);
		oTxId.setText(Integer.toString(oModel.getPessoa().getId()));
		oPessoa.setId(oModel.getPessoa().getId());
		oTxNome.setText(oModel.getPessoa().getNome());
		oChkBlql.setSelected(oModel.getPessoa().isBloqueado());
		oCbTipo.setSelectedIndex(oModel.getTipo());
	}
	
	public void actionPerformed(ActionEvent e) {
		
		int nIndex = 0;
		
		if(e.getSource()== oBtCancel){ 
			if (JOptionPane.showConfirmDialog(null,"Deseja realmente sair?")==JOptionPane.OK_OPTION){	
				doDefaultCloseAction();
			}       
        } else { 
        	if(e.getSource()== oBtCommit){ 
        		
        		if (nOpcx == 3 || nOpcx == 4 || nOpcx == 5){
        			
        			nIndex = oCbTipo.getSelectedIndex();
        			oPessoa.setNome(oTxNome.getText());
        			oPessoa.setStatus(oChkBlql.isSelected());        			
        			oModel.setPessoa(oPessoa);
        			oModel.setTipo(nIndex);

        			if (oControl.VldCommit(oModel, nOpcx)){
        				if (oControl.ExecCommit(oModel, nOpcx)){         
        			        oBrowse.Refresh();    			 
        					doDefaultCloseAction();    					
        				}        				
        			}
        		} else {
        			doDefaultCloseAction();
        		}    	
        	}
        }
	}
	
	public void focusGained(FocusEvent e) {
		
	}

	public void focusLost(FocusEvent e){
		
		if (e.getSource() == oTxNome){
			if (oTxNome.getText().trim().equals("") ){
				oTxNome.setBackground(new Color(255, 106, 106));
			} else {
				oTxNome.setBackground(Color.WHITE);;
			}
		}		
	}
	
	public void addLabel(JLabel oLabel ,int nPosX, int nPosY, int nTam, int nAlt){
		oPanel.add(oLabel);
		oLabel.setBounds(nPosX, nPosY, nTam, nAlt);		
		getContentPane().add(oLabel);
	}

	public void addJTextField(JTextField oField ,int nPosX, int nPosY, int nTam, int nAlt){
		oPanel.add(oField);
		oField.setBounds(nPosX, nPosY, nTam, nAlt);		
		getContentPane().add(oField);		
	}
	
	public void addJCheckBox(JCheckBox oCheck ,int nPosX, int nPosY, int nTam, int nAlt){
		oPanel.add(oCheck);
		oCheck.setBounds(nPosX, nPosY, nTam, nAlt);		
		getContentPane().add(oCheck);		
	}
	
	public void addJComboBox(JComboBox oCombo ,int nPosX, int nPosY, int nTam, int nAlt){
		oPanel.add(oCombo);
		oCombo.setBounds(nPosX, nPosY, nTam, nAlt);		
		getContentPane().add(oCombo);		
	}
}
