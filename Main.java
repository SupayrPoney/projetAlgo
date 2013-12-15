
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parsing parse = new Parsing(args[0]);
		CreationGraphe graphe = new CreationGraphe(parse.getListeCercles(),parse.getListeDettes());
	}

}
