import java.util.*;

public class Node implements Comparable<Node> {
	private double distance;
	private double longitude;
	private double latitude;
	private List<Edge> edges;
	private String ID;
	private Node prevNode;
	private ArrayList<Node> neighbors;
	
	public Node() {
		distance=Double.MAX_VALUE;
	}
	
	public Node(String id, double lat, double lon) {
		ID=id;
		longitude=lon;
		latitude=lat;
		prevNode=null;
		distance=Double.MAX_VALUE;
		edges=new ArrayList<Edge>();
		neighbors=new ArrayList<Node>();
	}
	
	public void addNeighbor(Node neighbor) {
		neighbors.add(neighbor);
	}
	
	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}
	
	public String getID() {
		return ID;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double d) {
		distance=d;
	}
	
	public List<Edge> getEdges() {
		return edges;
	}
	
	public void setEdges(List<Edge> e) {
		edges=e;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double l) {
		latitude=l;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double l) {
		longitude=l;
	}
	
	public Node getPrevNode() {
		return prevNode;
	}
	
	public void setPrevNode(Node p) {
		prevNode=p;
	}
	
	public int compareTo(Node o) {
		if(this.distance<o.distance) {
			return -1;
		}
		else if(this.distance>o.distance) {
			return 1;
		}
		
		return 0;
	}
	
	public String toString() {
		return ID;
	}
}