
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parsing parse = new Parsing(args[0]);
		Graphe graphe = new Graphe(parse.getListeCercles(),parse.getListeDettes());
	}

}
