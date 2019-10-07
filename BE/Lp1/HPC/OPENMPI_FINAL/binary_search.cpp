#include<cstdio>
#include<mpi.h>
#include<iostream>
#include<chrono>
#include<unistd.h>

#include <time.h>

using namespace std::chrono;
using namespace std;

void binary_search(int a[],int start,int end,int key,int rank)
{
	while(start <= end)
	{
		int m = (start+end)/2;
        
		if(a[m]==key)
		{
			cout<<"The element is found by Process No "<<rank+1<<endl;
			return;
		}
		else if(a[m]<key)
		{
			start=m+1;
		}
		else
		{
			end=m-1;
		}
	}
    cout<<"Not found by Process No : "<<rank+1<<endl;
}

int main(int argc, char **argv)
{

	//cout<<"Hello welcome to MPI World"<<endl;
    int n=8000;
    int key=4500;
    double c[4];
    int a[n];
	for(int i=0;i<n;i++)
	{
        a[i]=i+1;
	}
    
	int rank,blocksize;
	MPI_Init(&argc,&argv);
	MPI_Comm_rank(MPI_COMM_WORLD,&rank);
	MPI_Comm_size(MPI_COMM_WORLD,&blocksize);
	
	blocksize=n/4;

	if(rank==0)
	{
		double start = MPI_Wtime();
    
		binary_search(a,rank*blocksize,(rank+1)*blocksize-1,key,rank);

		double end = MPI_Wtime();

		cout<<"The time for process 1 is "<<(end-start)*1000<<endl<<endl;
		c[rank]=end;

	}

	else if(rank==1)
	{

		double start = MPI_Wtime();
		
		binary_search(a,rank*blocksize,(rank+1)*blocksize-1,key,rank);		

		double end = MPI_Wtime();
	
		cout<<"The time for process 2 is "<<(end-start)*1000<<endl<<endl;
		c[rank]=end;

	}

	else if(rank==2)
	{

		
		double start = MPI_Wtime();
		
		binary_search(a,rank*blocksize,(rank+1)*blocksize-1,key,rank);

		double end = MPI_Wtime();

		cout<<"The time for process 3 is "<<(end-start)*1000<<endl<<endl;
		c[rank]=end;

	}
	else if(rank==3)
	{

		
		double start = MPI_Wtime();
		
		binary_search(a,rank*blocksize,(rank+1)*blocksize-1,key,rank);

		double end = MPI_Wtime();

		cout<<"The time for process 4 is "<<(end-start)*1000<<endl<<endl;
		c[rank]=end;

	}
	
	MPI_Finalize();
}
