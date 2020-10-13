package UML;



import java.awt.Graphics;


public class segment extends composant {
	private Point2D x;

//  ------------------------------------
	
	public segment() {
		super();
		this.x= new Point2D(25,25);
	}
	public segment(Point2D orgn) {
		super(orgn);
		this.x = new Point2D(25,25);
	}
	public segment(Point2D orgn, Point2D x) {
		super(orgn);
		this.x = x;
	}
	
//  ------------------------------------
	
	public Point2D getX() {
		return x;
	}
	public void setX(Point2D x) {
		this.x = x;
	}
	
//  ------------------------------------
	
	@Override
	public String toString() {
		return "Le segment"+super.toString()+" et de point" + x;
	}

	public void draw(Graphics g){
		g.setColor(Graphique.couleur.getColor());
		g.drawLine(getOrgn().getX(), getOrgn().getY(),getX().getX(),getX().getY());
		
		}

	

}
