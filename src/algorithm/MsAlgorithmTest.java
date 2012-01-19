package algorithm;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Arrays;

import graph.Graph;

import org.junit.Test;

public class MsAlgorithmTest {

	@Test
	public void testRun() {

		MsAlgorithm msAlgo = new MsAlgorithm();

		try {
			msAlgo.setGraph(new Graph("Wikipedia-Beispiel.graph"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		msAlgo.setStartNode(0);
		msAlgo.setEndNode(4);
		msAlgo.run();
		
		int result[] = msAlgo.getResult();
		assertTrue(result.length>0);
		System.out.println(Arrays.toString(result));
		System.out.println(msAlgo.getTotalDistance());
	}

}
