import java.util.Vector;


public class Matrice {
	Vector <String> matrice [][];
	public Matrice(Vector <String>Cercles, Vector <String>Dettes){
		System.out.print("Cercles.size: ");
		System.out.println(Cercles.size());
		System.out.print("Dettes.size: ");
		System.out.println(Cercles.size());
		//Initialisation d'une matrice avec le nb de cercles et leur fonds(mais en 2 strings différents)
		String listeCerclesEtFonds[][] = new String [Cercles.size()][2];
		for(int i=0; i< Cercles.size(); i++){
			//prend le string "CD 20" par exemple
			String string1 = Cercles.get(i);
			System.out.print("Cercle.get(i): ");
			System.out.println(Cercles.get(i));
			//On va récupérer le cercle et le fond
			String[] CercleEtFond = string1.split(" ");
			System.out.print("CercleEtFond: ");
			System.out.println(CercleEtFond);
			//placer ds une liste pour le récupérer plus facilements
			listeCerclesEtFonds[i][0] = CercleEtFond[0];
			listeCerclesEtFonds[i][1] = CercleEtFond[1];
			for(int j=0; j< Dettes.size(); j++){
				if(i==0){
					String string2 = Dettes.get(j);
					String[] CercleEtDettes = string2.split(" ");
				}

			}
		}
	}
}
