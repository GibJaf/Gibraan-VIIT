import java.io.*;
import java.util.*;

class AStar {

	static int startNode, goalNode;
	static ArrayList<Integer> open = new ArrayList<>();
	static ArrayList<Integer> closed = new ArrayList<>();
	static Map<Integer, Integer> parentMapping = new HashMap<>();
	static ArrayList<Integer> path = new ArrayList<>();
	static int adjacencyMatrix[][] = new int [8][8];
	static ArrayList<Integer> g=new ArrayList<>();
	static ArrayList<Integer> heuristic = new ArrayList<>();
	static int node, index, parent=0, size;

	public static void main(String [] args)throws IOException {

		AStar obj = new AStar();
		obj.initialize();

		open.add(startNode);
		g.add(0);
		int k=0;

		while(!open.isEmpty()) {
			obj.sort(open);
			// System.out.println("current g");
			// for(int i=0; i<g.size();i++) {
			// 	System.out.print(g.get(i)+"\t");
			// }
			// System.out.println("\n");

			node = open.get(0);
			if(node == goalNode) {

				path.add(0,node);
				path.add(0,parent);
				System.out.println("Path:");

				while(parent != 0) {
					for(Object entry: parentMapping.keySet()){
            if(parent == parentMapping.get(entry)){
                parent = (Integer)entry;
								path.add(0,parent);
                break; //breaking because its one to one map
            }
				}
			}
				for(int i=1;i<path.size();i++) {
					System.out.print(path.get(i)+"\t");
				}
				System.out.println("");
				break;
			} else {
				closed.add(node);
				parentMapping.put(parent, node);
				parent = node;
				open.remove(0);
				obj.generateChildren(node);
			}
		}
	}

	public void generateChildren(int node) {
		int temp = 0;
		for(int i=0; i<size;i++) {
			if(adjacencyMatrix[node-1][i] != 0 && !(open.contains(i+1)||closed.contains(i+1))) {
				// System.out.println("\nchild = "+(i+1));
				temp = g.get(node-1) + adjacencyMatrix[node-1][i];
				g.add(temp);
				open.add(i+1);
			}
		}
	}

	public void sort(ArrayList<Integer> open) {

		// System.out.println("inside sort");
		int min = 0, temp = 0;
		int openHeur[] = new int[open.size()];
		int f[] = new int[open.size()];

		for(int i=0; i<open.size(); i++) {
			openHeur[i] = heuristic.get(open.get(i)-1);
			f[i] = openHeur[i] + g.get(open.get(i)-1);
			// System.out.println(openHeur[i]);
		}

		int n;

		for (int i = 0; i < open.size()-1; i++) {
			// System.out.println("i = "+i);
			for (int j = 0; j < open.size()-1-i; j++) {
				// System.out.println("j = "+j);
				// System.out.println("max = "+(open.size()-1-i));
				if (f[j] > f[j+1]) {
					temp = f[j];
          f[j] = f[j+1];
          f[j+1] = temp;
					temp = open.get(j);
					n = open.get(j+1);
					open.remove(j+1);
					open.remove(j);
					open.add(j,n);
					open.add(j+1,temp);
					// System.out.println("Swapped");
        }
      }
		}
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
