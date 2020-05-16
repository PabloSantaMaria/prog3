package entregable;
/*
 * La clase arco representa un arco del grafo. Contiene un vertice destino y una etiqueta.
 * Nota: Para poder exponer los arcos fuera del grafo y que nadie los modifique se hizo esta clase inmutable
 * (Inmutable: una vez creado el arco no es posible cambiarle los valores).
 */
public class Edge<T> {
	private Integer endVertex;
	private T label;

	public Edge(Integer endVertex, T label) {
		this.endVertex = endVertex;
		this.label = label;
	}

	public Integer getEndVertex() {
		return this.endVertex;
	}

	public T getLabel() {
		return this.label;
	}
	
	@Override
	public String toString() {
		return "->" + this.endVertex + " (" + this.label + ")";
	}
}
