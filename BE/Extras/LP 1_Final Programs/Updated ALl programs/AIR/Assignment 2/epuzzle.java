import java.io.*;
import java.util.*;
class info
{
	int h,g,f;
	String s=new String();
	info(String s, int h,int g,int f)
	{
		this.s=s;
		this.h=h;
		this.g=g;
		this.f=f;
	}
}
class epuzzle
{
	int n;
	char start[][],goal[][],current[][];
	Scanner sc;
	ArrayList<info> closed =new ArrayList<info>();
	ArrayList<info> open =new ArrayList<info>();
	epuzzle()
	{
		sc=new Scanner(System.in);
	}
	public void cequals()
	{
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				current[i][j]=start[i][j];
			}
		}
	}
	public void input()
	{
		System.out.println("\nEnter the size of input matrix");
		n=sc.nextInt();
		start=new char[n][n];
		goal=new char[n][n];
		current=new char[n][n];
		System.out.println("\nEnter the start matrix");
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				start[i][j]=sc.next().charAt(0);
				
			}
		}
		System.out.println("\nEnter the goal matrix");
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				goal[i][j]=sc.next().charAt(0);
			}
		}
	}
	public void output()
	{
		System.out.println("The start matrix is :\n");
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				System.out.print(start[i][j]+"\t");
			}
			System.out.println("\n");
		}
		System.out.println("The goal matrix is :\n");
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				System.out.print(goal[i][j]+"\t");
			}
			System.out.println("\n");
		}
	}
	public int find()
	{
		int count=0;
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(current[i][j]!=goal[i][j])
					count++;
			}
		}
		return count;
	}
	public void changes(String s,int ith ,int jth,char dir)
	{
		if(dir=='U')
		{
			current[ith][jth]=current[ith-1][jth];
			current[ith-1][jth]='s';
			open.add(new info(s.concat("U"),find(),s.length(),find()+s.length()));
			current[ith-1][jth]=current[ith][jth];
			current[ith][jth]='s';
		
		}
		if(dir=='L')
		{
			current[ith][jth]=current[ith][jth-1];
			current[ith][jth-1]='s';
			open.add(new info(s.concat("L"),find(),s.length(),find()+s.length()));
			current[ith][jth-1]=current[ith][jth];
			current[ith][jth]='s';
		}
		if(dir=='R')
		{
			current[ith][jth]=current[ith][jth+1];
			current[ith][jth+1]='s';
			open.add(new info(s.concat("R"),find(),s.length(),find()+s.length()));
			current[ith][jth+1]=current[ith][jth];
			current[ith][jth]='s';
		
		}
		if(dir=='D')
		{
			current[ith][jth]=current[ith+1][jth];
			current[ith+1][jth]='s';
			open.add(new info(s.concat("U"),find(),s.length(),find()+s.length()));
			current[ith+1][jth]=current[ith][jth];
			current[ith][jth]='s';
		}
	}
	public void getdirections(String s,int ith, int jth)
	{
		if(ith==0&&jth==0)
		{
			changes(s,ith,jth,'R');
			changes(s,ith,jth,'D');
		}
		else if(ith==0&&jth==n-1)
		{
			changes(s,ith,jth,'L');
			changes(s,ith,jth,'D');
			
		}
		else if(ith==n-1&&jth==0)
		{
			changes(s,ith,jth,'U');
			changes(s,ith,jth,'R');
		}
		else if(ith==n-1&&jth==n-1)
		{
			changes(s,ith,jth,'U');
			changes(s,ith,jth,'L');
		}
		else if(ith==0)
		{
			changes(s,ith,jth,'R');
			changes(s,ith,jth,'D');
			changes(s,ith,jth,'L');
		}
		else if(ith==n-1)
		{
			changes(s,ith,jth,'U');
			changes(s,ith,jth,'R');
			changes(s,ith,jth,'L');
		}
		else if(jth==0)
		{
			changes(s,ith,jth,'U');
			changes(s,ith,jth,'D');
			changes(s,ith,jth,'R');
		}
		else if(jth==n-1)
		{
			changes(s,ith,jth,'U');
			changes(s,ith,jth,'D');
			changes(s,ith,jth,'L');
		}
		else
		{
			changes(s,ith,jth,'U');
			changes(s,ith,jth,'D');
			changes(s,ith,jth,'L');
			changes(s,ith,jth,'R');
		}
	}
	public void gets(String s)
	{
		int ith=0,jth=0;
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(current[i][j]=='s')
				{
					ith=i;
					jth=j;
					break;
				}
			}
		}
		getdirections(s,ith,jth);
	}
	public void put(String s)
	{
		if(s.length()!=0)
		{
			int ith=0,jth=0;
			for(int i=0;i<n;i++)
			{
				for(int j=0;j<n;j++)
				{
					if(current[i][j]=='s')
					{
						ith=i;
						jth=j;
						break;
					}
				}
			}
			for(int i=0;i<s.length();i++)
			{
				if(s.charAt(i)=='U')
				{
					current[ith][jth]=current[ith-1][jth];
					current[ith-1][jth]='s';
				}
				if(s.charAt(i)=='L')
				{
					current[ith][jth]=current[ith][jth-1];
					current[ith][jth-1]='s';
				}
				if(s.charAt(i)=='R')
				{
					current[ith][jth]=current[ith][jth+1];
					current[ith][jth+1]='s';
				}
				if(s.charAt(i)=='D')
				{
					current[ith][jth]=current[ith+1][jth];
					current[ith+1][jth]='s';
				}
			}
		}
		gets(s);
	}
	public void sort()
	{
		for(int i=0;i<open.size()-1;i++)
		{
			for(int j=0;j<open.size()-1;j++)
			{
				if((open.get(j).f)>(open.get(j+1).f))
				{
					info c=open.get(j);
					open.remove(j);
					open.add(j+1,c);
				}
			}
		}
		
	}
	public void solve()
	{
		String k="";
		cequals();
		open.add(new info("",find(),k.length(),find()+k.length()));
		while(open.get(0).h!=0)
		{
			put(open.get(0).s);
			closed.add(open.get(0));
			open.remove(0);
			sort();
			cequals();
			break;
		}
		System.out.println(open.get(0).f);
		System.out.println(open.get(1).f);
		System.out.println(open.get(2).f);
		
		
		/*closed.add(open.get(0));
		System.out.println("The path from start to goal is \n=>");
		System.out.println(closed.get(0).s);
		System.out.println("The number of steps from start to goal is \n=>");
		System.out.println(closed.get(0).s.length());*/
	}
	public static void main(String args[])
	{
		epuzzle obj=new epuzzle();
		obj.input();
		obj.output();
		obj.solve();
	}
}
