package graph;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTest {

	@Test
	public void testGraph() {
		Graph g=new Graph("Deutschland");
		
		assertEquals(11, g.getDistance(new Node(2), new Node(3)));
		
		g.setDistance(new Node(3), new Node(2), 20);
		
		assertEquals(20, g.getDistance(new Node(2), new Node(3)));
		
		assertEquals("Wallau", g.getNodeName(new Node(2)));
	}

}
