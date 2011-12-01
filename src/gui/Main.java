package gui;

import gui.IUserinterface;
import algorithm.DijkstraAlgorithm;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// GUI soll als UI genutzt werden
		IUserinterface ui = new GUI();

		// Programierer legt Algorithmus fest
		ui.setAlgorithm(new DijkstraAlgorithm());

		// UI ausführen
		ui.run();

	}

}
