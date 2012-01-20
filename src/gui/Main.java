package gui;

import algorithm.*;

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
		ui.run();

	}
}
