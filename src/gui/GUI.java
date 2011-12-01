package gui;

import graph.Graph;
import graph.Node;
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
		this.algorithm.setGraph(new Graph("Deutschland-Metropole.xml"));
		this.algorithm.setStartNode(new Node(10)); // ID von Berlin
		this.algorithm.setEndNode(new Node(12)); // ID von München

		
		// Algorithmus laufen lassen
		this.algorithm.run();
		Node result[] = this.algorithm.getResult();
		
		System.out.println("Ergebnis: " + result);
		
		// BEISPIEL ENDE

	}
}
