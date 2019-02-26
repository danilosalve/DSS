package Armazem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import UnidadeMedida.IUnidMedDao;
import UnidadeMedida.UnidMedDao;
import Armazem.BrwArmazem.ArmazemTableModel;
import Framework.TamanhoFixoJTextField;

/**
 * View Cadastro de Unidade de Medida * 
 * @since 25/02/2019
 * @author Danilo Salve
 * @param nOpc Ação a ser executada (2 - Visual, 3 - Inclui, 4 - Altera e 5 Exclui)
 * @see package.Armazem
 * @see method
 */

public class ViewArmazem extends JInternalFrame implements ActionListener{
	
	private JPanel 	oPanel 		 = new JPanel();
	private JButton oBtCancel	 = new JButton("Cancelar");
	private JButton oBtCommit	 = new JButton("Confirmar");	
	private JLabel  oLbId   	 = new JLabel("Id: ");	
	private JLabel  oLbDesc      = new JLabel("Descrição: ");	
	private JTextField oTxId 	 = new JTextField(6);
	private JTextField oTxDesc	 = new JTextField(30);
	private JCheckBox oChkBlql   = new JCheckBox("Bloqueado");
	private IModelArmazem oModel = new ModelArmazem();
	private IControllerArmazem oControl = new ControllerArmazem();
	private ArmazemTableModel oBrowse;
	//Define o posicionamento inicial dos objetos na tela
	private int posX1 = 50, posX2 = 130, posX3 = posX2 - 10, posY = 0,alt = 30, sep = 20;
	private int nOpcx = 0;
	
	public ViewArmazem(int nOpc, int nReg, ArmazemTableModel md){
		super(" Armazem | DSS", false, false ,false , true );  
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
	    //adiciona objetos ao oPanel 
	    oPanel.add(oLbId);
	    oPanel.add(oLbDesc);
	    oPanel.add(oTxId);
	    oPanel.add(oChkBlql);
	    oPanel.add(oTxDesc);
	    
	    //define se campo é editavel
	    oTxId.setEditable(false);
	    
	    if ( nOpc == 2 || nOpc == 5 ) {
	    	oChkBlql.setEnabled(false);
	    	oTxDesc.setEditable(false);	    
	    }
	   
	    //define o posicionamento dos campos
	    posY += alt + sep; 
		oLbId.setBounds(posX1, posY, 20, alt);  
		oTxId.setBounds(posX2,posY,60,alt);		
		posY += alt + sep; 
		oLbDesc.setBounds(posX1, posY, 100, alt);  
		oTxDesc.setBounds(posX2,posY,600,alt);
		posY += alt + sep; 
		oChkBlql.setBounds(posX1, posY, 100, alt);
		
		//define o tooltip		
		oTxDesc.setToolTipText("Informe a descrição da unidade de medida");
		oChkBlql.setToolTipText("Informe o Registro está bloquado para uso");
		
		//define o tamanho maximo aceito no JTextField
        oTxDesc.setDocument(new TamanhoFixoJTextField(30));
		
		//adiciona objetos a janela
		this.add(oLbId);
		this.add(oChkBlql);
		this.add(oLbDesc);
		this.add(oTxId);
		this.add(oTxDesc);
		
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
		
		nOpcx = nOpc;
		oBrowse = md;
		if ( nOpc != 3){
			IArmazemDao oDao = new ArmazemDao();
			oModel = oDao.getArmazemId(nReg);
			oTxId.setText(Integer.toString(oModel.getId()));
			oTxDesc.setText(oModel.getDesc());
			oChkBlql.setSelected(oModel.getBloq());
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
        			oModel.setBloq(oChkBlql.isSelected());
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

}
