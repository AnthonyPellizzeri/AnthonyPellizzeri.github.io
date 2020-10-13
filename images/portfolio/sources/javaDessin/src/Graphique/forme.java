package Graphique;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;


public class forme extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String select_forme;
	
	public static String getSelect_forme() {
		return select_forme;
	}


	public static void setSelect_forme(String select_forme) {
		forme.select_forme = select_forme;
	}



	private static JList<String> list = new JList<String>();

	

	public forme() {
		setLayout(null);
		
		JLabel lblCrmles = new JLabel("FORME");
		lblCrmles.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrmles.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCrmles.setBounds(0, 0, 450, 30);
		add(lblCrmles);
		
		
		
		list.setFont(new Font("Tahoma", Font.PLAIN, 11));
		list.setBackground(new Color(192, 192, 192));
		list.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), null));
		
		list.setBounds(399, 41, 81, 80);
		add(list);
		list.setModel(dessin.getModel());
		
		JButton btnsupp = new JButton("");
		btnsupp.setIcon(new ImageIcon("icone_java\\supprimer.png"));
		btnsupp.setToolTipText("SUPPRIMER");
		btnsupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dessin.getPanel().repaint();
					dessin.getModel().removeAllElements();
					
			}
		});
		btnsupp.setBounds(322, 41, 71, 80);
		add(btnsupp);
		
		JButton btnCarre = new JButton("");
		btnCarre.setIcon(new ImageIcon("icone_java\\carre.png"));
		btnCarre.setToolTipText("CARRE");
		btnCarre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select_forme="carre";
			}
		});
		btnCarre.setBounds(86, 41, 65, 36);
		add(btnCarre);
		
		JButton btnLosange = new JButton("");
		btnLosange.setIcon(new ImageIcon("icone_java\\losange.png"));
		btnLosange.setToolTipText("LOSANGE");
		btnLosange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select_forme="losange";
			}
		});
		btnLosange.setBounds(161, 85, 72, 36);
		add(btnLosange);
		
		JButton btnRectangle = new JButton("");
		btnRectangle.setToolTipText("RECTANGLE");
		btnRectangle.setIcon(new ImageIcon("icone_java\\rectangle.png"));
		btnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select_forme="rectangle";
			}
		});
		
		btnRectangle.setBounds(10, 85, 66, 36);
		add(btnRectangle);
		
		JButton btnCercle = new JButton("");
		btnCercle.setIcon(new ImageIcon("icone_java\\cercle.png"));
		btnCercle.setToolTipText("CERCLE");
		btnCercle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select_forme="cercle";
			}
		});
		btnCercle.setBounds(86, 85, 65, 36);
		add(btnCercle);
		
		JButton btnTriangle = new JButton("");
		btnTriangle.setIcon(new ImageIcon("icone_java\\triangle.png"));
		btnTriangle.setToolTipText("TRIANGLE");
		btnTriangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				select_forme="triangle";
			}
		});
		btnTriangle.setBounds(161, 41, 72, 36);
		add(btnTriangle);
		
		JButton btnsegment = new JButton("");
		btnsegment.setIcon(new ImageIcon("icone_java\\segment.png"));
		btnsegment.setToolTipText("SEGMENT");
		btnsegment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				select_forme="segment";
			}
		});
		btnsegment.setBounds(10, 40, 66, 37);
		add(btnsegment);
		
		JButton btnTest = new JButton("");
		btnTest.setIcon(new ImageIcon("icone_java\\cog-modifier-icone-5656-32.png"));
		btnTest.setToolTipText("MODIFIER");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select_forme="modifier";
			}
		});
		btnTest.setBounds(243, 85, 71, 36);
		add(btnTest);
		
		JButton btnarc = new JButton("");
		btnarc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select_forme="arc";
			}
		});
		btnarc.setIcon(new ImageIcon("icone_java\\arc_de_cercle.png"));
		btnarc.setToolTipText("ARC DE CERCLE");
		btnarc.setBounds(241, 41, 71, 36);
		add(btnarc);
		

		
		
	}	
}
