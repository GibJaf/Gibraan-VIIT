import static org.junit.Assert.*;





import org.junit.Test;



public class HangmanTest {

	Hangman h=new Hangman();

	

	@Test()			//To test whether correctGuess variable was initialized properly or not

	public void testHangman1() {

		

		if(h.correctGuesses==0) {

			System.out.println("correctGuess was initialized successfully to 0");

			assertTrue(true);

		}

			

		else

		{

			System.out.println("correctGuess was not intialized properly");

			assertTrue(false);

		}

	}

	

	@Test			//To test whether incorrectGuess variable was initialized properly or not

	public void testHangman2() {

		

		if(h.incorrectGuesses==0) {

			System.out.println("incorrectGuess was initialized successfully to 0");

			assertTrue(true);

		}

			

		else

		{

			System.out.println("incorrectGuess was not intialized properly");

			assertTrue(false);

		}

	}

	

	@Test		//To test whether userInput frame  was initialized properly or not

	public void testHangman3() {

		

		if("".equals(h.userInput.getText())) {

			System.out.println("userInput frame was initialized successfully");

			assertTrue(true);

		}

			

		else

		{

			System.out.println("userInput frames was not intialized properly");

			assertTrue(false);

		}

	}

	

	@Test		//To test whether letter frame  was initialized properly or not

	public void testHangman4() {

		

		if("The letters you guess correctly go down here!".equals(h.letters.getText())) {

			System.out.println("Letters frame was initialized successfully");

			assertTrue(true);

		}

			

		else

		{

			System.out.println("Letters frame was not intialized properly");

			assertTrue(false);

		}

	}

	

	@Test  //To test whether wordselected frame  was initialized properly or not

	public void testHangman5() {

		

		if("Hint:It is an animal".equals(h.wordselected.getText())) {

			System.out.println("wordselected frame was initialized successfully");

			assertTrue(true);

		}

			

		else

		{

			System.out.println("wordselected frame was not intialized properly");

			assertTrue(false);

		}

	}

	



	@Test  //To test whether SeperateLetter function is working properly for single alphabet or not

	public void testSeperateLetter1() {

		String a=h.seperateLetter("a", 0, 1);

		if("a".equals(a))

		{

			System.out.println("Seperate Letter is working propely for single alphabet");

			assertTrue(true);

		}

		else

		{

			System.out.println("Seperate letter is not working properly for single alphabet");

			assertTrue(false);

		}

	}

	

	@Test  //To test whether SeperateLetter function is working properly for double alphabet or not

	public void testSeperateLetter2() {

		String a=h.seperateLetter("bc", 0, 1);

		if("b".equals(a))

		{

			System.out.println("Seperate Letter is working propely for double alphabet");

			assertTrue(true);

		}

		else

		{

			System.out.println("Seperate letter is not working properly for double alphabet");

			assertTrue(false);

		}

	}

	

	@Test		//To test whether SeperateLetter function is working properly for triple alphabet or not

	public void testSeperateLetter3() {

		String a=h.seperateLetter("cde", 0, 1);

		if("c".equals(a))

		{

			System.out.println("Seperate Letter is working propely for triple alphabet");

			assertTrue(true);

		}

		else

		{

			System.out.println("Seperate letter is not working properly for triple alphabet");

			assertTrue(false);

		}

	}

	

}


