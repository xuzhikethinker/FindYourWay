package gui;

/**
 * Die UI wird direkt aus der Main-Funktion mit gew�nschten Algorithmus aufgerufen
 * 
 * Jede UI soll selbstst�ndig Punktedaten (Graph) und Start- und Zielpunkt abfragen und setzen.
 * Die Interpretation von Punkten und Distanzen �bernimmt das UI. (zb. St�dte)
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