package UML;

import java.awt.Graphics;

public class deplacer extends composant{
private Point2D point;
	
//  ------------------------------------	

	public deplacer() {
		super();
		this.point= new Point2D(25,30);
	}
	public deplacer(Point2D orgn) {
		super(orgn);
		this.point = new Point2D(30,35);
	}
	public deplacer(Point2D orgn, Point2D point) {
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
	
public void deplace(Graphics g){
	if (getOrgn().getX()<getPoint().getX()){
		g.copyArea(getOrgn().getX(), getOrgn().getY(),getPoint().getX()-getOrgn().getX(),getPoint().getY()-getOrgn().getY(),getOrgn().getX()+100, getOrgn().getY()-50);
		g.clearRect(getOrgn().getX(), getOrgn().getY(),getPoint().getX()-getOrgn().getX(),getPoint().getY()-getOrgn().getY());
	}
	else
	{
		g.copyArea(getPoint().getX(), getPoint().getY(),getOrgn().getX()-getPoint().getX(),getOrgn().getY()-getPoint().getY(),getPoint().getX()+50, getPoint().getY()-50);
		g.clearRect(getPoint().getX(), getPoint().getY(),getOrgn().getX()-getPoint().getX(),getOrgn().getY()-getPoint().getY());
	}
}
}
