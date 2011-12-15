package algorithm;

import graph.Node;

public class AlgNode {
	private Node Knoten;
	private int Distanz;
	private Node Vorgaenger;
	

	 AlgNode(Node node, boolean Startnode) {
		this.Knoten=node;
		this.Vorgaenger = null;
		if (!Startnode) {
			this.Distanz = -1;
		} else {
			this.Distanz = 0;
		}
		
	}
	void setDistanz(int dis){
		this.Distanz=dis;
	}
	int getDistanz(){
		return this.Distanz;
	}
	void setVorgaenger(Node node){
		this.Vorgaenger=node;
	}
	Node getVorgeaenger(){
		return this.Vorgaenger;
	}
	Node getKnoten(){
		return this.Knoten;
	}

}
