#include<iostream>
#include<stdio.h>
#include<omp.h>
#include<bits/stdc++.h>
using namespace std;

int binary_search(int *a , int n , int key){
	int mid , mid1 , mid2 , low , low1 , low2 , high , high1 , high2;
	int location = -1;
	low = 0; high = n-1;
	mid = (low + high)/2;

	#pragma omp parallel sections
	{
		#pragma omp section
		{
			low1 = low;
			high1 = mid;
			while(low1<=high1){
				//to prevent this thread from running forever if key is not in this range
				if(!(key>=a[low1] && key<=a[high1])){
					low1 = low1 + high1;
					continue; //resort to continue because return not allowed in omp sections
				}
				mid1 = (low1+high1)/2;
				if(a[mid1]==key){
					location = mid1;
					low1 = high1+1;
				}else if(key>mid1)
					low1 = mid1 + 1;
				else
					high1 = mid1 - 1;


			}
		}

		#pragma omp section
		{
			low2 = mid+1;
			high2 = high;
			while(low2 <= high2){
				//if key is not in this range , return because the other section will find the key
				if(!(key>=a[low2] && key<=a[high2])){
					//cout<<"\nkey not in 2nd half";
					low2 = low2 + high2;
					continue;  //resort to continue because return not allowed in omp sections
				}
				mid2 = (low2+high2)/2;
				if(a[mid2] == key){
					location = mid2;
					low2 = high2+1;
					//return mid2;
				}else if(key < a[mid2])
					high2 = mid2-1;
				else if(key > a[mid2])
					low2 = mid2+1;

			}

		}

	}
	return location;

}


int main(){
	int n=10 , key;
	int *a;
        a = new int[n];
	for(int i=0;i<n;i++)
	//	a[i] = i+1;
		a[i] = rand();

	key = 846930886;
	
	for(int i=0;i<n;i++)
		cout<<" "<<a[i];

	sort(a,a+n);
	cout<<endl;
	
	for(int i=0;i<n;i++)
		cout<<" "<<a[i];

	int loc = binary_search(a,n,key);
	if(loc==-1)
		cout<<endl<<"Key = "<<key<<" is not in the array";
	else
		cout<<endl<<"Key = "<<key<<" found at location = "<<(loc+1) ;
	
}
