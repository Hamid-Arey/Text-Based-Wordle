import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // List of words to choose from
        String[] words = { "java", "programming", "hangman", "computer", "game" };
        Random random = new Random();
        String wordToGuess = words[random.nextInt(words.length)];
        char[] wordArray = wordToGuess.toCharArray();
        char[] displayWord = new char[wordArray.length];
        Arrays.fill(displayWord, '_');
        char[] wrongGuesses = new char[26]; // Assuming a maximum of 26 wrong guesses (one for each letter)
        int wrongGuessCount = 0;
        int attemptsLeft = 6; // Number of incorrect guesses allowed
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");

        boolean wordGuessed = false;

        while (attemptsLeft != 0 && !wordGuessed) {
            System.out.println("\nWord to guess: " + new String(displayWord));
            System.out.print("Wrong guesses: ");
            for (int i = 0; i < wrongGuessCount; i++) {
                System.out.print(wrongGuesses[i] + ", ");
            }
            System.out.println();
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.print("Guess a letter: ");
            char guess = scanner.nextLine().toLowerCase().charAt(0);

            boolean alreadyGuessed = false;
            for (int i = 0; i < wrongGuessCount; i++) {
                if (wrongGuesses[i] == guess) {
                    alreadyGuessed = true;
                    break;
                }
            }
            for (char c : displayWord) {
                if (c == guess) {
                    alreadyGuessed = true;
                    break;
                }
            }

            if (alreadyGuessed) {
                System.out.println("You've already guessed that letter. Try again.");
                continue;
            }

            boolean correctGuess = false;
            for (int i = 0; i < wordArray.length; i++) {
                if (wordArray[i] == guess) {
                    displayWord[i] = guess;
                    correctGuess = true;
                }
            }

            if (!correctGuess) {
                wrongGuesses[wrongGuessCount] = guess;
                wrongGuessCount++;
                attemptsLeft--;
            }

            wordGuessed = new String(displayWord).equals(wordToGuess);
        }

        if (wordGuessed) {
            System.out.println("\nCongratulations! You've guessed the word: " + wordToGuess);
        } else {
            System.out.println("\nYou've run out of attempts. The word was: " + wordToGuess);
        }

        scanner.close();
    }
}
