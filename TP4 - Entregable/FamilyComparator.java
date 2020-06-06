package entregable;

import java.util.Comparator;
import java.util.List;

/**
 * Compara familias:
 * en 1er lugar, por menor popularidad de su día preferido
 * en 2do lugar, por menor cantidad de miembros
 */

public class FamilyComparator implements Comparator<Family> {
	
	private List<Day> visitDays;
	
	public FamilyComparator(List<Day> visitDays) {
		this.visitDays = visitDays;
	}
	
	@Override
	public int compare(Family f1, Family f2) {
		
		Integer f1PrefDayPop = visitDays.get(f1.preferredDay() - 1).popularity();
		Integer f2PrefDayPop = visitDays.get(f2.preferredDay() - 1).popularity();
		int popularityComp = f1PrefDayPop.compareTo(f2PrefDayPop);
		
		if (popularityComp != 0)
			return popularityComp;
		
		Integer f1Members = f1.members();
		Integer f2Members = f2.members();
		
		return f1Members.compareTo(f2Members);
	}
}
