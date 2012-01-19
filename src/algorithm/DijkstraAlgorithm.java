package algorithm;

import graph.IGraph;

public class DijkstraAlgorithm implements IAlgorithm {
	private int 	startNode	= -1; 
	private int 	endNode 	= -1;
	private IGraph 	graph;
	private int 	distanz[]; 
	private int 	vorgaenger[];
	private boolean besucht[];
	private int 	weg[];
	private boolean startNodeChanged;

	@Override
	public void setStartNode(int node) throws IllegalArgumentException {
		if(this.graph == null || this.graph.getLength() <= node){
			throw new IllegalArgumentException("Dieser Punkt existiert nicht");
		}
		
		if(this.startNode != node){
			this.startNode = node;
			startNodeChanged = true;
		}
		
	}

	@Override
	public void setEndNode(int node) throws IllegalArgumentException {
		if(this.graph == null || this.graph.getLength() <= node){
			throw new IllegalArgumentException("Dieser Punkt existiert nicht");
		}
		this.endNode = node;
	}

	@Override
	public void run() throws IllegalStateException {
		if (this.startNode == -1 || this.endNode == -1 || this.graph == null) {
			throw new IllegalStateException("Es wurden noch keine Daten gesetzt");
		} else {
			if(startNodeChanged){
				init();
				distanz_update();	
				createShortestPath();
				startNodeChanged = false;
			}else{
				createShortestPath();
			}
			
		}

	}

	@Override
	public int[] getResult() {
		return weg;
	}

	@Override
	public void setGraph(IGraph graph) {
		this.graph = graph;
		
		//Start und End-Knoten noch nicht gesetzt
		this.startNode 	= -1;
		this.endNode 	= -1;
	}

	private void init() {		
		 distanz	= new int[graph.getLength()];
		 vorgaenger	= new int[graph.getLength()];
		 besucht	= new boolean[graph.getLength()];
		 
		 for(int i = 0; i < graph.getLength(); i++){
			 distanz[i]		= -1; // Unendliche Distanz f�r alle Knoten
			 besucht[i]		= false; // Alle Knoten nicht besucht
			 vorgaenger[i]	= -1; // Noch kein Vorgaenger f�r alle Knoten
		 }
		 
		 distanz[startNode] = 0;
		 
	}

	private void distanz_update() {
		int aktKnoten = startNode; //Knoten mit dem begonnen wird
		int[] aktNachbarn; //Nachbarn des zu bearbeitenden Knoten
		
		// F�r alle Knoten im Graph
		for(int i = 0; i < graph.getLength(); i++){
			aktNachbarn = graph.getNeighbors(aktKnoten);
			
			// F�r alle Nachbarn
			for(int j = 0; j < aktNachbarn.length; j++){
				
				// Wenn der benachbarte Knoten noch keine Distanz gesetzt bekommen hat bzw. eine hoehere Distanz hat als die gerade berechnete Distanz
				if(distanz[aktNachbarn[j]] == -1 || distanz[aktNachbarn[j]] > graph.getDistance(aktKnoten, aktNachbarn[j]) + distanz[aktKnoten]){
					
					// Setzt die Distanz des Nachbarn auf die Distanz zwischen aktuellem Knoten und dem Nachbar plus der aktullen Distanz des Knoten
					distanz[aktNachbarn[j]] = distanz[aktKnoten] + graph.getDistance(aktKnoten, aktNachbarn[j]);	
					
					// Der Vorg�nger des Nachbarn wird auf den aktuellen Knoten gesetzt
					vorgaenger[aktNachbarn[j]] = aktKnoten;
				}
					
			}
			
			besucht[aktKnoten] = true; // Setzt den aktuellen Knoten auf besucht
			int max = Integer.MAX_VALUE;
			
			// Fuer alle Nachbarn des aktullen Knoten
			for(int h = 0; h < aktNachbarn.length; h++){ //aktKnoten = Knoten mit der geringsten Distanz
				// Wenn der aktuelle Nachbar die kleinste Distanz hat und noch nicht besucht ist
				 if(distanz[aktNachbarn[h]] < max && besucht[aktNachbarn[h]] == false){
					 max = distanz[aktNachbarn[h]];
					 aktKnoten = aktNachbarn[h];
				 }
			}
			
		}
		
	}
	
	/**
	 * @post weg enthaelt die kuerzeste Strecke
	 */
	private void createShortestPath(){
		int 	aktKnoten 	= endNode;
		int 	c 			= 0;
		int[] 	tmp 		= new int[graph.getLength()];
		
		do{
			tmp[c] = aktKnoten;
			c++;
			aktKnoten = vorgaenger[aktKnoten];
		}while(aktKnoten != -1);
		
		this.weg = new int[c];
		
		for(int i = 0; i < this.weg.length; i++){
			this.weg[(this.weg.length - 1) - i] = tmp[i];
		}
		
	}

	@Override
	public int getStartNode() {
		return this.startNode;
	}

	@Override
	public int getEndNode() {
		return this.endNode;
	}

	@Override
	public int getTotalDistance() {
		return this.distanz[this.endNode];
	}

}
