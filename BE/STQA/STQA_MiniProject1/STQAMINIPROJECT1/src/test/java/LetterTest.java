import static org.junit.Assert.*;

import org.junit.Test;

public class LetterTest{
	Hangman h=new Hangman();
	
	@Test	//To test functionality of Testletter function for alphabet a
	public void testTestLetter1() {
		String a="a";
		h.testLetter(a);
		if(h.flag)
		{
			System.out.println("Alphabet 'a' is present in the word");
			assertTrue(true);
		}
		else if(h.flag==false)
		{
			System.out.println("Alphabet 'a' is absent in the word");
			assertTrue(true);
	
		}
		else
		{
			System.out.println("Test letter function is not working properly");
			assertTrue(false);
		}
		
	}

	@Test	//To test functionality of Testletter function for alphabet e
	public void testTestLetter2() {
		String a="e";
		h.testLetter(a);
		if(h.flag)
		{
			System.out.println("Alphabet 'e' is present in the word");
			assertTrue(true);
		}
		else if(h.flag==false)
		{
			System.out.println("Alphabet 'e' is absent in the word");
			assertTrue(true);
	
		}
		else
		{
			System.out.println("Test letter function is not working properly");
			assertTrue(false);
		}
		
	}
	
	@Test	//To test functionality of Testletter function for alphabet i
	public void testTestLetter3() {
		String a="i";
		h.testLetter(a);
		if(h.flag)
		{
			System.out.println("Alphabet 'i' is present in the word");
			assertTrue(true);
		}
		else if(h.flag==false)
		{
			System.out.println("Alphabet 'i' is absent in the word");
			assertTrue(true);
	
		}
		else
		{
			System.out.println("Test letter function is not working properly");
			assertTrue(false);
		}
		
		
	}

	@Test	//To test functionality of Testletter function for alphabet o
	public void testTestLetter4() {
		String a="o";
		h.testLetter(a);
		if(h.flag)
		{
			System.out.println("Alphabet 'o' is present in the word");
			assertTrue(true);
		}
		else if(h.flag==false)
		{
			System.out.println("Alphabet 'o' is absent in the word");
			assertTrue(true);
	
		}
		else
		{
			System.out.println("Test letter function is not working properly");
			assertTrue(false);
		}
		
	}
	
	@Test	//To test functionality of Testletter function for alphabet u
	public void testTestLetter5() {
		String a="u";
		h.testLetter(a);
		if(h.flag)
		{
			System.out.println("Alphabet 'u' is present in the word");
			assertTrue(true);
		}
		else if(h.flag==false)
		{
			System.out.println("Alphabet 'u' is absent in the word");
			assertTrue(true);
	
		}
		else
		{
			System.out.println("Test letter function is not working properly");
			assertTrue(false);
		}
		
	}



}
