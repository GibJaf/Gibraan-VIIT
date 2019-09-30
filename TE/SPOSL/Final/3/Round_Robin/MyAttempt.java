import java.util.*;

class process{
    int pid,bt,rem_bt,wt,tat;
    process(int pid,int bt,int rem_bt,int wt,int tat){
        this.pid = pid;
        this.bt = bt;
        this.rem_bt = rem_bt;
        this.wt = wt;
        this.tat = tat;
    }
}


public class MyAttempt{
    int quantum , num ;
    float avg_wt , avg_tat;
    Scanner sc = new Scanner(System.in);
    String sequence ="";
    ArrayList<process> processes = new ArrayList<process>();

    MyAttempt(){
            System.out.print(" Enter time quantum : ");
            quantum = sc.nextInt();
            System.out.print(" Enter number of processes : ");
            num = sc.nextInt();
            int bt;
            for(int i=0;i<num;i++){
                System.out.print(" PROCESS "+(i+1)+" burst time : ");
                bt = sc.nextInt();
                processes.add(new process((i+1),bt,bt,0,0));
            }
    }

    public void findWaitingTime(){
        boolean done;
        Iterator itr;
        int t=0; // time
            do{
                done = true;
                itr = processes.iterator();
                while(itr.hasNext()){
                    process p = (process)itr.next();
                    if(p.rem_bt > 0){
                        done = false;
                        if(p.rem_bt > quantum){
                            t+=quantum;
                            p.rem_bt -=quantum;
                            sequence = sequence.concat("->"+Integer.toString(p.pid));
                        }else{
                            t+=p.rem_bt;
                            p.rem_bt = 0;
                            p.wt = t - p.bt; //very important
                            sequence = sequence.concat("->"+Integer.toString(p.pid));
                        }
                    }

                }
            }while(done == false);
    }


    public void findTurnAroundTime(){
        Iterator itr = processes.iterator();
            while(itr.hasNext()){
                process p = (process)itr.next();
                p.tat = p.wt + p.bt;
            }
    }


    public void findAvgTime(){
        findWaitingTime();
        findTurnAroundTime();
        int total_wt=0 , total_tat=0;
        Iterator itr = processes.iterator();
        int num = processes.size();
        while(itr.hasNext()){
            process p = (process)itr.next();
            total_wt += p.wt;
            total_tat += p.tat;
        }
            avg_wt = (float)total_wt/num;
            avg_tat = (float)total_tat/num;
    }


    public void display(){
        System.out.println("Process \t Waiting_Time \t Burst_Time \t Turn_Around_Time ");
        Iterator itr = processes.iterator();
        while(itr.hasNext()){
            process p = (process)itr.next();
            System.out.println("    "+p.pid+"\t\t    "+p.wt+"\t\t    "+p.bt+"\t\t    "+p.tat);
        }
        System.out.println("Sequence : "+sequence);
        System.out.println(" Average Waiting Time = "+avg_wt);
        System.out.println(" Average Turn Around Time = "+avg_tat);
    }

    public static void main(String args[]){

        MyAttempt obj = new MyAttempt();
        obj.findAvgTime();
        obj.display();
    }
}
