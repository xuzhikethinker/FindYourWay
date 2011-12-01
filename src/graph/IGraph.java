package graph;

public interface IGraph {

	int getDistance(Node a, Node b);

	Node[] getNeighbors(Node a);

	Node[] getAllNodes();

	int getLength();

	String getNodeName(Node a);

	void setLength(int length);

	void setDistance(Node a, Node b, int dist);
}
