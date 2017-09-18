/*
 * MemoryGame.java
 * Emily Van Laarhoven
 * CS230 Problem 4
 * Due: 11/12/16 @11:59pm
 */

/*
 * I belive this code works correctly - I used a lot of println statements to debug 
 * but I deleted a lot of them as I was cleaning up my code by mistake -oops!
 * The most useful one I put back, which is the one that displays the cards on the table
 * when you guess so you can test it by choosing cards you already know form a pair
 * or not and see how the matching cards are replaced if a pair is found.  I set up a 
 * way for the code to handle when there are no more cards to deal out at the end.
 * Please see the script for testing, as I did all of my testing by running the program.
 */


import java.util.*;

public class MemoryGame {
  
  //instance variables
  private int cardsLeft;
  private LinkedList<Card> table;
  private Deck myDeck;
  private boolean gameOver;
  private Card guess1;
  private Card guess2;
  
  //constructor
  public MemoryGame() {
    gameOver = false;
    guess1=null;
    guess2=null;
    cardsLeft=42; //starts at 52 total minus 10 cards on table
    myDeck = new Deck(); //create a starter deck (in order)
    myDeck.shuffle(); //shuffle the deck
    table = new LinkedList<Card>(); //collection of cards on the table
    for (int i=0; i<10; i++) {
      table.add(myDeck.getNext());
    }
//    System.out.println(table.toString()); //for testing purposes to see if matches exist
    String welcome = "Welcome to the memory game. \n";
    String goal = "Your goal is to match the cards in the deck in the fewest rounds.";
    String startMsg = welcome+goal;
    System.out.println(startMsg);
    
  }
  
  public int getGuess() {
    Scanner scan = new Scanner(System.in);
    int choice = scan.nextInt(); //assuming correct input - could also throw exceptions if needed
    scan.close();
    if (choice==0) { //if they want to quit
      gameOver=true;
      System.out.println("Thanks for playing! Bye!");
      return -1;
    } else {
      return choice;
    }
  }
  
  public void playOneRound() {
    String instructions1 = "Pick the card number [1-10]. Type 0 to stop the game.";
    String instructions2 = "Pick the second card number [1-10]. Type 0 to stop the game.";
      
    //FIRST GUESS
    System.out.println(instructions1);
    int choice1 = getGuess()-1;
    if (!gameOver) {
      guess1 = table.get(choice1);//store card at index guessed
      System.out.println("Guess 1 = "+guess1.toString());
      String print = "";
      for (int j=1; j<11; j++) {
        if (j==choice1+1) {
          print+=guess1.toString(); //add string representation to string to be printed
        } else {
          print+= "["+j+"]";
        }
      }
      System.out.println("hand should be same: "+table.toString());
      System.out.println(print);
    }
    
    // SECOND GUESS
    if (!gameOver) {
      System.out.println(instructions2);
      int choice2 = getGuess()-1;
      if (!gameOver) {
        guess2 = table.get(choice2);
        System.out.println("Guess 2 = "+guess2.toString());
        String print2 = ""; //print cards on table
        for (int k=1; k<11; k++) {
          if (k==choice1+1) {
            print2+=guess1.toString();
          }
          if (k==choice2+1) {
            print2+=guess2.toString();
          } else {
            print2+= "["+k+"]";
          }
        }
        //MATCH? YES OR NO
        if (guess1.getNumber()==guess2.getNumber()) { //yes match
          System.out.println("You found a match!");
          if (cardsLeft>1) { //if there are still cards that can be dealt out
          table.remove(choice1);
          table.add(choice1,myDeck.getNext()); //fill in the blank spots with new cards
          table.remove(choice2);
          table.add(choice2,myDeck.getNext());
          cardsLeft=cardsLeft-2;
          } else{ //if there are no cards left delete the pair by using the object rather than the index
            table.remove(guess1);
            table.remove(guess2); //don't have any cards left to deal out so don't replace them
          }
        }else { //no match
          System.out.println(print2);
          System.out.println("Not a match! Try again!");
        }
      }
    }
  }

  private boolean getGameOver() {
    return (cardsLeft<2 || !gameOver);
  }
          
      
  public static void main (String [] args) {
    MemoryGame game = new MemoryGame();

    //play until either user quits or there are no cards left
    while (game.getGameOver()) { 
        game.playOneRound();
    }
    
  }
  
}
    
    
    
    
  