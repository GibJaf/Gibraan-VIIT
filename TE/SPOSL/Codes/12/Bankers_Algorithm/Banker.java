import java.util.Scanner;

public class Banker 
{    
    private int i,j,pros,res;
    public Banker(){
        Scanner in=new Scanner(System.in);
        System.out.println("Enter total number of processes");
        pros=in.nextInt();
        System.out.println("Enter total number of types of re-sources");
        res=in.nextInt();
        int[][] all_arr=new int[pros][res];
        int[][] max_req=new int[pros][res];
        int[] tot_res=new int[res];
        int[][] need_arr=new int[pros][res];
        int[] avail=new int[res];
        System.out.println("Enter the total number of  allocated resources  for processess ");
        for(j=0;j<pros;j++){
            for(i=0;i<res;i++){ 
               all_arr[j][i]=in.nextInt();
              // System.out.print("\t");
            }
            System.out.println("");
        }
        System.out.println("Enter  max resources required for resource type for processes");
         for(int j=0;j<pros;j++){
            for(i=0;i<res;i++){ 
                max_req[j][i]=in.nextInt();  
            //    System.out.print("\t");
            }
            System.out.println("");
        }
         
        System.out.println("Enter the total number of total available resources ");     
        for(i=0;i<res;i++){
            tot_res[i]=in.nextInt();
            avail[i]=tot_res[i];
            
        }
        int sum[]=new int[res];
        for(j=0;j<res;j++){
            sum[j]=0;
            for(i=0;i<pros;i++){
                sum[j]=sum[j]+all_arr[i][j];
            } 
        }
        for(i=0;i<res;i++){
                tot_res[i]=avail[i]-sum[i];
            } 
        for(int j=0;j<pros;j++){
                for(i=0;i<res;i++){ 
                need_arr[j][i]=max_req[j][i]-all_arr[j][i];
            }
        }  
        System.out.println("1");
        
        System.out.println("total number of  allocated resources for processes \n");
         for(int j=0;j<pros;j++){
            for(i=0;i<res;i++){ 
                System.out.print(+ all_arr[j][i]+"  ");
            }
            System.out.print("\n");
        }
        System.out.println("total number of  max resources required for processes \n");
         for(int j=0;j<pros;j++){
            for(i=0;i<res;i++){ 
                System.out.print(+max_req[j][i]+"  ");
            }
            System.out.print("\n");
        }
        System.out.println("total number of resources needed for processes \n");
        for(int j=0;j<pros;j++){
            for(i=0;i<res;i++){ 
                System.out.print(+need_arr[j][i]+"  ");
            }
            System.out.print("\n");
        }
        System.out.print("total number of resources available for processes \n");
        for(i=0;i<res;i++){
          System.out.print(avail[i]+"  ");
        }
        int[] ch=new int[res];
        for(j=0;j<res;j++){ 
        for(i=0;i<pros;i++){
            ch[j]=ch[j]+all_arr[i][j];
        }   
        }
        System.out.println("1");
        int k = 0;
        for(j=0;j<res;j++){
            if(avail[j]-ch[j]>=0){ k=0;}
            else{
                k=1;
                break;
            }
        }
           if(k==0){
        int[] ans=new int[pros];
         for(i=0;i<pros;i++){ 
                ans[i]=-1;
        }
         int num=0;
        System.out.println();
        int count=0,cnt=0,flag=0;  
        System.out.println("1");
        while(num<pros){
            for(j=0;j<pros;j++){ 
                cnt=0;
                count=j;
                if(ans[count]==-1 ){
                for(i=0;i<res;i++){
                    if(tot_res[i]-need_arr[j][i]>=0){
                   // System.out.println(tot_res[i]+" check "+need_arr[j][i]);
                   System.out.println("2");
                    }
                    else {
                            cnt=1;
                          //  System.out.println(tot_res[i]+" break "+need_arr[j][i]);
                          System.out.println("3");
                            break;
                            }
                    }   
                
                    if(cnt==0){
                         for(i=0;i<res;i++){
                            tot_res[i]=tot_res[i]+ all_arr[j][i];
                        //    System.out.println(tot_res[i]+" first if "+num);  
                        }
                        System.out.println("*******************"+j+"*******************************");
                        ans[count]=j;
                        num++;
                    }
                }
            }    
        }
        System.out.println("1");
     /*   for(i=0;i<pros;i++){
          System.out.print(ans[i]+"  ");
        }*/

           }
       else{
             System.out.print("unsafe state  ");
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Banker obj= new Banker();   
   }   
}
