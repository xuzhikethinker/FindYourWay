package algorithm;

import java.util.ArrayList;
import graph.IGraph;
/**
 * @author Moritz M.
 */
public class DjAlgo implements IAlgorithm {
	private int 	startNode;
	private int 	endNode;
	private IGraph	graph;
	private Node[]	allNodes;
	private int[]	path;
	
	@Override
	public void setGraph(IGraph graph) {
		this.graph 		= graph;
		this.startNode 	= -1;
		this.endNode 	= -1;
	}

	@Override
	public void setStartNode(int node) throws IllegalArgumentException {
		if(this.graph == null || this.graph.getLength() <= node){
			throw new IllegalArgumentException("Dieser Punkt existiert nicht");
		}
		
		this.startNode = node;
	}

	@Override
	public void setEndNode(int node) throws IllegalArgumentException {
		if(this.graph == null || this.graph.getLength() <= node){
			throw new IllegalArgumentException("Dieser Punkt existiert nicht");
		}
		
		this.endNode = node;
	}

	@Override
	public void run() throws IllegalStateException {
		if (this.startNode == -1 || this.endNode == -1 || this.graph == null) {
			throw new IllegalStateException("Es wurden noch keine Daten gesetzt");
		} else {
			init();
			distanz_update();		
			createShortestPath();
		}
	}

	@Override
	public int[] getResult() {
		return path;
	}

	@Override
	public int getTotalDistance() {
		return this.allNodes[this.endNode].getDistance();
	}

	@Override
	public int getStartNode() {
		return this.startNode;
	}

	@Override
	public int getEndNode() {
		return this.endNode;
	}
	
	private void init() {		
		this.allNodes = new Node[this.graph.getAllNodes().length];
		
		for(int i = 0; i < this.allNodes.length; i++) {
			this.allNodes[i] =  new Node(this.graph.getAllNodes()[i]);
		}
		
		for(int i = 0; i < this.allNodes.length; i++) {
			Node[] tmpneighbors = new Node[this.graph.getNeighbors(i).length];
			
			for(int j = 0; j < tmpneighbors.length; j++) {
				tmpneighbors[j] = this.allNodes[this.graph.getNeighbors(i)[j]];
			}
			
			this.allNodes[i].setNeighbors(tmpneighbors);
			
		}
		
		this.allNodes[this.startNode].distance = 0;
		
	}

	private void distanz_update() {
		Node aktnode = this.allNodes[this.startNode];
		
		do{ // Solange es noch unbesuchte Knoten gibt
			aktnode.setVisited();
			
			// Alle Nachbarn des aktuellen Knotens
			for(Node node : aktnode.getNeighbors()) {
				if(node.getDistance() > aktnode.getDistance() + graph.getDistance(aktnode.getId(), node.getId()) || node.getDistance() == -1){
					node.setDistance(aktnode.getDistance() + graph.getDistance(aktnode.getId(), node.getId()));
					node.setPredecessor(aktnode);
				}
			}
			
			aktnode = chooseNextNode();
			
		}while(unvisitedNodes());
		
	}
	
	private Node chooseNextNode() {
		int max = Integer.MAX_VALUE;
		Node tmpnode = null;
		
		for(Node node : this.allNodes) {
			if(!node.isVisited() && node.getDistance() < max && node.distance != -1){
				max = node.getDistance();
				tmpnode = node;
			}
		}
		
		return tmpnode;
	}
	
	private boolean unvisitedNodes() {
		for(Node node : this.allNodes) {
			if(!node.visited){
				return true;
			}
		}
		return false;
	}
	
	private void createShortestPath() {
		ArrayList<Integer> ar = new ArrayList<Integer>();
		
		Node aktnode = this.allNodes[this.endNode];
		ar.add(aktnode.id);
		
		do{
			aktnode = aktnode.getPredecessor();
			ar.add(aktnode.id);
		}while(aktnode.getId() != this.startNode);
		
		this.path = new int[ar.size()];
		
		for(int i = 0; i < this.path.length; i++){
			this.path[i] = ar.get((ar.size() - 1)  - i);
		}
		
	}
	
	private class Node {
		private boolean visited;
		private int 	distance;
		private Node 	predecessor;
		private int		id;
		private Node[]	neighbors;
		
		private Node(int id){
			this.id				= id;
			this.visited 		= false; 	// Noch nicht besucht
			this.distance 		= -1; 		// Noch keine Distanz gesetzt
			this.predecessor 	= null; 	// Kein Vorgänger
		}

		private boolean isVisited() {
			return visited;
		}

		private void setVisited() {
			this.visited = true;
		}

		private int getDistance() {
			return distance;
		}

		private void setDistance(int distance) {
			this.distance = distance;
		}

		private int getId() {
			return id;
		}

		public Node[] getNeighbors() {
			return neighbors;
		}

		public void setNeighbors(Node[] neighbors) {
			this.neighbors = neighbors;
		}

		public Node getPredecessor() {
			return predecessor;
		}

		public void setPredecessor(Node predecessor) {
			this.predecessor = predecessor;
		}
	}
	
}