#include "stdio.h"
#define COLUMNS 5
#define ROWS 5
__global__ void add(int *a, int *b, int *c)
{
int x = blockIdx.x;
int y = blockIdx.y;
int i = (COLUMNS*y) + x;
c[i] = a[i] + b[i];
}
int main()
{
int a[ROWS][COLUMNS], b[ROWS][COLUMNS], c[ROWS][COLUMNS];
int *dev_a, *dev_b, *dev_c;
cudaMalloc((void **) &dev_a, ROWS*COLUMNS*sizeof(int));
cudaMalloc((void **) &dev_b, ROWS*COLUMNS*sizeof(int));
cudaMalloc((void **) &dev_c, ROWS*COLUMNS*sizeof(int));
for (int y = 0; y < ROWS; y++)
// Fill Arrays
for (int x =
0; x < COLUMNS; x++)
{
a[y][x] = x;
b[y][x] = y;
}
cudaMemcpy(dev_a, a, ROWS*COLUMNS*sizeof(int),cudaMemcpyHostToDevice);
cudaMemcpy(dev_b, b, ROWS*COLUMNS*sizeof(int),cudaMemcpyHostToDevice);
dim3 grid(COLUMNS,ROWS);
add<<<grid,1>>>(dev_a, dev_b, dev_c);
cudaMemcpy(c, dev_c, ROWS*COLUMNS*sizeof(int),cudaMemcpyDeviceToHost);
for (int y = 0; y < ROWS; y++)
// Output Arrays
{
for (int x = 0; x < COLUMNS; x++)
{
printf("[%d][%d]=%d ",y,x,c[y][x]);
}
printf("\n");
}
return 0;
}
