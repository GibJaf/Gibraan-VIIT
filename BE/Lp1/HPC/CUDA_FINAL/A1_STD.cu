#include <iostream>

using namespace std;

__global__ void stdeviation(int *a, float *b, float mean, int n){
	
	int block =blockIdx.x;
	b[0] = 0.0;
	for(int i= block; i<n; i++){
		b[0] += (a[i] - mean)*(a[i] - mean);
	}
	
	b[0] = b[0]/n;

}


int main(){

	int n;
	cin>>n;
	int a[n];
	for(int i=0; i<n; i++){
		a[i] = i+1;
	}
	float mean = (n + 1)/2;
	int *ad;
	float *b;
	
	cudaMalloc(&ad, n*sizeof(int));
	cudaMalloc(&b, sizeof(float));

	cudaMemcpy(ad, a, n*sizeof(int), cudaMemcpyHostToDevice);
	
	stdeviation<<<n, 1>>> (ad, b, mean, n);

	float ans[1];
	cudaMemcpy(ans, b,sizeof(float), cudaMemcpyDeviceToHost);
	cout<<"Answer is: "<<sqrt(ans[0])<<endl;
}
