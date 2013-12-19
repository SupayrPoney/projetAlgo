import java.util.Vector;
import java.io.*;


public class Graphe {
	//Attributs
	Vector <Cercle> listeCercles;
	String [][]listeDettes;
	Matrice matriceIncidence;
	Cercle nextCercle;
	
	//Constructeur
	public Graphe(Vector <String >cercles,Vector <String >dettes){
		System.out.println("IN Créateur graphe");
		analyseListes(cercles,dettes);
		Matrice matriceIncidence = new Matrice(listeCercles,listeDettes);
	}
	
	//Méthodes
	private void analyseListes(Vector <String >listeC,Vector <String >listeD){
		System.out.print("listeC.size: ");
		System.out.println(listeC.size());
		System.out.print("listeD.size: ");
		System.out.println(listeD.size());
		//Initialisation d'une matrice avec le nb de cercles et leur fonds(mais en 2 strings différents)
		this.listeCercles = new Vector [listeC.size()];//TODO a modifier
		this.listeDettes = new String [listeD.size()][3];
		for(int i = 0; i<listeC.size();++i){
			String string1 = listeC.get(i);
			//On va récupérer le cercle et le fond
			String[] cercleEtFond = string1.split(" ");
			Cercle cercle = new Cercle(i,Integer.parseInt(cercleEtFond[1]),cercleEtFond[0]);
			//placer ds une liste pour le récupérer plus facilements
			this.listeCercles[i] = cercle;
		}
		for(int j = 0; j<listeD.size();++j){
			//idem qu'au dessus mas pour les dettes
			String string2 = listeD.get(j);
			String[] cercleEtDette = string2.split(" ");
			this.listeDettes[j][0] = cercleEtDette[0];
			this.listeDettes[j][1] = cercleEtDette[1];
			this.listeDettes[j][2] = cercleEtDette[2];
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

	public Vector <Cercle> cycle(int numCercle, Vector <Cercle> parcours){
		for (int i = 0;i<listeCercles.size();++i){
			//i == horizontal; numCercle = vertical
			parcours.add(listeCercles.get(numCercle));
			if ((matriceDettes[numCercle][i] != 0) && !(i==parcours[0].getNum())) {
				
				getCycles(i,parcours);
				parcours.remove(parcours.size()-1);
			}
			else if ((matriceDettes[numCercle][i] != 0) && (i==parcours[0].getNum())) {
				cycles.add(parcours);//on a trouve un cycle
			}
		}
	}

	private Vector<Vector<Cercle>> cycles;
	private Vector <Cercle>listeCercles;
	private Matrice matriceDettes;

	public Vector getCycles(){
		for (int i = 0;i<listeCercles.size();++i){
			Vector <Cercle> parcours = new Vector();
			cycle(i,parcours);
		}
	}

	public String getTextGraphViz(){
		String toWrite = new String();
		//ajoute la liste des cercles
		for (int i = 0; i<listeCercles.size();++i){
			toWrite = toWrite + listeCercles.get(i).getNom()+"[label=\"" + listeCercles.get(i).getNom()+ "\\n" +"("+ listeCercles.get(i).getSolde()+")" + "\""  +"]" + ";\n";
		}
		//ajoute les liens (dettes)
		for (int i = 0; i<listeCercles.size();++i){
			for (int j = 0; j<listeCercles.size();++j){
				if (matriceDettes.get(i).get(j) != 0){
					toWrite = toWrite + listeCercles.get(i).getNom() + "->" + listeCercles.get(j).getNom() + "[label=" + Integer.toString(matriceDettes.get(i).get(j)) +"]" + ";\n";
				}
			}
		}
		return toWrite;
	}

	public void sauvegarderGraphe(String texteGraphviz){
			//on met try si jamais il y a une exception
			try{
				/**
				 * BufferedWriter a besoin d un FileWriter, 
				 * les 2 vont ensemble, on donne comme argument le nom du fichier
				 * true signifie qu on ajoute dans le fichier (append), on ne marque pas par dessus 			 
				 */
				FileWriter fw = new FileWriter("graph.dot");
				
				// le BufferedWriter output auquel on donne comme argument le FileWriter fw cree juste au dessus
				BufferedWriter output = new BufferedWriter(fw);
				
				//on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
				output.write("digraph G {\n");
				output.write(texteGraphviz);
				output.write("\n}");
				//on peut utiliser plusieurs fois methode write
				
				output.flush();
				//ensuite flush envoie dans le fichier, ne pas oublier cette methode pour le BufferedWriter
				
				output.close();
				//et on le ferme
				System.out.println("fichier créé");
			}
			catch(IOException ioe){
				System.out.print("Erreur : ");
				ioe.printStackTrace();
			}

	}


//TODO, pas de [] pour vector
	public int trouveMin(Vector <Cercle>listeCercles, Matrice matriceDettes){
		int minimum = 0;
		for (int i = 0;i< listeCercles.size(); ++i){
			int indicei = listeCercles[i].getNum();
			if (i!= listeCercles.size()-1){//si c'est pas le dernier element
				int indicej = listeCercles[i+1].getNum();
			}
			else{
				int indicej = listeCercles[0].getNum();
			}
			if (matriceDettes[indicei][indicej]<minimum){
				minimum = matriceDettes[indicei][indicej];
			}


		}
		return minimum;
	}

//Fonction qui réduit la solde pour tout un cycle
	public void reductionSolde (Vector <Cercle> cycle, int min){
        for(int i = 0; i> cycle.size(); ++i){
            cycle.get(i).diminuerSolde(min);
        }
    }
}
