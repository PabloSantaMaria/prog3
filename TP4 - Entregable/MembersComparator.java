package entregable;

import java.util.Comparator;

/* Compara familias por cantidad de miembros */

public class MembersComparator implements Comparator<Family> {
	@Override
	public int compare(Family f1, Family f2) {
		
		Integer f1Members = f1.members();
		Integer f2Members = f2.members();
		
		return f1Members.compareTo(f2Members);
	}
}
