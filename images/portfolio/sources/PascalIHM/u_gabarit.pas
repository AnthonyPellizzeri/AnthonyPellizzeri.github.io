unit u_gabarit;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, FileUtil, Forms, Controls, Graphics, Dialogs, Menus,
  ExtCtrls, StdCtrls;

type

  { Tf_gabarit }

  Tf_gabarit = class(TForm)
    item_accueil: TMenuItem;
    item_stat: TMenuItem;
    item_inscrit: TMenuItem;
    item_inscrit_liste: TMenuItem;
    item_archive: TMenuItem;
    item_filiere: TMenuItem;
    item_quitter: TMenuItem;
    item_archive1: TMenuItem;
    item_archive2: TMenuItem;
    item_filiere_liste: TMenuItem;
    mnu_main: TMainMenu;
    pnl_selection: TPanel;
    pnl_travail: TPanel;
    pnl_info: TPanel;
    pnl_ariane: TPanel;
    procedure FormShow(Sender: TObject);
    procedure item_accueilClick(Sender: TObject);
    procedure item_quitterClick(Sender: TObject);
    procedure mnu_item_Click(Sender: TObject);
    procedure choix_item_inscrit_liste;
  private
    { private declarations }
  public
    { public declarations }
  end;

var
  f_gabarit: Tf_gabarit;

implementation

{$R *.lfm}

{ Tf_gabarit }
USES u_feuille_style, u_accueil, u_select_inscription, u_list_inscription, u_detail_inscription, u_modele;

procedure Tf_gabarit.item_accueilClick(Sender: TObject);
begin
f_accueil.borderstyle := bsNone;
f_accueil.parent := pnl_travail;
f_accueil.align := alClient;
f_accueil.Init;
f_accueil.show;
pnl_selection.Visible := false;
pnl_ariane.caption := ' > Accueil';
end;


procedure Tf_gabarit.FormShow(Sender: TObject);
begin
  style.panel_selection (pnl_ariane);
  style.panel_defaut (pnl_selection);
  style.panel_travail (pnl_travail);
  style.panel_defaut (pnl_info);
  f_gabarit.width := 1200;
  f_gabarit.height := 800;
  f_gabarit.item_accueilClick(Sender);
  modele.open;
end;

procedure Tf_gabarit.item_quitterClick(Sender: TObject);
begin
  IF messageDlg ('Voulez-vous vraiment quitter l''application ?', mtConfirmation, [mbYes,mbNo], 0) =
mrYes
THEN Close;
modele.close;
end;


procedure Tf_gabarit.mnu_item_Click(Sender: TObject);
var
   item : TMenuItem;
begin
   pnl_selection.show;

   pnl_ariane.Caption := '';
   item := TmenuItem(Sender);
   repeat
         pnl_ariane.caption := ' >' + item.caption +pnl_ariane.Caption;
         item := item.parent;
   until item.parent = nil;
   item := TmenuItem(Sender);
if item=item_inscrit_liste then choix_item_inscrit_liste;
end;

procedure Tf_gabarit.choix_item_inscrit_liste;
begin

f_list_inscription.borderstyle := bsNone;
f_list_inscription.parent := pnl_travail;
f_list_inscription.align := alClient;
f_list_inscription.init;
f_list_inscription.show ;

f_select_inscription.borderstyle := bsNone;
f_select_inscription.parent := pnl_selection;
f_select_inscription.align := alClient;
f_select_inscription.init;
f_select_inscription.show;

f_detail_inscription.borderstyle := bsNone;
f_detail_inscription.parent := pnl_travail;
f_detail_inscription.align := alClient;
end;

end.

