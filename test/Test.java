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
			cycle(i,parcours);
			/*
			for(int j = 0; j< parcours.size();++j){
				System.out.println(parcours.get(j).getNom());
			}
				*/
			
		}
	}
	public void cycle(int numCercle, Vector <Cercle> parcours){
		for (int i = 0;i<listeCercles_.size();++i){
			//System.out.println(listeCercles_.get(i).getNom());
			//i == horizontal; numCercle = vertical
			parcours.add(listeCercles_.get(numCercle));
			if ((matriceDettes_.get(numCercle).get(i) != 0) && (i==parcours.get(0).getNum())) {
				System.out.println("Coucou");
				cycle(i,parcours);
				parcours.remove(parcours.size()-1);
			}
			else if ((matriceDettes_.get(numCercle).get(i) != 0) && (i==parcours.get(0).getNum())) {
				//System.out.print("Parcours : ");
				//print(parcours);
				System.out.println();
				cycles.add(parcours);//on a trouve un cycle
			}
		}
	}

}

