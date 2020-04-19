package tp1_entregable;

import java.util.Iterator;

public class LinkedListIterator implements Iterator<Integer> {
	private Node current;
	
	public LinkedListIterator(Node current) {
		this.current = current;
	}
	@Override
	public boolean hasNext() {
		return this.current != null;
	}

	@Override
	public Integer next() {
		int data = this.current.getData();
		this.current = this.current.getNext();
		return data;
	}
	/**
	 * @return la data sin avanzar al siguiente nodo
	 */
	public Integer get() {
		return this.current.getData();
	}

}
