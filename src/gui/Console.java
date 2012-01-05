package gui;

import graph.Graph;
import graph.IGraph;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import algorithm.IAlgorithm;

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
			System.out.println("-- FindYourWay - Dijkstra-Algorithmus --");
			if (graph != null) {
				System.out.println("Startpunkt:"
						+ this.algorithm.getStartNode());
				System.out.println("Endpunkt: " + this.algorithm.getEndNode());
				System.out.println("Anzahl der Städte: "
						+ this.graph.getLength());
			}
			System.out.println("Algorithmus gelaufen: "
					+ (AlgRunned ? "ja" : "nein"));
			System.out.println();

			System.out.print("Eingabe: ");
			auswahl = scan.nextLine().trim();
			System.out.println();

			switch (auswahl) {
			case "c":
			case "config":

				if (graph == null) {
					System.out.println("Es wurden noch keine Städte geladen");
				} else {

					listNodes();
					System.out.println();
					System.out.print("Startpunkt: ");
					int start = Integer.parseInt(scan.nextLine());
					this.algorithm.setStartNode(start);

					System.out.print("Endpunkt: ");
					int end = Integer.parseInt(scan.nextLine());
					this.algorithm.setEndNode(end);
					
					// Da Daten geändert wurden
					AlgRunned=false;
				}
				break;

			case "run":
				try {
					this.algorithm.run();

					System.out.println("Fertig berechnet.");
					AlgRunned = true;
				} catch (Exception e) {
					// Fehlerbehandlung
					System.out.println("Algorithmus-Fehler");
				}

				break;

			case "result":
				if (AlgRunned) {
					int result[] = this.algorithm.getResult();
					System.out.println("Route: " + Arrays.toString(result));
				} else {
					System.out.println("Algorithmus ist noch nicht gelaufen");
				}

				break;

			case "h":
			case "help":
				System.out.println("h, help \t- Befehle anzeigen");
				System.out.println("c, config \t- Daten eingeben");
				System.out.println("run \t- Algorithmus laufen lassen");
				System.out.println("result \t- Ergebnis anzeigen");
				System.out.println("r, read \t- Städte laden");
				System.out.println("q, quit \t- Programm beenden");
				break;

			case "q":
			case "quit":
				run = false;
				System.out.println("Programm wird beendet");
				break;

			case "r":
			case "read":
				try {
					graph = new Graph("Deutschland-Metropole.xml");
					this.algorithm.setGraph(graph);
					System.out.println("Daten wurden geladen");
					showMatrix();
				} catch (FileNotFoundException e) {
					// Datei nicht vorhanden?
				}
				break;
			}

			System.out.println();

		} while (run);

	}

	private void listNodes() {
		int[] nodes = graph.getAllNodes();

		System.out.println("Vorhandene Städte:");
		for (int node : nodes) {
			System.out.printf("%d: %s\n", node, graph.getNodeName(node));
		}
	}

	private void showMatrix() {
		for (int i = 0; i < this.graph.getLength(); i++) {
			for (int j = 0; j < this.graph.getLength(); j++) {
				System.out.printf("%02d ", this.graph.getDistance(i, j));
			}
			System.out.println();
		}
	}
}
