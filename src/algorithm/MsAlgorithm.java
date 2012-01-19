package algorithm;

import graph.IGraph;

public class MsAlgorithm implements IAlgorithm {
	private int startNode=-1;
	private int endNode=-1;
	private IGraph graph;

	private int[] result;

	private int[] distance;
	private int[] previous;
	private boolean[] visited;

	@Override
	public void setStartNode(int node) {
		this.startNode = node;
		this.result = null;
	}

	@Override
	public void setEndNode(int node) {
		this.endNode = node;
		this.result = null;
	}

	@Override
	public void setGraph(IGraph graph) {
		this.graph = graph;
		this.result = null;
	}

	@Override
	public void run() {
		if (this.graph == null || this.startNode == this.endNode) {
			throw new IllegalStateException(
					"Es wurden noch keine Daten gesetzt");
		}

		// 1. Initialisierung
		initVariables();

		// 2. Knoten besuchen
		visitNodes();

		// 3. Ergebnis erstellen
		buildResult();

	}

	@Override
	public int[] getResult() {
		if (this.result == null) {
			return null;
		} else {
			return this.result;
		}
	}

	public int getTotalDistance() {
		if (this.result == null) {
			return 0;
		} else {
			return distance[this.endNode];
		}
	}

	private void buildResult() {
		int[] resultTmp = new int[this.graph.getLength()];
		int current = this.endNode;
		int j = 1;

		// Auflisten
		resultTmp[0] = this.endNode;
		do {
			current = previous[current];
			resultTmp[j] = current;
			j++;
		} while (current != this.startNode);

		// Richtige Größe anlegen
		this.result = new int[j];

		// Rückwärts schreiben
		for (int i = 0; i < j; i++) {
			this.result[i] = resultTmp[j - 1 - i];
		}
	}

	private void visitNodes() {
		int current;
		int[] neightbors;
		int dist;

		// TODO Optimieren falls Distanzen gleich lang sind
		// (z.B. Weg 5-2 und 2-5)!
		do {
			current = getNextByMinDistance();

			if (current == -1) {
				// TODO throw new Exception("Es kann kein Weg gefunden werden")
			}

			// 2.1. Knoten ist nun schon besucht
			visited[current] = true;

			// 2.2 Alle Nachbarknoten anschauen
			neightbors = this.graph.getNeighbors(current);

			for (int i = 0; i < neightbors.length; i++) {
				if (!visited[neightbors[i]]) {

					dist = this.graph.getDistance(current, neightbors[i])
							+ distance[current];

					// 2.3 Distanz kleiner?
					if (dist < distance[neightbors[i]]) {
						distance[neightbors[i]] = dist;
						previous[neightbors[i]] = current;
					}
				}
			}
		} while (current != this.endNode);
	}

	private void initVariables() {

		distance = new int[this.graph.getLength()];
		previous = new int[this.graph.getLength()];
		visited = new boolean[this.graph.getLength()];

		// Alle mit Default-Werte befüllen
		for (int i = 0; i < this.graph.getLength(); i++) {

			if (i == this.startNode) {
				distance[i] = 0;
			} else {
				distance[i] = Integer.MAX_VALUE;
			}

			previous[i] = -1;
			visited[i] = false;
		}
	}

	private int getNextByMinDistance() {
		int min_index = -1;

		for (int i = 0; i < this.graph.getLength(); i++) {
			if (!visited[i]
					&& (min_index == -1 || distance[i] < distance[min_index])) {
				min_index = i;
			}
		}

		return min_index;
	}

	@Override
	public int getStartNode() {
		return this.startNode;
	}

	@Override
	public int getEndNode() {
		return this.endNode;
	}

}
