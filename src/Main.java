import java.util.Scanner;

public class Main {

	static int[][] graph = new int[4][4];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int paths=0;
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				graph[i][j] = sc.nextInt();
				if (graph[i][j]==1)
					paths++;
			}
		}
		
		if (paths!=3*2) {
			System.out.println("No");
			return;
		}
		
		int root = 0;
		int number=getNumberOfAllSubnodes(-1, 0);
		if (number == 4) {
			System.out.println("Yes");
		} 
		else System.out.println("No");
	}
	
	private static int getNumberOfAllSubnodes(int parent, int node) {
		// get node count of all subtrees including node itself.
		int count=0; // initialize count to 0;
		graph[node][node]=1; // mark the node to be visited.
		for (int i=0;i<4;i++) { // scan all other nodes;
			if (i==node) continue; // if i is node itself, do nothing.
			if (i==parent) continue; // if i is parent, do nothing.
			if (graph[node][i]==0) continue; // if i is not connected with node, do nothing.
			if (graph[i][i]==1) { // if i is visited, there must be a ring.
				return -1;
			}
			
			int subnodes=getNumberOfAllSubnodes(node, i); // recursively get number of nodes of i's subtrees.
			if (subnodes==-1) { // if there is a ring in node's subtrees, return there is a ring.
				return -1;
			}
			count+=subnodes; // sum up the subtrees' nodes.
		}
		
		return count+1; // count node itself.
	}

}
