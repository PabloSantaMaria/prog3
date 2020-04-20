package tp1_entregable;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		LinkedList input = new LinkedList();
		input.insertFront(28);
		input.insertFront(28);
		input.insertFront(14);
		input.insertFront(18);
		input.insertFront(17);
		input.insertFront(20);
		input.insertFront(19);
		input.insertFront(5);
		input.insertFront(5);
		input.insertFront(7);
		input.insertFront(2);
		input.insertFront(2);
		input.insertFront(5);
		input.insertFront(3);

		System.out.println("Entrada: " + input);
		
		LinkedListIterator it = input.iterator();
		
		int index = 1;
		
		while (it.hasNext()) {
			Integer data = it.next();
			getSequence(data);
			index++;
			if (index == input.size() + 1) {
				getSequence(-1);
			}
		}

		System.out.println("Salida: " + output);
	}
	
	static LinkedList sequence = new LinkedList();
	static List<LinkedList> output = new ArrayList<LinkedList>();
	
	private static void getSequence(Integer data) {
		if (!sequence.isEmpty()) {
			if (data <= sequence.getFirst() && sequence.size() >= 2) {
				addToOutput(sequence);
			}
			else if (data <= sequence.getFirst() && sequence.size() == 1) {
				sequence.extractFront();
			}
		}
		sequence.insertFront(data);
	}
	
	private static void addToOutput(LinkedList sequence) {
		LinkedList aux = new LinkedList();
		LinkedListIterator it = sequence.iterator();
		while (it.hasNext()) {
			aux.insertFront(sequence.extractFront());
			it.next();
		}
		output.add(aux);
	}
}
