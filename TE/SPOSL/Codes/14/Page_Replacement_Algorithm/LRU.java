package Replacement;
import java.util.*;
class LRU{
	ArrayList<PageReference> pages;
	int frames;
	PageReference p,p1,f1;
	public int pagehit,pagefault;
	public LRU(ArrayList<PageReference> pages,int frames)
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
			p=pages.get(i);//page i
			System.out.println("PageReference :"+p.page);
			
			
			if(!f.contains(p) && f.size()<frames)
			{
				/*Condition 1 
				if frame doesnt contains page i
				and frame is not full
				increase the backdistance of every element in frame by 1
				and add page i  
				*/
				for(int j=0;j<f.size();j++)
				{
					p1=f.remove(j);
					p1.distance++;
					f.add(p1);		
				}
				f.add(p);
				
			}
			else if(!f.contains(p) && f.size()>=frames)
			{
				/*Sort ArrayList f so that page with highest backdistance comes first */
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
				remove the page with longest backdistance
				increase the backdistance of every other element in frame by 1
				and add page i  
				*/
				f.remove(0);
				for(int j=0;j<f.size();j++)
				{
					p1=f.remove(j);
					p1.distance++;
					f.add(p1);		
				}
				f.add(p);	
			}
			
			else if(f.contains(p))
			{
				/*Condition 3
				if frame contains page i
				then
				set the backdistance of page to 0
				increase the backdistance of every element in frame by 1  
				*/
				f.remove(p);
				for(int j=0;j<f.size();j++)
				{
					p1=f.remove(j);
					p1.distance++;
					f.add(p1);		
				}
				p.distance=0;
				f.add(p);
				pagehit++;	
			}
			System.out.println(f);
			System.out.println("Pagehit: "+pagehit);
		}
	}
}

