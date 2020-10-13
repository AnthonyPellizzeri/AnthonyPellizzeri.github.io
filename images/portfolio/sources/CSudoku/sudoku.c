//toutes les fonctions du sudoku seront dï¿½finies

#include <stdio.h>
#include <stdlib.h> //pour les pointeurs
#include "fonctionsudoku.h"


void lireGrille(int N, int T[][9])
{
   FILE* fichier = NULL;
   char caractereActuel;

   int i, j, lig , col, val;

	for (i=0; i<9; i++){
		for (j=0; j<9; j++) {
			T[i][j] = 0;
		}
	}


   fichier = fopen("sudoku_test6.txt", "r");



   if (fichier != NULL)

   {
        do

        {

				fscanf(fichier,"%d %d %d\n", &lig,&col,&val);

				for (i=0; i<9; i++)
				{
					for (j=0; j<9; j++)
					{
							if ( lig == i && col==j) {
								T[i][j] = val;
								fscanf(fichier,"%d %d %d\n", &lig,&col,&val);
							}
					}
				}

				caractereActuel = fgetc(fichier);

        }

		  while (caractereActuel != EOF); /* On continue tant que fgetc n'a pas retournÃ© EOF (fin de fichier) */


        fclose(fichier);

    }
}

void ecrireGrille(int N, int T[][9])
{
   int k, i, j;
   for (i = 0; i < N; i++){
		k = 0;
		k += printf("|");
      for (j = 0; j < N; j++) {
         k += printf("%d ",T[i][j]);
			if (j%3==2){
				k += printf("|");
			}
      }
      printf("\n");

		if (i%3==2){
			for (j=0; j<k; j++){
				printf("-");
			}
			printf("\n");
		}



   }
}

int estCandidat(int val, int lig, int col, int N, int T[][9])
{
	int i,j, res = 1, liginf, ligsup, colinf, colsup;


	if (T[lig][col] != 0)
      res = 0;

   if (res == 1) { // si res change a 0, ca veut dire que la valeur n'est pas candidate dans la case et on peut arreter
   	for (i=0; i < N; i++) // existance de la valeur dans la colonne
      {
         if (T[i][col] == val){
            res = 0;
            break; // car valeur n'est pas candidat
         }
      }
   }


	if (res == 1) {
      for (j=0; j < N; j++) // existance de la valeur dans la ligne
      {
         if (T[lig][j] == val){
            res = 0;
            break;
         }
	   }
   }

   if (res == 1){

      i = lig;

	   while (i%3!=0)			// cherche lign min
		   i--;

	   liginf=i;
	   ligsup=liginf+2;

	   j=col;

	   while (j%3!=0)			// cherche lign min
		   j--;

	   colinf=j;
	   colsup=colinf+2;

	   for(i=liginf;i<=ligsup;i++)		// test dans le caree
		   for(j=colinf;j<=colsup;j++)
			   if (T[i][j] == val){
               res = 0;
               break;
            }



   }

	return res; // si res = 1, alors la valeur est candidate

}



int initTab(int nblc, int G[][9], Cand C[][9], Case *O) // cela retourne la qte de cases ouvertes
/* nblc = nombre de lignes et de colonnes = 9
   C = c'est le tableau des candidats - ï¿½ 2 dimensions
   nbc = nb de candidats d'1 case
   O = tableau de cases ouvertes - 1 dimension */
{

int lig, col, val, qtecasesouv = 0; // qtecasesouv = qte de cases ouvertes

 for (lig = 0; lig < nblc; lig++)
   {
      for (col = 0; col < nblc; col++)
      {
         C[lig][col].nbc = 0;
			C[lig][col].tab = (int *)malloc(9*sizeof(int));

         for (val = 1; val <= 9; val++) // pour tester chaque valeur possible de 1 a 9
           {
            if(estCandidat(val,lig,col,nblc,G) == 1)
               {
                  C[lig][col].tab[C[lig][col].nbc] = val; /* premiere partie recuperer position de la case
																					apres le tab=  recupere la valeur */
                  C[lig][col].nbc++;
               }
            }


         if (C[lig][col].nbc != 0) // si c'est different de 0, case ouverte
            {
               O[qtecasesouv].x = lig;
               O[qtecasesouv].y = col;
               qtecasesouv++;
            }
			else {
				C[lig][col].tab = NULL;
			}

      }

   }

	return qtecasesouv;
}

void ecrireCand(int nblc, Cand C[][9], int nbcasouv, Case *O)
{
   int i, j, lig, col;
   for (i = 0; i < nbcasouv; i++){
      lig = O[i].x;
      col = O[i].y;

      printf("Candidats de la case [%d][%d]: ", lig, col);

      for (j = 0; j < C[lig][col].nbc; j++){ // affichage tous les candidats de la case
         printf("%d ",C[lig][col].tab[j]);
      }

      printf("\n");
   }
}

int admetUnique(int N, int lig, int col, int G[][9], Cand C[][9])
{

	int i,j,l,k,c, liginf, ligsup, colinf, colsup, res = 0;


	if (G[lig][col] == 0){

		i = lig;

		while (i%3!=0)			// cherche ligne min
		i--;

		liginf=i;
		ligsup=liginf+2;

		j = col;

		while (j%3!=0)			// cherche colonne min
		j--;

		colinf=j;
		colsup=colinf+2;


		if (C[lig][col].nbc == 1){
			res = C[lig][col].tab[0];
		}

		j = 0; // j sert a acceder aux candidats de la case qu'on evalue

		while(res == 0 && j < C[lig][col].nbc) { // si les valeurs ne sont pas candidats dans la m ligne

					c = 0; // compteur qui permet de savoir s'il y a candidat unique, si c = 0, candidat unique

					for (i=0; i<N; i++){

						if (G[lig][i] == 0 && i!=col){

							for (k=0; k < C[lig][i].nbc; k++) {

								if (C[lig][col].tab[j]==C[lig][i].tab[k]){
									c++;
								}

							}

						}

					}

					if (c == 0){
						res = C[lig][col].tab[j];
					}

					j++;

		}

		j = 0;

		while(res == 0 && j < C[lig][col].nbc) { // si les valeurs ne sont pas candidats dans la m colonne

					c = 0;

					for (i=0; i<N; i++){

						if (G[i][col] == 0 && i!=lig){

							for (k=0; k < C[i][col].nbc; k++) {

								if (C[lig][col].tab[j]==C[i][col].tab[k]){
									c++;
								}

							}

						}

					}

					if (c == 0){
						res = C[lig][col].tab[j];
					}

					j++;

		}

		j = 0;

		while(res == 0 && j < C[lig][col].nbc) { // si les valeurs ne sont pas candidats dans le carre

				c = 0;

				for (i=liginf; i<=ligsup; i++){

					for (l=colinf; l<=colsup; l++) {

						if (G[i][l] == 0 && (i!=lig || l!=col)){

							for (k=0; k < C[i][l].nbc; k++) {

								if (C[lig][col].tab[j]==C[i][l].tab[k]){
										c++;
								}

							}

						}
					}
				}


				if (c == 0){
					res = C[lig][col].tab[j];
				}

				j++;

		}

	}

	return res;//si res sort une valeur different de 0, celle ci sera candidat unique de la case

}

int fermerCase(int N, int lig, int col, int NBO, int G[][9], Cand C[][9], Case *O) {

	int n = NBO, trouve = 0, i, j, z, ligaux, colaux;
	int k,c, liginf, ligsup, colinf, colsup;

	if (admetUnique(N,lig,col,G,C) != 0) {
		G[lig][col] = c = admetUnique(N,lig,col,G,C);
		C[lig][col].nbc = 0;
		C[lig][col].tab = NULL;

		i = 0;

		while (trouve == 0 && i < NBO){

			ligaux = O[i].x;
			colaux = O[i].y;

			if (ligaux == lig && colaux == col){
            trouve = 1;
            j = i;
            while (j < n-1) {
               O[j].x = O[j+1].x;
				   O[j].y = O[j+1].y;
               j++;
            }
			}

			i++;

		}

		n--; // on diminue le nb de cases ouvertes

		i = lig;

		while (i%3!=0)			// cherche ligne min
		   i--;

		liginf=i;
		ligsup=liginf+2;

		j = col;

		while (j%3!=0)			// cherche colonne min
		   j--;

		colinf=j;
		colsup=colinf+2;




		for (i=0; i<N; i++){ //pour eliminer les candidats dans la ligne

			trouve = 0;
			k = 0; // k parcourt l'ensemble de candidats d'1 case

			while(trouve == 0 && k < C[lig][i].nbc) {


				if (c == C[lig][i].tab[k]){
					trouve = 1;
               z = k; // décaler dans le tableau des candidats dans la case car valeur supprimée
               while (z < C[lig][i].nbc-1){
                  C[lig][i].tab[z] = C[lig][i].tab[z+1];
                  z++;
               }
               C[lig][i].nbc--; //on réduit les nb de candidats de la case

				}


				k++;

			}

		}

		for (j=0; j<N; j++){ //pour eliminer les candidats dans la colonne

			trouve = 0;
			k = 0;

			while(trouve == 0 && k < C[j][col].nbc) {


				if (c == C[j][col].tab[k]){
					trouve = 1;
               z = k;
               while (z < C[j][col].nbc-1){
                  C[j][col].tab[z] = C[j][col].tab[z+1];
                  z++;
               }

					C[j][col].nbc--;
				}

				k++;

			}

		}

		for (i=liginf; i<=ligsup; i++){ // ï¿½liminer les candidats dans le carre

			for (j=colinf; j<=colsup; j++) {

				trouve = 0;
				k = 0;

				while(trouve == 0 && k < C[i][j].nbc) {

					if (c == C[i][j].tab[k]){
						trouve = 1;
                  z = k;
                  while (z < C[i][j].nbc-1){
                     C[i][j].tab[z] = C[i][j].tab[z+1];
                     z++;
                  }

						C[i][j].nbc--;
					}


					k++;

				}


			}


		}
	}
	
  	return n;
  }

int fermerGrille(int N, int NBO, int G[][9], Cand C[][9], Case *O)

{

	int i, y, z = NBO;
	
	do {
	
		
		NBO = y = z;
		
		i = 0;
		
		do {		

			z = fermerCase(N,O[i].x,O[i].y,z,G,C,O);
			
			if (z == y){
				i++;
			}
			else {
				y = z;
			}			
		
		}
		while (i < z);
	
	}
	while (z > 0 && z != NBO); // different de NBO si pas difficile
	
	return z; // ca retourne le nb de cases ouvertes
	 
}
