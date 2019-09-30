import java.util.*;

class MyAttempt{
    ArrayList<Integer> arr = new ArrayList<Integer>();
    int pg[] = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2 };
    int capacity , hit ;

    MyAttempt(){
        capacity = 4;
    }

    int predict(int index){
        for(int i=0;i<arr.size();i++){
            for
        }
    }

    void optimal(){

        for(int i : pg){

            if(arr.contains(i)){
                hit++;
            }else{
                if(arr.size() < capacity)
                    arr.add(i);
                else{
                    predict(i);
                }

            }
        }
    }

    void display_arr(){
        System.out.println(" Hits = "+hit);
        System.out.print(" Memory contents => ");
        for(int i : arr)
            System.out.print(" , "+i);
        System.out.println();
    }

    public static void main(String[] args) {
        MyAttempt obj = new MyAttempt();
        obj.optimal();
        obj.display_arr();
    }
}
