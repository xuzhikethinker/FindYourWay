package gui;

import algorithm.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IUserinterface ui;

		// UI festlegen
		if (args.length >0 && args[0] == "console") {
			ui = new Console();
		} else {
			ui = new GUI();
		}

		// Algorithmus setzen
		ui.setAlgorithm(new DijkstraAlgorithm());

		// UI ausführen
		ui.run();

	}
}
