package entregable;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Solution {
	private String name;
	private SpaceWorkshop spaceWorkshop;
	private Map<Integer, List<Integer>> schedule;
	
	public Solution(String name, SpaceWorkshop spaceWorkshop) {
		this.name = name;
		this.spaceWorkshop = spaceWorkshop;
	}
	
	public void solve() {
		spaceWorkshop.allocateFamilies();
		this.schedule = spaceWorkshop.getSchedule();
	}
	
	public void print() {
		System.out.println("Solución: " + name);
		System.out.println("Cantidad de estados totales: " + spaceWorkshop.totalStates());
		System.out.println("Cantidad de estados finales: " + spaceWorkshop.finalStates());
		System.out.println("Monto mínimo encontrado: $" + spaceWorkshop.getTotalCost());
		System.out.println("Cronograma: ");
		
		for (Entry<Integer, List<Integer>> day : schedule.entrySet()) {
			System.out.println("Día " + day.getKey() + ": " + day.getValue());
		}
	}
}
