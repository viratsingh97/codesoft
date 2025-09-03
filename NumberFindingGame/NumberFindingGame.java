import java.util.Random;
import java.util.Scanner;

public class NumberFindingGame {
    public static void main(String[] args) {
        final int MIN = 1;
        final int MAX = 100;
        final int MAX_ATTEMPTS = 5;

        Scanner scanner = new Scanner(System.in);
        String playAgain;
        int totalRounds = 0;
        int totalScore = 0;

        System.out.println(" Welcome to the Number Finding Game!");

        do {
            // Generate random number
            Random random = new Random();
            int targetNumber = random.nextInt(MAX - MIN + 1) + MIN;

            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\n I'm thinking of a number between " + MIN + " and " + MAX + ".");
            System.out.println("⏳ You have " + MAX_ATTEMPTS + " attempts to guess it.");

            // Guess loop
            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess (Attempt " + (attempts + 1) + "): ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == targetNumber) {
                    System.out.println(" Correct! You guessed it in " + attempts + " attempt(s).");
                    int roundScore = (MAX_ATTEMPTS - attempts + 1) * 10;
                    System.out.println("ou earned " + roundScore + " points this round.");
                    totalScore += roundScore;
                    guessedCorrectly = true;
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("You're out of attempts! The correct number was: " + targetNumber);
                System.out.println("ou earned 0 points this round.");
            }

            totalRounds++;


            System.out.print("\n Do you want to play again? (yes/no): ");
            playAgain = scanner.next();

        } while (playAgain.equalsIgnoreCase("yes"));

        
        System.out.println("\n Game Over!");
        System.out.println(" Rounds played: " + totalRounds);
        System.out.println(" Final Score: " + totalScore + " points");
        System.out.println(" Thanks for playing the Number Finding Game!");
        scanner.close();
    }
}