#ifndef STRUCT
#define STRUCT
#include <stdbool.h>
struct carte
{
  int couleur;
  int val;
};
typedef struct carte carte;

struct joueur
{
  int adresse;
  int budget;
  carte main[5];
};
typedef struct joueur joueur;

carte CreerCarte(int,int);
bool couleur(carte*, int);
bool brelan(carte *, int);
bool paire(carte*, int);
bool dblpaire(carte *, int);
void afficherCarte(carte);
void ajoutCarte(carte*,carte,int i);
void affichertab(carte* ,int i);
void combiMax(carte*,int i);
carte carteAleatoire();
void mise(joueur* j);
void initBudget(joueur* j);
void afficheBudget(joueur *j);
void afficherNumPartie(int i);
void gain(joueur* j,int i);
  
#endif
