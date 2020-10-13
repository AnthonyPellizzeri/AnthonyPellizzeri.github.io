unit u_modele;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, u_loaddataset;

type
Tmodele = class(TMySQL)
   private
   { private declarations }
   public
   { public declarations }
   procedure open;
   function  inscription_liste_tous : TLoadDataSet;
   function  inscription_liste_etud (id, nom : string) : TLoadDataSet;
   function  inscription_liste_filiere (code, lib : string) : TLoadDataSet;
   function  inscription_num  (num : string) : TLoadDataSet;
   function  inscription_filiere (num : string) : string;
   function  inscription_note (num : string) : TLoadDataSet;
   function  moy_inscrit (num : string) : string;
   function  moy_filiere (num : string) : string;
   procedure close;
end;

var
   modele: Tmodele;

implementation

procedure Tmodele.open;
begin
      //Bd_open ('infodb2', 3306, 'licata2u_bdprojet', 'licata2u_appli', '31609462', 'mysqld-5', 'libmysql64.dll');
      Bd_open ('localhost', 3306, 'licata2u_bdprojet', 'licata2u_appli','31609462', 'mysqld-5', 'libmysql64.dll');
end;
procedure Tmodele.close;
begin
      Bd_close;
end;

// tous les inscris
function Tmodele.inscription_liste_tous : TLoadDataSet;
begin
     result := load('sp_inscription_liste_tous',[]);
end;


// inscris qui concernent les etudiants dont le n° d'étudiant contient la valeur contenue dans id
// ou le nom de l'étudiant contient la valeur contenue dans nom
function Tmodele.inscription_liste_etud (id, nom : string) : TLoadDataSet;
begin
      result := load('sp_inscription_liste_etud',[id, nom]);
end;


// inscris qui concernent les etudiants dont le n° de filière contient la valeur contenue dans code
// ou le nom de la filiere contient la valeur contenue dans lib
function Tmodele.inscription_liste_filiere (code, lib : string) : TLoadDataSet;
begin
      result := load('sp_inscription_liste_filiere',[code, lib]);
end;

function Tmodele.inscription_num (num : string) : TLoadDataSet;
begin
     result := load('sp_inscription_num',[num]);
end;

function Tmodele.inscription_filiere (num : string) : string;
begin
     load('sp_inscription_filiere',[num], result);
end;

function Tmodele.inscription_note (num : string) : TLoadDataSet;
begin
     result := load('sp_inscription_note',[num]);
end;

function Tmodele.moy_inscrit (num : string) : string;
begin
     load('sp_moy_inscrit', [num], result);
end;

function Tmodele.moy_filiere (num : string) : string;
begin
     load('sp_fil', [num], result);
end;

begin
     modele := Tmodele.create;
end.

