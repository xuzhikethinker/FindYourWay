
package graph;

import java.io.FileNotFoundException;

public class Graph implements IGraph {
	
	private int matrix[][];
	private String names[];
	
	public Graph(String file) throws FileNotFoundException {
		// TODO Aus Datei einlesen
		
		// Beispieldatensatz, Hardcoded
		this.setLength(6);
		this.setDistance(new Node(0), new Node(1), 7); // 1 zu 2
		this.setDistance(new Node(0), new Node(2), 9); // 1 zu 3
		this.setDistance(new Node(0), new Node(5), 14); // 1 zu 3
		this.setDistance(new Node(1), new Node(2), 10); // 2 zu 3
		this.setDistance(new Node(1), new Node(4), 15); // 2 zu 4
		this.setDistance(new Node(2), new Node(3), 11); // 3 zu 4
		this.setDistance(new Node(2), new Node(5), 2); // 3 zu 6
		this.setDistance(new Node(3), new Node(4), 6); // 4 zu 5
		this.setDistance(new Node(5), new Node(5), 9); // 5 zu 6
		names[0]="Gießen";
		names[1]="Frankfurt";
		names[2]="Wallau";
	}

	public Graph(int length) {
		this.setLength(length);
	}

	@Override
	public int getDistance(Node a, Node b) {
		return matrix[a.getIndex()][b.getIndex()];
	}

	@Override
	public Node[] getNeighbors(Node a) {
		Node neighborsTmp[]=new Node[matrix.length];
		int j=0;
		for(int i=0; i<matrix.length; i++){
			if(matrix[a.getIndex()][i]>0){
				neighborsTmp[j]=new Node(i);
				j++;
			}
		}
		
		Node neighbors[]=new Node[j];
		for(int i=0; i<neighborsTmp.length; i++){
			neighbors[i]=neighborsTmp[i];
		}

		return neighbors;
	}

	@Override
	public Node[] getAllNodes() {
		Node node[]=new Node[matrix.length];

		for(int i=0; i<matrix.length; i++){
				node[i]=new Node(i);
		}
		return node;
	}

	@Override
	public int getLength() {
		return matrix.length;
	}

	@Override
	public String getNodeName(Node a) {
		return names[a.getIndex()];
	}

	@Override
	public void setLength(int length) {
		matrix=new int[length][length];
		names=new String[length];
	}

	@Override
	public void setDistance(Node a, Node b, int dist) {
		matrix[a.getIndex()][b.getIndex()]=dist;
		matrix[b.getIndex()][a.getIndex()]=dist;
	}
	


}
