
import java.io.*;
import java.lang.String;
import java.util.Scanner;

public class MacroPass2N {
	public static void main(String []args) throws Exception
	{
            File f=new File("program.txt");
            Scanner s=new Scanner(f);
                		
            String[] tokens,macro_args;	         //Array for storing tokens generated
            boolean is_macro=false;    
            while(s.hasNextLine())
	    {
	        tokens=s.nextLine().split(" ");      //split the line on space
	                       
	        for(int i=0;i<tokens.length;i++)
	        {	
	            is_macro=check_if_macro(tokens[i]);
                    if(is_macro==true)
                    {
                        macro_args=tokens[i+1].split(",");
                        expand(tokens[i],macro_args);
                        //System.out.println(tokens[i]+" "+macro_args[0]+" "+macro_args[1]);
                        
                        
                    }
                    else
                    {
                       if(!tokens[i].contains(","))
                            System.out.println(tokens[i]);
                    }
        	}
                
                System.out.println();
	               
	    }
				 
            s.close();
				
	}
	
        static void expand(String macro_name,String[] macro_args) throws FileNotFoundException
        {
                File f1=new File("contents.txt");
                Scanner s1=new Scanner(f1);
      
		String[] tokens_arr;
		while(!s1.nextLine().contains("MDT:"))
			s1.nextLine();
                while(!s1.nextLine().contains(macro_name))
                        continue;
          
                String def="";
                while(!def.contains("MEND"))
                {
                    def=s1.nextLine();
                    
                    def= def.replace("#0",macro_args[0]);
                    def=def.replace("#1",macro_args[1]);
                    if(!def.contains("MEND"))
                        System.out.println(def);
                }
		
        }
        
        
	static boolean check_if_macro(String token) throws FileNotFoundException
	{
		
		File f1=new File("contents.txt");
		Scanner s1=new Scanner(f1);
        
		String[] tokens_arr;
		
		while(!s1.nextLine().contains("MNT:"))
			s1.nextLine();
		while(s1.hasNextLine())
		{
			tokens_arr=s1.nextLine().split(" ");
                        for(int i=0;i<tokens_arr.length;i++)
                        {
                            if(tokens_arr[i].equals(token))
                            {
                                s1.close();
                
                                return true;
                            }
                        }
                	
		}
		
		s1.close();
                return false;
		
	}

}
