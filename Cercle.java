import java.util.Vector;


public class Cercle {
	//Attributs
	private int solde;

	public void augmenterSolde(int montant){
		solde += montant;
	}
	public void diminuerSolde(int montant){
		solde -= montant;
	}
	public int getsolde(){
		return solde;
	}
}