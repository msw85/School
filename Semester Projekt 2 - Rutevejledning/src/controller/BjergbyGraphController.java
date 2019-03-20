package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import database.BjergbyGraphDB;
import database.BjergbyGraphIF;
import model.BjergbyGraph;
import model.Edge;
import model.Vertex;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public class BjergbyGraphController {
	private BjergbyGraphIF bgdb;
	private BjergbyGraph bjergby;
	
	/**
	 * Class constructor, intializes DB class and sets up the graph.
	 * 
	 * @throws SQLException
	 */
	public BjergbyGraphController() throws SQLException {
		bgdb = new BjergbyGraphDB();
		setupGraph();
	}
	
	/**
	 * This method finds all the persisted vertices in the database,
	 * to be used to set up the graph.
	 * 
	 * @return list a list of vertices
	 * @throws SQLException
	 */
	public List<Vertex> findAllVertices() throws SQLException {
		return bgdb.findAllVertices();
	}
	
	/**
	 * This method finds a specified vertex by the supplied id.
	 * 
	 * @param id the id of the vertex to be found
	 * @return vertex the found vertex
	 * @throws SQLException
	 */
	public Vertex findVertOnId(int id) throws SQLException {
		return bgdb.findVertOnId(id);
	}
	
	/**
	 * This method finds all the persisted edges in the database
	 * 
	 * @return list a list of edges
	 * @throws SQLException
	 */
	public List<Edge> findAllEdges() throws SQLException {
		return bgdb.findAllEdges();
	}
	
	/**
	 * This method returns the vertices contained in the graph.
	 * 
	 * @return list a list of vertices
	 */
	public List<Vertex> getVertList() {
		return bjergby.getVertList();
	}
	
	/**
	 * This method returns the adjadencies, in the form of edges, in the graph.
	 * 
	 * @return list a list of edges
	 */
	public List<LinkedList<Edge>> getAdjaList() {
		return bjergby.getAdjaList();
	}
	
	/**
	 * This method generates an adjadency list to be used to set up the graph.
	 * 
	 * @return a list with a linked list containing adjadencies persisted in storage
	 * @throws SQLException
	 */
	private List<LinkedList<Edge>> generateAdjadencyList() throws SQLException {
		List<Edge> edges = findAllEdges();
		List<Vertex> vertices = findAllVertices();
		List<LinkedList<Edge>> adjaList = new ArrayList<LinkedList<Edge>>();
		
		for(int i = 0; i < vertices.size(); i++) {
			Vertex tempVert = vertices.get(i);
			adjaList.add(new LinkedList<Edge>());
			for(int j = 0; j < edges.size(); j++) {
				if(edges.get(j).getSource().equals(tempVert)) {
					adjaList.get(i).add(edges.get(j));
				};
			}
		}
		return adjaList;
	}
	
	/**
	 * This method initializes and instansiates the graph.
	 * 
	 * @throws SQLException
	 */
	public void setupGraph() throws SQLException {
		bjergby = new BjergbyGraph(0, findAllVertices(), generateAdjadencyList());
	}

}
