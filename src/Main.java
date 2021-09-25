import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Node[] nodes = new Node[4];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i=0;i<4;i++) {
			Node node = new Node(i);
			nodes[i]=node;
		}
		
		Scanner sc = new Scanner(System.in);
		int paths=0;
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				int path = sc.nextInt();
				if (path==1) {
					paths++;
					nodes[i].connectedNodes.add(nodes[j]);
				}
			}
		}
		
		if (paths!=3*2) {
			System.out.println("No");
			return;
		}
		
		int number = getSubtreeNodes(nodes[0]);
		if (number == 4) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
		
		
	}

	private static int getSubtreeNodes(Node node) {
		// TODO Auto-generated method stub
		ArrayList<Node> current = new ArrayList<Node>();
		ArrayList<Node> next = new ArrayList<>();
		
		current.add(node);
		int count=0;
		while (!current.isEmpty()) {
			for (Node n:current) {
				n.visited=true;
				count++;
				ArrayList<Node> children = n.getChildren();
				if (children==null) return -1;
				next.addAll(children);
			}
			
			current = next;
			next=new ArrayList<>();
		}
		
		return count;
	}
	
	

}

class Node {
	int id;
	Node parent=null;
	boolean visited = false;
	ArrayList<Node> connectedNodes=new ArrayList<>();
	
	public Node(int id) {
		this.id = id;
	}
	
	public ArrayList<Node> getChildren() {
		ArrayList<Node> children = new ArrayList<>();
		
		for (Node child:connectedNodes) {
			if (child!=parent) {
				if (child.visited) {
					return null;
				}
				
				children.add(child);
				child.parent=this;
			}
		}
		return children;
	}
}
