

	import javax.swing.*;

	import java.awt.event.*;

	import java.awt.*;



	public class Hangman implements ActionListener{

	    public JFrame frame;

	    public JTextField userInput;

	    public JLabel textContents;

	    public JLabel letters;

	    public JLabel wordselected;



	    String array = "ant bat cat dog fish goat hen lion monkey ox penguin rat snake tiger whale yak zebra";

	    String[] split=array.split("\\ ");

	    

	    

	    int i=(int)(Math.random()*17);

	    public String word=split[i];

	    public int correctGuesses;

	    public int incorrectGuesses;

	    public String guessesLeft;

	    public boolean lose = false;

	    public boolean flag;

	    public StringBuilder wordsGuessedCorrectly;

	    

	    Hangman() {

	        incorrectGuesses = 0;

	        correctGuesses = 0;

	        guessesLeft = "You have " + (6 - incorrectGuesses) +" chances left to guess a " + word.length() + " letter word";

	        wordsGuessedCorrectly = new StringBuilder();



	        frame = new JFrame("A Hangman Game");

	        frame.setSize(1380,720);

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        frame.setLayout(new GridLayout(4, 1));



	        userInput = new JTextField();

	        userInput.setHorizontalAlignment(JLabel.CENTER);

	        userInput.addActionListener(this);

	        frame.add(userInput);



	        textContents = new JLabel();

	        textContents.setText(guessesLeft);

	        textContents.setHorizontalAlignment(JLabel.CENTER);

	        textContents.setBorder(BorderFactory.createLineBorder(Color.BLUE));

	        frame.add(textContents);



	        letters = new JLabel("The letters you guess correctly go down here!");

	        letters.setHorizontalAlignment(JLabel.CENTER);

	        letters.setBorder(BorderFactory.createLineBorder(Color.RED));

	        frame.add(letters);

	        

	       wordselected =new JLabel("Hint:It is an animal");

	       wordselected.setHorizontalAlignment(JLabel.CENTER);

	       frame.add(wordselected);

	        frame.setVisible(true);

	        



	    }



	    String seperateLetter(String a, int x, int y) {

	        return a.substring(x, y);

	    }







	    void testLetter(String a) {

	    	flag=false;

	    	System.out.println(word);

	        if (word.contains(a) && wordsGuessedCorrectly.toString().contains(a) == false && correctGuesses != (word.length() - 1)) {

	            correctGuesses++;

	            wordsGuessedCorrectly.append(a);

	            letters.setText(wordsGuessedCorrectly.toString());

	            textContents.setText("Correct Guess!");

	            flag=true;

	        }

	        else if (word.contains(a) && wordsGuessedCorrectly.toString().contains(a)) {

	            textContents.setText("You've already guessed this letter!");

	            flag=true;

	        }

	        else if (word.contains(a) && wordsGuessedCorrectly.toString().contains(a) == false && correctGuesses == (word.length() - 1)) {

	            lose = true;

	            textContents.setText("You Win!");

	            wordsGuessedCorrectly.append(a);

	            letters.setText(wordsGuessedCorrectly.toString());

	            wordselected.setText("You guessed it right, the correct word was "+word);

	            flag=true;

	        }

	        else if (word.contains(a) == false && incorrectGuesses == 5){

	            textContents.setText("You lose!");

	            wordselected.setText("Ohh.. hard luck this time, the correct word was "+word);

	            lose = true;

	        }

	        else {

	            incorrectGuesses++;

	            textContents.setText("Incorrect Guess! You have " + (6 - incorrectGuesses)  + " left.");

	        }

	    }



	    public void actionPerformed(ActionEvent ae) {

	        if (lose == false) {

	        	String a=userInput.getText();

	            testLetter(seperateLetter(a, 0 , 1));

	            userInput.setText("");

	        }

	    }



	    public static void main(String[] args) {

	        SwingUtilities.invokeLater(new Runnable() {

	            public void run() {

	                new Hangman();

	            }

	        }); 

	    }

	}


