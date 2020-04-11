import java.util.*; 
public class astar
 { 
   public static void main(String[] args) 
   {
    		int n,i,j; 
    		int s,g,z,x;
    Scanner sc=new Scanner(System.in); 
    System.out.println("Enter number of nodes in graph"); 
    
    n=sc.nextInt(); 
    int[][] graph=new int[n][n]; 
    int h[]=new int[n];
    int c[][]=new int[n][n];
    for(i=0;i<n;i++)
     
   	 for(j=0;j<n;j++) {
   	 	graph[i][j]=0; 
   	 	c[i][j]=0;
   	 	}
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
     System.out.println("Enter the heuristic value for "+i+" between (1-99)");
     h[i]=sc.nextInt();
     
    } 
    for(i=0;i<n;i++)
    {
    	for(j=0;j<n;j++){
    	if(graph[i][j]==1){
     System.out.println("Enter the cost value for "+i+" and "+j);
     c[i][j]=sc.nextInt(); 
     c[i][j] +=h[j]; 
     }
    } 
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
       System.out.println("The cost matrix is:");
    for(i=0;i<n;i++)
       { for(j=0;j<n;j++)
        {
          System.out.print(c[i][j]+ "\t");
        }
        System.out.println();
       }
       int s1=s;
        Integer k=0;
   i=0;
   int flag=0;
   int flag1=0;
   int total=0;
List<Integer> ol=new ArrayList<Integer>();
List<Integer> cl=new ArrayList<Integer>();
List<Integer> path=new ArrayList<Integer>();
for(i=0;i<n;i++){
ol.add(100);
}
ol.remove(s);
ol.add(s,h[s]);
   do
   { 
     
     Integer min=ol.get(0);
     Iterator<Integer> itr=ol.iterator();
     while(itr.hasNext())
      {
      	Integer q=itr.next();
      	if(q < min){
      		min=q;
      	}
      }
      s=ol.indexOf(min);
     ol.remove(s);
     ol.add(s,100);
     cl.add(s);
     if(cl.size()!=1){
     int par=cl.size()-2;
     int par1=cl.get(par);
     for(j=0;j<n;j++){
     	if(graph[s][j]==2){
     	if(j==par1){
     	continue;
     	}
     	else{
     	graph[s][j]=0;
     	}
     }
     }
     }
     if(s==g){
     Iterator<Integer> itr1=cl.iterator();
     	while(itr1.hasNext())
      	{
      		k=itr1.next();		
      	}
      	System.out.println("path node:");
      	path.add(k);
      	int a=cl.size() - 2;
      	int a1=cl.get(a);
      	path.add(a1);
      	while(a1!=s1){
      	for(i=0;i<n;i++){
      		if(graph[a1][i]==2){
      		a1=i;
      		path.add(a1);
      	}
      	}
      	}
      	for(i=path.size()-1;i>=0;i--)
      	{
      		System.out.println(path.get(i));
      		if(i!=path.size()-1){
      		total +=c[path.get(i+1)][path.get(i)];	
      		}
      	}
      	System.out.println("cost:"+total);
     	flag=1;
     }
     for(j=0;j<n;j++){
     	if(graph[s][j]==1){
     	int var=ol.get(j);
     	if(var>c[s][j]){
     	ol.remove(j);
     	ol.add(j,c[s][j]);
     	}
     	}
     }
  }
  while(flag==0);
} 
}
