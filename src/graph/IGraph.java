package graph;

import java.io.IOException;

public interface IGraph {

	/**
	 * Liefert Distanz zwischen zwei Knoten.
	 * @param a Knoten 1
	 * @param b Knoten 2
	 * @return Distanz
	 */
	int getDistance(int a, int b);
	
	/**
	 * Liefert alle Nachbarn eines Knotens.
	 * @param a Knoten
	 * @return Alle Nachbarn von a
	 */
	int[] getNeighbors(int a);

	/**
	 * Liefert alle vorhandene Knoten.
	 * @return Die Knoten
	 */
	int[] getAllNodes();

	/**
	 * Liefert Anzahl der Knoten des Graphens.
	 * @return Anzahl
	 */
	int getLength();

	/**
	 * Liefert "Namen" eines Knotens.
	 * @param a Knoten
	 * @return Knotennamen
	 */
	String getNodeName(int a);

	/**
	 * Setzt "Namen" eines Knotens.
	 * @param a Knoten
	 * @param n Name
	 * @return 
	 */
	void setNodeName(int a, String n);
	
	/**
	 * Setzt Anzahl der Knoten des Graphens.
	 * @param length Anzahl
	 * @post Daten eines vorherigen Graphens werden gelöscht.
	 */
	void setLength(int length);

	/**
	 * Setzt Distanz zwischen zwei Knoten.
	 * @param a Knoten 1
	 * @param b Knoten 2
	 * @param dist Distanzwert
	 */
	void setDistance(int a, int b, int dist);
	
	
	void save() throws IOException;
}
