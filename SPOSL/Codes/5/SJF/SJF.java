import java.util.*;
import java.lang.*;
import java.io.*;

class SJF
{
	public static void main (String[] args) throws java.lang.Exception
	{
		int num,ct,a,flag=0;
		String n;
		Scanner scan = new Scanner(System.in);
	    Process[] proc = new Process[100];
	    System.out.print("\nEnter Number Of Processes : ");
		num=scan.nextInt();
		System.out.println("\nEnter Data Of "+num+" Processes : ");
		for(int i=0;i<num;i++)
		{
			System.out.print("\nEnter Process Name   : ");
			n=scan.next();
			System.out.print("\nEnter CPU Burst Time : ");
			ct=scan.nextInt();
			System.out.print("\nEnter Arrival Time   : ");
			a=scan.nextInt();
			proc[i]=new Process();
			proc[i].Input(i,n,a,ct);
		}
		
		for(int i=0;i<num;i++)
		{
			for(int j=i;j<num;j++)
			{
				if(proc[j].cpu<proc[i].cpu)
				{
					Process temp=new Process();
					temp=proc[i];
					proc[i]=proc[j];
					proc[j]=temp;
				}
			}
		}
				
		for(int i=0;i<num;i++)
		{
			if(flag==0)
			{
				proc[i].wait=0;
				proc[i].turnarnd=proc[i].cpu;
				flag=1;
			}
			else
			{
				proc[i].wait=proc[i-1].turnarnd;
				proc[i].turnarnd=proc[i].cpu+proc[i].wait;
			}
		}
		
		System.out.println("\nSJF Scheduling...");
		for(int i=0;i<num;i++)
		{
			proc[i].Print();
		}
	}
}

class Process 
{
	public int no;
	public String name;
	public int ariv;
	public int cpu;
	public int turnarnd;
	public int wait;

	public void Input(int num, String n, int a, int c) 
	{
		no=num;
		name=n;
		ariv=a;
		cpu=c;
		turnarnd=0;
		wait=0;
	}

	public void Print() 
	{
		System.out.println("\nProcess Number  : "+no);
		System.out.println("Process Name    : "+name);
		System.out.println("Arrival Time    : "+ariv);
		System.out.println("CPU Burst Time  : "+cpu);
		System.out.println("Turnaround Time : "+turnarnd);
		System.out.println("Waiting Time    : "+wait);
	}
}
