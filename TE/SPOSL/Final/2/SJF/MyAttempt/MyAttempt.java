/* In SJF if you assume Arrival Time for all processes = 0
    then it is simple Priority scheduling except that here we
    are assigning priority based on burst time .
    If arrival time = 0 for all processes then there is basically no difference
    between preemptive and non-preemptive

    Am making this program for arrival time = 0 for all processes because
    1) Too little time left
    2) College doesn't appreciate good quality code (neither internal or external)*/

import java.util.*;

class process{
    int pid,bt,wt,tat;
    process(int pid,int bt,int wt,int tat){
        this.pid = pid;
        this.bt = bt;
        this.wt = wt;
        this.tat = tat;
    }
}

class SortbyBurst implements Comparator<process>{
    public int compare(process a , process b){
        return a.bt - b.bt ;
    }
}

class SortbyPID implements Comparator<process>{
    public int compare(process a , process b){
        return a.pid - b.pid;
    }
}


class MyAttempt{
    ArrayList<process> processes = new ArrayList<process>();
    int num , total_wt , total_tat;
    String sequence = "" ;
    Scanner sc = new Scanner(System.in);

    MyAttempt(){
        int bt;
        System.out.print(" Enter number of processes : ");
        num = sc.nextInt();
        for(int i=0;i<num;i++){
            System.out.print(" PROCESS "+(i+1)+" Burst Time : ");
            bt = sc.nextInt();
            processes.add(new process((i+1),bt,0,0));
        }
    }


    public void findAvgTime(){
        Collections.sort(processes , new SortbyBurst());
        findWaitingTime();
        findTurnAroundTime();
        Collections.sort(processes , new SortbyPID());
    }


    public void findWaitingTime(){
        processes.get(0).wt = 0;
        for(int i=1;i<processes.size();i++){
            processes.get(i).wt = processes.get(i-1).wt + processes.get(i-1).bt;
            total_wt += processes.get(i).wt;
        }
    }

    public void findTurnAroundTime(){
        Iterator itr = processes.iterator();
        while(itr.hasNext()){
            process p = (process)itr.next();
            p.tat = p.wt + p.bt;
            total_tat += p.tat;
            sequence = sequence.concat(" -> P"+Integer.toString(p.pid));
        }
    }


    public void display(){
        System.out.println("Process \t Waiting_Time \t Burst_Time \t Turn_Around_Time ");
        Iterator itr = processes.iterator();
        while(itr.hasNext()){
            process p = (process)itr.next();
            System.out.println("    "+p.pid+"\t\t    "+p.wt+"\t\t    "+p.bt+"\t\t    "+p.tat);
        }
        System.out.println(" Sequence : "+sequence);
        System.out.println(" Average Waiting Time = "+(float)total_wt/num);
        System.out.println(" Average Turn Around Time = "+(float)total_tat/num);
    }


    public static void main(String args[]){

        MyAttempt obj = new MyAttempt();
        obj.findAvgTime();
        obj.display();
    }

}
