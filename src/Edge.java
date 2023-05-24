
public class Edge {
	private String ID;
	private Node startNode;
	private Node endNode;
	private double weight;
	
	public Edge(String ID, Node start, Node end, double weight) {
		this.ID=ID;
		this.startNode=start;
		this.endNode=end;
		this.weight=weight;
	}
	
	public Node getStart() {
		return this.startNode;
	}
	
	public void setStart(Node start) {
		this.startNode=start;
	}
	
	public Node getEnd() {
		return this.endNode;
	}
	
	public void setEnd(Node end) {
		this.endNode=end;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID=ID;
	}
}