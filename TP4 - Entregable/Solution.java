package entregable;

import java.util.List;
import java.util.Map;

public class Solution {
	private SpaceWorkshop spaceWorkshop;
	private int totalCost;
	private int totalBonus;
	private int totalFamilies;
	private Map<Integer, List<Integer>> days;
	
	public Solution(SpaceWorkshop spaceWorkshop) {
		this.spaceWorkshop = spaceWorkshop;
	}
	
	public void solve() {
		spaceWorkshop.sortFamilies();
		spaceWorkshop.allocateFamilies();
		spaceWorkshop.calculateCost();
		this.totalCost = spaceWorkshop.getTotalCost();
		this.totalBonus = spaceWorkshop.getTotalBonus();
		this.totalFamilies = spaceWorkshop.getFamiliesAllocated();
		this.days = spaceWorkshop.getVisitDays();
		
		System.out.println("Solución greedy: ");
		System.out.println("Costo total: " + totalCost);
		System.out.println("Cantidad de bonos entregados: " + totalBonus);
		System.out.println("Cantidad de familias asignadas: " + totalFamilies);
		System.out.println("Calendario: " + days);
	}

	public int getTotalCost() {
		return totalCost;
	}

	public int getTotalBonus() {
		return totalBonus;
	}

	public int getTotalFamilies() {
		return totalFamilies;
	}

	public Map<Integer, List<Integer>> getDays() {
		return days;
	}
}
