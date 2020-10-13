package UML;

import java.awt.Graphics;

import Graphique.style;


public class cercle extends composant{
	private Point2D point;


//  ------------------------------------

	public cercle() {
		super();
		point= new Point2D(30,35);
	}
public cercle(Point2D orgn) {
	super(orgn);
	this.point = new Point2D(30,35);
}
public cercle(Point2D orgn, Point2D r) {
	super(orgn);
	this.point = r;
}

//------------------------------------

public Point2D getPoint() {
	return point;
}
public void setPoint(Point2D rayon) {
	this.point = rayon;
}


//------------------------------------

@Override
public String toString() {
	return  "LE cercle "+super.toString()+"et de point: " + point;
}

public void draw(Graphics g){
	g.setColor(Graphique.couleur.getColor());
	if (getOrgn().getX()<getPoint().getX() & style.getRemp()=="vide")
		g.drawOval(getOrgn().getX(), getOrgn().getY(),getPoint().getX()-getOrgn().getX(),getPoint().getX()-getOrgn().getX());
	else if (getOrgn().getX()>getPoint().getX() & style.getRemp()=="vide")
		g.drawOval(getPoint().getX(), getPoint().getY(),getOrgn().getX()-getPoint().getX(),getOrgn().getX()-getPoint().getX());
	
	
	if (getOrgn().getX()<getPoint().getX() & style.getRemp()=="plein")
		g.fillOval(getOrgn().getX(), getOrgn().getY(),getPoint().getX()-getOrgn().getX(),getPoint().getX()-getOrgn().getX());
	else if (getOrgn().getX()>getPoint().getX() & style.getRemp()=="plein")
		g.fillOval(getPoint().getX(), getPoint().getY(),getOrgn().getX()-getPoint().getX(),getOrgn().getX()-getPoint().getX());


}
	
}

