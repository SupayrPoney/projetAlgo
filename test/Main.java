import java.util.Vector;
import java.io.*;

	
public class Main{	

	/*
	public String getGraphVizText(Vector <Cercle> listeCercles, Vector <Vector <Integer>> matriceDettes){
		String toWrite = new String();
		//ajoute la liste des cercles
		for (int i = 0; i<listeCercles.size();++i){
			toWrite = toWrite + listeCercles.get(i) + ";\n";
		}
		//ajoute les liens (dettes)
		for (int i = 0; i<listeCercles.size();++i){
			for (int j = 0; j<listeCercles.size();++j){
				if (matriceDettes.get(i).get(j) != 0){
					toWrite = toWrite + listeCercles.get(i) + "->" + listeCercles.get(j) + "[label=" + Integer.toString(matriceDettes.get(i).get(j)) +"]" + ";\n";
				}
			}
		}
		return toWrite;


	}

	public void ecrire(String nomFichier, Vector <Cercle> listeCercles, Vector <Vector <Integer>> matriceDettes){
			
			//on met try si jamais il y a une exception
			try
			{
				
				// * BufferedWriter a besoin d un FileWriter, 
				 //* les 2 vont ensemble, on donne comme argument le nom du fichier
				 //* true signifie qu on ajoute dans le fichier (append), on ne marque pas par dessus 			 
				 
				FileWriter fw = new FileWriter(nomFichier);
				
				// le BufferedWriter output auquel on donne comme argument le FileWriter fw cree juste au dessus
				BufferedWriter output = new BufferedWriter(fw);
				
				//on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
				output.write("digraph G {\n");
				output.write(getGraphVizText(listeCercles,matriceDettes));
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
	*/
	public static void main(String[] args) {
		Vector <Cercle> listeCercles = new Vector <Cercle>();
		Vector <Vector <Integer>> matriceDettes = new Vector <Vector <Integer>>();
		listeCercles.add(new Cercle(0,150,"CI"));
		listeCercles.add(new Cercle(1,500,"ACE"));
		listeCercles.add(new Cercle(2,5000,"CP"));

		Vector <Integer>ligne0 = new Vector <Integer>();
		ligne0.add(0);
		ligne0.add(10);
		ligne0.add(20);
		Vector <Integer> ligne1 = new Vector <Integer>();
		ligne1.add(0);
		ligne1.add(0);
		ligne1.add(20);
		Vector <Integer> ligne2 = new Vector <Integer>();
		ligne2.add(200);
		ligne2.add(0);
		ligne2.add(0);

		matriceDettes.add(ligne0);
		matriceDettes.add(ligne1);
		matriceDettes.add(ligne2);

		/*Vector <Vector <Cercle>> matriceCercles = new Vector <Vector <Cercle>>();
		matriceCercles.add(listeCercles);
		matriceCercles.add(listeCercles);

		Vector <Cercle> listeCercless = new Vector <Cercle>();
		listeCercless.add(new Cercle(3,5000,"PL"));
		listeCercless.add(new Cercle(3,5000,"PL"));
		listeCercless.add(new Cercle(3,5000,"PL"));
		*/
		Test test = new Test(listeCercles,matriceDettes);

		//System.out.println(test.containsCycle(matriceCercles, listeCercless));

		test.getCycles();



		/*try{
			FileWriter fw = new FileWriter("test.dot");
				
			BufferedWriter output = new BufferedWriter(fw);
				
			output.write("digraph G {\n");
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
			output.write(toWrite);
			output.write("\n}");

			output.flush();
				
			output.close();
			System.out.println("fichier créé");
		}catch(IOException ioe){
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}*/
		
	}
}