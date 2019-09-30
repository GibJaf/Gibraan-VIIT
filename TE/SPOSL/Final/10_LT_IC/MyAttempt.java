import java.util.*;
import java.io.*;

class SymbolTableRow{
    int index ;
    String symbol;
    int address;
        SymbolTableRow(int index , String symbol , int address){
            this.index = index;
            this.symbol = symbol;
            this.address = address;
        }
}

class LiteralTableRow{
    int index ;
    String literal;
    int address;
        LiteralTableRow(int index , String literal , int address){
            this.index = index;
            this.literal = literal;
            this.address = address;
        }
}


public class MyAttempt{
        HashMap<String,String> AD = new HashMap<>();
        HashMap<String,String> IS = new HashMap<>();
        HashMap<String,String> DL = new HashMap<>();
        HashMap<String,String> Registers = new HashMap<>();
        ArrayList<SymbolTableRow> SymbolTable = new ArrayList<SymbolTableRow>();
        ArrayList<LiteralTableRow> LiteralTable = new ArrayList<LiteralTableRow>();
        ArrayList<Integer> PoolTable = new ArrayList<Integer>();

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

            PoolTable.add(0);

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
    }

    String Check_Symbol(String word){
        if(word.matches("[a-zA-Z]+[a-zA-Z0-9]*")){
            if(!CheckSymbolExists(word))
                return InsertIntoSymbolTable(word);
            else
                return LocationFromSymbolTable(word);
        }
        else
            return "NULL";
    }


    boolean CheckSymbolExists(String word){
        Iterator itr = SymbolTable.iterator();
        boolean flag = false;
            while(itr.hasNext()){
                SymbolTableRow sym = (SymbolTableRow)itr.next();
                if(sym.symbol.equals(word)){ // symbol found in symbol table
                    flag = true;
                    break;
                }
            }

            if(flag == false); // symbol does't exist in symbol table

                return flag;
    }

    String InsertIntoSymbolTable(String word){
        int index = SymbolTable.size();
        SymbolTable.add(new SymbolTableRow(index,word,0));
        return "(S,"+Integer.toString(index)+")"; // (S,__) is returned .
    }

    String LocationFromSymbolTable(String word){
        String result = "";
        Iterator itr = SymbolTable.iterator();
        while(itr.hasNext()){
            SymbolTableRow sym = (SymbolTableRow)itr.next();
                if(sym.symbol.equals(word)){
                    result =  "(S,"+Integer.toString(sym.index)+")";
                }
            }
            return result;
    }

    void AddAddressSymbolTable(String word , int LC){
        Iterator itr = SymbolTable.iterator();
        while(itr.hasNext()){
            SymbolTableRow sym = (SymbolTableRow)itr.next();
                if(sym.symbol.equals(word)){
                    sym.address = LC;
                }
            }
    }

    String Check_Literal(String word){
        if(word.matches("=[0-9]+")){
            if(!CheckLiteralExists(word))
                return InsertIntoLiteralTable(word);
            else
                return LocationFromLiteralTable(word);
        }
        else
            return "NULL";
    }

    boolean CheckLiteralExists(String word){
        Iterator itr = LiteralTable.iterator();
        boolean flag = false;
            while(itr.hasNext()){
                LiteralTableRow lit = (LiteralTableRow)itr.next();
                if(lit.literal.equals(word)){ // symbol found in symbol table
                    flag = true;
                    break;
                }
            }

            if(flag == false); // literal does't exist in literal table

                return flag;
    }

    String InsertIntoLiteralTable(String word){
        int index = LiteralTable.size();
        LiteralTable.add(new LiteralTableRow(index,word,0));
        return "(L,"+Integer.toString(index)+")"; // (S,__) is returned .
    }

    String LocationFromLiteralTable(String word){
        String result = "";
        Iterator itr = LiteralTable.iterator();
        while(itr.hasNext()){
            LiteralTableRow lit = (LiteralTableRow)itr.next();
                if(lit.literal.equals(word)){
                    result =  "(L,"+Integer.toString(lit.index)+")";
                }
            }
            return result;
    }

    void AddAddressLiteralTable(int LC){
        int mark = 0 ;// marks till where addresses have been allocated .
        int count = 0;// counts literals that have been allocated addresses in this run.
                      // used to update PoolTable

        for(int i=0;i<LiteralTable.size();i++){
            if(LiteralTable.get(i).address == 0){
                mark = i; break;
            }
        }

        for(int i=mark;i<LiteralTable.size();i++){
            LiteralTable.get(i).address = LC++;
            count++;
        }

        PoolTable.add(count);
    }


    // return tuple(__,__)
    String Identify(String str){
        String AD_result ;
        String IS_result ;
        String DL_result ;
        String Register  ;
        boolean const_result ;
        String symbol_result ;
        String literal_result;

        if((AD_result = Check_AD(str))!="NULL"){
            return "(AD,"+AD_result+")";
        }else if((IS_result = Check_IS(str))!="NULL"){
            return "(IS,"+IS_result+")";
        }else if((DL_result = Check_DL(str))!="NULL"){
            return "(DL,"+DL_result+")";
        }else if((Register = Check_Register(str))!="NULL"){
            return Register;
        }else if((const_result = Check_Constant(str)) == true){
            return "(C,"+str+")";
        }else if((symbol_result = Check_Symbol(str))!="NULL"){
            return symbol_result;
        }else if((literal_result = Check_Literal(str))!="NULL"){
            return literal_result;
        }else{
            return "Unidentified";
        }
    }


    void DisplaySymbolTable(){
        System.out.println("      SYMBOL TABLE   ");
        System.out.println("Index \t Symbol \t Address");
        Iterator itr = SymbolTable.iterator();
        while(itr.hasNext()){
            SymbolTableRow sym = (SymbolTableRow)itr.next();
            System.out.println(sym.index+" \t   "+sym.symbol+" \t\t    "+sym.address);
            }
        System.out.println("----------------");
    }

    void WriteSymbolTable(){
        Iterator itr = SymbolTable.iterator();
        try{
            FileWriter fw = new FileWriter("SymbolTable.txt");
            fw.write("      SYMBOL TABLE   \n");
            fw.write("Index \t Symbol \t Address\n");
            while(itr.hasNext()){
                SymbolTableRow sym = (SymbolTableRow)itr.next();
                fw.write(sym.index+" \t   "+sym.symbol+" \t\t    "+sym.address+"\n");
            }
            fw.close();
        }catch(Exception e){
            System.out.println(" Error in writing SymbolTable.txt => "+e);
        }
    }

    void DisplayLiteralTable(){
        System.out.println("      LITERAL TABLE   ");
        System.out.println("Index \t Literal \t Address");
        Iterator itr = LiteralTable.iterator();
        while(itr.hasNext()){
            LiteralTableRow lit = (LiteralTableRow)itr.next();
            System.out.println(lit.index+" \t   "+lit.literal+" \t\t    "+lit.address);
            }
        System.out.println("----------------");
    }

    void WriteLiteralTable(){
        Iterator itr = LiteralTable.iterator();
        try{
            FileWriter fw = new FileWriter("LiteralTable.txt");
            fw.write("      LITERAL TABLE   \n");
            fw.write("Index \t Literal \t Address \n");
            while(itr.hasNext()){
                LiteralTableRow lit = (LiteralTableRow)itr.next();
                fw.write(lit.index+" \t\t   "+lit.literal+" \t\t    "+lit.address+"\n");
            }
            fw.close();
        }catch(Exception e){
            System.out.println(" Error in writing LiteralTable.txt => "+e);
        }
    }


    void DisplayPoolTable(){
        System.out.println("      POOL TABLE   ");
        Iterator itr = PoolTable.iterator();
        while(itr.hasNext()){
            System.out.println("\t    "+itr.next());
        }
        System.out.println("-------------------");
    }

    void WritePoolTable(){
        Iterator itr = PoolTable.iterator();
        try{
            FileWriter pt = new FileWriter("PoolTable.txt");
            pt.write("      POOL TABLE   \n");
            while(itr.hasNext()){
                pt.write("\t    "+itr.next()+"\n");
            }
            pt.close();
        }catch(Exception e){
            System.out.println(" Error while writing PoolTable.txt =>"+e);
        }
    }

    public static void main(String[] args)throws FileNotFoundException , IOException {

        MyAttempt obj = new MyAttempt();//populate AD,IS,DL HashMap

        //FileReader fr = new FileReader("Assembly1.txt");
	FileReader fr = new FileReader("Assembly2.txt");
        FileWriter IC = new FileWriter("IntermediateCode.txt");
        BufferedReader br = new BufferedReader(fr);
        String s1 = null , result;

        s1 = br.readLine();
        for(int LC = 0 ; s1!=null ; LC++){
            String s2[] = s1.split("\\s");

            // Just printing each line
            System.out.print(" Line : "+LC+"\t");
            for(int i=0;i<s2.length;i++){
                System.out.print(" "+s2[i]);
            } System.out.println();

            // Processing each word
            for(int i=0;i<s2.length;i++)
            {
                if(s2[0].equals("START"))
                    LC = Integer.parseInt(s2[1]) - 1;

                if(s2[i].equals("DC"))
                    obj.AddAddressSymbolTable(s2[i-1],LC);

                if(s2[i].equals("LTORG") || s2[i].equals("END"))
                    obj.AddAddressLiteralTable(LC);

                result = obj.Identify(s2[i]);
                System.out.println("word is "+result);
                IC.write(" "+result);
            }
            System.out.println("---------------");
            System.out.println();
            s1 = br.readLine();
            IC.write("\n");
        }


        obj.DisplaySymbolTable();
        obj.WriteSymbolTable();
        obj.DisplayLiteralTable();
        obj.WriteLiteralTable();
        obj.DisplayPoolTable();
        obj.WritePoolTable();
        IC.close();
    }
}
