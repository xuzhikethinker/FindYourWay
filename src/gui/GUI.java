package gui;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import graph.Graph;
import graph.IGraph;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import org.apache.commons.collections15.Transformer;

import algorithm.IAlgorithm;

public class GUI extends JFrame implements IUserinterface {

	private IAlgorithm algorithm;
	private IGraph graph;

	@Override
	public void setAlgorithm(IAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	public GUI() {
		super("-- FindYourWay - Algorithmus --");
		this.setSize(800, 400);
		this.setLayout(new FlowLayout());
		// this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void run() {

		JPanel panel = new JPanel();

		try {
			this.graph = new Graph("Cities.graph");
			this.algorithm.setGraph(this.graph);
		} catch (FileNotFoundException e) {
			// Datei nicht vorhanden?
		}

		Integer[] nodes = new Integer[this.graph.getLength()];
		for (int i = 0; i < this.graph.getLength(); i++) {
			nodes[i] = new Integer(i);
		}

		class ComboBoxRenderer extends JLabel implements ListCellRenderer {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public ComboBoxRenderer() {
				setOpaque(true);
				setHorizontalAlignment(CENTER);
				setVerticalAlignment(CENTER);
			}

			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				if (isSelected) {
					setBackground(list.getSelectionBackground());
					setForeground(list.getSelectionForeground());
				} else {
					setBackground(list.getBackground());
					setForeground(list.getForeground());
				}

				setText(graph.getNodeName(index));
				return this;
			}
		}

		JComboBox<Integer> startCombo = new JComboBox<Integer>(nodes);
		startCombo.setSelectedIndex(0);
		// startCombo.setRenderer(new ComboBoxRenderer());
		startCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				algorithm.setStartNode((Integer) ((JComboBox<Integer>) e
						.getSource()).getSelectedItem());

			}
		});
		panel.add(startCombo);

		JComboBox<Integer> endCombo = new JComboBox<Integer>(nodes);
		endCombo.setSelectedIndex(0);
		// endCombo.setRenderer(new ComboBoxRenderer());
		endCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				algorithm.setEndNode((Integer) ((JComboBox<Integer>) e
						.getSource()).getSelectedItem());
			}
		});
		panel.add(endCombo);

		JButton runButton = new JButton("Berechnen");
		runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Algorithmus laufen lassen
				try {
					algorithm.run();
					int result[] = algorithm.getResult();
					showGraph();
				} catch (IllegalStateException ex) {
					// Fehlerbehandlung
					System.out.println("Fehler");
				}

			}
		});
		panel.add(runButton);

		this.getContentPane().add(panel);
		this.setVisible(true);
	}

	private void showGraph() {

		DirectedSparseGraph<String, Integer> g = new DirectedSparseGraph<String, Integer>();
		int[] nodes = this.graph.getAllNodes();
		g = new DirectedSparseGraph<String, Integer>();
		int ii = 0;

		// Knoten
		for (int node : nodes) {
			g.addVertex("" + node);
		}

		// Kanten
		for (int i : nodes) {
			for (int j : nodes) {
				if (0 != this.graph.getDistance(i, j)) {
					g.addEdge(ii++, "" + i, "" + j);
				}
			}
		}

		printGraph(g);
	}

	private void printGraph(final DirectedSparseGraph<String, Integer> g) {

		FRLayout frLayout = new FRLayout(g);
		VisualizationViewer vv = new VisualizationViewer(frLayout);
		vv.setForeground(Color.black);
		vv.getRenderer().getVertexLabelRenderer()
				.setPosition(Renderer.VertexLabel.Position.CNTR);
		vv.getRenderContext().setVertexLabelTransformer(new Transformer<String, String>() {
			public String transform(String e) {
				return graph.getNodeName(Integer.parseInt(e));
			}
		});
		vv.getRenderContext().setVertexFillPaintTransformer(
				new MyVertexFillPaintFunction<String>());


//		vv.getRenderContext().setEdgeLabelTransformer(new Transformer<Integer, String>() {
//			public String transform(Integer e) {
//				return "Edge: " + e;
//			}
//		});
		this.getContentPane().add(vv);
		this.pack();
	}

	public class MyVertexFillPaintFunction<String> implements
			Transformer<String, Paint> {

		public Paint transform(String v) {
			int nodeIndex = Integer.parseInt((java.lang.String) v);

			System.out.println(v);
			if (nodeIndex == GUI.this.algorithm.getStartNode())
				return Color.GREEN;
			if (nodeIndex == GUI.this.algorithm.getEndNode())
				return Color.RED;

			for (int node : GUI.this.algorithm.getResult()) {
				if (node == nodeIndex) {
					return Color.YELLOW;
				}
			}

			return Color.WHITE;
		}
	}

}
