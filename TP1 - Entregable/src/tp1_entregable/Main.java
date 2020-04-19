package tp1_entregable;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		LinkedList entrada = new LinkedList();
		entrada.insertFront(28);
		entrada.insertFront(14);
		entrada.insertFront(19);
		entrada.insertFront(7);
		entrada.insertFront(2);
		entrada.insertFront(2);
		entrada.insertFront(5);
		entrada.insertFront(3);
		
		System.out.println("Entrada: " + entrada);
		
		int index = 1;
		
		while (index < entrada.size()) {
			if (!entrada.isEmpty()) {
				int fin = buscarSecuencia(entrada, index);
				agregarSalida(entrada, index, fin);
				index = fin + 1;				
			}
		}
		
		System.out.println("Salida: " + salida);
		
		
	}
	static List<LinkedList> salida = new ArrayList<LinkedList>();
	
	private static void agregarSalida(LinkedList entrada, int inicio, int fin) {
		LinkedList aux = new LinkedList();
		
		for (int i = inicio; i <= fin; i++) {
			aux.insertBack(entrada.get(i));
		}
		if (aux.size() >= 2) {
			salida.add(aux);	
		}
	}

	private static int buscarSecuencia(LinkedList entrada, int start) {
		int end = start + 1;
		Integer val1 = entrada.get(start);
		Integer val2 = entrada.get(end);
		
		while (val1 < val2) {
			val1 = entrada.get(end);
			val2 = entrada.get(end + 1);
			end++;
		}
		return end - 1;
	}
}
