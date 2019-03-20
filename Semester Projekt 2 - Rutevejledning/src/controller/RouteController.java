package controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import database.RouteDB;
import database.RouteDBIF;
import model.Edge;
import model.Order;
import model.Route;
import model.Vertex;
import model.Dijkstra;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public class RouteController {
	private BjergbyGraphController bgCtr;
	private OrderController oCtr;
	private RouteDBIF rDb;
	private List<Order> sortedOrders;

	/**
	 * Class constructor, intializes needed controllers and DB class
	 * 
	 * @throws SQLException
	 */
	public RouteController() throws SQLException {
		bgCtr = new BjergbyGraphController();
		oCtr = new OrderController();
		rDb = new RouteDB();
	}

	/**
	 * This method calls the findAllEdge() method in the DB class to find all edges.
	 * 
	 * @return list a list of edges
	 * @throws SQLException
	 */
	public List<Edge> findAllEdge() throws SQLException {
		return rDb.findAllEdges();
	}

	/**
	 * This method takes the supplied id and calls the findEdgeById() method in
	 * the DB class. Then returns the desired edge.
	 * 
	 * @param id the id of the wanted edge
	 * @return an edge object
	 * @throws SQLException
	 */
	public Edge findEdgeById(int id) throws SQLException {
		return rDb.findEdgeById(id);
	}

	/**
	 * This method creates an edge via a call to createEdge() in DB class,
	 * using the supplied parameters.
	 * 
	 * @param description the description of what the edge connects
	 * @param sourceId the id of the source vertex
	 * @param destId the id of the destination vertex
	 * @param weight the desired wight of the class
	 * @return edge returns the created edge
	 * @throws SQLException
	 */
	public Edge createEdge(String description, int sourceId, int destId, int weight) throws SQLException {
		Vertex source = findVertById(sourceId);
		Vertex destination = findVertById(destId);
		Edge newEdge = new Edge(description, weight, destination, source);
		return rDb.createEdge(newEdge);
	}

	/**
	 * This method updates an edge via a call to updateEdge(),
	 * using the supplied paramters.
	 * 
	 * @param id the id of the edge to update
	 * @param description the updated or original description
	 * @param weightVal the updated or original weight
	 * @param destId the updated or original destination vertex id
	 * @param sourceId the updated or original source vertex id
	 * @throws SQLException
	 */
	public void updateEdge(int id, String description, int weightVal, int destId, int sourceId) throws SQLException {
		Vertex source = findVertById(sourceId);
		Vertex destination = findVertById(destId);
		Edge edge = new Edge(id, description, weightVal, destination, source);
		rDb.updateEdge(edge);
	}

	/**
	 * This method deletes an edge with the supplied id.
	 * 
	 * @param id the id of the edge to delete
	 * @throws SQLException
	 */
	public void deleteEdge(int id) throws SQLException {
		Edge edge = rDb.findEdgeById(id);
		rDb.deleteEdge(edge);
	}

	/**
	 * This method calls the findAllVertices() method in the DB class to find all vertices. 
	 * 
	 * @return list a list of all vertices
	 * @throws SQLException
	 */
	public List<Vertex> findAllVertices() throws SQLException {
		return rDb.findAllVertices();
	}

	/**
	 * This method takes the supplied id and calls the findVertById() method in
	 * the DB class. Then returns the desired vertex.
	 * 
	 * @param id the id of the wanted edge
	 * @return an vertex object
	 * @throws SQLException
	 */
	public Vertex findVertById(int id) throws SQLException {
		return rDb.findVertById(id);
	}

	/**
	 * This method creates a vertex via a call to createVertex() in DB class,
	 * using the supplied name.
	 * 
	 * @param name the desired name of the new vertex
	 * @return vertex the newly created vertex
	 * @throws SQLException
	 */
	public Vertex createVertex(String name) throws SQLException {
		Vertex vert = new Vertex(name);
		return rDb.createVert(vert);
	}

	/**
	 * This method deletes a call via a call to the deleteVertex() in DB class,
	 * using the supplied id.
	 * 
	 * @param id the id of the vertex to delete
	 * @throws SQLException
	 */
	public void deleteVertex(int id) throws SQLException {
		Vertex vert = rDb.findVertById(id);
		rDb.deleteVert(vert);
	}

	/**
	 * This method takes the supplied id and calls the findRoutById() method in
	 * the DB class. Then returns the desired route.
	 * 
	 * @param id the id of the wanted edge
	 * @return a route object
	 * @throws SQLException
	 */
	public Route findRoutById(int id) throws SQLException {
		return rDb.findRoutById(id);
	}

	/**
	 * This method takes the supplied route object and calls the findRoutById()
	 * method in the DB class. The object is then persisted in storage.
	 * 
	 * @param route the route object to be persisted
	 * @throws SQLException
	 */
	public void createRoute(Route route) throws SQLException {
		rDb.createRoute(route);
	}

	/**
	 * This method deletes a route via a call to deleteRoute() in DB class.
	 * 
	 * @param route the route to delete
	 * @throws SQLException
	 */
	public void deleteRoute(Route route) throws SQLException {
		rDb.deleteRoute(route);
	}

	/**
	 * This method returns a route object build from the ready orders in the
	 * system. This route is to be used for delivery.
	 * 
	 * @return a route object
	 * @throws SQLException
	 */
	public Route getRoute() throws SQLException {
		sortedOrders = sortReadyOrders();
		List<Vertex> verts = bgCtr.getVertList();
		List<Vertex> vertsForRoute = new LinkedList<>();
		List<Vertex> routeList = new LinkedList<>();

		Vertex hansGrillBar = bgCtr.getVertList().get(0);

		vertsForRoute.add(hansGrillBar);

		for (int i = 0; i < sortedOrders.size(); i++) {
			String address = sortedOrders.get(i).getPers().getAddress();
			for (int j = 0; j < verts.size(); j++) {
				if (verts.get(j).getName().equalsIgnoreCase(address)) {
					vertsForRoute.add(verts.get(j));
				}
			}
		}

		for (int i = 0; i < vertsForRoute.size(); i++) {
			int count = i + 1;
			List<Vertex> tempRouteList = new LinkedList<>();
			if (!(count >= vertsForRoute.size())) {
				Vertex source = vertsForRoute.get(i);
				Vertex target = vertsForRoute.get(count);
				tempRouteList = generateTempRouteList(source, target);
				if (!source.equals(target)) {
					for (int j = 0; j < tempRouteList.size(); j++) {
						if (!source.equals(tempRouteList.get(j))) {
							if (routeList.isEmpty()) {
								routeList.add(tempRouteList.get(j));
							} else {
								routeList.add(tempRouteList.get(j));
							}
						}
					}
				}
			}
		}

		Route route = new Route(routeList);
		createRoute(route);
		return route;
	}

	/**
	 * This method gets orders ready for delivery from persisted storage.
	 * 
	 * @return a list of ready orders
	 * @throws SQLException
	 */
	public List<Order> getReadyOrders() throws SQLException {
		List<Order> orders = oCtr.findAll();
		List<Order> readyOrders = new LinkedList<Order>();

		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getToDeliver() && orders.get(i).getIsReady() && !orders.get(i).getIsDelivered()) {
				readyOrders.add(orders.get(i));
			}
		}
		return readyOrders;
	}

	public void markOrderDelivered(String vertName) {
		for (int i = 0; i < sortedOrders.size(); i++) {
			if (sortedOrders.get(i).getPers().getAddress().equalsIgnoreCase(vertName)) {
				Order order = sortedOrders.get(i);
				order.setIsDelivered(true);
				try {
					oCtr.updateOrder(order);
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * This method is used to generate a list of vertices which is the shortest
	 * route between the supplied source and target vertex. This list is used as
	 * temp list to full the list with the full route, in the route object.
	 * 
	 * @param source the source vertex of the route nedded
	 * @param target the target vertex of the route needed
	 * @return a list of vertices
	 * @throws SQLException
	 */
	private List<Vertex> generateTempRouteList(Vertex source, Vertex target) throws SQLException {

		List<Vertex> verts = bgCtr.getVertList();
		List<LinkedList<Edge>> adjas = bgCtr.getAdjaList();
		List<Vertex> routeList;

		Dijkstra dijkstra = new Dijkstra(verts, adjas);

		dijkstra.setupRoutePlanning(source);

		routeList = dijkstra.getPath(target);

		return routeList;
	}

	/**
	 * This method is used to get and sort ready orders. The list returned has
	 * the orders sorted after food type, fast food first.
	 * 
	 * @return a sorted list of orders
	 * @throws SQLException
	 */
	private List<Order> sortReadyOrders() throws SQLException {
		List<Order> nonFastFoodOrders = new LinkedList<Order>();
		List<Order> fastFoodOrders = new LinkedList<Order>();
		List<Order> sortedOrders = new LinkedList<Order>();

		for (int i = 0; i < getReadyOrders().size(); i++) {
			for (int j = 0; j < getReadyOrders().get(i).getOrderL().size(); j++) {
				if (getReadyOrders().get(i).getOrderL().get(j).getFood().getType().equalsIgnoreCase("fast food")) {
					fastFoodOrders.add(getReadyOrders().get(i));
				}

				else {
					nonFastFoodOrders.add(getReadyOrders().get(i));
				}
			}
		}

		sortedOrders.addAll(fastFoodOrders);
		sortedOrders.addAll(nonFastFoodOrders);
		return sortedOrders;
	}
}
