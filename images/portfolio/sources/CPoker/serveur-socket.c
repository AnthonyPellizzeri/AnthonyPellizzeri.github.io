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
#include<string.h>
#include"structure.h"

int main()
{
int nrbJoueur=3;
int tube[2];
int tube2[2];
pipe(tube);
pipe(tube2);
/* Déclaration des variables */
srand(time(NULL));
int sockserveur,newsockfd[nrbJoueur],lg;
struct sockaddr_in coord_client;
struct sockaddr_in mes_coord;
struct tm *m;
char* pc;
char msg[100];
/* Création d'un socket */
sockserveur=socket(AF_INET, SOCK_STREAM, 0);
/* Serveur : appel BIND */
bzero(&mes_coord,sizeof(mes_coord));
mes_coord.sin_family=AF_INET;
mes_coord.sin_port=htons(2003);
mes_coord.sin_addr.s_addr=htonl(INADDR_ANY);
if(bind(sockserveur,(struct sockaddr*)&mes_coord,sizeof(mes_coord))<0){
printf("Erreur BIND\n");
exit(0);
}
/* Serveur : appel LISTEN */
if (listen(sockserveur,5)<0){
printf("Erreur LISTEN\n");
exit(0);
}
/* Serveur : appel ACCEPT */
  int pid[nrbJoueur];
  int i,stats=0;
  lg=sizeof(coord_client);
  //ceation des cartes
  carte ca1,ca2,ca3,c11,c12,c21,c22,c31,c32,c41,c42,c51,c52;
	//carte aleatoires communes (tapis)
  ca1=carteAleatoire();
  ca2=carteAleatoire();
  ca3=carteAleatoire();
  carte tapis[3]={ca1,ca2,ca3};
	//carte joueur 1
  c11=carteAleatoire();
  c12=carteAleatoire();
  carte mainj1[2]={c11,c12};
	//carte joueur 2
  c21=carteAleatoire();
  c22=carteAleatoire();
  carte mainj2[2]={c21,c22};
	//carte joueur 3
  c31=carteAleatoire();
  c32=carteAleatoire();
  carte mainj3[2]={c31,c32};

c41=carteAleatoire();
  c42=carteAleatoire();
  carte mainj4[2]={c41,c42};

c51=carteAleatoire();
  c52=carteAleatoire();
  carte mainj5[2]={c51,c52};
  
  
  for(i=0;i<nrbJoueur;i++)
  {
    newsockfd[i]=accept(sockserveur,(struct sockaddr*)&coord_client,&lg);
    if(newsockfd[i]==(-1))
    {
      perror("Erreur ACCEPT \n");
      exit(0);
    }
    pid[i]=fork();
  	
  	
  if(pid[i]<0)	//erreur
  {
	perror("erreur pid");
	exit(EXIT_FAILURE);
  }
  if (pid[i]>0)	//pere
  {
	/*int t;
	close(tube[0]);
	int write3=write(tube[1],&mainj1,sizeof(mainj1));
	if(write3<0)
	{
		perror("erreur write3");
		exit(EXIT_FAILURE);
	}
	exit(EXIT_SUCCESS);*/
  }
  if(pid[i]==0)	//fils
  {
          	//lire la socket
          	joueur j;
          	int n;
          	char rep=' ';
		int w;
		
          	
          	
          	int read1=read(newsockfd[i],&j,sizeof(j));
		if(read1<0)
		{
			perror("erreur read 1");
			exit(EXIT_FAILURE);
		}
		int adr=i+1;
            	j.adresse=adr;
		if (j.adresse==1)
		{
			for (w=0;w<2;w++)
			{
				j.main[w]=mainj1[w];
		  	}
          	}
		else if(j.adresse==2)
		{
			for (w=0;w<2;w++)
			{
				j.main[w]=mainj2[w];
		  	}
		}
		else if(j.adresse==3)
		{
			for (w=0;w<2;w++)
			{
				j.main[w]=mainj3[w];
		  	}
		}
		else if(j.adresse==4)
		{
			for (w=0;w<2;w++)
			{
				j.main[w]=mainj4[w];
		  	}
		}
		else if(j.adresse==5)
		{
			for (w=0;w<2;w++)
			{
				j.main[w]=mainj5[w];
		  	}
		}
          	initBudget(&j); //initialiser le budjet du joueur
          	  
          	int write1=write(newsockfd[i],&j,sizeof(j));	// envoi des 2 cartes alléatoires
		if(write1<0)
		{
			perror("erreur write 1");
			exit(EXIT_FAILURE);
		}
		  for(n=2;n<5;n++)
		  {
		    	int read2=read(newsockfd[i],&j,10*sizeof(j));
		    	if(read2<0)
			{
				perror("erreur read 2");
				exit(EXIT_FAILURE);
			}  
		  	j.main[n]=tapis[n-2];
		  	mise(&j);
		  	
		  	int write2=write(newsockfd[i],&j,sizeof(j));
			if(write2<0)
			{
				perror("erreur write2");
				exit(EXIT_FAILURE);
			}
		  }
		  
	
exit(EXIT_SUCCESS);
}
}
int z;
for(z=0;z<nrbJoueur;z++)
{
	waitpid(pid[i],&stats,NULL);
}
close(sockserveur);
}
