program tp_projet;

{$mode objfpc}{$H+}

uses
  {$IFDEF UNIX}{$IFDEF UseCThreads}
  cthreads,
  {$ENDIF}{$ENDIF}
  Interfaces, // this includes the LCL widgetset
  Forms, u_gabarit, u_feuille_style, u_accueil, u_select_inscription,
  u_list_inscription, zcomponent, u_detail_inscription, u_note_list,
  u_modele;

{$R *.res}

begin
  RequireDerivedFormResource:=True;
  Application.Initialize;
  Application.CreateForm(Tf_gabarit, f_gabarit);
  Application.CreateForm(Tf_accueil, f_accueil);
  Application.CreateForm(Tf_select_inscription, f_select_inscription);
  Application.CreateForm(Tf_list_inscription, f_list_inscription);
  Application.CreateForm(Tf_detail_inscription, f_detail_inscription);
  Application.CreateForm(Tf_note_list, f_note_list);
  Application.Run;
end.
