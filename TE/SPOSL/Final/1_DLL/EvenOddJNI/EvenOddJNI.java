import java.util.*;

public class EvenOddJNI{

	static
	{
		System.loadLibrary("evenodd");
	}

	private native void Determine(int x);


public static void main(String args[]){
	Scanner sc = new Scanner(System.in);
	System.out.print(" Enter a  number : ");
	int x = sc.nextInt();
	new EvenOddJNI().Determine(x);
}
}
