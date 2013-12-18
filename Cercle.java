import java.util.Vector;


public class Cercle {
	//Attributs
	private int solde_;
	private int num;
	private String nom;

	public Cercle(int numero,int solde, String name){
		solde_ = solde;
		num = numero;
		nom = name;
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
	public String getNom(){
		return nom;
	}
}