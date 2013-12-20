import java.util.Vector;
import java.io.*;

	
public class Main{	
	public static void main(String[] args) {


		Graphe graphe = new Graphe(args[0]);
		
		System.out.println("Cyles supprimés: ");
		graphe.reduitCycles();
		String texteGraphviz = graphe.getTextGraphViz();//cree le texte a mettre dans le fichier .gv
		graphe.sauvegarderGraphe(texteGraphviz,"dettesNoCycles.gv");//le met dans le fichier
		System.out.println("Le fichier dettesNoCycles.gv contient la situation sans cycles.\nUtilisez la commande : dot -Tpng dettesNoCycles.gv -o dettesNoCycles.png pour créer l’image.");
		System.out.println();
		System.out.println("Ordre de remboursement: ");
		System.out.println();
		graphe.rembourser();//s'occupe du remboursement des dernieres dettes

		texteGraphviz = graphe.getTextGraphViz();
		graphe.sauvegarderGraphe(texteGraphviz,"dettesRemb.gv");
		System.out.println("Le Fichier dettesRemb.gv contient la situation actuelle.\nUtilisez la commande : dot -Tpng dettesRemb.gv -o dettesRemb.png pour créer l’image.");

		System.out.println("---------------------------------------------\nSITUATION FINALE\n");
		
		for(int i = 0; i< graphe.getListeCercles().size();++i){
				System.out.println("La solde du cercle " + graphe.getListeCercles().get(i).getNom() + " est de "+ graphe.getListeCercles().get(i).getSolde());
		}
		
	}
}