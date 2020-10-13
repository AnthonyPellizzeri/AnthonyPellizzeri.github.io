unit u_select_inscription;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, FileUtil, Forms, Controls, Graphics, Dialogs, ExtCtrls,
  StdCtrls;

type

  { Tf_select_inscription }

  Tf_select_inscription = class(TForm)
    btn_ok: TButton;
    edt_code: TEdit;
    edt_libelle: TEdit;
    edt_nom: TEdit;
    edt_num: TEdit;
    lbl_libelle: TLabel;
    lbl_code: TLabel;
    lbl_nom: TLabel;
    lbl_num: TLabel;
    pnl_btn: TPanel;
    pnl_filiere_edit: TPanel;
    pnl_filiere_btn: TPanel;
    pnl_etudiant_edit: TPanel;
    pnl_etudiant_btn: TPanel;
    pnl_etudiant: TPanel;
    pnl_filiere: TPanel;
    pnl_tous_edit: TPanel;
    pnl_tous_btn: TPanel;
    pnl_tous: TPanel;
    pnl_choix: TPanel;
    pnl_titre: TPanel;
    procedure Init;
    procedure NonSelectionPanel (pnl : TPanel);
    procedure AucuneSelection;
    procedure pnl_btnClick(Sender: TObject);
    procedure pnl_choix_btnClick (Sender: TObject);
  private
    { private declarations }
  public
    { public declarations }
  end;

var
  f_select_inscription: Tf_select_inscription;

implementation

{$R *.lfm}

 uses u_feuille_style, u_list_inscription, u_modele;

 var
    pnl_actif : TPanel;

{ Tf_select_inscription }

procedure Tf_select_inscription.Init;
begin
style.panel_defaut(pnl_choix);
style.panel_selection(pnl_titre);
style.panel_defaut(pnl_btn);
pnl_choix_btnClick (pnl_tous_btn);
edt_num.clear;
edt_nom.clear;
edt_code.clear;
edt_libelle.clear;
end;

procedure Tf_select_inscription.pnl_choix_btnClick (Sender : TObject);
var pnl : TPanel;
begin
AucuneSelection; // retirer la sélection en cours
pnl := TPanel(Sender) ;
style.panel_selection (pnl);
pnl := TPanel(pnl.Parent); // récupération du panel parent "pnl_xxx"
style.panel_selection (pnl);
pnl := TPanel(f_select_inscription.FindComponent(pnl.name +'_edit'));
style.panel_selection (pnl);
pnl.show;
pnl_actif := pnl; pnl_actif.enabled := true;
btn_ok.visible := true;
end;

procedure Tf_select_inscription.NonSelectionPanel (pnl : TPanel);
var pnl_enfant : TPanel;
begin
style.panel_defaut(pnl); // affectation des paramètres Fonte et Couleur du panel pnl_choix
// récupération du panel '_btn'
pnl_enfant := TPanel(f_select_inscription.FindComponent(pnl.name +'_btn'));
style.panel_bouton(pnl_enfant);
// récupération du panel '_edit'
pnl_enfant := TPanel(f_select_inscription.FindComponent(pnl.name +'_edit'));
pnl_enfant.Hide;
end;

procedure Tf_select_inscription.AucuneSelection;
begin
NonSelectionPanel (pnl_tous);
NonSelectionPanel (pnl_etudiant);
NonSelectionPanel (pnl_filiere);
end;

procedure Tf_select_inscription.pnl_btnClick(Sender: TObject);
begin
  btn_ok.visible := false;
  pnl_actif.enabled := false;
  if  pnl_tous_edit.Visible  then
    f_list_inscription.affi_data(modele.inscription_liste_tous)
else if  pnl_etudiant_edit.visible  then
     f_list_inscription.affi_data(modele.inscription_liste_etud(edt_num.text,edt_nom.text))
else if pnl_filiere_edit.visible  then
     f_list_inscription.affi_data(modele.inscription_liste_filiere(edt_code.text,edt_libelle.text));
end;

end.

