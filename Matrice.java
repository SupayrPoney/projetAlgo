import java.util.Vector;


public class Matrice {
	//Attributs
	private int matrice [][];
	//Constructeur
	public Matrice(Cercle Cercles[], String Dettes[][]){
		System.out.println("IN Matrice");
		matrice = new int[Cercles.length][Cercles.length];
		for(int i=0; i< Cercles.length; i++){
			for(int j=0; j< Cercles.length; j++){
				matrice[i][j] = 0;
			}
		}


		for(int i=0; i< Dettes.length; i++){
				for(int k=0; k< Cercles.length; k++){
					for(int l=0; l< Cercles.length; l++){
						System.out.print("nom cercle1");
						System.out.println(Dettes[i][0]);
						System.out.println(Cercles[k].getNom());
						System.out.print("nom cercle2");
						System.out.println(Dettes[i][1]);
						System.out.println(Cercles[l].getNom());
						System.out.print("Dette");
						System.out.println(Dettes[0][2]);
						System.out.print("num cercle");
						System.out.println(Cercles[0].getNum());
						System.out.print("Solde cercle");
						System.out.println(Cercles[0].getSolde());
						if(Dettes[i][0].equals(Cercles[k].getNom()) && Dettes[i][1].equals(Cercles[l].getNom())) {
							matrice[Cercles[k].getNum()][Cercles[l].getNum()] = Integer.parseInt(Dettes[i][2]);
					}
				}
			}
		}




		for(int i=0; i< Cercles.length; i++){
			for(int j=0; j< Cercles.length; j++){
				System.out.print(matrice[i][j]);
			}
			System.out.println(",");
		}
	}
	//Méthodes
	
}
