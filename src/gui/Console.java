package gui;

import graph.Graph;
import graph.IGraph;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import algorithm.IAlgorithm;
/**
 * @author Marco S.
 */
public class Console implements IUserinterface {

	private IAlgorithm algorithm;
	private IGraph graph;
	private boolean AlgRunned = false;

	private Scanner scan = new Scanner(System.in);

	@Override
	public void setAlgorithm(IAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public void run() {

		String auswahl;
		boolean run = true;

		do {
			System.out.println("-- FindYourWay - Algorithmus: "
					+ this.algorithm.getClass().getSimpleName() + " --");

			if (this.algorithm.getStartNode() != -1)
				System.out
						.println("Startpunkt: "
								+ this.graph.getNodeName(this.algorithm
										.getStartNode()));
			
			if (this.algorithm.getStartNode() != -1)
				System.out.println("Endpunkt: "
						+ this.graph.getNodeName(this.algorithm.getEndNode()));
			
			if (graph != null)
				System.out.println("Anzahl der Städte: "
						+ this.graph.getLength());

			System.out.println("Algorithmus gelaufen: "
					+ (AlgRunned ? "ja" : "nein"));
			
			System.out.println();

			System.out.print("Eingabe: ");
			auswahl = scan.nextLine().trim();
			System.out.println();

			switch (auswahl) {

			case "run":
				runDialog();
				break;

			case "result":
				showResult();
				break;

			case "c":
			case "config":
				configDialog();
				break;

			case "h":
			case "help":
				showHelp();
				break;

			case "r":
			case "read":
				readDialog();
				break;
				
			case "w":
			case "write":
				writeDialog();
				break;
				
			case "q":
			case "quit":
			case "exit":
				run = false;
				System.out.println("Programm wird beendet.");
				break;
			}

			System.out.println();

		} while (run);

	}

	private void configDialog() {
		if (this.graph == null) {
			System.out.println("Es wurden noch keine Städte geladen.");
		} else {

			showNodes();
			System.out.println();

			System.out.print("Startpunkt: ");
			int start;
			try {
				start = Integer.parseInt(scan.nextLine());
				
				try {
					this.algorithm.setStartNode(start);
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				
			} catch (NumberFormatException e2) {
				System.out.println("Es wurde keine Zahl eingegeben");
			}

			System.out.print("Endpunkt: ");
			int end;
			
			try {
				end = Integer.parseInt(scan.nextLine());
				
				try {
					this.algorithm.setEndNode(end);
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				
			} catch (NumberFormatException e1) {
				System.out.println("Es wurde keine Zahl eingegeben");
			}

			// Da Daten geändert wurden
			this.AlgRunned = false;
		}
	}

	private void readDialog() {
		System.out.print("Dateiname eingeben (*.graph): ");
		String file = scan.nextLine().trim();

		try {
			graph = new Graph(file);
			this.algorithm.setGraph(graph);
			System.out.println("Daten wurden aus " + file + " geladen.");
			showMatrix();
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht vorhanden.");
		}
	}
	
	private void writeDialog() {
		System.out.print("Dateiname eingeben (*.graph): ");
		String file = scan.nextLine().trim();

		try {
			graph.writeToFile(file);
			System.out.println("Daten wurden in " + file + " geschrieben.");
			showMatrix();
		} catch (IOException e) {
			System.out.println("Datei nicht vorhanden.");
		}
	}
	
	private void runDialog() {
		try {
			this.algorithm.run();

			System.out.println("Fertig berechnet.");
			AlgRunned = true;
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showResult() {
		if (this.AlgRunned) {
			int result[] = this.algorithm.getResult();

			System.out.print("Route: - ");
			for (int node : result) {
				System.out.print(this.graph.getNodeName(node) + " - ");
			}
			System.out.println();
			System.out.println("Distanz: " + this.algorithm.getTotalDistance());
			
		} else {
			System.out.println("Algorithmus ist noch nicht gelaufen.");
		}
	}

	private void showNodes() {
		int[] nodes = this.graph.getAllNodes();

		System.out.println("Vorhandene Städte:");
		for (int node : nodes) {
			System.out.printf("%d: %s\n", node, this.graph.getNodeName(node));
		}
	}

	private void showHelp() {
		System.out.println("h, help \t- Befehle anzeigen");
		System.out.println("c, config \t- Daten eingeben");
		System.out.println("run \t- Algorithmus laufen lassen");
		System.out.println("result \t- Ergebnis anzeigen");
		System.out.println("r, read \t- Städte laden");
		System.out.println("q, quit \t- Programm beenden");
	}

	private void showMatrix() {
		for (int i = 0; i < this.graph.getLength(); i++) {
			for (int j = 0; j < this.graph.getLength(); j++) {
				System.out.printf("%03d ", this.graph.getDistance(i, j));
			}
			System.out.println();
		}
	}
}
