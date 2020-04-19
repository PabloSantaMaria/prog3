package tp1_entregable;

public class Node {
	private Integer data;
	private Node next;
	
	public Node(int data) {
		this.data = data;
		this.next = null;
	}
	public Node(int data, Node next) {
		this.data = data;
		this.next = next;
	}
	
	public int getData() {
		return this.data;
	}
	public Node getNext() {
		return this.next;
				
	}
	public void setData(int data) {
		this.data = data;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
	public String toString() {
		return this.data.toString();
	}
}
