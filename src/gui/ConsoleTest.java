package gui;

import org.junit.Test;

import algorithm.MsAlgorithm;

public class ConsoleTest {

	@Test
	public void test() {
		
		IUserinterface ui = new Console();
		ui.setAlgorithm(new MsAlgorithm());
		try {
			ui.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
