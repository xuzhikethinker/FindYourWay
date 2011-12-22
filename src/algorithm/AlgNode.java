package algorithm;

public class AlgNode {
	private int Knoten;
	private int Distanz;
	private int Vorgaenger;
	

	 AlgNode(int node, boolean Startnode) {
		this.Knoten=node;
		this.Vorgaenger = -1;
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
	void setVorgaenger(int node){
		this.Vorgaenger=node;
	}
	int getVorgeaenger(){
		return this.Vorgaenger;
	}
	int getKnoten(){
		return this.Knoten;
	}

}
