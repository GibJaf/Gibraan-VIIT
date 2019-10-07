#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<mpi.h>

void printInorder(int* arr, int start, int end, int my_id) {
    if(start > end) 
        return; 
    printInorder(arr, start*2+1, end, my_id); 
    printf("%d:%d\t ", my_id, arr[start]); 
    printInorder(arr, start*2+2, end, my_id); 
}
void buildTree(int* arr, int start, int end, int *tree, int k, int offset, int N) {
    if(start <= end && k <= N-1) {
        int mid = (end+start+1)/2;
        tree[k] = mid+offset;
        buildTree(arr, start, mid-1, tree, k*2+1, offset, N);
        buildTree(arr, mid+1, end, tree, k*2+2, offset, N);
    }
}

int main(int argc, char const *argv[]) {
    int N = 1024;
    int arr[N], tree[N], slaveArray[N], children;
    MPI_Status status;
    int my_id, num_procs, received, send_data_tag;

    double start,finish;
    MPI_Init(&argc, &argv); 
    start = MPI_Wtime();
    MPI_Comm_rank(MPI_COMM_WORLD, &my_id);
    MPI_Comm_size(MPI_COMM_WORLD, &num_procs);
	children = num_procs;

    if(my_id == 0) {
        for(int i = 0; i<children; i++) {
            int offset = i*N;
            buildTree(arr, 0, N, tree, 0, offset, N);
            MPI_Send(&N, 1, MPI_INT, i, send_data_tag, MPI_COMM_WORLD);
            MPI_Send(&tree, N, MPI_INT, i, send_data_tag, MPI_COMM_WORLD);
        }
    }
    MPI_Recv(&received, 1, MPI_INT, 0, send_data_tag, MPI_COMM_WORLD, &status);
    MPI_Recv(&slaveArray, received, MPI_INT, 0, send_data_tag, MPI_COMM_WORLD, &status);
    printInorder(slaveArray, 0, received-1, my_id);

    finish = MPI_Wtime();
    printf("Execution time = %f seconds\n",(finish-start));
    MPI_Finalize();
}
