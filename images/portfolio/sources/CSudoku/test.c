#include <stdio.h>
#include <stdlib.h>
#include "fonctionsudoku.h"

main()
{

int G[9][9];
int NBO;
Cand C[9][9];
Case O[81];


lireGrille(9,G);
printf("\n");
ecrireGrille(9,G);
printf("\n");
NBO = initTab(9,G,C,O);
printf("Nombre de cases ouvertes: %d\n",NBO);
printf("\n");
printf("Candidats pour chaque case ouverte: \n");
printf("\n");
ecrireCand(9,C,NBO,O);
printf("\n");
printf("Maintenant, on ferme la grille et on met a jour le sudoku: \n");
NBO = fermerGrille(9,NBO,G,C,O);
printf("\n");
ecrireGrille(9,G);
printf("\n");
printf("Nombre de cases qui restent ouvertes: %d\n",NBO);
printf("\n");
ecrireCand(9,C,NBO,O);
printf("\n");

}
