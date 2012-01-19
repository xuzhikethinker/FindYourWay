package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Graph implements IGraph {

	private int matrix[][];
	private String names[];

	public Graph(String filename) throws FileNotFoundException {
		this.readFromFile(filename);
	}

	public Graph(int length) {
		this.setLength(length);
	}

	@Override
	public int getDistance(int a, int b) {
		return matrix[a][b];
	}

	@Override
	public int[] getNeighbors(int a) {
		int neighborsTmp[] = new int[matrix.length];
		int j = 0;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[a][i] > 0) {
				neighborsTmp[j] = i;
				j++;
			}
		}

		int neighbors[] = new int[j];
		for (int i = 0; i < neighbors.length; i++) {
			neighbors[i] = neighborsTmp[i];
		}

		return neighbors;
	}

	@Override
	public int[] getAllNodes() {
		int node[] = new int[matrix.length];

		for (int i = 0; i < matrix.length; i++) {
			node[i] = i;
		}
		return node;
	}

	@Override
	public int getLength() {
		return matrix.length;
	}

	@Override
	public String getNodeName(int a) {
		if (a < 0 || a >= matrix.length) {
			return null;
		}
		return names[a];
	}

	@Override
	public void setNodeName(int a, String n) {
		this.names[a] = n;
	}

	@Override
	public void setLength(int length) {
		matrix = new int[length][length];
		names = new String[length];
	}

	@Override
	public void setDistance(int a, int b, int dist) {
		// TODO Speicheroptimierung, nur halben Graphen verwenden und dann Node
		// a >= Node b annehmen
		matrix[a][b] = dist;
		matrix[b][a] = dist;
	}

	@Override
	public void writeToFile(String filename) throws IOException {
		int tmp;
		
		// TODO ggf. Dateiendung anhängen (über Funktion addFileExt())
		FileWriter fw = new FileWriter("./files/" + filename);
		BufferedWriter writer = new BufferedWriter(fw);

		// Matrix
		for (int i = 0; i < this.getLength(); i++) {
			for (int j = 0; j < this.getLength(); j++) {
				tmp = this.getDistance(i, j);

				// Immer zweistellige Zahlen anzeigen
				writer.write(String.format("%02d", tmp));
				writer.write(" ");
			}
			writer.write(System.getProperty("line.separator"));
			writer.flush();
		}

		// Namen
		for (int i = 0; i < this.getLength(); i++) {
			writer.write(this.getNodeName(i));

			if (i != this.getLength() - 1) {
				writer.write(System.getProperty("line.separator"));
			}

			writer.flush();
		}
		writer.close();

	}

	@Override
	public void readFromFile(String filename) throws FileNotFoundException {
		String neu;
		int anzahl = 0;
		String[] Knoten;
		int i = 0;

		// TODO ggf. Dateiendung anhängen (über Funktion addFileExt())
		FileReader fr = new FileReader("./files/" + filename);
		BufferedReader reader = new BufferedReader(fr);

		try {
			while ((neu = reader.readLine()) != null) {
				if (i == 0) {
					// Graph initalisieren
					anzahl = neu.split(" ").length;
					this.setLength(anzahl);
				}
				if (i < anzahl) {
					Knoten = neu.split(" ");
					for (int j = 0; j < Knoten.length; j++) {
						if (!Knoten[j].equals("00")) {
							this.setDistance(i, j, Integer.parseInt(Knoten[j]));
						}
					}
				} else {
					this.setNodeName(i - anzahl, neu);
				}
				i++;
			}

		} catch (IOException e) {
			System.out.println("Fehler beim Lesen der Datei");
		}
	}

}
