package entregable;

/* Una familia, con su cantidad de dias, y una arreglo con el top de 3 dias preferidos */
public class Family implements Comparable<Family> {
	
	private int id;
	private int members;
	private int[] preferredDays;
	private int bonus;
	private int visitDay;
	
	public Family(int id, int members, int... preferredDays) {
		this.id = id;
		this.members = members;
		this.preferredDays = preferredDays;
		this.bonus = 0;
		this.visitDay = -1;
	}

	/* Id de la familia */
	public int getId() {
		return id;
	}

	/* Retorna la cantidad de miembros de la familia. */
	public int members() {
		return members;
	}

	/* Dado un indice entre 0 y 2, retorna el día preferido por la familia para ese indice. */
	public int preferedAt(int index) {
		return this.preferredDays[index];
	}
	
	/* Retorna el día preferido de la familia */
	public int preferredDay() {
		return preferedAt(0);
	}
	
	/* Dado un dia pasado por parametro, indica el orden de ese dia en el top 3 de preferencias.
	Si el dia no forma parte de las preferencias del usuario, se retorna -1. */ 
	public int prefDayIndex(int day) {
		for (int index = 0; index < preferredDays.length; index++)
			if (day == preferredDays[index])
				return index;
		return -1;
	}
	
	/* Día asignado para asistir al evento
	 * La familia que tiene como día -1, no tiene día asignado */
	public int visitDay() {
		return visitDay;
	}
	public void setVisitDay(int day) {
		this.visitDay = day;
	}
	
	/* Monto del bono compensatorio que le corresponde */
	public int getBonus() {
		return this.bonus;
	}
	
	/* Calcula el bono si el día asignado no es el preferido */
	public void setBonus(int base, int membersMultp, int dayMultip) {
		if (this.visitDay == this.preferredDay() || this.visitDay == -1)
			this.bonus = 0;
		else
			this.bonus = base + (membersMultp * members) + (dayMultip * prefDayIndex(visitDay));
	}
	
	/* Compara familias por cantidad de miembros en forma descendente */
	@Override
	public int compareTo(Family o) {
		return Integer.compare(o.members, this.members);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/* Una familia es igual si tiene el mismo id */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Family other = (Family) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Familia " + id + ": miembros = " + members + ", día asignado = " + visitDay	+ ", bono = " + bonus;
	}
}
