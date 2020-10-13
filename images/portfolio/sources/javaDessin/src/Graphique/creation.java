package Graphique;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;


public class creation extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField origine;
	private JTextField point1;

	public creation() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MODIFIER");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 225, 32);
		add(lblNewLabel);
		
		
		JLabel lblOrigine = new JLabel("Origine:");
		lblOrigine.setBounds(23, 108, 55, 14);
		add(lblOrigine);

		
		origine = new JTextField();
		origine.setBounds(72, 105, 86, 20);
		origine.setColumns(10);
		add(origine);
		
		point1 = new JTextField();
		point1.setColumns(10);
		point1.setBounds(72, 143, 86, 20);
		add(point1);

		
		JButton btnValider = new JButton("VALIDER");
		btnValider.setBounds(10, 223, 89, 23);
		add(btnValider);

		
		JButton btnAnnuler = new JButton("ANNULER");
		btnAnnuler.setBounds(109, 223, 89, 23);
		add(btnAnnuler);
		
		JLabel lblDiametre = new JLabel("Point: ");
		lblDiametre.setBounds(23, 146, 46, 14);
		add(lblDiametre);

	
		
	}
}
