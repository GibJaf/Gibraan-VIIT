import java.util.*;

public class CalcJNI{

    static{
        System.loadLibrary("Calc");
    }

    private native void doCalculate(int a , int b , char ch);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Enter a number : ");
        int a = sc.nextInt();
        System.out.println(" Enter one more number : ");
        int b = sc.nextInt();
        System.out.println(" Enter operation : ");
        char ch = sc.next().charAt(0);
        new CalcJNI().doCalculate(a,b,ch);
    }
}
