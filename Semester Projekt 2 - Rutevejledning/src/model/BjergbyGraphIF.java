package model;
import java.util.List;

public interface BjergbyGraphIF {
	void addVertex(Vertex vertex);
	void addEdge(Vertex vert, Edge edge);
	void dfs(Vertex vertex);
	void bfs(Vertex vertex);
	boolean isEmpty();
	boolean clearGraph();
	boolean unVisitAll();
	boolean containsVertex(Vertex vertex);
	boolean isAdjacent(Vertex v1, Vertex v2);
	int getVertCount();
	int getNoOfEdges();
	List<Edge> getAdjacencies(Vertex vertex);
	List<Vertex> dfs(Vertex vertex, List<Vertex> vList);
}
 