//structure pour les candidats � une case ouverte

#ifndef STRUCTURECANDIDAT
#define STRUCTURECANDIDAT

struct candidats
{
   int nbc; // le nombre de candidats � la case
   int *tab; // le tableau qui enregistra les candidats � la case
};

typedef struct candidats Cand;

#endif
