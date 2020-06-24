package entregable;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		CSVReader reader1 = new CSVReader("./data/familias-1.csv");
		ArrayList<Family> familias1 = reader1.read();
		CSVReader reader2 = new CSVReader("./data/familias-2.csv");
		ArrayList<Family> familias2 = reader2.read();
		
		SpaceWorkshop sw1 = new SpaceWorkshop(familias1);
		Solution sol1 = new Solution("familias-1", sw1);
		sol1.solve();
		sol1.print();
		
		System.out.println();
		
		SpaceWorkshop sw2 = new SpaceWorkshop(familias2);
		Solution sol2 = new Solution("familias-2", sw2);
		sol2.solve();
		sol2.print();
	}
}
