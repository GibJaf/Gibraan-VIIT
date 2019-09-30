import java.util.*;

class process{
    int pid;
    int at;
    int bt;
    int priority;

    process(){
        System.out.println("Inside process constructor");
        this.pid = this.at = this.bt = this.priority = 0;
    }
}

public class Priority_Algo{
    int n;
    ArrayList<process> set ;
    Scanner in = new Scanner(System.in);

        Priority_Algo(){
            System.out.print(" Enter number of processes : ");
            n = in.nextInt();
            set = new ArrayList<process>(n);
        }


    void init_processes(){
        for(int i=0;i<n;i++){
            System.out.println();
            //init_process(set[i],(i+1));
                    System.out.println(" Process "+(i+1));
                    System.out.println(" at = "+set[i].at);
        }
    }
    void init_process(process pro , int pid){

    }


    void arrange_processes(){
        System.out.println(" From arrange_them() , number of processes = "+set.length);
    }

    public static void main(String args[])throws Exception{
        Priority_Algo obj = new Priority_Algo();
        obj.init_processes();
        obj.arrange_processes();
    }
}
