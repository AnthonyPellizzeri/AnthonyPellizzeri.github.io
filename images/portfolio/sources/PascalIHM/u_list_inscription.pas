unit u_list_inscription;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, FileUtil, Forms, Controls, Graphics, Dialogs, ExtCtrls,
  Buttons, Grids, Spin, StdCtrls, u_liste;

type

  { Tf_list_inscription }

  Tf_list_inscription = class(TF_liste)
    procedure btn_line_addClick(Sender: TObject);
    procedure btn_line_deleteClick(Sender: TObject);
    procedure btn_line_detailClick(Sender: TObject);
    procedure btn_line_editClick(Sender: TObject);
  procedure Init;
  private
    { private declarations }
  public
    { public declarations }
  end;

var
  f_list_inscription: Tf_list_inscription;

implementation

{$R *.lfm}

{ Tf_list_inscription }

uses u_feuille_style, u_detail_inscription;

procedure Tf_list_inscription.btn_line_addClick(Sender: TObject);
begin
  f_detail_inscription.add;
end;

procedure Tf_list_inscription.btn_line_deleteClick(Sender: TObject);
begin
  f_detail_inscription.delete (sg_liste.cells[0,sg_liste.row]);
end;

procedure Tf_list_inscription.btn_line_detailClick(Sender: TObject);
begin
  f_detail_inscription.detail (sg_liste.cells[0,sg_liste.row]);
end;

procedure Tf_list_inscription.btn_line_editClick(Sender: TObject);
begin
  f_detail_inscription.edit (sg_liste.cells[0,sg_liste.row]);
end;

procedure Tf_list_inscription.Init;
begin
style.panel_travail(pnl_titre);
style.panel_travail(pnl_btn);
style.panel_travail(pnl_affi);
style.grille (sg_liste);
end;

end.

