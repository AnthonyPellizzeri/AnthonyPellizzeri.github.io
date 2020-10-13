package Graphique;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
public class couleur extends JPanel implements ChangeListener {
 private static final long serialVersionUID = 1L;
 private JSlider r_slider = new JSlider(JSlider.HORIZONTAL,0,255,0);
 private JSlider v_slider = new JSlider(JSlider.HORIZONTAL,0,255,0);
 private JSlider b_slider = new JSlider(JSlider.HORIZONTAL,0,255,0);
 private JSlider S[] = {r_slider,v_slider,b_slider};
 private JLabel r_lbl = new JLabel("R = 0");
 private JLabel v_lbl = new JLabel("V = 0");
 private JLabel b_lbl = new JLabel("B = 0");
 private JLabel L[] = {r_lbl,v_lbl,b_lbl};
 private JPanel panel_princ = new JPanel();
 private JPanel panel_couleur = new JPanel();
 private JButton btn_Ok = new JButton("OK");
 private static Color color;


public static Color getColor() {
	return color;
}

public static void setColor(Color color) {
	couleur.color = color;
}

public couleur() {

  setSize(583,146);

  
  GridBagLayout gbl = new GridBagLayout();
  gbl.columnWidths = new int[]{322, 0, 0};
  gbl.rowHeights = new int[]{0, 0};
  gbl.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
  gbl.rowWeights = new double[]{1.0, Double.MIN_VALUE};
  setLayout(gbl);
  panel_princ.setLayout(null);
  
  GridBagConstraints gbc_panel_princ = new GridBagConstraints();
  gbc_panel_princ.gridwidth = 2;
  gbc_panel_princ.insets = new Insets(0, 0, 0, 5);
  gbc_panel_princ.fill = GridBagConstraints.BOTH;
  gbc_panel_princ.gridx = 0;
  gbc_panel_princ.gridy = 0;
  add(panel_princ, gbc_panel_princ);
  panel_couleur.setBounds(40, 92, 403, 30);
  panel_princ.add(panel_couleur);
  panel_couleur.setBackground(Color.BLACK);
  btn_Ok.setBounds(472, 92, 68, 30);
  panel_princ.add(btn_Ok);  
  btn_Ok.addMouseListener(new MouseAdapter() {
   public void mouseClicked(MouseEvent arg0) {
	   color=new Color(r_slider.getValue(),v_slider.getValue(),b_slider.getValue());
   }
  });
  
  btn_Ok.setFocusPainted(false);
  btn_Ok.setBackground(Color.white);
  
  JLabel lblCouleur = new JLabel("COULEUR");
  lblCouleur.setFont(new Font("Tahoma", Font.PLAIN, 30));
  lblCouleur.setHorizontalAlignment(SwingConstants.CENTER);
  lblCouleur.setBounds(0, 0, 578, 25);
  panel_princ.add(lblCouleur);
  
for(int i=0;i<3;i++){
	S[i].setValue(0);
	S[i].setMaximum(255);
	r_slider.setBounds(31, 45, 169, 45);
	r_lbl.setBounds(41, 13, 46, 44);
	v_slider.setBounds(229, 45, 166, 45);
	v_lbl.setBounds(234, 13, 46, 44);
	b_slider.setBounds(399, 45, 169, 45);
	b_lbl.setBounds(406, 7, 52, 44);
	panel_princ.add(S[i]);
	panel_princ.add(L[i]);
	S[i].addChangeListener(this);
	}
	
  r_lbl.setForeground(Color.red);
  v_lbl.setForeground(new Color(0,150,0));
  b_lbl.setForeground(Color.blue);
 }
 
 public void stateChanged(ChangeEvent arg0) {
  
  int r = r_slider.getValue();
  int v = v_slider.getValue();
  int b = b_slider.getValue();
  r_lbl.setText("R = "+r);
  v_lbl.setText("V = "+v);
  b_lbl.setText("B = "+b);
  panel_couleur.setBackground(new Color(r,v,b));
 
 }
}