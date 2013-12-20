import java.util.Vector;


public class Matrice {
	//Attributs
	private Vector <Vector <Integer>> matrice;
	//Constructeur
	public Matrice(Vector <Cercle> listeCercles,Vector <Vector <String>> listeDettes){
		matrice = new Vector <Vector <Integer>>();
		Vector <Integer> line = new Vector <Integer>();

		//initialise la matrice pour pouvoir changer une valeur n'importe ou
		for(int i=0; i< listeCercles.size(); i++){
			line = new Vector <Integer>();
			for(int j=0; j< listeCercles.size(); j++){
				line.add(0);
			}
			matrice.add(line);
		}

		for(int i=0; i< listeDettes.size(); i++){
				for(int k=0; k< listeCercles.size(); k++){
					for(int l=0; l< listeCercles.size(); l++){
						if(listeDettes.get(i).get(0).equals(listeCercles.get(k).getNom()) && listeDettes.get(i).get(1).equals(listeCercles.get(l).getNom())) {
							matrice.get(k).set(l,Integer.parseInt(listeDettes.get(i).get(2)));
					}
				}
			}
		}

	}
	//Methode
	public Vector <Vector <Integer>> getMatrice(){
		return matrice;
	}
	
}
