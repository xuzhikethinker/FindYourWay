package algorithm;

import graph.IGraph;
import graph.Node;

public class DijkstraAlgorithm implements IAlgorithm {
	private Node startNode;
	private Node endNode;
	private IGraph graph;
	private AlgNode[] AlgKnoten;

	

	@Override
	public void setStartNode(Node node) {
		this.startNode = node;
	}

	@Override
	public void setEndNode(Node node) {
		this.endNode = node;
	}

	@Override
	public void run() {
		if (this.startNode == null || this.endNode == null
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
	public Node[] getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGraph(IGraph graph) {
		this.graph = graph;
	}

	private void init() {
		Node Knoten[] = graph.getAllNodes();
		this.AlgKnoten = new AlgNode[Knoten.length];
		for (int i = 0; i < Knoten.length; i++) {
			if (this.startNode.getIndex() == Knoten[i].getIndex()) {
				this.AlgKnoten[i] = new AlgNode(Knoten[i], true);
			} else {
				this.AlgKnoten[i] = new AlgNode(Knoten[i], false);
			}
		}
	}

}
