package Framework;

import java.awt.Color;
import java.awt.event.ActionEvent;
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

public class FwCadastro extends JInternalFrame {
	
	private static final long serialVersionUID = 8071591009439104883L;
	private JPanel 	oPanel 		 = new JPanel();
	public JButton oBtCancel	 = new JButton("Cancelar");
	public JButton oBtCommit	 = new JButton("Confirmar");

	/**
	 * Cria JInternalFrame.
	 */
	public FwCadastro(String cTitulo) {
		
		super(cTitulo, false, false ,false , true );		
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
	    
	    oPanel.setBounds(20, 20, 740, 485);
		oPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));			
		getContentPane().add(oPanel);
		
		oBtCancel.setBounds(665, 515, 89, 23);
		oBtCancel.setToolTipText("Cancelar operação");
		oBtCancel.setMnemonic(KeyEvent.VK_F);  
		getContentPane().add(oBtCancel);
		
		oBtCommit.setBounds(540, 516, 105, 23);
		oBtCommit.setToolTipText("Confirmar a operação");
		oBtCommit.setMnemonic(KeyEvent.VK_C);		
		getContentPane().add(oBtCommit);
		
		DefineLayout();
	}
	
	/**
	 * Ativa janela
	 */
	
	public void Activate(){
		getParent().add(this);
		setVisible(true);
	}
	
	/**
	 * Adiciona JLabel ao Panel
	 */
	
	public void addLabel(JLabel oLabel ,int nPosX, int nPosY, int nTam, int nAlt){
		oPanel.add(oLabel);
		oLabel.setText("Teste");
		oLabel.setBounds(nPosX, nPosY, nTam, nAlt);		
		getContentPane().add(oLabel);
	}
	
	/**
	 * Adiciona JTextField ao Panel
	 */
	
	public void addJTextField(JTextField oField ,int nPosX, int nPosY, int nTam, int nAlt){
		oPanel.add(oField);
		oField.setBounds(nPosX, nPosY, nTam, nAlt);		
		getContentPane().add(oField);		
	}
	
	/**
	 * Adiciona as cores na janela
	 */
	
	private void DefineLayout(){
		oPanel.setBackground(new Color(250, 250, 250));	    
	    oBtCommit.setBackground(new Color(0, 120, 0));	    
	    oBtCancel.setBackground(new Color(0, 0, 0));	    
	    oBtCommit.setForeground(Color.WHITE);
	    oBtCancel.setForeground(Color.WHITE);
	}
}
