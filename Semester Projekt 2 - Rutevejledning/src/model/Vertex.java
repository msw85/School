package model;

public class Vertex implements Comparable {
	private int id;
	private String name;
	private boolean visited;

	public Vertex(String name) {
		this.name = name;
	}
	
	public Vertex(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public Vertex(int id, String name, Boolean visited) {
		this.id = id;
		this.name = name;
		this.visited = visited;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Boolean getVisited() {
		return visited;
	}

	public void setVisited(Boolean visited) {
		this.visited = visited;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (visited ? 1231 : 1237);
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
		Vertex other = (Vertex) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (visited != other.visited)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vertex [id=" + id + ", name=" + name + ", visited=" + visited + "]";
	}

	@Override
	public int compareTo(Object o) {
		int result = -1;
		Vertex other = (Vertex) o;
		if(this.id == other.id) {
			result = 0;
		} else {
			result = 1;
		}
		return result;
	}
	
}
