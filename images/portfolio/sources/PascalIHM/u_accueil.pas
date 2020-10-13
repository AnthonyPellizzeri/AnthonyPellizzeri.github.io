unit u_accueil;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, FileUtil, Forms, Controls, Graphics, Dialogs, StdCtrls,
  ExtCtrls;

type

  { Tf_accueil }

  Tf_accueil = class(TForm)
    lbl_accueil: TLabel;
    pnl_accueil: TPanel;
    procedure Init;
  private
    { private declarations }
  public
    { public declarations }
  end;

var
  f_accueil: Tf_accueil;

implementation

{$R *.lfm}

uses u_feuille_style;


procedure Tf_accueil.Init;
begin
style.panel_travail(pnl_accueil);
end;


end.

