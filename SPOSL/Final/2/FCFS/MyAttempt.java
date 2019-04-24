// Assumed that all processes arrival_time array
// has arrival times in ascending order.

public class MyAttempt {

    static void findWaitingTime(int n,int at[],int bt[],int wt[]){
        int service_time[] = new int[n];
        service_time[0] = wt[0] = 0;
            for(int i=1 ; i<n ; i++){
                service_time[i] = service_time[i-1] + bt[i-1];
                wt[i] = service_time[i] - at[i];
                if(wt[i]<0)
                    wt[i]=0;
            }
        }



    static void findTurnAroundTime(int n,int bt[],int wt[],int tat[]){
        for(int i=0;i<n;i++)
            tat[i] = wt[i]+bt[i];
    }


    static void findAvgTime(int at[] , int bt[]){
        int n = at.length; // number of processes
        int wt[] = new int[n];
        int tat[] = new int[n];
        int compl_time[] = new int[n];
        int total_wt = 0 , total_tat = 0;

        findWaitingTime(n,at,bt,wt);
        findTurnAroundTime(n,bt,wt,tat);

        System.out.println("PROCESS     ARRIVAL     BURST   WAITING     TURN_AROUND     COMPLETION");
        for(int i=0;i<n;i++){
            total_wt  += wt[i];
            total_tat += tat[i];
            compl_time[i] = tat[i]+at[i];
            System.out.println(" "+(i+1)+"\t\t"+at[i]+"\t  "+bt[i]+"\t  "+wt[i]+"\t\t"+tat[i]+"\t\t"+compl_time[i]);
        }
        System.out.println(" Average waiting time : "+((float)total_wt/n));
        System.out.println(" Average turn around time : "+(total_tat/(float)n));
    }


    public static void main(String[] args) {
        int at[] = {0,3,6};
        int bt[] = {5,9,6};
        findAvgTime(at,bt);
    }
}
