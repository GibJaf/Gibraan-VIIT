#include<iostream>
#include<chrono>

using namespace std;
using namespace std::chrono;

__global__ void vecAdd(int *a, int *b, int *c, int n)
{
	int block = blockIdx.x;
	if(block<n)
		c[block] = a[block]+b[block];
}

int main()
{
	int n;
	cin>>n;
	int *a=new int[n];
	int *b=new int[n];
	int *c=new int[n];
	
	for(int i=0;i<n;i++)
	{
		a[i]=i+1;
		b[i]=i+1;
	}
	
	int *ad, *bd, *cd;
	
	cudaMalloc(&ad, n*sizeof(int));
	cudaMemcpy(ad, a, n*sizeof(int), cudaMemcpyHostToDevice);

	cudaMalloc(&bd, n*sizeof(int));
	cudaMemcpy(bd, b, n*sizeof(int), cudaMemcpyHostToDevice);

	cudaMalloc(&cd, n*sizeof(int));
	
	vecAdd<<<n, 1>>>(ad, bd, cd, n);
	
	cudaMemcpy(c, cd, n*sizeof(int), cudaMemcpyDeviceToHost);
	
	for(int i=0;i<n;i++)
		cout<<c[i]<<endl;
}
