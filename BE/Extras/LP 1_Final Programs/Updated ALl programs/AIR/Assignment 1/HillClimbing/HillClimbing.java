import java.io.*;
import java.util.*;

class HillClimbing {

	static int startNode, goalNode;
	static ArrayList<Integer> children = new ArrayList<>();
	static ArrayList<Integer> closed = new ArrayList<>();
	static HashMap<Integer, Integer> finalpath = new HashMap<>();
	static int adjacencyMatrix[][];
	static ArrayList<Integer> heuristic = new ArrayList<>();
	static int node, size;

	public static void main(String [] args)throws IOException {

		HillClimbing obj = new HillClimbing();
		obj.initialize();

		int k=0;
    node = startNode;

		while(node != goalNode) {
			// System.out.println("Inside while");
      closed.add(node);
			// System.out.println("Current node = "+node);

		  node = obj.generateBestChild(node);

		}
		if(node == goalNode) {
			System.out.println("\nPath");
			for(k=0;k<closed.size();k++) {
				System.out.print(closed.get(k)+"\t");
			}
			System.out.println(node);
		}
	}

	public int generateBestChild(int node) {
		int min = 999;
		int next_node = 9999;
		for(int i=0; i<size;i++) {
			if(adjacencyMatrix[node-1][i] == 1 && !closed.contains(i+1)) {
				// System.out.println("\nchild = "+(i+1));
				if(min > heuristic.get(i)) {
					min = heuristic.get(i);
					next_node = i+1;
				}
			}
		}
		return next_node;
	}

	public void initialize()throws IOException {
		startNode = 1;
		goalNode = 8;

		Scanner x = new Scanner(System.in);

		System.out.print("\n\nEnter number of nodes : ");
		size = x.nextInt();

		adjacencyMatrix = new int[size][size];

		System.out.print("\nEnter the start node : ");
		startNode = x.nextInt();

		System.out.print("\nEnter the goal node : ");
		goalNode = x.nextInt();

		System.out.println("Enter the adjacency matrix : ");

		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				adjacencyMatrix[i][j] = x.nextInt();
			}
		}

		System.out.println("Enter heurictic values : ");
		for(int i=0;i<size;i++) {
			heuristic.add(x.nextInt());
		}
	}
}
