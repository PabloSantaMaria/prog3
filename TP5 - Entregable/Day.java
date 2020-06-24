package entregable;

import java.util.ArrayList;
import java.util.List;

/* Un día, identificado por su número,
 * guarda una lista de familias asignadas */
public class Day {

	private int number;
	private int vacancy;
	private List<Family> families;

	public Day(int number, int vacancy) {
		this.number = number;
		this.vacancy = vacancy;
		this.families = new ArrayList<>();
	}

	/* Número identificador */
	public int number() {
		return this.number;
	}

	/* Lugar disponible */
	public int vacancy() {
		return this.vacancy;
	}
	
	/* Devuelve una lista de los id de las familias asignadas */
	public List<Integer> getFamilies() {
		List<Integer> output = new ArrayList<>();
		for (Family f : families)
			output.add(f.getId());
		return output;
	}

	/* Agrega una familia a la lista de familias asignadas a este día */
	public void addFamily(Family family) {
		if (!families.contains(family)) {
			families.add(family);
			vacancy -= family.members();
		}
	}
	
	/* Borra una familia de la lista y aumenta las vacantes */
	public void removeFamily(Family family) {
		if (families.contains(family)) {
			families.remove(family);
			vacancy += family.members();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	/* Un día es igual si tiene el mismo número identificador */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Day other = (Day) obj;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Día " + number + ", familias = " + this.families.size() + ", vacantes = " + vacancy;
	}
}
