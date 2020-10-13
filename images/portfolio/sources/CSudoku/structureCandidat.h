//structure pour les candidats à une case ouverte

#ifndef STRUCTURECANDIDAT
#define STRUCTURECANDIDAT

struct candidats
{
   int nbc; // le nombre de candidats à la case
   int *tab; // le tableau qui enregistra les candidats à la case
};

typedef struct candidats Cand;

#endif
