package UML;

import java.awt.Graphics;


public class triangle extends composant{
	private Point2D point1;
	
//  ------------------------------------
	
	public triangle() {
		super();
		point1= new Point2D(30,30);
	}
	public triangle(Point2D orgn) {
		super(orgn);
		this.point1= new Point2D(30,30);
	}
	public triangle(Point2D orgn, Point2D point) {
		super(orgn);
		this.point1 = point;
	}

	
//  ------------------------------------
	
	public Point2D getPoint1() {
		return point1;
	}
	public void setPoint1(Point2D point1) {
		this.point1 = point1;
	}
	
//  ------------------------------------
	
	@Override
	public String toString() {
		return "Le triangle " + super.toString()+" et de largeur:"+ point1;
	}

	public void draw (Graphics g){
		g.setColor(Graphique.couleur.getColor());
		int taille= getPoint1().getX()-getOrgn().getX();
		g.drawLine(getOrgn().getX(),getOrgn().getY(),getPoint1().getX(),getPoint1().getY());		
		g.drawLine(getOrgn().getX(),getOrgn().getY(),    getOrgn().getX()+ taille/2,getOrgn().getY()+taille/2);
		g.drawLine(getOrgn().getX()+ taille/2,getOrgn().getY()+taille/2,getPoint1().getX(),getPoint1().getY());
	}
	
}
