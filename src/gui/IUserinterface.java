package gui;

/**
 * Die UI wird direkt aus der Main-Funktion mit gewünschten Algorithmus aufgerufen
 * 
 * Jede UI soll selbstständig Punktedaten (Graph) und Start- und Zielpunkt abfragen und setzen.
 * Die Interpretation von Punkten und Distanzen übernimmt das UI. (zb. Städte)
 */

import algorithm.IAlgorithm;

public interface IUserinterface {
	/**
	 * Setzt eine Instanz des Algorithmus.
	 * @param algorithm
	 * @return 
	 */
	void setAlgorithm(IAlgorithm algorithm);
	/**
	 * Startet Interface
	 */
	void run();
}