import java.util.*;
import java.io.*;

class MNT_Row{
    int index;
    String name;
    int start , end ;

    MNT_Row(int index , String name , int start , int end){
        this.index = index;
        this.name = name;
        this.start = start;
        this.end = end;
    }
}

class MDT_Row{
    int index;
    String def;

    MDT_Row(int index , String def){
        this.index = index;
        this.def = def;
    }
}

class MyAttempt{
    ArrayList<String> Macro = new ArrayList<String>();
    ArrayList<MNT_Row> MNT = new ArrayList<MNT_Row>();
    ArrayList<MDT_Row> MDT = new ArrayList<MDT_Row>();


    void InsertMacro(){
        int index = MNT.size();
        int start = MDT.size();
        int size = Macro.size() - 1; //-1 cuz it includes Macro name as first element .
        int end = start + size;
        String name = Macro.get(0).split("\\s")[0];
        MNT.add(new MNT_Row(index+1 , name , start+1 , end));
        for(int i=1;i<Macro.size();i++){
            MDT.add(new MDT_Row(++start , Macro.get(i)));
        }
    }

    void DisplayMNT(){
        System.out.println("           MNT   ");
        System.out.println("Index \t Name \t Start \t End");
        Iterator itr = MNT.iterator();
        while(itr.hasNext()){
            MNT_Row mnt = (MNT_Row)itr.next();
            System.out.println(mnt.index+" \t "+mnt.name+" \t  "+mnt.start+" \t  "+mnt.end);
            }
        System.out.println("----------------");
    }

    void DisplayMDT(){
        System.out.println("           MDT   ");
        System.out.println("Index \t Definition");
        Iterator itr = MDT.iterator();
        while(itr.hasNext()){
            MDT_Row mdt = (MDT_Row)itr.next();
            System.out.println(mdt.index+" \t "+mdt.def);
            }
        System.out.println("----------------");
    }

    public static void main(String[] args)throws FileNotFoundException , IOException {
        FileReader fr = new FileReader("Assembly.txt");
        BufferedReader br = new BufferedReader(fr);
        String s1;

        MyAttempt obj = new MyAttempt();

        while((s1 = br.readLine())!=null){
            if(s1.trim().equals("MACRO")){
                while(!(s1 = br.readLine()).equals("MEND")){
                    obj.Macro.add(s1);
                }
                obj.InsertMacro();
                obj.Macro.clear();
            }
        }

        obj.DisplayMNT();
        obj.DisplayMDT();
    }
}
