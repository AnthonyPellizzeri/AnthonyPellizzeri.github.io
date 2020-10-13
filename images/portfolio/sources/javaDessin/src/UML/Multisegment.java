package UML;

import java.util.ArrayList;
import java.util.Iterator;

public class Multisegment {
	private ArrayList<segment> tab;

//  ------------------------------------

	public Multisegment() {
		super();
	}

	public Multisegment(ArrayList<segment> tab) {
		super();
		this.tab = tab;
	}

//------------------------------------

	@Override
	public String toString() {
		return "Multisegment [Tab=" + tab + "]";
	}

	
//  ------------------------------------

	public void ajouter(segment s){
		tab.add(s);
	}
	public void supprimer(segment s){
		tab.remove(s);
	}
	
//  ------------------------------------

	public void afficher(){
		Iterator<segment> it=tab.iterator();
		while( it.hasNext())
			System.out.println(it.next());
	}
	
//  ------------------------------------

	public int nbseg (){
		int n=0;
		for (int i=0; i <= tab.size();i++){
			n++;
		}
		return n;
	}

}

