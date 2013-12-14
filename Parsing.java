import java.util.Vector;
import java.io.*;


public class Parsing{
	//Attributs
	private Vector listeCercles = new Vector();
	private Vector listeDettes = new Vector();
	private int ordreGraphe;
	private String chaine="";
	
	//Constructeur
	public Parsing(String fichier){
		try{
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			int nbCercles = Integer.parseInt(br.readLine());
			System.out.println(nbCercles);
			while((ligne = br.readLine())!= null){
				if(nbCercles > 0){
					listeCercles.add(ligne);
					nbCercles-=1;
				}else{
					listeDettes.add(ligne);
				}
			}
			br.close();
		}catch(Exception e){
			System.out.println(e.toString());
		}	
	}
	
	//Méthodes
	public Vector getListeCercles(){
		return this.listeCercles;
	}
	public Vector getListeDettes(){
		return this.listeDettes;
	}
	public int getOrdreGraphe(){
		return this.ordreGraphe;
	}
}
