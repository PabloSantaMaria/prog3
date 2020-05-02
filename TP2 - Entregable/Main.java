package tp2_entregable;

public class Main {

	public static void main(String[] args) {
		//Genera un árbol al azar de 15 nodos con valores no repetidos entre 1 y 40
		BinarySearchTree randomBST = new BinarySearchTree(15, 1, 40);
		System.out.println("Arbol aleatorio: ");
		printTree(randomBST);
		
		//Construyo un arbol de ejemplo a partir de un arreglo (inserta de a uno los valores)
		Integer[] array = {54, 28, 64, 14, 31, 61, 69, 13, 16, 36, 37, 59, 62};
		BinarySearchTree sampleBST = new BinarySearchTree(array);
		
		//Verificacion de funcionamiento de cada metodo tomando el arbol de ejemplo
		System.out.println("Arbol de ejemplo: ");
		printTree(sampleBST);
		
		//Insertar
		sampleBST.insert(65);
		System.out.println("Inserto el 65: ");
		printTree(sampleBST);
		
		//Buscar
		System.out.println("Contiene el elemento 36: " + sampleBST.hasElem(36));
		System.out.println("Contiene el elemento 99: " + sampleBST.hasElem(99));
		
		//Frontera
		System.out.println("Frontera: ");
		System.out.println(sampleBST.getFrontera());
		
		//Listar un nivel
		System.out.println("Elementos del nivel 2: ");
		System.out.println(sampleBST.getElemAtLevel(2));
		
		//Elemento mas grande del arbol
		System.out.println("Maximo: " + sampleBST.getMaxElem());
				
		//Elemento mas chico del arbol
		System.out.println("Minimo: " + sampleBST.getMinElem());
		
		//Obtener la altura
		System.out.println("Altura del arbol: " + sampleBST.getHeight());
		
		//Listar la rama mas larga
		System.out.println("Elementos de la rama mas larga: ");
		System.out.println(sampleBST.getLongestBranch());
		
		//Borrado
		System.out.println("Borrar 37");
		sampleBST.delete(37); //hoja
		printTree(sampleBST);
		System.out.println("Borrar 69");
		sampleBST.delete(69); //nodo con 1 hijo
		printTree(sampleBST);
		System.out.println("Borrar 28");
		sampleBST.delete(28); //nodo con 2 hijos
		printTree(sampleBST);
		
	}
	
	private static void printTree(BinarySearchTree tree) {
		tree.printPreOrder();
		System.out.println();
	}

}
