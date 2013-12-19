import java.util.Vector;


public class Matrice {
	//Attributs
	private Vector <Vector <Integer>> matrice;
	//Constructeur
	public Matrice(Vector <Cercle> listeCercles,Vector <Vector <String>> listeDettes){
		System.out.println("IN Matrice");
		matrice = new Vector <Vector <Integer>>();
		//initialise la matrice pour pouvoir changer une valeur n'importe ou
		Vector <Integer> line = new Vector <Integer>();
		for(int i=0; i< listeCercles.size(); i++){
			line = new Vector <Integer>();
			for(int j=0; j< listeCercles.size(); j++){
				line.add(0);
			}
			matrice.add(line);
		}

		System.out.println("taille listeDettes : " + listeDettes.size());
		System.out.println("taille listeCercles : " + listeCercles.size());

		for(int i=0; i< listeDettes.size(); i++){
				for(int k=0; k< listeCercles.size(); k++){
					for(int l=0; l< listeCercles.size(); l++){

						//System.out.println("i : "+i+ " k: " + k + " l: " + l);
						/*
						System.out.print("nom cercle1");
						System.out.println(listeDettes[i][0]);
						System.out.println(listeCercles[k].getNom());
						System.out.print("nom cercle2");
						System.out.println(listeDettes[i][1]);
						System.out.println(listeCercles[l].getNom());
						System.out.print("Dette");
						System.out.println(listeDettes[0][2]);
						System.out.print("num cercle");
						System.out.println(listeCercles[0].getNum());
						System.out.print("Solde cercle");
						System.out.println(listeCercles[0].getSolde());
						*/
						if(listeDettes.get(i).get(0).equals(listeCercles.get(k).getNom()) && listeDettes.get(i).get(1).equals(listeCercles.get(l).getNom())) {
							matrice.get(k).set(l,Integer.parseInt(listeDettes.get(i).get(2)));
							
							//matrice[listeCercles[k].getNum()] [listeCercles[l].getNum()] = Integer.parseInt(listeDettes[i][2]);
					}
				}
			}
		}

	}
	public Vector <Vector <Integer>> getMatrice(){
		return matrice;
	}
	//Méthodes
	
}
