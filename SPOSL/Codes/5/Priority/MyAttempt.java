import java.util.*;

public class MyAttempt{
    int pid,at,bt,wt,tat,priority;

    Scanner in = new Scanner(System.in);

    MyAttempt(){
        System.out.println(" Inside MyAttempt() constructor ");
    }

    void input(){
        System.out.println(" Inside input() constructor ");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print(" Enter number of processes : ");
        int n = in.nextInt();

        /*MyAttempt set[] =new MyAttempt[n];

        for(int i=0;i<n;i++){
            set[i] = new MyAttempt();
            set[i].input();
        }*/
        ArrayList<MyAttempt> set = new ArrayList<MyAttempt>(n);
        for(int i=0;i<n;i++){
            set.get(i) = new MyAttempt();
            set.get(i).input();
        }
    }
}
