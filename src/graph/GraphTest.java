package graph;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class GraphTest {

	@Test
	public void testGraph() {
		Graph g;
		
		try {
			g = new Graph("Deutschland");
		} catch (FileNotFoundException e) {
			fail("Datei nicht vorhanden");
			return;
		}
		assertEquals(11, g.getDistance(2, 3));

		g.setDistance(3, 2, 20);

		assertEquals(20, g.getDistance(2, 3));

		assertEquals("Wallau", g.getNodeName(2));
	}

}
