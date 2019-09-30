int vect1[100000], vect2[100000];
// double time_seq1, time_seq2, time_seq3, time_para1, time_para2, time_para3;

// DONE
void sequential_vector_addition() {
    int result[100000];
    // for 1000
    memset(result, 0, 100000);
    double start_time = omp_get_wtime();
    for(int i=0;i<1000;i++) {
        result[i] = vect1[i] + vect2[i];
    }
    time_seq1 = omp_get_wtime() - start_time;

    // for 10_000
    memset(result, 0, 100000);
    start_time = omp_get_wtime();
    for(int i=0;i<10000;i++) {
        result[i] = vect1[i] + vect2[i];
    }
    time_seq2 = omp_get_wtime() - start_time;

    // for 100_000
    memset(result, 0, 100000);
    start_time = omp_get_wtime();
    for(int i=0;i<100000;i++) {
        result[i] = vect1[i] + vect2[i];
    }
    time_seq3 = omp_get_wtime() - start_time;

    cout<<"Sequential vector addition: \n";
    cout<<"1000: "<<time_seq1<<endl;
    cout<<"10000: "<<time_seq2<<endl;
    cout<<"100000: "<<time_seq3<<endl;
}

void parallel_vector_addition(int t) {
    int result[100000];
    // for 1000
    memset(result, 0, 100000);
    double start_time = omp_get_wtime();
    #pragma omp parallel for collapse(1) num_threads(t)
    for(int i=0;i<1000;i++) {
        result[i] = vect1[i] + vect2[i];
    }
    time_para1 = omp_get_wtime() - start_time;

    // for 10_000
    memset(result, 0, 100000);
    start_time = omp_get_wtime();
    #pragma omp parallel for collapse(1) num_threads(t)
    for(int i=0;i<10000;i++) {
        result[i] = vect1[i] + vect2[i];
    }
    time_para2 = omp_get_wtime() - start_time;

    // for 100_000
    memset(result, 0, 100000);
    start_time = omp_get_wtime();
    #pragma omp parallel for collapse(1) num_threads(t)
    for(int i=0;i<100000;i++) {
        result[i] = vect1[i] + vect2[i];
    }
    time_para3 = omp_get_wtime() - start_time;

    cout<<"Parallel vector addition: \n";
    cout<<"1000: "<<time_para1<<endl;
    cout<<"10000: "<<time_para2<<endl;
    cout<<"100000: "<<time_para3<<endl;
}

// TODO: add table and stuff
void vector_addition_main() {
    for(int i=0;i<100000;i++) {
        vect1[i] = rand() % 1000000;
        vect2[i] = rand() % 1000000;
    }

    int choice;
    do {
        cout<<"*****Menu for vector addition*****\n";
        cout<<"1. Sequential addition\n";
        cout<<"2. Parallel addition (Multiple Threads)\n";
        
        cout<<"3. Back to main menu\n";
        cin>>choice;
        if(choice == 1) sequential_vector_addition();
        else if(choice == 2){ 
            cout<<"\n\nThread num : 2\n";parallel_vector_addition(2); 
            cout<<"\n\nThread num : 3\n";parallel_vector_addition(3);
            cout<<"\n\nThread num : 4\n";parallel_vector_addition(4);
        }
        else if(choice != 3) cout<<"Please enter valid choice.\n";
    } while(choice != 3);
}
