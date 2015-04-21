package ia.missionary;

import java.util.ArrayList;

public class Node {
	
	private State state;
	private Node parent;
	private ArrayList<Node> children;
	boolean check;
	
	Node() {
		state = new State();
		parent = null;
		children = new ArrayList<Node>();
		check = false;
	}
	
	Node(Node p, State s) {
		state = s;
		parent = p;
		children = new ArrayList<Node>();
		check = false;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void checked() {
		check = true;
	}
	
	public boolean cState() {
		return check;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}
	
	public void expand() {
		ArrayList<State> nStates = state.genStates();
		for(State s: nStates) {
			children.add(new Node(this, s));
		}
	}

	public State getState() {
		return state;
	}
	
}