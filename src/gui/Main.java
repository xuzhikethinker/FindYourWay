package gui;

import java.util.Arrays;

import algorithm.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IUserinterface ui;

		// UI festlegen
		if (Arrays.asList(args).contains("console")) {
			ui = new Console();
		} else {
			ui = new GUI();
		}

		// Algorithmus setzen
		if (Arrays.asList(args).contains("msalgo")) {
			ui.setAlgorithm(new MsAlgorithm());
		} else {
			ui.setAlgorithm(new DijkstraAlgorithm());
		}
		
		// UI ausführen
		ui.run();

	}
}
