import java.util.*;
import java.io.*;

public class MyAttempt{
        HashMap<String,String> AD = new HashMap<>();
        HashMap<String,String> IS = new HashMap<>();
        HashMap<String,String> DL = new HashMap<>();
        HashMap<String,String> Registers = new HashMap<>();

        MyAttempt(){
            AD.put("START","00");
            AD.put("ORIGIN","01");
            AD.put("EQU","02");
            AD.put("LTORG","03");
            AD.put("END","04");
            AD.put("USING","05");

            IS.put("MOVER","01");
            IS.put("MOVEM","02");
            IS.put("ADD","03");
            IS.put("SUB","04");
            IS.put("MULT","05");
            IS.put("DIV","06");
            IS.put("BC","07");
            IS.put("COMP","08");
            IS.put("PRINT","09");
            IS.put("READ","10");
            IS.put("STOP","11");

            DL.put("DS","01");
            DL.put("DC","02");

            Registers.put("AREG","(RG,01)");
            Registers.put("BREG","(RG,02)");
            Registers.put("CREG","(RG,03)");
            Registers.put("DREG","(RG,04)");


        }

    String Check_AD(String word){
        if(AD.containsKey(word)){
            return AD.get(word);
        }else
            return "NULL";
    }

    String Check_IS(String word){
        if(IS.containsKey(word)){
            return IS.get(word);
        }else
            return "NULL";
    }

    String Check_DL(String word){
        if(DL.containsKey(word)){
            return DL.get(word);
        }else
            return "NULL";
    }

    String Check_Register(String word){
        if(Registers.containsKey(word)){
            return Registers.get(word);
        }else
            return "NULL";
    }

    boolean Check_Constant(String word){
        if(word.matches("[0-9]+"))
            return true;
        else
            return false;
        // include logic to add to constants arra
    }

    // return tuple(__,__)
    String Identify(String str){
        String AD_result = Check_AD(str);
        String IS_result = Check_IS(str);
        String DL_result = Check_DL(str);
        String Register  = Check_Register(str);
        boolean const_result = Check_Constant(str);

        if(AD_result!="NULL"){
            return "(AD,"+AD_result+")";
        }else if(IS_result!="NULL"){
            return "(IS,"+IS_result+")";
        }else if(DL_result!="NULL"){
            return "(DL,"+DL_result+")";
        }else if(Register!="NULL"){
            return Register;
        }else if(const_result == true){
            return "(C,--)";
        }else{
            return "Unidentified";
        }
    }

    public static void main(String[] args)throws FileNotFoundException , IOException {

        MyAttempt obj = new MyAttempt();//populate AD,IS,DL HashMap

        FileReader fr = new FileReader("assembly.txt");
        BufferedReader br = new BufferedReader(fr);
        String s1 = null;

        for(int LC=0  ;(s1 = br.readLine())!=null ; LC++ ){
            String s2[]=s1.replaceAll("^[,\\s]+", "").split("[,\\s]+");


                System.out.print("LC : "+LC+"\t");
                for(int i=0;i<s2.length;i++){
                    System.out.print(" "+s2[i]);
                    if(s2[i] == "200")
                        break;
                }

                System.out.println();

                for(int i=0;i<s2.length;i++){
                    System.out.println("word "+s2[i]+" is "+obj.Identify(s2[i]));

                if(s2[i] == "START")
                    System.out.println(" FOUND START");
            }
            System.out.println();
            System.out.println("---------------");


        }
    }
}
