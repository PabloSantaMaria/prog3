package tp1_entregable;

public class LinkedList implements Iterable<Integer> {
	protected Node first;
	protected int size;

	public LinkedList() {
		this.first = null;
		this.size = 0;
	}

	public Integer getFirst() {
		return this.first.getData();
	}
	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.first == null;
	}

	public Integer get(int index) {
		if (!this.isEmpty() && index <= this.size && index > 0) {
			Node tmp = first;
			for (int i = 1; i < index; i++) {
				tmp = tmp.getNext();
			}
			return tmp.getData();
		}
		else return -1;
	}

	public void insertFront(int data) {
		Node newNode = new Node(data);
		newNode.setNext(this.first);
		this.first = newNode;
		this.size++;
	}

	public void insertBack(int data) {
		if (isEmpty()) insertFront(data);
		else {
			Node newNode = new Node(data);
			Node tmp = this.first;
			while (tmp.getNext() != null) {
				tmp = tmp.getNext();
			}
			tmp.setNext(newNode);
			this.size++;
		}
	}

	public void reverse() {
		LinkedList aux = new LinkedList();
		Node tmp = first;

		while (tmp.getNext() != null) {
			aux.insertFront(tmp.getData());
			tmp = tmp.getNext();
		}

		tmp.setNext(aux.first);
		this.first = tmp;
	}

	public void orderedInsert(int data) {
		if (isEmpty() || this.first.getData() > data) insertFront(data);
		else {
			Node newNode = new Node(data);
			Node tmp = this.first;

			while (tmp.getNext() != null && tmp.getNext().getData() < data) {
				tmp = tmp.getNext();
			}
			newNode.setNext(tmp.getNext());
			tmp.setNext(newNode);
		}

		this.size++;
	}

	public Integer extractFront() {
		if (!isEmpty()) {
			int data = this.first.getData();
			this.first = this.first.getNext();
			this.size--;
			return data;
		}
		else return null;
	}

	public int indexOf(int data) {
		if (isEmpty()) return -1;

		Node tmp = this.first;
		int index = 1;

		while (tmp.getNext() != null) {
			if (tmp.getData() == data) {
				return index;
			} else {
				tmp = tmp.getNext();
				index++;
			}
		}

		if (tmp.getData() == data) return index;

		return -1;
	}

	public LinkedListIterator iterator() {
		return new LinkedListIterator(this.first);
	}

	@Override
	public String toString() {
		String list = "";
		if (isEmpty())
			list = "Lista vacía";
		else {
			list = "[";
			Node temp = first;
			while (temp.getNext() != null) {
				list += temp + " - ";
				temp = temp.getNext();
			}
			list += temp + "]";
		}
		return list;
	}
}
