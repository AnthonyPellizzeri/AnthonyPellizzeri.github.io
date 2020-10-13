#include <stdlib.h>
#include<stdio.h>
#include<fcntl.h>
#include<time.h>
#include<stdbool.h>

#include"structure.h"


carte CreerCarte(int,int);
carte carteAleatoire();
bool couleur(carte*, int i);
bool brelan(carte *, int i);
bool paire(carte *, int i);
bool dblpaire(carte *, int i);
void afficherCarte(carte);
void ajoutCarte(carte*,carte,int i);
void affichertab(carte*,int i);
void combiMax(carte*,int i);
void mise(joueur* j);
void initBudget(joueur* j);
void afficheBudget(joueur *j);
void afficherNumPartie(int i);
void gain(joueur* j,int i);
  
  
  


/*int main() {
  int posTab=0;
  carte c,s,v,t,z;
  carte tab[5];
  c=CreerCarte(3,5);
  s=carteAleatoire();
  v=carteAleatoire();
  t=CreerCarte(3,5);
  z=carteAleatoire();

  ajoutCarte(tab,c,posTab);
  posTab=posTab+1;
  ajoutCarte(tab,s,posTab);
  posTab=posTab+1;
  ajoutCarte(tab,v,posTab);
  posTab=posTab+1;
  ajoutCarte(tab,t,posTab);
  posTab=posTab+1;
  ajoutCarte(tab,z,posTab);
  posTab=posTab+1;
  affichertab(tab,posTab);
  printf("%d \n",posTab);
  combiMax(tab,posTab);
  printf("\n");
  
}
*/
carte CreerCarte(int coul,int valeur)
{
	carte c;
	c.couleur=coul;
	c.val=valeur;
	return c;
}
carte carteAleatoire()
{
	carte c;
	c.couleur=rand()%4+1;
	c.val=rand()%13+1;
	return c;
}
void ajoutCarte(carte* tab,carte c,int i)
{
	tab[i]=c;
}




// fonction combinaison
bool couleur(carte c[], int i) {
  if (i<5){
	return false;
  }
  int coul=c[0].couleur;
  int j;
  for (j=0;j<5 ; j++)
  {
	if( c[j].couleur!=coul )
	{
		return false;
	}
  }
  return true;
}


//-------------------------


bool brelan(carte c[], int i) {
  int valeur;
  int j,nbrValIdent;  //si nbrValIdent=3 alors il y a un brelan 
  
  for (j=0 ; j<i-1 ; j++){  // on test a partir des 3 ou 4 ou 5 premieres valeurs du tableau car besoin juste de 2 valeur egales ->on compare avec le reste du tableau
	  nbrValIdent=0;
	  valeur=c[j].val; // recupere la valeur de la premiere colonne(j) et compare avec les suivante du tableau
	  int n=j;
	  for (n; n<i ; n++)
	  {
		if( (c[n].val==valeur) )
		{
			nbrValIdent=nbrValIdent+1;
		}
	  }
  	  if (nbrValIdent==3)
		{return true;}
  }
  return false;	// si jamais nbrValIdent==3 c'est qu'il n'y a pas de brelan
} 

//-------------------------
bool dblpaire(carte c[], int i) 
{
  int valeur,valeurPaire1=0;
  bool valide1=false,valide2=false;
  int j,nbrValIdent;  //si nbrValIdent=2 alors il y a une paire 
  if (i<4)
	return false; // car il faut au moins 4 carte
// test de la premiere paire
  for (j=0 ; j<i-1 ; j++){  // on test a partir des 3 ou 4 ou 5 premieres valeurs du tableau car besoin juste de 2 valeur egales ->on compare avec le reste du tableau
	  nbrValIdent=0;
	  valeur=c[j].val; // recupere la valeur de la premiere colonne(j) et compare avec les suivante du tableau
	  int n=j;
	  for (n; n<i ; n++)
	  {
		if( (c[n].val==valeur) )
		{
			nbrValIdent=nbrValIdent+1;
		}
		if (nbrValIdent==2)
		{
			valide1=true;
			valeurPaire1=valeur;
			break;
		}
	  }
	if(valide1==true)
		break;
  	  
  }
  if(valide1==false)
	return false;

// test de la deuxieme paire
  for (j=0 ; j<i-1 ; j++){  // on test a partir des 3 ou 4 ou 5 premieres valeurs du tableau car besoin juste de 2 valeur egales ->on compare avec le reste du tableau
	  
	if(c[j].val!=valeurPaire1)
	{
	  nbrValIdent=0;
	  valeur=c[j].val; // recupere la valeur de la premiere colonne(j) et compare avec les suivante du tableau
	  int n=j;
	  for (n; n<i ; n++)
	  {
		if( (c[n].val==valeur) )
		{
			nbrValIdent=nbrValIdent+1;
		}
	  }
  	  if (nbrValIdent==2)
		valide2=true;
	}
  }

if(valide1 && valide2)
	return true;
else 
	return false;
} 

//-------------------------

bool paire(carte c[], int i) {
  int valeur;
  int j,nbrValIdent;  //si nbrValIdent=2 alors il y a une paire 
  
  for (j=0 ; j<i-1 ; j++){  // on test a partir des 3 ou 4 ou 5 premieres valeurs du tableau car besoin juste de 2 valeur egales ->on compare avec le reste du tableau
	  nbrValIdent=0;
	  valeur=c[j].val; // recupere la valeur de la premiere colonne(j) et compare avec les suivante du tableau
	  int n=j;
	  for (n; n<i ; n++)
	  {
		if( (c[n].val==valeur) )
		{
			nbrValIdent=nbrValIdent+1;
		}
	  }
  	  if (nbrValIdent==2)
		{return true;}
  }
  return false;	// si jamais nbrValIdent==2 c'est qu'il n'y a pas de paire
} 

void combiMax(carte c[],int i)
{
	if( couleur(c,i) )
		printf("couleur");
	else if( brelan(c,i) )
		printf("brelan");
	else if( dblpaire(c,i) )
		printf("double paire");
	else if( paire(c,i) )
		printf("paire");
	else 
	  printf("pas de combinaison");
}
void afficherCarte(carte c)
{
	int couleur,valeur;
	couleur=c.couleur;
	valeur=c.val;
	char const *couleurString;  // const pour etre en dynamique
	char const *valeurString;
	switch (couleur)
	{
		case 1: couleurString="carreau";
			break;
		case 2: couleurString="coeur";
			break;
		case 3: couleurString="pique";
			break;
		case 4: couleurString="trèfle";
			break;
	}

	switch (valeur)
	{
		case 1: valeurString="As";
			break;
		case 2: valeurString="2";
			break;
		case 3: valeurString="3";
			break;
		case 4: valeurString="4";
			break;
		case 5: valeurString="5";
			break;
		case 6: valeurString="6";
			break;
		case 7: valeurString="7";
			break;
		case 8: valeurString="8";
			break;
		case 9: valeurString="9";
			break;
		case 10: valeurString="10";
			break;
		case 11: valeurString="Valet";
			break;
		case 12: valeurString="Reine";
			break;
		case 13: valeurString="Roi";
			break;
	}
	printf("%s de %s \n",valeurString,couleurString);
	
}

void affichertab(carte* tab,int i)
{
	int j;
  
	printf("\n-----votre main-----\n");
	for (j=0;j<i;j++)
	{
		afficherCarte(tab[j]);
	}
	printf("----------------------\n");
}

void mise(joueur* j)
{
  int bud=j->budget;
  j->budget=j->budget-10;
  if(bud<0)
    printf("vous devez remiser car vous etes en négatif");
}

void initBudget(joueur* j)
{
  j->budget=100;
}

void afficheBudget(joueur *j)
{
  printf("Budget: %d\n",j->budget);
  printf("*************\n");
}

void gain(joueur* j,int i)
{
	if( couleur(j->main,i) )
	{
		for(i=0;i<60;i++)
			j->budget++;
	}
	else if( brelan(j->main,i) )
	{
		for(i=0;i<40;i++)
			j->budget++;
	}
	else if( dblpaire(j->main,i) )
	{
		for(i=0;i<20;i++)
			j->budget++;
	}
	else if( paire(j->main,i) )
	{
		for(i=0;i<10;i++)
			j->budget++;
	}
}

void afficherNumPartie(int i)
{
  printf("\n---------------------\n");
  printf("\n_______TOUR %d_______\n",i);
  printf("\n---------------------\n");
}

afficheFinal(joueur* j,int i)
{
  printf("\n\n------FIN PARTIE-------\n");
  printf("Vous etes le joueur n°%d\n",j->adresse);
  affichertab(j->main,i);
  printf("Votre budjet est de: %d\n",j->budget);
  printf("Votre combinaison maximal est: ");
  combiMax(j->main,i);
  printf("\n------------------------\n");
}
