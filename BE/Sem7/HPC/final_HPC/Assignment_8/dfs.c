#include<stdio.h>
#include<omp.h>
#include<unistd.h>
#include<stdlib.h>
#define SIZE 20000
/*int stack[SIZE];
int *sp;
#define push(sp, n) (*((sp)++) = (n))
#define pop(sp) (*--(sp))*/

void DFSearch();
    int a[SIZE][SIZE],reach[SIZE],n;
    void dfs(int v) {
      //  printf("Visited %d \n",v);
    	int i;
    	reach[v]=1;
    	for (i=1;i<=n;i++)
    	  if(a[v][i] && !reach[i]) {
    		dfs(i);
    	}
    }
    void main() {
   
        //sp = stack; /* initialize */
        //push(sp, 10);
        //int x = pop(sp);
       // printf("\n%d",x);
    	
    	int i,j,count=0;
    	printf("\n Enter number of vertices:");
    	//scanf("%d",&n);
    	n=SIZE;
    	for (i=1;i<=n;i++) {
    		reach[i]=0;
    		for (j=1;j<=n;j++)
    		   a[i][j]=0;
    	}
    	printf("\n Enter the adjacency matrix:\n");
    	for (i=1;i<=n;i++)
    	  for (j=1;j<=i;j++){
    	    a[i][j]=(rand()%2);
    	    a[j][i]=a[i][j];
    	    }
    	    
    	for (i=1;i<=n;i++){
    	  for (j=1;j<=n;j++){
    	        //printf("%d ",a[i][j]);
    	    }  
           // printf("\n");    	    
    	   }   
    	   //scanf("%d",&a[i][j]);
    	   double start = omp_get_wtime(); 
    	   dfs(1);
           double end = omp_get_wtime();   
           double elapsed=end-start;
           printf("\nTime requried SERIAL is %f \n",elapsed); 
           //printf("\n");
    	    for (i=1;i<=n;i++) {
    	    	if(reach[i])
    	    	   count++;
            	}
            //	    	    printf("%d",count);
    	if(count==n)
    	  printf("Graph is connected Serial \n"); else
    	  printf("Graph is not connected Serial \n");
    	  
          //  printf("\n\n\n\n\n::::::::::PARALLEL STUFF::::::::\n");
           start = omp_get_wtime(); 
    	   DFSearch(1);
           end = omp_get_wtime();   
           elapsed=end-start;
           printf("\nTime requried PARALLEL is %f \n",elapsed); 
    
    	//printf("\n");
    	count=0;
    	for (i=1;i<=n;i++) {
    		if(reach[i])
    		   count++;
    	}
    	
    	    //printf("%d",count);
    	if(count==n)
    	  printf("Graph is connected Parallel\n"); else
    	  printf("Graph is not connected Parallel\n");
    }  
    
    

void visit(int k)
{
  int i, iWillVisitK = 0;
#pragma omp critical
  if (!reach[k]) {
    iWillVisitK = 1;
    reach[k] = 1;
    //printf("Visted %d\n",k);
   // for (i = 1; i <= n; i++){printf("%d\n",a[k][i]);}
  }

  if (iWillVisitK) {    
     for (i = 1; i <= n; i++){
    if (a[k][i] && !reach[i]) {
    
        #pragma omp task
          visit(i);
        }
      }
    }
} 	  
    	  
void DFSearch()
{
  int k;
#pragma omp parallel
{
#pragma omp for
  for (k = 1; k <= n; k++)
    reach[k] = 0;
#pragma omp single
  visit(1);  // start at root node with index 0
} // end parallel
}

