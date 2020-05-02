package tp2_entregable;

public class Node {
	private Integer data;
	private Node left;
	private Node right;
	
	public Node(Integer data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
	
	public Integer getData() {
		return this.data;
	}
	public Node getLeft() {
		return this.left;
	}
	public Node getRight() {
		return this.right;
	}
	public void setData(Integer data) {
		this.data = data;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	
	public boolean isLeaf() {
		return this.left == null && this.right == null;
	}
	
	@Override
	public String toString() {
		return this.data.toString();
	}
}
