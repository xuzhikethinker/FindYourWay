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
	 * Führt den Algorithmus aus.
	 */
	void run() throws IllegalStateException;
	
	/**
	 * Gibt Punkte in ermittelter Reihenfolge zurück
	 * @return
	 */
	int[] getResult();
	
	/**
	 * Gibt die Gesamtdistanz zurück
	 * @return
	 */
	
	int getTotalDistance();
	
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
