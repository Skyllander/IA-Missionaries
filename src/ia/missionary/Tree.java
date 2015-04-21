package ia.missionary;

import java.util.concurrent.ArrayBlockingQueue;

public class Tree {
	
	Node root;
	
	Tree() {
		root = new Node();
	}
	
	/*public void build() {
		Node base = root;
		base.expand();
		while(!base.getState().gameOver()) {
			for(Node n: base.getChildren()) {
				n.expand();
			}
			
		}

	}*/
	
	public void build() {
		ArrayBlockingQueue<Node> queue = new ArrayBlockingQueue<Node>(90000);
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
					if(k.getState().gameOver()) queue.clear();
				}
			}
		}
	}
	
	public void checkNode(Node n) {
		if(!n.getState().gameOver() && n.getState().isValid()) {
			n.expand();
			for(Node k: n.getChildren()) {
				checkNode(k);
			}
		}

	}

}