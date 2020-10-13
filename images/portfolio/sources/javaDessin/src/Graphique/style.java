package Graphique;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class style extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String remp="vide";
	private static String arrond="faux";
	public static String getArrond() {
		return arrond;
	}

	public static void setArrond(String arrond) {
		style.arrond = arrond;
	}

	public static String getRemp() {
		return remp;
	}

	public static void setRemp(String remp) {
		style.remp = remp;
	}


	public style() {
		setLayout(null);
		
		JLabel lblTaille = new JLabel("STYLE");
		lblTaille.setHorizontalAlignment(SwingConstants.CENTER);
		lblTaille.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTaille.setBounds(0, 0, 189, 34);
		add(lblTaille);

		
		JCheckBox chckbxRemplissage = new JCheckBox("remplissage");
		chckbxRemplissage.setToolTipText("A utiliser avec RECTANGLE et CARRE\r\n");
		chckbxRemplissage.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chckbxRemplissage.getSelectedObjects() != null)
					style.setRemp("plein");	
				else
					style.setRemp("vide");
			}
		});
		chckbxRemplissage.setBounds(38, 54, 97, 23);
		add(chckbxRemplissage);
		
		JCheckBox chckbxArrondi = new JCheckBox("arrondi");
		chckbxArrondi.setToolTipText("A utiliser avec RECTANGLE et CARRE\r\n");
		chckbxArrondi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxArrondi.getSelectedObjects() != null)
					style.setArrond("vrai");	
				else
					style.setArrond("faux");
			}
			
		});
		chckbxArrondi.setBounds(38, 80, 97, 23);
		add(chckbxArrondi);
		
		
				
			}
}