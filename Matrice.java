import java.util.Vector;


public class Matrice {
	//Attributs
	private Vector<String> matrice [][];
	//Constructeur
	public Matrice(String Cercles[][], String Dettes[][]){
		System.out.println("IN Matrice");
		matrice = new Vector[Cercles.length][Dettes.length];
		for(int i=0; i< Cercles.length; i++){
			for(int j=0; j< Dettes.length; j++){
				Vector<String> element = new Vector();
				System.out.print("Dettes[0]");
				System.out.println(Dettes[j][0]);
				System.out.print("Dettes[1]");
				System.out.println(Dettes[j][1]);
				System.out.print("Cerles[0]");
				System.out.println(Cercles[i][0]);
				if(Cercles[i][0].equals(Dettes[j][0])){
					//si c'est le début de l'arc
					element.add("1");
					element.add(Dettes[j][2]);
					matrice[i][j] = element;
				}
				else if(Cercles[i][0].equals(Dettes[j][1])){
					//si c'est la fin de l'arc
					element.add("-1");
					element.add(Dettes[j][2]);
					matrice[i][j] = element;
				}
				else{
					// si l'arc ne passe pas par ces sommets
					element.add("0");
					element.add("0");
					matrice[i][j] = element;
				}
			}
		}
		for(int i=0; i< Cercles.length; i++){
			for(int j=0; j< Dettes.length; j++){
				System.out.print(matrice[i][j]);
			}
			System.out.println(",");
		}
	}
	//Méthodes
}
