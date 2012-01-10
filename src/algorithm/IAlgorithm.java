package algorithm;

import graph.IGraph;

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
	void setStartNode(int node) throws IllegalArgumentException;
	
	/**
	 * Setzt den Endpunkt.
	 * @param node
	 */
	void setEndNode(int node) throws IllegalArgumentException;
	
	/**
	 * F�hrt den Algorithmus aus.
	 */
	void run() throws IllegalStateException;
	
	/**
	 * Gibt Punkte in ermittelter Reihenfolge zur�ck
	 * @return
	 */
	int[] getResult();
	
	/**
	 * Liefert Start-Knotens.
	 * @return Start-Knoten
	 */
	int getStartNode();

	/**
	 * Liefert Ende-Knotens.
	 * @return Ende-Knoten
	 */
	int getEndNode();
}
