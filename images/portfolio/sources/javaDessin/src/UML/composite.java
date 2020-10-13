package UML;

import java.util.ArrayList;
import java.util.Iterator;

public class composite extends composant{
	private ArrayList<composant> tab;

//  ------------------------------------
	
	public composite() {
		super();
	}
	public composite(ArrayList<composant> tab) {
		super();
		this.tab = tab;
	}
//  ------------------------------------
	
	public void ajouter(composant s){
		tab.add(s);
	}
	public void supprimer(composant s){
		tab.remove(s);
	}
	
//  ------------------------------------
	
	public void afficher(){
		Iterator<composant> it=tab.iterator();
		while( it.hasNext())
			System.out.println(it.next());
	}
	
//  ------------------------------------

	public int nbcomp (){
		int n=0;
		for (int i=0; i <= tab.size();i++){
			n++;
		}
		return n;
	}


	
}

