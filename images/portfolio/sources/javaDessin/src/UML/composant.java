package UML;

public class composant {
	private Point2D orgn;

//  ------------------------------------
	
	public composant() {
		super();
	}
	public composant(Point2D orgn) {
		super();
		this.orgn = orgn;
	}
	
//  ------------------------------------
	
	public Point2D getOrgn() {
		return orgn;
	}
	public void setOrgn(Point2D orgn) {
		this.orgn = orgn;
	}
	
//  ------------------------------------
	
	public void deplacer(Point2D orgn){
		this.orgn = orgn;
	}
	
	
	public String toString() {
		return "d'origine: " + orgn;
	}

	
	
}
