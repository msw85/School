package database;

import java.sql.SQLException;
import java.util.List;

import model.Edge;
import model.Vertex;

/**
 * @author Gruppe 6, DMAA0917
 * 
 * Interface for RouteDB class.
 *
 */
public interface BjergbyGraphIF {
	/**
	 * Methods regarding graph implementation.
	 */
	List<Vertex> findAllVertices() throws SQLException;
	Vertex findVertOnId(int id) throws SQLException;
	List<Edge> findAllEdges() throws SQLException;
}
