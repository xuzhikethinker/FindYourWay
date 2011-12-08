package algorithm;

import graph.IGraph;
import graph.Node;

public  class DijkstraAlgorithm implements IAlgorithm {
private  Node startNode;
private  Node endNode;
private IGraph graph;
	
	private static void Dijkstra(IGraph graph,Node startNode){
		int zwischen=graph.getLength();
		if(zwischen!=0){
			
		}
		else{
		  //Fehler
		}
	}
	@Override
	public void setStartNode(Node node) {
		this.startNode=node;
	}

	@Override
	public void setEndNode(Node node) {
		this.endNode=node;
	}

	@Override
	public void run() {
		Dijkstra(graph,startNode);
	}

	@Override
	public Node[] getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGraph(IGraph graph) {
		this.graph=graph;
	}

}
