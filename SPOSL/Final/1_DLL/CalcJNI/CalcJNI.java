import java.util.Scanner;
public class CalcJNI
{
	static
	{
		System.loadLibrary("Calc");
	}

	private native void doCalculate(int a,int b,char ch);

	public static void main(String[] args)
	{
		int a,b;
		char ch;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter first number:");
		a=sc.nextInt();
		System.out.println("Enter second number:");
		b=sc.nextInt();
		System.out.println("Enter Operation('+' '-' '*' '/':");
		ch=sc.next().charAt(0);
		new CalcJNI().doCalculate(a,b,ch);
	}
}
