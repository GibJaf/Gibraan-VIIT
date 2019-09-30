import java.util.*;

class MyAttempt{
    int processes , resources ;
    Scanner sc = new Scanner(System.in);
    int Need[][] , Allocation[][] , Max[][] , Available[] ;
    int SafeSequence[] ;

    MyAttempt(){
       // System.out.print(" Enter number of processes : ");
        processes = 5; //sc.nextInt();
       // System.out.print(" Enter number of resources : ");
        resources = 3; //sc.nextInt();
        SafeSequence = new int[processes];
        Need = new int[processes][resources];
        Allocation = new int[][] { { 0, 1, 0 }, //P0
                  { 2, 0, 0 }, //P1
                  { 3, 0, 2 }, //P2
                  { 2, 1, 1 }, //P3
                  { 0, 0, 2 } }; //P4

        Max = new int[][] { { 7, 5, 3 }, //P0
             { 3, 2, 2 }, //P1
             { 9, 0, 2 }, //P2
             { 2, 2, 2 }, //P3
             { 4, 3, 3 } }; //P4

        Available = new int[] { 3, 3, 2 };
    }

    void Safety_Check(){
        int count=0;

        boolean visited[] = new boolean[processes];
        for(int i=0;i<processes;i++)
            visited[i] = false;

        int work[] = new int[resources];
        for(int i=0;i<resources;i++)
            work[i] = Available[i];


        while(count<processes){
            boolean flag = false;//to check if any process was executed in a certain iteration.
                                // If none then this is used to exit while loop.

            for(int i=0;i<processes;i++){
                if(visited[i] == false){
                    //System.out.println(" Checking process "+(i+1));
                    int j; //declaring j here is required cuz it's value is used by next if()
                    for(j=0;j<resources;j++){
                        if(Need[i][j] > work[j])
                            break;
                    }

                    if(j == resources){
                        flag = true; //atleast one process was executed in this iteration of while loop.
                        visited[i] = true;
                        SafeSequence[count++] = i;
                        //System.out.println(" Process "+(i+1)+" is FEASIBLE ");

                        for(int k=0;k<resources;k++)
                            work[k] += Allocation[i][k];
                    }//else
                        //System.out.println(" Process "+(i)+" is NOT FEASIBLE ");

                }
            }

            if(flag == false)
                break;
        }

        if(count<processes)
            System.out.println(" System is UNSAFE !!");
        else{
            System.out.println(" System is SAFE !!");
            System.out.println(" Process execution sequence : ");
            for(int i : SafeSequence)
                System.out.print(" => "+i);
        }
    }


    void CalculateNeed(){
        for(int i=0;i<processes;i++){
            for(int j=0;j<resources;j++)
                Need[i][j] = Max[i][j] - Allocation[i][j];
        }
    }


    public static void main(String[] args) {
        MyAttempt obj = new MyAttempt();
        obj.CalculateNeed();
        obj.Safety_Check();
        System.out.println();
    }
}
