package entregable;

public class Main {
	public static void main(String[] args) {
		
		TaskManager tm = new TaskManager();
		
		//se modela el ejemplo específico del ejercicio solicitado
		//las tareas se crean con (id, nombre, duración)
		tm.addTask(0, "Tarea 0", 0);
		tm.addTask(1, "Tarea 1", 4);
		tm.addTask(2, "Tarea 2", 18);
		tm.addTask(3, "Tarea 3", 4);
		tm.addTask(4, "Tarea 4", 13);
		tm.addTask(5, "Tarea 5", 22);
		tm.addTask(6, "Tarea 6", 18);
		tm.addTask(7, "Tarea 7", 12);
		tm.addTask(8, "Tarea 8", 3);
		tm.addTask(9, "Tarea 9", 2);
		tm.addTask(10, "Tarea 10", 3);
		tm.addTask(11, "Tarea 11", 1);
		tm.addTask(12, "Tarea 12", 5);
		
		//los arcos se crean con (id1, id2, tiempo de espera)
		tm.addPath(0, 1, 0);
		tm.addPath(0, 2, 0);
		tm.addPath(1, 3, 3);
		tm.addPath(2, 5, 1);
		tm.addPath(2, 7, 18);
		tm.addPath(3, 4, 5);
		tm.addPath(3, 6, 8);
		tm.addPath(4, 11, 3);
		tm.addPath(5, 6, 2);
		tm.addPath(6, 12, 2);
		tm.addPath(6, 10, 6);
		tm.addPath(7, 8, 7);
		tm.addPath(8, 9, 4);
		tm.addPath(9, 10, 1);
		tm.addPath(11, 12, 9);
		
		System.out.println("La secuencia de ejecución crítica (partiendo desde la Tarea 0) es:");
		System.out.println(tm.criticalPath(0));
		System.out.println();
		System.out.println("Impresión del grafo para representarlo en Graphviz Online (https://dreampuf.github.io/GraphvizOnline)");
		System.out.println(tm);
	}
}
