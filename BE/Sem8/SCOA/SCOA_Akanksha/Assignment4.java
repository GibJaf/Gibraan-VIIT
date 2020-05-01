import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class demo
{
	private HashMap<String,Float>setA;
	private HashMap<String,Float>setB;
	private int size,rel_size;
	Scanner s;
	String key;
	float prob;
	private HashMap<String,Float>Union;
	private HashMap<String,Float>Intersection;
	private HashMap<String,Float>ComplementA;
	private HashMap<String,Float>ComplementB;
	private HashMap<String,Float>CartProd;
	private ArrayList<String>strA;
	private ArrayList<String>strB;
	private HashMap<String,Float>Difference;
	private float Relation1[][];
	private float Relation2[][];
	private float min_max_comp[][];

	public demo()
	{
		setA = new HashMap<String,Float>();
		setB = new HashMap<String,Float>();
		size = rel_size = 0;
		s = new Scanner(System.in);
		key = null;
		prob = 0;
		Union = new HashMap<String,Float>();
		Intersection = new HashMap<String,Float>();
		CartProd = new HashMap<String,Float>();
		ComplementA = new HashMap<String,Float>();
		ComplementB = new HashMap<String,Float>();
		strA = new ArrayList<String>();
		strB = new ArrayList<String>();
		Difference = new HashMap<String,Float>();
	}

	public void display_relation(float relation[][],int size)
	{
		for(int i=0;i<size;i++)
		{
			System.out.print("\n");
			for(int j=0;j<size;j++)
			{
				System.out.print(relation[i][j]+" ");
			}
		}
	}
	public void get_input()
	{
		System.out.print("Enter the size of set : ");
		size = s.nextInt();

		System.out.println("SetA :-");
		for(int i=0;i<size;i++)
		{
			System.out.print("Element "+(i+1)+" Key :");
			key = s.next();
			strA.add(key);
			System.out.print("Element "+(i+1)+" probability :");
			prob = s.nextFloat();
			setA.put(key, prob);
		}

		System.out.print("\nSet A : "+setA+"\n");

		System.out.println("\nSetB :-");
		for(int i=0;i<size;i++)
		{
			System.out.print("Element "+(i+1)+" Key :");
			key = s.next();
			strB.add(key);
			System.out.print("Element "+(i+1)+" probability :");
			prob = s.nextFloat();
			setB.put(key, prob);
		}

		System.out.print("\nSet B : "+setB+"\n");

		System.out.print("\nEnter the size of Fuzzy Relation : ");
		rel_size = s.nextInt();

		Relation1 = new float[rel_size][rel_size];
		Relation2 = new float[rel_size][rel_size];	
		min_max_comp = new float[rel_size][rel_size];

		System.out.println("\nEnter the elements of Fuzzy Relation 1 :- ");
		for(int i=0;i<rel_size;i++)
		{
			for(int j=0;j<rel_size;j++)
			{
				Relation1[i][j] = s.nextFloat();
			}
		}

		System.out.println("\nEnter the elements of Fuzzy Relation 2 :- ");
		for(int i=0;i<rel_size;i++)
		{
			for(int j=0;j<rel_size;j++)
			{
				Relation2[i][j] = s.nextFloat();
			}
		}

		System.out.println("\nFuzzy Relation 1 :-");
		display_relation(Relation1, rel_size);

		System.out.println("\nFuzzy Relation 2 :-");
		display_relation(Relation2, rel_size);	
	}
	public void union()
	{
		float val1 = 0;
		float val2 = 0;
		String key = null;

		for(int i=0;i<size;i++)
		{
			key = strA.get(i);
			val1 = setA.get(key);
			val2 = setB.get(key);

			if(val1>=val2)
				Union.put(key, val1);
			else
				Union.put(key, val2);
		}

		System.out.print("\nUnion : "+Union+"\n");
	}
	public void Intersection()
	{
		float val1 = 0;
		float val2 = 0;
		String key = null;

		for(int i=0;i<size;i++)
		{
			key = strA.get(i);
			val1 = setA.get(key);
			val2 = setB.get(key);

			if(val1>=val2)
				Intersection.put(key, val2);
			else
				Intersection.put(key, val1);
		}

		System.out.print("\nIntersection : "+Intersection+"\n");
	}
	public void CartesianProd()
	{
		float val1 = 0;
		float val2 = 0;
		String key1 = null;
		String key2 = null;

		for(int i=0;i<size;i++)
		{
			key1 = strA.get(i);
			val1 = setA.get(key1);
			for(int j=0;j<size;j++)
			{
				key2 = strB.get(j);
				val2 = setB.get(key2);

				if(val1>=val2)
				{
					CartProd.put(key1+" & "+key2, val2);
				}
				else
				{
					CartProd.put(key1+" & "+key2, val1);
				}
			}
		}
		System.out.print("\nCartesian Product : "+CartProd+"\n");
	}
	public void complement()
	{
		float val = 0;
		String key = null;

		for(int i=0;i<size;i++)
		{
			key = strA.get(i);
			val = setA.get(key);
			ComplementA.put(key, 1-val);
		}
		System.out.print("\nComplement A : "+ComplementA+"\n");

		for(int i=0;i<size;i++)
		{
			key = strB.get(i);
			val = setB.get(key);
			ComplementB.put(key, 1-val);
		}
		System.out.print("\nComplement B : "+ComplementB+"\n");
	}
	public void difference()
	{
		float val1 = 0;
		float val2 = 0;
		String key = null;

		for(int i=0;i<size;i++)
		{
			key = strB.get(i);
			val1 = ComplementB.get(key);
			val2 = setA.get(key);

			if(val1>=val2)
				Difference.put(key, val2);
			else
				Difference.put(key, val1);
		}
		System.out.print("\nDifference A - B : "+Difference+"\n");
	}
	public void minmaxcomp()
	{
		float temp_Arr[] = new float[rel_size];
		float min;
		int x = 0;

		for(int i=0;i<rel_size;i++)
		{
			for(int j=0;j<rel_size;j++)
			{
				for(int k=0;k<rel_size;k++)
				{
					min = Math.min(Relation1[i][k],Relation2[k][j]);
					temp_Arr[x++] = min;
				}
				Arrays.parallelSort(temp_Arr);
				min_max_comp[i][j] = temp_Arr[rel_size-1];
				x = 0;
			}
		}

		System.out.println("\nMin Max Composition :-");
		for(int i=0;i<rel_size;i++)
		{
			System.out.print("\n");
			for(int j=0;j<rel_size;j++)
			{
				System.out.print(min_max_comp[i][j]+" ");
			}
		}
	}
}


public class Assignment4 {
	public static void main(String[] args) {
		demo d = new demo();
		d.get_input();
		d.union();
		d.Intersection();
		d.CartesianProd();
		d.complement();
		d.difference();
		d.minmaxcomp();
	}
}
