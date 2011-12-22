
package graph;

import java.io.FileNotFoundException;

public class Graph implements IGraph {
	
	private int matrix[][];
	private String names[];
	
	public Graph(String file) throws FileNotFoundException {
		// TODO Aus Datei einlesen
		
		// Beispieldatensatz, Hardcoded
		this.setLength(6);
		this.setDistance(0, 1, 7); // 1 zu 2
		this.setDistance(0, 2, 9); // 1 zu 3
		this.setDistance(0, 5, 14); // 1 zu 3
		this.setDistance(1, 2, 10); // 2 zu 3
		this.setDistance(1, 4, 15); // 2 zu 4
		this.setDistance(2, 3, 11); // 3 zu 4
		this.setDistance(2, 5, 2); // 3 zu 6
		this.setDistance(3, 4, 6); // 4 zu 5
		this.setDistance(5, 5, 9); // 5 zu 6
		names[0]="Gießen";
		names[1]="Frankfurt";
		names[2]="Wallau";
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
		int neighborsTmp[]=new int[matrix.length];
		int j=0;
		for(int i=0; i<matrix.length; i++){
			if(matrix[a][i]>0){
				neighborsTmp[j]=i;
				j++;
			}
		}
		
		int neighbors[]=new int[j];
		for(int i=0; i<neighborsTmp.length; i++){
			neighbors[i]=neighborsTmp[i];
		}

		return neighbors;
	}

	@Override
	public int[] getAllNodes() {
		int node[]=new int[matrix.length];

		for(int i=0; i<matrix.length; i++){
				node[i]=i;
		}
		return node;
	}

	@Override
	public int getLength() {
		return matrix.length;
	}

	@Override
	public String getNodeName(int a) {
		return names[a];
	}

	@Override
	public void setLength(int length) {
		matrix=new int[length][length];
		names=new String[length];
	}

	@Override
	public void setDistance(int a, int b, int dist) {
		matrix[a][b]=dist;
		matrix[b][a]=dist;
	}
	


}
