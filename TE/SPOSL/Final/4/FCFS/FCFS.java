/* Problem with this program :
	It is asking for priority , whereas priority is decided based on
	which task arrives first .*/

import java.io.*;
import java.util.Scanner;
public class FCFS
{

	int cpu_time,arrival_time,process_num,priority,wait_time;
	Scanner s=new Scanner(System.in);

		FCFS(int no)
		{
			System.out.println("Constructor executed");
			process_num=no+1;
			cpu_time=arrival_time=priority=0;
		}

		void input()
		{
			System.out.print("Enter the burst time for P"+process_num+" :");
			cpu_time=s.nextInt();

			System.out.print("Enter the arrival time :");
			arrival_time=s.nextInt();

			System.out.println("Enter its priority :");
			priority=s.nextInt();

		}


		static void cal_time(FCFS[] obj,int no)
		{

			// ------Waiting time-----------
			int count=0;
			float avg=0;
			System.out.println();
			for(int i=0;i<no;i++)
			{
				avg+=count;
				obj[i].wait_time=count;
				System.out.println("Waiting time for P"+(i+1)+" :"+obj[i].wait_time);
				count+=obj[i].cpu_time;
			}

			System.out.println("Average Waiting time :"+(avg/no));

			// ------TurnAround time-----------
			System.out.println();
			count=0;avg=0;
			for(int i=0;i<no;i++)
			{

				System.out.println("TurnAround time for P"+(i+1)+" :"+(obj[i].wait_time+obj[i].cpu_time));
				count+=obj[i].wait_time+obj[i].cpu_time;
				avg+=obj[i].wait_time+obj[i].cpu_time;
			}
			System.out.println(avg);
			System.out.println("Average TurnAround time :"+(avg/no));

		}


	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		System.out.print("Enter the number of processes :");
		int num=in.nextInt();

		FCFS[] obj=new FCFS[num];

		for(int i=0;i<num;i++)
		{
			obj[i]=new FCFS(i);
			obj[i].input();
		}

		FCFS.cal_time(obj,num);

	}

}
