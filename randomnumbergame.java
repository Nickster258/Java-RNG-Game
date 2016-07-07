/*      Author:         Nicholas Stonecipher
**      Date:           6.27.2016
**      Professor:      ####
**      Project:        #1 - Random Number Game
**      Version:        1.3 More bug fixes...
**      Disclaimer:
**      My compiler did not like splitting System.out lines into multiple lines.
**      Be it either because of Java 7 or 8, I do not know, but it would not
**      compile if I split up the print statement.
**
**      Points are not influenced if the number is invalid, out of range, or part of a command.
**
*/
import java.util.Random;
import java.util.Scanner;
 
public class randomnumbergame {
  public static void main (String[] args) {
    Scanner scan = new Scanner (System.in);

// Generation of the infamous random number
    Random rand = new Random ();
    int random = (rand.nextInt(1000)+1);
 
// General variables
    int wins = 0;           // Keeps track of the total wins
    int points = 100;       // Keeps tract of the total points
    int guesses = 0;        // Keeps track of total guesses
    int rounds = 1;         // Keeps track of total rounds played
    int upperLimit = 1000;  // Sets the upper limit of the guess
    int lowerLimit = 1;     // Sets the lower limit of the guess
    int pointsLost = 0;     // Keeps track of the total points lost
    int pointsWon = 0;      // Keeps track of the total points gained
    Boolean over = false;   // Boolean that keeps the while going unless otherwise stated
    
    System.out.println("\n\tTo play the game, enter a random number from 1 to 1000.\n\tThe user starts out with 100 points. Every incorrect\n\tresponse is a deduction of 2 points while with\n\tevery correct response there is an award of 10 points.\n\tThe program will keep track of your guesses and other statistics.\n\tYou can exit the game at any time by typing \'done\'.\n");

    while (!over) {

    // Multiple statements for better pint out flow
      if (guesses == 0) {
        System.out.print("Guess a number from 1 to 1000: ");
      }
      else {
        System.out.print("Guess a number from " + lowerLimit + " to " + upperLimit + " (score=" + points + "): ");
      }

      String input = scan.nextLine();

    // Quick validation if the user wants to quit the game
      if (input.equalsIgnoreCase ("done")){
        over = true;
      }
      else {

    // Put into a try block so improper inputs can be handled without the game crashing
        try {
          int guess = Integer.parseInt(input);

      // Win detection
          if (guess == random){
            System.out.print("Congragulations, you guessed correctly! Go for round " + (rounds+1) + "? (y/n): ");
            String temp = scan.nextLine();
            if (temp.equalsIgnoreCase ("n")){
              over = true;
            }
            else {
              upperLimit = 1000;
              lowerLimit = 1;
              random = (rand.nextInt(1000)+1);
              rounds++;
            }
            points = points + 10;
            pointsWon = pointsWon + 10;
            wins++;
            guesses++;
          }

      // Loss detection
          else if ((points-2) <= 0) {
            System.out.print("Oh no, you ran out of points! Would you like to reset and try again? (y/n): ");
            String temp = scan.nextLine();
            if (temp.equalsIgnoreCase ("n")){
              over = true;
              points = points - 2;
            }
            else if (temp.equalsIgnoreCase("y")){
              upperLimit = 1000;
              lowerLimit = 1;
              points = 100;
              wins = 0;
              rounds = 0;
              guesses = 0;
              pointsLost = 0;
              pointsWon = 0;
              random = (rand.nextInt(1000)+1);
            }
            else {
              System.out.println("\'" + temp + "\' is not a valid input!");
            }
          }
 
      // Outside of range detection
          else if (guess > upperLimit || guess < lowerLimit){
            System.out.println("That number is outside of the range. Try again.");
          }
 
      // Detecting if user guessed previously guessed limits
          else if (guess == upperLimit || guess == lowerLimit){
            System.out.println("That number was already guessed and is not within the range. Try again.");
          }
 
      // Detecting and setting upperLimit
          else if (guess > random){
            upperLimit = guess;
            System.out.println("You guessed higher than the number! ");
            points = points - 2;
            pointsLost = pointsLost + 2;
            guesses++;
          }
 
      // Detecting and setting lowerLimit
          else {
            lowerLimit = guess;
            System.out.println("You guessed lower than the number! ");
            points = points - 2;
            pointsLost = pointsLost + 2;
            guesses++;
          }
        }
 
    // Prevents the game from crashing
        catch(NumberFormatException exception){
          System.out.println ("\'" + input + "\' is not a valid input!");
        }
      }
    }
 
  // Clean output of global statistics collected throughout the game
    System.out.println("\nHere are some global statistics for you!\n\tRounds played:\t" + rounds + "\n\tTotal wins:\t" + wins + "\n\tTotal guesses:\t" + guesses + "\n\tTotal points lost:\t" + pointsLost + "\n\tTotal points won:\t" + pointsWon + "\n");
  }
}
