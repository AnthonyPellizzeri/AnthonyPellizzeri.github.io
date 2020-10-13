package UML;

import java.awt.Graphics;

import UML.Point2D;
import UML.composant;

public class arc_cercle extends composant{
	private Point2D point;


//  ------------------------------------

	public arc_cercle() {
		super();
		point= new Point2D(30,35);
	}
public arc_cercle(Point2D orgn) {
	super(orgn);
	this.point = new Point2D(30,35);
}
public arc_cercle(Point2D orgn, Point2D r) {
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

public void draw(Graphics g){
	g.setColor(Graphique.couleur.getColor());
	if (getOrgn().getX()<getPoint().getX())
		g.drawArc(getOrgn().getX(), getOrgn().getY(),getPoint().getX()-getOrgn().getX(),getPoint().getX()-getOrgn().getX(),-180,-180);
	else
		g.drawArc(getPoint().getX(), getPoint().getY(),getOrgn().getX()-getPoint().getX(),getOrgn().getX()-getPoint().getX(),180,180);
}

}
