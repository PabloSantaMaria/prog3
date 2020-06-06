package entregable;

import java.util.ArrayList;

public class Main {

	public static void main(String... args) {

		CSVReader reader = new CSVReader("./data/familias.csv");	
		ArrayList<Family> families = reader.read();
		
		SpaceWorkshop sw1 = new SpaceWorkshop(families);
		Solution geedy = new Solution(sw1);
		geedy.solve();
		
		SpaceWorkshop sw2 = new SpaceWorkshop(families);
		SolutionOpt greedyOpt = new SolutionOpt(sw2);
		greedyOpt.solve();
		
	}
}
