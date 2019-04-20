import java.io.*;
import java.util.Scanner;
public class RR
{
	int process_num,cpu_time;
	Scanner s=new Scanner(System.in);
	
	RR(int no)
	{
		cpu_time=0;
		process_num=no+1;
	}
	
	public void input()
	{	
		System.out.print("Enter the CPU time for P"+(process_num)+" :");
		cpu_time=s.nextInt();
	}
	
	public static void process(RR[] obj,int num,int time_slice)
	{
		while(!check_all(obj,num))
		{
			for(int i=0;i<num;i++)
			{
				if(obj[i].cpu_time!=0 )
				{
					if(obj[i].cpu_time>=time_slice)
					{
						obj[i].cpu_time=obj[i].cpu_time-time_slice;
						System.out.println("P" + obj[i].process_num +" :"+time_slice);
					}
					
					else
					{
						System.out.println("P" + obj[i].process_num +" :"+obj[i].cpu_time);
						obj[i].cpu_time=0;	
					}
				}
			}
		}	
	}
	
		
	public static boolean check_all(RR[] process,int no)
	{
		boolean var=true;
		for(int i=0;i<no;i++)
		{
			if(process[i].cpu_time!=0)
			{
				var=false;
				break;
			}
		}
		
		return var;
	}
		
	
	
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);	
		
		System.out.print("Enter the time slice :");
		int time_slice=in.nextInt();
		
		System.out.print("Enter the number of processes :");
		int num=in.nextInt();		
		
		RR[] obj=new RR[num];
		
		for(int i=0;i<num;i++)
		{
			obj[i]=new RR(i);
			obj[i].input();
		}
		
		RR.process(obj,num,time_slice);
	}

}
