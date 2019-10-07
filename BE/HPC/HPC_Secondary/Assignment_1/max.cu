#include <cuda.h>
#include <stdio.h>
#include <time.h>
// #define SIZE 1000
#define SIZE 10
__global__ void max(int *a , int *c)
{
int i = threadIdx.x;
// kernel function definition
// initialize i to thread ID
*c = a[0];
if(a[i] > *c)
{
*c = a[i];
}
}
int main()
{

srand(time(NULL)); 
//of the seed
//makes use of the computer's internal clock to control the choice
int a[SIZE]={12,4,7,3,9,5,11,6,1};
int c;
int *dev_a, *dev_c;
//GPU / device parameters
cudaMalloc((void **) &dev_a, SIZE*sizeof(int));
//GPU from CUDA runtime API
cudaMalloc((void **) &dev_c, SIZE*sizeof(int));

//assign memory to parameters on
// input the numbers
cudaMemcpy(dev_a , a, SIZE*sizeof(int),cudaMemcpyHostToDevice);
//array from CPU to GPU
max<<<1,SIZE>>>(dev_a,dev_c);
// call kernel function <<<number of blocks, number of threads
cudaMemcpy(&c, dev_c, SIZE*sizeof(int),cudaMemcpyDeviceToHost);
//result back from GPU to CPU
printf("\nmax = %d ",c);
//copy the
// copy thecudaFree(dev_a);
cudaFree(dev_c);

return 0;
// Free the allocated memory
}
