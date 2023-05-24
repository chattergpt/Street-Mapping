import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Queue;

public class StreetMap extends JFrame {
	
	public static void main(String[] args) {
		Scanner fileIn=null;
		Graph graph=new Graph();
		ArrayList<Node> path;
		
		try {
			fileIn=new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
        		System.out.println("File not found");
        		System.exit(-1);
    		}
		
		while(fileIn.hasNextLine()) {
			String[] line=fileIn.nextLine().split("\t");
			if(line[0].equals("i")) {
				graph.addNode(line[1], Double.parseDouble(line[2]), Double.parseDouble(line[3]));
			}
			else {
				graph.addEdge(line[1], line[2], line[3]);
			}
		}
		
		if(args[1].equals("--show")) {
			GUI map=new GUI(graph, null);
			
			if(args.length>2&&args[2].equals("--directions")) {
				path=graph.getShortestPath(graph.nodes.get(args[3]), graph.nodes.get(args[4]));
				if(path.size()==0) {
					System.out.println("No path found between "+args[3]+" & "+args[4]);
				}
				else {
					System.out.println("Shortest path between "+args[3]+" & "+args[4]+": "+path);
					System.out.println("Total distance: "+graph.nodes.get(args[4]).getDistance()+" miles");
				}
				
				map=new GUI(graph, path);
			}
			
			JFrame frame=new JFrame("Street Map");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800,800);
			frame.setResizable(true);
			frame.add(map);
			frame.setVisible(true);
		}
		else if(args[1].equals("--directions")) {
			path=graph.getShortestPath(graph.nodes.get(args[2]), graph.nodes.get(args[3]));
			if(path.size()==0) {
				System.out.println("No path found between "+args[2]+" & "+args[3]);
			}
			else {
				System.out.println("Shortest path between "+args[2]+" & "+args[3]+": "+path);
				System.out.println("Total distance: "+graph.nodes.get(args[3]).getDistance()+" miles");
			}
		}
	}
}

class GUI extends JPanel {
    Graph graph;
    List<Node> path;
    
    public GUI(Graph g, List<Node> p) {
    	graph=g;
    	path=p;
    	repaint();
    }
    
    public void paintComponent(Graphics g) {
    	double height=getHeight();
    	double width=getWidth();
    	Graphics2D g2d=(Graphics2D) g;
    	if(getWidth()>getHeight()) {
            height=width=getHeight();
        } else {
            height=width=getWidth();
        }
    	
    	int ctr=0;
    	for (String s:graph.nodes.keySet()) {
            Node node = graph.nodes.get(s);
            int x1=(int) ((node.getLongitude()-graph.minLon)/(graph.maxLon-graph.minLon)*width);
            int y1=(int) (height-(node.getLatitude()-graph.minLat)/(graph.maxLat-graph.minLat)*height);
            
            for(Node neighbor:node.getNeighbors()) {
            	int x2=(int) ((neighbor.getLongitude()-graph.minLon)/(graph.maxLon-graph.minLon)*width);
                int y2=(int) (height-(neighbor.getLatitude()-graph.minLat)/(graph.maxLat-graph.minLat)*height);
                
                if(path!=null&&ctr<path.size()-1&&node.equals(path.get(ctr))&&neighbor.equals(path.get(ctr+1))) {
                    ctr++;
                    g2d.setColor(Color.RED);
                    g2d.setStroke(new BasicStroke(3));
                    g2d.drawLine(x1, y1, x2, y2);
                } else {
                    g2d.setColor(Color.BLACK);
                    g2d.setStroke(new BasicStroke(1));
                    g2d.drawLine(x1, y1, x2, y2);
                }
            }
            
        }
    }
}