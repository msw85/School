package database;

import java.sql.SQLException;
import java.util.List;

import model.Edge;
import model.Route;
import model.Vertex;

/**
 * @author Gruppe 6, DMAA0917
 * 
 * Interface for RouteDB class.
 *
 */
public interface RouteDBIF {
	/**
	 * The below methods handles Routes
	 */
	public List<Route> findAllRout() throws SQLException;
	public Route findRoutById(int id) throws SQLException;
	public void createRoute(Route r) throws SQLException;
	public void deleteRoute(Route r) throws SQLException;
	public void updateRoute(Route r) throws SQLException;

	/**
	 * The below methods handles Edges
	 */
	List<Edge> findAllEdges() throws SQLException;
	public Edge findEdgeById(int id) throws SQLException;
	public Edge createEdge(Edge e) throws SQLException;
	public void deleteEdge(Edge e) throws SQLException;
	public void updateEdge(Edge e) throws SQLException;

	/**
	 * The below methods handles Vertices
	 */
	List<Vertex> findAllVertices() throws SQLException;
	Vertex findVertById(int id) throws SQLException;
	public Vertex createVert(Vertex v) throws SQLException;
	public void deleteVert(Vertex v) throws SQLException;
	public void updateVert(Vertex v) throws SQLException;
	
	/**
	 * The below methods handles RoutesAndVertices
	 */
	public List<Vertex> findRoutAndVertByRouteId(int id) throws SQLException;
	public void createRoutAndVert(Route route, Vertex vert) throws SQLException;
	public void deleteRoutAndVert(Route route) throws SQLException;

}
