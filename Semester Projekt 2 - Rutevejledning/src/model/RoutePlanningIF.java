package model;

import java.util.LinkedList;

//Interface for route planning implementations.

public interface RoutePlanningIF {
	void setupRoutePlanning(Vertex source);
	LinkedList<Vertex> getPath(Vertex target);
}
