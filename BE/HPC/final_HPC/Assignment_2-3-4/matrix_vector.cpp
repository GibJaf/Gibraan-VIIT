#include "bits/stdc++.h"
#include "omp.h"
using namespace std;

int matrix[10000][10000];
int vect[10000];
double time_seq1, time_seq2, time_seq3, time_para1, time_para2, time_para3;

// XXX: for matrix vect multiplication
void sequential_matrix_vector_multiplication() {
    int result[10000];

    memset(result, 0, 10000);
    double start_time = omp_get_wtime();
    for(int i=0;i<100;i++) {
        for(int j=0;j<100;j++) {
            result[i] += matrix[j][i] * vect[i];
        }
    }
    time_seq1 = omp_get_wtime() - start_time;

    memset(result, 0, 10000);
    start_time = omp_get_wtime();
    for(int i=0;i<1000;i++) {
        for(int j=0;j<1000;j++) {
            result[i] += matrix[j][i] * vect[i];
        }
    }
    time_seq2 = omp_get_wtime() - start_time;

    memset(result, 0, 10000);
    start_time = omp_get_wtime();
    for(int i=0;i<10000;i++) {
        for(int j=0;j<10000;j++) {
            result[i] += matrix[j][i] * vect[i];
        }
    }
    time_seq3 = omp_get_wtime() - start_time;

    cout<<"\n\nSequential matrix vector multiplication operation:\n";
    cout<<"\n100: "<<time_seq1<<endl;
    cout<<"1000: "<<time_seq2<<endl;
    cout<<"10000: "<<time_seq3<<endl;
}

// XXX: for matrix vect multiplication
void parallel_matrix_vector_multiplication(int t) {
    int result[10000];

    memset(result, 0, 10000);
    double start_time = omp_get_wtime();
    #pragma omp parallel for collapse(2) num_threads(t)
    for(int i=0;i<100;i++) {
        for(int j=0;j<100;j++) {
            result[i] += matrix[j][i] * vect[i];
        }
    }
    time_para1 = omp_get_wtime() - start_time;

    memset(result, 0, 10000);
    start_time = omp_get_wtime();
    #pragma omp parallel for collapse(2) num_threads(t)
    for(int i=0;i<1000;i++) {
        for(int j=0;j<1000;j++) {
            result[i] += matrix[j][i] * vect[i];
        }
    }
    time_para2 = omp_get_wtime() - start_time;

    memset(result, 0, 10000);
    start_time = omp_get_wtime();
    #pragma omp parallel for collapse(2) num_threads(t)
    for(int i=0;i<10000;i++) {
        for(int j=0;j<10000;j++) {
            result[i] += matrix[j][i] * vect[i];
        }
    }
    time_para3 = omp_get_wtime() - start_time;

    cout<<"\nParallel matrix vector multiplication operation:\n";
    cout<<"\n100: "<<time_para1<<endl;
    cout<<"1000: "<<time_para2<<endl;
    cout<<"10000: "<<time_para3<<endl;
}

// TODO: table and hist ogram
void matrix_vector_main() {
    int choice;
    for(int i=0;i<10000;i++) {
        for(int j=0;j<10000;j++) {
            matrix[i][j] = rand() % 100000;
        }
        vect[i] = rand() % 100000;
    }

    do {
        cout<<"\n\n*****Menu for matrix vector multiplication*****\n";
        cout<<"1. Sequential operation\n";
        cout<<"2. Parallel operation\n";
        cout<<"3. Display graph\n";
        cout<<"4. Time analysis\n";
        cout<<"5. Back to main menu\n";
        cout<<"Enter your choice: ";
        cin>>choice;
        if(choice == 1) sequential_matrix_vector_multiplication();
        else if(choice == 2){
            cout<<"\n\nThread num : 2\n";parallel_matrix_vector_multiplication(2); 
            cout<<"\n\nThread num : 3\n";parallel_matrix_vector_multiplication(3);
            cout<<"\n\nThread num : 4\n";parallel_matrix_vector_multiplication(4);
        } 
        // TODO: print histogram comparison
        else if(choice == 3) ;
        // TODO: print table for analysis
        else if(choice == 4) ;
        else if(choice != 5) cout<<"Please enter valid option\n";
    } while(choice != 5);
    cout<<endl;
}
