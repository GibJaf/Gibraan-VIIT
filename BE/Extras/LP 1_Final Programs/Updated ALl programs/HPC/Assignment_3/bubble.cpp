//Parallel Bubble Sort using Openmp

#include<iostream>
#include<stdlib.h>
#include<omp.h>
#include<time.h>
using namespace std;

double bubble(int *, int);
double serialbubble(int *, int);
void swap(int &, int &);
double bubble(int *a, int n,int valthe)
{
  	double start; 
	start = omp_get_wtime();
	for( int i = 0; i < n; i++ )
	{
		int first = i % 2;
		#pragma omp parallel for shared(a,first) num_threads(valthe)
			for( int j = first; j < n-1; j += 2 )
				{
				if( a[ j ] > a[ j+1 ] )
					{
					swap( a[ j ], a[ j+1 ] );
					}	
				}
	}
	return omp_get_wtime()-start;
}
double serialbubble(int *a, int n)
{
	double start;
	start = omp_get_wtime();
	for( int i = 0; i < n; i++ )
	{
		for( int j = i+1; j < n; j ++ )
		{
			if( a[ j ] > a[ i ] )
			{
				swap( a[ j ], a[ i ] );
			}	
		}
	}
	return omp_get_wtime()-start;
}

void swap(int &a, int &b)
{
	int test;
	test=a;
	a=b;
	b=test;
}

int main()
{
	int n,*a;
	/*cout<<"\nEnter total no of elements:";
	cin>>n;
	a=new int[n];
	cout<<"\nEnter elements:";
	for(int i=0;i<n;i++)
		{
		cin>>a[i];
		}
	*/
	a= new int[10000];
	for(int i = 0; i < 10000; i++)
	{
	a[i]=rand();
	}
	n=10000;
	double a2,a3,a4,s;
	s=serialbubble(a,n);	
	a2=bubble(a,n,2);
	a3=bubble(a,n,3);
	a4=bubble(a,n,4);	
	cout<<"\nSorted array is:\n";
	/*for(int i=0;i<n;i++)
		{
		cout<<a[i]<<endl;
		}*/
	cout<<"serial\t\t | parallel n=2\t | parallel n=3\t | parallel n=4\t |"<<endl;
	
	cout<<s<<"\t | "<<a2<<"\t | "<<a3<<"\t | "<<a4<<"\t | "<<endl;
	return 0;
}

