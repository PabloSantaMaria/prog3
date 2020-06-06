package entregable;

import java.util.List;
import java.util.Map;

public class SolutionOpt {

	private SpaceWorkshop spaceWorkshop;
	private int totalCost;
	private int totalBonus;
	private int totalFamilies;
	private Map<Integer, List<Integer>> days;
	
	public SolutionOpt(SpaceWorkshop spaceWorkshop) {
		this.spaceWorkshop = spaceWorkshop;
	}
	
	public void solve() {
		spaceWorkshop.sortFamilies();
		spaceWorkshop.allocateFamilies();
		spaceWorkshop.optimize();
		spaceWorkshop.calculateCost();
		this.totalCost = spaceWorkshop.getTotalCost();
		this.totalBonus = spaceWorkshop.getTotalBonus();
		this.totalFamilies = spaceWorkshop.getFamiliesAllocated();
		this.days = spaceWorkshop.getVisitDays();
		
		System.out.println("Solución greedy optimizada: ");
		System.out.println("Costo total: " + totalCost);
		System.out.println("Cantidad de bonos entregados: " + totalBonus);
		System.out.println("Cantidad de familias asignadas: " + totalFamilies);
		System.out.println("Calendario: " + days);
	}
}
