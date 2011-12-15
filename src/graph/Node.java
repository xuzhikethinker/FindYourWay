package graph;

public class Node {
	private int _index;

	public Node(int index) {
		this._index = index;
	}

	public int getIndex() {
		return this._index;
	}

	public String toString() {
		return "" + this._index;
	}

}