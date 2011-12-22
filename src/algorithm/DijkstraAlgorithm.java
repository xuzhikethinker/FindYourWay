package algorithm;

import graph.IGraph;

public class DijkstraAlgorithm implements IAlgorithm {
	private int startNode;
	private int endNode;
	private IGraph graph;
	private AlgNode[] AlgKnoten;

	

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
		if (this.startNode == -1 || this.endNode == -1
				|| this.graph == null) {
			// throw new Exception();
		} else {
			int unbesucht = graph.getLength();
			if (unbesucht != 0) {
				init();
			}
		}

	}

	@Override
	public int[] getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGraph(IGraph graph) {
		this.graph = graph;
	}

	private void init() {
		int Knoten[] = graph.getAllNodes();
		this.AlgKnoten = new AlgNode[Knoten.length];
		for (int i = 0; i < Knoten.length; i++) {
			if (this.startNode == Knoten[i]) {
				this.AlgKnoten[i] = new AlgNode(Knoten[i], true);
			} else {
				this.AlgKnoten[i] = new AlgNode(Knoten[i], false);
			}
		}
	}

}
