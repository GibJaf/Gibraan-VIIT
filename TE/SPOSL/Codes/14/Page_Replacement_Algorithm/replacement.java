package Replacement;
import java.util.*;


class replacement{

	public static void main(String args[]) throws Exception
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter number of page reference string\n");
		int n=s.nextInt();
		System.out.println("Enter page reference string\n");
		ArrayList<PageReference> pages=new ArrayList<>();
		for(int i=0;i<n;i++)
		{
			pages.add(new PageReference(s.nextInt(),0));
		} 
		System.out.println("Enter Number of page frames\n");
		int frames=s.nextInt();
		System.out.println("1)Solve using LRU\n2)Solve using Optimal algoritm");
		int choice=s.nextInt();
		switch(choice)
		{
			case 1:
			{
				LRU l=new LRU(pages,frames);
				l.solve();
			}
			case 2:
			{
				Optimal op=new Optimal(pages,frames);
				op.solve();
			}
		}
	}
}
