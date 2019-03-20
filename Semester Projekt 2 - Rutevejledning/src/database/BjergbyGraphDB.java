package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Edge;
import model.Vertex;

public class BjergbyGraphDB implements BjergbyGraphIF{
	
	/**
	 * Below are strings containing SQL scripts, to be used by the prepared statements regarding graph.
	 */
	private static final String FIND_ALL_VERT_Q = "SELECT id, name, visited FROM vertices";
	private static final String FIND_VERT_ON_ID_Q = "SELECT id, name, visited FROM vertices WHERE id = ?";
	private static final String FIND_ALL_EDGE_Q = "SELECT id, description, weightVal, destinationId, sourceId FROM edges";
	
	/**
	 * Below are prepared statements regarding graph.
	 */
	private PreparedStatement findAllVertices;
	private PreparedStatement findVertOnId;
	private PreparedStatement findAllEdges;
	
	/**
	 * The constructor initializes the prepared statements compiled with the scripts.
	 * 
	 * @throws SQLException
	 */
	public BjergbyGraphDB() throws SQLException {
		findAllVertices = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_VERT_Q);
		findVertOnId = DBConnection.getInstance().getConnection().prepareStatement(FIND_VERT_ON_ID_Q);
		findAllEdges = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_EDGE_Q);
	}

	/**
	 * This method finds all the persisted vertices in the database,
	 * to be used to set up the graph.
	 * 
	 * @return list a list of vertices
	 * @throws SQLException
	 */
	@Override
	public List<Vertex> findAllVertices() throws SQLException {
		ResultSet rs;
		rs = findAllVertices.executeQuery();
		List<Vertex> allVert = buildVertObjects(rs);
		return allVert;
	}
	
	/**
	 * This method finds a specified vertex by the supplied id.
	 * 
	 * @param id the id of the vertex to be found
	 * @return vertex the found vertex
	 * @throws SQLException
	 */
	@Override
	public Vertex findVertOnId(int id) throws SQLException {
		Vertex vert;
		ResultSet rs;
		findVertOnId.setInt(1, id);
		rs = findVertOnId.executeQuery();
		rs.next();
		vert = buildVertObject(rs);
		return vert;
	}

	/**
	 * This method finds all the persisted edges in the database
	 * 
	 * @return list a list of edges
	 * @throws SQLException
	 */
	@Override
	public List<Edge> findAllEdges() throws SQLException {
		ResultSet rs;
		rs = findAllEdges.executeQuery();
		List<Edge> allEdges = buildEdgeObjects(rs);
		return allEdges;
	}
	
	/**
	 * This methods makes a list of vertex objects from the supplied resultset.
	 * 
	 * @param rs resultset recieved from a method running a prepared statement
	 * @return list a list of vertices
	 * @throws SQLException
	 */
	private List<Vertex> buildVertObjects(ResultSet rs) throws SQLException {
		List<Vertex> vertices = new LinkedList<>();
		while(rs.next()) {
			vertices.add((buildVertObject(rs)));
		}
		return vertices;
	}
	
	/**
	 * This method builds a vertex object from the supplied resultset.
	 * 
	 * @param rs a resultset recieved from the buildVertObjects() method
	 * @return vertex the build vertex object
	 * @throws SQLException
	 */
	private Vertex buildVertObject(ResultSet rs) throws SQLException {
		Vertex vert = new Vertex(rs.getInt("id"), rs.getString("name"), rs.getBoolean("visited"));
		return vert;
	}
	
	/**
	 * This methods makes a list of edge objects from the supplied resultset.
	 * 
	 * @param rs resultset recieved from a method running a prepared statement
	 * @return list a list of edges
	 * @throws SQLException
	 */
	private List<Edge> buildEdgeObjects(ResultSet rs) throws SQLException {
		List<Edge> edges = new LinkedList<>();
		while(rs.next()) {
			edges.add((buildEdgeObject(rs)));
		}
		return edges;
	}

	/**
	 * This method builds an edge object from the supplied resultset.
	 * 
	 * @param rs a resultset recieved from the buildEdgeObjects() method
	 * @return edge the build edge object
	 * @throws SQLException
	 */
	private Edge buildEdgeObject(ResultSet rs) throws SQLException {
		Vertex destination = findVertOnId(rs.getInt("destinationId"));
		Vertex source = findVertOnId(rs.getInt("sourceId"));
		Edge edge = new Edge(rs.getInt("id"), rs.getString("Description"), rs.getInt("weightVal"), destination, source);
		return edge;
	}

}
