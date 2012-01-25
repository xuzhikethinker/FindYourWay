package algorithm;

import static org.junit.Assert.*;
import graph.Graph;
import graph.IGraph;

import java.io.FileNotFoundException;

import org.junit.Test;

public class _AllTest {

	@Test
	public void testRun() {
		int start 	= 3;
		int end 	= 8;
		long time 	= 0;
		
		
		MsAlgorithm msAlgo = new MsAlgorithm();
		DijkstraAlgorithm dkAlgo=new DijkstraAlgorithm();
		DjAlgo djAlgo = new DjAlgo();
		
		IGraph graph;
		
		try {
			graph=new Graph("Cities.graph");
			msAlgo.setGraph(graph);
			dkAlgo.setGraph(graph);
			djAlgo.setGraph(graph);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
			return;
		}
		
		// DIJKSTSTRAALGORITHM
		dkAlgo.setStartNode(start);
		dkAlgo.setEndNode(end);
		
		time = System.nanoTime();
		dkAlgo.run();
		time = System.nanoTime() - time;

		System.out.print("DkAlgo-Route: - ");
		for (int node : dkAlgo.getResult()) {
			System.out.print(graph.getNodeName(node) + " - ");
		}
		System.out.println();
		System.out.println("DkAlgo-Distanz: " + dkAlgo.getTotalDistance() + " km");
		System.out.println("Zeit: " + time/1000 + "µs");
		
		System.out.println();
		
		// MSALGORITHM
		msAlgo.setStartNode(start);
		msAlgo.setEndNode(end);
		
		time = System.nanoTime();
		msAlgo.run();
		time = System.nanoTime() - time;
		
		System.out.print("MsAlgo-Route: - ");
		for (int node : msAlgo.getResult()) {
			System.out.print(graph.getNodeName(node) + " - ");
		}
		System.out.println();
		System.out.println("MsAlgo-Distanz: " + msAlgo.getTotalDistance() + " km");
		System.out.println("Zeit: " + time/1000 + "µs");
		
		System.out.println();
		
		// DJALGO
		djAlgo.setStartNode(start);
		djAlgo.setEndNode(end);
		
		time = System.nanoTime();
		djAlgo.run();
		time = System.nanoTime() - time;

		System.out.print("DjAlgo-Route: - ");
		for (int node : djAlgo.getResult()) {
			System.out.print(graph.getNodeName(node) + " - ");
		}
		System.out.println();
		System.out.println("DjAlgo-Distanz: " + djAlgo.getTotalDistance() + " km");
		System.out.println("Zeit: " + time/1000 + "µs");
		
		assertArrayEquals(dkAlgo.getResult(), msAlgo.getResult());
		assertArrayEquals(djAlgo.getResult(), msAlgo.getResult());
		assertArrayEquals(djAlgo.getResult(), dkAlgo.getResult());
	}

}
