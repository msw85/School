package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BjergbyGraph implements BjergbyGraphIF {
	private List<Vertex> vertList;
	private List<LinkedList<Edge>> adjaList;
	private int vertCount;
	private int noOfEdges;
	
	public BjergbyGraph(int vertCount, List<Vertex> vertList, List<LinkedList<Edge>> adjaList) {
		this.vertList = vertList;
		this.adjaList = adjaList;
		initGraph(vertCount);
	}

	private void initGraph(int vertCount) {
		//Set up graph
		this.vertCount = 0;
		this.noOfEdges = 0;
		this.vertCount = vertCount;
	}

	@Override
	public void addVertex(Vertex vertex) {
		//Add vertex to vertList and add an adjadency list to adjaList
		
		if(!vertList.contains(vertex)) {
			vertList.add(vertex);
			adjaList.add(new LinkedList<Edge>());
			vertCount++;
		}
	}

	@Override
	public void addEdge(Vertex vert, Edge edge) {
		//Add an edge to reprecent and adjadency to the specified vertex.
		//If the vertex is not in the vertList,
		//do this first and set up adjadency list in adjaList.

		int vertIndex = vertList.indexOf(vert);
		if(!vertList.contains(vert)) {
			vertList.add(vert);
			adjaList.add(new LinkedList<Edge>());
			adjaList.get(vertIndex).add(edge);
			noOfEdges++;
		} else {
			adjaList.get(vertIndex).add(edge);
			noOfEdges++;
		}
	}

	public boolean isEmpty() {
		//Check to see if the graph have been populated in any way.
		
		return vertList.isEmpty();
	}
	
	public int getVertCount() {
		//Return the number of vertices in the graph.
		
		return vertCount;
	}


	@Override
	public boolean containsVertex(Vertex vertex) {
		//Check if specified vertex is in the graph
		
		return vertList.contains(vertex);
	}

	@Override
	public boolean isAdjacent(Vertex vert1, Vertex vert2) {
		//See if the two specified vertices are adjacent.
		//First: Check if vert2 is in any of the edges in vert1's adjaList.
		//Second: Check if vert1 is in any of the edges in vert2's adjaList.
		//In both cases print to console (primarily to verify), set result and return.
		
		boolean result = false;
		int vert1Index = vertList.indexOf(vert1);
		int vert2Index = vertList.indexOf(vert2);
		
		LinkedList<Vertex> tempAdjaList1 = new LinkedList<Vertex>();
		LinkedList<Vertex> tempAdjaList2 = new LinkedList<Vertex>();

		
		for(int i = 0; i < adjaList.get(vert1Index).size(); i++) {
			tempAdjaList1.add(adjaList.get(vert1Index).get(i).getDestination());
		}
		
		for(int i = 0; i < adjaList.get(vert2Index).size(); i++) {
			tempAdjaList2.add(adjaList.get(vert2Index).get(i).getDestination());
		}

		
		if(tempAdjaList1.contains(vert2)) {
			result = true;
		}
		
		if(tempAdjaList2.contains(vert1)) {
			result = true;
		}
		
		return result;
	}


	@Override
	public List<Edge> getAdjacencies(Vertex vertex) {
		//Returns a list of objects of adjadencies (Edges) for the specified vertex.
		
		return adjaList.get(vertList.indexOf(vertex));
	}

	public List<Vertex> getVertList() {
		return vertList;
	}

	public void setVertList(List<Vertex> vertList) {
		this.vertList = vertList;
	}

	@Override
	public int getNoOfEdges() {
		//Returns the edge count of the graph.
		
		return noOfEdges;
	}

	public List<LinkedList<Edge>> getAdjaList() {
		return adjaList;
	}

	public void setAdjaList(List<LinkedList<Edge>> adjaList) {
		this.adjaList = adjaList;
	}

	@Override
	public boolean clearGraph() {
		//Clear the graph by running initGraph(),
		//check to see if successful and return boolean.
		
		boolean done = false;
		initGraph(vertCount);
		for(int i = 0; i > adjaList.size(); i++) {
			if(vertList.isEmpty() && adjaList.get(i).isEmpty()) {
				done = true;
			}
		}
		return done;
	}

	@Override
	public boolean unVisitAll() {
		//setVisited = false on all vertices in the graph.
		//If all have been unvisited return true.
		
		int countProgress = 0;
		boolean done = false;
		for(Vertex vert : vertList) {
			vert.setVisited(false);
			countProgress++;
			if(countProgress == vertList.size()) {
				done = true;
			}
		}
		return done;
	}

	@Override
	public void dfs(Vertex vertex) {
		//Traverse the graph with depth first search (recursive).
		//setVisit = true, print visited to console.
		//Loop through the adjadencies for the vertex and for
		//all non visited vertices do a recurcive call to dfs()
		//with the unvisited vertex.
		//Continue till all vertices have visited set to true.
		
		vertex.setVisited(true);
		for(Edge e : getAdjacencies(vertex)) {
			if(!e.getDestination().getVisited()) {
				dfs(e.getDestination());
			}
		}
		
	}
	
	@Override
	public List<Vertex> dfs(Vertex vertex, List<Vertex> vList) {
		//Like the dfs() above, except a list is returned when done
		//instead of printing each vertex visited.
		
		vertex.setVisited(true);
		vList.add(vertex);
		for(Edge e : getAdjacencies(vertex)) {
			if(!e.getDestination().getVisited()) {
				dfs(e.getDestination(), vList);
			}
		}
		return vList;
	}

	@Override
	public void bfs(Vertex vertex) {
		//Traverse the graph with breadth first search (iterative).
		//Make a Queue to keep track of where/what level the search is at,
		//poll from the cue to get the head of the queue as current vertex.
		//Visit all non visited adjacent vertecies to the current vertex
		//before moving on.
		//When marking a vertex as visited, add it to the cue.
		
		Queue<Vertex> vertQueue = new LinkedList<Vertex>();
		vertex.setVisited(true);
		vertQueue.add(vertex);
		while(!vertQueue.isEmpty()) {
			Vertex currentVert = vertQueue.poll();
			for(Edge e : getAdjacencies(vertex)) {
				if(!e.getDestination().getVisited()) {
					e.getDestination().setVisited(true);
					vertQueue.add(e.getDestination());
				}
			}
			
		}
		
	}

}
