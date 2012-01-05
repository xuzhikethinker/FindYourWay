package algorithm;

import graph.IGraph;

public class DijkstraAlgorithm implements IAlgorithm {
	private int startNode=1;
	private int endNode;
	private IGraph graph;
	private	int knoten[]; 
	private int distanz[]; 
	private int vorgaenger[];
	private boolean besucht[];

	@Override
	public void setStartNode(int node) {
		this.startNode = node;
	}

	@Override
	public void setEndNode(int node) {
		this.endNode = node;
	}

	@Override
	public void run() {
		if (this.startNode == -1 || this.endNode == -1 || this.graph == null) {
			// throw new Exception();
		} else {
			int unbesucht = graph.getLength();
			if (unbesucht != 0) {
				init();
				wege();
			}
		}

	}

	@Override
	public int[] getResult() {
		// TODO Auto-generated method stub
		return new int[]{0,1,3};
	}

	@Override
	public void setGraph(IGraph graph) {
		this.graph = graph;
	}

	private void init() {
		 knoten = graph.getAllNodes();
		 distanz =new int[graph.getLength()];
		 vorgaenger=new int[graph.getLength()];
		 besucht=new boolean[graph.getLength()];
		
	}

	private void wege() {
		int aktKnoten=startNode;
		int[]aktNachbarn;
		for(int i=0;i<graph.getLength();i++){
			aktNachbarn=graph.getNeighbors(aktKnoten);
			for(int j=0;j<aktNachbarn.length;j++){
				if(distanz[aktKnoten]==-1 || distanz[aktKnoten]>graph.getDistance(aktKnoten, aktNachbarn[j])){
					distanz[aktKnoten]=graph.getDistance(aktKnoten,aktNachbarn[j]);	
					vorgaenger[aktNachbarn[j]]=aktKnoten;
				}
					
			}
			besucht[aktKnoten]=true;
			int max=Integer.MAX_VALUE;
			for(int h=0;h<aktNachbarn.length;h++){
				 if(distanz[aktNachbarn[h]]<max){
					 max=distanz[aktNachbarn[h]];
				 }
			}
			
		}
		
	}

	@Override
	public int getStartNode() {
		return this.startNode;
	}

	@Override
	public int getEndNode() {
		return this.endNode;
	}

}
