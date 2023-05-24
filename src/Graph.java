import java.util.*;

public class Graph {
	Map<String, Node> nodes;
	ArrayList<Edge> edges;
	double maxLon;
	double minLon;
	double maxLat;
	double minLat;
	
	public Graph() {
		nodes=new HashMap<String, Node>();
		edges=new ArrayList<Edge>();
		maxLat = maxLon = -Double.MAX_VALUE;
		minLat = minLon = Double.MAX_VALUE;
	}
	
	public void addNode(String name, double latitude, double longitude) {
		nodes.put(name, new Node(name, latitude, longitude));
		
		if(latitude>maxLat) {
			maxLat=latitude;
		}
		else if(latitude<minLat) {
			minLat=latitude;
		}
		
		if(longitude>maxLon) {
			maxLon=longitude;
		}
		else if(longitude<minLon) {
			minLon=longitude;
		}
	}
	
	public void addEdge(String ID, String start, String end) {
		Node from=nodes.get(start);
		Node to=nodes.get(end);
		double weight=findDistance(from, to);
		Edge e=new Edge(ID, from, to, weight);
		edges.add(e);
		from.addNeighbor(to);
		to.addNeighbor(from);
	}
	
	//Uses haversine formula to calculate distance between two nodes
	public double findDistance(Node start, Node end) {
        double dLat=Math.toRadians(end.getLatitude() - start.getLatitude());
        double dLon=Math.toRadians(end.getLongitude() - end.getLongitude());
        double a=Math.pow(Math.sin(dLat/2), 2) + Math.cos(start.getLatitude()) * Math.cos(end.getLatitude()) * Math.pow(Math.sin(dLon/2), 2);
        double c=2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return 3958.8*c;
	}
	
	//Uses dijkstra's algorithm to find shortest path between two nodes
	public ArrayList<Node> getShortestPath(Node start, Node end) {
		ArrayList<Node> path=new ArrayList<Node>();
		Node currNode=new Node();
		start.setDistance(0);
		PriorityQueue<Node> queue=new PriorityQueue<Node>();
		queue.add(start);
		
		while(!queue.isEmpty()&&!currNode.equals(end)) {
			currNode=queue.remove();
			for(Node neighbor:currNode.getNeighbors()) {
				double distance=currNode.getDistance()+findDistance(currNode, neighbor);
				if(distance<neighbor.getDistance()) {
					queue.remove(neighbor);
					neighbor.setDistance(distance);
					neighbor.setPrevNode(currNode);
					queue.add(neighbor);
				}
			}
		}
		
		while(currNode!=null) {
			path.add(0, currNode);
			currNode=currNode.getPrevNode();
		}
		
		return path;
	}
}