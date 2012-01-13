package graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Graph implements IGraph {

	private int matrix[][];
	private String names[];

	public Graph(String filename) throws FileNotFoundException {
		// TODO ggf. Dateiendung anhängen.
		String neu;
		int anzahl;
		String[] Knoten;
		int i = 0;
		FileReader fr = new FileReader("./files/" + filename);
		BufferedReader reader = new BufferedReader(fr);
		try {
			while ((neu = reader.readLine()) != null) {
				anzahl = neu.split(" ").length;
				if (i < anzahl) {
					Knoten = neu.split(" ");
					for (int j = 0; j <= i - 1; j++) {
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
			System.out.println("Fehler beim lesen der Datei");
		}

		// Beispieldatensatz, Hardcoded
		// http://upload.wikimedia.org/wikipedia/commons/4/45/Dijksta_Anim.gif
		this.setLength(6);
		this.setDistance(0, 1, 7); // 1 zu 2
		this.setDistance(0, 2, 9); // 1 zu 3
		this.setDistance(0, 5, 14); // 1 zu 6
		this.setDistance(1, 2, 10); // 2 zu 3
		this.setDistance(1, 3, 15); // 2 zu 4
		this.setDistance(2, 3, 11); // 3 zu 4
		this.setDistance(2, 5, 2); // 3 zu 6
		this.setDistance(3, 4, 6); // 4 zu 5
		this.setDistance(4, 5, 9); // 5 zu 6

		this.setNodeName(0, "Punkt 1");
		this.setNodeName(1, "Punkt 2");
		this.setNodeName(2, "Punkt 3");
		this.setNodeName(3, "Punkt 4");
		this.setNodeName(4, "Punkt 5");
		this.setNodeName(5, "Punkt 6");
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

}
