package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Gruppe 6, DMAA0917
 *
 */
public class Dijkstra implements RoutePlanningIF {
	private static List<Vertex> vertices;
	private static ArrayList<LinkedList<Edge>> edges;
	private static Set<Vertex> vertsKnownDistance;
	private static Set<Vertex> vertsUnknownDistance;
	private static Map<Vertex, Vertex> predecessors;
	private static Map<Vertex, Integer> distances;

	/**
	 * Class constructor, initializes and fills vertices and edges lists from
	 * graph.
	 * 
	 * @param vertList vertices from the graph
	 * @param adjaList adjadency list from the graph
	 */
	public Dijkstra(List<Vertex> vertList, List<LinkedList<Edge>> adjaList) {
		this.vertices = new ArrayList<Vertex>(vertList);
		this.edges = new ArrayList<LinkedList<Edge>>(adjaList);
	}

	/**
	 * This method is used to set op the algorithm for the desired source
	 * vertex. Set and Maps are initialised and source vertex is added first in
	 * distances, with value 0, and added first to vertsKnownDistances.
	 * 
	 * @param source the vertex Dijkstra is meant to use as source for path
	 */
	@Override
	public void setupRoutePlanning(Vertex source) {
		vertsKnownDistance = new HashSet<Vertex>();
		vertsUnknownDistance = new HashSet<Vertex>();
		distances = new HashMap<Vertex, Integer>();
		predecessors = new HashMap<Vertex, Vertex>();
		distances.put(source, 0);
		vertsUnknownDistance.add(source);
		while (vertsUnknownDistance.size() > 0) {
			Vertex vertex = getMinimumDistanceInSet(vertsUnknownDistance);
			vertsKnownDistance.add(vertex);
			vertsUnknownDistance.remove(vertex);
			findShortestDistanceToAdja(vertex);
		}
	}

	/**
	 * This method returns the path from the source, set in setupDijkstra(), to
	 * the supplied target. Returns NULL if no path exists.
	 * 
	 * @param target the vertex a path is needed for
	 * @return a linkedlist of vertices that is the path
	 */
	@Override
	public LinkedList<Vertex> getPath(Vertex target) {
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		Vertex targetVert = target;
		// check if a path exists
		if (predecessors.get(targetVert) == null) {
			return null;
		}

		path.add(targetVert);

		while (predecessors.get(targetVert) != null) {
			targetVert = predecessors.get(targetVert);
			path.add(targetVert);
		}

		Collections.reverse(path);
		return path;
	}

	/**
	 * This method finds the shortest distance to all adjadencies for the
	 * supplied vertex.
	 * 
	 * @param vertex the vertex to calculate adjadency distance to
	 */
	private static void findShortestDistanceToAdja(Vertex vertex) {
		List<Vertex> adjaToVertex = getAdjadencies(vertex);
		for (Vertex target : adjaToVertex) {
			if (getShortestDistance(target) > getShortestDistance(vertex) + getDistance(vertex, target)) {
				distances.put(target, getShortestDistance(vertex) + getDistance(vertex, target));
				predecessors.put(target, vertex);
				vertsUnknownDistance.add(target);
			}
		}

	}

	/**
	 * This method returns the distance, the wight of the edge, between two
	 * vertices. If there is no adjadency between the vertices, -1 is returned.
	 * 
	 * @param vertex1 the first vertex
	 * @param vertex2 the second vertex
	 * @return An int value of the weight of the edge between the two vertices
	 */
	private static int getDistance(Vertex vertex1, Vertex vertex2) {
		int vertId = vertices.indexOf(vertex1);
		int distance = -1;

		for (int i = 0; i < edges.get(vertId).size(); i++) {
			if (edges.get(vertId).get(i).getDestination().equals(vertex2)) {
				distance = edges.get(vertId).get(i).getWeightVal();
			}
		}
		return distance;
	}

	/**
	 * This methods returns a list of adjadencies to the supplied vertex.
	 * 
	 * @param vertex
	 *            the vertex for which adjacdencies are wanted
	 * @return a list of vertices
	 */
	private static List<Vertex> getAdjadencies(Vertex vertex) {
		List<Vertex> adjaToVertex = new LinkedList<Vertex>();
		int vertId = vertices.indexOf(vertex);

		for (int i = 0; i < edges.get(vertId).size(); i++) {
			adjaToVertex.add(edges.get(vertId).get(i).getDestination());
		}

		return adjaToVertex;
	}

	/**
	 * This method returns the vertex in the supplied set with the least
	 * distance.
	 * 
	 * @param vertSet the set that is to be traversed
	 * @return the vertex with the least destance
	 */
	private static Vertex getMinimumDistanceInSet(Set<Vertex> vertSet) {
		Vertex minimum = null;
		for (Vertex vertex : vertSet) {
			if (minimum == null) {
				minimum = vertex;
			} else if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
				minimum = vertex;
			}
		}
		return minimum;
	}

	/**
	 * This method returns true of the supplied vertex is in the Set with known
	 * distances, returns false if not.
	 * 
	 * @param vertex the vertex that needs to be checked
	 * @return true or false
	 */
	private boolean hasDistanceCheck(Vertex vertex) {
		return vertsKnownDistance.contains(vertex);
	}

	/**
	 * This method returns the shortest distance saved in distances between the
	 * source, set in setupDijkstra(), to the supplied target. If no distance is
	 * availible, the distance returned is the largest int value.
	 * 
	 * @param target the vertex the shortest distance is needed for
	 * @return an int representing the distance from source to target
	 */
	private static int getShortestDistance(Vertex target) {
		Integer dist = distances.get(target);
		if (dist == null) {
			return Integer.MAX_VALUE;
		} else {
			return dist;
		}
	}

}
