package model;

public class Edge implements Comparable {
	private int id;
	private String description;
	private Vertex destination;
	private Vertex source;
	private int weightVal;
	
	public Edge(String description, int weightVal, Vertex destination, Vertex source) {
		this.description = description;
		this.weightVal = weightVal;
		this.destination = destination;
		this.source = source;
	}

	public Edge(int id, String description, int weightVal, Vertex destination, Vertex source) {
		this.id = id;
		this.description = description;
		this.weightVal = weightVal;
		this.destination = destination;
		this.source = source;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Vertex getDestination() {
		return destination;
	}

	public void setDestination(Vertex destination) {
		this.destination = destination;
	}
	
	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex destination) {
		this.source = source;
	}

	public int getWeightVal() {
		return weightVal;
	}

	public void setWeightVal(int weightVal) {
		this.weightVal = weightVal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Edge [id=" + id + ", description=" + description + ", destination=" + destination + ", source=" + source + ", weightVal="
				+ weightVal + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + id;
		result = prime * result + weightVal;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (id != other.id)
			return false;
		if (weightVal != other.weightVal)
			return false;
		return true;
	}

	@Override
	public int compareTo(Object e) {
		int result = -1;
		Edge other = (Edge) e;
		if(this.id == other.id) {
			result = 0;
		} else {
			result = 1;
		}
		return result;
	}
	
}
