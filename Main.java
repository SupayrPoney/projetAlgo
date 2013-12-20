import java.util.Vector;
import java.io.*;

	
public class Main{	
	public static void main(String[] args) {


		Graphe graphe = new Graphe();
		graphe.parse(args[0]);//récupère les infos dans le graphe
		graphe.analyseListes();//traite les infos
		Vector <Cercle> listeCercles = graphe.getListeCercles();//recupere les infos depuis graphe
		Matrice matriceDettes = new Matrice(listeCercles,graphe.getListeDettesTemp());//initialise une matrice de dette à partir des cercles et des dettes

		graphe.reduitCycles(matriceDettes);
		System.out.println("Suppression des cycles...");
		String texteGraphviz = graphe.getTextGraphViz(matriceDettes);//cree le texte a mettre dans le fichier .gv
		graphe.sauvegarderGraphe(texteGraphviz,"dettesNoCycles.gv");//le met dans le fichier
		System.out.println("Le fichier dettesNoCycles.gv contient la situation sans cycles.\nUtilisez la commande : dot -Tpng dettesNoCycles.gv -o dettesNoCycles.png pour créer l’image.");

		graphe.rembourser(matriceDettes);//s'occupe du remboursement des dernieres dettes
		System.out.println("Remboursement des dettes...");
		texteGraphviz = graphe.getTextGraphViz(matriceDettes);
		graphe.sauvegarderGraphe(texteGraphviz,"dettesRemb.gv");
		System.out.println("Le Fichier dettesRemb.gv contient la situation actuelle.\nUtilisez la commande : dot -Tpng dettesRemb.gv -o dettesRemb.png pour créer l’image.");

		System.out.println("---------------------------------------------\nSITUATION FINALE\n");
		
		for(int i = 0; i<listeCercles.size();++i){
				System.out.println("Le cercle " + listeCercles.get(i).getNom() + " a "+ listeCercles.get(i).getSolde());
		}
		
	}
}