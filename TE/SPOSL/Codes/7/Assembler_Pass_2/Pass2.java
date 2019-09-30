

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Pass2 {
	 
	public static void main(String args[]) throws IOException
	{
    		ArrayList<String> arr=new ArrayList();
    		ArrayList<String> arr1=new ArrayList();
   		FileReader fr=new FileReader("PassOp.txt");
   		FileReader fr1=new FileReader("sym.txt");
        	BufferedReader br=new BufferedReader(fr);
        	BufferedReader br1=new BufferedReader(fr1);
       		String s1,s7 = null;     
      		FileWriter fw=new FileWriter("pass2Op.txt",true);
        	while((s1=br.readLine())!=null)
        	{
        	    String s2[]=s1.replaceAll("^[,\\s]+", "").split("[ ( ) ,\\s]+");
       		    for(String w:s2)
       		    {
                	if(!w.equals("\n"))
                	{
                		arr.add(w);
                	}
                    }
        	}
         	while((s1=br1.readLine())!=null)
         	{
        		String s2[]=s1.replaceAll("^[,\\s]+", "").split("[ ( ) ,\\s]+");
            		for(String w:s2)
            		{
                		arr1.add(w);
      			}
        
        	}
        	System.out.println(arr);
        	Iterator ir=arr.iterator();
        	int lc=Integer.parseInt(arr.get(4));
        	for(int i=0;i<arr.size();i++)
        	{
            		String op=arr.get(i);
            		
            		if(op.equals("IS"))
            		{
            			fw.write(lc+" ");
                		fw.write(arr.get(++i).toString()+" ");
                		lc++;
            		}
             		if(op.equals("RG"))
            		{
              			fw.write(arr.get(++i).toString()+" ");
            		}
             		if(op.equals("S"))
            		{
            			op=arr.get(i+1).toString();
                		for(int adi=0;adi<arr1.size();adi++)
                		{
                    			if(op.equals(arr1.get(adi)))
                    			{
                        			fw.write(arr1.get(adi+2));
                    			}
                		}
                		fw.write('\n');
            
            		}
            		
         
        	}
        	fr.close();
       		fw.close();
	}    
}
