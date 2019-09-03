package Categoria;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import Categoria.BrwCateg.CategTableModel;
import Framework.TamanhoFixoJTextField;

public class ViewCateg extends JInternalFrame implements ActionListener, FocusListener {
	
	private static final long serialVersionUID = 3534710768311740110L;
	private JPanel 	oPanel 		 = new JPanel();
	private JButton oBtCancel	 = new JButton("Cancelar");
	private JButton oBtCommit	 = new JButton("Confirmar");
	private JLabel  oLbId   	 = new JLabel("Id: ");	
	private JLabel  oLbDesc      = new JLabel("Descrição: ");	
	private JTextField oTxId 	 = new JTextField(6);
	private JTextField oTxDesc	 = new JTextField(30);
	private int nOpcx = 3;
	private int posX1 = 50, posX2 = 130, posX3 = posX2 - 10, posY = 0,alt = 30, sep = 20;
	public CategTableModel oBrowse;
	public IModelCateg oModel	= new ModelCateg();
	public IControllerCateg oControl = new ControllerCateg();
			
	public ViewCateg(String cTitulo) {
		super(cTitulo);
		CriaTela(nOpcx);
	}
	
	public ViewCateg(String cTitulo, int nOpc, int nReg) {
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
				
		posY += alt + sep;
		addLabel(oLbId, posX1, posY, 20, alt);  
		addJTextField(oTxId, posX2, posY, 60, alt);
		posY += alt + sep; 
		addLabel(oLbDesc, posX1, posY, 100, alt);  
		addJTextField(oTxDesc, posX2, posY, 600, alt);
		
		//define se campo é editavel
	    oTxId.setEditable(false);
	    if (nOpcx == 2 || nOpcx == 5){
	    	oTxDesc.setEditable(false);
        }
		//adicina tooltip
		oTxDesc.setToolTipText("Informe a descrição do Categoria");
				
		//define o tamanho maximo aceito no JTextField
        oTxDesc.setDocument(new TamanhoFixoJTextField(30));
		oTxDesc.addFocusListener(this);
		
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
	}	
	
	private void CarregarDados(int nReg){		
		ICategDao oDao = new CategDao();
		oModel = oDao.getCategoriaId(nReg);
		oTxId.setText(Integer.toString(oModel.getId()));
		oTxDesc.setText(oModel.getDesc());					
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== oBtCancel){ 
			if (JOptionPane.showConfirmDialog(null,"Deseja realmente sair?")==JOptionPane.OK_OPTION){	
				doDefaultCloseAction();
			}       
        } else { 
        	if(e.getSource()== oBtCommit){        		
        		if (nOpcx == 3 || nOpcx == 4 || nOpcx == 5){        			
        			oModel.setDesc(oTxDesc.getText());        			
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
		if (e.getSource() == oTxDesc){
			if (oTxDesc.getText().trim().equals("") ){
				oTxDesc.setBackground(new Color(255, 106, 106));
			} else {
				oTxDesc.setBackground(Color.WHITE);;
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
}
