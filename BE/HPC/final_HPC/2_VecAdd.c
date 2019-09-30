#include<stdio.h>
#include<omp.h>

int main(){
	int size = 15;
	int v1[size] , v2[size];

	// initialise v1 and v2 arrays
	for(int i=0;i<size;i++){ v1[i]=i+1; v2[size-i-1]=i; }
	
	int v3[size]; //will store result

	#pragma omp parallel num_threads(size)
	{
		int id = omp_get_thread_num();
		v3[id] = v1[id] + v2[id];
	}

	printf("Vector 3 => ");                              	
	for(int i=0 ; i<size ; i++)	printf("%d ",v3[i]);

	return 0;
}
