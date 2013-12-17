import java.util.Vector;


public class Graphe {
	//Attributs
	String [][]listeCercles;
	String [][]listeDettes;
	Matrice matriceIncidence;
	
	//Constructeur
	public Graphe(Vector <String >Cercles,Vector <String >Dettes){
		System.out.println("IN Créateur graphe");
		analyseListes(Cercles,Dettes);
		Matrice matriceIncidence = new Matrice(listeCercles,listeDettes);
	}
	
	//Méthodes
	private void analyseListes(Vector <String >listeC,Vector <String >listeD){
		System.out.print("listeC.size: ");
		System.out.println(listeC.size());
		System.out.print("listeD.size: ");
		System.out.println(listeD.size());
		//Initialisation d'une matrice avec le nb de cercles et leur fonds(mais en 2 strings différents)
		this.listeCercles = new String [listeC.size()][2];
		this.listeDettes = new String [listeD.size()][3];
		for(int i = 0; i<listeC.size();++i){
			String string1 = listeC.get(i);
			//On va récupérer le cercle et le fond
			String[] CercleEtFond = string1.split(" ");
			//placer ds une liste pour le récupérer plus facilements
			this.listeCercles[i][0] = CercleEtFond[0];
			this.listeCercles[i][1] = CercleEtFond[1];
		}
		for(int j = 0; j<listeD.size();++j){
			//idem qu'au dessus mas pour les dettes
			String string2 = listeD.get(j);
			String[] CercleEtDette = string2.split(" ");
			this.listeDettes[j][0] = CercleEtDette[0];
			this.listeDettes[j][1] = CercleEtDette[1];
			this.listeDettes[j][2] = CercleEtDette[2];
		}
		/*for(int i = 0; i<listeC.size();++i){
			for(int j = 0; j<2;++j){
				System.out.println("listeCercles");
				System.out.println(this.listeCercles[i][j]);
			}
		}
		for(int i = 0; i<listeD.size();++i){
			for(int j = 0; j<3;++j){
				System.out.println("listeDettes");
				System.out.println(this.listeDettes[i][j]);
			}
		}*/
			
	}
}
