package gui;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import graph.Graph;
import algorithm.IAlgorithm;

public class GUI implements IUserinterface {

	String array[]={"OK","Delete","Start","Finish","Options"};
	Object Miraculi=JOptionPane.showOptionDialog(null," Willst du das Programm wirklich starten?","FindYourWay ",JOptionPane.DEFAULT_OPTION,JOptionPane.OK_CANCEL_OPTION,null,array,"OK");
	private IAlgorithm algorithm;
	String anzahl=JOptionPane.showInputDialog("Geben sie die Anzahl der Städte an","Städtezahl");
	String namen=JOptionPane.showInputDialog("Geben sie die Städtenamen ein","Städtenamen");
	String p=JOptionPane.showInputDialog("Bitte geben sie eine Zahl ein!","Startzahl");
   int a=Integer.parseInt(p);
   String c=JOptionPane.showInputDialog("Bitte geben sie eine Zahl ein","Endzahl");
   int z=Integer.parseInt(c);
   

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
			this.algorithm.setStartNode(a); // ID von Berlin
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		}
		
		try {
			this.algorithm.setEndNode(z); // ID von M�nchen
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
		JOptionPane.showMessageDialog(null,"Ergebnis:" + result,"Ergebnisse",JOptionPane.DEFAULT_OPTION);

		// BEISPIEL ENDE

	}
}
