package ia.missionary;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Tree {
	
	Node root;
	Node end;
	
	Tree() {
		root = new Node();
		end = null;
	}
	
	public void build() {
		ArrayBlockingQueue<Node> queue = new ArrayBlockingQueue<Node>(30000);
		Node n;
		root.checked();
		queue.add(root);
		while(!queue.isEmpty()) {
			n = queue.poll();
			n.expand();
			for(Node k : n.getChildren()) {
				if (!k.cState() && k.getState().isValid()) {
					k.checked();
					queue.add(k);
					if(k.getState().gameOver()){
						queue.clear();
						end = k;
					}
				}
			}
		}
	}
	
	public void path() {
		if (end != null) {
			ArrayList<Node> path = new ArrayList<Node>();
			Node n = end;
			path.add(n);
			while (n.getParent() != null) {
				n = n.getParent();
				path.add(n);
			}
			for(int i = path.size() - 1; i >= 0; --i) {
				path.get(i).getState().printState();
			}
		}
	}

}