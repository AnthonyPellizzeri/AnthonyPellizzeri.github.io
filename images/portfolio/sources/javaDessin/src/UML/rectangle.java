package UML;


import java.awt.Graphics;

import Graphique.style;


public class rectangle extends composant {
	private Point2D point;
	
//  ------------------------------------	

	public rectangle() {
		super();
		this.point= new Point2D(25,30);
	}
	public rectangle(Point2D orgn) {
		super(orgn);
		this.point = new Point2D(30,35);
	}
	public rectangle(Point2D orgn, Point2D point) {
		super(orgn);
		this.point = point;
	}
	
//  ------------------------------------
	
	public Point2D getPoint() {
		return point;
	}

	public void setPoint(Point2D point) {
		this.point = point;
	}
	
//  ------------------------------------
	
	
	public String toString() {
		return "Le rectangle "+ super.toString()+" et de diamètre: "+ point;
	}
	public void draw(Graphics g){
		g.setColor(Graphique.couleur.getColor());
		if (getOrgn().getX()<getPoint().getX() & style.getRemp()=="vide" &style.getArrond()=="faux")
			g.drawRect(getOrgn().getX(), getOrgn().getY(),getPoint().getX()-getOrgn().getX(),getPoint().getY()-getOrgn().getY());
		else if (getOrgn().getX()>getPoint().getX() & style.getRemp()=="vide" &style.getArrond()=="faux")
			g.drawRect(getPoint().getX(), getPoint().getY(),getOrgn().getX()-getPoint().getX(),getOrgn().getY()-getPoint().getY());
		
		if (getOrgn().getX()<getPoint().getX() & style.getRemp()=="plein" &style.getArrond()=="faux" )
			g.fillRect(getOrgn().getX(), getOrgn().getY(),getPoint().getX()-getOrgn().getX(),getPoint().getY()-getOrgn().getY());
		else if (getOrgn().getX()>getPoint().getX() & style.getRemp()=="plein" &style.getArrond()=="faux")
			g.fillRect(getPoint().getX(), getPoint().getY(),getOrgn().getX()-getPoint().getX(),getOrgn().getY()-getPoint().getY());
		
		
		
		if (getOrgn().getX()<getPoint().getX() & style.getRemp()=="vide" &style.getArrond()=="vrai")
			g.drawRoundRect(getOrgn().getX(), getOrgn().getY(),getPoint().getX()-getOrgn().getX(),getPoint().getY()-getOrgn().getY(),40,40);
		else if (getOrgn().getX()>getPoint().getX() & style.getRemp()=="vide" &style.getArrond()=="vrai")
			g.drawRoundRect(getPoint().getX(), getPoint().getY(),getOrgn().getX()-getPoint().getX(),getOrgn().getY()-getPoint().getY(),40,40);
		
		if (getOrgn().getX()<getPoint().getX() & style.getRemp()=="plein" &style.getArrond()=="vrai" )
			g.fillRoundRect(getOrgn().getX(), getOrgn().getY(),getPoint().getX()-getOrgn().getX(),getPoint().getY()-getOrgn().getY(),40,40);
		else if (getOrgn().getX()>getPoint().getX() & style.getRemp()=="plein" &style.getArrond()=="vrai")
			g.fillRoundRect(getPoint().getX(), getPoint().getY(),getOrgn().getX()-getPoint().getX(),getOrgn().getY()-getPoint().getY(),40,40);
		
	}

}