package algorithm;

import graph.IGraph;

public class DijkstraAlgorithm implements IAlgorithm {
	private int 	startNode;
	private int 	endNode;
	private IGraph 	graph;
	private int 	distanz[]; 
	private int 	vorgaenger[];
	private boolean besucht[];
	private int 	weg[];
	private boolean startNodeChanged;
	private int		Koten[];
	
	{
		this.startNode 	= -1;
		this.endNode	= -1;
	}
	
	@Override
	public void setStartNode(int node) throws IllegalArgumentException {
		if(this.graph == null || this.graph.getLength() <= node){
			throw new IllegalArgumentException("Dieser Punkt existiert nicht");
		}
		
		if(this.startNode != node){
			this.startNode = node;
			this.startNodeChanged = true;
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
			if(this.startNodeChanged){
				init();
				distanz_update();	
				createShortestPath();
				this.startNodeChanged = false;
			}else{
				createShortestPath();
			}
			
		}

	}

	@Override
	public int[] getResult() {
		return this.weg;
	}

	@Override
	public void setGraph(IGraph graph) {
		this.graph = graph;
		
		//Start und End-Knoten noch nicht gesetzt
		this.startNode 	= -1;
		this.endNode 	= -1;
	}

	private void init() {		
		 this.distanz		= new int[graph.getLength()];
		 this.vorgaenger	= new int[graph.getLength()];
		 this.besucht		= new boolean[graph.getLength()];
		 this.Koten			= this.graph.getAllNodes();
		 
		 for(int i = 0; i < graph.getLength(); i++){
			 this.distanz[i]		= -1; // Unendliche Distanz f�r alle Knoten
			 this.besucht[i]		= false; // Alle Knoten nicht besucht
			 this.vorgaenger[i]		= -1; // Noch kein Vorgaenger f�r alle Knoten
		 }
		 
		 this.distanz[this.startNode] = 0;
		 
	}

	private void distanz_update() {
		int aktKnoten = this.startNode; //Knoten mit dem begonnen wird
		int[] aktNachbarn; //Nachbarn des zu bearbeitenden Knoten
		
		// F�r alle Knoten im Graph
		for(int i = 0; i < this.graph.getLength(); i++){
			aktNachbarn = this.graph.getNeighbors(aktKnoten);
			
			// F�r alle Nachbarn
			for(int j = 0; j < aktNachbarn.length; j++){
				
				// Wenn der benachbarte Knoten noch keine Distanz gesetzt bekommen hat bzw. eine hoehere Distanz hat als die gerade berechnete Distanz
				if(this.distanz[aktNachbarn[j]] == -1 || this.distanz[aktNachbarn[j]] > this.graph.getDistance(aktKnoten, aktNachbarn[j]) + this.distanz[aktKnoten]){
					
					// Setzt die Distanz des Nachbarn auf die Distanz zwischen aktuellem Knoten und dem Nachbar plus der aktullen Distanz des Knoten
					this.distanz[aktNachbarn[j]] = this.distanz[aktKnoten] + this.graph.getDistance(aktKnoten, aktNachbarn[j]);	
					
					// Der Vorg�nger des Nachbarn wird auf den aktuellen Knoten gesetzt
					this.vorgaenger[aktNachbarn[j]] = aktKnoten;
				}
					
			}
			
			this.besucht[aktKnoten] = true; // Setzt den aktuellen Knoten auf besucht
			int max = Integer.MAX_VALUE;
			
			for(int h = 0; h < this.Koten.length; h++){
				 if(this.distanz[this.Koten[h]] < max && !this.besucht[this.Koten[h]] && this.distanz[this.Koten[h]] != -1){	
					max		 	= this.distanz[this.Koten[h]];
					aktKnoten 	= this.Koten[h];
				 }
			}
			
		}
		
	}
	
	/**
	 * @post weg enthaelt die kuerzeste Strecke
	 */
	private void createShortestPath(){
		int 	aktKnoten 	= this.endNode;
		int 	c 			= 0;
		int[] 	tmp 		= new int[this.graph.getLength()];
		
		do{
			tmp[c] = aktKnoten;
			c++;
			aktKnoten = this.vorgaenger[aktKnoten];
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
