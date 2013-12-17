import java.util.Vector;


public class Cercle {
	//Attributs
	private int solde;
	
	augmenterSolde(int montant){
		solde += montant;
	}
	diminuerSolde(int montant){
		solde -= montant;
	}
	getsolde(){
		return solde;
	}
}