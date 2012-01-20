package algorithm;

import static org.junit.Assert.*;
import graph.Graph;
import graph.IGraph;

import java.io.FileNotFoundException;

import org.junit.Test;

public class MsAlgorithmTest {

	@Test
	public void testRun() {

		MsAlgorithm msAlgo = new MsAlgorithm();
		DijkstraAlgorithm dkAlgo=new DijkstraAlgorithm();
		
		IGraph graph;
		
		try {
			graph=new Graph("Cities.graph");
			msAlgo.setGraph(graph);
			dkAlgo.setGraph(graph);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
			return;
		}
		
		dkAlgo.setStartNode(3);
		dkAlgo.setEndNode(8);
		dkAlgo.run();

		System.out.print("DkAlgo-Route: - ");
		for (int node : dkAlgo.getResult()) {
			System.out.print(graph.getNodeName(node) + " - ");
		}
		System.out.println();
		System.out.println("DkAlgo-Distanz: " + dkAlgo.getTotalDistance() + " km");
		
		
		msAlgo.setStartNode(3);
		msAlgo.setEndNode(8);
		msAlgo.run();

		System.out.print("MsAlgo-Route: - ");
		for (int node : msAlgo.getResult()) {
			System.out.print(graph.getNodeName(node) + " - ");
		}
		System.out.println();
		System.out.println("MsAlgo-Distanz: " + msAlgo.getTotalDistance() + " km");
		
		assertArrayEquals(dkAlgo.getResult(), msAlgo.getResult());
	}

}
