package entregable;

import java.util.ArrayList;
import java.util.List;

/* Un día, identificado por su número,
 * guarda una lista de familias asignadas, cantidad de vacantes y popularidad de elección entre las familias
 * Es responsable de admitir una familia si hay vacantes suficientes
 */
public class Day {

	private int number;
	private int vacancy;
	private int popularity;
	private List<Integer> families;

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

	/* Cantidad de veces que fue elegido por las familias */
	public int popularity() {
		return popularity;
	}
	/* Devuelve una lista de los id de las familias asignadas */
	public List<Integer> getFamilies() {
		return families;
	}

	/* Aumenta la popularidad en +1 */
	public void increasePopularity() {
		this.popularity++;
	}

	/* Agrega una familia a la lista de familias asignadas a este día, 
	 * si hay vacantes */
	public boolean allocate(Family family) {
		int members = family.members();
		if (this.vacancy >= members) {
			this.families.add(family.getId());
			this.vacancy -= members;
			return true;
		}
		return false;
	}
	
	/* Borra una familia de la lista y aumenta las vacantes */
	public void removeFamily(Integer id, int members) {
		if (families.contains(id)) {
			families.remove(id);
			this.vacancy += members;
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
		return "Día " + number + ": popularidad = " + popularity + ", familias = " + this.families.size() + ", vacantes = " + vacancy;
	}
}
