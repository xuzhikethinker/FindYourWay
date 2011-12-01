package gui;

/**
 * Die UI wird direkt aus der Main-Funktion mit gewünschten Algorithmus aufgerufen
 * 
 * Jede UI soll selbstständig Punktedaten (Graph) und Start- und Zielpunkt abfragen und setzen.
 * Die Interpretation von Punkten und Distanzen übernimmt das UI. (zb. Städte)
 */

import algorithm.IAlgorithm;

public interface IUserinterface {
	void setAlgorithm(IAlgorithm algorithm);
	void run();
}