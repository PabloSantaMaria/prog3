package entregable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DirectedGraph<T> implements Graph<T> {

	private Map<Integer, List<Edge<T>>> vertices;

	public DirectedGraph() {
		this.vertices = new HashMap<>();
	}
	
	/**
	 * Complejidad: Al utilizar una estructura propia de Java, la complejidad depende
	 * de la implementación de HashMap.
	 * En general la complejidad de put() y get() del HashMap es O(1) y depende de la
	 * implementación del hashcode
	 */
	@Override
	public void addVertex(Integer vertexId) {
		this.vertices.putIfAbsent(vertexId, new LinkedList<>());
	}
	
	/**
	 * Complejidad: O(V+A)
	 * Recorro todos los vértices (V) y los arcos (A) de ese vértice para encontrar las referencias
	 * al vértice a borrar
	 */
	@Override
	public void deleteVertex(Integer vertexId) {
		if (this.containsVertex(vertexId)) {
			
			Iterator<Map.Entry<Integer, List<Edge<T>>>> verticesIt = this.iterator();
	          
	        while(verticesIt.hasNext()) {
	             List<Edge<T>> edges = verticesIt.next().getValue(); 
	             for (Edge<T> edge : edges) {
	            	 if (vertexId.equals(edge.getEndVertex()))
	            		 edges.remove(edge);
	             }
	        }
			
			this.vertices.remove(vertexId);
		}
	}

	/**
	 * Complejidades: todas operaciones constantes para HashMap
	 * O(1) para containsKey(), put(), get()
	 * O(1) para add() de LinkedList porque es una lista nueva
	 */
	@Override
	public void addEdge(Integer startVertexId, Integer endVertexId, T label) {
		Edge<T> newEdge = new Edge<>(endVertexId, label);

		if (!this.containsVertex(startVertexId)) {
			List<Edge<T>> edges = new LinkedList<>();
			edges.add(newEdge);
			this.vertices.put(startVertexId, edges);
		}
		else {
			this.vertices.get(startVertexId).add(0, newEdge);
		}

		this.addVertex(endVertexId);
	}

	/**
	 * Complejidad: O(A)
	 * Recorro los arcos de un vértice para encontrar un arco
	 */
	@Override
	public void deleteEdge(Integer startVertexId, Integer endVertexId) {
		if (this.containsVertex(startVertexId)) {
			List<Edge<T>> edges = this.vertices.get(startVertexId);
			for (Edge<T> edge : edges) {
				if (edge.getEndVertex().equals(endVertexId))
					edges.remove(edge);
			}
		}
	}

	/**
	 * Complejidad: generalmente el containsKey() de HashMap es O(1)
	 */
	@Override
	public boolean containsVertex(Integer vertexId) {
		return this.vertices.containsKey(vertexId);
	}
	
	/**
	 * Complejidad: O(A)
	 * Recorro los arcos de un vértice para encontrar un arco
	 */
	@Override
	public boolean hasEdge(Integer startVertexId, Integer endVertexId) {
		if (this.containsVertex(startVertexId)) {
			
			Iterator<Edge<T>> edgesIt = this.getEdges(startVertexId);
			while (edgesIt.hasNext()) {
				Edge<T> edge = edgesIt.next();
				if (edge.getEndVertex().equals(endVertexId))
					return true;
			}
		}
		return false;
	}

	/**
	 * Complejidad: O(A)
	 * Recorro los arcos de un vértice para encontrar un arco
	 */
	@Override
	public Edge<T> getEdge(Integer startVertexId, Integer endVertexId) {
		if (this.containsVertex(startVertexId)) {
			
			Iterator<Edge<T>> edgesIt = this.getEdges(startVertexId);
			while (edgesIt.hasNext()) {
				Edge<T> edge = edgesIt.next();
				if (edge.getEndVertex().equals(endVertexId))
					return edge;
			}
		}
		return null;
	}
	
	/**
	 * Complejidad: O(1)
	 */
	@Override
	public int totalVertices() {
		return this.vertices.size();
	}
	
	/**
	 * Complejidad: O(V)
	 * Recorro todos los vértices para saber cuántos arcos tiene cada uno
	 */
	@Override
	public int totalEdges() {
		int total = 0;
		for (Map.Entry<Integer, List<Edge<T>>> vertex : this.vertices.entrySet())
			total += vertex.getValue().size();

		return total;
	}
	
	/**
	 * Complejidad: O(1)
	 */
	@Override
	public Iterator<Integer> getVertices() {
		return this.vertices.keySet().iterator();
	}
	
	/**
	 * Complejidad: O(A)
	 * Recorro todos los arcos de un vértice 
	 */
	@Override
	public Iterator<Integer> getAdjacents(Integer vertexId) {
		if (!this.containsVertex(vertexId))
			return Collections.emptyIterator();
		
		List<Integer> adjacents = new LinkedList<>();
		Iterator<Edge<T>> edgesIt = this.getEdges(vertexId);
		
		while (edgesIt.hasNext()) {
			Integer adjacent = edgesIt.next().getEndVertex();
			adjacents.add(adjacent);
		}
		return adjacents.iterator();
	}
	
	/**
	 * Complejidad: O(A)
	 * Recorro todos los arcos para agregarlos a una estructura auxiliar
	 */
	@Override
	public Iterator<Edge<T>> getEdges() {
		List<Edge<T>> edges = new LinkedList<>();
		Iterator<List<Edge<T>>> edgesIt = this.vertices.values().iterator();
		
		while (edgesIt.hasNext())
			edges.addAll(edgesIt.next());
		
		return edges.iterator();
	}
	
	/**
	 * Complejidad: O(1)
	 * get() de HashMap
	 */
	@Override
	public Iterator<Edge<T>> getEdges(Integer vertexId) {
		if (!this.containsVertex(vertexId))
			return Collections.emptyIterator();
		List<Edge<T>> edges = this.vertices.get(vertexId);
		return edges.iterator();
	}
	
	private Iterator<Map.Entry<Integer, List<Edge<T>>>> iterator() {
		return this.vertices.entrySet().iterator();
	}
	
	@Override
	public String toString() {
		String graph = "";
		for (Map.Entry<Integer, List<Edge<T>>> verticesEntry : this.vertices.entrySet()) {
			List<Edge<T>> edges = verticesEntry.getValue();
			for (Edge<T> edge : edges) {
				Integer vertex = verticesEntry.getKey();
				graph += vertex + " -> " + edge.getEndVertex() + "[label=\"" + edge.getLabel() + "\"];\n";
			}
		  }
		return graph;
	}
}
