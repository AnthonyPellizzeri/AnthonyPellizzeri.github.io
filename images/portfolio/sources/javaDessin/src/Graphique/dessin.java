package Graphique;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;


import UML.Point2D;
import UML.carre;
import UML.cercle;
import UML.deplacer;
import UML.losange;
import UML.rectangle;
import UML.segment;
import UML.triangle;
import UML.arc_cercle;

import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class dessin extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel panel = new JPanel();
	private Point2D orgn,p1;
	private static DefaultListModel<String> model=new DefaultListModel<String>();
	


	public static DefaultListModel<String> getModel() {
		return model;
	}



	public static void setModel(DefaultListModel<String> model) {
		dessin.model = model;
	}



	public static JPanel getPanel() {
		return panel;
	}



	public static void setPanel(JPanel panel) {
		dessin.panel = panel;
	}



	public dessin() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		panel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				orgn=new Point2D(e.getX(),e.getY());
				}
			
			public void mouseReleased(MouseEvent e) {
				p1=new Point2D(e.getX(),e.getY());	
				switch (forme.getSelect_forme())
				{
				case "rectangle":
					rectangle r=new rectangle(orgn,p1);
					getModel().addElement("rectangle");
					r.draw(getGraphics());
					orgn=p1=null;
					break;
					
					
				case "segment":
					segment s=new segment(orgn,p1);
					getModel().addElement("segment");
					s.draw(getGraphics());
					orgn=p1=null;
					break;
					
				case "carre":
					carre ca=new carre(orgn,p1);
					getModel().addElement("carre");
					ca.draw(getGraphics());
					orgn=p1=null;
					break;
					
				case "cercle":
					cercle c=new cercle(orgn,p1);
					getModel().addElement("cercle");
					c.draw(getGraphics());
					orgn=p1=null;
					break;
				
				case "triangle":
					triangle t=new triangle(orgn,p1);
					getModel().addElement("triangle");
					t.draw(getGraphics());
					orgn=p1=null;
					break;
					
						
				case "losange":
					losange l=new losange(orgn,p1);
					getModel().addElement("losange");
					l.draw(getGraphics());
					orgn=p1=null;
					break;
					
				case "modifier":
					deplacer tt=new deplacer(orgn,p1);
					tt.deplace(getGraphics());
					orgn=p1=null;
					break;
					
				case "arc":
					arc_cercle a=new arc_cercle(orgn,p1);
					a.draw(getGraphics());
					orgn=p1=null;
					break;
				}
				
			}
		});
		panel.setBounds(0, 0, 1630, 1167);
		add(panel);
		
		
}

	
	}
