#include<iostream>
#include<stdlib.h>
#include<omp.h>
//#include<time.h>
using namespace std;

//double bubble(int *,int);
//void swap(int & , int &);

double bubble(int *a ,int n, int num_of_threads){
	double start = omp_get_wtime();

	for(int i=0;i<n;i++){
		int first = i%2;
		#pragma omp parallel for shared(a,first) num_threads(num_of_threads)
		//cout<<"Thread num => "<<omp_get_thread_num()<<"    "<<"first value => "<<first<<endl;
		for(int j=first ; j < n-1 ; j+=2){
			if(a[j]>a[j+1])
				swap(a[j],a[j+1]);
		}

	}
	return omp_get_wtime() - start;

}


double serialbubble(int *a , int n){
	double start = omp_get_wtime();
	for(int i=0;i<n-1;i++){
		for(int j=0 ; j<n-i-1 ; j++){
			if(a[j] >a[j+1])
				swap(a[j],a[j+1]);
		}
	}
	return omp_get_wtime() - start;
}

void swap(int &a , int &b){
	int temp = a;
	a = b;
	b = temp;
}
	
void display_array(int *a , int n){
	for(int i=0 ; i<n ; i++)
		cout<<" , "<<a[i];
	cout<<endl;
}

int main(){
	int n=10000;
	int *a = new int[n];
	for(int i=0;i<n;i++)
		a[i] = rand();

	//display_array(a,10);
	//bubble(a,10,5);
	double x = serialbubble(a,n);
	double t2 = bubble(a,n,2); //with 2 threads
	double t3 = bubble(a,n,3); // with 3 threads
	double t4 = bubble(a,n,4); // with 4 threads
	double t5 = bubble(a,n,5); // with 5 threads

	cout<<"serial\t\t | parallel 2 \t | parallel 3 \t | parallel 4 \t | parallel 5"<<endl;
	cout<<x<<"\t | "<<t2<<"\t | "<<t3<<"\t | "<<t4<<"\t | "<<t5<<endl;
	//display_array(a,10);
	return 0;
}
