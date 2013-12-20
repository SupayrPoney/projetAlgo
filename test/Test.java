import java.util.Vector;
import java.io.*;

	
public class Test{
	public Vector <Vector <Integer>> matriceDettes_;
	public Vector <Cercle> listeCercles_;
	public Vector <Vector <Cercle>> cycles;

	public Test(Vector <Cercle> listeCercles,Vector <Vector <Integer>> matriceDettes){
		listeCercles_ = listeCercles;
		matriceDettes_ = matriceDettes;
		cycles = new Vector <Vector <Cercle>>();
	}

	public void print(Vector <Cercle> vecteur){
		for (int i = 0;i<vecteur.size();++i){
			System.out.println(vecteur.get(i).getNom());
		}
	}

	public void getCycles(){
		for (int i = 0;i<listeCercles_.size();++i){ // i -> lecture verticale
			Vector <Cercle> parcours = new Vector <Cercle>(); //Initialisatin du vecteur de cercles qui va sauvegarder le parcours effectué lors de la detection
			parcours.add(listeCercles_.get(i)); // Au début de chaque boucle parcours aura comme premier element, le sommet dont il est question dans la boucle.
			cycle(i,parcours);			
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
 
	public void cycle(int numCercle, Vector <Cercle> parcours){
		for (int i = 0;i<listeCercles_.size();++i){//i == horizontal; numCercle = vertical
			if ((matriceDettes_.get(numCercle).get(i) != 0)){
				if(!(i==parcours.get(0).getNum()) && !(contains(parcours,listeCercles_.get(i).getNum()))){ // On vérifie bien que le sommet actuel n'est pas le sommet de départ et que "parcours" ne contient pas encore le sommet actuel.
					parcours.add(listeCercles_.get(i));// Ajout du sommet actuel dans "parcours" avant de rentrer dans la récursion
					cycle(i,parcours);
					parcours.remove(parcours.size()-1); // Pour récupérer l'état de "parcours"
				}
				else if (i==parcours.get(0).getNum()) { // Si le sommet actuel est le sommet de départ de recherche de cycle
					//System.out.println("Cycle trouvé");
					parcours.add((listeCercles_.get(i)));// Après avoir trouvé le cycle on ajoute le sommet qui termine le cycle.
					//System.out.println("Print avant containsCycle");
					/*for(int j = 0; j< parcours.size();++j){
							System.out.print(" ");
							System.out.print(parcours.get(j).getNom());
					}*/
					if (!(containsCycle(cycles,parcours))){ // Vérifie que le cycle trouvé ne se trouve pas déjà dans "cycles"
						Vector <Cercle>cycleParcours = (Vector <Cercle>)parcours.clone();// Deep copy de parcours afin de stocker la valeur actuelle de parcours dans "cycles".
						cycles.add(cycleParcours);// On ajoute le cycle trouvé à "cycles"
						//System.out.println();
						/*System.out.println("LECTURE NUM CYCLES");
						for(int m = 0; m< cycles.size();++m){
							for(int n = 0; n< cycles.get(0).size();++n){
								System.out.print(cycles.get(m).get(n).getNum());
							}
							System.out.println();
						}*/
						System.out.println("PRINT DE CYCLE YOLOL OLOLOL SWAGGGG TROP CONTENT");
						for(int j = 0; j< parcours.size();++j){
							System.out.print(" ");
							System.out.print(parcours.get(j).getNom());

						}
					}
					parcours.remove(parcours.size()-1);//Comme on entre à nouveau dans la boucle, pour retrouver l'état de parcours avant la boucle.
					/*if(parcours.size()>2){
						parcours.remove(parcours.size()-1);
						System.out.println("Print Après avoir trouvé");
						for(int j = 0; j< parcours.size();++j){
							System.out.print(" ");
							System.out.print(parcours.get(j).getNom());
						}
						System.out.println();
					}*/
				}
			}

		}
	}

}

