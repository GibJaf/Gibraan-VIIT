// Java program for page replacement algorithms
import java.util.ArrayList;

public class LRU {

    // Driver method
    public static void main(String[] args) {
        int capacity = 4;
        int arr[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};

        // To represent set of current pages.We use
        // an Arraylist
        ArrayList<Integer> s=new ArrayList<>(capacity);
        int count=0;
        int page_faults=0;
        for(int i:arr)
        {
            // Insert it into set if not present
            // already which represents page fault
            if(!s.contains(i))
            {

                // Check if the set can hold equal pages
                if(s.size()==capacity)
                {
                    s.remove(0);
                    s.add(capacity-1,i);
                }
                else
                    //s.add(count,i);
                    s.add(i);
                // Increment page faults
                page_faults++;
                //++count;

            }
            else
            {
                // Remove the indexes page
                s.remove((Object)i);
                // insert the current page
                s.add(s.size(),i);
            }

        }
        System.out.println(page_faults);
        for(int i : s)
            System.out.print(" "+i);
        System.out.println();
    }
}
