package entregable;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskManager {
	private Graph<Integer> tGraph;
	private Map<Integer, Task> tasks;

	public TaskManager() {
		this.tGraph = new DirectedGraph<>();
		this.tasks = new HashMap<>();
	}

	public void addTask(Integer taskId, String name, int duration) {
		if (!this.tGraph.containsVertex(taskId)) {
			Task task = new Task(name, duration);
			this.tGraph.addVertex(taskId);
			this.tasks.put(taskId, task);
		}
	}

	public void addPath(Integer taskId1, Integer taskId2, int time) {
		if (this.tGraph.containsVertex(taskId1) && this.tGraph.containsVertex(taskId2))
			this.tGraph.addEdge(taskId1, taskId2, time);
	}

	/**
	 * Método que devuelve la secuencia de ejecución de tareas que resulta en el máximo tiempo empleado
	 * @param start: tarea por la cual se quiere comenzar la secuencia
	 * @return un ArrayList de los ids de las tareas
	 */
	public List<Integer> criticalPath(Integer start) {
		List<Integer> startPath = new ArrayList<>();
		startPath.add(start);
		boolean[] visited = new boolean[this.tasks.size()];

		return getCritPath(start, visited, startPath, new ArrayList<>());
	}

	/**
	 * Método auxiliar recursivo que busca las secuencias con mayor tiempo empleado,
	 * realizando un recorrido en profundidad (dfs) del grafo y comparando la secuencia obtenida
	 * al llegar a una tarea final (sin adyacentes)
	 * Se asume que el grafo es acíclico
	 * @param currentTask: tarea actual en el momento de la recursión
	 * @param visited: arreglo que lleva la cuenta de las tareas visitadas
	 * @param path: la secuencia actual en el momento de la recursión
	 * @param criticalPath: lista auxiliar donde se va guardando la secuencia mayor encontrada 
	 * @return ArrayList de los ids de las tareas
	 */
	private List<Integer> getCritPath(Integer currentTask, boolean[] visited, List<Integer> path, List<Integer> criticalPath) {
		
		//marco la tarea actual como visitada
		visited[currentTask] = true;
		boolean endPath = true;

		//obtengo un iterador de las tareas adyacentes a la actual
		Iterator<Integer> adjTasks = this.tGraph.getAdjacents(currentTask);
		
		//mientras tenga adyacentes sin visitar se van agregando a la lista en forma recursiva
		while (adjTasks.hasNext()) {
			Integer task = adjTasks.next();
			if (!visited[task]) {
				path.add(task);
				endPath = false;
				getCritPath(task, visited, path, criticalPath);
			}
		}
		
		//si endPath sigue true, no se entró al while, la tarea no tiene adyacentes
		//y marca el fin de una secuencia
		//comparo el tiempo total de la secuencia encontrada con la secuencia auxiliar y me quedo
		//con la mayor
		if (endPath) {
			int pathTime = getPathTime(path);
			int criticalPathTime = getPathTime(criticalPath);
			if (pathTime > criticalPathTime) {
				criticalPath.clear();
				criticalPath.addAll(path);
			}
		}
		
		//marco la tarea como no visitada y la quito de la lista para retroceder hasta la última bifurcación
		//y poder visitarla si es necesario en el próximo camino
		visited[currentTask] = false;
		path.remove(currentTask);
		
		//devuelvo la secuencia con el mayor tiempo
		return criticalPath;
	}

	/**
	 * Método que calcula el tiempo que se tarda en realizar una secuencia de tareas
	 * @param path: lista de tareas
	 * @return entero que representa las horas
	 */
	private int getPathTime(List<Integer> path) {
		int time = 0;
		int size = path.size();

		if (size > 0) {
			for (int i = 0; i < size - 1; i++) {
				int taskId = path.get(i);
				int duration = this.tasks.get(taskId).getDuration();
				int edgeTime = this.tGraph.getEdge(taskId, path.get(i + 1)).getLabel();
				time += duration + edgeTime;
			}
			time += this.tasks.get(path.get(size - 1)).getDuration();
		}

		return time;
	}

	@Override
	public String toString() {
		return this.tGraph.toString();
	}
}
