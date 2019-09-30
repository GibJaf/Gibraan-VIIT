


import java.io.*;
import java.util.*;
class AD {

    String startclass="IS";
    int startop=1;



    String endclass="IS";
    int endop=2;


    String orgclass="IS";
    int orgop=3;


    String equclass="IS";
    int equop=4;


    String ltorgclass="IS";
    int ltorgop=5;


}
class Dl {

    String dsclass="DL";
    int dspop=1;
    int dslen=0;


    String dcclass="DL";
    int dcop=2;
    int dclen=1;
}
class IS {
    String stpclass="IS";
    int stopop=0;
    int stoplen=1;

    String addclass="IS";
    int addop=1;
    int addlen=1;


    String subclass="IS";
    int subop=2;
    int sublen=1;


    String multclass="IS";
    int multop=3;
    int multlen=1;


    String moverclass="IS";
    int moverop=4;
    int moverlen=1;


    String movemclass="IS";
    int movemop=5;
    int movemlen=1;


    String compclass="IS";
    int compop=6;
    int complen=1;


    String bcclass="IS";
    int bcop=7;
    int bclen=1;


    String divclass="IS";
    int divop=8;
    int divlen=1;


    String readclass="IS";
    int readop=9;
    int readlen=1;


    String printclass="IS";
    int printop=10;
    int printlen=1;
}
public class Pass1a {

	public static void main(String args[]) throws IOException{
		IS is=new IS();
    		AD ad=new AD();
    		Dl dl=new Dl();
   		FileReader fr=new FileReader("assembly.txt");
        	BufferedReader br=new BufferedReader(fr);
       		String s1,s7 = null;
      		FileWriter fw=new FileWriter("IC.txt",true);
      		while((s1=br.readLine())!=null)
       		{
          		String s2[]=s1.replaceAll("^[,\\s]+", "").split("[,\\s]+");
         		for(int i=0;i<s2.length;i++)
         		{
              			if(s2[i].equals("START") || s2[i].equals("END"))
              			{
                			fw.write("(");
                			fw.write("AD"+" ,");

                			if(s2[i].equals("START")){
                    				fw.write("0"+ad.startop);
                    				fw.write(") ");
                    				fw.write("(");
                				fw.write("C"+" ,");
                				fw.write(s2[i+1]+") ");
                			}
                			if(s2[i].equals("END")){
                    			fw.write("0"+ad.endop);
                			fw.write(")");
                			}


             			}
             			if(s2[i].equals("MOVER") || s2[i].equals("MOVEM"))
             			{
                			fw.write("\n");
                			fw.write("(");
                			fw.write("IS"+" ,");
                			if(s2[i].equals("MOVER"))
                    			fw.write("0"+is.moverop);
                			if(s2[i].equals("MOVEM"))
                    			fw.write("0"+is.movemop);
                			fw.write(")");

             			}
             			if(s2[i].equals("AREG") || s2[i].equals("BREG")){
                			s7=s2[i];
                			if(s2[i].equals("AREG"))
                			fw.write(" (RG,01) ");
                			if(s2[i].equals("BREG"))
                			fw.write(" (RG,02) ");
             			}
             			if((s2[i].equals("A")|| s2[i].equals("B")) && (s7.equals("AREG")|| s7.equals("BREG"))){
                   			if(s2[i].equals("A"))
                   			fw.write(" (S,0) ");
                   			if(s2[i].equals("B"))
                   			fw.write(" (S,1) ");
                   			s7="";
              			}
             			if((s2[i].equals("'5'")|| s2[i].equals("'4'")) && (s7.equals("AREG")|| s7.equals("BREG"))){
                   			s7="";
              			}
             			if(s2[i].equals("'5'") || s2[i].equals("'4'")){
                   			fw.write("(");
                			fw.write("L"+" ,");
                			if(s2[i].equals("'5'")){
                			fw.write("01");
                			}
                			if(s2[i].equals("'4'")){
                			fw.write("02");
                			}
                			fw.write(")");
             			}

				if(s2[i].equals("DS")){
					fw.write("\n");
					fw.write("(");
					fw.write("DL"+" ,");
					fw.write("01");
					fw.write(") ");
					fw.write("(");
					fw.write("C,01)");

				}

          		}
         		fw.write("\n");
       	     }
       fr.close();
       fw.close();

	}

}

