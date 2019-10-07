#include <iostream>
using namespace std;

__global__ void matMul(int *a, int *b, int *c, int n){
	
	int row=blockIdx.y*blockDim.y+threadIdx.y;
	int col=blockIdx.x*blockDim.x+threadIdx.x;
	
	int sum=0;
	
	for(int j=0;j<n;j++)
	{

	
		sum=sum+a[row*n+j]*b[j*n+col];

	}

	c[n*row+col]=sum;
}

int main(){
	
	int n;
	cin>>n;
	int *a= new int[n*n];
	int *b = new int[n*n];
	int *c = new int[n*n];
	
	for(int i=0; i<n; i++){
		for(int j=0; j<n; j++){
			a[i*n+j] = i+1;
			b[i*n+j] = j+1;
		}
	}

	int *ad, *bd, *cd;
	cudaMalloc(&ad, n*n*sizeof(int));
	cudaMalloc(&bd, n*n*sizeof(int));
	cudaMalloc(&cd, n*n*sizeof(int));

	cudaMemcpy(ad, a, n*n*sizeof(int), cudaMemcpyHostToDevice);
	cudaMemcpy(bd, b, n*n*sizeof(int), cudaMemcpyHostToDevice);
	
	dim3 grids(n, n, 1);

	matMul<<<grids, 1>>>(ad, bd, cd, n);

	cudaMemcpy(c, cd, n*n*sizeof(int), cudaMemcpyDeviceToHost);
		
	for(int i=0; i<n; i++){
		for(int j=0; j<n; j++){
			cout<<c[i*n+j]<<" ";
		}

		cout<<endl;
}

}
