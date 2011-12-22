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
	void setStartNode(int node);
	
	/**
	 * Setzt den Endpunkt.
	 * @param node
	 */
	void setEndNode(int node);
	
	/**
	 * Führt den Algorithmus aus.
	 */
	void run();
	
	/**
	 * Gibt Punkte in ermittelter Reihenfolge zurück
	 * @return
	 */
	int[] getResult();
}
