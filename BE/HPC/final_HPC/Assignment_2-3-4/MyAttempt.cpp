#include "omp.h"
#include<iostream>
#include<unistd.h>

using namespace std;

int main(){
	/*int vect1[10000] , vect2[10000] , result[10000];
	for(int i=0;i<10000;i++){
		vect1[i] = rand() % 1000;
		vect2[i] = rand() % 1000;
		result[i] = 0;
	}
	//memset(result,0,10000);

	double start = omp_get_wtime();
	usleep(5000000);
	#pragma omp parallel for collapse(1) num_threads(3)
	for(int i=0;i<10000;i++)
		result[i] = vect1[i] + vect2[i]; */


	/* MATRIX VECTOR MULTIPLICATION

	int matrix[1000][1000] , vect[1000] , result[1000];
	for(int i=0;i<1000;i++){
		vect[i] = rand()%1000;
		result[i] = 0;
		for(int j=0;j<1000;j++)
			matrix[i][j] = rand()%1000;
	}


	double start = omp_get_wtime();
	#pragma omp parallel for collapse(2) num_threads(4)
	for(int i=0;i<1000;i++){
		for(int j=0;j<1000;j++)
			result[i] += matrix[i][j]*vect[j];
	} */

	int n=100;
	int  matrix1[n][n] , matrix2[n][n] , result[n][n];
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			matrix1[i][j] = rand()%1000;
			matrix2[i][j] = rand()%1000;
			result[i][j] = 0;
		}
	}

	double start = omp_get_wtime();
	//usleep(1000000);
	#pragma omp parallel for collapse(3) num_threads(3)
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			for(int k=0;k<n;k++)
				result[i][j] = matrix1[i][k]*matrix2[k][j];
		}
	}
	double end = omp_get_wtime();

	cout<<" Time => "<<(end-start);
}
