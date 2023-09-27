package wordlegame82;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main{
	
	//FILE location
    private static final String DICTIONARY_FILE = "C:/Users/tiago/Downloads/popular.txt";
    private static final int MAX_ATTEMPTS = 6;
    private static final int EXPECTED_WORD_LENGTH = 5;
    final String BG_GREEN = "\u001b[42m";
    final String BG_YELLOW = "\u001b[43m";
    final String BG_RED = "\\u001b[31m";
    final String RESET = "\u001b[0m";

    public static void main(String[] args) {
        List<String> dictionary = DictionaryLoader.loadDictionary(DICTIONARY_FILE);

        if (dictionary.isEmpty()) {
            System.out.println("Dictionary is empty. Cannot play the game.");
            return;
        }
        
        String targetWord = DictionaryLoader.selectRandomWord(dictionary).toUpperCase();
        
        int attempts = 0;

        System.out.println("Welcome to Wordle!");
        System.out.println("The target word is: " + targetWord); // Display the target word
        System.out.println("Guess the word from our dictionary.");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts.");

        Scanner scanner = new Scanner(System.in);

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Attempt " + (attempts + 1) + ": ");
            String guess = scanner.nextLine().toUpperCase();

            if (!isValidInput(guess, dictionary, targetWord)) {
                continue;
            }

            if (guess.equals(targetWord)) {
            	WordleDisplay.displayCorrectWord(guess, targetWord);
                System.out.println("Congratulations! You guessed the word: " + targetWord);
                break;
            } else {
            	 WordleDisplay.displayIncorrectWord(guess, targetWord);
                System.out.println("Incorrect guess.");
                attempts++;
                int remainingAttempts = MAX_ATTEMPTS - attempts;
                System.out.println("You have " + remainingAttempts + " attempts remaining.");
            }
        }

        if (attempts == MAX_ATTEMPTS) {
            System.out.println("Sorry, you've run out of attempts. The word was: " + targetWord);
        }

        scanner.close();
    }
    
    private static boolean isValidInput(String guess, List<String> dictionary, String targetWord) {
    	guess = guess.trim().toUpperCase(); // Converte a guess para uppercase e remove trailing e espaços
    	if(containsNumbers(guess)) {
    		System.out.println("Please don't insert numbers.");
    		return false;
    	}
    	 //System.out.println("Debug- Length of guess: " + guess.length());
    	 //System.out.println("Debug- Length of targetWord: " + targetWord.length());
    	
    	   if (guess.length() != EXPECTED_WORD_LENGTH) {
    	        System.out.println("The word must have exactly " + EXPECTED_WORD_LENGTH + " letters. Please guess again.");
    	        return false;
    	    }
    	   
    	//compara cada letra
    	    for (int i = 0; i < guess.length(); i++) {
    	        char guessedLetter = guess.charAt(i);
    	        char targetLetter = targetWord.charAt(i);
    	       // System.out.println("Debug- Guessed Letter: " + guessedLetter);
    	        //System.out.println("Debug Target Letter: " + targetLetter);

    	        if (guessedLetter != targetLetter) {
    	            //System.out.println("Character mismatch at position " + (i + 1) + ": " + guessedLetter + " (Guessed) vs. " + targetLetter + " (Target)");
    	        }
    	    }
    	
    	guess = guess.toUpperCase();//converte a guess para maiusculo
    	for(int i = 0; i<dictionary.size(); i++) {
    		dictionary.set(i, dictionary.get(i).toUpperCase());
    	}
    	//System.out.println("Debug Guess (Uppercase): "+guess);
    	if (guess.length() != EXPECTED_WORD_LENGTH) {
    		 if (guess.length() > EXPECTED_WORD_LENGTH) {
                 System.out.println("The word can only have 5 letters. You inserted more than allowed. Please guess again");
             } else {
                 System.out.println("The word must have exactly " + EXPECTED_WORD_LENGTH + " letters. Please guess again.");
             }
             return false;
    	}
    	 //System.out.println("Debug- contains targetWord (Uppercase): " + dictionary.contains(targetWord.toUpperCase()));
    	//System.out.println("Debug - (Uppercase):" + targetWord.toUpperCase());
    	 if (!dictionary.contains(guess)) {
             System.out.println("The word '" + guess + "' is not in our dictionary. Please guess again.");
             return false;
         }
         return true;
             
     }
    
    
    private static boolean containsNumbers(String input) {
        return input.matches(".*\\d.*");
    }
}