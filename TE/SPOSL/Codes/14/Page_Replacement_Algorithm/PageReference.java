package Replacement;
class PageReference{
	/*
	PageReference: class for storing page references
	page:page reference
	backdistance: use to calculate least recently used page
	*/
	public int page;
	public int distance;
	public PageReference(int page,int distance)
	{
		this.page=page;
		this.distance=distance;
	}
	public boolean equals(Object o)
	{
		/*
		override equals function from Object class
		*/
		PageReference p=(PageReference)o;//convert Object to PageReference
		if(p.page==this.page)//if page attribute is equal then PageReference are equal
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	public String toString()
	{
		return Integer.toString(page);
	}
}

