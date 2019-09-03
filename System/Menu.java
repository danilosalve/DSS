package System;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.*;
import java.beans.PropertyVetoException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Armazem.BrwArmazem;
import Categoria.BrwCateg;
import Cores.BrwCores;
import Fabricante.BrwFabricante;
import Tamanho.BrwTamanho;
import UnidadeMedida.BrwUnidMed;

/**
 * Exibe tela de menu do sistema.
 * 
 * @since 17/02/2019
 * @author Danilo Salve
 * @see Class
 * @see package.System
 */
public class Menu extends JFrame implements ActionListener, TreeSelectionListener, MouseListener {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private final JDesktopPane jdPane = new JDesktopPane();
   private final JTree tree = new JTree();
   private JTextField txProcurar = new JTextField();
   private BrwArmazem oBrowseAmz;
   private BrwCores oBrowseCor;
   private BrwCateg oBrowseCateg;
   private BrwTamanho oBrowseTamanho;
   private BrwFabricante oBrowseFabricante;
   public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu menu = new Menu();					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
   
   /**
    * Metodo Construtor.
    * 
    * @since 17/02/2019
    * @author Danilo Salve
    * @see package.System
    * @see Class#Constructor
    */
   public Menu() {
	  
	  setTitle("Danilo Salve Sistemas - DSS");
	  setBounds(100,100,600,400); 
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setExtendedState(JFrame.MAXIMIZED_BOTH);	  
	  this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);   
	
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
	  
	  addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent evt) {
			  fecharsistema();
		  }
	  });
	  
	  contentPane = new JPanel();
	  contentPane.setBackground(new Color(135, 206, 235));
	  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	  contentPane.setLayout(new BorderLayout(0, 6));
	  setContentPane(contentPane);
	  jdPane.setBorder(new LineBorder(new Color(070, 130, 180)));
	  jdPane.setBackground(new Color(250, 250, 250));
  
	  txProcurar.setBounds(250,010,600,050);
	  txProcurar.setText(" Digite aqui para procurar um programa");
	  txProcurar.setToolTipText("Procurar um programa");
	  txProcurar.addActionListener(this);
	  txProcurar.addMouseListener(this);
	  txProcurar.setForeground(Color.lightGray);
	  
	  jdPane.add(txProcurar);
	  contentPane.add(jdPane, BorderLayout.CENTER);

	  tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Menu                                             ") {
					private static final long serialVersionUID = 1L;

					{
						DefaultMutableTreeNode nEstoque;
						DefaultMutableTreeNode nSuprimentos;
						DefaultMutableTreeNode nCRM;
						nCRM = new DefaultMutableTreeNode("CRM");
							nCRM.add(new DefaultMutableTreeNode("Pessoas"));
							nCRM.add(new DefaultMutableTreeNode("Contatos"));
						add(nCRM);
						nEstoque = new DefaultMutableTreeNode("Estoque");
							nEstoque.add(new DefaultMutableTreeNode("Armazens"));
							nEstoque.add(new DefaultMutableTreeNode("Requisição"));
							nEstoque.add(new DefaultMutableTreeNode("Saldo"));							
						add(nEstoque);
						nSuprimentos = new DefaultMutableTreeNode("Suprimentos");							
							nSuprimentos.add(new DefaultMutableTreeNode("Categorias"));
							nSuprimentos.add(new DefaultMutableTreeNode("Cores"));
							nSuprimentos.add(new DefaultMutableTreeNode("Fabricantes"));
							nSuprimentos.add(new DefaultMutableTreeNode("Produtos"));						
							nSuprimentos.add(new DefaultMutableTreeNode("Unidades de Medida"));
							nSuprimentos.add(new DefaultMutableTreeNode("Tamanhos"));
						add(nSuprimentos);
					}
				}
			));
	  tree.addTreeSelectionListener(this);
	  contentPane.add(tree, BorderLayout.WEST);
      setVisible(true);
   }
      
   /**
    * Metodo resposavel por encerrar sistema
    * 
    * @since 17/02/2019
    * @author Danilo Salve
    * @see package.System
    * @see method
    */
   
   public void fecharsistema(){
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog (null, "Deseja realmente sair?","Warning",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION){
			dispose();
		}
	}
   
   /**
    * Metodo responsavel por Centralizar JInternalFrame
    * 
    * @since 17/02/2019
    * @author Danilo Salve
    * @param frame Objeto tipo JInternalFrame a ser centralizado
    * @see package.System
    * @see method
    */
   public void centralizaForm(JInternalFrame frame) {
		Dimension desktopSize = jdPane.getSize();
		Dimension jInternalFrameSize = frame.getSize();
		frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
				(desktopSize.height - jInternalFrameSize.height) / 2);
	}
   
   /**
    * Executa evento 
    * 
    * @since 17/02/2019
    * @author Danilo Salve
    * @param evt Evento a ser executado
    * @see package.System
    * @see method
    */  
   public void actionPerformed(ActionEvent evt) {
	   if (evt.getSource() == txProcurar){
			String cBusca = txProcurar.getText();
			if (Menu(cBusca)){			
			} else {
				JOptionPane.showMessageDialog(null, "Menu não localizado", "Não há dados", JOptionPane.INFORMATION_MESSAGE);
			}
		}
   }
   
   /**
    * Metodo responsavel por tratar os dados do tree
    * 
    * @since 17/02/2019
    * @author Danilo Salve
    *  @param e Evento a ser executado
    * @see package.System
    * @see method
    */
   public void valueChanged(TreeSelectionEvent e) {
	   
		String node = e.getNewLeadSelectionPath().getLastPathComponent().toString();
		Menu(node);		
	}

   /**
    * Metodo responsavl por clique do mouse
    * 
    * @since 17/02/2019
    * @author Danilo Salve
    * @param e Evento a ser executado
    * @see package.System
    * @see method
    */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == txProcurar){
			txProcurar.setText("");
			txProcurar.setForeground(Color.BLACK);
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}
	   /**
	    * Metodo responsavl por tratar clique do mouse - Saida
	    * 
	    * @since 17/02/2019
	    * @author Danilo Salve
	    * @param e Evento a ser executado
	    * @see package.System
	    * @see method
	    */
	@Override
	public void mouseExited(MouseEvent e) {
		
		if (e.getSource() == txProcurar){
			if (txProcurar.getText().trim().equals("") ){
				txProcurar.setText(" Digite aqui para procurar um programa");
				txProcurar.setForeground(Color.lightGray);
			}
		}			
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}   
	
	private boolean Menu(String cMenu){
		boolean lRet = false;
		cMenu = cMenu.toUpperCase();
		switch(cMenu){
		case "UNIDADES DE MEDIDA":
			BrwUnidMed oModelUM = new BrwUnidMed();
			lRet = true;
			if(!oModelUM.isVisible()){
				oModelUM.setVisible(true);
				centralizaForm(oModelUM);
				jdPane.add(oModelUM);
			} else {
				centralizaForm(oModelUM);
				jdPane.add(oModelUM);
			}
		break;
		case "ARMAZENS":
			oBrowseAmz = new BrwArmazem();
			lRet = true;
			if(!oBrowseAmz.isVisible()){
				oBrowseAmz.setVisible(true);
				centralizaForm(oBrowseAmz);
				jdPane.add(oBrowseAmz);
			} else {
				centralizaForm(oBrowseAmz);
				jdPane.add(oBrowseAmz);
			}
		break;
		case "CORES":
			oBrowseCor = new BrwCores();						
			lRet = true;
			if(!oBrowseCor.isVisible()){
				oBrowseCor.setVisible(true);
				centralizaForm(oBrowseCor);
				jdPane.add(oBrowseCor);
			} else {
				centralizaForm(oBrowseCor);
				jdPane.add(oBrowseCor);
			}
		break;
		case "CATEGORIAS":
			oBrowseCateg = new BrwCateg();						
			lRet = true;
			if(!oBrowseCateg.isVisible()){
				oBrowseCateg.setVisible(true);
				centralizaForm(oBrowseCateg);
				jdPane.add(oBrowseCateg);
			} else {
				centralizaForm(oBrowseCateg);
				jdPane.add(oBrowseCateg);
			}
		break;
		case "TAMANHOS":
			oBrowseTamanho = new BrwTamanho();						
			lRet = true;
			if(!oBrowseTamanho.isVisible()){
				oBrowseTamanho.setVisible(true);
				centralizaForm(oBrowseTamanho);
				jdPane.add(oBrowseTamanho);
			} else {
				centralizaForm(oBrowseTamanho);
				jdPane.add(oBrowseTamanho);
			}
		break;
		case "FABRICANTES":
			oBrowseFabricante = new BrwFabricante();						
			lRet = true;
			if(!oBrowseFabricante.isVisible()){
				oBrowseFabricante.setVisible(true);
				centralizaForm(oBrowseFabricante);
				jdPane.add(oBrowseFabricante);
			} else {
				centralizaForm(oBrowseFabricante);
				jdPane.add(oBrowseFabricante);
			}
		break;
		}		
		return lRet;
	}
}
