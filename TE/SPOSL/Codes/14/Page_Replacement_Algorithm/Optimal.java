package Replacement;
import java.util.*;
class Optimal{
	ArrayList<PageReference> pages;
	int frames;
	PageReference p,p1,f1;
	public int pagehit,pagefault;
	public Optimal(ArrayList<PageReference> pages,int frames)
	{
		this.pages=pages;
		this.frames=frames;
		this.pagehit=0;
		this.pagefault=0;
	}
	public void solve(){
		ArrayList<PageReference> f=new ArrayList<>(frames);//frame to store pages
		for(int i=0;i<pages.size();i++)
		{
			p=pages.get(i);
			System.out.println("PageReference :"+p.page);
			
			
			if(!f.contains(p) && f.size()<frames)
			{
				/*Condition 1 
				if frame doesnt contains page i
				and frame is not full
				then
				add page i  
				*/
				f.add(p);
				
			}
			else if(!f.contains(p) && f.size()>=frames)
			{
				/*Sort ArrayList f so that page with highest forwarddistance comes first */
				Collections.sort(f,new Comparator<PageReference>(){
				public int compare(PageReference p1,PageReference p2)
				{
					return p2.distance-p1.distance;
				}
				});
				/*Condition 2 
				if frame doesnt contains page i
				and frame is full
				then
				remove the page with longest forwarddistance
				and add page i  
				*/
				f.remove(0);
				f.add(p);	
			}
			
			else if(f.contains(p))
			{
				pagehit++;	
			}
			for(int j=0;j<f.size();j++)                             
			{
				
				p=f.remove(0);
				int k=i+1;
				for(;k<pages.size();k++)
				{
					p1=pages.get(k);
					if(p.page==p1.page)
					{
						p.distance=k-i;
						break;
					}
				}
				if(k==pages.size())
				{
					p.distance=pages.size()+1;
				}
				f.add(p);
			}
						
			System.out.println(f);
			System.out.println("Pagehit: "+pagehit);
		}
	}
}
