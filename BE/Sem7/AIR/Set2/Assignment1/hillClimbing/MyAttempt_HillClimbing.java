import java.util.*;
public class MyAttempt_HillClimbing{
	public static void main(String args[]){
			int n,i,j,s,g;
	int[][] graph = {{1,1,1,0,0,0,0},
			 {0,1,0,0,1,1,0},
			 {0,0,1,1,1,0,0},
			 {0,0,0,1,1,0,0},
			 {0,0,0,0,1,0,1},
			 {0,0,0,0,0,1,1},
			 {0,0,0,0,0,0,1}};

	int[][] cost =  {{0,4,3,0,0,0,0},
			 {0,0,0,0,12,5,0},
			 {0,0,0,7,10,0,0},
			 {0,0,0,0,2,0,0},
			 {0,0,0,0,0,0,5},
			 {0,0,0,0,0,0,16},
			 {0,0,0,0,0,0,0}};

	// heuristic values
	int[] h = {14,12,11,6,4,11,0};

	s = 0; //start node
	g = 6; //goal  node
	n = 7; // no. of nodes
	int m[] = new int[n];
//	System.out.print("Adjacency matrix => \n");
//	for(i=0;i<7;i++){
//		for(j=0;j<7;j++)
//			System.out.print("	"+graph[i][j]);
//		System.out.println();
//	}


	// Here the real stuff starts
	int total=0,flag=0,flag1=0,total_till_now=0;
	i=0; j=0;
	List<Integer> l = new ArrayList<Integer>();// sequence of nodes from source to destination
	do{
		flag1=0;
		for(int y=0;y<n;y++)	m[y]=100;
		l.add(s);

		// if source == goal
		if(s==g){
			System.out.println(" Size = "+l.size());
			System.out.println("Path for nodes => ");
			for(i=0;i<=l.size()-1;i++){
				System.out.println(l.get(i));
				if(i<l.size()-1)
					total += cost[l.get(i)][l.get(i+1)];
			}
			System.out.println("cost => "+total);
			flag=1;
		}else{ // source != goal
			for(j=0;j<n;j++) // detecting if any node connected to source and closer to goal
				if(graph[s][j]==1 && h[s]>h[j])
					m[j]=h[j];

			// checking if any such node was detected or not
			for(int b=0;b<n;b++) 
				if(m[b]!=100)
					flag1=1;

			if(flag1==1){ // such a node was detected
				//checking for node closest to goal
				/*int min=m[0];
				for(int z=1 ; z<n ; z++)
					if(m[z]<min){
						s=z;
						min=m[z];
					}*/
				//System.out.println("Closest node => "+s+
				int min_cost = Integer.MAX_VALUE , cost_to_consider , closest = s;
				System.out.println("Current node => "+s);	
				for(int z=0;z<n;z++){
					cost_to_consider = total_till_now + cost[s][z] + h[z];
					if((s!=z) && graph[s][z]==1 && cost_to_consider<min_cost){
						min_cost = cost_to_consider;
						closest=z;
						System.out.println("Checking node => "+z+"	Cost_to_consider => "+cost_to_consider);
					}
				}	System.out.println("Closest node => "+closest);	s=closest;

			}else{ // no such node was detected
				System.out.print("Path for nodes => ");
				for(i=0;i<l.size();i++){
					System.out.print("	"+l.get(i));
					total += cost[l.get(i)][l.get(i+1)];  // it is surprising that it is not crashing at this point because of the (i+1)
				}
				System.out.println("Total cost => "+total);
				flag=1;
			}	
		}


	}while(flag==0);
	}
}
