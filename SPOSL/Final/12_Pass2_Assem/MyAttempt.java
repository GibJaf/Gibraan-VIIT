import java.io.*;
import java.util.*;

class SymbolTableRow{
    int index;
    int location;

    SymbolTableRow(int index , int location){
        this.index = index;
        this.location = location;
    }
}

class MyAttempt{

    ArrayList<SymbolTableRow> SymbolTable = new ArrayList<SymbolTableRow>();
    FileReader fr = new FileReader("SymbolTable.txt");
    BufferedReader br = new BufferedReader(fr);

    MyAttempt()throws IOException , FileNotFoundException{
        String str;
        int index , location ;
        while((str = br.readLine())!= null){
            index = Integer.parseInt(str.split("\\s")[0]);
            location = Integer.parseInt(str.split("\\s")[1]);
            SymbolTable.add(new SymbolTableRow(index , location));
        }
    }

    boolean Valid(String word){
        String substr = word.substring(1,3);
        if(substr.equals("AD") || substr.equals("DL"))
            return false;
        else
            return true;
    }

    String Extract(String word){
        return word.substring(4,6);
    }

    String RegisterCheck(String Instruction[]){
        for(int i=0;i<Instruction.length;i++){
            if(Instruction[i].substring(1,3).equals("RG")){
                return Instruction[i].substring(4,6);
            }
        }
        return "(00)";
    }

    String FetchFromSymbolTable(String word){
        int index = Integer.parseInt(word.substring(3,4));
        int result = SymbolTable.get(index-1).location;
        return "("+Integer.toString(result)+")";
    }

    public static void main(String[] args)throws IOException , FileNotFoundException {
        MyAttempt obj = new MyAttempt();
        FileReader IC = new FileReader("IntermediateCode.txt");
        FileWriter MC = new FileWriter("MachineCode.txt");
        BufferedReader br = new BufferedReader(IC);
        String s1 , s2[] ;
        String output="";

        while((s1 = br.readLine()) != null){
            s2 = s1.trim().split("\\s");

            if(obj.Valid(s2[0])){ //check for AD , DL
                output = output.concat("("+obj.Extract(s2[0])+")");
                output = output.concat(" ");
                output = output.concat(obj.RegisterCheck(s2));
                output = output.concat(" ");
                output = output.concat(obj.FetchFromSymbolTable(s2[1]));
            }

            if(output.equals(""))
                MC.write("----------------\n");
            else
                MC.write(output+"\n");

            output = "";
        }

        MC.close();
    }
}
