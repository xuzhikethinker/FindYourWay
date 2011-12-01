package gui;

/**
 * Die UI wird direkt aus der Main-Funktion mit gew�nschten Algorithmus aufgerufen
 * 
 * Jede UI soll selbstst�ndig Punktedaten (Graph) und Start- und Zielpunkt abfragen und setzen.
 * Die Interpretation von Punkten und Distanzen �bernimmt das UI. (zb. St�dte)
 */

import algorithm.IAlgorithm;

public interface IUserinterface {
	void setAlgorithm(IAlgorithm algorithm);
	void run();
}