package entregable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SpaceWorkshop {

	private static final int DAYSTOTAL = 10;
	private static final int DAYCAPACITY = 30;
	private static final int FAVDAYS = 3;
	private static final int baseBONUS = 25;
	private static final int membersMultipBONUS = 10;
	private static final int dayMultipBONUS = 5;

	private List<Family> families;
	private List<Day> visitDays;
	private Map<Integer, List<Integer>> schedule;
	private int totalFamilies;
	
	private int totalCost;
	private int familiesAllocated;
	private int totalStates;
	private int finalStates;
	
	public SpaceWorkshop(List<Family> families) {
		this.families = families;
		this.visitDays = initDays(DAYSTOTAL);
		this.schedule = new TreeMap<>();
		this.totalFamilies = families.size();
		this.totalCost = Integer.MAX_VALUE;
		this.familiesAllocated = 0;
		this.totalStates = 0;
		this.finalStates = 0;
	}

	/* Costo total de bonos entregados */
	public int getTotalCost() {
		return totalCost;
	}

	/* Cantidad de familias que tienen día asignado */
	public int getFamiliesAllocated() {
		return familiesAllocated;
	}
	
	/* LLeva cuenta de los estados por los que pasa el algoritmo de backtracking */
	public int totalStates() {
		return this.totalStates;
	}
	
	/* Lleva la cuenta de los estados finales alcanzados por el algoritmo de backtracking */
	public int finalStates() {
		return this.finalStates;
	}

	/*
	 * Crea 10 días con capacidad de 30
	 */
	private List<Day> initDays(int total) {
		ArrayList<Day> days = new ArrayList<>();

		for (int i = 1; i <= total; i++) {
			Day day = new Day(i, DAYCAPACITY);
			days.add(day);
		}

		return days;
	}
	
	/* El cronograma es un mapeo de los días del evento y los id de las familias asignadas a cada uno */ 
	public Map<Integer, List<Integer>> getSchedule() {
		return this.schedule;
	}
	
	/* Asigna en el cronograma los días de visita y la lista de familias */
	private void setSchedule() {
		for (Day visitDay : this.visitDays) {
			Integer dayNumber = visitDay.number();
			List<Integer> familyList = visitDay.getFamilies();
			schedule.put(dayNumber, familyList);
		}
	}


	/* Algoritmo de backtracking que asigna familias a los días de visita.
	 * Las familias se van extrayendo de un arreglo ordenado por cantidad de miembros en forma descendente.
	 * Cuando no hay más familias por asignar y todas están en su día correspondiente,
	 * se actualiza el cronograma.
	 * Mantiene contadores de estados del algoritmo (estados parciales y finales) al recorrer
	 * el espacio de búsqueda.
	 */
	public void allocateFamilies() {
		List<Family> toAllocate = new ArrayList<>(this.families);
		Collections.sort(toAllocate);
		allocateFamilies(toAllocate, 0);
	}

	private void allocateFamilies(List<Family> toAllocate, int partialCost) {
		
		totalStates++;
		
		/* Caso base: no quedan familias por asignar y todas están en un día
		 * Si el costo obtenido es menor, se actualiza como solución */
		if (toAllocate.isEmpty() && familiesAllocated == totalFamilies) {
			
			finalStates++;
			
			if (partialCost < totalCost) {
				totalCost = partialCost;
				setSchedule();
			}
		}
		
		/* Toma la siguiente familia del arreglo e intenta colocarla en alguno de sus días preferidos,
		 * excepto que la rama cumpla el criterio de poda (no hay vacantes suficientes o el monto parcial
		 * ya es mayor que el mejor encontrado)
		 */
		else {
			Family currentF = toAllocate.remove(0);
			int prefDayIndex = 0;
			
			while (prefDayIndex < FAVDAYS) {
				
				Day prefDay = visitDays.get(currentF.preferedAt(prefDayIndex) - 1);
				int vacancy = prefDay.vacancy();
				int members = currentF.members();
				
				if (vacancy >= members && partialCost <= totalCost) {	// Poda
					
					addToDay(currentF, prefDay);
					int bonus = currentF.getBonus();
					partialCost += bonus;
					
					allocateFamilies(toAllocate, partialCost);
					
					removeFromDay(currentF, prefDay);
					partialCost -= bonus;
				}
				prefDayIndex++;
			}
			toAllocate.add(0, currentF); // Devuelve la familia al principio del arreglo
		}
	}

	/* Agrega la familia al día de visita
	 * La familia calcula el bono que le corresponde
	 * Aumenta el contador de familias asignadas
	 */
	private void addToDay(Family family, Day visitDay) {
		visitDay.addFamily(family);
		int dayNumber = visitDay.number();
		family.setVisitDay(dayNumber);
		
		if (dayNumber != family.preferredDay())
			family.setBonus(baseBONUS, membersMultipBONUS, dayMultipBONUS);
		
		familiesAllocated++;
	}
	
	/* Borra la familia del día de visita
	 * Disminuye el contador de familias asignadas
	 * La familia actualiza su bono
	 */
	private void removeFromDay(Family family, Day visitDay) {
		visitDay.removeFamily(family);
		familiesAllocated--;
		family.setVisitDay(-1);
		family.setBonus(0, 0, 0);
	}
}
