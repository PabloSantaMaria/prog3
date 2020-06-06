package entregable;

import java.util.Comparator;

/* Compara días por popularidad (cantidad de veces que fue elegido como favorito) */

public class PopularityComparator implements Comparator<Day> {

	@Override
	public int compare(Day d1, Day d2) {
		if (d1.popularity() < d2.popularity())
			return 1;
		else if (d1.popularity() > d2.popularity())
			return -1;
		else
			return 0;
	}
	
}
