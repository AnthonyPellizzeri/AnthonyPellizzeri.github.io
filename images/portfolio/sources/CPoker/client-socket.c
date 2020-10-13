#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include <arpa/inet.h>
#include <string.h>
#include"structure.h"

int main(int argc,char** argv)
{
struct sockaddr_in coord_serveur;
int sockclient;
struct tm *m;
char* pc;
u_short port;
int nb;
//création de la socket
if(argc!=3)
	{
	printf("Usage: ./client <adresse ip> <port>\n");
	return 0;
	}
port=atoi(argv[2]);
sockclient=socket(AF_INET, SOCK_STREAM, 0);

//Partie connect ()

//préparation de la structure permettant de connecter le serveur
bzero(&coord_serveur,sizeof(coord_serveur));
coord_serveur.sin_family = AF_INET;
coord_serveur.sin_port = htons(port);
coord_serveur.sin_addr.s_addr = inet_addr(argv[1]);
if(connect(sockclient, (struct sockaddr *) &coord_serveur, sizeof(coord_serveur)) <0) 
	{ 
	printf ("erreur de connexion \n");
	exit(0);
	}

//Traitement
joueur j;
int i,gainJ=0;
char rep=' ';
printf("Vous allez joueur au meilleur jeu de poker\nVous aller recevoir 2 cartes");
j.adresse=1;	//dire que c est le joueur 1
int write1=write(sockclient,&j,sizeof(j));	//envoi du joueur créé
if(write1<0)
{
	perror("erreur write1");
	exit(EXIT_FAILURE);
}
int read1=read(sockclient,&j,sizeof(j));
if(read1<0)
{
	perror("erreur read1");
	exit(EXIT_FAILURE);
}
printf("\033[H\033[2J");
afficherNumPartie(0);
affichertab(j.main,2);
afficheBudget(&j);

for (i=2;i<5;i++)
{
  printf("\nVoulez vous continuez? tapez 'y' pour oui et 'n' pour non\n");
  scanf("%s",&rep);
  if (rep!='y' && rep!='Y'){
    printf("\033[H\033[2J");
    /*gainJ=gain(&j,5);
    &j.budget+gain;*/
    afficheFinal(&j,i);
    exit(-2); //fermer le programme
  }
    printf("\033[H\033[2J");
    int write2=write(sockclient,&j,10*sizeof(j));
    if(write2<0)
    {
	perror("erreur write2");
	exit(EXIT_FAILURE);
    }
    int read2=read(sockclient,&j,sizeof(j));  //lire l'ajout de carte
    if(read2<0)
    {
	perror("erreur read2");
	exit(EXIT_FAILURE);
    }
    afficherNumPartie(i-1);
    affichertab(j.main,i+1);
    afficheBudget(&j);
}

printf("\033[H\033[2J");
gain(&j,5);
//j.budget+gainJ;
//&j.budget++;
afficheFinal(&j,5);
//printf("\n%d\n",gainJ);
  
  //close(sockclient);
}
