// pour toutes les fonctions du sudoku

#ifndef FONCTIONSUDOKU
#define FONCTIONSUDOKU

#include "structureCase.h"
#include "structureCandidat.h"

void lireGrille(int, int T[][9]);
void ecrireGrille(int, int T[][9]);
char carreCorrespondant(int, int);
int estCandidat(int, int, int, int, int T[][9]);
int initTab(int, int G[][9], Cand C[][9], Case *O);
void ecrireCand(int, Cand C[][9], int, Case *O);
int admetUnique(int, int, int, int G[][9], Cand C[][9]);
int fermerCase(int, int, int, int, int G[][9], Cand C[][9], Case *O);
int fermerGrille(int, int, int G[][9], Cand C[][9], Case *O);

#endif
