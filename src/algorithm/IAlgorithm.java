package algorithm;

import graph.IGraph;
import graph.Node;

public interface IAlgorithm {
	
	/**
	 * Setzt die Punktedaten.
	 * @param graph
	 */
	void setGraph(IGraph graph);
	
	/**
	 * Setzt den Startpunkt.
	 * @param node
	 */
	void setStartNode(Node node);
	
	/**
	 * Setzt den Endpunkt.
	 * @param node
	 */
	void setEndNode(Node node);
	
	/**
	 * F�hrt den Algorithmus aus.
	 */
	void run();
	
	/**
	 * Gibt Punkte in ermittelter Reihenfolge zur�ck
	 * @return
	 */
	Node[] getResult();
}
