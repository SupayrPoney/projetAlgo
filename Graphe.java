import java.util.Vector;
import java.io.*;


public class Graphe {
	//Attributs

	private Vector <Cercle> listeCercles;
	private Vector <Vector <Cercle>> cycles;
	private Vector <Vector <String>> listeDettesTemp;

	private Vector <String>listeCerclesString;
	private Vector <String>listeDettesString;
	private int ordreGraphe;
	private String chaine;
	
	//Constructeur
	public Graphe(){

		cycles = new Vector <Vector <Cercle>>();
	}

	public void parse(String fichier){
		try{
			listeCerclesString = new Vector <String>();
			listeDettesString = new Vector <String>();

			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);

			String ligne;
			int nbCercles = Integer.parseInt(br.readLine());

			while((ligne = br.readLine())!= null){
				//System.out.println(ligne);
				if(nbCercles > 0){//on va decrementer le nombre de cercle. Une fois arrive a 0, c'est qu'on arrive aux relations
					listeCerclesString.add(ligne);
					nbCercles-=1;
				}
				else{//on est arrive aux relations
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
			toWrite = toWrite + listeCercles.get(i).getNom()+"[label=\"" + listeCercles.get(i).getNom()+ "\\n" +"("+ listeCercles.get(i).getSolde()+")" + "\""  +"]" + ";\n";//Nom du cercle et son solde
		}

		//ajoute les liens (dettes)
		for (int i = 0; i<listeCercles.size();++i){
			for (int j = 0; j<listeCercles.size();++j){
				if (matriceDettes.getMatrice().get(i).get(j) != 0){
					toWrite = toWrite + listeCercles.get(i).getNom() + "->" + listeCercles.get(j).getNom() + "[label=" + Integer.toString(matriceDettes.getMatrice().get(i).get(j)) +"]" + ";\n";//relation avec la valeur de la dette
				}
			}
		}

		return toWrite;
	}

	public void sauvegarderGraphe(String texteGraphviz,String fileName){
			try{
				FileWriter fw = new FileWriter(fileName);
				
				
				BufferedWriter output = new BufferedWriter(fw);//crée le buffer
				
				output.write("digraph G {\n");//entete de graphviz
				output.write(texteGraphviz);//on ecrit le texte genere plus tot dans le fichier
				output.write("\n}");
				
				
				output.flush();//vide le buffer
				
				output.close();//et le ferme
			}
			catch(IOException ioe){//si erreur lors de l'ouverture du fichier
				System.out.print("Erreur : ");
				ioe.printStackTrace();
			}

	}
	public void rembourser(Matrice matriceDettes){
		int numeroOperation = 0;//permet d'afficher le numéro des opérations
		boolean actionDone = false;//permet de verifier si une action a ete effectuee
		do{
			actionDone = false;//rien n'as encore ete fait
			for (int i = 0; i<listeCercles.size();++i){
				int solde = listeCercles.get(i).getSolde();
				for (int j = 0; j<listeCercles.size();++j){
					int dette = matriceDettes.getMatrice().get(i).get(j);
					if (solde > 0 && dette != 0){
						++numeroOperation;//on a realise un transfert d'argent
						System.out.print(numeroOperation + ") ");
						if (solde>=dette){// si le le payeur peut payer toute sa dette
							System.out.print("Le cercle " + listeCercles.get(i).getNom() +" paie : " + dette + " au cercle " + listeCercles.get(j).getNom());
							solde-= dette;//le donneur perd ce qu'il donne
							listeCercles.get(i).diminuerSolde(dette);
							matriceDettes.getMatrice().get(i).set(j,0);
							listeCercles.get(j).augmenterSolde(dette);//et le receveur le recupere
						}
						else{//s'il ne peut payer qu'une partie
							System.out.print("Le cercle " + listeCercles.get(i).getNom() +" paie : " + solde+ " au cercle " + listeCercles.get(j).getNom());
							listeCercles.get(i).diminuerSolde(solde);//il donne tout ce qu'il a
							matriceDettes.getMatrice().get(i).set(j,dette-solde);
							System.out.print(" (reste a payer : ) " + matriceDettes.getMatrice().get(i).get(j));
							listeCercles.get(j).augmenterSolde(solde);//le cercle receveur depouille le cercle donneur
							solde = 0;//il donne tout ce qu'il a


						}
						System.out.println(" et possède désormais : " + listeCercles.get(i).getSolde());
						System.out.println("Le cercle " + listeCercles.get(j).getNom() + " a maintenant " + listeCercles.get(j).getSolde());
						actionDone = true;//ici on a fait qqchose, c'est reparti pour un tour

					}
				}
			}
		}while(actionDone == true);//tant qu'une action est faite
	}

	public void print(Vector <Cercle> vecteur){
		for (int i = 0;i<vecteur.size();++i){
			System.out.println(vecteur.get(i).getNom());
		}
	}

	public void getCycles(Matrice matriceDettes){
		for (int i = 0;i<listeCercles.size();++i){ // i -> lecture verticale
			Vector <Cercle> parcours = new Vector <Cercle>(); //Initialisatin du vecteur de cercles qui va sauvegarder le parcours effectué lors de la detection
			parcours.add(listeCercles.get(i)); // Au début de chaque boucle parcours aura comme premier element, le sommet dont il est question dans la boucle.
			cycle(i,parcours,matriceDettes);			
		}
	}

	public Boolean contains( Vector <Cercle> vecteur, int numCercle){ // Fonction qui permet de savoir si un cercle se trouve dans un vecteurs de cercles.
		for(int i = 0; i< vecteur.size();++i){ // On parcourt l'ensemble des cercles
			if(vecteur.get(i).getNum() == numCercle){ // On compare le numero du cercle du vecteur et le numero donné en paramètre, dans le cas d'une égalité => le cercle se trouve dans le vecteur.
				return true;
			}
		}
		return false;
	}

	public Boolean containsCycle(Vector <Vector <Cercle>> vecteurCycles, Vector <Cercle> cycle ){//Fonction qui nous permet de savoir si un cycle se trouve déjà dans "cycles" (Vector <Vector<Cercle>>)
		for(int i = 0; i< vecteurCycles.size();++i){ 
			if((vecteurCycles.get(i).size() == cycle.size())) { // on ne s'intéresse qu'aux vecteurs de même taille
				int compteur = 0; //compteur qui va sauvegarder le nombre de cercles qui apparaissent en comparant deux vecteurs
				for(int j = 0; j < cycle.size()-1;++j){ //pour ne pas reprendre le dernier
					if ((contains(vecteurCycles.get(i),cycle.get(j).getNum()))){ // utilisation de contains() vérifie si un cercle se trouve parmi un vecteur de cercles (en utilisant le numero attribué à chacun d'eux)
						compteur+=1;
					}
				}
				if(compteur == cycle.size()-1){
					return true;
				}
			}
		}
		return false;
	}
 
	public void cycle(int numCercle, Vector <Cercle> parcours,Matrice matriceDettes){
		for (int i = 0;i<listeCercles.size();++i){//i == horizontal; numCercle = vertical
			if ((matriceDettes.getMatrice().get(numCercle).get(i) != 0)){
				if(!(i==parcours.get(0).getNum()) && !(contains(parcours,listeCercles.get(i).getNum()))){ // On vérifie bien que le sommet actuel n'est pas le sommet de départ et que "parcours" ne contient pas encore le sommet actuel.
					parcours.add(listeCercles.get(i));// Ajout du sommet actuel dans "parcours" avant de rentrer dans la récursion
					cycle(i,parcours,matriceDettes);
					parcours.remove(parcours.size()-1); // Pour récupérer l'état de "parcours"
				}
				else if (i==parcours.get(0).getNum()) { // Si le sommet actuel est le sommet de départ de recherche de cycle
					parcours.add((listeCercles.get(i)));// Après avoir trouvé le cycle on ajoute le sommet qui termine le cycle.
					if (!(containsCycle(cycles,parcours))){ // Vérifie que le cycle trouvé ne se trouve pas déjà dans "cycles"
						Vector <Cercle>cycleParcours = (Vector <Cercle>)parcours.clone();// Deep copy de parcours afin de stocker la valeur actuelle de parcours dans "cycles".
						cycles.add(cycleParcours);// On ajoute le cycle trouvé à "cycles"
						for(int j = 0; j< parcours.size();++j){
							System.out.print(" ");
							System.out.print(parcours.get(j).getNom());

						}
					}
					parcours.remove(parcours.size()-1);//Comme on entre à nouveau dans la boucle, pour retrouver l'état de parcours avant la boucle.
				}
			}

		}
	}	

    public int trouveMin(Vector <Cercle>cycle, Matrice matriceDettes){
        int minimum = matriceDettes.getMatrice().get(cycle.get(0).getNum()).get(cycle.get(1).getNum());//min = 1ere dette
        for (int i = 0;i< cycle.size(); ++i){
            int indicei = cycle.get(i).getNum();
            int indicej;
            if (i!= cycle.size()-1){//si c'est pas le dernier element
                    indicej = cycle.get(i+1).getNum();
            }
            else{
                    indicej = cycle.get(0).getNum();
            }
            if (matriceDettes.getMatrice().get(indicei).get(indicej)<minimum){//si la dette entre les 2 est plus petite que le min courant
                minimum = matriceDettes.getMatrice().get(indicei).get(indicej);//remplacer le min
            }


        }
        return minimum;
    }
     
    //Fonction qui réduit la solde pour tout un cycle
    public void reductionSolde (Vector <Cercle> cycle, int min){
    	System.out.println("reduction de : " + min);
        for(int i = 0; i> cycle.size(); ++i){ //on parcourt le cycle et on diminue de manière égale la solde de tous les cercles du cycle
            cycle.get(i).diminuerSolde(min);
            System.out.print(cycle.get(i).getNom()+"("+cycle.get(i).getSolde() + ")->");
        }
    	System.out.println("...");

    }

    public void reduitCycles(Matrice matriceDettes){
    	getCycles(matriceDettes);
    	for (int i = 0;i<cycles.size();++i){
    		/*
    		reduction de 120
			CI (120)-> CePha (140)-> CI (120)-> ...
			nouvelle situation :
			CePha (20)-> CI
			*/
    		reductionSolde(cycles.get(i),trouveMin(cycles.get(i),matriceDettes));
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
