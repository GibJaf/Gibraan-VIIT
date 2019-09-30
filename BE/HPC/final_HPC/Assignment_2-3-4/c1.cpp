// TODO:
// 1. add two large vectors
// 2. matrix vector multiplication
// 3. matrix matrix multiplication using n sq processors

#include "matrix_vector.cpp"
#include "vector_addition.cpp"
#include "matrix_matrix.cpp"

// TODO
int main() {
    int choice;
    do {
        cout<<"*******MENU*******\n";
        cout<<"1. Vector addition\n";
        cout<<"2. Matrix vector multiplication\n";
        cout<<"3. Matrix matrix multiplication\n";
        cout<<"4. Exit\n";
        cout<<"Enter choice: ";
        cin>>choice;
        if(choice == 1) vector_addition_main();
        else if(choice == 2) matrix_vector_main();
        else if(choice == 3) matrix_matrix_main();
        else if(choice != 4) cout<<"Enter valid choice.\n";
    } while(choice != 4);
    return 0;
}
