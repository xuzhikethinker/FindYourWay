package gui;

import java.io.FileNotFoundException;

import graph.Graph;
import algorithm.IAlgorithm;

public class GUI implements IUserinterface {

	private IAlgorithm algorithm;

	@Override
	public void setAlgorithm(IAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public void run() {

		// BEISPIEL:

		// Diese Daten bitte vorher vom Benutzer abfragen
		try {
			this.algorithm.setGraph(new Graph("Deutschland-Metropole.xml"));
		} catch (FileNotFoundException e) {
			// Datei nicht vorhanden?
		}

		try {
			this.algorithm.setStartNode(10); // ID von Berlin
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		}
		
		try {
			this.algorithm.setEndNode(12); // ID von M�nchen
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		}

		// Algorithmus laufen lassen
		try {
			this.algorithm.run();
		} catch (IllegalStateException e) {
			// Fehlerbehandlung
		}
		int result[] = this.algorithm.getResult();

		System.out.println("Ergebnis: " + result);

		// BEISPIEL ENDE

	}
}
