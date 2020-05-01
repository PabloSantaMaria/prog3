package ej6;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BinarySearchTree {
	private Node root;
	
	/**
	 * Constructor arbol vacio
	 */
	public BinarySearchTree() {
		this.root = null;
	}
	/**
	 * Contructor a partir de un arreglo
	 * @param array: conjunto de elementos para crear el arbol
	 */
	public BinarySearchTree(Integer[] array) {
		for (Integer data : array)
			insert(data);
	}
	/**
	 * Constructor de arbol aleatorio sin contener repetidos
	 * @param size: cantidad de nodos
	 * @param min: valor maximo que puede tomar un nodo
	 * @param max: valor minimo que puede tomar un nodo
	 */
	public BinarySearchTree(int size, int min, int max) {
		Random random = new Random();
		int n = 0;
		
		while (n < size) {
			Integer element = random.nextInt((max+1) - min) + min;
			boolean inserted = this.insert(element);
			if (inserted)
				n++;
		}
	}
	
	/**
	 * @return: key de la raiz
	 */
	public Integer getRoot() {
		return this.root.getData();
	}
	/**
	 * @return: boolean es arbol vacio
	 */
	public boolean isEmpty() {
		return this.root == null;
	}
	
	/**
	 * Complejidad: O(h) donde h es la altura del arbol
	 * En el peor caso debo ir hasta el nodo mas lejano de la rama mas larga
	 * @param data: key del nodo a insertar
	 * @return: boolean insertado (no es repetido)
	 */
	public boolean insert(Integer data) {
		if (isEmpty()) {
			this.root = new Node(data);
			return true;
		}
		else
			return insert(this.root, data);
	}
	private boolean insert(Node current, Integer data) {
		if (data.equals(current.getData()))
			return false;
		else {
			if (data < current.getData()) {
				if (current.getLeft() == null)
					current.setLeft(new Node(data));
				else
					return insert(current.getLeft(), data);
			}
			else
				if (current.getRight() == null)
					current.setRight(new Node(data));
				else
					return insert(current.getRight(), data);
			
			return true;
		}
	}
	
	/**
	 * Imprime todos los nodos Pre Orden
	 * Complejidad: O(n)
	 * Tengo que recorrer todos los nodos para imprimirlos
	 */
	public void printPreOrder() {
		if (!isEmpty()) printPreOrder(this.root);
	}
	private void printPreOrder(Node current) {
		if (current == null)
			System.out.print("*, ");
		else {
			System.out.print(current + ", ");
			printPreOrder(current.getLeft());
			printPreOrder(current.getRight());
		}
	}
	
	/**
	 * Imprime todos los nodos Post Orden
	 * Complejidad: O(n)
	 * Tengo que recorrer todos los nodos para imprimirlos
	 */
	public void printPostOrder() {
		if (!isEmpty()) printPostOrder(this.root);
	}
	private void printPostOrder(Node current) {
		if (current == null)
			System.out.print("*, ");
		else {
			printPostOrder(current.getLeft());
			printPostOrder(current.getRight());
			System.out.print(current + ", ");
		}
	}
	
	/**
	 * Imprime todos los nodos En Orden
	 * Complejidad: O(n)
	 * Tengo que recorrer todos los nodos para imprimirlos
	 */
	public void printInOrder() {
		if (!isEmpty()) printInOrder(this.root);
	}
	private void printInOrder(Node current) {
		if (current == null)
			System.out.print("*, ");
		else {
			printInOrder(current.getLeft());
			System.out.print(current + ", ");
			printInOrder(current.getRight());
		}
	}
	
	/**
	 * Complejidad: O(h) donde h es la altura del arbol
	 * En el peor caso voy hasta la hoja de la rama mas larga
	 * @param data: key a buscar
	 * @return: boolean existe la key en el arbol
	 */
	public boolean hasElem(Integer data) {
		return hasElem(this.root, data);
	}
	private boolean hasElem(Node current, Integer data) {
		if (current == null) 
			return false;
		else if (data.equals(current.getData()))
			return true;
		else {
			if (data < current.getData())
				return hasElem(current.getLeft(), data);
			else
				return hasElem(current.getRight(), data);
		}
	}
	
	/**
	 * Complejidad: O(h) donde h es la altura del arbol
	 * El elemento mas grande siempre va a ser el nodo que esta mas a la derecha
	 * @return: key mas grande del arbol
	 */
	public Integer getMaxElem() {
		if (isEmpty()) return -1;
		else return getMaxElem(this.root);
	}
	private Integer getMaxElem(Node current) {
		if (current.getRight() == null)
			return current.getData();
		else
			return getMaxElem(current.getRight());
	}
	
	/**
	 * Complejidad: O(h) donde h es la altura del arbol
	 * El elemento mas chico siempre va a ser el nodo que esta mas a la izquierda
	 * @return: key mas chica del arbol
	 */
	public Integer getMinElem() {
		if (isEmpty()) return -1;
		else return getMinElem(this.root);
	}
	private Integer getMinElem(Node current) {
		if (current.getLeft() == null)
			return current.getData();
		else
			return getMinElem(current.getLeft());
	}
	
	/**
	 * Complejidad: O(n) donde n es la cantidad de nodos
	 * Accedo a cada nodo para preguntarle su profundidad
	 * @return: la profundidad de la hoja mas lejana
	 */
	public int getHeight() {
		if (isEmpty())
			return -1;
		else
			return getHeight(this.root);
	}
	private int getHeight(Node current) {
		if (current == null)
			return -1;
		else 
			return Math.max(getHeight(current.getLeft()) + 1, getHeight(current.getRight()) + 1);
	}
	
	/**
	 * Complejidad: O(n) donde n es la cantidad de nodos
	 * Accedo a cada nodo para preguntarle cual es su rama mas larga
	 * @return: una lista con las key de los nodos de la rama mas larga
	 */
	public List<Integer> getLongestBranch() {
		if (isEmpty())
			return Collections.emptyList();
		else
			return getLongestBranch(this.root);
	}
	private List<Integer> getLongestBranch(Node current) {
		List<Integer> leftBranch = new LinkedList<>();
		List<Integer> rightBranch = new LinkedList<>();
				
		if (current != null) {
			leftBranch.add(current.getData());
			rightBranch.add(current.getData());
			leftBranch.addAll(getLongestBranch(current.getLeft()));
			rightBranch.addAll(getLongestBranch(current.getRight()));
		}
		
		if (leftBranch.size() > rightBranch.size())
			return leftBranch;
		else
			return rightBranch;
	}
	
	/**
	 * Complejidad: O(n) donde n es la cantidad de nodos
	 * Accedo a todos los nodos para preguntarle a cada uno si es una hoja
	 * @return: una lista con las key de todos los nodos hoja
	 */
	public List<Integer> getFrontera() {
		if (isEmpty())
			return Collections.emptyList();
		else
			return getFrontera(this.root);
	}
	private List<Integer> getFrontera(Node current) {
		List<Integer> frontera = new LinkedList<>();
		
		if (current.isLeaf())
			frontera.add(current.getData());
		else {
			if (current.getLeft() != null)
				frontera.addAll(getFrontera(current.getLeft()));
			if (current.getRight() != null)
				frontera.addAll(getFrontera(current.getRight()));
		}
		
		return frontera;
	}
	
	/**
	 * Complejidad: O(n) donde n es la cantidad de nodos
	 * En el peor caso tengo que recorrer el arbol hasta llegar a los nodos del ultimo nivel
	 * @param level: profundidad de los nodos que quiero consultar
	 * @return: una lista con las key de los nodos que estan en ese nivel
	 */
	public List<Integer> getElemAtLevel(int level) {
		if (isEmpty())
			return Collections.emptyList();
		else
			return getElemAtLevel(this.root, level);
	}
	private List<Integer> getElemAtLevel(Node current, int level) {
		List<Integer> elements = new LinkedList<>();
		
		if (current == null)
			return elements;
		if (level == 0)
			elements.add(current.getData());
		else if (level > 0) {
			elements.addAll(getElemAtLevel(current.getLeft(), level - 1));
			elements.addAll(getElemAtLevel(current.getRight(), level - 1));
		}
		
		return elements;
	}
	
	/**
	 * Complejidad: O(h) donde h es la altura del arbol
	 * Es el peor caso de hacer una busqueda
	 * @param data: key a borrar
	 * @return: boolean borrado exitoso
	 */
	public boolean delete(Integer data) {
		if (isEmpty())
			return false;
		else if (data.equals(this.getRoot())) {
			this.root = null;
			return true;
		}
		else return delete(this.root, null, data);
	}
	private boolean delete(Node current, Node parent, Integer data) {
		if (current == null)
			return false;
		
		if (data < current.getData())
			return delete(current.getLeft(), current, data);
		else if (data > current.getData())
			return delete(current.getRight(), current, data);
		else {
			// Caso 1: es una hoja
			if (current.isLeaf())
				return deleteLeaf(parent, current);
			// Caso 2: tiene 1 hijo
			else if (current.getLeft() == null || current.getRight() == null)
				return deleteOneChild(parent, current);
			// Caso 3: tiene 2 hijos
			else {
				Integer minElemRT = getMinElem(current.getRight());
				current.setData(minElemRT);
				return delete(current.getRight(), current, minElemRT);
			}
		}
	}
	private boolean deleteOneChild(Node parent, Node current) {
		if (current.getLeft() == null) {
			if (parent.getLeft() != null && parent.getLeft().equals(current))
				parent.setLeft(current.getRight());
			else
				parent.setRight(current.getRight());
		}
		
		else {
			if (parent.getLeft() != null && parent.getLeft().equals(current))
				parent.setLeft(current.getLeft());
			else
				parent.setRight(current.getLeft());
		}
		
		return true;
	}
	private boolean deleteLeaf(Node parent, Node current) {
		if (parent.getLeft() != null && parent.getLeft().equals(current))
			parent.setLeft(null);
		else
			parent.setRight(null);
		return true;
	}
}
