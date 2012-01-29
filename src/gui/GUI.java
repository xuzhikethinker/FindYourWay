package gui;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import graph.Graph;
import graph.IGraph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.collections15.Transformer;

import algorithm.IAlgorithm;
/**
 * @author Marco S.
 */

public class GUI extends JFrame implements IUserinterface {
	private static final long serialVersionUID = 1L;
	
	private IAlgorithm algorithm;
	private IGraph graph;

	private JLabel totalLabel;

	@Override
	public void setAlgorithm(IAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	public GUI() {
		super("-- FindYourWay - Algorithmus --");
		this.setSize(1000, 800);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void run() {

		JPanel northPanel = new JPanel();
		JPanel southPanel = new JPanel();

		do {
			String fileName = JOptionPane
					.showInputDialog("Bitte Dateinamen eingeben");

			if (fileName == null) {
				System.exit(0);
			}
			try {
				this.graph = new Graph(fileName);
				this.algorithm.setGraph(this.graph);
			} catch (FileNotFoundException e) {
				// Datei nicht vorhanden?
			}
		} while (this.graph == null);

		Integer[] nodes = new Integer[this.graph.getLength()];
		for (int i = 0; i < this.graph.getLength(); i++) {
			nodes[i] = new Integer(i);
		}

		class ComboBoxRenderer extends DefaultListCellRenderer {
			private static final long serialVersionUID = 1L;

			public ComboBoxRenderer() {
				super();
				this.setOpaque(true);
			}
			
			public Component getListCellRendererComponent(JList<?> list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);

				if (isSelected) {
					setBackground(list.getSelectionBackground());
					setForeground(list.getSelectionForeground());
				} else {
					setBackground(list.getBackground());
					setForeground(list.getForeground());
				}

				setText(graph.getNodeName(Integer.parseInt(value.toString())));
				setPreferredSize(new Dimension(100, 20));
				return this;
			}
		}

		JLabel startLabel = new JLabel("Start-Punkt: ");
		northPanel.add(startLabel);

		JComboBox<Integer> startCombo = new JComboBox<Integer>(nodes);
		startCombo.setRenderer(new ComboBoxRenderer());
		startCombo.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				algorithm.setStartNode((Integer) ((JComboBox<Integer>) e
						.getSource()).getSelectedItem());

			}
		});
		northPanel.add(startCombo);

		JLabel endLabel = new JLabel("Ziel-Punkt: ");
		northPanel.add(endLabel);

		JComboBox<Integer> endCombo = new JComboBox<Integer>(nodes);
		endCombo.setRenderer(new ComboBoxRenderer());
		endCombo.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				algorithm.setEndNode((Integer) ((JComboBox<Integer>) e
						.getSource()).getSelectedItem());
			}
		});
		northPanel.add(endCombo);

		JButton runButton = new JButton("Berechnen");
		runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Algorithmus laufen lassen
				try {
					algorithm.run();
					// int result[] = algorithm.getResult();
					showGraph();
					totalLabel.setText("Gesamtstrecke: "
							+ algorithm.getTotalDistance() + " km");

				} catch (IllegalStateException ex) {
					// Fehlerbehandlung
					System.out.println("Fehler");
				}

			}
		});
		northPanel.add(runButton);

		totalLabel = new JLabel("Gesamtstrecke: - km");
		southPanel.add(totalLabel);

		this.add(northPanel, BorderLayout.NORTH);
		this.add(southPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	private void showGraph() {

		DirectedSparseGraph<Integer, Integer> g = new DirectedSparseGraph<Integer, Integer>();
		g = new DirectedSparseGraph<Integer, Integer>();
		int ii = 0;

		int[] nodes = this.graph.getAllNodes();

		// Knoten
		for (int node : nodes) {
			g.addVertex(node);
		}

		// Kanten
		for (int i : nodes) {
			for (int j : nodes) {
				if (i < j && 0 != this.graph.getDistance(i, j)) {
					g.addEdge(ii++, i, j);
				}
			}
		}

		printGraph(g);
	}

	private void printGraph(final DirectedSparseGraph<Integer, Integer> g) {

		FRLayout<Integer, Integer> frLayout = new FRLayout<Integer, Integer>(g);
		VisualizationViewer<Integer, Integer> vv = new VisualizationViewer<Integer, Integer>(
				frLayout);
		vv.setForeground(Color.black);
		vv.getRenderer().getVertexLabelRenderer()
				.setPosition(Renderer.VertexLabel.Position.CNTR);

		// Knoten-Bezeichnung
		vv.getRenderContext().setVertexLabelTransformer(
				new Transformer<Integer, String>() {
					public String transform(Integer e) {
						return graph.getNodeName(e);
					}
				});

		// Knoten-Farbe
		vv.getRenderContext().setVertexFillPaintTransformer(
				new Transformer<Integer, Paint>() {

					public Paint transform(Integer nodeIndex) {
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
				});

		// Kanten-Bezeichnung
		vv.getRenderContext().setEdgeLabelTransformer(
				new Transformer<Integer, String>() {
					public String transform(Integer e) {
						int[] nodes = graph.getAllNodes();
						int ii = 0;
						for (int i : nodes) {
							for (int j : nodes) {
								if (i < j && 0 != graph.getDistance(i, j)) {
									if (e == ii) {
										return "" + graph.getDistance(i, j)
												+ " km";
									}
									ii++;
								}
							}
						}
						return "";
					}
				});

		// In Layout einbinden
		JPanel centerPanel = new JPanel();
		centerPanel.add(vv);

		this.add(centerPanel, BorderLayout.CENTER);
		this.setVisible(true);
		// this.pack();
	}

}
