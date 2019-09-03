package Tamanho;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import Framework.TamanhoFixoJTextField;
import Tamanho.BrwTamanho.TamanhoTableModel;

public class ViewTamanho extends JInternalFrame implements ActionListener, FocusListener {
	
	private JPanel 	oPanel 		 = new JPanel();
	private JButton oBtCancel	 = new JButton("Cancelar");
	private JButton oBtCommit	 = new JButton("Confirmar");
	private JLabel  oLbCodigo    = new JLabel("Codigo: ");	
	private JLabel  oLbDesc      = new JLabel("Descrição: ");
	private JLabel  oLbPeso      = new JLabel("Peso: ");
	private JLabel  oLbAltura    = new JLabel("Altura: ");
	private JLabel  oLbComp      = new JLabel("Comprimento: ");
	private JLabel  oLbLarg      = new JLabel("Largura: ");
	private JTextField oTxCodigo = new JTextField(6);
	private JTextField oTxDesc	 = new JTextField(30);	
	private JFormattedTextField oTxPeso = new JFormattedTextField();
	private JFormattedTextField oTxAltura = new JFormattedTextField();
	private JFormattedTextField oTxComp = new JFormattedTextField();
	private JFormattedTextField oTxLarg = new JFormattedTextField();	
	private int nOpcx = 3;
	private int posX1 = 50, posX2 = 130, posX3 = posX2 - 10, posY = 0,alt = 30, sep = 20;
	private IModelTamanho oModel = new ModelTamanho();
	private IControllerTamanho oControl = new ControllerTamanho();
	public TamanhoTableModel oBrowse;
	
	public ViewTamanho(String cTitulo){
		super(cTitulo);
		CriaTela(nOpcx);
	}
	
	public ViewTamanho(String cTitulo, int nOpc, String cCodigo) {
		super(cTitulo);
		nOpcx = nOpc;
		CriaTela(nOpcx);
		CarregarDados(cCodigo);
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
		addLabel(oLbCodigo, posX1, posY, 100, alt);  
		addJTextField(oTxCodigo, posX2, posY, 60, alt);
		posY += alt + sep;
		addLabel(oLbDesc, posX1, posY, 100, alt);
		addJTextField(oTxDesc, posX2, posY, 600, alt);
		posY += alt + sep;
		addLabel(oLbPeso, posX1, posY, 100, alt);
		addJTextField(oTxPeso, posX2, posY, 100, alt);
		posY += alt + sep;
		addLabel(oLbAltura, posX1, posY, 100, alt);
		addJTextField(oTxAltura, posX2, posY, 100, alt);
		posX1 += posX2 + 80;
		posX2 = posX1 + 80;
		addLabel(oLbLarg, posX1, posY, 100, alt);
		addJTextField(oTxLarg, posX2, posY, 100, alt);
		posX1 += 250;
		posX2 = posX1 + 110;
		addLabel(oLbComp, posX1, posY, 100, alt);
		addJTextField(oTxComp, posX2, posY, 100, alt);
		
		//define se campo é editavel	    
	    if (nOpcx == 2 || nOpcx == 5){
	    	oTxCodigo.setEditable(false);
	    	oTxDesc.setEditable(false);
	    	oTxPeso.setEditable(false);
	    	oTxAltura.setEditable(false);
	    	oTxComp.setEditable(false);
	    	oTxLarg.setEditable(false);
        } else if (nOpcx == 4){
        	oTxCodigo.setEditable(false);
        }
		//adicina tooltip
	    oTxDesc.setToolTipText("Informe o codigo do Tamanho");
		oTxDesc.setToolTipText("Informe a descrição do Tamanho");
		oTxAltura.setToolTipText("Informe a altura");
		oTxComp.setToolTipText("Informe o comprimento");
		oTxLarg.setToolTipText("Informe a largura");
		oTxPeso.setToolTipText("Informe o peso");
		
				
		//define o tamanho maximo aceito no JTextField
		oTxCodigo.setDocument(new TamanhoFixoJTextField(6));
        oTxDesc.setDocument(new TamanhoFixoJTextField(30));
        oTxCodigo.addFocusListener(this);
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
	
	private void CarregarDados(String cCodigo){		
		
		ITamanhoDao oDao = new TamanhoDao();
		
		oModel = oDao.getCodigo(cCodigo);
		oTxCodigo.setText(oModel.getCodigo());
		oTxDesc.setText(oModel.getDescricao());
		oTxPeso.setText(Double.toString(oModel.getPeso()).replace(".",","));
		oTxAltura.setText(Double.toString(oModel.getAltura()).replace(".",","));
		oTxLarg.setText(Double.toString(oModel.getLargura()).replace(".",","));
		oTxComp.setText(Double.toString(oModel.getComprimento()).replace(".",","));
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== oBtCancel){ 
			if (JOptionPane.showConfirmDialog(null,"Deseja realmente sair?")==JOptionPane.OK_OPTION){	
				doDefaultCloseAction();
			}       
        } else { 
        	if(e.getSource()== oBtCommit){ 
        		
        		if (nOpcx == 3 || nOpcx == 4 || nOpcx == 5){
        			
        			String cPeso = oTxPeso.getText().replace(".", "");
        			String cAltura = oTxAltura.getText().replace(".", "");
        			String cComprimento = oTxComp.getText().replace(".", "");
        			String cLargura = oTxLarg.getText().replace(".", "");
        			
        			oModel.setCodigo(oTxCodigo.getText());
        			oModel.setDescricao(oTxDesc.getText());
        			oModel.setPeso(Double.parseDouble(cPeso.replaceAll(",", ".")));
        			oModel.setAltura(Double.parseDouble(cAltura.replaceAll(",", ".")));
        			oModel.setLargura(Double.parseDouble(cLargura.replaceAll(",", ".")));
        			oModel.setComprimento(Double.parseDouble(cComprimento.replaceAll(",", ".")));
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
		
		if (e.getSource() == oTxCodigo){
			if (oTxCodigo.getText().trim().equals("") ){
				oTxCodigo.setBackground(new Color(255, 106, 106));
			} else {
				oTxCodigo.setBackground(Color.WHITE);;
			}
		}
		
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
	
	public void addJTextField(JFormattedTextField oField ,int nPosX, int nPosY, int nTam, int nAlt){
		oPanel.add(oField);
		oField.setBounds(nPosX, nPosY, nTam, nAlt);		
		oField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###,##0.000"))));
		oField.setText("0.000");
		getContentPane().add(oField);		
	}
}

