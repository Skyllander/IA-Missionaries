package ia.missionary;

public class Run {
	
	public static void main(String []args) {
		Tree sTree = new Tree();
		sTree.build();
		System.out.println("Game Over: Estado Ideal encontrado\n");
		System.out.println("Caminho Principal:\n");
		sTree.path();
		System.out.println("Fim");
	}
}
