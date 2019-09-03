package Cores;

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
import Cores.BrwCores.CoresTableModel;
import Framework.TamanhoFixoJTextField;

/**
 * View - Responsavel pela camada de visualizaçáo do cadastro de Cores 
 * @since 01/03/2019
 * @author Danilo Salve
 * @param nOpc Ação a ser executada (2 - Visual, 3 - Inclui, 4 - Altera e 5 Exclui)
 * @see package.Cores
 * @see method
 */
public class ViewCores extends JInternalFrame implements ActionListener, FocusListener  {
	
	private JPanel 	oPanel 		 = new JPanel();
	private JButton oBtCancel	 = new JButton("Cancelar");
	private JButton oBtCommit	 = new JButton("Confirmar");
	private JLabel  oLbId   	 = new JLabel("Id: ");	
	private JLabel  oLbDesc      = new JLabel("Descrição: ");	
	private JTextField oTxId 	 = new JTextField(6);
	private JTextField oTxDesc	 = new JTextField(30);
	private CoresTableModel oBrowse;
	private IModelCores oModel	= new ModelCores();
	private IControllerCores oControl = new ControllerCores();
	//Define o posicionamento inicial dos objetos na tela
	private int posX1 = 50, posX2 = 130, posX3 = posX2 - 10, posY = 0,alt = 30, sep = 20;
	private int nOpcx = 0;	

	public ViewCores(int nOpc, int nReg, CoresTableModel md){
		super("Cores | DSS ", false, false ,false , true );
		nOpcx = nOpc;
		oBrowse = md;
		CriaTela(nOpc);
		DefineLayout();
		CarregarDados(nOpc,nReg);
	}	
	
	public void CriaTela(int nOpc){
						
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
	   
		oPanel.add(oLbId);
		oPanel.add(oLbDesc);
		oPanel.add(oTxId);
		oPanel.add(oTxDesc);
		
		posY += alt + sep; 
		oLbId.setBounds(posX1, posY, 20, alt);  
		oTxId.setBounds(posX2,posY,60,alt);		
		posY += alt + sep; 
		oLbDesc.setBounds(posX1, posY, 100, alt);  
		oTxDesc.setBounds(posX2,posY,600,alt);
		
		//define se campo é editavel
	    oTxId.setEditable(false);
	    if (nOpcx == 2 || nOpcx == 5){
	    	oTxDesc.setEditable(false);
        }
		//adicina tooltip
		oTxDesc.setToolTipText("Informe a descrição da Cor");
				
		//define o tamanho maximo aceito no JTextField
        oTxDesc.setDocument(new TamanhoFixoJTextField(30));
		oTxDesc.addFocusListener(this);
		//adiciona objetos a janela
        add(oLbId);		
        add(oLbDesc);
        add(oTxId);
        add(oTxDesc);    
        
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
		
	}
	
	private void DefineLayout(){
		oPanel.setBackground(new Color(250, 250, 250));	    
	    oBtCommit.setBackground(new Color(0, 120, 0));	    
	    oBtCancel.setBackground(new Color(0, 0, 0));	    
	    oBtCommit.setForeground(Color.WHITE);
	    oBtCancel.setForeground(Color.WHITE);
	}	
	private void CarregarDados(int nOpc, int nReg){
		if ( nOpc != 3){
			ICoresDao oDao = new CoresDao();
			oModel = oDao.getCoresId(nReg);
			oTxId.setText(Integer.toString(oModel.getId()));
			oTxDesc.setText(oModel.getDesc());			
		}		
	}
	
	@Override
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
}
