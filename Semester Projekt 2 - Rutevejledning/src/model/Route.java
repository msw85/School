package model;

import java.util.LinkedList;
import java.util.List;

public class Route implements Comparable {
	private int id;
	private List<Vertex> vertices;
	private int noOfVerts;

	/**
	 * This contructor is used when instantiating an object without id.
	 * 
	 * @param vertices a list of vertices in the route
	 */
	public Route(List<Vertex> vertices) {
		this.vertices = new LinkedList<Vertex>();
		this.vertices = vertices;
		noOfVerts = vertices.size() - 1;
	}

	/**
	 * This contructor is used when instantiating an object with id.
	 * 
	 * @param id the id of the route
	 * @param vertices a list of vertices in the route
	 */
	public Route(int id, List<Vertex> vertices) {
		this.vertices = new LinkedList<Vertex>();
		this.vertices = vertices;
		noOfVerts = vertices.size() - 1;
	}

	/**
	 * This method returns all vertices.
	 * 
	 * @return list the list of vertices contained in the route
	 */
	public List<Vertex> getVertices() {
		return vertices;
	}

	/**
	 * This method removes a vertex from vertices list.
	 * 
	 * @param vertex the vertex to be removed.
	 */
	public void removeVertex(Vertex vertex) {
		vertices.remove(vertex);
	}

	/**
	 * This method adds a vertex to vertices list.
	 * 
	 * @param vertex the vertex to be added
	 */
	public void addVertex(Vertex vertex) {
		vertices.add(vertex);
	}

	/**
	 * This method returns the id.
	 * 
	 * @return id the id of the route
	 */
	public int getId() {
		return id;
	}

	/**
	 * This method sets the id.
	 * 
	 * @param id the id to be set on the route
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * This method returns the number of vertices
	 * 
	 * @return noOfVerts the number of vertices in the vertices list
	 */
	public int getNoOfVerts() {
		return noOfVerts;
	}

	/**
	 * This method sets the number of vertices on the route.
	 * 
	 * @param noOfVerts the desired number to be set
	 */
	public void setNoOfVerts(int noOfVerts) {
		this.noOfVerts = noOfVerts;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Route [id=" + id + ", vertices=" + vertices + "]";
	}

}
