// program to perform basic operations on two fuzzy sets

#include <iostream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;

class ordered_pair {
	
	private:
		string var;
		float truth_value;

	public:
		ordered_pair ();
		ordered_pair (string _var, float _truth_value);
		void print_pair ();
		
		float get_truth_value () {
			return truth_value;
		}
		
		string get_var () {
			return var;
		}
};

ordered_pair :: ordered_pair () {

	var = "";
	truth_value = 0.0;
}

ordered_pair :: ordered_pair (string _var, float _truth_value) {

	var = _var;
	truth_value = _truth_value;
}

void ordered_pair :: print_pair () {

	cout << "(" << var << ", " << truth_value << ")";
}

class set {

	private:
		int size;
		vector <ordered_pair *> set_values;

	public:
		set ();
		void set_size (int _size);
		void set_element (ordered_pair *new_pair);
		void print_elements ();
		
		vector <ordered_pair *> get_elements () {
			return set_values;
		}

		~set();
};

set :: set () {

	size = 0;
}

void set :: set_size (int _size) {

	size = _size;
}

void set :: set_element (ordered_pair *new_pair) {

	set_values.push_back(new_pair);
}

void set :: print_elements () {

	cout << "{";
	bool flag = false;
	/*	
	for (ordered_pair *curr_ptr : set_values) {

		if(flag)		
			cout << ", ";
		curr_ptr -> print_pair ();
		flag = true;
	}
	*/

	for (unsigned int i = 0; i < set_values.size(); i++) {

		if(flag)		
			cout << ", ";
		set_values[i] -> print_pair ();
		flag = true;
	}

	cout << "}";
}

set :: ~set () {

	// cout << "Set size = " << set_values.size() << endl;	
	for (int i = set_values.size() - 1; i >= 0; --i) {
		// cout << "Fine till " << i << endl;
		// set_values[i] -> print_pair ();
		delete set_values[i];
	}
}

class fuzzy_calculator {

	private:

	public:
		set *get_union (set *A_ptr, set *B_ptr);
		set *get_intersection (set *A_ptr, set *B_ptr);
		set *get_complement (set *A_ptr);
		set *get_difference (set *A_ptr, set *B_ptr);
		set *get_cartesian_set (set *A_ptr, set *B_ptr);
};

set* fuzzy_calculator :: get_union (set *A_ptr, set *B_ptr) {

	set *result_set = new set;

	// result_set -> set_element ();
	vector <ordered_pair *> A_values = A_ptr -> get_elements();
	vector <ordered_pair *> B_values = B_ptr -> get_elements();

	/*
	cout << "A vector:" << endl;
	for (int i = 0; i < A_values.size(); i ++) {
		
	}*/

	for (int i = 0; i < A_values.size(); i ++) {

		/*
		cout << "A Value at " << i << " = " << A_values[i] -> get_truth_value () << endl;
		cout << "B Value at " << i << " = " << B_values[i] -> get_truth_value () << endl;
		cout << "Min value = " << min(A_values[i] -> get_truth_value (), B_values[i] -> get_truth_value ()) << endl;
		cout << endl;
		*/
		float max_truth_value = max(A_values[i] -> get_truth_value (), B_values[i] -> get_truth_value ());
		ordered_pair *obj_ptr = new ordered_pair ("x" + to_string(i), max_truth_value);
		result_set -> set_element (obj_ptr);
	}

	return result_set;
}

set* fuzzy_calculator :: get_intersection (set *A_ptr, set *B_ptr) {

	set *result_set = new set;

	// result_set -> set_element ();
	vector <ordered_pair *> A_values = A_ptr -> get_elements();
	vector <ordered_pair *> B_values = B_ptr -> get_elements();

	/*
	cout << "A vector:" << endl;
	for (int i = 0; i < A_values.size(); i ++) {
		
	}*/

	for (int i = 0; i < A_values.size(); i ++) {

		/*
		cout << "A Value at " << i << " = " << A_values[i] -> get_truth_value () << endl;
		cout << "B Value at " << i << " = " << B_values[i] -> get_truth_value () << endl;
		cout << "Min value = " << min(A_values[i] -> get_truth_value (), B_values[i] -> get_truth_value ()) << endl;
		cout << endl;
		*/
		float min_truth_value = min(A_values[i] -> get_truth_value (), B_values[i] -> get_truth_value ());
		ordered_pair *obj_ptr = new ordered_pair ("x" + to_string(i), min_truth_value);
		result_set -> set_element (obj_ptr);
	}

	return result_set;
}

set* fuzzy_calculator :: get_complement (set *A_ptr) {

	set *result_set = new set;
	
	vector <ordered_pair *> A_values = A_ptr -> get_elements();
	
	for (int i = 0; i < A_values.size(); i ++) {

		ordered_pair *obj_ptr = new ordered_pair (A_values[i] -> get_var (), 1 - A_values[i] -> get_truth_value ());
		result_set -> set_element (obj_ptr);
	}
	
	return result_set;
}

set* fuzzy_calculator :: get_difference (set *A_ptr, set *B_ptr) {

	set *B_complement_ptr = get_complement (B_ptr);

	return get_intersection (A_ptr, B_complement_ptr);
}

set* fuzzy_calculator :: get_cartesian_set (set *A_ptr, set *B_ptr) {

	set *result_set = new set;

	vector <ordered_pair *> A_values = A_ptr -> get_elements();
	vector <ordered_pair *> B_values = B_ptr -> get_elements();


	for (int i = 0; i < A_values.size(); i ++) {

		for (int j = 0; j < B_values.size(); j ++) {
		
			float min_truth_value = min(A_values[i] -> get_truth_value (), B_values[j] -> get_truth_value ());
			ordered_pair *obj_ptr = new ordered_pair ("(" + A_values[i] -> get_var () + ", " + B_values[j] -> get_var () + ")", min_truth_value);
			result_set -> set_element (obj_ptr);
		}
	}

	return result_set;
}

int main () {

	// cout << "Test 1: Ordered Pair" << endl;
	string var = "x1";
	float truth_value = 0.4;

	ordered_pair *obj_ptr1 = new ordered_pair (var, truth_value);
	// obj_ptr1 -> print_pair();
	// cout << endl;	

	// cout << "Test 2: Set" << endl;

	ordered_pair *obj_ptr2 = new ordered_pair ("x2", 0.5);
	ordered_pair *obj_ptr3 = new ordered_pair ("x3", 0.24); 

	// create set
	set *A_ptr = new set ();
	
	// populate set
	A_ptr -> set_element (obj_ptr1);
	A_ptr -> set_element (obj_ptr2);
	A_ptr -> set_element (obj_ptr3);

	// print set
	cout << endl;
	cout << "Set A:" << endl;
	A_ptr -> print_elements ();
	cout << endl;
	
	cout << endl;

	// cout << "-----------------------------------------------Test over---------------------------------------" << endl;
	// cout << "----------------------------------------------------------------------------------------------" << endl;

	// dummy set B (for testing)
	ordered_pair *obj_ptr4 = new ordered_pair ("y1", 0.1);
	ordered_pair *obj_ptr5 = new ordered_pair ("y2", 0.23);
	ordered_pair *obj_ptr6 = new ordered_pair ("y3", 0.59); 

	// create set
	set *B_ptr = new set ();
	
	// populate set
	B_ptr -> set_element (obj_ptr4);
	B_ptr -> set_element (obj_ptr5);
	B_ptr -> set_element (obj_ptr6);

	// print set
	cout << "Set B:" << endl;
	B_ptr -> print_elements ();
	cout << endl;

	cout << endl;
	
	cout << "--------------------------------------- Calculating and Displaying Results --------------------------------------" << endl;
	
	// create calculator
	fuzzy_calculator *calc_ptr = new fuzzy_calculator;

	// Union
	set *result1_ptr = calc_ptr -> get_union (A_ptr, B_ptr);
	cout << "Printing union:" << endl;
	result1_ptr -> print_elements ();
	cout << endl;
	
	cout << endl;
	
	// Intersection
	set *result2_ptr = calc_ptr -> get_intersection (A_ptr, B_ptr);
	cout << "Printing intersection:" << endl;
	result2_ptr -> print_elements ();
	cout << endl;
	
	cout << endl;
	
	// Complement
	set *result3_ptr = calc_ptr -> get_complement (A_ptr);
	cout << "Printing complement of A:" << endl;
	result3_ptr -> print_elements();
	cout << endl;
	
	cout << endl;
	
	// Difference
	set *result4_ptr = calc_ptr -> get_difference (A_ptr, B_ptr);
	cout << "Printing difference of A and B:" << endl;
	result4_ptr -> print_elements();
	cout << endl;
	
	cout << endl;
	
	// Cartesian set
	set *result5_ptr = calc_ptr -> get_cartesian_set (A_ptr, B_ptr);
	cout << "Printing cartesian product of A and B:" << endl;
	result5_ptr -> print_elements();
	cout << endl;
	
	cout << endl;
	
	// display menu	
//	cout << "Select an operation

	/*
	delete obj_ptr1;
	delete obj_ptr2;
	delete obj_ptr3;
	*/
	
	// Free memory
	delete A_ptr;
	delete B_ptr;
	delete result1_ptr;
	delete result2_ptr;
	delete result3_ptr;
	delete result4_ptr;
	delete result5_ptr;
	delete calc_ptr;

	return 0;
}
