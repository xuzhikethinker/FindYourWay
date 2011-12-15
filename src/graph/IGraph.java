package graph;

public interface IGraph {

	/**
	 * Liefert Distanz zwischen zwei Knoten.
	 * @param a Knoten 1
	 * @param b Knoten 2
	 * @return Distanz
	 */
	int getDistance(Node a, Node b);

	/**
	 * Liefert alle Nachbarn eines Knotens.
	 * @param a Knoten
	 * @return Alle Nachbarn von a
	 */
	Node[] getNeighbors(Node a);

	/**
	 * Liefert alle vorhandene Knoten.
	 * @return Die Knoten
	 */
	Node[] getAllNodes();

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
	String getNodeName(Node a);

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
	void setDistance(Node a, Node b, int dist);
}
