#include<iostream>
#include<omp.h>

#include "omp.h"

#include<chrono>

using namespace std;
using namespace std::chrono;


void gen(int a[],int b[],int n)
{


	for(int i=0;i<n;i++)
	{

		a[i]=b[i]=n-i;

	}

}

void merge(int a[],int s1,int e1,int s2,int e2)
{

    // e2 - last index of array, s1 - first index of array
	int *temp = new int[e2 - s1 + 1];

	int i1=s1;
	int i2=s2;

	int k=0;
	while(i1<=e1 &&i2<=e2 )
	{

		if(a[i1]<a[i2])
			temp[k++]=a[i1++];
		else
			temp[k++]=a[i2++];

	}

	while(i1<=e1)
		temp[k++]=a[i1++];

	while(i2<=e2)
		temp[k++]=a[i2++];


	for(i1=s1,i2=0;i1<=e2;i1++,i2++)
		a[i1]=temp[i2];



}

void mergeserial(int a[],int low,int high)
{


	if(low<high)

	{

		int middle=(low+high)/2;
		mergeserial(a,low,middle);
		mergeserial(a,middle+1,high);
		merge(a,low,middle,middle+1,high);


	}


}


void mergeparallel(int b[],int low,int high)
{

	if(low<high)

	{

		int middle=(low+high)/2;
	
		#pragma omp parallel sections
		{
			#pragma omp section
			{	

				mergeserial(b,low,middle);

			}
			#pragma omp section
			{
	
				mergeserial(b,middle+1,high);

			}

		}


		merge(b,low,middle,middle+1,high);
	}

}


int main()
{


	cout<<"Enter the no of elements"<<endl;
	int n;
	cin>>n;


    int *a = new int[n];
    int *b =  new int[n];

	gen(a,b,n);


	time_point<system_clock> start,end;

	start=system_clock::now();
	mergeserial(a,0,n-1);
	end=system_clock::now();
	
	duration<double> time=end-start;

	cout<<"The serial time is "<<time.count()*1000<<endl;
    
    

	omp_set_num_threads(2);

	start=system_clock::now();
	mergeparallel(b,0,n-1);
	end=system_clock::now();
		
	time=end-start;
    cout<<"The parallel time is  "<<time.count()*1000<<endl;


}
