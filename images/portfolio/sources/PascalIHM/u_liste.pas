unit u_liste;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, FileUtil, Forms, Controls, Graphics, Dialogs,
  Grids, ExtCtrls, Buttons, Spin, StdCtrls, u_loaddataset;

type

  { Tf_liste }

  Tf_liste = class(TForm)
    btn_line_edit: TSpeedButton;
    btn_line_delete: TSpeedButton;
    btn_line_detail: TSpeedButton;
    btn_line_add: TSpeedButton;
    btn_page_first: TSpeedButton;
    btn_page_prior: TSpeedButton;
    btn_page_next: TSpeedButton;
    btn_page_last: TSpeedButton;
    lbl_spe_line: TLabel;
    pnl_btn_page: TPanel;
    pnl_affi: TPanel;
    pnl_btn_ligne: TPanel;
    pnl_titre: TPanel;
    pnl_btn: TPanel;
    sg_liste: TStringGrid;
    nb_ligne: TSpinEdit;

    procedure btn_page_firstClick(Sender: TObject);
    procedure btn_page_lastClick(Sender: TObject);
    procedure btn_page_nextClick(Sender: TObject);
    procedure btn_page_priorClick(Sender: TObject);
    procedure affi_page;
    procedure affi_data (flux_data : TLoadDataSet);

    procedure line_add  (ligne : TLoadDataSet);
    procedure line_add  (ligne : TTableauAssociatif);
    procedure line_add  (ligne : Array of string);
    procedure line_edit (ligne : TLoadDataSet);
    procedure line_delete;
    procedure nb_ligneChange(Sender: TObject);

    function  SumColumn (cle : string)   : real;
    function  SumColumn (p : integer)    : real;

  private
    indicedebut  : integer;
    nbpages, pageencours : integer;
    flux : TLoadDataSet;
    { private declarations }
  public
    { public declarations }
  end;


var
  f_liste: Tf_liste;

implementation

{$R *.lfm}

{ Tf_liste }


procedure Tf_liste.affi_page;
var
   unenreg  : TTableauAssociatif;
   i, j     : integer;
begin
// vider le contenu de sg_liste sauf la ligne de titre fixe
   sg_liste.Clean([gzNormal]);
   sg_liste.rowcount := 1; // la ligne de titre
   if (indicedebut > 0) then
   begin
      pageencours := indicedebut DIV nb_ligne.Value +1;
      if pageencours >= nbpages
      then // dernière page ==> calculer nombre de lignes à afficher
         sg_liste.rowCount := sg_liste.rowCount +flux.count - (nb_ligne.value *(nbpages-1))
      else sg_liste.rowCount := sg_liste.rowCount +nb_ligne.value;

      i := 1;   // ligne de titres
      flux.Position(indicedebut);

      btn_line_detail.enabled := NOT flux.EndOf;
      btn_line_edit.enabled   := NOT flux.EndOf;
      btn_line_delete.enabled := NOT flux.EndOf;

   // tant que non fin flux et non fin page
      while not(flux.EndOf) and (i < sg_liste.rowCount)
      do begin
   // lecture de la ligne du flux et stockage, positionnement sur la ligne suivante
         unenreg := flux.Read;
         j := 0;
         while (j < sg_liste.colCount)
         do begin
            sg_liste.Cells[j,i] := unenreg[j];
            j := j +1;
         end;
         i := i +1;
      end;
   end
   else begin
        btn_line_detail.enabled := false;
        btn_line_edit.enabled   := false;
        btn_line_delete.enabled := false;
   end;

   pnl_btn_page.caption := 'page ' +inttostr(pageencours)
                           +' / ' +inttostr(nbpages);
   btn_page_last.enabled := (pageencours < nbpages);
   btn_page_next.enabled := (pageencours < nbpages);
   btn_page_first.enabled:= (pageencours > 1);
   btn_page_prior.enabled:= (pageencours > 1);
end;

procedure Tf_liste.affi_data (flux_data : TLoadDataSet);
begin
   sg_liste.RowCount:=nb_ligne.Value+1;
   flux       := flux_data;
   nbpages    := (flux.count +nb_ligne.Value -1) DIV nb_ligne.Value;

   btn_line_detail.enabled := false;
   btn_line_edit.enabled   := false;
   btn_line_delete.enabled := false;

   btn_page_firstClick(btn_page_first);
end;


procedure Tf_liste.btn_page_firstClick(Sender: TObject);
begin
   indicedebut := 1;
   affi_page;
end;


procedure Tf_liste.btn_page_priorClick(Sender: TObject);
begin
   indicedebut := indicedebut -nb_ligne.Value;
   affi_page;
end;

procedure Tf_liste.btn_page_nextClick(Sender: TObject);
begin
   indicedebut := indicedebut +nb_ligne.Value;
   affi_page;
end;

procedure Tf_liste.btn_page_lastClick(Sender: TObject);
begin
   indicedebut := (nbpages-1) *nb_ligne.Value +1;
   affi_page;
end;

procedure Tf_liste.line_add(ligne : TLoadDataSet);
begin
   if not (flux=nil) then
   begin
        flux.AjouterDesLignes(ligne);
        nbpages    := (flux.count +nb_ligne.Value -1) DIV nb_ligne.Value;
        btn_page_lastClick(btn_page_last);
        affi_page;
        sg_liste.Row := sg_liste.RowCount -1;
   end;
end;
procedure Tf_liste.line_add(ligne :  TTableauAssociatif);
begin
   flux.AjouterDesLignes(ligne);
   nbpages    := (flux.count +nb_ligne.Value -1) DIV nb_ligne.Value;
   btn_page_lastClick(btn_page_last);
   affi_page;
   sg_liste.Row := sg_liste.RowCount -1;
end;
procedure Tf_liste.line_add(ligne : Array of string);
begin
   if not (flux=nil) then
   begin
        flux.AjouterDesLignes(ligne);
        nbpages    := (flux.count +nb_ligne.Value -1) DIV nb_ligne.Value;
        btn_page_lastClick(btn_page_last);
        affi_page;
        sg_liste.Row := sg_liste.RowCount -1;
   end;
end;

procedure Tf_liste.line_edit(ligne : TLoadDataSet);
begin
   if not (flux=nil) then
   begin
        flux.ModifierDesLignes(indicedebut +sg_liste.Row -1, ligne);                                                                    // +
        affi_page;
   end;
end;

procedure Tf_liste.line_delete;
begin
   if not (flux=nil) then
   begin
        flux.SupprimerUneLigne(indicedebut +sg_liste.Row -1);
        nbpages := (flux.count +nb_ligne.Value -1) DIV nb_ligne.Value;
        if pageencours > nbpages
        then btn_page_lastClick(btn_page_last)
        else affi_page;
   end;
end;

procedure Tf_liste.nb_ligneChange(Sender: TObject);
begin
   nbpages := (flux.count +nb_ligne.Value -1) DIV nb_ligne.Value;
   if pageencours > nbpages
   then btn_page_lastClick(btn_page_last)
   else affi_page;
end;

function  Tf_liste.SumColumn (cle : string)   : real;
begin
   if not (flux=nil) then
      result := flux.SumColumn(cle)
   else result:= 0;
end;

function  Tf_liste.SumColumn (p : integer)    : real;
begin
   if not (flux=nil) then
      result := flux.SumColumn(p)
   else result:= 0;
end;
end.
