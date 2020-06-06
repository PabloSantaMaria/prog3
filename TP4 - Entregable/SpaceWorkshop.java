package entregable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

public class SpaceWorkshop {

	private static final int DAYSTOTAL = 100;
	private static final int DAYCAPACITY = 340;
	private static final int FAVDAYS = 8;
	private static final int baseBONUS = 25;
	private static final int membersMultipBONUS = 10;
	private static final int dayMultipBONUS = 5;

	private int totalCost;
	private int totalBonus;
	private int familiesAllocated;
	private List<Family> families;
	private List<Day> visitDays;

	public SpaceWorkshop(List<Family> families) {
		this.families = families;
		this.visitDays = initDays(DAYSTOTAL);
		setPopularities();
		this.totalCost = 0;
		this.totalBonus = 0;
		this.familiesAllocated = 0;
	}

	/* Costo total de bonos entregados */
	public int getTotalCost() {
		return totalCost;
	}

	/* Cantidad de bonos entregados */
	public int getTotalBonus() {
		return totalBonus;
	}

	/* Cantidad de familias que tienen día asignado */
	public int getFamiliesAllocated() {
		return familiesAllocated;
	}
	
	/* Busca una familia por id */
	private Family getFamily(int id) {
		for (Family family : families) {
			if (family.getId() == id)
				return family;
		}
		return null;
	}

	/* Devuelve un mapa con el número de día y los id de las familias asignadas a cada uno */
	public Map<Integer, List<Integer>> getVisitDays() {
		Map<Integer, List<Integer>> output = new TreeMap<>();

		for (Day visitDay : this.visitDays) {
			Integer dayNumber = visitDay.number();
			List<Integer> familyList = visitDay.getFamilies();

			output.put(dayNumber, familyList);
		}

		return output;
	}

	/*
	 * Crea 100 días
	 * en el índice 0 está el día 1
	 * en el índice 99 está el día 100
	 */
	private List<Day> initDays(int total) {
		ArrayList<Day> days = new ArrayList<>();

		for (int i = 1; i <= total; i++) {
			Day day = new Day(i, DAYCAPACITY);
			days.add(day);
		}
		
		return days;
	}

	/* Recorre sólo el 1er día preferido de las familias y le aumenta la popularidad */
	private void setPopularities() {
		for (Family family : this.families) {
			Day prefDay = visitDays.get(family.preferredDay() - 1);
			prefDay.increasePopularity();
		}
	}

	/*
	 * Ordena familias
	 * en 1er lugar, por menor popularidad de su día preferido
	 * en 2do lugar, por menor cantidad de miembros
	 */
	public void sortFamilies() {
		Collections.sort(families, new FamilyComparator(visitDays));		
	}

	/*
	 * Asigna cada familia a un día que se encuentre dentro de sus días favoritos
	 * empezando por el primero y si ese día tiene vacantes,
	 * si no, pasa al siguiente.
	 * Actualiza la cantidad de familias asignadas exitosamente
	 */
	public void allocateFamilies() {
		for (Family family : families) {
			allocate(family);
		}
	}
	private void allocate(Family family) {
		int pDayIndex = 0;
		boolean allocated = false;

		while (!allocated && pDayIndex < FAVDAYS) {
			Day visitDay = visitDays.get(family.preferedAt(pDayIndex) - 1);

			if (visitDay.allocate(family)) {
				family.setVisitDay(visitDay.number());
				familiesAllocated++;
				allocated = true;
			}
			else
				pDayIndex++;
		}
	}

	/* Calcula familia x familia el costo del bono a entregar
	 * Suma al costo total y cantidad de bonos
	 */
	public void calculateCost() {
		for (Family family : families) {
			if (family.visitDay() != family.preferredDay()) {
				family.calculateBonus(baseBONUS, membersMultipBONUS, dayMultipBONUS);
				totalBonus++;
				totalCost += family.getBonus();						
			}
		}
	}

	/* Optimiza las familias ya repartidas: 
	 * Se ordenan los días por popularidad (desc). 
	 * Se ordenan las familias por cantidad de miembros (asc). 
	 * Se selecciona por cada día, una familia numerosa 
	 * la cual NO está en su día preferido y se la saca de ese día, generando así nuevas vacantes en el día. 
	 * Luego se eligen familias menos numerosas que tienen ese día preferido y están en otro, 
	 * y se las agrega al día con nuevas vacantes. 
	 * Por último, se reasigna la familia numerosa sacada en un principio 
	 * al primer día disponible que le corresponda.
	 */
	public void optimize() {
		List<Day> daysCopy = new ArrayList<>(visitDays);
		Collections.sort(daysCopy, new PopularityComparator());
		List<Family> famCopy = new ArrayList<>(families);
		Collections.sort(famCopy, new MembersComparator());
		int maxMembers = famCopy.get(famCopy.size() - 1).members(); // 8 en este dataset

		for (Day day : daysCopy) {
			Family fToReallocate = searchFTR(day, maxMembers);
			
			if (fToReallocate != null) {
				int freeVacancy = fToReallocate.members() + day.vacancy(); // vacantes liberadas
				List<Family> swapList = searchFTS(day, freeVacancy, famCopy); // familias que entran en esas vacantes
				
				if (!swapList.isEmpty()) {
					day.removeFamily(fToReallocate.getId(), fToReallocate.members());
					familiesAllocated--;
					
					for (Family family : swapList) {
						day.allocate(family);
						family.setVisitDay(day.number());
					}
					
					allocate(fToReallocate);
				}
			}
		}
	}
	/* Retorna la 1er familia numerosa que encuentra en el día, 
	 * la cual no fue asignada a su día preferido. 
	 * Para ello, se ordenan las familias del día según su cantidad de miembros (asc) 
	 * y se recorre de atrás para adelante.
	 */
	private Family searchFTR(Day day, int maxMembers) {
		List<Integer> idList = day.getFamilies();
		List<Family> famList = new ArrayList<>();
		
		for (Integer famId : idList)
			famList.add(getFamily(famId));
			
		Collections.sort(famList, new MembersComparator());
		
		ListIterator<Family> it = famList.listIterator(famList.size());

		while (it.hasPrevious()) {
			Family f = it.previous();
			if (f.members() >= maxMembers && f.visitDay() != f.preferredDay())
				return f;
		}

		return null;
	}
	
	/* Retorna una lista de familias la cual la suma de sus miembros no supera 
	 * las vacantes disponibles. 
	 * Tienen prioridad las familias con menos miembros. 
	 * Para esto, se pasa la lista de familias ya ordenada por miembros en forma ascendente.
	 */
	private List<Family> searchFTS(Day day, int vacants, List<Family> famCopy) {
		List<Family> swapList = new ArrayList<>();
		int dayNum = day.number();
		int added = 0;
		boolean filled = false;

		Iterator<Family> it = famCopy.iterator();
		
		while (it.hasNext() && !filled) {
			Family f = it.next();
			int toAdd = f.members() + added;
			
			if (toAdd > vacants)
				filled = true;
			else {
				int prefDay = f.preferredDay();
				int visitDay = f.visitDay();
				if (dayNum == prefDay && visitDay != prefDay) {
					swapList.add(f);
					added += f.members();
				}
			}
		}
		return swapList;
	}
}
