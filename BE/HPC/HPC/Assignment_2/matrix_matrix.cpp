int matrix1[1000][1000], matrix2[1000][1000];

void sequential_matrix_matrix_multiplication() {
    int result[1000][1000];

    memset(result, 0, sizeof(result[0][0]*1e6));
    double start_time = omp_get_wtime();
    for(int i=0;i<10;i++) {
        for(int j=0;j<10;j++) {
            for(int k=0;k<10;k++) {
                result[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        }
    }
    time_seq1 = omp_get_wtime() - start_time;

    memset(result, 0, sizeof(result[0][0]*1e6));
    start_time = omp_get_wtime();
    for(int i=0;i<100;i++) {
        for(int j=0;j<100;j++) {
            for(int k=0;k<100;k++) {
                result[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        }
    }
    time_seq2 = omp_get_wtime() - start_time;

    memset(result, 0, sizeof(result[0][0]*1e6));
    start_time = omp_get_wtime();
    for(int i=0;i<1000;i++) {
        for(int j=0;j<1000;j++) {
            for(int k=0;k<1000;k++) {
                result[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        }
    }
    time_seq3 = omp_get_wtime() - start_time;

    cout<<"\n\nSequential matrix matrix multiplication operation:\n";
    cout<<"\n10: "<<time_seq1<<endl;
    cout<<"100: "<<time_seq2<<endl;
    cout<<"1000: "<<time_seq3<<endl;
}

// TODO
void parallel_matrix_matrix_multiplication(int t) {
    int result[1000][1000];

    memset(result, 0, sizeof(result[0][0])*1e6);
    double start_time = omp_get_wtime();
    #pragma omp parallel for collapse(3) num_threads(t)
    for(int i=0;i<10;i++) {
        for(int j=0;j<10;j++) {
            for(int k=0;k<10;k++) {
                result[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        }
    }
    time_para1 = omp_get_wtime() - start_time;

    memset(result, 0, sizeof(result[0][0])*1e6);
    start_time = omp_get_wtime();
    #pragma omp parallel for collapse(3) num_threads(t)
    for(int i=0;i<100;i++) {
        for(int j=0;j<100;j++) {
            for(int k=0;k<100;k++) {
                result[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        }
    }
    time_para2 = omp_get_wtime() - start_time;

    memset(result, 0, sizeof(result[0][0])*1e6);
    start_time = omp_get_wtime();
    #pragma omp parallel for collapse(3) num_threads(t)
    for(int i=0;i<1000;i++) {
        for(int j=0;j<1000;j++) {
            for(int k=0;k<1000;k++) {
                result[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        }
    }
    time_para3 = omp_get_wtime() - start_time;

    cout<<"\n\nParallel matrix matrix multiplication operation:\n";
    cout<<"\n10: "<<time_para1<<endl;
    cout<<"100: "<<time_para2<<endl;
    cout<<"1000: "<<time_para3<<endl;
}

void matrix_matrix_main() {
    int choice;
    for(int i=0;i<1000;i++) {
        for(int j=0;j<1000;j++) {
            matrix1[i][j] = rand() % 10000;
            matrix2[i][j] = rand() % 10000;
        }
    }

    do {
        cout<<"\n\n*****Menu for matrix matrix multiplication*****\n";
        cout<<"1. Sequential operation\n";
        cout<<"2. Parallel operation\n";
        cout<<"3. Back to main menu\n";
        cout<<"Enter choice: ";
        cin>>choice;
        if(choice == 1) sequential_matrix_matrix_multiplication();
        else if(choice == 2){
            cout<<"\n\nThread num : 2\n";parallel_matrix_matrix_multiplication(2); 
            cout<<"\n\nThread num : 3\n";parallel_matrix_matrix_multiplication(3);
            cout<<"\n\nThread num : 4\n";parallel_matrix_matrix_multiplication(4);
        } 
        else if(choice != 3) cout<<"Please enter valid option.\n";
    } while(choice != 3);
}
