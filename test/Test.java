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
		for (int i = 0;i<listeCercles_.size();++i){
			Vector <Cercle> parcours = new Vector <Cercle>();
			parcours.add(listeCercles_.get(i));
			cycle(i,parcours);			
		}
	}

	public Boolean contains( Vector <Cercle> vecteur, int numCercle){
		for(int i = 0; i< vecteur.size();++i){
			/*System.out.print("getNum: ");
			System.out.println(vecteur.get(i).getNum());
			System.out.print("numCercle: ");
			System.out.println(numCercle);*/
			if(vecteur.get(i).getNum() == numCercle){
				//System.out.println("EGALITE");
				//System.out.println();
				return true;
			}
			System.out.println();
		}
		return false;
	}

	public Boolean containsCycle(Vector <Vector <Cercle>> vecteurCycles, Vector <Cercle> cycle ){
		for(int i = 0; i< vecteurCycles.size();++i){
			if((vecteurCycles.get(i).size() == cycle.size())) {
				int compteur = 0;
				for(int j = 0; j < cycle.size()-1;++j){ //pour ne pas reprendre le dernier)
					if ((contains(vecteurCycles.get(i),cycle.get(j).getNum()))){
						compteur+=1;
						//System.out.print("compteur: ");
						//System.out.println(compteur);
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
		//System.out.println();
		//System.out.println("APPEL A CYCLE");
		//System.out.println();
		for (int i = 0;i<listeCercles_.size();++i){
			//i == horizontal; numCercle = vertical
			if ((matriceDettes_.get(numCercle).get(i) != 0)){
				if(!(i==parcours.get(0).getNum()) && !(contains(parcours,listeCercles_.get(i).getNum()))){
					//System.out.println("Coucou");
					parcours.add(listeCercles_.get(i));
					cycle(i,parcours);
					parcours.remove(parcours.size()-1);
				}
				else if (i==parcours.get(0).getNum()) {
					//System.out.println("Cycle trouvé");
					parcours.add((listeCercles_.get(i)));
					//System.out.println("Print avant containsCycle");
					/*for(int j = 0; j< parcours.size();++j){
							System.out.print(" ");
							System.out.print(parcours.get(j).getNom());
					}*/
					System.out.println();
					if (!(containsCycle(cycles,parcours))){
						Vector <Cercle>cycleParcours = (Vector <Cercle>)parcours.clone();
						cycles.add(cycleParcours);//on a trouve un cycle
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
					parcours.remove(parcours.size()-1);
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

