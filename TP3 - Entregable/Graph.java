package entregable;

import java.util.Iterator;

public interface Graph<T> {
	// Agrega un vertice 
	public void addVertex(Integer vertexId);

	// Borra un vertice
	public void deleteVertex(Integer vertexId);

	// Agrega un arco con una etiqueta, que conecta el verticeId1 con el verticeId2
	public void addEdge(Integer startVertexId, Integer endVertexId, T label);

	// Borra el arco que conecta el verticeId1 con el verticeId2
	public void deleteEdge(Integer startVertexId, Integer endVertexId);

	// Verifica si existe un vertice
	public boolean containsVertex(Integer vertexId);  

	// Verifica si existe un arco entre dos vertices
	public boolean hasEdge(Integer startVertexId, Integer endVertexId);

	// Obtener el arco que conecta el verticeId1 con el verticeId2
	public Edge<T> getEdge(Integer startVertexId, Integer endVertexId);

	// Devuelve la cantidad total de vertices en el grafo
	public int totalVertices();

	// Devuelve la cantidad total de arcos en el grafo
	public int totalEdges();

	// Obtiene un iterador que me permite recorrer todos los vertices almacenados en el grafo 
	public Iterator<Integer> getVertices();

	// Obtiene un iterador que me permite recorrer todos los vertices adyacentes a verticeId 
	public Iterator<Integer> getAdjacents(Integer vertexId);

	// Obtiene un iterador que me permite recorrer todos los arcos del grafo
	public Iterator<Edge<T>> getEdges();

	// Obtiene un iterador que me permite recorrer todos los arcos que parten desde verticeId
	public Iterator<Edge<T>> getEdges(Integer vertexId);
}
