package UnidadeMedida;

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

import Framework.TamanhoFixoJTextField;
import UnidadeMedida.BrwUnidMed.UnidMedTableModel;

/**
 * View Cadastro de Unidade de Medida * 
 * @since 17/02/2019
 * @author Danilo Salve
 * @param nOpc Ação a ser executada (2 - Visual, 3 - Inclui, 4 - Altera e 5 Exclui)
 * @see package.UnidadeMedida
 * @see method
 */
public class ViewUnidMed extends JInternalFrame implements ActionListener, FocusListener{
	
	private JPanel 	oPanel 		 = new JPanel();
	private JButton oBtCancel	 = new JButton("Cancelar");
	private JButton oBtCommit	 = new JButton("Confirmar");	
	private JLabel  oLbId   	 = new JLabel("Id: ");
	private JLabel  oLbCod       = new JLabel("Codigo: ");
	private JLabel  oLbDesc      = new JLabel("Descrição: ");	
	private JTextField oTxId 	 = new JTextField();
	private JTextField oTxCod	 = new JTextField(2);
	private JTextField oTxDesc	 = new JTextField(30);
	private IModelUnidMed oModel = new ModelUnidMed();
	private IControllerUnidMed oControl = new ControllerUnidMed();
	private UnidMedTableModel oBrowse;
	
	//Define o posicionamento inicial dos objetos na tela
	private int posX1 = 50, posX2 = 130, posX3 = posX2 - 10, posY = 0,alt = 30, sep = 20;
	private int nOpcx = 0;
	
	public ViewUnidMed(int nOpc, int nReg, UnidMedTableModel md){		
	
		super(" Unidade de Medida | DSS", false, false ,false , true );  
        this.setSize(800, 590); 
        DefineLayout();
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
	    oPanel.add(oLbCod);
	    oPanel.add(oLbDesc);
	    oPanel.add(oTxId);
	    oPanel.add(oTxCod);
	    oPanel.add(oTxDesc);
	    
	    //define se campo é editavel
	    oTxId.setEditable(false);
	    
	    if ( nOpc == 2 || nOpc == 5 ) {
	    	oTxCod.setEditable(false);
	    	oTxDesc.setEditable(false);	    
	    }
	   
	    //define o posicionamento dos campos
	    posY += alt + sep; 
		oLbId.setBounds(posX1, posY, 20, alt);  
		oTxId.setBounds(posX2,posY,60,alt);		
		posY += alt + sep; 
		oLbCod.setBounds(posX1, posY, 100, alt);
		oTxCod.setBounds(posX2,posY,60,alt);		
		posY += alt + sep; 
		oLbDesc.setBounds(posX1, posY, 100, alt);  
		oTxDesc.setBounds(posX2,posY,600,alt);
		
		//define o tooltip
		oTxCod.setToolTipText("Informe o codigo da unidade de medida");
		oTxDesc.setToolTipText("Informe a descrição da unidade de medida");
		
		//define o tamanho maximo aceito no JTextField
        oTxCod.setDocument(new TamanhoFixoJTextField(2));
        oTxDesc.setDocument(new TamanhoFixoJTextField(30));
		oTxDesc.addFocusListener(this);
		oTxCod.addFocusListener(this);
		//adiciona objetos a janela
		this.add(oLbId);
		this.add(oLbCod);
		this.add(oLbDesc);
		this.add(oTxId);
		this.add(oTxCod);
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
			IUnidMedDao oDao = new UnidMedDao();
			oModel = oDao.getUnidMedById(nReg);
			oTxId.setText(Integer.toString(oModel.getId()));
			oTxCod.setText(oModel.getCod());
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
        			oModel.setCod(oTxCod.getText());
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
	private void DefineLayout(){
		oPanel.setBackground(new Color(250, 250, 250));	    
	    oBtCommit.setBackground(new Color(0, 120, 0));	    
	    oBtCancel.setBackground(new Color(0, 0, 0));	    
	    oBtCommit.setForeground(Color.WHITE);
	    oBtCancel.setForeground(Color.WHITE);
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
		
		if (e.getSource() == oTxCod){
			if (oTxCod.getText().trim().equals("") ){
				oTxCod.setBackground(new Color(255, 106, 106));
			} else {
				oTxCod.setBackground(Color.WHITE);;
			}
		}	
	}

}
