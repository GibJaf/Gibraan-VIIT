import java.util.*; 
public class bfs
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
    int c[]=new int[n];
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
     System.out.println("Enter the heuristic value for"+i+"between (1-99)");
     h[i]=sc.nextInt();  
    } 
    for(i=0;i<n;i++)
    {
     System.out.println("Enter the cost value for"+i);
     c[i]=sc.nextInt();  
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
     //Collections.sort(ol);
     //cl.add(ol.get(0));
    // System.out.println("s");
     Integer min=ol.get(0);
     Iterator<Integer> itr=ol.iterator();
     
     
    // System.out.println("s1");
     while(itr.hasNext())
      {
      	Integer q=itr.next();
      	if(q < min){
      		min=q;
     // 		System.out.println("s2");
      	}
      }
      s=ol.indexOf(min);
     ol.remove(s);
     ol.add(s,100);
     cl.add(s);
   //  System.out.println("s3");
     if(s==g){
     //System.out.println("s3");
     Iterator<Integer> itr1=cl.iterator();
     	while(itr1.hasNext())
      	{
      		k=itr1.next();		
      	}
      	System.out.println("path node:");
      	path.add(k);
      	while(k!=s1){
      	for(i=0;i<n;i++){
      		if(graph[k][i]==2){
      		k=i;
      		path.add(k);
      	}
      	}
      	}
      	ListIterator<Integer> itr2=path.listIterator();
      	while(itr2.hasNext())
      	{
      		Integer r=itr2.next();
      		System.out.println(r);	
      		total +=c[r];	
      	}
      	System.out.println("cost:"+total);
     	flag=1;
     }
   //  System.out.println("s4");
     for(j=0;j<n;j++){
     	if(graph[s][j]==1){
     	ol.remove(j);
     	ol.add(j,h[j]);
     	}
     }
   //  System.out.println("s5");
  }
  while(flag==0);
} 
}
