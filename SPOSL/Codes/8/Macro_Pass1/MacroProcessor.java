
import java.io.*;
import java.lang.String;
import java.util.Scanner;


public class MacroProcessor
{
	static int mdtp=0,mntp=0;

    public static void main(String []args) throws Exception
	{
				File f=new File("program.txt");
				Scanner s=new Scanner(f);

				String tokens[];	         //Array for storing tokens generated

				String MDT_Def,macro_def_line;

                MNT[] mnt_obj=new MNT[5];      //Array of objects for MNT
                MDT[] mdt_obj=new MDT[5];		//Array of objects for MDT

                String[] ALA;                  //Data member of MNT Class

                while(s.hasNextLine())
                {
                        macro_def_line=s.nextLine();           //read line in macro_def_line variable
                        tokens=macro_def_line.split(" ");      //split the line on space

                        for(int i=0;i<tokens.length;i++)
                        {
                            if(tokens[i].equals("MACRO"))      //if macro
                            {
                                //----------MNT------
                                mnt_obj[mntp]=new MNT(mdtp,mntp);    //create MNT object
                                mnt_obj[mntp].name=tokens[i+1];		// name of macro

                               // mnt_obj[mntp].print();              //print MNT Contents

                   //-------------------MDT-----------------------------------------
                                MDT_Def=macro_def_line;            //store line in MDT_Def

                               while(true)                         //read until MEND not found
                               {
                                   String x=s.nextLine();           //read macro defintion line by line

                                   if(x.equals("MEND"))
                                   {
                                       MDT_Def=MDT_Def.concat(x);    //MDT also contains MEND statement
                                       mdtp++;                       //increment mdtp for next macro definition
                                       break;
                                   }

                                   MDT_Def=MDT_Def+"\n";             //add new line to MDT contents
                                   MDT_Def=MDT_Def.concat(x);        //put next statement of macro definition

                                   mdtp++;

                               }

                               if(MDT_Def.contains("X"))               //Replace argument with #1 ,#2......
                               	MDT_Def=MDT_Def.replaceAll("X","#0");

                               if(MDT_Def.contains("Y"))
                               	MDT_Def=MDT_Def.replaceAll("Y","#1");

                               MDT_Def=MDT_Def.replaceFirst("#0","X"); //Macro name line contains parameters as it  is
                               MDT_Def=MDT_Def.replaceFirst("#1","Y");

                               mdt_obj[mntp]=new MDT(MDT_Def);          //create mdt object and store contents in it
                               //mdt_obj[mntp].print();                 //print mdt contents  

                                 //--------ALA--------
                               ALA=tokens[i+2].split(",");              //arguments at 3rd index of token array and splitting the arguments
                               mnt_obj[mntp].setALA(ALA);               //set ALA for  each macro

			       mntp++;
                            }

                        }

                }
                print(mnt_obj,mdt_obj);
             s.close();
        }

    	static void print(MNT[] obj,MDT[] obj1)
    	{
    			System.out.println("---------MNT--------");
    		for(int i=0;i<mntp;i++)
    			obj[i].print();

    			System.out.println();
    		System.out.println("---------MDT--------");
    		for(int i=0;i<mntp;i++)
    			obj1[i].print();

    			System.out.println();
    		System.out.println("--------ALA--------");
    		for(int i=0;i<mntp;i++)
    			obj[i].printALA();

    			System.out.println();

    	}


}

class MNT
{
	int no,address;
	String name;
	String[] ALA;

	MNT(int mdtp,int mntp)
	{
                no=mntp;
                if(mntp==0)
                	address=mdtp;          //First Macro will be at 0th position in MDT
                else
                	address=mdtp+1;
		name="";
	}

	public void print()
	{
		System.out.print(this.no + "\t"+this.name + "\t" + this.address);
		System.out.println();
	}

	public void setALA(String[] obj)
	{
		this.ALA=obj;
	}

	public void printALA()
	{
		System.out.println("0 " + this.ALA[0]);
		System.out.println("1 " + this.ALA[1]);

	}

}

class MDT
{
    String def;

    MDT(String defintion)
    {
        this.def=defintion;
    }

    public void print()
	{
		System.out.print(this.def);
		System.out.println();
	}
}
