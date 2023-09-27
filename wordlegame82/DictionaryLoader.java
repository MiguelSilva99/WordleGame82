package wordlegame82;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DictionaryLoader {
	 private static final int EXPECTED_WORD_LENGTH = 5;
	 public static List<String> loadDictionary(String fileName) {
	        List<String> dictionary = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String word = line.trim();
	                if (word.length() == EXPECTED_WORD_LENGTH) {
	                    dictionary.add(word);
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading dictionary file: " + e.getMessage());
	        }
	        return dictionary;
	    }

	    public static String selectRandomWord(List<String> dictionary) {
	        Random random = new Random();
	        return dictionary.get(random.nextInt(dictionary.size()));
	    }
	}
