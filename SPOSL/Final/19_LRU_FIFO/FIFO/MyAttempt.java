import java.util.*;

class MyAttempt{

    public static void main(String[] args) {
        int arr[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};
        int capacity = 4 , fault = 0;
        ArrayList<Integer> s = new ArrayList<Integer>();

        for(int i : arr){

            if(!s.contains(i)){
                if(s.size() == capacity){
                    s.remove(0);
                    s.add(i);
                }else
                    s.add(i);

                    fault++;
            }

        }


        System.out.println(" Page faults : "+fault);
        for(int i : s)
            System.out.print(" "+i);
        System.out.println();
    }
}
