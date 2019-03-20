package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Edge;
import model.Route;
import model.Vertex;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public class RouteDB implements RouteDBIF {

	/**
	 * Below are strings containing SQL scripts, to be used by the prepared statements regarding routes.
	 */
	private static final String FIND_ALL_ROUT_Q = "SELECT id, noOfVerts FROM routes";
	private static final String FIND_ROUT_BY_ID_Q = "SELECT id, noOfVerts FROM routes WHERE id = ?";
	private static final String CREATE_ROUT_Q = "INSERT INTO routes(noOfVerts) values(?)";
	private static final String UPDATE_ROUT_Q = "UPDATE routes SET noOfVerts = ? FROM routes WHERE id = ?";
	private static final String DELETE_ROUT_Q = "DELETE FROM routes WHERE id = ?";
	
	/**
	 * Below are strings containing SQL scripts, to be used by the prepared statements regarding edges.
	 */
	private static final String FIND_ALL_EDGE_Q = "SELECT id, description, weightVal, destinationId, sourceId FROM edges";
	private static final String FIND_EDGE_BY_ID_Q = "SELECT id, description, weightVal, destinationId, sourceId FROM edges WHERE id=?";
	private static final String CREATE_EDGE_Q = "INSERT INTO edges(description, weightVal, destinationId, sourceId) values(?, ?, (SELECT id FROM vertices WHERE id = ?), (SELECT id FROM vertices WHERE id = ?)) ";
	private static final String UPDATE_EDGE_Q = "UPDATE edges SET description = ?, weightVal = ?, destinationId = ?, sourceId = ? FROM edges WHERE id = ?";
	private static final String DELETE_EDGE_Q = "DELETE FROM edges WHERE id = ?";
	
	/**
	 * Below are strings containing SQL scripts, to be used by the prepared statements regarding vertices.
	 */
	private static final String FIND_ALL_VERT_Q = "SELECT id, name, visited FROM vertices";
	private static final String FIND_VERT_BY_ID_Q = "SELECT id, name, visited FROM vertices WHERE id=?";
	private static final String CREATE_VERT_Q = "INSERT INTO vertices(name, visited) values(?, ?)";
	private static final String UPDATE_VERT_Q = "UPDATE vertices SET name = ?, visited = ? FROM vertices WHERE id = ?";
	private static final String DELETE_VERT_Q = "DELETE FROM vertices WHERE id = ?";
	
	/**
	 * Below are strings containing SQL scripts, to be used by the prepared statements regarding routeandvertices.
	 */
	private static final String FIND_ROUTANDVERT_BY_ID_Q = "SELECT vId FROM routeAndVertices WHERE rId = ?";
	private static final String CREATE_ROUTANDVERT_Q = "INSERT INTO routeAndVertices(vId, rId) values(?, ?)";
	private static final String DELETE_ROUTANDVERT_BY_RID_Q = "DELETE FROM routeAndVertices WHERE rId = ?";

	/**
	 * Below are prepared statements regarding routes.
	 */
	private PreparedStatement findAllRout;
	private PreparedStatement findRoutById;
	private PreparedStatement createRout;
	private PreparedStatement updateRout;
	private PreparedStatement deleteRout;
	
	/**
	 * Below are prepared statements regarding edges.
	 */
	private PreparedStatement findAllEdges;
	private PreparedStatement findEdgeById;
	private PreparedStatement createEdge;
	private PreparedStatement updateEdge;
	private PreparedStatement deleteEdge;
	
	/**
	 * Below are prepared statements regarding vertices.
	 */
	private PreparedStatement findAllVertices;
	private PreparedStatement findVertById;
	private PreparedStatement createVert;
	private PreparedStatement updateVert;
	private PreparedStatement deleteVert;
	
	/**
	 * Below are prepared statements regarding routeandvertices.
	 */
	private PreparedStatement findRoutAndVertByRouteId;
	private PreparedStatement createRoutAndVert;
	private PreparedStatement deleteRoutAndVert;

	/**
	 * The constructor initializes the prepared statements compiled with the scripts.
	 * 
	 * @throws SQLException
	 */
	public RouteDB() throws SQLException {
		findAllRout = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_ROUT_Q);
		findRoutById = DBConnection.getInstance().getConnection().prepareStatement(FIND_ROUT_BY_ID_Q);
		createRout = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ROUT_Q, Statement.RETURN_GENERATED_KEYS);
		updateRout = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_ROUT_Q);
		deleteRout = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ROUT_Q);

		findAllEdges = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_EDGE_Q);
		findEdgeById = DBConnection.getInstance().getConnection().prepareStatement(FIND_EDGE_BY_ID_Q);
		createEdge = DBConnection.getInstance().getConnection().prepareStatement(CREATE_EDGE_Q, Statement.RETURN_GENERATED_KEYS);
		updateEdge = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_EDGE_Q);
		deleteEdge = DBConnection.getInstance().getConnection().prepareStatement(DELETE_EDGE_Q);

		findAllVertices = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_VERT_Q);
		findVertById = DBConnection.getInstance().getConnection().prepareStatement(FIND_VERT_BY_ID_Q);
		createVert = DBConnection.getInstance().getConnection().prepareStatement(CREATE_VERT_Q, Statement.RETURN_GENERATED_KEYS);
		updateVert = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_VERT_Q);
		deleteVert = DBConnection.getInstance().getConnection().prepareStatement(DELETE_VERT_Q);

		findRoutAndVertByRouteId = DBConnection.getInstance().getConnection().prepareStatement(FIND_ROUTANDVERT_BY_ID_Q);
		createRoutAndVert = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ROUTANDVERT_Q);
		deleteRoutAndVert = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ROUTANDVERT_BY_RID_Q);
	}


	/** 
	 * This method finds all routes in the database.
	 * 
	 * @return list a list of routes
	 */
	@Override
	public List<Route> findAllRout() throws SQLException {
		ResultSet rs;
		rs = findAllRout.executeQuery();
		List<Route> allRout = buildRoutObjects(rs);
		return allRout;			
	}

	/**
	 * This method finds a route in the database using the supplied id.
	 * 
	 * @return route the found route
	 */
	@Override
	public Route findRoutById(int id) throws SQLException {
		Route rout = null;
		ResultSet rs;
		findRoutById.setInt(1, id);
		rs = findRoutById.executeQuery();
		rs.next();
		rout = buildRoutObject(rs);
		return rout;		
	}

	/**
	 *  This method persists the data of a route in the database,
	 *  using the supplied routes information.
	 */
	@Override
	public void createRoute(Route route) throws SQLException {
		dbUtil.prepareTransaction();
		createRout.setInt(1, route.getNoOfVerts());
		int affectedRows = createRout.executeUpdate();
		dbUtil.commitTransaction(true);
		
		//Check if affectedRows == 0
		if (affectedRows == 0) {
			//An error occurred and no rows were affected
			throw new SQLException("Creating route failed, no rows affected.");
		}
		//Get result-set from generated keys
		try (ResultSet generatedKeys = createRout.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				//Set ID = the generated key
				route.setId(generatedKeys.getInt(1));
			}
			else {
				throw new SQLException("Creating route failed, no ID obtained.");
			}
		}	
		
		for(int i = 0; i < route.getVertices().size(); i++) {
			System.out.print(route.getVertices().get(i));
			createRoutAndVert(route, route.getVertices().get(i));
		}
	}
	
	/**
	 * This method updates the data of a route persisted in the database,
	 * using the supplied route
	 */
	@Override
	public void updateRoute(Route route) throws SQLException {		
		dbUtil.prepareTransaction();
		updateRout.setInt(1, route.getNoOfVerts());
		updateRout.setInt(2, route.getId());
		updateRout.executeUpdate();	
		dbUtil.commitTransaction(true);
	}

	/**
	 * This method deletes the data of a route persisted in the database,
	 * using the supplied route
	 */
	@Override
	public void deleteRoute(Route route) throws SQLException {
		deleteRoutAndVert(route);
		dbUtil.prepareTransaction();
		deleteRout.setInt(1, route.getId());
		deleteRout.executeUpdate();
		dbUtil.commitTransaction(true);
	}

	/**
	 * This method finds all edges in the database.
	 * 
	 * @return list a list of edges
	 */
	@Override
	public List<Edge> findAllEdges() throws SQLException {
		ResultSet rs;
		rs = findAllEdges.executeQuery();
		List<Edge> allEdges = buildEdgeObjects(rs);
		return allEdges;
	}

	/**
	 * This method finds an edge in the database using the supplied id.
	 * 
	 * @return route the found edge
	 */
	@Override
	public Edge findEdgeById(int id) throws SQLException {
		Edge edge = null;
		ResultSet rs;
		findEdgeById.setInt(1, id);
		rs = findEdgeById.executeQuery();
		rs.next();
		edge = buildEdgeObject(rs);
		return edge;		
	}

	/**
	 * This method persists the data of a route in the database,
	 * using the supplied routes information.
	 * 
	 * @return edge the newly created edge
	 */
	@Override
	public Edge createEdge(Edge edge) throws SQLException {
		dbUtil.prepareTransaction();
		createEdge.setString(1, edge.getDescription());
		createEdge.setInt(2, edge.getWeightVal());
		createEdge.setInt(3, edge.getDestination().getId());
		createEdge.setInt(4, edge.getSource().getId());

		int affectedRows = createEdge.executeUpdate();
		dbUtil.commitTransaction(true);
		//Check if affectedRows == 0
		if (affectedRows == 0) {
			//An error occurred and no rows were affected
			throw new SQLException("Creating edge failed, no rows affected.");
		}
		//Get result-set from generated keys
		try (ResultSet generatedKeys = createEdge.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				//Set ID = the generated key
				edge.setId(generatedKeys.getInt(1));
			}
			else {
				throw new SQLException("Creating edge failed, no ID obtained.");
			}
		}	
		return edge;

	}
	
	/**
	 * This method updates the data of an edge persisted in the database,
	 * using the supplied route.
	 */
	@Override
	public void updateEdge(Edge edge) throws SQLException {	
		dbUtil.prepareTransaction();
		updateEdge.setString(1, edge.getDescription());
		updateEdge.setInt(2, edge.getWeightVal());
		updateEdge.setInt(3, edge.getDestination().getId());
		updateEdge.setInt(4, edge.getSource().getId());
		updateEdge.setInt(5, edge.getId());
		updateEdge.executeUpdate();		
		dbUtil.commitTransaction(true);
	}

	/**
	 * This method deletes the data of an edge persisted in the database,
	 * using the supplied route
	 */
	@Override
	public void deleteEdge(Edge e) throws SQLException {
		dbUtil.prepareTransaction();
		deleteEdge.setInt(1, e.getId());
		deleteEdge.executeUpdate();
		dbUtil.commitTransaction(true);
	}

	/**
	 * This method finds all vertices in the database.
	 * 
	 * @return list a list of vertices
	 */
	@Override
	public List<Vertex> findAllVertices() throws SQLException {
		ResultSet rs;
		rs = findAllVertices.executeQuery();
		List<Vertex> allVert = buildVertObjects(rs);
		return allVert;
	}

	/**
	 * This method finds a vertex in the database using the supplied id.
	 * 
	 * @return vertex the found vertex
	 */
	@Override
	public Vertex findVertById(int id) throws SQLException {
		Vertex vert;
		ResultSet rs;
		findVertById.setInt(1, id);
		rs = findVertById.executeQuery();
		rs.next();
		vert = buildVertObject(rs);
		return vert;
	}

	/**
	 * This method persists the data of a vertex in the database,
	 * using the supplied vertex information.
	 * 
	 * @return Vertex the newly created vertex
	 */
	@Override
	public Vertex createVert(Vertex vert) throws SQLException {
		dbUtil.prepareTransaction();
		createVert.setString(1, vert.getName());
		createVert.setBoolean(2, false);

		int affectedRows = createVert.executeUpdate();
		dbUtil.commitTransaction(true);
		//Check if affectedRows == 0
		if (affectedRows == 0) {
			//An error occurred and no rows were affected
			throw new SQLException("Creating vertex failed, no rows affected.");
		}
		//Get result-set from generated keys
		try (ResultSet generatedKeys = createVert.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				//Set ID = the generated key
				vert.setId(generatedKeys.getInt(1));
			}
			else {
				throw new SQLException("Creating vertex failed, no ID obtained.");
			}
		}	
		return vert;
	}
	
	/**
	 * This method updates the data of an vertex persisted in the database,
	 * using the supplied vertex.
	 */
	@Override
	public void updateVert(Vertex v) throws SQLException {	
		dbUtil.prepareTransaction();
		updateVert.setString(1, v.getName());
		updateVert.setBoolean(2, v.getVisited());
		updateVert.executeUpdate();	
		dbUtil.commitTransaction(true);
	}

	/**
	 * This method deletes the data of a vertex persisted in the database,
	 * using the supplied vertex
	 */
	@Override
	public void deleteVert(Vertex v) throws SQLException {
		dbUtil.prepareTransaction();
		deleteVert.setInt(1, v.getId());
		deleteVert.executeUpdate();
		dbUtil.commitTransaction(true);
	}

	/**
	 * This method finds all routeandvertices in the database using the supplied id.
	 * 
	 * @return vertex the found vertex
	 */
	public List<Vertex> findRoutAndVertByRouteId(int id) throws SQLException {
		ResultSet rs;
		findRoutAndVertByRouteId.setInt(1, id);
		rs = findRoutAndVertByRouteId.executeQuery();
		rs.next();
		List<Vertex> verts = buildVertObjects(rs);
		return verts;		
	}

	/**
	 * This method persists the data of the realtionship between a route and its vertices in the database,
	 * using the supplied route and vertex information.
	 * 
	 */
	public void createRoutAndVert(Route route, Vertex vert) throws SQLException {
		dbUtil.prepareTransaction();
		createRoutAndVert.setInt(1, vert.getId());
		createRoutAndVert.setInt(2, route.getId());
		createRoutAndVert.execute();
		dbUtil.commitTransaction(true);
	}

	/**
	 * This method deletes the data of the realtionship between a route and its vertices in the database,
	 * using the supplied route
	 */
	public void deleteRoutAndVert(Route route) throws SQLException {
		dbUtil.prepareTransaction();
		deleteRoutAndVert.setInt(1, route.getId());
		deleteRoutAndVert.executeUpdate();
		dbUtil.commitTransaction(true);
	}

	/**
	 * This methods makes a list of route objects from the supplied resultset.
	 * 
	 * @param rs resultset recieved from a method running a prepared statement
	 * @return list a list of routes
	 * @throws SQLException
	 */
	private List<Route> buildRoutObjects(ResultSet rs) throws SQLException {
		List<Route> result = new ArrayList<>();
		while(rs.next()) {
			result.add(buildRoutObject(rs));
		}
		return result;
	}

	/**
	 * This method builds a route object from the supplied resultset.
	 * 
	 * @param rs a resultset recieved from the buildRoutObjects() method
	 * @return route the build route object
	 * @throws SQLException
	 */
	private Route buildRoutObject(ResultSet rs) throws SQLException {
		Route route = new Route(rs.getInt("id"), findRoutAndVertByRouteId(rs.getInt("rId")));
		return route;
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
		Vertex destination = findVertById(rs.getInt("destinationId"));
		Vertex source = findVertById(rs.getInt("sourceId"));
		Edge edge = new Edge(rs.getInt("id"), rs.getString("Description"), rs.getInt("weightVal"), destination, source);
		return edge;
	}

}
