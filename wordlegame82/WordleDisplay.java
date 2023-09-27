package wordlegame82;

public class WordleDisplay {
    // ANSI color codes
    private static final String BG_GREEN = "\u001b[42m";
    private static final String BG_RED = "\u001b[41m";
    private static final String BG_YELLOW = "\u001b[43m";
    private static final String RESET = "\u001b[0m";

    public static void displayCorrectWord(String word, String targetWord) {
       // targetWord = targetWord.toUpperCase(); 
        System.out.print("Congratulations! You guessed the word: ");
        for (int i = 0; i < targetWord.length(); i++) {
            char letter = targetWord.charAt(i);
            char guessedLetter = word.charAt(i);
            if (letter == guessedLetter) {
                System.out.print(BG_GREEN + letter + RESET);
            } else {
                System.out.print(letter);
            }
        }
        System.out.println();
    }

    public static void displayIncorrectWord(String word, String targetWord) {
        System.out.print("Incorrect guess: ");
        for (int i = 0; i < word.length(); i++) {
            char guessedLetter = word.charAt(i);
            char targetLetter = targetWord.charAt(i);

            if (guessedLetter == targetLetter) {
                System.out.print(BG_GREEN + guessedLetter + RESET);
            } else if (targetWord.contains(String.valueOf(guessedLetter))) {
                System.out.print(BG_YELLOW + guessedLetter + RESET);
            } else {
                System.out.print(BG_RED + guessedLetter + RESET);
            }
        }
        System.out.println();
    }
}
