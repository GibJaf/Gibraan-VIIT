/* This is Non Pre-emptive */
/* Improvements :
Here assumed that arrival time for all is 0
Improve such that accept arrival time and calculate accordingly*/
import java.util.*;

public class MyAttempt{
    int pid,at,bt,wt,tat,priority;

    Scanner in = new Scanner(System.in);

    MyAttempt(int pid){
            this.pid=pid;
            System.out.println(" PROCESS "+(pid));
            this.at=0;
            System.out.print(" Burst time : ");
            this.bt = in.nextInt();
            System.out.print(" Priority    :");
            this.priority = in.nextInt();
            System.out.println();
    }

    static void output(MyAttempt set[] , int num){
        for(int i=0;i<num;i++){
            System.out.println(" Process "+set[i].pid);
            System.out.println(" Burst time = "+set[i].bt);
            System.out.println(" Priority   = "+set[i].priority);
            System.out.println();
        }

    }

    static void sort(MyAttempt set[] , int num){
        MyAttempt temp;
        for(int i=0;i<num;i++){
            for(int j=i+1;j<num;j++){
                if(set[i].priority > set[j].priority){
                    temp = set[i];
                    set[i] = set[j];
                    set[j] = temp;
                }
            }
        }
    }


    static void cal_time(MyAttempt set[] , int num){

        // Wait Time
        int wait = 0 , sum = 0;
        for(int i=0;i<num;i++){
            set[i].wt = wait;
            sum += wait;
            wait+= set[i].bt; //increment count by burst time of current process
            System.out.println(" PROCESS "+set[i].pid+"  wait_time = "+set[i].wt);
        }
        System.out.println("Total wait time = "+sum);
        System.out.println("Average wait time = "+((float)sum/num));

        // Turn Around Time
        int total = 0;
        for(int i=0;i<num;i++){
            set[i].tat = set[i].wt + set[i].bt;
            total += set[i].tat;
            System.out.println(" PROCESS "+set[i].pid+"  turn_around_time = "+set[i].tat);
        }
        System.out.println("Total turn around time = "+total);
        System.out.println("Average turn around time = "+((float)total/num));
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print(" Enter number of processes : ");
        int n = in.nextInt();

        MyAttempt set[] =new MyAttempt[n];
        for(int i=0;i<n;i++){
            set[i] = new MyAttempt(i+1);
        }

        System.out.println("-------------------");
        MyAttempt.output(set , n);
        MyAttempt.sort(set , n);
        System.out.println("-------------------");
        MyAttempt.output(set , n);

        MyAttempt.cal_time(set , n);
    }
}
