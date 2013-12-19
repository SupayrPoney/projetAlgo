import java.util.Vector;
import java.io.*;


public class Graphe {
	//Attributs
	String [][]listeDettes;
	Cercle nextCercle;

	private Vector <Cercle> listeCercles;
	private Vector <Vector <Cercle>> cycles;
	private Vector <Vector <String>> listeDettesTemp;

	private Vector <String>listeCerclesString;
	private Vector <String>listeDettesString;
	private int ordreGraphe;
	private String chaine;
	
	//Constructeur
	public Graphe(){
		System.out.println("IN Créateur graphe");
	}
	public void parse(String fichier){
		System.out.println("IN Parsing");
		try{
			listeCerclesString = new Vector <String>();
			listeDettesString = new Vector <String>();

			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);

			String ligne;
			int nbCercles = Integer.parseInt(br.readLine());
			//System.out.println(nbCercles);
			while((ligne = br.readLine())!= null){
				//System.out.println(ligne);
				if(nbCercles > 0){
					listeCerclesString.add(ligne);
					nbCercles-=1;
				}
				else{
					listeDettesString.add(ligne);
				}
			}
			br.close();
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void analyseListes(){

		//Initialisation d'une matrice avec le nb de cercles et leur fonds(mais en 2 strings différents)
		listeCercles = new Vector <Cercle>();
		listeDettesTemp = new Vector <Vector <String>>();

		for(int i = 0; i<listeCerclesString.size();++i){
			String string1 = listeCerclesString.get(i);
			String[] cercleEtFond = string1.split(" ");//On va récupérer le cercle et le fond dans une liste de dim = 2

			listeCercles.add(new Cercle(i,Integer.parseInt(cercleEtFond[1]),cercleEtFond[0]));//placer ds une liste pour le récupérer plus facilement
		}

		for(int j = 0; j<listeDettesString.size();++j){
			//idem qu'au dessus mas pour les dettes
			String string2 = listeDettesString.get(j);
			String[] cercleEtDette = string2.split(" ");
			Vector <String> temp = new Vector <String>();

			for(int a = 0; a<3;++a){
				temp.add(cercleEtDette[a]);//ajoute des strings dans le vecteur
			}
			listeDettesTemp.add(temp);//ajoute le  vecteur dans la "matrice"
		}	
	}

	public String getTextGraphViz(Matrice matriceDettes){
		String toWrite = new String();
		//ajoute la liste des cercles
		for (int i = 0; i<listeCercles.size();++i){
			toWrite = toWrite + listeCercles.get(i).getNom()+"[label=\"" + listeCercles.get(i).getNom()+ "\\n" +"("+ listeCercles.get(i).getSolde()+")" + "\""  +"]" + ";\n";
		}
		//ajoute les liens (dettes)

		System.out.println("AVANT = " + matriceDettes.getMatrice().get(0));

		for (int i = 0; i<listeCercles.size();++i){
			for (int j = 0; j<listeCercles.size();++j){
				if (matriceDettes.getMatrice().get(i).get(j) != 0){
					toWrite = toWrite + listeCercles.get(i).getNom() + "->" + listeCercles.get(j).getNom() + "[label=" + Integer.toString(matriceDettes.getMatrice().get(i).get(j)) +"]" + ";\n";
				}
			}
		}
		System.out.println("APRES");

		return toWrite;
	}

	public void sauvegarderGraphe(String texteGraphviz,String fileName){
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
	//Getters
	public Vector <Vector <String>> getListeDettesTemp(){
		return listeDettesTemp;
	}

	public Vector <Cercle> getListeCercles(){
		return listeCercles;
	}



}
