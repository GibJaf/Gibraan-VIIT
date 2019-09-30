
import java.io.*;
import java.util.*;
public class Pass1 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
       FileReader fr=new FileReader("abc.txt");
       BufferedReader br=new BufferedReader(fr);
        
       Queue q = new LinkedList();
            
       String s1;
       int lc=0;
       int c=0;
       int l=0;
       FileWriter fw=new FileWriter("sym.txt",true);
       FileWriter fw1=new FileWriter("lt.txt",true);
       
       while((s1=br.readLine())!=null)
       {
            
           lc=lc+1;
           
           String s2[]=s1.split("\\s");
           String s4[]=s1.split(",");
            
           for(int i=0;i<s4.length;i++){
            	if(s4[i].equals("DS"))
         	{
               		System.out.println(s4[i+1]);    
         	}
          
           	if(s4[i].equals("'5'") || s4[i].equals("'4'"))
         	{
            		q.add(s4[i]);
              
         	}
           } 
          for(int i=0;i<s2.length;i++)
          {
             
		if(s2[i].equals("START")){
			lc=Integer.parseInt(s2[i+1]);
			lc++;
		}
           	if(s2[i].equals("A") || s2[i].equals("B"))
         	{
			String s3=s2[i];
			fw.write(" "+c);
			fw.write(" "+s3);
			fw.write(" "+lc);
			fw.write("\n");
             		c++;
             
         	}
           	if(s2[i].equals("END"))
          	{ 
           		Iterator itr2=q.iterator();  
         		while(itr2.hasNext())
         		{  
             			fw1.write(l+" ");
              			fw1.write(itr2.next().toString()+" ");
              			fw1.write(lc+" ");
              			lc++;
              			l++;
          		}  
          	}
                
           }
         }
          fw.close();
          fw1.close();
          fr.close();
          
       }
       
}