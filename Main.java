import java.util.Vector;
import java.io.*;

	
public class Main{	
	public static void main(String[] args) {
		System.out.println("Suppression des cycles...");


		Graphe graphe = new Graphe();
		graphe.parse(args[0]);
		graphe.analyseListes();
		Vector <Cercle> listeCercles = graphe.getListeCercles();
		Vector <Vector <String>> listeDettes = graphe.getListeDettesTemp();
		Matrice matriceDettes = new Matrice(listeCercles,listeDettes);

		//ICI ENLEVER CYCLES
		String texteGraphviz = graphe.getTextGraphViz(matriceDettes);
		graphe.sauvegarderGraphe(texteGraphviz,"dettesNoCycles.gv");
		System.out.println("Le fichier dettesNoCycles.gv contient la situation sans cycles.\nUtilisez la commande : dot -Tpng dettesNoCycles.gv -o dettesNoCycles.png pour créer l’image.");




		//ICI REMBOURSER
		String texteGraphviz = graphe.getTextGraphViz(matriceDettes);
		graphe.sauvegarderGraphe(texteGraphviz,"dettesRemb.gv");
		System.out.println("Le Fichier dettesRemb.gv contient la situation actuelle.\nUtilisez la commande : dot -Tpng dettesRemb.gv -o dettesRemb.png pour créer l’image.");


		/*
		for(int i = 0; i<listeCercles.size();++i){
				System.out.println("Le cercle " + listeCercles.get(i).getNom() + " a "+ listeCercles.get(i).getSolde());
		}
		*/
	}
}