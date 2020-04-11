import java.util.*; 
public class hillClimbing
 { 
   public static void main(String[] args) 
   {
    		int n,i,j; 
    		int s,g;
    Scanner sc=new Scanner(System.in); 
    System.out.println("Enter number of nodes in graph"); 
    
    n=sc.nextInt(); 
    int[][] graph=new int[n][n]; 
    int h[]=new int[n];
    
    for(i=0;i<n;i++)
     
   	 for(j=0;j<n;j++) 
   	 	graph[i][j]=0; 
    		int m[]=new int[n];
    for(i=0;i<n;i++) 
    		m[i]=0;
    for(i=0;i<n;i++) 
    { 
      for(j=0;j<n;j++)
       {
         System.out.println("Is "+i+" is connected to "+ j);
         graph[i][j]=sc.nextInt();
       } 
    }
    for(i=0;i<n;i++)
    {
     System.out.println("Enter the heuristic value for(1-99)"+i);
     h[i]=sc.nextInt();  
    } 
    System.out.println("Enter start nodes in graph"); 
    s=sc.nextInt(); 
    System.out.println("Enter goal nodes in graph"); 
    g=sc.nextInt(); 
     System.out.println("The adjacency matrix is:");
    for(i=0;i<n;i++)
       { for(j=0;j<n;j++)
        {
          System.out.print(graph[i][j]+ "\t");
        }
        System.out.println();
       }
        int k=0;
   i=0;
   int flag=0;
   int flag1=0;
List<Integer> l=new ArrayList<Integer>();
   do
   { 
   flag1=0;
   for(int y=0;y<n;y++) 
     m[y]=100;
     
     l.add(s);
   if(s==g)
   {
	 System.out.println("Path for nodes: ");
	ListIterator<Integer> itr=l.listIterator();
     while(itr.hasNext())
      {
      System.out.println(itr.next());
      }
     // System.out.println(g);
     flag=1;
   }
   else
   { 

     for(j=0;j<n;j++)
     {
      if(graph[s][j]==1 && h[s]>h[j])
      {
        m[j]=h[j];
   	}
     }
    		for(int b=0;b<n;b++){
    			if(m[b]!=100){
    			flag1=1;
    			}
    		}
    		if(flag1==1){
    		int min = m[0];
		//System.out.println(s);
		for( int z = 1; z < n ;z++)
		{
			if(m[z] < min)
			{
				s = z;
				//System.out.println(s);
				min=m[z];
			}
		}
		}
		else
		{
			System.out.println("Path for nodes: ");
			ListIterator<Integer> itr1=l.listIterator();
			while(itr1.hasNext())
		        {
		          System.out.println(itr1.next());
		        }
     			flag=1;
	        }		
  }
  }
  while(flag==0);
} 
}
