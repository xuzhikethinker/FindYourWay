package gui;

import gui.IUserinterface;
import algorithm.DijkstraAlgorithm;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Console soll als UI genutzt werden
		IUserinterface ui = new Console();

		// Programierer legt Algorithmus fest
		ui.setAlgorithm(new DijkstraAlgorithm());

		// UI ausführen
		try {
			ui.run();
		} catch (Exception e) {
			System.out.println("Fehler beim Ausführen der Oberfläche. "	+ e.getMessage());
		}

	}
}
