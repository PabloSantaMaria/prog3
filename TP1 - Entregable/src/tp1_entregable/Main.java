package tp1_entregable;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		LinkedList input = new LinkedList();
		input.insertFront(28);
		input.insertFront(14);
		input.insertFront(18);
		input.insertFront(19);
		input.insertFront(7);
		input.insertFront(2);
		input.insertFront(5);
		input.insertFront(3);

		System.out.println("Entrada: " + input);

		int index = 1;

		if (!input.isEmpty()) {
			while (index < input.size()) {
				int end = getSequence(input, index);
				if (index != end) addToOutput(input, index, end);
				index = end + 1;				
			}			
		}

		System.out.println("Salida: " + output);
	}

	static List<LinkedList> output = new ArrayList<LinkedList>();
	
	private static int getSequence(LinkedList input, int start) {
		int end = start + 1;
		Integer val1 = input.get(start);
		Integer val2 = input.get(end);

		while (val1 < val2) {
			val1 = input.get(end);
			val2 = input.get(end + 1);
			end++;
		}

		return end - 1;
	}
	
	private static void addToOutput(LinkedList input, int start, int end) {
		LinkedList aux = new LinkedList();

		for (int i = start; i <= end; i++) {
			aux.insertBack(input.get(i));
		}

		output.add(aux);
	}
}
