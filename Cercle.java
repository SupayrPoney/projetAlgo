import java.util.Vector;


public class Cercle {
	//Attributs
	private int solde_;
	private int num;

	public Cercle(int numero,int solde){
		solde_ = solde;
		num = numero;
	}
	public void augmenterSolde(int montant){
		solde_ += montant;
	}
	public void diminuerSolde(int montant){
		solde_ -= montant;
	}
	public int getNum(){
		return num;
	}
	public int getSolde(){
		return solde_;
	}
}