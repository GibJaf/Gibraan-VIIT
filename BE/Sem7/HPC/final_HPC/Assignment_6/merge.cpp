//Parallel Merger Sort
#include<iostream>
#include<stdlib.h>
#include<omp.h>
using namespace std;

double mergesort(int a[],int i,int j,int thrval);
double serialmergesort(int a[],int i,int j);
void merge(int a[],int i1,int j1,int i2,int j2);

double serialmergesort(int a[],int i,int j)
{
int mid;
double start;
if(i<j)
	{
	start = omp_get_wtime();
	mid=(i+j)/2; 
			
			serialmergesort(a,i,mid);
		
			serialmergesort(a,mid+1,j);
			
	
	
	
merge(a,i,mid,mid+1,j);
return omp_get_wtime()-start;
}
}



double mergesort(int a[],int i,int j,int thrval)
{
int mid;
double start;
if(i<j)
	{
	start = omp_get_wtime();	
	mid=(i+j)/2; 	
	#pragma omp parallel sections num_threads(thrval)
	{
		#pragma omp section
			{
			mergesort(a,i,mid,thrval);
			}
		#pragma omp section
			{
			mergesort(a,mid+1,j,thrval);
			}
	}
	
	
merge(a,i,mid,mid+1,j);
return omp_get_wtime()-start;
}
}

void merge(int a[],int i1,int j1,int i2,int j2)
{
	int temp[10000];int i,j,k;
	i=i1;
	j=i2;
	k=0;
	while(i<=j1 && j<=j2)
		{
		if(a[i]<a[j])
			{
			temp[k++]=a[i++];
			}
		else
			{
			temp[k++]=a[j++];
			}
		}
	while(i<=j1)
		{
		temp[k++]=a[i++];
		}
	while(j<=j2)
		{
		temp[k++]=a[j++];
		}
	for(i=i1,j=0;i<=j2;i++,j++)
		{
		a[i]=temp[j];
		}
}

int main()
{
int *a,n,i;
/*
cout<<"\nEnter total no of elements:";
cin>>n;a= new int[n];
cout<<"\nEnter elements:\n";

for(i=0;i<n;i++)
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
	s=serialmergesort(a, 0, n-1);
	a2=mergesort(a, 0, n-1,2);
	a3=mergesort(a, 0, n-1,3);
	a4=mergesort(a, 0, n-1,4);
	cout<<"\nSorted array is:"<<endl;
	cout<<"serial\t\t | parallel n=2\t | parallel n=3\t | parallel n=4\t |"<<endl;
	
	cout<<s<<"\t | "<<a2<<"\t | "<<a3<<"\t | "<<a4<<"\t | "<<endl;
	
return 0; 
}

