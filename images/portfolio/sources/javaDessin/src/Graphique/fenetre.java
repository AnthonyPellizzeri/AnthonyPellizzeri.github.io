package Graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.io.FileOutputStream;

import java.io.ObjectOutputStream;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;





public class fenetre extends JFrame {


	private static final long serialVersionUID = 1L;
	private int hauteur = 800;
     private int longueur = 1500;
     private JMenu mnAide;
     private JMenuItem mntmEnregistrer;
     private JMenuItem mntmFermer;

	public fenetre()
     {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	getContentPane().setBackground(Color.LIGHT_GRAY);
             setTitle("Dessin Assisté");

             setLocation(10,20);
             setSize(longueur,hauteur);
             setVisible(true);
             setResizable(false);
             
     		 setIconImage(new ImageIcon("icone_java/icone_logiciel.png").getImage());
     		 
     		 // ------------- menu ------------
             
             JMenuBar menuBar = new JMenuBar();
             setJMenuBar(menuBar);
             
             JMenu mnFichier = new JMenu("Fichier");
             menuBar.add(mnFichier);
             
             JMenuItem mntmTest = new JMenuItem("Ouvrir");
             mntmTest.addActionListener(new ActionListener() {
             	public void actionPerformed(ActionEvent arg0) {
             		ouvrir();
             		
             	}
             });
             mnFichier.add(mntmTest);
             
             mntmEnregistrer = new JMenuItem("Enregistrer");
             mntmEnregistrer.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					enregistrer();
					
				}
             });
              		
             mnFichier.add(mntmEnregistrer);
             
             mntmFermer = new JMenuItem("Fermer");
             mnFichier.add(mntmFermer);
             
             mnAide = new JMenu("Aide");
             mnAide.addMouseListener(new MouseAdapter() {
             	public void mouseClicked(MouseEvent arg0) {
             		int option = JOptionPane.showConfirmDialog(null,"Pour utiliser ce logiciel vous devez:\n       -selectionner une forme \n       -pressez la souris au point d'origne puis relachez au second point  \nVous pouvez deplus modifier: \n       -la couleur du trait \n       -le replissage de la figure\n       -les angles de la figure \n attention les 2 derniers paramètres sont valables seulement pour les formes RECTANGLE et CARRE ","aide", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE, null);
                    if(option == JOptionPane.CLOSED_OPTION) {
                    }	
             	}
             });
             menuBar.add(mnAide);
             
             JMenu mnQuitter = new JMenu("Quitter");
             mnQuitter.addMouseListener(new MouseAdapter() {
                     public void mouseClicked(MouseEvent e) {
                         int option = JOptionPane.showConfirmDialog(null,"Voulez-vous vraiment quitter l'application ?","Quitter", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE, null);
                         if(option == JOptionPane.OK_OPTION) {
                    	 System.exit(0);
                         }
                     }
             });
             menuBar.add(mnQuitter);
             
            
             getContentPane().setLayout(null);
             
             //  ---------- forme ------------
             
             JPanel panel_forme = new forme();
             panel_forme.setBorder(UIManager.getBorder("CheckBox.border"));
             panel_forme.setBounds(700, 0, 495, 132);
             getContentPane().add(panel_forme);
        
             
             // ------couleur-----
             JPanel panel_couleur = new couleur();
             panel_couleur.setBorder(UIManager.getBorder("CheckBox.border"));
             panel_couleur.setBounds(10, 0, 660, 132);
             getContentPane().add(panel_couleur);
             
             //-------- dessin------
             
             JPanel panel_dessin = new dessin();
             panel_dessin.setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 1, true));
             getContentPane().add(panel_dessin);
             panel_dessin.setBounds(10, 143, 1474, 584);
             
             
             // -------- style------
             
             JPanel panel_taille = new style();
             panel_taille.setBorder(UIManager.getBorder("CheckBox.border"));
             panel_taille.setBounds(1225, 0, 176, 132);
             getContentPane().add(panel_taille);

      }
	 
	private void enregistrer() { 
		 File c_dossier = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\dessin_assiste\\");
	     c_dossier.mkdirs();
	     JFileChooser fileChooser = new JFileChooser();
	     fileChooser.setCurrentDirectory(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\dessin_assiste\\"));
	     fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	     fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Dessin","fig"));
	     fileChooser.setAcceptAllFileFilterUsed(false);
	     int result = fileChooser.showSaveDialog(this);
	     if (result == JFileChooser.APPROVE_OPTION) {
	             try {
	                     FileOutputStream f=new FileOutputStream(fileChooser.getSelectedFile());
	                     ObjectOutputStream s=new ObjectOutputStream(f);
	                     s.writeObject(null); 
	                     s.flush();
	                     s.close();        
	                     }
	             catch(Exception e){
	                     JOptionPane.showMessageDialog(null,"Problème IO","Erreur",JOptionPane.ERROR_MESSAGE);
	                     }
	             
	     }
		
		
		
	
	
	}

	     

	     
	 
	void ouvrir(){
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\dessin_assiste\\"));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {	// verifier si c'est bien un fichier
		    File selectedFile = fileChooser.getSelectedFile(); // choisi le fichier selectionné
		    
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());  // affiche fichier selectionné
		
		}
	}
}
	 
